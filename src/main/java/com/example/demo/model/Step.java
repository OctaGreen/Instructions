package com.example.demo.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stepId;

    @NotEmpty
    private String headline;

    @OneToMany
    @NotEmpty
    private List<StepContent> contentList;

    protected Step(){}

    public Step(String headline, List<StepContent> contentList){
        this.headline = headline;
        this.contentList.addAll(contentList);
    }

    public void setHeadline(String headline){ this.headline = headline; }

    public String getHeadline(){ return headline; }

    public void addStepContentToContentList(StepContent stepContent){ contentList.add(stepContent); }

    public List<StepContent> getContentList(){ return contentList; }

}
