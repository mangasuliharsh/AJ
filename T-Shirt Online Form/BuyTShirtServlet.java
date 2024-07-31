
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BuyTShirtServlet")
public class BuyTShirtServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String[] accessories = request.getParameterValues("accessory");
        String tagline = request.getParameter("tagline");
        String pocket = request.getParameter("pocket");
        String color = request.getParameter("color");
        
        String accessoryList = "";
        if (accessories != null) {
            for (String accessory : accessories) {
                accessoryList += accessory + " ";
            }
        }

        try {
            // Load the MySQL JDBC driver
        	Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbms", "root", "myadmin123");

            // Insert the T-Shirt details into the database
            PreparedStatement ps = con.prepareStatement("INSERT INTO TShirts (Accessories, Tagline, Pocket, Color) VALUES (?, ?, ?, ?)");
            ps.setString(1, accessoryList.trim());
            ps.setString(2, tagline);
            ps.setString(3, pocket);
            ps.setString(4, color);
            ps.executeUpdate();

            // Retrieve and display all T-Shirt orders
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM TShirts");
            out.println("<h2>All T-Shirts Orders</h2>");
            out.println("<table border='1'><tr><th>OrderNo</th><th>Accessories</th><th>Tagline</th><th>Pocket</th><th>Color</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("OrderNo") + "</td><td>" + rs.getString("Accessories") + "</td><td>" + rs.getString("Tagline") + "</td><td>" + rs.getString("Pocket") + "</td><td>" + rs.getString("Color") + "</td></tr>");
            }
            out.println("</table>");

            // Close the connection
            con.close();
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}

