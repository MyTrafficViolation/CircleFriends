package com.spring.data.neo4j.mapper;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Administrator on 2018/4/22.
 */
@Repository
public interface ObjectNeo4jMapper extends GraphRepository {
    @Query("match (a) return (a)")
    Set findAll();
}
