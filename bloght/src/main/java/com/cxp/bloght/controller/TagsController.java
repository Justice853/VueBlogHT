package com.cxp.bloght.controller;

import com.cxp.bloght.pojo.Result;
import com.cxp.bloght.pojo.TagsDo;
import com.cxp.bloght.service.TagsDoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
public class TagsController {
    @Autowired
    TagsDoService tagsDoService;
    @CrossOrigin
    @GetMapping("api/tags")
    public PageInfo<TagsDo> list(@RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        String orderBy="id desc";
        PageHelper.startPage ( pageNum,5,orderBy );
        List<TagsDo> list = tagsDoService.list ();
        PageInfo<TagsDo> pageInfo = new PageInfo<> ( list );
        return pageInfo;
    }

    @CrossOrigin
    @PostMapping("api/tags")
    public Result post(@RequestBody TagsDo tagsDo, RedirectAttributes attributes){
        if(tagsDoService.getTagsByName( tagsDo.getName () )!=null){
            return new Result ( 202 );
        }
        int t =tagsDoService.save( tagsDo );
        if(t==0){
            return  new Result ( 500 );
        }else {
            return new Result ( 200 );
        }
    }
    @CrossOrigin
    @PostMapping("api/tags/{id}")
    public Result editpost(@RequestBody TagsDo tagsDo, @PathVariable Long id, RedirectAttributes attributes){
        if(tagsDoService.getTagsByName ( tagsDo.getName ())!=null){
            return new Result ( 202 );
        }
        int t =tagsDoService.updateTags (id, tagsDo);
        System.out.println (t);
        if(t==0){
            return  new Result ( 500 );
        }else {
            return new Result ( 200 );
        }
    }

    @CrossOrigin
    @GetMapping("api/tags/{id}/delete")
    public void delete(@PathVariable Long id, RedirectAttributes attributes){
        tagsDoService.deleteTags ( id );

    }
}
