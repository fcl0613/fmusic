package com.fcl.fmusic.controller;

import com.fcl.fmusic.service.AdminService;
import com.fcl.fmusic.vo.Result;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 登录页
     * @return
     */
    @GetMapping(value = {"/","/login"})
    public String toLogin(){
        return "login";
    }

    /**
     * 登录验证
     * @param account
     * @param password
     * @param captcha
     * @param request
     * @param session
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public Result adminLogin(@RequestParam("username") String account,
                             @RequestParam("password") String password,
                             @RequestParam("captcha") String captcha,
                             HttpServletRequest request,
                             HttpSession session){
        //验证码判断
        if (!CaptchaUtil.ver(captcha, request)) {
            CaptchaUtil.clear(request);  // 清除session中的验证码
            return Result.fail("验证码错误");
        }
        boolean flag = adminService.verifyPassword(account, password);
        if (flag){
            session.setAttribute("account",account);
            return Result.success();
        }
        return Result.fail("用户名或密码错误");
    }

    /**
     * 登录成功后去的页面
     * @return
     */
    @GetMapping("/index.html")
    public String indexPage(){
        return "index";
    }

    /**
     * 欢迎页
     * @return
     */
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
