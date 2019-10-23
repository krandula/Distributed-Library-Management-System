
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


public class US_UserLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String uname=request.getParameter("uname");
            String pw=request.getParameter("pw");
            
            String URL=ResAccessUrl.USER_SERVICE+"UserLogin/"+uname+"/"+pw;
            String METHOD="GET";
            String res=new ResAccessMethod().callMethod(URL, METHOD);
            
            if(res.isEmpty()){
                response.sendRedirect("index.jsp?error=Login failed");
            }else{
                JSONObject obj=new JSONObject(res);
                if(obj.getBoolean("result")){
                    request.getSession().setAttribute("user", obj);
                    System.out.println("User Login : "+obj);
                    response.sendRedirect("home.jsp");
                }else{
                    response.sendRedirect("index.jsp?error="+obj.getString("msg"));
                }
                
            }
            
//            PrintWriter out = response.getWriter();
//            out.println(res);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("index.jsp?error=Login failed");
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
