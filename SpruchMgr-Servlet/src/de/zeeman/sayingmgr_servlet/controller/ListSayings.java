package de.zeeman.sayingmgr_servlet.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeeman.sayingmgr_ejb.ejb.SayingDAOBean;
import de.zeeman.sayingmgr_ejb.ejb.SayingDAOLocal;
import de.zeeman.sayingmgr_ejb.entity.Saying;

/**
 * Servlet implementation class ListSayings
 */
public class ListSayings extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private SayingDAOLocal sayingDAO;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListSayings() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<Saying> sayings = sayingDAO.getAllSayings();
		Writer w = new OutputStreamWriter(response.getOutputStream());
		for(Saying s : sayings) {
			w.write("<p>");
			w.write(s.getSaying());
			w.write("<br>");
			w.write(s.getAuthor().toString());
			w.write("</p>");
		}
		w.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

}
