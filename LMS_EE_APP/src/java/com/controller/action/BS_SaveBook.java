
package com.controller.action;

import com.controller.common.ResAccessMethod;
import com.controller.common.ResAccessUrl;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;


public class BS_SaveBook extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            if (request.getSession().getAttribute("user") != null) {
                JSONObject obj = new JSONObject(request.getSession().getAttribute("user").toString());

                String name = URLEncoder.encode(request.getParameter("name"), "UTF-8");
                String isbn = URLEncoder.encode(request.getParameter("isbn"), "UTF-8");
                String description = URLEncoder.encode(request.getParameter("description"), "UTF-8");
                int printedYear = Integer.parseInt(request.getParameter("printedYear"));
                int bookCount = Integer.parseInt(request.getParameter("bookCount"));
                int author = Integer.parseInt(request.getParameter("bookAuthor"));
                int category = Integer.parseInt(request.getParameter("bookCategory"));
                int printers = Integer.parseInt(request.getParameter("bookPrinters"));
                int lhid = obj.getInt("id");

                String URL = ResAccessUrl.BOOK_SERVICE + "BookProfile/" + lhid + "/" + name + "/" + isbn + "/" + description+"/"+bookCount+"/"+printedYear+"/"+author+"/"+category+"/"+printers;
                String METHOD = "POST";
                String res = new ResAccessMethod().callMethod(URL, METHOD);

                if (res.isEmpty()) {
                    response.sendRedirect("book_management.jsp?error=New book save failed");
                } else {
                    JSONObject obj2 = new JSONObject(res);
                    if (obj2.getBoolean("result")) {
                        response.sendRedirect("book_management.jsp?error=Successfully saved");
                    } else {
                        response.sendRedirect("book_management.jsp?error=" + obj2.getString("msg"));
                    }

                }


            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("book_management.jsp?error=New book save failed");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
