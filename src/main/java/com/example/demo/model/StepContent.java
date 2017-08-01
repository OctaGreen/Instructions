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

    @NotEmpty
    private String information;

///Можно выбросить при условии что порядок сохраняется в таблице?
    @NotEmpty
    private Integer elementId;
////

    protected StepContent(){}

    public StepContent(String type, String information, Integer elementId){
        this.type = type;
        this.information = information;
        this.elementId = elementId;
    }

    public void setType(String type){ this.type = type; }

    public String getType(){ return type; }

    public void setInformation(String information){ this.information = information; }

    public String getInformation(){ return information; }

    public void setElementId(Integer elementId){ this.elementId = elementId; }

    public Integer getElementId(){ return elementId; }
}
