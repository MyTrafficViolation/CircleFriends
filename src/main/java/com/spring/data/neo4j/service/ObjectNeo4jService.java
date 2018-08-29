package com.spring.data.neo4j.service;

import com.spring.data.neo4j.mapper.ObjectNeo4jMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Administrator on 2018/4/22.
 */
@Service
public class ObjectNeo4jService {
    @Autowired
    private ObjectNeo4jMapper objectNeo4jMapper;

    public Set<Object> findAll(){
        return objectNeo4jMapper.findAll();
    }
}
