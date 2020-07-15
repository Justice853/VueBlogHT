package com.cxp.bloght.service.impl;

import com.cxp.bloght.dao.BlogDoMapper;
import com.cxp.bloght.exception.NotFoundException;
import com.cxp.bloght.pojo.BlogDo;
import com.cxp.bloght.pojo.BlogDoExample;
import com.cxp.bloght.service.BlogDoService;
import com.cxp.bloght.utils.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BlogDoServiceImpl implements BlogDoService {
    @Autowired
    BlogDoMapper blogDoMapper;


    public BlogDo getBlogById (Long id) {
        return blogDoMapper.selectByPrimaryKey ( id );
    }


    public List<BlogDo> getAlllist ( ) {
        BlogDoExample blogDoExample = new BlogDoExample ();
        blogDoExample.setOrderByClause ( "id desc" );
        return blogDoMapper.selectByExample ( blogDoExample );
    }

    @Override
    public List<BlogDo> getNoPublishList ( ) {
        return blogDoMapper.selectNoPublishByPrimaryKey ();
    }

    @Override
    public List<BlogDo> getPublishList ( ) {
        return blogDoMapper.selectPublishByPrimaryKey ();
    }

    public int saveBlog (BlogDo blogDo) {
        blogDo.setCreate_time ( new Date (  ) );
        blogDo.setUpdate_time ( new Date (  ) );
        blogDo.setViews ( 0 );
        blogDo.setComment_count (0);

        return blogDoMapper.insert ( blogDo );

        }


    @Override
    public int updateBlog (BlogDo blogDo) {
        blogDo.setUpdate_time ( new Date (  ) );
        System.out.println ("的撒说"+blogDoMapper.updateByPrimaryKey ( blogDo ));
        return blogDoMapper.updateByPrimaryKey ( blogDo );
    }


    public int updateBlog (Long id, BlogDo blogDo) {
        BlogDo b =blogDoMapper.selectByPrimaryKey ( id );
        if(b==null){
            throw new NotFoundException ( "该文章不存在" );
        }
        BeanUtils.copyProperties (blogDo,b );
        blogDo.setUpdate_time ( new Date (  ) );
        return blogDoMapper.updateByPrimaryKey ( blogDo );
    }

    public void deleteBlog (Long id) {
        blogDoMapper.deleteByPrimaryKey ( id );
    }





}
