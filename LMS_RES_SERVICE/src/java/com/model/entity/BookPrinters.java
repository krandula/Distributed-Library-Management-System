package com.model.entity;
// Generated Apr 25, 2019 10:09:54 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

public class BookPrinters  implements java.io.Serializable {


     private Integer id;
     private String name;
     private Set<BookProfile> bookProfiles = new HashSet<BookProfile>(0);

    public BookPrinters() {
    }

    public BookPrinters(String name, Set<BookProfile> bookProfiles) {
       this.name = name;
       this.bookProfiles = bookProfiles;
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
    public Set<BookProfile> getBookProfiles() {
        return this.bookProfiles;
    }
    
    public void setBookProfiles(Set<BookProfile> bookProfiles) {
        this.bookProfiles = bookProfiles;
    }




}


