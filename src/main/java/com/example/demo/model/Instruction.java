package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonView
    private String textinfo;

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
