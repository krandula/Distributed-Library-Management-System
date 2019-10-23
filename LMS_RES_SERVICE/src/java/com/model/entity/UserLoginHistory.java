package com.model.entity;
// Generated Apr 25, 2019 10:09:54 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserLoginHistory  implements java.io.Serializable {


     private Integer id;
     private UserLogin userLogin;
     private Date loginDateTime;
     private Date logoutDateTime;
     private Set<UserProfile> userProfiles = new HashSet<UserProfile>(0);
     private Set<BookProfile> bookProfiles = new HashSet<BookProfile>(0);

    public UserLoginHistory() {
    }

	
    public UserLoginHistory(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
    public UserLoginHistory(UserLogin userLogin, Date loginDateTime, Date logoutDateTime, Set<UserProfile> userProfiles, Set<BookProfile> bookProfiles) {
       this.userLogin = userLogin;
       this.loginDateTime = loginDateTime;
       this.logoutDateTime = logoutDateTime;
       this.userProfiles = userProfiles;
       this.bookProfiles = bookProfiles;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public UserLogin getUserLogin() {
        return this.userLogin;
    }
    
    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
    public Date getLoginDateTime() {
        return this.loginDateTime;
    }
    
    public void setLoginDateTime(Date loginDateTime) {
        this.loginDateTime = loginDateTime;
    }
    public Date getLogoutDateTime() {
        return this.logoutDateTime;
    }
    
    public void setLogoutDateTime(Date logoutDateTime) {
        this.logoutDateTime = logoutDateTime;
    }
    public Set<UserProfile> getUserProfiles() {
        return this.userProfiles;
    }
    
    public void setUserProfiles(Set<UserProfile> userProfiles) {
        this.userProfiles = userProfiles;
    }
    public Set<BookProfile> getBookProfiles() {
        return this.bookProfiles;
    }
    
    public void setBookProfiles(Set<BookProfile> bookProfiles) {
        this.bookProfiles = bookProfiles;
    }




}


