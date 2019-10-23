package com.model.entity;
// Generated Apr 25, 2019 10:09:54 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserProfile  implements java.io.Serializable {


     private Integer id;
     private Country country;
     private UserLoginHistory userLoginHistory;
     private UserProfileType userProfileType;
     private String generatedId;
     private String firstName;
     private String lastName;
     private String mobile;
     private String address;
     private Date createDateTime;
     private Date updateDateTime;
     private Boolean isActive;
     private Boolean isApproved;
     private Set<UserLogin> userLogins = new HashSet<UserLogin>(0);

    public UserProfile() {
    }

	
    public UserProfile(Country country, UserProfileType userProfileType) {
        this.country = country;
        this.userProfileType = userProfileType;
    }
    public UserProfile(Country country, UserLoginHistory userLoginHistory, UserProfileType userProfileType, String generatedId, String firstName, String lastName, String mobile, String address, Date createDateTime, Date updateDateTime, Boolean isActive, Boolean isApproved, Set<UserLogin> userLogins) {
       this.country = country;
       this.userLoginHistory = userLoginHistory;
       this.userProfileType = userProfileType;
       this.generatedId = generatedId;
       this.firstName = firstName;
       this.lastName = lastName;
       this.mobile = mobile;
       this.address = address;
       this.createDateTime = createDateTime;
       this.updateDateTime = updateDateTime;
       this.isActive = isActive;
       this.isApproved = isApproved;
       this.userLogins = userLogins;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    public UserLoginHistory getUserLoginHistory() {
        return this.userLoginHistory;
    }
    
    public void setUserLoginHistory(UserLoginHistory userLoginHistory) {
        this.userLoginHistory = userLoginHistory;
    }
    public UserProfileType getUserProfileType() {
        return this.userProfileType;
    }
    
    public void setUserProfileType(UserProfileType userProfileType) {
        this.userProfileType = userProfileType;
    }
    public String getGeneratedId() {
        return this.generatedId;
    }
    
    public void setGeneratedId(String generatedId) {
        this.generatedId = generatedId;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
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
    public Boolean getIsApproved() {
        return this.isApproved;
    }
    
    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }
    public Set<UserLogin> getUserLogins() {
        return this.userLogins;
    }
    
    public void setUserLogins(Set<UserLogin> userLogins) {
        this.userLogins = userLogins;
    }




}


