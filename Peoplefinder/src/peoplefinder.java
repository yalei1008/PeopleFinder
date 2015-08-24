import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Servlet implementation class peoplefinder
 */
@WebServlet("/peoplefinder")
public class peoplefinder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection conn;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public peoplefinder() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String name=request.getParameter("name");
		System.out.println(name);
		String resultname= "";
		String sql = "";
		Statement stmt = null;
		
		try {
            String url = "jdbc:oracle:thin:testuser/password@localhost";
             Class.forName("oracle.jdbc.driver.OracleDriver");
            //properties for creating connection to Oracle database
            Properties props = new Properties();
            props.setProperty("user", "testdb");
            props.setProperty("password", "password");

            //creating connection to Oracle database using JDBC

                conn = DriverManager.getConnection(url,props);
               stmt = conn.createStatement();
            } catch (Exception e) {
                e.printStackTrace();}
		 try{
	       sql = "select firstname, lastname from customers where lastname like '" + name +"%'";
			System.out.println(sql);
//	       sql = "select firstname, lastname from customers";
	            //creating PreparedStatement object to execute query
//	        
//	            PreparedStatement preStatement = null;
//	                preStatement = conn.prepareStatement(sql);
//	            
//	                ResultSet result = preStatement.executeQuery();	    
			 
			 ResultSet result = stmt.executeQuery(sql);
	                while(result.next())
	                {   System.out.println(result.getString("firstname"));
	                
	                	resultname+= "<tr>";
	                	resultname+="<td>";
	                	resultname+=result.getString("firstname");
	                    resultname+="</td>";
	                	resultname+="<td>";	                
	                	resultname+=  result.getString("lastname");
	                			resultname+="</td>";
	                			resultname+= "</tr>";
	                			
	                }
		 }
	          catch (SQLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();}

	            
	            response.setContentType("text/html");
	            request.setAttribute("name", resultname);
	          
	    
	            getServletContext()
	            .getRequestDispatcher("/result.jsp")
	            .forward(request,  response);
      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
doGet(request, response);
	}

}
