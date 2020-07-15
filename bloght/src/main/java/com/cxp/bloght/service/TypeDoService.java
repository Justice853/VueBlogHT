package com.cxp.bloght.service;

import com.cxp.bloght.pojo.TagsDo;
import com.cxp.bloght.pojo.TypeDo;

import java.util.List;

public interface TypeDoService {
    int save (TypeDo typeDo);

    TypeDo getType (Long id);

    TypeDo getTypeByName (String name);

    List<TypeDo> list ( );

    int updateType (Long id, TypeDo typeDo);

    void deleteType (Long id);




}
