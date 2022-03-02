package com.fcl.fmusic.controller;

import com.fcl.fmusic.entity.ResultPage;
import com.fcl.fmusic.entity.Singer;
import com.fcl.fmusic.service.SingerService;
import com.fcl.fmusic.utils.PageUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;

    @GetMapping("/list")
    public String getList(@RequestParam(value = "p", defaultValue = "1") int pageNo,
                              @RequestParam(value = "size", defaultValue = "10") int size,
                              Model model){
        PageInfo<Singer> pageInfo = singerService.selectPage(pageNo, size);
        ResultPage resultPage = PageUtils.getResultPage(pageInfo);
        model.addAttribute("resultPage", resultPage);
        return "singerlist";
    }

    @GetMapping("/toAdd")
    public String toAdd(){
        return "singerinsert";
    }

    @PostMapping("/add")
    public String addSinger(Singer singer){
        System.out.println(singer);
        boolean flag = singerService.insert(singer);
        if (flag){
            return "redirect:/singer/toAdd";
        }
        return "filed";
    }
}
