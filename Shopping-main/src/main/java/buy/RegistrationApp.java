package buy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegistrationApp extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		String password = request.getParameter("password");
		int i = -1;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String pass = "Vinesh12";
			Connection con = DriverManager.getConnection(url, user, pass);
			String query = "insert into registration (firstName, lastName, mobile_number, mail_Id, city_name, state_name, country_name, pass_word) values (?,?,?,?,?,?,?,?)";
			
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setString(1, firstName);
			pst.setString(2, lastName);
			pst.setString(3, mobile);
			pst.setString(4, email);
			pst.setString(5, city);
			pst.setString(6, state);
			pst.setString(7, country);
			pst.setString(8, password);
			i = pst.executeUpdate();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		if(i > 0) {
			RequestDispatcher rd = request.getRequestDispatcher("Success.html");
			rd.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException{
		doGet(request, response);
	}
}
