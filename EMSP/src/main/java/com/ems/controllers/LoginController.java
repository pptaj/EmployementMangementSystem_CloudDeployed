/**
 * 
 */
package com.ems.controllers;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;
import com.ems.doa.*;
import com.ems.pojo.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * @author Christopher Dsouza
 *
 */

@Controller
@RequestMapping("/loginuser.htm")
public class LoginController {
	private AWSCredentials createAwsCredentials() throws IOException {
		Properties properties = new Properties();
		InputStream input;
		properties.load(getClass().getClassLoader().getResourceAsStream("aws.properties"));
		AWSCredentials credentials = new BasicAWSCredentials(properties.getProperty("accessKey"), properties.getProperty("secretKey"));
		return credentials;
	}
	@RequestMapping(method=RequestMethod.POST)
	protected ModelAndView doSubmitAction(@ModelAttribute("person") Person person, @ModelAttribute("leaves") Leaves leaves, 
			@ModelAttribute("manager") Manager manager, @ModelAttribute("employee") Employee employee,
			@ModelAttribute("tasks") Tasks tasks, @ModelAttribute("workRequests") WorkRequests workRequests, 
			@ModelAttribute("perfFeedback") PerformanceFeedback perfFeedback, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		PersonDAO personDAO = new PersonDAO();
		RequestDAO requestDAO= new RequestDAO();
		PasswordDAO passwordDAO = new PasswordDAO();
		List empList = new ArrayList();
		List leaveList = new ArrayList();
		List taskList = new ArrayList();
		List reqList = new ArrayList();
		HttpSession session =request.getSession();
		String userRoleType = "";
		String returnJSPValue="login";
		boolean verifyFlag = false;
        boolean resetFlag = false;
		
		if(session.getAttribute("person")!=null){
			person = (Person)session.getAttribute("person");
		}else{
			AmazonSimpleEmailService sesclient = new AmazonSimpleEmailServiceClient(createAwsCredentials());
			ListVerifiedEmailAddressesResult response = sesclient.listVerifiedEmailAddresses();
			List<String> verifiedList = response.getVerifiedEmailAddresses();
			if(!(verifiedList.contains(request.getParameter("username")))){
				person=null;
				verifyFlag=true;
			}
			else{
				person = personDAO.getUserRole(request.getParameter("username"),request.getParameter("password"));
				resetFlag = passwordDAO.chkResetPassword(request.getParameter("username"),request.getParameter("password"));
			}
			if(person!=null)
				session.setAttribute("person", person);
		}
		
		if(person!=null){
			userRoleType = person.getUserRole();
			if(userRoleType!=null){
				if(userRoleType.equalsIgnoreCase("admin")){
					try {
						RequestDynamoDB dynamo =new RequestDynamoDB();
						dynamo.clientdynamo();
						reqList = dynamo.getRequest();
					} catch (IOException e) {
						e.printStackTrace();
					}

					if(reqList.size()>0)
						mv.addObject("reqList", reqList);
					returnJSPValue = "adminHome";
				}
					
				else if(userRoleType.equalsIgnoreCase("employee")){
					person = (Person)session.getAttribute("person");
					EmployeeDAO employeeDAO = new EmployeeDAO();
					employee = employeeDAO.getEmployee(person.getEmpID());
					if(employee!=null){
						int showValue=0;
						Iterator leaveIterator = employee.getLeav().iterator();
						
						while(leaveIterator.hasNext()){
							Leaves lea = (Leaves)leaveIterator.next();
							leaveList.add(lea);
						}
						
						Iterator taskIterator = employee.getTasks().iterator();
						
						while(taskIterator.hasNext()){
							Tasks tas = (Tasks)taskIterator.next();
							taskList.add(tas);
						}
						
						FeedbackDAO feedbackDAO = new FeedbackDAO();
						perfFeedback = feedbackDAO.checkperfGiven(person.getEmpID());
						if(perfFeedback!=null){
							showValue=2;
						}
						mv.addObject("leaveList", leaveList);
						mv.addObject("taskList", taskList);
						mv.addObject("showValue", showValue);
						returnJSPValue = "employeeHome";
					}else{
						returnJSPValue="index";
						mv.addObject("message", "Issue with the data in backend. Please contact Admin");
					}
				}
				
				else if(userRoleType.equalsIgnoreCase("manager")){
					person = (Person)session.getAttribute("person");
					ManagerDAO managerDAO = new ManagerDAO();
					manager = managerDAO.searchManager(person.getEmpID());
					if(manager!=null){
						Iterator empIterator = manager.getEmployees().iterator();
						
						
						while(empIterator.hasNext()){
							Employee emp = (Employee)empIterator.next();
							empList.add(emp);
						}
						
						//Iterator wrkIterator = manager.getWorkRequests().iterator();
						Iterator wrkIterator = null;
						try {
							RequestDynamoDB dynamo =new RequestDynamoDB();
							dynamo.clientdynamo();
							wrkIterator =   dynamo.getRequest(manager.getManagerID()).iterator();
						} catch (IOException e) {
							e.printStackTrace();
						}

						
						while(wrkIterator.hasNext()){
                            RequestDynamoDB.WorkRequest wrk = (RequestDynamoDB.WorkRequest)wrkIterator.next();
						//	WorkRequests wrk = (WorkRequests)wrkIterator.next();
							if(wrk.getRequestStatus().equalsIgnoreCase("Open")||wrk.getRequestStatus().equalsIgnoreCase("In Progress")){
								reqList.add(wrk);
							}
						}
						
						mv.addObject("reqList", reqList);
						mv.addObject("employeeList", empList);
						returnJSPValue = "managerHome";
					}else{
						returnJSPValue="index";
						mv.addObject("message", "Issue with the data in backend. Please contact Admin");
					}
				}
				else{
					returnJSPValue="index";
					mv.addObject("message", "User Role not created correctly. Please contact Admin");
				}
			}else{
				returnJSPValue="index";
				mv.addObject("message", "User does not exist. Please contact Admin");
			}
		}
		else if(verifyFlag){
			returnJSPValue="index";
			mv.addObject("message", "Email Address is not verified. Kindly verify your email address and try again");
		}
        else if(resetFlag){
            returnJSPValue="resetPassword";
        }
		else{
			returnJSPValue="index";
			mv.addObject("message", "Credentials provided were incorrect. Kindly try again");
		}
		mv.setViewName(returnJSPValue);
		return mv;
	}
}