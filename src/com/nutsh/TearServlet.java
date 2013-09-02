package com.nutsh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TearServlet
 */
public class TearServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Set<String> fullSet = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TearServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String size = request.getParameter("size");

		Set<String> resluts = new HashSet<String>();
		for (int i = 0; i < 7; i++) {
			String numi = request.getParameter("num" + (i + 1));
			numi = numi == null ? "" : numi;
			String[] arr = numi.split(",");
			if (arr.length > 1) {
				List<String> zuhe = new MathLottery().zuhe(arr,
						Integer.valueOf(size));
				resluts.addAll(zuhe);
			}
		}

		Set<String> filters = getFullSet();
		filters.removeAll(resluts);
		StringBuilder html = new StringBuilder();
		html.append("<table border rules='cols' cellspacing='0'>");
		html.append("<tr>");
		html.append("<td>");
		html.append(0);
		html.append("</td>");
		html.append("<td>");
		html.append("共").append(filters.size()).append("组");
		html.append("</td>");
		html.append("</tr>");

		int i = 1;
		for (String string : filters) {
			html.append("<tr>");
			html.append("<td>");
			html.append(i++);
			html.append("</td>");
			html.append("<td>");
			html.append(string);
			html.append("</td>");
			html.append("</tr>");
		}
		html.append("</table>");
		response.setContentType("text/html;charset=utf-8");  
        PrintWriter out = response.getWriter(); 
        out.println(html);  
        out.flush();  
        out.close();  
	}

	public Set<String> getFullSet() {
		if (fullSet == null) {
			String[] arr = new String[] { "1", "2", "3", "4", "5", "6", "7",
					"8", "9", "10", "11" };
			List<String> zuhe = new MathLottery().zuhe(arr, 5);
			fullSet = new HashSet<String>(zuhe);
		}
		return new HashSet(fullSet);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
