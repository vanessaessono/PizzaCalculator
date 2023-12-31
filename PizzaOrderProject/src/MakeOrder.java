
/**
 * @file MakeOrder.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MakeOrder")
public class MakeOrder extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MakeOrder() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String userName = request.getParameter("username");
      String toppings = request.getParameter("toppings");
      String size = request.getParameter("size");
     
      

      Connection connection = null;
      String insertSql = " INSERT INTO myTable (id, MYUSER, TOPPINGS, SIZE) values (default, ?, ?, ?)";

      try {
         DBPizConnection.getDBConnection();
         connection = DBPizConnection.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, userName);
         preparedStmt.setString(2, toppings);
         preparedStmt.setString(3, size);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //

            "  <li><b>User Name</b>: " + userName + "\n" + //
            "  <li><b>Email</b>: " + toppings + "\n" + //
            "  <li><b>Phone</b>: " + size + "\n" + //

            "</ul>\n");

      out.println("<a href=/PizzaOrderProject/SearchOrder.html>Search Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
