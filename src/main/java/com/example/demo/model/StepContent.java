package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
@Entity
public class StepContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stepContentId;

    @NotEmpty
    @JsonView
    private String type;

    @JsonView
    private String message;

    @JsonView
    private String files;

    @JsonView
    @NotNull
    private Integer position;

    protected StepContent(){}

    public StepContent(String type, Integer position){
        this.type = type;
        this.position = position;
    }

    public void setType(String type){ this.type = type; }

    public String getType(){ return type; }

    public void setMessage(String message){ this.message = message; }

    public String getMessage(){ return message; }

    public void setFiles(String files){ this.files = files; }

    public String getFiles(){ return files; }

    public void setPosition(Integer position){ this.position = position; }

    public Integer getPosition(){ return position; }

    @Override
    public String toString(){
        return "StepContent{ " + "type: " + type + " message: " + message + " files: " + files + " position: " + position + " }";
    }
}
