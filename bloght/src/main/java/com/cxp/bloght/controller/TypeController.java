package com.cxp.bloght.controller;

import com.cxp.bloght.pojo.Result;
import com.cxp.bloght.pojo.TypeDo;
import com.cxp.bloght.service.TypeDoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
public class TypeController {
    @Autowired
    TypeDoService typeDoService;

    @CrossOrigin
    @GetMapping("api/types")
    public PageInfo<TypeDo> list(Model model, @RequestParam(defaultValue = "1",value = "pageNum")Integer pageNum){
        String orderBy="id desc";
        PageHelper.startPage ( pageNum,5,orderBy );
        List<TypeDo> list = typeDoService.list ();
        PageInfo<TypeDo> pageInfo = new PageInfo<> ( list );
        return pageInfo;
    }

    @CrossOrigin
    @PostMapping("api/types")
    public Result post(@RequestBody TypeDo typeDo, RedirectAttributes attributes){
        if(typeDoService.getTypeByName ( typeDo.getName () )!=null){
            return new Result ( 202 );
        }
        int t =typeDoService.save( typeDo );;
        if(t==0){
            return  new Result ( 500 );
        }else {
            return new Result ( 200 );
        }
    }

    @CrossOrigin
    @PostMapping("api/types/{id}")
    public Result editpost(@RequestBody TypeDo typeDo, @PathVariable Long id, RedirectAttributes attributes){
        if(typeDoService.getTypeByName ( typeDo.getName ())!=null){
            return new Result ( 202 );
        }
        int t =typeDoService.updateType (id, typeDo);
        if(t==0){
            return  new Result ( 500 );
        }else {
            return new Result ( 200 );
        }
    }

    @CrossOrigin
    @GetMapping("api/types/{id}/delete")
    public void delete(@PathVariable Long id, RedirectAttributes attributes){
        typeDoService.deleteType ( id );
    }
}
