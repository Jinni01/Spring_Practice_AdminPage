package com.midasin.spr;

import com.midasin.spr.user.User;
import com.midasin.spr.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    UserServiceImpl service;

    @ModelAttribute("context_path")
    public String getContextPath(HttpServletRequest request) {
        return request.getContextPath();
    }

    @GetMapping(value = "/")
    public String login(HttpSession session) {
        if(session != null) {
            User user = (User) session.getAttribute("user");
            if(user != null){
                return "redirect:/user/manage-admin";
            }
        }

        return "login";
    }
}
