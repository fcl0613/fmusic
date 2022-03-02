package com.fcl.fmusic.controller;

import com.fcl.fmusic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping(value = {"/","/login"})
    public String toLogin(){
        return "login";
    }

    @PostMapping(value = "/login")
    public String adminLogin(@RequestParam("username") String account,
                             @RequestParam("pwd") String password,
                             HttpSession session,
                             Model model){
        boolean flag = adminService.verifyPassword(account, password);
        if (flag){
            session.setAttribute("account",account);
            return "redirect:/index.html";
        }
        model.addAttribute("msg","账号密码错误");
        return "login";
    }

    @GetMapping("/index.html")
    public String indexPage(){
        return "index";
    }
}
