package com.cxp.bloght.controller;

import com.cxp.bloght.pojo.PictureDo;
import com.cxp.bloght.pojo.Result;
import com.cxp.bloght.service.PictureDoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
public class PictureController {
    @Autowired
    private PictureDoService pictureDoService;

    //    查询照片列表
    @CrossOrigin
    @GetMapping("api/pictures")
    public PageInfo<PictureDo> pictures(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum,6);
        List<PictureDo> listFriendLink = pictureDoService.listPicture();
        PageInfo<PictureDo> pageInfo = new PageInfo<PictureDo>(listFriendLink);
        return pageInfo;
    }


    //    照片新增
    @CrossOrigin
    @PostMapping("api/postpictures")
    public Result post(@RequestBody PictureDo picture){
        int P = pictureDoService.savePicture(picture);
        if (P == 0 ) {
            return new Result (400);
        } else {
            return new Result ( 200 );
        }
    }

    //    编辑相册
    @CrossOrigin
    @PostMapping("api/editpictures/{id}")
    public Result editPost(@RequestBody PictureDo picture) {
        int P = pictureDoService.updatePicture(picture);
        if (P == 0 ) {
            return new Result (400);
        } else {
            return new Result ( 200 );
        }
    }

    //    删除照片
    @CrossOrigin
    @GetMapping("api/deletepictures/{id}")
    public void delete(@PathVariable Long id, RedirectAttributes attributes){
        pictureDoService.deletePicture(id);
    }
}
