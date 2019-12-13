package com.pipilu.ggshop.com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {

    @RequestMapping(value = {"/index"})
    public String fun(){
        System.out.println("11111111111111111111");
        return "index.html";
    }

    @RequestMapping(value = {"/index1"})
    public String fun1(){
        System.out.println("11111111111111111111");
        return "redirect:a.html";
    }

    @RequestMapping(value = "/index2")
    public void fun2(){
        System.out.println("");
    }
}
