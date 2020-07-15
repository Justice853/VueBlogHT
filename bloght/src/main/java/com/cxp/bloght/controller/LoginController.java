package com.cxp.bloght.controller;

import com.cxp.bloght.pojo.Result;
import com.cxp.bloght.pojo.UserDo;
import com.cxp.bloght.service.UserDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

@Controller
//@RequestMapping("/api")
public class LoginController {
    @Autowired
    UserDoService userDoServiceImpl;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody UserDo userDo, HttpSession httpSession){
        String username =userDo.getUsername ();
        username= HtmlUtils.htmlEscape ( username );
        UserDo user=userDoServiceImpl.checkUser(username,userDo.getPassword ());
        if(user!=null){
            user.setPassword (null);
            httpSession.setAttribute ( "user",user );
            if(httpSession.getAttribute ( "user" )==null){

                return new Result(400);
            }
            else{
                System.out.println (httpSession.getAttribute ( "user" ).toString ());
                return new Result(200);
            }

        }else {
            return new Result(400);
        }

    }
}
