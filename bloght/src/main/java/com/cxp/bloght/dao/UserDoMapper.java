package com.cxp.bloght.dao;

import com.cxp.bloght.pojo.UserDo;
import com.cxp.bloght.pojo.UserDoExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface UserDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserDo record);

    int insertSelective(UserDo record);

    List<UserDo> selectByExample(UserDoExample example);

    UserDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserDo record);

    int updateByPrimaryKey(UserDo record);

    UserDo findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}