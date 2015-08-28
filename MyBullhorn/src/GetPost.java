import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DBUtil;
import model.Microblog;


@WebServlet("/GetPost")
public class GetPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPost() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select c from Microblog c order by c.id desc";
		TypedQuery<Microblog> q = em.createQuery(qString, Microblog.class);
		List <Microblog> content;	
		try{
			content=q.getResultList();
			if(content ==null ||content.isEmpty()){
				content=null;
			}

		String Allpost = "<ul>";
		for(int i=0;i<content.size();i++)
        {
            Allpost+="<li class=\"list-group-item\"><a href=\"List.jsp?userid="+content.get(i).getId().replace(" ", "%20")+"\">" + content.get(i).getContect() +"</a></li>";
            System.out.println(content.get(i).getContect());
        }
	
		// Set response content type
				//response.setContentType("text/html");
				Allpost += "</ul>";
				request.setAttribute("Allpost", Allpost);
				
				getServletContext().getRequestDispatcher("/List.jsp")
						.forward(request, response);
				Allpost = "";
				
		}catch(Exception e){
			
			System.out.println(e.getMessage());
		}finally{
			em.close();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}