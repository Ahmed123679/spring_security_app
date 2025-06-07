package com.medo.spring_security_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController 
{

    @RequestMapping(path="/")
    public String home()
    {
        return "index";
    }
    @RequestMapping(path="/login", method=RequestMethod.GET)
    public String login()
    {
            return "login";
    }
     @RequestMapping(path="/logout-sucess", method=RequestMethod.GET)
    public String logout()
    {
        return "logout";

    }
     @RequestMapping(path="login-error", method=RequestMethod.GET)
    public String logineError()
    {
        return "login-error";

    }
}
