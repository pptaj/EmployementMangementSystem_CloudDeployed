package com.ems.controllers;

/**
 * Created by Christopher Dsouza on 3/18/2017.
 */
import com.ems.doa.PasswordDAO;
import com.ems.doa.PersonDAO;
import com.ems.mailexchange.SendMail;
import com.ems.pojo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PasswordController {
    @RequestMapping(value="/forgotpassword.htm",method=RequestMethod.POST)
    protected ModelAndView doSubmitAction(@ModelAttribute("person") Person person, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        PersonDAO personDAO = new PersonDAO();
        String returnJSPValue = "passwordSuccess";
        person = personDAO.searchUser(request.getParameter("username"));
        if(person!=null){
            //Send email to the email id requesting password
            new SendMail().sendTestEmail(request.getParameter("username"));
        }
        mv.addObject("message", "Password Reset Details have been successfully sent to your email address. Please check your mailbox.");
        mv.setViewName(returnJSPValue);
        return mv;
    }


    @RequestMapping(value="/resetPassword.htm", method=RequestMethod.POST)
    protected ModelAndView resetPassword(@ModelAttribute("person") Person person, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView();
        PasswordDAO passwordDAO = new PasswordDAO();
        //String returnJSPValue = "index";

        if (request.getParameter("password").equals(request.getParameter("confirmpassword"))){
            int count = passwordDAO.updatePassword(request.getParameter("email"),request.getParameter("password"));
            if(count>0){
                passwordDAO.updateTempPassword(request.getParameter("email"),"");
                }
            mv.addObject("message", "Password Successfully Reset.");
            mv.setViewName("index");
        }
        else{
            mv.addObject("message", "Password does not match.");
            mv.setViewName("resetPassword");
        }
        return mv;


    }
}
