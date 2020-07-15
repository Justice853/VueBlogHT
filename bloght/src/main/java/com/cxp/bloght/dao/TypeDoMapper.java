package com.cxp.bloght.dao;

import com.cxp.bloght.pojo.TypeDo;
import com.cxp.bloght.pojo.TypeDoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TypeDo record);

    int insertSelective(TypeDo record);

    List<TypeDo> selectByExample(TypeDoExample example);

    TypeDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TypeDo record);

    int updateByPrimaryKey(TypeDo record);

    TypeDo getType(Long id);

    TypeDo fingByName(String name);
}