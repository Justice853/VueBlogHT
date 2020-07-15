package com.cxp.bloght.dao;

import com.cxp.bloght.pojo.MessageDo;
import com.cxp.bloght.pojo.MessageDoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MessageDo record);

    int insertSelective(MessageDo record);

    List<MessageDo> selectByExample(MessageDoExample example);

    MessageDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageDo record);

    int updateByPrimaryKey(MessageDo record);
}