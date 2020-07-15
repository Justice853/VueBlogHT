package com.cxp.bloght.service.impl;

import com.cxp.bloght.dao.TypeDoMapper;
import com.cxp.bloght.exception.NotFoundException;
import com.cxp.bloght.pojo.TypeDo;
import com.cxp.bloght.pojo.TypeDoExample;
import com.cxp.bloght.service.TypeDoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeDoServiceImpl implements TypeDoService {
    @Autowired
    TypeDoMapper typeDoMapper;

    @Override
    public int save (TypeDo typeDo) {
        return typeDoMapper.insert ( typeDo );
    }

    @Override
    public TypeDo getType (Long id) {
        return typeDoMapper.getType ( id ) ;
    }

    @Override
    public TypeDo getTypeByName (String name) {
        return typeDoMapper.fingByName ( name );
    }

    @Override
    public List<TypeDo> list ( ) {
        TypeDoExample typeDoExample = new TypeDoExample ();
        typeDoExample.setOrderByClause ( "id desc" );
        return typeDoMapper.selectByExample ( typeDoExample );
    }

    @Override
    @CacheEvict(allEntries = true)
    public int updateType (Long id,TypeDo typeDo) {
        TypeDo t = typeDoMapper.selectByPrimaryKey ( id );
        if (t==null){
            throw  new NotFoundException ( "不存在该类型" );
        }
        BeanUtils.copyProperties ( typeDo,t );
        return typeDoMapper.updateByPrimaryKey ( typeDo ) ;
    }

    @Override
    public void deleteType (Long id) {
        typeDoMapper.deleteByPrimaryKey ( id );
    }



}
