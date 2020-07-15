package com.cxp.bloght.controller;

import com.cxp.bloght.pojo.FriendDo;
import com.cxp.bloght.pojo.Result;
import com.cxp.bloght.service.FriendDoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@RestController
public class FriendController {
    @Autowired
    private FriendDoService friendDoService;

    @CrossOrigin
    @GetMapping("api/friendlinks")
    public PageInfo<FriendDo> friend(Model model, @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        PageHelper.startPage (pageNum,3);
        List<FriendDo> listFriend = friendDoService.listFriendLink ();
        PageInfo<FriendDo> pageInfo = new PageInfo<> ( listFriend );
        return pageInfo;
    }

    @CrossOrigin
    @PostMapping("api/friendlinks")
    public Result post(@RequestBody FriendDo friendDo){
        FriendDo friendDo1 = friendDoService.getFriendLinkByBlogaddress ( friendDo.getBlogaddress () );
        if(friendDo1 !=null){
            return new Result ( 202 );
        }
        friendDo.setCreate_time ( new Date (  ) );
        int f = friendDoService.saveFriendLink ( friendDo );
        if(f==0){
            return  new Result ( 500 );
        }
        else {
           return new Result ( 200 );
        }
    }

    @CrossOrigin
    @PostMapping("api/friendlinks/{id}")
    public Result editPost(@RequestBody FriendDo friendDo){
        FriendDo friendDo1 = friendDoService.getFriendLinkByBlogaddress ( friendDo.getBlogaddress () );
        if(friendDo1 !=null){
            return new Result ( 202 );
        }
        friendDo.setCreate_time ( new Date (  ) );
        int t = friendDoService.updateFriendLink ( friendDo );
        if (t == 0 ) {
            return  new Result ( 500 );
        } else {
            return new Result ( 200 );
        }
    }
    @CrossOrigin
    @GetMapping("api/friendlinks/{id}/delete")
    public void delete(@PathVariable Long id){
        friendDoService.deleteFriendLink ( id );
    }
}
