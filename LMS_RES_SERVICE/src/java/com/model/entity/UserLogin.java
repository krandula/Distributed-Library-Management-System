package com.model.entity;
// Generated Apr 25, 2019 10:09:54 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class UserLogin  implements java.io.Serializable {


     private Integer id;
     private UserProfile userProfile;
     private UserRole userRole;
     private String username;
     private String password;
     private Date createDateTime;
     private Date updateDateTime;
     private Boolean isActive;
     private Set<UserLoginHistory> userLoginHistories = new HashSet<UserLoginHistory>(0);

    public UserLogin() {
    }

	
    public UserLogin(UserProfile userProfile, UserRole userRole) {
        this.userProfile = userProfile;
        this.userRole = userRole;
    }
    public UserLogin(UserProfile userProfile, UserRole userRole, String username, String password, Date createDateTime, Date updateDateTime, Boolean isActive, Set<UserLoginHistory> userLoginHistories) {
       this.userProfile = userProfile;
       this.userRole = userRole;
       this.username = username;
       this.password = password;
       this.createDateTime = createDateTime;
       this.updateDateTime = updateDateTime;
       this.isActive = isActive;
       this.userLoginHistories = userLoginHistories;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public UserProfile getUserProfile() {
        return this.userProfile;
    }
    
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
    public UserRole getUserRole() {
        return this.userRole;
    }
    
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getCreateDateTime() {
        return this.createDateTime;
    }
    
    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
    public Date getUpdateDateTime() {
        return this.updateDateTime;
    }
    
    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
    public Boolean getIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public Set<UserLoginHistory> getUserLoginHistories() {
        return this.userLoginHistories;
    }
    
    public void setUserLoginHistories(Set<UserLoginHistory> userLoginHistories) {
        this.userLoginHistories = userLoginHistories;
    }




}


