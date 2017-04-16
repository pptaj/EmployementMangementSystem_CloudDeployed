/**
 * 
 */
package com.ems.controllers;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Christopher Dsouza
 *
 */
@Controller
public class ManagerController {
	
	@RequestMapping(value="/employeede.htm", method=RequestMethod.POST)
	protected ModelAndView doSubmitAction(@ModelAttribute("leaves") Leaves leaves, @ModelAttribute("employee") Employee employee,
			@ModelAttribute("tasks") Tasks tasks, @ModelAttribute("perfFeedback") PerformanceFeedback perfFeedback, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		List leaveList = new ArrayList();
		List taskList = new ArrayList();
		int empID = employee.getEmpID();
		int showValue=2;
		
		perfFeedback = feedbackDAO.checkperfGiven(empID);
		if(perfFeedback!=null){
			showValue=0;
		}
		
		employee = employeeDAO.getEmployee(empID);
			
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
		
		mv.addObject("leaveList", leaveList);
		mv.addObject("employee", employee);
		mv.addObject("taskList", taskList);
		mv.addObject("showValue", showValue);
		mv.setViewName("employeeDetails");
		
		return mv;
	}
	
	
	@RequestMapping(value="/employeeLeave.htm", method=RequestMethod.POST)
	protected ModelAndView leavesUpdate(@ModelAttribute("leaves") Leaves leaves, @ModelAttribute("employee") Employee employee,
			@ModelAttribute("tasks") Tasks tasks, @ModelAttribute("perfFeedback") PerformanceFeedback perfFeedback, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		LeavesDAO leavesDAO = new LeavesDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		List leaveList = new ArrayList();
		List taskList = new ArrayList();
		int showValue = 2;
		int empID = employee.getEmpID();
		int leaveID = Integer.parseInt((String)request.getParameter("leaveID"));
		String approvalString = (String)request.getParameter("approvalStatus");
		char approvalStatus = approvalString.charAt(0);
		String comments = request.getParameter("comments");
		leavesDAO.updateLeaveStatus(leaveID,approvalStatus, comments);
		
		perfFeedback = feedbackDAO.checkperfGiven(empID);
		if(perfFeedback!=null){
			showValue=0;
		}
		
		employee = employeeDAO.getEmployee(empID);
			
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
		
		mv.addObject("leaveList", leaveList);
		mv.addObject("employee", employee);
		mv.addObject("taskList", taskList);
		mv.addObject("showValue", showValue);
		mv.setViewName("employeeDetails");
		
		return mv;
	}
	
	
	
	
	@RequestMapping(value="/employeeTask.htm", method=RequestMethod.POST)
	protected ModelAndView taskUpdate(@ModelAttribute("leaves") Leaves leaves, @ModelAttribute("employee") Employee employee,
			@ModelAttribute("tasks") Tasks tasks, @ModelAttribute("perfFeedback") PerformanceFeedback perfFeedback, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		TasksDAO tasksDAO = new TasksDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		List leaveList = new ArrayList();
		List taskList = new ArrayList();
		int showValue = 2;
		int empID = employee.getEmpID();
		int taskID = Integer.parseInt((String)request.getParameter("taskID"));
		String managerComment = (String)request.getParameter("managerComment");
		tasksDAO.updateTasks(taskID, managerComment);
		
		perfFeedback = feedbackDAO.checkperfGiven(empID);
		if(perfFeedback!=null){
			showValue=0;
		}
		
		employee = employeeDAO.getEmployee(empID);
			
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
		
		mv.addObject("leaveList", leaveList);
		mv.addObject("employee", employee);
		mv.addObject("taskList", taskList);
		mv.addObject("showValue", showValue);
		mv.setViewName("employeeDetails");
		return mv;
	}
	
	
	@RequestMapping(value="/tasksManager.htm", method=RequestMethod.POST)
	protected ModelAndView tasksCreated(@ModelAttribute(value="tasks") Tasks tasks,@ModelAttribute(value="person") Person person, 
			@ModelAttribute("manager") Manager manager, @ModelAttribute("employee") Employee employee, @ModelAttribute("workRequests") WorkRequests workRequests, 
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		TasksDAO tasksDAO = new TasksDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List empList = new ArrayList();
		List reqList = new ArrayList();
		HttpSession session =request.getSession();
		int employeeID = Integer.parseInt((String)request.getParameter("empID"));
		Employee emp = employeeDAO.getEmployee(employeeID);
		tasksDAO.createTasks(tasks.getTaskName(), tasks.getCurrentStatus(), "", tasks.getSupervisorComment(), emp);
		
		person = (Person)session.getAttribute("person");
		ManagerDAO managerDAO = new ManagerDAO();
		manager = managerDAO.searchManager(person.getEmpID());
		
		Iterator empIterator = manager.getEmployees().iterator();
		
		while(empIterator.hasNext()){
			emp = (Employee)empIterator.next();
			empList.add(emp);
		}
		
		Iterator reqIterator = manager.getWorkRequests().iterator();
		
		while(reqIterator.hasNext()){
			WorkRequests req = (WorkRequests)reqIterator.next();
			if(req.getRequestStatus().equalsIgnoreCase("Open")||req.getRequestStatus().equalsIgnoreCase("In Progress")){
				reqList.add(req);
			}
		}
		
		mv.addObject("employeeList", empList);
		mv.addObject("reqList", reqList);
		mv.setViewName("managerHome");
		return mv;
	}
	
	
	@RequestMapping(value="/deleteEmployeeTask.htm", method=RequestMethod.POST)
	protected ModelAndView tasksDeleted(@ModelAttribute(value="tasks") Tasks tasks,@ModelAttribute(value="person") Person person, 
			@ModelAttribute("manager") Manager manager, @ModelAttribute("employee") Employee employee,@ModelAttribute("workRequests") WorkRequests workRequests, 
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		TasksDAO tasksDAO = new TasksDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		ManagerDAO managerDAO = new ManagerDAO();
		List empList = new ArrayList();
		List reqList = new ArrayList();
		HttpSession session =request.getSession();
		int employeeID = Integer.parseInt((String)request.getParameter("empID"));
		Employee emp = employeeDAO.getEmployee(employeeID);
		tasksDAO.deleteTasks(tasks.getTaskID());
		
		person = (Person)session.getAttribute("person");
		manager = managerDAO.searchManager(person.getEmpID());
		
		Iterator empIterator = manager.getEmployees().iterator();
		
		while(empIterator.hasNext()){
			emp = (Employee)empIterator.next();
			empList.add(emp);
		}
		
		Iterator reqIterator = manager.getWorkRequests().iterator();
		
		while(reqIterator.hasNext()){
			WorkRequests req = (WorkRequests)reqIterator.next();
			if(req.getRequestStatus().equalsIgnoreCase("Open")||req.getRequestStatus().equalsIgnoreCase("In Progress")){
				reqList.add(req);
			}
		}
		
		mv.addObject("employeeList", empList);
		mv.addObject("reqList", reqList);
		mv.setViewName("managerHome");
		return mv;
	}
	
	
	@RequestMapping(value="/createEmployeeRequests.htm", method=RequestMethod.POST)
	protected ModelAndView createRequests(@ModelAttribute(value="tasks") Tasks tasks,@ModelAttribute(value="person") Person person, 
			@ModelAttribute("manager") Manager manager, @ModelAttribute("employee") Employee employee, @ModelAttribute("workRequests") WorkRequests workRequests, 
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		RequestDAO requestDAO =  new RequestDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		ManagerDAO managerDAO = new ManagerDAO();
		List empList = new ArrayList();
		List reqList = new ArrayList();
		HttpSession session =request.getSession();
		int employeeID = workRequests.getEmployeeID();
		employee = employeeDAO.getEmployee(employeeID);
		Iterator reqIterator = null;
		person = (Person)session.getAttribute("person");
		manager = managerDAO.searchManager(person.getEmpID());
		//workRequests = requestDAO.createWorkRequests(workRequests.getDesignation(), workRequests.getEmailAddress(), workRequests.getFirstName(), workRequests.getLastName(), workRequests.getUserRole(), workRequests.getManagerComments(), workRequests.getRequestStatus(), workRequests.getEmployeeID(), manager);
        try {
            RequestDynamoDB dynamo =new RequestDynamoDB();
            dynamo.clientdynamo();
            workRequests.setAdminComments("na");
            dynamo.createWorkrequests(workRequests.getDesignation(),
                    workRequests.getEmailAddress(),
                    workRequests.getFirstName(),
                    workRequests.getLastName(), workRequests.getUserRole(), workRequests.getManagerComments(), workRequests.getAdminComments(), workRequests.getRequestStatus(), workRequests.getEmployeeID(), manager.getManagerID());
			reqIterator =   dynamo.getRequest(manager.getManagerID()).iterator();//iterator();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}

		if(workRequests!=null){
			mv.addObject("message", "Request Successfully Created");
		}else{
			mv.addObject("message", "Request Creation failed");
		}
		Iterator empIterator = manager.getEmployees().iterator();
		
		while(empIterator.hasNext()){
			employee = (Employee)empIterator.next();
			empList.add(employee);
		}
		
		// reqIterator = manager.getWorkRequests().iterator();



		while(reqIterator.hasNext()){
			RequestDynamoDB.WorkRequest req = (RequestDynamoDB.WorkRequest)reqIterator.next();
			//WorkRequests req = (WorkRequests)reqIterator.next();
			if(req.getRequestStatus().equalsIgnoreCase("Open")||req.getRequestStatus().equalsIgnoreCase("In Progress")){
				reqList.add(req);
			}
		}
		
		mv.addObject("employeeList", empList);
		mv.addObject("reqList", reqList);
		mv.setViewName("managerHome");
		return mv;
	}
	
	
	
	@RequestMapping(value="/mgrUpdateRequests.htm", method=RequestMethod.POST)
	protected ModelAndView mgrUpdateRequests(@ModelAttribute(value="tasks") Tasks tasks,@ModelAttribute(value="person") Person person, 
			@ModelAttribute("manager") Manager manager, @ModelAttribute("employee") Employee employee, @ModelAttribute("workRequests") RequestDynamoDB.WorkRequest workRequests,//WorkRequests workRequests,
			HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		RequestDAO requestDAO =  new RequestDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		ManagerDAO managerDAO = new ManagerDAO();
		List empList = new ArrayList();
		List reqList = new ArrayList();
	  	HttpSession session =request.getSession();
		//int employeeID = Integer.parseInt((String)request.getParameter("empID"));
		//employee = employeeDAO.getEmployee(employeeID);
		
		person = (Person)session.getAttribute("person");
		manager = managerDAO.searchManager(person.getEmpID());
		
		//int workRequestID = Integer.parseInt((String)request.getParameter("workRequestID"));
		String workRequestID = (String)request.getParameter("workRequestID");
		String managerComments = (String)request.getParameter("mgrcomments");
		String status = (String)request.getParameter("requestStatus");
		int result = 0;
		//result = requestDAO.mgrUpdateTasks(workRequestID, managerComments, status);
		try {
			RequestDynamoDB dynamo =new RequestDynamoDB();
			dynamo.clientdynamo();
			result = dynamo.mgrUpdateTasks(workRequestID,managerComments,status);
			reqList = dynamo.getRequest(manager.getManagerID());
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(result>0){
			mv.addObject("message", "Update Successfully");
		}else{
			mv.addObject("message", "Update Failed");
		}
		Iterator empIterator = manager.getEmployees().iterator();
		
		while(empIterator.hasNext()){
			employee = (Employee)empIterator.next();
			empList.add(employee);
		}
		
		//reqList= requestDAO.getRequests();
		if(reqList.size()>0)
			mv.addObject("reqList", reqList);
		
		mv.addObject("employeeList", empList);
		mv.setViewName("managerHome");
		return mv;
	}

	
	@RequestMapping(value="/giveFeedback.htm", method=RequestMethod.POST)
	protected ModelAndView giveFeedback(@ModelAttribute(value="perfFeedback")PerformanceFeedback perfFeedback,@ModelAttribute(value="perfFeedback1")PerformanceFeedback perfFeedback1, @ModelAttribute(value="person") Person person,
			@ModelAttribute("manager") Manager manager, @ModelAttribute("employee")Employee employee, @ModelAttribute("workRequests")WorkRequests workRequests, HttpServletRequest request) throws IOException {
		ModelAndView mv = new ModelAndView();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		ManagerDAO managerDAO = new ManagerDAO();
		RequestDAO requestDAO =  new RequestDAO();
		FeedbackDAO feedbackDAO = new FeedbackDAO();
		List empList = new ArrayList();
		List reqList = new ArrayList();
		HttpSession session =request.getSession();
		
		int employeeID = Integer.parseInt((String)request.getParameter("empID"));
		employee = employeeDAO.getEmployee(employeeID);
		
		person = (Person)session.getAttribute("person");
		manager = managerDAO.searchManager(person.getEmpID());

		perfFeedback = feedbackDAO.giveFeedback(perfFeedback.getBusinessOperator(), perfFeedback.getPeopleDeveloper(), perfFeedback.getValueCreator(), perfFeedback.getCommunicationSkills(), perfFeedback.getTechnicalSkills(), perfFeedback.getTaskCompletionSkills(), perfFeedback.getUninformedLeavesTaken(), perfFeedback.getImprovementAreas(), perfFeedback.getEfficientAreas(), employee, manager);
		perfFeedback1 = feedbackDAO.getFeedback(employee.getEmpID());
		request.setAttribute("empID", employee.getEmpID());
		request.setAttribute("firstName", employee.getFirstName());
		request.setAttribute("lastName", employee.getLastName());
		request.setAttribute("emailAddress", employee.getEmailAddress());
		request.setAttribute("designation", employee.getDesignation());
		request.setAttribute("managerFirstName", perfFeedback1.getManager().getFirstName());
		request.setAttribute("managerLastName", perfFeedback1.getManager().getLastName());
		request.setAttribute("managerID", perfFeedback1.getManager().getManagerID());
		request.setAttribute("managerEmailAddress", perfFeedback.getManager().getEmailAddress());
		request.setAttribute("managerDesignation", perfFeedback1.getManager().getDesignation());
		request.setAttribute("valueCreator", perfFeedback1.getValueCreator());
		request.setAttribute("peopleDeveloper", perfFeedback1.getPeopleDeveloper());
		request.setAttribute("businessOperator", perfFeedback1.getBusinessOperator());
		request.setAttribute("technicalSkills", perfFeedback1.getTechnicalSkills());
		request.setAttribute("taskCompletionSkills", perfFeedback1.getTaskCompletionSkills());
		request.setAttribute("uninformedLease", perfFeedback1.getUninformedLeavesTaken());
		request.setAttribute("communicationSkills", perfFeedback1.getCommunicationSkills());
		request.setAttribute("efficientAreas", perfFeedback1.getEfficientAreas());
		request.setAttribute("improvementAreas", perfFeedback1.getImprovementAreas());

		FeedbackPDFView view=new FeedbackPDFView();
		view.buildPdfDocument(request);

		if(perfFeedback!=null){
			mv.addObject("message", "Feedback Successfully Given");
		}else{
			mv.addObject("message", "Try again. Feedback failed");
		}
		Iterator empIterator = manager.getEmployees().iterator();

		while(empIterator.hasNext()){
			employee = (Employee)empIterator.next();
			empList.add(employee);
		}

		//reqList=requestDAO.getRequests();
		try {
			RequestDynamoDB dynamo =new RequestDynamoDB();
			dynamo.clientdynamo();
			reqList = dynamo.getRequest(manager.getManagerID());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(reqList.size()>0)
			mv.addObject("reqList", reqList);
		
		mv.addObject("employeeList", empList);
		mv.setViewName("managerHome");
		return mv;
	}
}
