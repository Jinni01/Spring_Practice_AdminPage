package com.midasin.spr.user.controller;

import com.midasin.spr.user.User;
import com.midasin.spr.user.pagination.Criteria;
import com.midasin.spr.user.pagination.PageMaker;
import com.midasin.spr.user.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl service;

    @PostMapping("/login")
    public String userlogin(User user, HttpSession session){
        User u = service.userSearch(user);

        if(u == null)
            return "redirect:/";

        session.setAttribute("user", u);

        return "redirect:/user/manage-admin";
    }

    @GetMapping(value = "manage-admin")
    public String manageAdmin(Criteria criteria, Model model, HttpSession session){

        if(session != null) {
            User user = (User) session.getAttribute("user");
            if(user == null){
                return "redirect:/";
            }
        }

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(criteria);
        pageMaker.setTotalCount(service.userCount(criteria));

        List<User> userList = service.userListup(criteria);
        model.addAttribute("userList", userList);
        model.addAttribute("pageMaker", pageMaker);

        return "manage-admin";
    }

    @GetMapping(value = "register-admin")
    public String registerAdmin(HttpSession session){
        if(session != null) {
            User user = (User) session.getAttribute("user");
            if(!user.getUserSuper()){
                return "redirect:/user/manage-admin";
            }
        }

        return "register-admin";
    }

    @GetMapping(value = "manage-recruit")
    public String manageRecruit(HttpSession session){

        if(session != null) {
            User user = (User) session.getAttribute("user");
            if(user == null){
                return "redirect:/";
            }
        }

        return "manage-recruit";
    }

    //test code
    @GetMapping("insert-dummy")
    public String insertDummy(){
        User user = new User();

        for(int i=1; i<=100; i++)
        {
            user.setUserID("admin" + Integer.toString(i));
            user.setUserPW("admin" + Integer.toString(i));
            user.setUserName("name" + Integer.toString(i));
            user.setUserPhone("010-0000-0000");
            user.setUserDivision("기반개발팀");
            user.setUserSuper(true);

            service.userRegister(user);
        }

        return "redirect:/login";
    }

}
