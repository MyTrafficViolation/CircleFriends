package com.spring.data.neo4j.mapper.role;

import com.spring.data.neo4j.dto.link.Friend;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Administrator on 2018/6/13.
 */
@RepositoryRestResource(collectionResourceRel = "friend",path = "friend")
@Repository
public interface FriendMapper extends GraphRepository<Friend>{
    Set<Friend> findAll();
    Set<Friend> findAll(int limit);
}
