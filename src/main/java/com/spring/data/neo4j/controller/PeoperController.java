package com.spring.data.neo4j.controller;

import com.spring.data.neo4j.dto.Peoper;
import com.spring.data.neo4j.dto.link.BlockList;
import com.spring.data.neo4j.dto.link.Friend;
import com.spring.data.neo4j.service.PeoperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Set;

/**
 * Created by Administrator on 2018/4/22.
 */
@RestController
@RequestMapping("/peoper")
public class PeoperController {
    @Autowired
    private PeoperService peoperService;


    @RequestMapping(value = "/findAll")
    public Set<Peoper> findAll(){
        System.out.println("--->");
        return peoperService.findAll();
    }

    @RequestMapping("/addOne")
    public Boolean addOne(Peoper peoper){
        if (peoper.getName()==null||peoper.getName().isEmpty()){
            peoper.setName("youke"+(new Date().getTime()));
        }
        if (peoperService.findByName(peoper.getName())!=null){
            return false;
        }
        return peoperService.save(peoper);
    }

    @RequestMapping("/addOneFriendTwoId")
    public Peoper addOneFriend(Long startId,Long endId){
        Peoper start=peoperService.findByOne(startId);
        Peoper end=peoperService.findByOne(endId);
        if (addFriend(start,end)){
            return peoperService.findByOne(start.getId());
        }else {
            return null;
        }
    }
    /*
    * 添加关系（朋友）
    * */
    @RequestMapping("/addOneFriendTwoUser")
    public Peoper addOneFriend(String startName,String endName){
        Peoper start=peoperService.findByName(startName);
        Peoper end=peoperService.findByName(endName);
        if (addFriend(start,end)){
            return peoperService.findByName(startName);
        }else {
            return null;
        }
    }

    private Boolean addFriend(Peoper start,Peoper end){
        Boolean flag=true;
        try {
            if (start!=null&&end!=null){
                Friend friend=new Friend("一面之交",1l,start,end);
                start.addFriends(friend);
                peoperService.save(start);
            }else {
                flag = false;
            }
        }catch (Exception e){
            flag = false;
        }finally {
            return flag;
        }
    }
    /*
    * 添加黑名单
    * */
    @RequestMapping("/addOneBlockTwoUser")
    public Peoper addOneBlockTwoUser(String startName,String endName){
        Peoper start=peoperService.findByName(startName);
        Peoper end=peoperService.findByName(endName);
        if (addBlock(start,end)){
            return peoperService.findByName(startName);
        }else {
            return null;
        }
    }

    private Boolean addBlock(Peoper start,Peoper end){
        Boolean flag=true;
        try {
            if (start!=null&&end!=null){
                BlockList friend=new BlockList("面目可憎",-1l,start,end);
                start.addBlocks(friend);
                peoperService.save(start);
            }else {
                flag = false;
            }
        }catch (Exception e){
            flag = false;
        }finally {
            return flag;
        }
    }
}
