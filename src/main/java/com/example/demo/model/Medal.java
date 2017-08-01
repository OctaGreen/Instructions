package com.example.demo.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long medalId;

    @NotEmpty
    private String medalName;

    @NotEmpty
    private String medalDescription;

    @NotEmpty
    private String medalIconFilename;

    protected Medal(){}

    public Medal(String medalName, String medalDescription, String medalIconFilename){
        this.medalName = medalName;
        this.medalDescription = medalDescription;
        this.medalIconFilename = medalIconFilename;
    }

    public void setMedalName(String medalName){ this.medalName = medalName; }

    public String getMedalName(){ return medalName; }

    public void setMedalDescription(String medalDescription){ this.medalDescription = medalDescription; }

    public String getMedalDescription(){ return medalDescription; }

    public void setMedalIconFilename(String medalIconFilename){ this.medalIconFilename = medalIconFilename; }

    public String getMedalIconFilename(){ return medalIconFilename; }
}
