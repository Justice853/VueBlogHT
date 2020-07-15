package com.cxp.bloght.dao;

import com.cxp.bloght.pojo.CommentDo;
import com.cxp.bloght.pojo.CommentDoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CommentDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CommentDo record);

    int insertSelective(CommentDo record);

    List<CommentDo> selectByExample(CommentDoExample example);

    CommentDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommentDo record);

    int updateByPrimaryKey(CommentDo record);
}