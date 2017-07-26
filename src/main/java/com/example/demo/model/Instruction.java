package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Instruction {
    @JsonView
    private String textinfo;

    @Id
    private Integer id;
    protected Instruction(){}

    public Instruction(String textinfo){
        this.textinfo = textinfo;
    }

    public String getTextInfo(){
        return textinfo;
    }

    public void setTextInfo(String text){
        this.textinfo = text;
    }
}
