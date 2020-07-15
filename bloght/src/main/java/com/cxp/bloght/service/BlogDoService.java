package com.cxp.bloght.service;

import com.cxp.bloght.pojo.BlogDo;

import java.util.List;

public interface BlogDoService {
    BlogDo getBlogById (Long id);

    List<BlogDo> getAlllist ( ); //查询所有

    List<BlogDo> getNoPublishList ( );

    List<BlogDo> getPublishList ( );

    int saveBlog (BlogDo blogDo);

    int updateBlog (BlogDo blogDo);

    void deleteBlog (Long id);






}
