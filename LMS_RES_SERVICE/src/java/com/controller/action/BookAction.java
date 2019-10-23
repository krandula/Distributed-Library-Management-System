
package com.controller.action;

import com.model.db.HibernateUtil;
import com.model.entity.BookAuthor;
import com.model.entity.BookCategory;
import com.model.entity.BookPrinters;
import com.model.entity.BookProfile;
import com.model.entity.UserLoginHistory;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;


public class BookAction {


    public String saveNewBookCategory(String name) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            name=name.replaceAll("\\+", " ");
            Query qr = s.createQuery("FROM BookCategory a WHERE a.name='" + name + "'");
            if (!qr.list().isEmpty()) {
                msg = "This name already exists";
            } else {
                BookCategory bc = new BookCategory();
                bc.setName(name);

                s.save(bc);

                s.beginTransaction().commit();
                s.flush();
                s.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }

    //deleteBookCategory
    public String deleteBookCategory(int categoryID) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            Query qr1 = s.createQuery("DELETE BookProfile a WHERE a.bookCategory.id=" + categoryID);
            qr1.executeUpdate();

            BookCategory bc = (BookCategory) s.load(BookCategory.class, categoryID);
            s.delete(bc);

            s.beginTransaction().commit();
            s.flush();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }

    //saveNewBookPrinters
    public String saveNewBookPrinters(String name) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            name=name.replaceAll("\\+", " ");
            Query qr = s.createQuery("FROM BookPrinters a WHERE a.name='" + name + "'");
            if (!qr.list().isEmpty()) {
                msg = "This name already exists";
            } else {
                BookPrinters bp = new BookPrinters();
                bp.setName(name);

                s.save(bp);

                s.beginTransaction().commit();
                s.flush();
                s.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }

    //deleteBookPrinters
    public String deleteBookPrinters(int printersID) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            Query qr1 = s.createQuery("DELETE BookProfile a WHERE a.bookPrinters.id=" + printersID);
            qr1.executeUpdate();

            BookPrinters bp = (BookPrinters) s.load(BookPrinters.class, printersID);
            s.delete(bp);

            s.beginTransaction().commit();
            s.flush();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }

    //saveNewBookAuthor
    public String saveNewBookAuthor(String name) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            name=name.replaceAll("\\+", " ");
            Query qr = s.createQuery("FROM BookAuthor a WHERE a.name='" + name + "'");
            if (!qr.list().isEmpty()) {
                msg = "This name already exists";
            } else {
                BookAuthor ba = new BookAuthor();
                ba.setName(name);

                s.save(ba);

                s.beginTransaction().commit();
                s.flush();
                s.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }

    //deleteBookAuthor
    public String deleteBookAuthor(int authorID) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            Query qr1 = s.createQuery("DELETE BookProfile a WHERE a.bookAuthor.id=" + authorID);
            qr1.executeUpdate();

            BookAuthor ba = (BookAuthor) s.load(BookAuthor.class, authorID);
            s.delete(ba);

            s.beginTransaction().commit();
            s.flush();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }

    //saveNewBook
    public String saveNewBook(int lhid, String name, String isbn, String description, int bookCount, int printedYear, int bookAuthor, int bookCategory, int bookPrinters) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            name=name.replaceAll("\\+", " ");
            description=description.replaceAll("\\+", " ");
            Query qr = s.createQuery("FROM BookProfile a WHERE a.name='" + name + "'");
            if (!qr.list().isEmpty()) {
                msg = "This name already exists";
            } else {
                UserLoginHistory lh = (UserLoginHistory) s.load(UserLoginHistory.class, lhid);
                BookAuthor ba = (BookAuthor) s.load(BookAuthor.class, bookAuthor);
                BookPrinters bpr = (BookPrinters) s.load(BookPrinters.class, bookPrinters);
                BookCategory bc = (BookCategory) s.load(BookCategory.class, bookCategory);
                Date date = new Date();
                Query q = s.createQuery("FROM BookProfile a");
                int a = q.list().isEmpty() ? 1 : q.list().size() + 1;
                String genID = "BKP00" + a;

                BookProfile bp = new BookProfile();
                bp.setGeneratedId(genID);
                bp.setName(name);
                bp.setIsbn(isbn);
                bp.setDescription(description);
                bp.setBookCount(bookCount);
                bp.setPrintedYear(printedYear);
                bp.setCreateDateTime(date);
                bp.setIsActive(Boolean.TRUE);
                bp.setBookAuthor(ba);
                bp.setBookCategory(bc);
                bp.setBookPrinters(bpr);
                bp.setUserLoginHistory(lh);

                s.save(bp);

                s.beginTransaction().commit();
                s.flush();
                s.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }

    //updateBook
    public String updateBook(int lhid, int bookID, String name, String isbn, String description, int bookCount, int printedYear, int bookAuthor, int bookCategory, int bookPrinters) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();

            name=name.replaceAll("\\+", " ");
            description=description.replaceAll("\\+", " ");
            Query qr = s.createQuery("FROM BookProfile a WHERE a.name='" + name + "' AND a.id!=" + bookID);
            if (!qr.list().isEmpty()) {
                msg = "This name already exists";
            } else {
                UserLoginHistory lh = (UserLoginHistory) s.load(UserLoginHistory.class, lhid);
                BookAuthor ba = (BookAuthor) s.load(BookAuthor.class, bookAuthor);
                BookPrinters bpr = (BookPrinters) s.load(BookPrinters.class, bookPrinters);
                BookCategory bc = (BookCategory) s.load(BookCategory.class, bookCategory);
                Date date = new Date();

                BookProfile bp = (BookProfile) s.load(BookProfile.class, bookID);
                bp.setName(name);
                bp.setIsbn(isbn);
                bp.setDescription(description);
                bp.setBookCount(bookCount);
                bp.setPrintedYear(printedYear);
                bp.setUpdateDateTime(date);
                bp.setBookAuthor(ba);
                bp.setBookCategory(bc);
                bp.setBookPrinters(bpr);
                bp.setUserLoginHistory(lh);

                s.update(bp);

                s.beginTransaction().commit();
                s.flush();
                s.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }

    //deleteBook
    public String deleteBook(int bookID) {
        String msg = "Done";
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            
            BookProfile bp = (BookProfile) s.load(BookProfile.class, bookID);
            s.delete(bp);
            
            s.beginTransaction().commit();
            s.flush();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Something went to wrong";
        }
        return msg;
    }

    //loadBookList
    public List<BookProfile> loadBookList() {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            List<BookProfile> list;
            Query q = s.createQuery("FROM BookProfile a WHERE a.isActive=1");
            list = q.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //getBookDetails
    public BookProfile getBookDetails(int bookID) {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            
            BookProfile bp = (BookProfile) s.load(BookProfile.class, bookID);
            
            return bp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //searchBook
    public List<BookProfile> searchBook(String name, String isbn, int bookAuthor, int bookCategory, int bookPrinters) {
        try {
            String query="FROM BookProfile a WHERE a.isActive=1";
            if(!name.isEmpty() && !name.equals("none")){
                query+=" AND a.name LIKE '%"+name+"%'";
            }
            if(!isbn.isEmpty() && !isbn.equals("none")){
                query+=" AND a.isbn LIKE '%"+isbn+"%'";
            }
            if(bookAuthor!=0){
                query+=" AND a.bookAuthor.id="+bookAuthor;
            }
            if(bookCategory!=0){
                query+=" AND a.bookCategory.id="+bookCategory;
            }
            if(bookPrinters!=0){
                query+=" AND a.bookPrinters.id="+bookPrinters;
            }
            
            System.out.println("query : "+query);
            Session s = HibernateUtil.getSessionFactory().openSession();
            List<BookProfile> list;
            Query q = s.createQuery(query);
            list = q.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //loadBookCategoryList
    public List<BookCategory> loadBookCategoryList() {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            List<BookCategory> list;
            Query q = s.createQuery("FROM BookCategory a");
            list = q.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //loadBookPrintersList
    public List<BookPrinters> loadBookPrintersList() {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            List<BookPrinters> list;
            Query q = s.createQuery("FROM BookPrinters a");
            list = q.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //loadBookAuthorList
    public List<BookAuthor> loadBookAuthorList() {
        try {
            Session s = HibernateUtil.getSessionFactory().openSession();
            List<BookAuthor> list;
            Query q = s.createQuery("FROM BookAuthor a");
            list = q.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
