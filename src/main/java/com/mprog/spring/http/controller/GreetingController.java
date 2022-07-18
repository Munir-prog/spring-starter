package com.mprog.spring.http.controller;

import com.mprog.spring.database.entity.Role;
import com.mprog.spring.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @ModelAttribute("roles")
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping("/hello")
    public String hello2(Model model,
                         HttpServletRequest request,
                         @ModelAttribute("userReadDto") UserReadDto userReadDto) {
//        model.addAttribute("user", new UserReadDto(1L, "Ivan"));
        model.addAttribute("user", userReadDto);
        return "greeting/hello";
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
    public String bye(@SessionAttribute("user") UserReadDto user, Model model) {
        return "greeting/bye";
    }
}
