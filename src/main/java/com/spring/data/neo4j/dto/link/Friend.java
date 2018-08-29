package com.spring.data.neo4j.dto.link;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.data.neo4j.dto.Peoper;
import org.neo4j.ogm.annotation.*;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import java.util.Set;

/**
 * Created by Administrator on 2018/6/11.
 */
//OrBlack
@RelationshipEntity(type = "朋友")
public class Friend {
    @Id
    @GeneratedValue
    private Long id;
    @Index
    private String name;
    @Property
    private Long friendNum;
    @StartNode
    private Peoper start;
    @EndNode
    private Peoper end;

    public Friend() {
    }

    public Friend(String name, Long friendNum, Peoper start, Peoper end) {
        this.name = name;
        this.friendNum = friendNum;
        this.start = start;
        this.end = end;
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

    public Long getFriendNum() {
        return friendNum;
    }

    public void setFriendNum(Long friendNum) {
        this.friendNum = friendNum;
    }

    public Peoper getStart() {
        return start;
    }

    public void setStart(Peoper start) {
        this.start = start;
    }

    public Peoper getEnd() {
        return end;
    }

    public void setEnd(Peoper end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "name='" + name + '\'' +
                ", friendNum=" + friendNum +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
