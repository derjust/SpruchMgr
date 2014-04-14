package de.zeeman.spruchmgr_war;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.zeeman.spruchmgr.Author;
import de.zeeman.spruchmgr.Saying;
import de.zeeman.spruchmgr_ejb.CacheRefresher;

@WebServlet(value = { "/random/txt" }, initParams = { @WebInitParam(name = "dateFormat", value = "dd.MM.yyyy") })
public class SpruchRandomTxt extends HttpServlet {

	private static final long serialVersionUID = -6089443120168696187L;

	@Inject
	protected CacheRefresher cacheRefresher;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		
		Saying s = cacheRefresher.getRandomSaying();
		System.out.println(s);
		response.getWriter().println(s.getSaying());
		
		Author a = s.getAuthor();
		StringBuilder sb = new StringBuilder();
		sb.append("[ ").append(a.getFirstname()).append(" ").append(a.getLastname());
		if (a.getWork() != null && a.getWork().length() > 0) {
			sb.append(", ").append(a.getWork());
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(getInitParameter("dateFormat"));
		response.getWriter().println(sb.toString());
		sb = new StringBuilder();
		if (a.getBirthday() != null) {
			sb.append("\u002A").append(sdf.format(a.getBirthday()));
		}
		
		if (a.getObit() != null) {
			if (sb.length() > 0) {
				sb.append(" ");
			}
			sb.append("\u2020").append(sdf.format(a.getObit()));
		}
		sb.append("]");
		
		response.getWriter().println(sb.toString());
		
		response.getWriter().flush();
	}
}
