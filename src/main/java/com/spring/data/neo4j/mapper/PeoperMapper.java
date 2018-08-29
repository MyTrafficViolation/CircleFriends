package com.spring.data.neo4j.mapper;

import com.spring.data.neo4j.dto.Peoper;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/4/20.
 */
@RepositoryRestResource(collectionResourceRel = "peoper",path = "peoper")
@Repository
public interface PeoperMapper extends GraphRepository<Peoper> {

    /*@Query("match (n:Peoper) return n limit {limit}")
    Set<Peoper> findAll(@Param("limit") int limit);*/
    Set<Peoper> findAll(int limit);
    Set<Peoper> findAll();
    Peoper findByName(@Param("name") String name);
}
