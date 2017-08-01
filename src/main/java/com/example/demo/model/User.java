package com.example.demo.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @NotEmpty
    @Size(min = 3, max = 20)
    private String username;

    @NotEmpty
    @Size(min = 6)
    private String email;

    @NotEmpty
    private String role;

    private String avatarFilename;

    private String generalInfo;

    @OneToMany
    private List<Medal> achievementsList;

    protected User(){}

    public User(String username, String email){
        this.username = username;
        this.email = email;
    }

    public void setUsername(String username){ this.username = username; }

    public String getUsername(){ return username; }

    public void setEmail(String email){ this.email = email; }

    public String getEmail(){ return email; }

    public void setRole(String role){ this.role = role; }

    public String getRole(){ return role; }

    public void setAvatarFilename(String avatarFilename){ this.avatarFilename = avatarFilename; }

    public String getAvatarFilename(){ return avatarFilename; }

    public void setGeneralInfo(String generalInfo){ this.generalInfo = generalInfo; }

    public String getGeneralInfo(){ return generalInfo; }

    public void addMedalToAchievementsList(Medal medal){ achievementsList.add(medal); }

    public List<Medal> getAchievementsList(){ return achievementsList;}
}
