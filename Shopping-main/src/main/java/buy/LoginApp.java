package buy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginApp extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				HttpSession session = request.getSession();
				String email = request.getParameter("user1");
				String password = request.getParameter("pass1");
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					String url = "jdbc:oracle:thin:@localhost:1521:xe";
					String user = "system";
					String pass = "Vinesh12";
					Connection con = DriverManager.getConnection(url, user, pass);
					String query = "select * from registration";
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(query);
					boolean flag = false;
					while(rs.next()) {
						if(rs.getString(4).equals(email) && rs.getString(8).equals(password)) {
							RequestDispatcher rd = request.getRequestDispatcher("Welcome.html");
							rd.forward(request, response);
							flag = true;
						}
						if(flag) {
							break;
						}
					}
					if(!flag) {
						RequestDispatcher rd = request.getRequestDispatcher("Error.html");
						rd.forward(request, response);
						flag = false;
					}
					
				}catch(ClassNotFoundException e) {
					e.printStackTrace();
				}catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
			protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
				doGet(request, response);
			}
}
