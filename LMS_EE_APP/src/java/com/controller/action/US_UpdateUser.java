
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


public class US_UpdateUser extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            if (request.getSession().getAttribute("user") != null) {
                JSONObject obj = new JSONObject(request.getSession().getAttribute("user").toString());

                String fname = URLEncoder.encode(request.getParameter("fname"), "UTF-8");
                String lname = URLEncoder.encode(request.getParameter("lname"), "UTF-8");
                String mobile = URLEncoder.encode(request.getParameter("mobile"), "UTF-8");
                String address = URLEncoder.encode(request.getParameter("address"), "UTF-8");
                String pt = request.getParameter("pt");
                String country = request.getParameter("country");
                int lhid = obj.getInt("id");

                String URL = ResAccessUrl.USER_SERVICE + "UserProfile/" + obj.getInt("userID") + "/" + fname + "/" + lname + "/" + mobile + "/" + address + "/" + pt + "/" + country + "/" + lhid;
                String METHOD = "PUT";
                String res = new ResAccessMethod().callMethod(URL, METHOD);

                if (res.isEmpty()) {
                    response.sendRedirect("user_profile.jsp?error=User update failed");
                } else {
                    JSONObject obj2 = new JSONObject(res);
                    if (obj2.getBoolean("result")) {
                        response.sendRedirect("user_profile.jsp?error=Successfully updated");
                    } else {
                        response.sendRedirect("user_profile.jsp?error=" + obj2.getString("msg"));
                    }

                }

//            PrintWriter out = response.getWriter();
//            out.println(res);
            } else {
                response.sendRedirect("index.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("user_profile.jsp?error=User update failed");
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
