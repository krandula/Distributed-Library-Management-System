package com.model.entity;
// Generated Apr 25, 2019 10:09:54 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

public class UserRole  implements java.io.Serializable {


     private Integer id;
     private String name;
     private Set<UserLogin> userLogins = new HashSet<UserLogin>(0);

    public UserRole() {
    }

    public UserRole(String name, Set<UserLogin> userLogins) {
       this.name = name;
       this.userLogins = userLogins;
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
    public Set<UserLogin> getUserLogins() {
        return this.userLogins;
    }
    
    public void setUserLogins(Set<UserLogin> userLogins) {
        this.userLogins = userLogins;
    }




}


