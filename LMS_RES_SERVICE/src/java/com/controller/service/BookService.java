
package com.controller.service;

import com.controller.action.BookAction;
import com.model.entity.BookAuthor;
import com.model.entity.BookCategory;
import com.model.entity.BookPrinters;
import com.model.entity.BookProfile;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Path("bookService")
public class BookService {

    private BookAction ba;
    
    @Context
    private UriInfo context;

  
    public BookService() {
        ba=new BookAction();
    }

 
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    
    @PUT
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public void putJson(String content) {
    }
    
    //saveNewBookCategory
    @POST
    @Path("BookCategory/{name}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String saveNewBookCategory(
            @PathParam("name") String name) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response=ba.saveNewBookCategory(name);
            
            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");
                
            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }
    
        msg=objSend.toString();
        return msg;
    }
    
    //deleteBookCategory
    @DELETE
    @Path("BookCategory/{categoryID}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String deleteBookCategory(
            @PathParam("categoryID") int categoryID) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response=ba.deleteBookCategory(categoryID);
            
            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");
                
            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }
    
        msg=objSend.toString();
        return msg;
    }
    
    //saveNewBookPrinters
    @POST
    @Path("BookPrinter/{name}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String saveNewBookPrinters(
            @PathParam("name") String name) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response=ba.saveNewBookPrinters(name);
            
            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");
                
            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }
    
        msg=objSend.toString();
        return msg;
    }
    
    //deleteBookPrinters
    @DELETE
    @Path("BookPrinter/{printersID}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String deleteBookPrinters(
            @PathParam("printersID") int printersID) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response=ba.deleteBookPrinters(printersID);
            
            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");
                
            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }
    
        msg=objSend.toString();
        return msg;
    }
    
    //saveNewBookAuthor
    @POST
    @Path("BookAuthor/{name}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String saveNewBookAuthor(@PathParam("name") String name) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response=ba.saveNewBookAuthor(name);
            
            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");
                
            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }
    
        msg=objSend.toString();
        return msg;
    }
    
    //deleteBookAuthor
    @DELETE
    @Path("BookAuthor/{authorID}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String deleteBookAuthor(
            @PathParam("authorID") int authorID) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response=ba.deleteBookAuthor(authorID);
            
            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");
                
            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }
    
        msg=objSend.toString();
        return msg;
    }
    
    //saveNewBook
    @POST
    @Path("BookProfile/{lhid}/{name}/{isbn}/{description}/{bookCount}/{printedYear}/{bookAuthor}/{bookCategory}/{bookPrinters}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String saveNewBook(
            @PathParam("lhid") int lhid,
            @PathParam("name") String name,
            @PathParam("isbn") String isbn,
            @PathParam("description") String description,
            @PathParam("bookCount") int bookCount,
            @PathParam("printedYear") int printedYear,
            @PathParam("bookAuthor") int bookAuthor,
            @PathParam("bookCategory") int bookCategory,
            @PathParam("bookPrinters") int bookPrinters) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response=ba.saveNewBook(lhid, name, isbn, description, bookCount, printedYear, bookAuthor, bookCategory, bookPrinters);
            
            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");
                
            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }
    
        msg=objSend.toString();
        return msg;
    }
    
    //updateBook
    @PUT
    @Path("BookProfile/{lhid}/{bookID}/{name}/{isbn}/{description}/{bookCount}/{printedYear}/{bookAuthor}/{bookCategory}/{bookPrinters}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String updateBook(
            @PathParam("lhid") int lhid,
            @PathParam("bookID") int bookID,
            @PathParam("name") String name,
            @PathParam("isbn") String isbn,
            @PathParam("description") String description,
            @PathParam("bookCount") int bookCount,
            @PathParam("printedYear") int printedYear,
            @PathParam("bookAuthor") int bookAuthor,
            @PathParam("bookCategory") int bookCategory,
            @PathParam("bookPrinters") int bookPrinters) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response=ba.updateBook(lhid, bookID, name, isbn, description, bookCount, printedYear, bookAuthor, bookCategory, bookPrinters);
            
            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");
                
            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }
    
        msg=objSend.toString();
        return msg;
    }
    
    //deleteBook
    @DELETE
    @Path("BookProfile/{bookID}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String deleteBook(
            @PathParam("bookID") int bookID) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            String response=ba.deleteBook(bookID);
            
            objSend.put("result", response.equals("Done"));
            objSend.put("id", 0);
            objSend.put("msg", response);
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");
                
            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }
    
        msg=objSend.toString();
        return msg;
    }
    
    //loadBookList
    @GET
    @Path("BookProfiles")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String loadBookList() {
        String msg = "";
        JSONArray arrSend=new JSONArray();
        JSONObject objSend;
        try {
            List<BookProfile> list = new ArrayList<>(ba.loadBookList());
            String response=list.isEmpty()?"No Data":"Done";
            objSend = new JSONObject();
            objSend.put("result", !list.isEmpty());
            objSend.put("id", 0);
            objSend.put("msg", response);
            
            arrSend.put(objSend);
            
            for(BookProfile bp:list){
                objSend = new JSONObject();
                objSend.put("id", bp.getId());
                objSend.put("genID", bp.getGeneratedId());
                objSend.put("name", bp.getName());
                objSend.put("isbn", bp.getIsbn());
                objSend.put("description", bp.getDescription());
                objSend.put("printedYear", bp.getPrintedYear());
                objSend.put("bookCount", bp.getBookCount());
                objSend.put("author", bp.getBookAuthor().getName());
                objSend.put("category", bp.getBookCategory().getName());
                objSend.put("printers", bp.getBookPrinters().getName());
                
                arrSend.put(objSend);
            }
            
        } catch (Exception e) {
            try {
                objSend = new JSONObject();
                
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");
                
                arrSend.put(objSend);
            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }
        msg=arrSend.toString();
        return msg;

    }
    
    //getBookDetails
    @GET
    @Path("BookProfile/{bookID}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String getBookDetails(
            @PathParam("bookID") int bookID) {
        String msg = "";
        JSONObject objSend = new JSONObject();
        try {
            BookProfile bp = ba.getBookDetails(bookID);

            if (bp != null) {
                objSend.put("result", true);
                objSend.put("id", bp.getId());
                objSend.put("genID", bp.getGeneratedId());
                objSend.put("name", bp.getName());
                objSend.put("isbn", bp.getIsbn());
                objSend.put("description", bp.getDescription());
                objSend.put("printedYear", bp.getPrintedYear());
                objSend.put("bookCount", bp.getBookCount());
                objSend.put("author", bp.getBookAuthor().getName());
                objSend.put("category", bp.getBookCategory().getName());
                objSend.put("printers", bp.getBookPrinters().getName());
                objSend.put("authorId", bp.getBookAuthor().getId());
                objSend.put("categoryId", bp.getBookCategory().getId());
                objSend.put("printersId", bp.getBookPrinters().getId());
                objSend.put("msg", "Done");
            } else {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "No user found");
            }
        } catch (Exception e) {
            try {
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");
                
            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }
    
        msg=objSend.toString();
        return msg;
    }
    
    //searchBook
    @GET
    @Path("BookProfiles/{name}/{isbn}/{bookAuthor}/{bookCategory}/{bookPrinters}")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String searchBook(
            @PathParam("name") String name,
            @PathParam("isbn") String isbn,
            @PathParam("bookAuthor") int bookAuthor,
            @PathParam("bookCategory") int bookCategory,
            @PathParam("bookPrinters") int bookPrinters) {
        String msg = "";
        JSONArray arrSend=new JSONArray();
        JSONObject objSend;
        try {
            List<BookProfile> list = new ArrayList<>(ba.searchBook(name, isbn, bookAuthor, bookCategory, bookPrinters));
            String response=list.isEmpty()?"No Data":"Done";
            objSend = new JSONObject();
            objSend.put("result", !list.isEmpty());
            objSend.put("id", 0);
            objSend.put("msg", response);
            
            arrSend.put(objSend);
            
            for(BookProfile bp:list){
                objSend = new JSONObject();
                objSend.put("id", bp.getId());
                objSend.put("genID", bp.getGeneratedId());
                objSend.put("name", bp.getName());
                objSend.put("isbn", bp.getIsbn());
                objSend.put("description", bp.getDescription());
                objSend.put("printedYear", bp.getPrintedYear());
                objSend.put("bookCount", bp.getBookCount());
                objSend.put("author", bp.getBookAuthor().getName());
                objSend.put("category", bp.getBookCategory().getName());
                objSend.put("printers", bp.getBookPrinters().getName());
                
                arrSend.put(objSend);
            }
            
        } catch (Exception e) {
            try {
                objSend = new JSONObject();
                
                objSend.put("result", false);
                objSend.put("id", 0);
                objSend.put("msg", "Something went to wrong...");
                
                arrSend.put(objSend);
            } catch (JSONException ex) {
                e.printStackTrace();
            }
        }
        msg=arrSend.toString();
        return msg;
    }
    
    //loadBookCategoryList
    @GET
    @Path("BookCategories")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String loadBookCategoryList() {
        String msg = "";
        JSONArray arrSend = new JSONArray();
        JSONObject objSend;
        try {
            List<BookCategory> list = new ArrayList<>(ba.loadBookCategoryList());

            for (BookCategory a : list) {
                objSend = new JSONObject();
                objSend.put("id", a.getId());
                objSend.put("name", a.getName());

                arrSend.put(objSend);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        msg = arrSend.toString();
        return msg;

    }
    
    //loadBookPrintersList
    @GET
    @Path("BookPrinters")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String loadBookPrintersList() {
        String msg = "";
        JSONArray arrSend = new JSONArray();
        JSONObject objSend;
        try {
            List<BookPrinters> list = new ArrayList<>(ba.loadBookPrintersList());

            for (BookPrinters a : list) {
                objSend = new JSONObject();
                objSend.put("id", a.getId());
                objSend.put("name", a.getName());

                arrSend.put(objSend);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        msg = arrSend.toString();
        return msg;

    }
    
    //loadBookAuthorList
    @GET
    @Path("BookAuthors")
    @Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
    public String loadBookAuthorList() {
        String msg = "";
        JSONArray arrSend = new JSONArray();
        JSONObject objSend;
        try {
            List<BookAuthor> list = new ArrayList<>(ba.loadBookAuthorList());

            for (BookAuthor a : list) {
                objSend = new JSONObject();
                objSend.put("id", a.getId());
                objSend.put("name", a.getName());

                arrSend.put(objSend);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        msg = arrSend.toString();
        return msg;

    }
}
