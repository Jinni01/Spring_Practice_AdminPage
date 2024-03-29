package com.midasin.spr.user.controller;

import com.midasin.spr.pagination.Criteria;
import com.midasin.spr.pagination.PageMaker;
import com.midasin.spr.user.UserVO;
import com.midasin.spr.user.service.UserServiceImpl;
import com.midasin.spr.util.FileUtil;
import com.midasin.spr.util.PrevUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    FileUtil fileUtil;

    @Autowired
    UserServiceImpl service;

    @PostMapping(value = "/login")
    public String userlogin(UserVO user, HttpSession session){
        UserVO u = service.userSearch(user);

        if(u == null)
            return "redirect:/";

        //session.setAttribute("user", u);

        return "redirect:/user/manage-admin";
    }

    @GetMapping(value = "logout")
    public String userLogout(HttpSession session){
        //session.invalidate();

        return "redirect:/";
    }

    @PostMapping(value = "/register")
    public String userRegister(MultipartFile uploadfile, UserVO user, PrevUrl prevUrl, HttpServletRequest request) throws Throwable {
        user.setUserImage(fileUtil.SaveFile(uploadfile, request));
        service.userRegister(user);
        return "redirect:" + prevUrl.getPrevUrl();
    }

    @PostMapping(value="/modify")
    public String userModify(MultipartFile uploadfile, UserVO user, HttpServletRequest request) throws Throwable {
        //delete exist prev image
        UserVO u = service.userSearchByNo(user.getUserNo());
        String prevImageFileName = u.getUserImage();
        fileUtil.RemoveFile(request, prevImageFileName);
        //insert new image
        user.setUserImage(fileUtil.SaveFile(uploadfile, request));
        service.userModify(user);
        return "redirect:/user/info?userNo=" + Integer.toString(user.getUserNo());
    }

    @GetMapping(value = "/delete")
    public String usetDelete(UserVO user_no, HttpServletRequest request) throws Throwable {
        UserVO user = service.userSearchByNo(user_no.getUserNo());
        fileUtil.RemoveFile(request, user.getUserImage());
        service.userRemoveByNo(user_no.getUserNo());
        return "redirect:/user/manage-admin";
    }


    @GetMapping(value = "manage-admin")
    public String manageAdmin(Criteria criteria, Model model, HttpSession session){
        /*if(session != null) {
            User user = (User) session.getAttribute("user");
            if(user == null){
                return "redirect:/";
            }
        }*/
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(criteria);
        pageMaker.setTotalCount(service.userCount(criteria));

        List<UserVO> userList = service.userListup(criteria);
        model.addAttribute("userList", userList);
        model.addAttribute("pageMaker", pageMaker);

        return "manage-admin";
    }

    @GetMapping(value = "register-admin")
    public String registerAdmin(Model model, HttpServletRequest request, HttpSession session){
       /* if(session != null) {
            User user = (User) session.getAttribute("user");
            if(!user.getUserSuper()){
                return "redirect:/user/manage-admin";
            }
        }*/
        model.addAttribute("prevUrl", request.getHeader("Referer"));
        return "register-admin";
    }

    @GetMapping(value = "manage-recruit")
    public String manageRecruit(HttpSession session){
        /*if(session != null) {
            User user = (User) session.getAttribute("user");
            if(user == null){
                return "redirect:/";
            }
        }*/
        return "manage-recruit";
    }

    @GetMapping(value="info")
    public String userInfo(UserVO user_no, Model model, HttpServletRequest request, HttpSession session) {
        /*if(session != null) {
            User user = (User) session.getAttribute("user");
            if(user == null){
                return "redirect:/";
            }
        }*/
        UserVO u = service.userSearchByNo(user_no.getUserNo());
        model.addAttribute("user", u);

        return "info-admin";
    }

    @GetMapping(value = "info/download-image")
    public void userInfoDownloadImage(UserVO user_no, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserVO user = service.userSearchByNo(user_no.getUserNo());
        fileUtil.DownLoadFile(request, response, user.getUserImage());
    }

    @GetMapping(value = "modify-admin")
    public String userModifyPage(UserVO user_no, Model model){
        /*if(session != null) {
            User user = (User) session.getAttribute("user");
            if(user == null){
                return "redirect:/";
            }
        }*/
        UserVO u = service.userSearchByNo(user_no.getUserNo());
        model.addAttribute("user", u);

        return "modify-admin";
    }

    //test code
    @GetMapping("insert-dummy")
    public String insertDummy(){
        UserVO user = new UserVO();

        for(int i=1; i<=100; i++)
        {
            user.setUserID("admin" + Integer.toString(i));
            user.setUserPW("admin" + Integer.toString(i));
            user.setUserName("name" + Integer.toString(i));
            user.setUserPhone("010-0000-0000");
            user.setUserDivision("기반개발팀");
            user.setUserSuper(true);
            user.setUserImage("춘식이.png");

            //service.userRegister(user);
        }

        return "redirect:/login";
    }

}
