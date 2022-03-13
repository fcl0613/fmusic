package com.fcl.fmusic.controller;

import com.fcl.fmusic.entity.User;
import com.fcl.fmusic.service.UserService;
import com.fcl.fmusic.utils.FileUpload;
import com.fcl.fmusic.vo.Result;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 后台更新用户信息
     * @param user
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result<Object> userInfoUpdate(User user){
        boolean flag = userService.update(user);
        if (flag){
            return Result.success("更新成功");
        }
        return Result.fail("更新失败");
    }

    /**
     * 跳转到歌手更新的界面
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/toupdate/{id}")
    public String toUpdate(@PathVariable("id") Integer userId, Model model){
        User user = userService.selectUserById(userId);
        model.addAttribute("user",user);
        return "user/updatepage";
    }

    /**
     * 单个删除
     * @param userId
     * @return
     */
    @DeleteMapping("/deletebyid")
    @ResponseBody
    public Result<Object> deleteById(@RequestParam("userid") Integer userId){
        boolean flag = userService.delete(userId);
        if (flag){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("/deletebyids/{ids}")
    @ResponseBody
    public Result<Object> deleteByIds(@PathVariable("ids") String ids){
        List<String> strings = Arrays.asList(ids.split(","));
        List<Integer> list = new ArrayList<>();
        for (String s: strings) {
            list.add(Integer.valueOf(s));
        }
        boolean flag = userService.deleteByIds(list);
        if (flag){
            return Result.success("删除成功");
        }else {
            return Result.fail("删除失败");
        }
    }

    /**
     * 上传用户头像的方法
     * @param multipartFile
     * @param userId
     * @return
     */
    @PostMapping("/avatarupdate")
    @ResponseBody
    public Result<Object> avatarUpdate(@RequestParam("file")MultipartFile multipartFile,
                                       @RequestParam("id") Integer userId){
        String filePath = System.getProperty("user.dir")+
                System.getProperty("file.separator")+
                "img"+System.getProperty("file.separator")+"user_img";
        String fileName = System.currentTimeMillis()+multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length()-4);
        try {
            FileUpload.fileUpload(multipartFile,filePath,fileName);
            User user = new User();
            user.setUserId(userId);
            user.setAvatar("/img/userPic/"+fileName);
            boolean flag = userService.update(user);
            if (flag){
                return Result.success("上传成功");
            }
            return Result.fail("上传失败");
        } catch (IOException e) {
            return Result.fail("上传失败");
        }
    }

    /**
     * 转向修改用户头像的界面，貌似这个功能没什么用处
     * @return
     */
    @GetMapping("/toupdateavatar")
    public String toUpdateAvatar(@RequestParam("img") String img,
                                 @RequestParam("id") String id, Model model){
        model.addAttribute("avatar",img);
        model.addAttribute("userid",id);
        return "user/updateavatar";
    }

    /**
     * 转向用户添加页面
     * @return
     */
    @GetMapping("/add.html")
    public String toAddPage(){
        return "user/add";
    }

    /**
     * 管理员新建用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result<Object> addUser(User user){
        user.setPassword("123456");     //默认密码
        user.setAvatar("/img/userPic/moren.jpg");   //默认用户头像
        user.setIsValid(new Byte("1"));         //默认账号可用
        boolean flag = userService.insert(user);
        if (flag){
            return Result.success("添加成功");
        }
        return Result.fail("添加失败");
    }

    /**
     * 管理界面的查找
     * @param page  当前的页数
     * @param limit 每页几条记录
     * @param userName  查找的用户名  模糊查询
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Result<Object> selectSong(@RequestParam(value = "page",defaultValue = "1") int page,
                                     @RequestParam(value = "limit",defaultValue = "10") int limit,
                                     @RequestParam(value = "username",defaultValue = "") String userName){
        PageInfo<User> pageInfo = userService.selectPage(page, limit, userName);
        List<User> list = pageInfo.getList();
        long total = pageInfo.getTotal();
        return Result.success(list,total);
    }

    /**
     * 跳转到用户界面
     * @return
     */
    @GetMapping("")
    public String toUserPage(){
        return "user/userpage";
    }
}