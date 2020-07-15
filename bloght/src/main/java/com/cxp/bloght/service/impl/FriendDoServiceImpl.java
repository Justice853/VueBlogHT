package com.cxp.bloght.service.impl;

import com.cxp.bloght.dao.FriendDoMapper;
import com.cxp.bloght.pojo.FriendDo;
import com.cxp.bloght.pojo.FriendDoExample;
import com.cxp.bloght.service.FriendDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendDoServiceImpl implements FriendDoService {

    @Autowired
    private FriendDoMapper friendDoMapper;

    @Override
    public List<FriendDo> listFriendLink ( ) {
        FriendDoExample friendDoExample = new FriendDoExample ();
        friendDoExample.setOrderByClause ("id desc");
        return  friendDoMapper.selectByExample ( friendDoExample );
    }

    @Override
    public int saveFriendLink (FriendDo friend) {
        return friendDoMapper.insert ( friend );
    }

    @Override
    public FriendDo getFriendLink (Long id) {
        return friendDoMapper.selectByPrimaryKey ( id );
    }

    @Override
    public FriendDo getFriendLinkByBlogaddress (String blogaddress) {
        return friendDoMapper.getFriendLinkByBlogaddress ( blogaddress );
    }

    @Override
    public int updateFriendLink (FriendDo friend) {
        return friendDoMapper.updateByPrimaryKey ( friend );
    }

    @Override
    public void deleteFriendLink (Long id) {
        friendDoMapper.deleteByPrimaryKey ( id );
    }
}
