package com.nutsh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServlet1
 */
public class CalculatorServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculatorServlet1() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String>[] results = new ArrayList[7];

		String size = request.getParameter("size");

		List<Integer> index2show = new ArrayList<Integer>();
		
		for (int i = 0; i < 7; i++) {
			String numi = request.getParameter("num"+(i+1));
			numi = numi==null?"":numi;
			String[] arr = numi.split(",");
			if (arr.length>1) {
				index2show.add(i);
				List<String> zuhe = new MathLottery().zuhe(arr, Integer.valueOf(size));
				results[i] = zuhe;
			}
			else{
				results[i] = new ArrayList<String>();
			}

		}
		
		int maxCount = 0;
		int maxelemnts = 0;

		Map<String,Integer> element2Counts = new HashMap();
		
		for (List<String> group : results) {
			if(group.size()>maxelemnts){
				maxelemnts = group.size();
			}
			for (String element : group) {
				int currentCount = element2Counts.get(element.trim())== null?Integer.valueOf(0):element2Counts.get(element.trim());
				currentCount++;
				element2Counts.put(element.trim(), currentCount);
				if(currentCount>maxCount){
					maxCount = currentCount;
				}
			}
		}
		
		
		StringBuilder html = new StringBuilder("<table border rules='cols' cellspacing='0'>");
		html.append("<tr>");
		for (int index: index2show) {
			html.append("<td>").append("组合").append(index+1).append(": 共").append(results[index].size()).append("组</td>");
		}
		html.append("</tr>");
		for (int i = 0; i < maxelemnts ; i++) {
			html.append("<tr>");
			for (int index: index2show) {
				html.append("<td>");
				List<String> list = results[index];
				String element = list.size()>i ?list.get(i):null;
				if(element == null){
					html.append("");
				}else{
					int count = element2Counts.get(element.trim());
					if(count == maxCount && count != 1){
						html.append("<font color='red'>");
					}
					html.append(element);
					html.append("       (<font font-size＝'small'>");
					html.append(count);
					html.append("</font>)");
					if(count == maxCount && count != 1){
						html.append("</font>");
					}
				}
				html.append("</td>");
			}
			html.append("</tr>");
		}
		html.append("</table>");

		response.setContentType("text/html;charset=utf-8");  
		  
        PrintWriter out = response.getWriter();  
  
        out.println(html);  
        out.flush();  
        out.close();  
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
