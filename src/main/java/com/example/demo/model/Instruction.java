package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int instructionId;

    @JsonView
    @NotEmpty
    private String title;

    @JsonView
    @NotEmpty
    private String author;

    @JsonView
    @NotEmpty
    private String creationDate;

    //private List<> список тегов к инструкции
    @JsonView
    @NotEmpty
    private String category;

    @JsonView
    @NotEmpty
    private String shortDescription;

    @JsonView
    @OneToMany(cascade = {CascadeType.ALL})
    @NotEmpty
    private List<Step> stepsList;

    protected Instruction(){}

    public Instruction(String title, String author, String creationDate, String category, String shortDescription, List<Step> stepsList){
        this.title = title;
        this.author = author;
        this.creationDate = creationDate;
        this.category = category;
        this.shortDescription = shortDescription;
        this.stepsList.addAll(stepsList);
    }

    public int getInstructionId(){ return instructionId; }

    public void setTitle(String title){ this.title = title; }

    public String getTitle(){ return title; }

    public void setAuthor(String author){ this.author = author; }

    public String getAuthor(){ return author; }

    public void setCreationDate(String creationDate){ this.creationDate = creationDate; }

    public String getCreationDate(){ return creationDate; }

    public void setCategory(String category){ this.category = category; }

    public String getCategory(){ return category; }

    public void setShortDescription(String shortDescription){ this.shortDescription = shortDescription; }

    public String getShortDescription(){ return shortDescription; }

    public void addStepToStepsList(Step step) { stepsList.add(step); }

    public List<Step> getStepsList(){ return stepsList; }

    @Override
    public String toString(){
        return "Instruction{ " + " title: " + title + " author: " + author + " creationDate: " + creationDate + " category: " + category + " shortDescription: " + shortDescription + " stepsList: " + stepsList;
    }
}
