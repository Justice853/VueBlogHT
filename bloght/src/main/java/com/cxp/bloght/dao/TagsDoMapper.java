package com.cxp.bloght.dao;

import com.cxp.bloght.pojo.TagsDo;
import com.cxp.bloght.pojo.TagsDoExample;
import com.cxp.bloght.pojo.TypeDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagsDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TagsDo record);

    int insertSelective(TagsDo record);

    List<TagsDo> selectByExample(TagsDoExample example);

    TagsDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TagsDo record);

    int updateByPrimaryKey(TagsDo record);

    TagsDo fingByName(String name);
}