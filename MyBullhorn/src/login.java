import java.io.IOException;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DBUtil;
import model.User;

/**
 * Servlet implementation class login
 */


@WebServlet("/login")
public class login extends HttpServlet {

	private static final long serialVersionUID = 1L;
		String message="";
	public void init() throws ServletException {
		
	}

	  public login() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	  

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		EntityManager em=DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans= em.getTransaction();
		String userid= request.getParameter("id");
		String password = request.getParameter("upassword");
	
	
		
		if(password!=null && userid!=null)
		{
			String sql="select b from users b where b.userid= '"+userid+"'";
			TypedQuery<User>bq =em.createQuery(sql,model.User.class);
			List<model.User> list=bq.getResultList();
			BigDecimal pass=null;
			for (User temp:list)
			pass = temp.getUpassword();
		}
			else{
				
				response.setContentType("text/html");
				String alert = "Please log in";
				request.setAttribute("alert",alert);
				getServletContext().getRequestDispatcher("/error.jsp").include(request, response);
				
							
			
			{
				getServletContext().getRequestDispatcher("/index.html").forward(request, response);
			}
			 em.close();}
				
		}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

		
		
}
	