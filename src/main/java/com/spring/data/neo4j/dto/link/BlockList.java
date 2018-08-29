package com.spring.data.neo4j.dto.link;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.data.neo4j.dto.Peoper;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

/**
 * Created by Administrator on 2018/6/12.
 */
@RelationshipEntity(type = "黑名单")
public class BlockList {
    @Id
    @GeneratedValue
    private Long id;
    @Property
    private String name;
    @Property
    private Long blockNum;
    @JsonIgnore
    @StartNode
    private Peoper start;
    @EndNode
    private Peoper end;

    public BlockList() {
    }

    public BlockList(String name, Long blockNum, Peoper start, Peoper end) {
        this.name = name;
        this.blockNum = blockNum;
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

    public Long getBlockNum() {
        return blockNum;
    }

    public void setBlockNum(Long blockNum) {
        this.blockNum = blockNum;
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
        return "BlockList{" +
                "name='" + name + '\'' +
                ", blockNum=" + blockNum +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
