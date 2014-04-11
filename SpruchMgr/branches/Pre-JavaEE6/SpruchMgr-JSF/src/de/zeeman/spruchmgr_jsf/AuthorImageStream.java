package de.zeeman.spruchmgr_jsf;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeeman.sayingmgr_ejb.ejb.SayingDAOLocal;
import de.zeeman.sayingmgr_ejb.entity.Author;
import de.zeeman.sayingmgr_ejb.entity.Picture;

/**
 * Servlet implementation class AuthorImageStream
 */
public class AuthorImageStream extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	SayingDAOLocal sayingDAO;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int authorid = Integer.parseInt(request.getParameter("authorid"));
		
		Author author = sayingDAO.getAuthor(authorid);
		Picture picture = author.getPicture();
		
		byte [] rb = picture.getImage();
		int len = rb.length;
        response.reset();
        response.setContentType(picture.getMime());
        response.getOutputStream().write(rb,0,len);
        response.getOutputStream().flush();
		

	}

}
