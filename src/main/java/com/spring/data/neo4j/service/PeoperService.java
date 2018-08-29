package com.spring.data.neo4j.service;

import com.spring.data.neo4j.dto.Peoper;
import com.spring.data.neo4j.mapper.PeoperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/4/20.
 */
@Transactional(readOnly = true)
@Service
public class PeoperService {
    @Autowired
    private PeoperMapper peoperMapper;

    public Set<Peoper> findAll() {
        return peoperMapper.findAll();
    }

    @Transactional(readOnly = false)
    public Boolean save(Peoper peoper){
        Boolean flag=true;
        try {
            peoperMapper.save(peoper);
        }catch (Exception e){
            flag=false;
        }finally {
            return flag;
        }
    }

    @Transactional(readOnly = true)
    public Peoper findByOne(Long id){return peoperMapper.findOne(id);}
    @Transactional(readOnly = true)
    public Peoper findByName(String name){return peoperMapper.findByName(name);}

}
