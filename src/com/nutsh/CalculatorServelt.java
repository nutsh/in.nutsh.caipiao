package com.nutsh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServelt
 */
public class CalculatorServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculatorServelt() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String wrap = "<br/>";
		String nums = request.getParameter("nums");
		String size = request.getParameter("size");
		String[] arr = nums.split(",");
		List<String> zuhe = new MathLottery().zuhe(arr, Integer.valueOf(size));
		String result = "组合组数为:"+zuhe.size()+ "<br>";
		result += "结果如下:"+wrap;
		
		for (String aline : zuhe) {
			result = result + aline+ wrap;
		}
		
		response.setContentType("text/html;charset=utf-8");  
		  
        PrintWriter out = response.getWriter();  
  
        out.println(result);  
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
