package com.cxp.bloght.controller;

import com.cxp.bloght.pojo.UserDo;
import com.cxp.bloght.service.UserDoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserDoService userDoService;
    @CrossOrigin
    @GetMapping("api/userList")
    public PageInfo<UserDo> showUser( @RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum){
        String orderBy="update_time desc";
        PageHelper.startPage ( pageNum,6,orderBy);
        List<UserDo> list = userDoService.getList ();
        PageInfo<UserDo> pageInfo = new PageInfo<> ( list );
        return pageInfo;
    }

}
