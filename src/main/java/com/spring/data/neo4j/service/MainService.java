package com.spring.data.neo4j.service;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/4/20.
 */
public interface MainService<T> {
    Set<T> findAll();
    T findByOne(Long id);
    String save(T t);
    String save(Set<T> t);
    String save(List<T> t);
    String update(T t);
    T addLink(T t,T s);
    T addLink(T t,Set<T> s);

}
