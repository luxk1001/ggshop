package com.pipilu.ggshop.controller;

import com.pipilu.ggshop.bean.User;
import com.pipilu.ggshop.service.EmailService;
import com.pipilu.ggshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/selectUserAll")
    @ResponseBody
    public String selectUserAll(){
        return userService.selectUserAll().toString();
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/myajaxRegister",method = RequestMethod.POST)
    @ResponseBody
    public String sendEmail(User user,String email){
        System.out.println(user);
        System.out.println(email);
        new Thread(){
            @Override
            public void run(){
                try {
                    emailService.sendSimpleMail(email);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return "邮件已发送至您的邮箱，请激活";
    }

    @RequestMapping(value = "/activateMail")
    public String activateMail(String emailToken){
        if(emailService.balanceToken(emailToken)){
            return "success";
        }
        return "login";
    }
}
