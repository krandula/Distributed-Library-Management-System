package com.model.entity;
// Generated Apr 25, 2019 10:09:54 PM by Hibernate Tools 4.3.1


import java.util.Date;


public class BookProfile  implements java.io.Serializable {


     private Integer id;
     private BookAuthor bookAuthor;
     private BookCategory bookCategory;
     private BookPrinters bookPrinters;
     private UserLoginHistory userLoginHistory;
     private String generatedId;
     private String name;
     private String isbn;
     private String description;
     private Integer bookCount;
     private Integer printedYear;
     private Date createDateTime;
     private Date updateDateTime;
     private Boolean isActive;

    public BookProfile() {
    }

	
    public BookProfile(BookAuthor bookAuthor, BookCategory bookCategory, BookPrinters bookPrinters, UserLoginHistory userLoginHistory) {
        this.bookAuthor = bookAuthor;
        this.bookCategory = bookCategory;
        this.bookPrinters = bookPrinters;
        this.userLoginHistory = userLoginHistory;
    }
    public BookProfile(BookAuthor bookAuthor, BookCategory bookCategory, BookPrinters bookPrinters, UserLoginHistory userLoginHistory, String generatedId, String name, String isbn, String description, Integer bookCount, Integer printedYear, Date createDateTime, Date updateDateTime, Boolean isActive) {
       this.bookAuthor = bookAuthor;
       this.bookCategory = bookCategory;
       this.bookPrinters = bookPrinters;
       this.userLoginHistory = userLoginHistory;
       this.generatedId = generatedId;
       this.name = name;
       this.isbn = isbn;
       this.description = description;
       this.bookCount = bookCount;
       this.printedYear = printedYear;
       this.createDateTime = createDateTime;
       this.updateDateTime = updateDateTime;
       this.isActive = isActive;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public BookAuthor getBookAuthor() {
        return this.bookAuthor;
    }
    
    public void setBookAuthor(BookAuthor bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
    public BookCategory getBookCategory() {
        return this.bookCategory;
    }
    
    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }
    public BookPrinters getBookPrinters() {
        return this.bookPrinters;
    }
    
    public void setBookPrinters(BookPrinters bookPrinters) {
        this.bookPrinters = bookPrinters;
    }
    public UserLoginHistory getUserLoginHistory() {
        return this.userLoginHistory;
    }
    
    public void setUserLoginHistory(UserLoginHistory userLoginHistory) {
        this.userLoginHistory = userLoginHistory;
    }
    public String getGeneratedId() {
        return this.generatedId;
    }
    
    public void setGeneratedId(String generatedId) {
        this.generatedId = generatedId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getIsbn() {
        return this.isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getBookCount() {
        return this.bookCount;
    }
    
    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }
    public Integer getPrintedYear() {
        return this.printedYear;
    }
    
    public void setPrintedYear(Integer printedYear) {
        this.printedYear = printedYear;
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




}


