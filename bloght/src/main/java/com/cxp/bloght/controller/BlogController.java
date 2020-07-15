package com.cxp.bloght.controller;

import com.cxp.bloght.pojo.*;
import com.cxp.bloght.service.BlogDoService;
import com.cxp.bloght.service.TagsDoService;
import com.cxp.bloght.service.TypeDoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class BlogController {
    @Autowired
    BlogDoService blogDoService;
    @Autowired
    TypeDoService typeDoService;
    @Autowired
    TagsDoService tagsDoService;
    @CrossOrigin
    @GetMapping("api/blogList")
    public PageInfo<BlogDo> showBlog(Model model, @RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum){
        String orderBy="update_time desc";
        PageHelper.startPage ( pageNum,6,orderBy);
        List<BlogDo> list = blogDoService.getAlllist ();
        PageInfo<BlogDo> pageInfo = new PageInfo<> ( list );
        return pageInfo;
    }
    @CrossOrigin
    @GetMapping("api/blogNoPublishList")
    public PageInfo<BlogDo> showNoPublishBlog( @RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum){
        String orderBy="update_time desc";
        PageHelper.startPage ( pageNum,6,orderBy);
        List<BlogDo> list = blogDoService.getNoPublishList ();
        PageInfo<BlogDo> pageInfo = new PageInfo<> ( list );
        return pageInfo;
    }
    @CrossOrigin
    @GetMapping("api/blogPublishList")
    public PageInfo<BlogDo> showPublishBlog( @RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum){
        String orderBy="update_time desc";
        PageHelper.startPage ( pageNum,6,orderBy);
        List<BlogDo> list = blogDoService.getPublishList ();
        PageInfo<BlogDo> pageInfo = new PageInfo<> ( list );
        return pageInfo;
    }
    @CrossOrigin
    @GetMapping("api/blogs/{id}/delete")
    public void delete(@PathVariable Long id) {
        blogDoService.deleteBlog(id);
    }

    @CrossOrigin
    @PostMapping("api/blogs")
    public Result post(@RequestBody BlogDo blogDo, HttpSession httpSession) {
        blogDo.setUserDo ( (UserDo)httpSession.getAttribute ( "user" ) );
        blogDo.setUser_id ( blogDo.getUserDo ().getId () );
        blogDo.setType_id ( blogDo.getType_id ());
        blogDo.setTypeDo ( typeDoService.getType ( blogDo.getType_id () ) );
        blogDo.setTags ( tagsDoService.getTagByString ( blogDo.getTag_ids () ) );
        int b= blogDoService.saveBlog ( blogDo );

        if(b==0){
            return  new Result ( 500 );
        }else {
            return new Result ( 200 );
        }


    }
    @CrossOrigin
    @GetMapping("api/blogs/input/types")
    public List<TypeDo> types(){
        return typeDoService.list ();
    }

    @CrossOrigin
    @GetMapping("api/blogs/input/tags")
    public List<TagsDo> tags(){

        return tagsDoService.list  ();
    }
    @CrossOrigin
    @GetMapping("api/blogs/{id}/input")
    public BlogDo edit(@PathVariable Long id){
        BlogDo blogDo = blogDoService.getBlogById ( id );
        blogDo.init ();
        return blogDo;
    }

    @CrossOrigin
    @PostMapping("api/blogs/{id}")
    public Result editpost(@RequestBody BlogDo blogDo) {
        int b= blogDoService.updateBlog ( blogDo );
        if(b==0){
            return  new Result ( 500 );
        }else {
            return new Result ( 200 );
        }
    }



}
