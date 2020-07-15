package com.cxp.bloght.service.impl;


import com.cxp.bloght.dao.UserDoMapper;
import com.cxp.bloght.pojo.UserDo;

import com.cxp.bloght.pojo.UserDoExample;
import com.cxp.bloght.service.UserDoService;
import com.cxp.bloght.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDoServiceImpl implements UserDoService {
    @Autowired
    UserDoMapper userDoMapper;
    @Override
    public UserDo checkUser (String username, String password) {
        UserDo userDo =userDoMapper.findByUsernameAndPassword (username, MD5Utils.code ( password ));
        return userDo;
    }

    @Override
    public List<UserDo> getList ( ) {
        UserDoExample userDoExample = new UserDoExample ();
        userDoExample.setOrderByClause ( "id desc" );
        return userDoMapper.selectByExample ( userDoExample );
    }
}
