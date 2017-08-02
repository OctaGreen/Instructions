package com.example.demo.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StepContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stepContentId;

    @NotEmpty
    private String type;

    private String message;

    private String files;

    @NotEmpty
    private Integer position;

    protected StepContent(){}

    public StepContent(String type, Integer position){
        this.type = type;
        this.position = position;
    }

    public void setType(String type){ this.type = type; }

    public String getType(){ return type; }

    public void setInformation(String information){ this.message = information; }

    public String getInformation(){ return message; }

    public void setFiles(String files){ this.files = files; }

    public String getFiles(){ return files; }

    public void setElementId(Integer position){ this.position = position; }

    public Integer getElementId(){ return position; }
}
