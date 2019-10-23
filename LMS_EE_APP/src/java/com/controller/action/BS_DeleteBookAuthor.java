
package com.controller.action;

import com.controller.common.ResAccessMethod;
import com.controller.common.ResAccessUrl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

public class BS_DeleteBookAuthor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            if (request.getSession().getAttribute("user") != null) {
                int id = Integer.parseInt(request.getParameter("id"));

                String URL = ResAccessUrl.BOOK_SERVICE + "BookAuthor/" +id;
                String METHOD = "DELETE";
                String res = new ResAccessMethod().callMethod(URL, METHOD);

                if (res.isEmpty()) {
                    response.sendRedirect("type_management.jsp?error=Book author delete failed");
                } else {
                    JSONObject obj2 = new JSONObject(res);
                    if (obj2.getBoolean("result")) {
                        response.sendRedirect("type_management.jsp?error=Successfully deleted");
                    } else {
                        response.sendRedirect("type_management.jsp?error=" + obj2.getString("msg"));
                    }

                }

            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("type_management.jsp?error=Book author delete failed");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
