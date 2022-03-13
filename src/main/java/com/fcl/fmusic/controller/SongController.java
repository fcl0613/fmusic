package com.fcl.fmusic.controller;

import com.alibaba.fastjson.JSONObject;
import com.fcl.fmusic.entity.Song;
import com.fcl.fmusic.service.SongService;
import com.fcl.fmusic.utils.FileUpload;
import com.fcl.fmusic.vo.Result;
import com.fcl.fmusic.vo.SongVo;
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
@RequestMapping("/song")
public class SongController {
    @Autowired
    SongService songService;

    @GetMapping("/singer/detail")
    @ResponseBody
    public JSONObject getSongBySingerId(@RequestParam("singerId") Integer id,
                                        @RequestParam(value = "page",defaultValue = "1") int page,
                                        @RequestParam(value = "limit", defaultValue = "10") int limit){
        JSONObject jsonObject = new JSONObject();
        PageInfo<Song> pageInfo = songService.selectPage(page, limit, "", id);
        jsonObject.put("song",pageInfo.getList());
        return jsonObject;
    }

    @PostMapping("/updatecover")
    @ResponseBody
    public Result<Object> updateCover(@RequestParam("file") MultipartFile multipartFile,
                                      @RequestParam("id") Integer songId){
        String filePath = System.getProperty("user.dir") +
                System.getProperty("file.separator") +
                "img"+System.getProperty("file.separator")+"song_img";
        //文件名=当前时间+原来的文件名
        String fileName = System.currentTimeMillis()+multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length()-4);
        try {
            FileUpload.fileUpload(multipartFile,filePath,fileName);
            Song song = new Song();
            song.setPic("/img/songPic/"+fileName);
            song.setSongId(songId);
//            boolean flag = true;
            boolean flag = songService.update(song);
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
     * 跳转到修改歌曲封面
     * @param imgPath
     * @param songId
     * @param model
     * @return
     */
    @GetMapping("/coverpicupload")
    public String toCoverPicUpload(@RequestParam("img") String imgPath,
                                   @RequestParam("id") Integer songId,
                                   Model model){
        model.addAttribute("img",imgPath);
        model.addAttribute("songId",songId);
        return "song/coverupload";
    }

    /**
     * 歌曲信息的更新
     * @param song
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public Result<Object> update(Song song){
        boolean flag = songService.update(song);
        if (flag){
            return Result.success("更新成功");
        }
        return Result.fail("更新失败");
    }

    /**
     * 跳转到歌曲修改页面
     * @param songId
     * @param model
     * @return
     */
    @GetMapping("/toupdate/{id}")
    public String toUpdate(@PathVariable("id") Integer songId, Model model){
        Song song = songService.selectById(songId);
        model.addAttribute("song",song);
        return "song/songupdate";
    }

    /**
     * 删除歌曲
     * @param songId
     * @return
     */
    @DeleteMapping("/deletebyid")
    @ResponseBody
    public Result<Object> deleteById(@RequestParam("id") Integer songId){
        boolean flag = songService.delete(songId);
        if (flag){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    /**
     * 批量删除歌曲
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
        boolean flag = songService.deleteByIds(list);
        if (flag){
            return Result.success("删除成功");
        }else {
            return Result.fail("删除失败");
        }
    }

    /**
     * 转向所有的歌曲界面
     * @return
     */
    @GetMapping("/all")
    public String toAllSongPage(){
        return "song/songall";
    }

    /**
     * 根据歌手姓名、歌曲名查找
     * @param page
     * @param limit
     * @param singerName
     * @param songName
     * @return
     */
    @GetMapping("/allsong")
    @ResponseBody
    public Result<Object> getAllSong(@RequestParam(value = "page",defaultValue = "1") int page,
                                     @RequestParam(value = "limit", defaultValue = "10") int limit,
                                     @RequestParam(value = "singername", defaultValue = "") String singerName,
                                     @RequestParam(value = "songname", defaultValue = "") String songName){
        PageInfo<SongVo> pageInfo = songService.selectAll(page, limit, songName, singerName);
        List<SongVo> list = pageInfo.getList();
        long total = pageInfo.getTotal();
        return Result.success(list,total);
    }


    /**
     * 某个歌手的歌曲的添加，加入数据库的数据的正确性没有得到保证
     * @param multipartFile
     * @param song
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result<Object> songUpload(@RequestParam("file") MultipartFile multipartFile,
                                     Song song){
        String filePath = System.getProperty("user.dir") +
                System.getProperty("file.separator") +
                "music";
        //文件名=当前时间+原来的文件名
        String fileName = System.currentTimeMillis()+multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length()-4);
        //dbPAth
        String dbPath = "/music/"+fileName;
        try {
            FileUpload.fileUpload(multipartFile,filePath,fileName);
            song.setUrl(dbPath);
            song.setPic("/img/songPic/moren.jpg");      //设置默认图片
//            boolean flag = true;
            boolean flag = songService.insert(song);
            if(flag){
                return Result.success("上传成功");
            }
            return Result.fail("上传失败");
        } catch (IOException e) {
            return Result.fail("上传失败");
        }finally {
        }
    }

    @GetMapping("add.html")
    public String toAdd(@RequestParam("singerId") String singerId,Model model){
        model.addAttribute("singerId",singerId);
//        System.out.println(singerId);
        return "song/songupload";
    }

    /**
     * 歌曲的查询、分页
     * @param page
     * @param limit
     * @param singerId
     * @param songName
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Result<Object> selectSong(@RequestParam(value = "page",defaultValue = "1") int page,
                                     @RequestParam(value = "limit", defaultValue = "10") int limit,
                                     @RequestParam(value = "singerid") Integer singerId,
                                     @RequestParam(value = "songname", defaultValue = "") String songName){
        PageInfo<Song> pageInfo = songService.selectPage(page, limit, songName, singerId);
        List<Song> list = pageInfo.getList();
        long total = pageInfo.getTotal();
        return Result.success(list,total);
    }

    /**
     * 跳转到歌曲界面
     * @return
     */
    @GetMapping("")
    public String toSongPage(@RequestParam("singerId") Integer singerId, Model model){
        model.addAttribute("singerId",singerId);
        return "song/songpage";
    }
}