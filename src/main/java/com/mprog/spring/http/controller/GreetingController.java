package com.mprog.spring.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/api/v1")
public class GreetingController {


    @GetMapping("/hello")
    public ModelAndView hello(ModelAndView modelAndView,
                              HttpServletRequest request
    ) {
        modelAndView.setViewName("greeting/hello");
        return modelAndView;
    }

    @GetMapping("/bye")
    public ModelAndView bye(ModelAndView modelAndView) {
        modelAndView.setViewName("greeting/bye");
        return modelAndView;
    }
}
