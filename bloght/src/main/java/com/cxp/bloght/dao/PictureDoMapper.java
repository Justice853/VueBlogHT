package com.cxp.bloght.dao;

import com.cxp.bloght.pojo.PictureDo;
import com.cxp.bloght.pojo.PictureDoExample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PictureDoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PictureDo record);

    int insertSelective(PictureDo record);

    List<PictureDo> selectByExample(PictureDoExample example);

    PictureDo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PictureDo record);

    int updateByPrimaryKey(PictureDo record);
}