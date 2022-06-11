package com.mprog.spring.http.controller;

import com.mprog.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {


    @GetMapping("/hello")
    public ModelAndView hello2(ModelAndView modelAndView,
                              HttpServletRequest request) {
        modelAndView.setViewName("greeting/hello");
        modelAndView.addObject("user", new UserReadDto(1L, "Ivan"));
        return modelAndView;
    }

    @GetMapping("/hello/{id}")
    public ModelAndView hello(ModelAndView modelAndView,
                              HttpServletRequest request,
                              @RequestParam("age") Integer age,
                              @RequestHeader("accept") String accept,
                              @CookieValue("JSESSIONID") String jSessionId,
                              @PathVariable("id") Integer id
    ) {
        var ageParamValue = request.getParameter("age");
        var acceptHeader = request.getHeader("accept");
        var cookies = request.getCookies();
        modelAndView.setViewName("greeting/hello");
        return modelAndView;
    }

    @GetMapping("/bye")
    public ModelAndView bye(ModelAndView modelAndView, @SessionAttribute("user") UserReadDto user) {
        modelAndView.setViewName("greeting/bye");
        return modelAndView;
    }
}
