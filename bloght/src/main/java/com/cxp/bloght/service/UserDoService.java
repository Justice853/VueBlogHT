package com.cxp.bloght.service;

import com.cxp.bloght.pojo.UserDo;

import java.util.List;

public interface UserDoService {
    UserDo checkUser (String username, String password);
    List<UserDo> getList();
}
