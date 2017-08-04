package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
@Embeddable
@Entity
public class Step {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stepId;

    @JsonView
    @NotEmpty
    private String headline;

    @JsonView
    @OneToMany(cascade = {CascadeType.ALL})
    @NotEmpty
    private List<StepContent> contentList;

    @JsonView
    @NotNull
    private Integer stepIndex;

    protected Step(){}

    public Step(String headline, List<StepContent> contentList, Integer stepIndex){
        this.headline = headline;
        this.contentList.addAll(contentList);
        this.stepIndex = stepIndex;
    }

    public void setHeadline(String headline){ this.headline = headline; }

    public String getHeadline(){ return headline; }

    public void addStepContentToContentList(StepContent stepContent){ contentList.add(stepContent); }

    public List<StepContent> getContentList(){ return contentList; }

    public void setStepIndex(Integer stepIndex){ this.stepIndex = stepIndex; }

    public Integer getStepIndex(){ return stepIndex; }

    @Override
    public String toString(){
        return "Step{ " + "headline: " + headline + " contentList: " + contentList + " stepIndex: " + stepIndex + " }";
    }

}
