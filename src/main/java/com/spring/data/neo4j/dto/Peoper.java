package com.spring.data.neo4j.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.data.neo4j.dto.link.BlockList;
import com.spring.data.neo4j.dto.link.Friend;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2018/4/20.
 */
@NodeEntity
public class Peoper {
    @Id
    @GeneratedValue
    private Long id;
    @Index
    private String name;
    @Index
    private String username;
    @Property
    private String password;
    @Index
    private String idcard;
    @Index
    private String phone;
    @Index
    private String email;
    @Property
    private String address;

    @JsonIgnoreProperties("start")
    @Relationship(type = "朋友")
    private Set<Friend> friends=new HashSet<>();
    @JsonIgnoreProperties("start")
    @Relationship(type = "黑名单")
    private Set<BlockList> blocks=new HashSet<>();


    public Peoper() {
    }

    public Peoper(String name, String username, String password, String idcard, String phone, String email,String address) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.idcard = idcard;
        this.phone = phone;
        this.email = email;
        this.address=address;
    }

    public Peoper(String username, String password) {
        this.name=username;
        this.username = username;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Friend> getFriends() {
        return friends;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
    }

    public Set<BlockList> getBlocks() {
        return blocks;
    }

    public void setBlocks(Set<BlockList> blocks) {
        this.blocks = blocks;
    }

    public void addFriends(Friend e){
        this.friends.add(e);
    }

    public void addBlocks(BlockList e){
        this.blocks.add(e);
    }
}
