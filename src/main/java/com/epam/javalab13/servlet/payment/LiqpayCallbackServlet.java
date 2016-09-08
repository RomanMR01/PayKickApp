package com.epam.javalab13.servlet.payment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.javalab13.service.PaymentService;

/**
 * Servlet implementation class LiqpayCallbackServlet
 */
public class LiqpayCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PaymentService ps;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LiqpayCallbackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		String signature = request.getParameter("signature");
		ps=new PaymentService();
		ps.callback(data,signature);
	}

}
