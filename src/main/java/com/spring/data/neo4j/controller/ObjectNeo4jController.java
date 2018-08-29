package com.spring.data.neo4j.controller;

import com.spring.data.neo4j.service.ObjectNeo4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Administrator on 2018/4/22.
 */
@RestController
@RequestMapping("/object")
public class ObjectNeo4jController {
    @Autowired
    private ObjectNeo4jService objectNeo4jService;


    @RequestMapping(value = "/findAll")
    public Set<Object> findAll(){
        System.out.println("--->");
        return objectNeo4jService.findAll();
    }
}
