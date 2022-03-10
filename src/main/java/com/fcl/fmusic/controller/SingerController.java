package com.fcl.fmusic.controller;

import com.fcl.fmusic.entity.Singer;
import com.fcl.fmusic.service.SingerService;
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
@RequestMapping("/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;

    /**
     * 转向歌手修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/toUpdate/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model){
        Singer singer = singerService.selectById(id);
        model.addAttribute("singer",singer);
        return "update";
    }

    /**
     * 根据id删除歌手
     * @param singerId
     * @return
     */
    @DeleteMapping("/deleteById")
    @ResponseBody
    public Result deleteById(@RequestParam("id") Integer singerId){
        boolean flag = singerService.delete(singerId);
        if (flag){
            return Result.success("删除成功");
        }else {
            return Result.fail("删除失败");
        }
    }

    /**
     * 歌手的批量删除
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteByIds/{ids}")
    @ResponseBody
    public Result deleteByIds(@PathVariable("ids") String ids){
        List<String> strings = Arrays.asList(ids.split(","));

        List<Integer> list = new ArrayList<>();
        for (String s: strings) {
            list.add(Integer.valueOf(s));
        }
        boolean flag = singerService.deleteByIds(list);
        if (flag){
            return Result.success("删除成功");
        }else {
            return Result.fail("删除失败");
        }
    }

    /**
     * 歌手的添加
     * @param singer
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(Singer singer){
        singer.setPic("/img/singerPic/moren.png");
        boolean flag = singerService.insert(singer);
        if (flag){
            return Result.success("添加成功");
        }else {
            return Result.fail("添加失败");
        }
    }

    /**
     * 前往歌手添加页面
     * @return
     */
    @GetMapping("/add.html")
    public String toAdd(){
        return "add";
    }

    /**
     * 上传头像
     * @param multipartFile
     * @param id
     * @return
     */
    @PostMapping("/picUpdate")
    @ResponseBody
    public Result picUpdate(@RequestParam("file") MultipartFile multipartFile,
                            @RequestParam("id") Integer id){
        String filePath = System.getProperty("user.dir") +
                System.getProperty("file.separator") +
                "img" + System.getProperty("file.separator") +
                "singer_img";
        //文件名=当前时间+原来的文件名
        String fileName = System.currentTimeMillis()+multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length()-4);
        //dbPAth
        String dbPath = "/img/singerPic/"+fileName;
        try {
            FileUpload.fileUpload(multipartFile,filePath,fileName);
            Singer singer = new Singer();
            singer.setPic(dbPath);
            singer.setSingerId(id);
//            boolean flag = true;
            boolean flag = singerService.update(singer);
            if(flag){
                return Result.success("上传成功");
            }
            return Result.fail("上传失败");
        } catch (IOException e) {
            return Result.fail("上传失败");
        }finally {
        }
    }

    /**
     * 转向上传头像的iframe
     * @param img
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/headpicupload")
    public String goHeadPicUpload(@RequestParam("img") String img,
                                  @RequestParam("id") Integer id,
                                  Model model){
        model.addAttribute("img",img);
        model.addAttribute("singerId",id);
        return "headpicupload";
    }

    /**
     *初始化singer.html
     * @return
     */
    @GetMapping("")
    public String to(){
        return "singer";
    }

    /**
     * 更新歌手信息
     * @param singer
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result updateSinger(Singer singer){
        boolean flag = singerService.update(singer);
        if (flag){
            return Result.success("更新信息成功");
        }else {
            return Result.fail("更新信息失败");
        }
    }


    /**
     * 歌手查询、遍历、分页
     * @param page
     * @param limit
     * @param searchParams
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Result<Object> getList(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "limit", defaultValue = "10") int limit,
                              @RequestParam(value = "singerName", defaultValue = "") String searchParams,
                              @RequestParam(value = "gender", defaultValue = "") String genderStr){
        Byte gender = null;
        if (!"".equals(genderStr)){
            if ("男".equals(genderStr)){
                gender = new Byte("0");
            }else if("女".equals(genderStr)){
                gender = new Byte("1");
            }else if("组合".equals(genderStr)){
                gender = new Byte("2");
            }else if("保密".equals(genderStr)){
                gender = new Byte("3");
            }
        }
        PageInfo<Singer> pageInfo = singerService.selectPage(page, limit, searchParams, gender);
        List<Singer> list = pageInfo.getList();
        long total = pageInfo.getTotal();
        return Result.success(list, total);
    }
}
