package com.cxp.bloght.dao;

import com.cxp.bloght.pojo.BlogDo;
import com.cxp.bloght.pojo.BlogDoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BlogDo record);

    int insertSelective(BlogDo record);

    List<BlogDo> selectByExampleWithBLOBs(BlogDoExample example);

    List<BlogDo> selectByExample(BlogDoExample example);

    BlogDo selectByPrimaryKey(Long id);

    List<BlogDo> selectNoPublishByPrimaryKey();

    List<BlogDo> selectPublishByPrimaryKey();

    int updateByPrimaryKeySelective(BlogDo record);

    int updateByPrimaryKeyWithBLOBs(BlogDo record);

    int updateByPrimaryKey(BlogDo record);

}