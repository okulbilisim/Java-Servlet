package com.okulbilisim.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.okulbilisim.db.util.DBConnectionManager;
import com.okulbilisim.db.util.Result;

/**
 *
 * @author huseyinmert
 */
public class AddBook extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");         //output olarak JSON response dönderilecek karakter seti olarak UTF-8        
        request.setCharacterEncoding("UTF-8");                                       //request olarak türkçe karakter girildiğinde sorun olmaması için bu satırı yazıyoruz
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();                                                           //sonucu json olarak yazdırmak için gson-2.0.jar kitaplığını kullanıyoruz
        DBConnectionManager dm = new DBConnectionManager();             //veritabanı bağlantı işlemlerini bu sınıf aracılığıyla yapıyoruz
        PreparedStatement pst = null;
        Connection conn;
        Result result = null;
        String bookName, publisher, categoryName;

        try {

            bookName = request.getParameter("bookName");            //bookName parametresi ile kitap ismini alıyoruz
            publisher = request.getParameter("publisher");                //publisher parametresi ile yayınevi ismini alıyoruz
            categoryName = request.getParameter("categoryName");  //categoryName parametresi ile kategori ismini alıyoruz

            /*
             * Veritabanı işlemlerini burada yapabiliriz ve niteliklerini almış olduğumuz kitabı veritabanına ekleyebiliriz
             */
            conn = dm.getConnection();
            pst = conn.prepareStatement("INSERT INTO examplebooks (bookname,publisher,categoryname) VALUES (?,?,?)");
            pst.setString(1, bookName);
            pst.setString(2, publisher);
            pst.setString(3, categoryName);
            pst.executeUpdate();
            conn.commit();

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            ///// işlem tamamlandı , başarılı işlem olduğuna yönelik log basıyoruz /////////////////////////////////////////////////////////
            result.code = 200;
            result.message = "Success";

            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        } catch (Exception e) {
            /*
             * oluşacak hataları burada ele alıyoruz ve hataya göre log basıyoruz
             */
            result.code = 500;
            result.message = "error";
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        } finally {
            out.println(gson.toJson(result));
            out.close();
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
            dm.closePSStatement(pst);
            dm.closeConnection();
            // resultset,preparestatement,connection;bunların kapatılma sırası==>
            // önce resultset,sonra preparestatement en son connection            
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
