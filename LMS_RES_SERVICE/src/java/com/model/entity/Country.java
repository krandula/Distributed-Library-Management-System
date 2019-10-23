package com.model.entity;
// Generated Apr 25, 2019 10:09:54 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

public class Country  implements java.io.Serializable {


     private Integer id;
     private String name;
     private Set<UserProfile> userProfiles = new HashSet<UserProfile>(0);

    public Country() {
    }

    public Country(String name, Set<UserProfile> userProfiles) {
       this.name = name;
       this.userProfiles = userProfiles;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set<UserProfile> getUserProfiles() {
        return this.userProfiles;
    }
    
    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }




}


