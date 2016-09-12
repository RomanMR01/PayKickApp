package com.epam.javalab13.servlet.bookmaker;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.javalab13.service.game.CoefficientService;

/**
 * Servlet implementation class ConfirmCoefficientsServlet
 */
public class ConfirmCoefficientsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CoefficientService coefficientService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmCoefficientsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String gameId = request.getParameter("gameId");
		String[] resultCoefficients = request.getParameterValues("resultCoefficients");
		String[] goalsCoefficients = request.getParameterValues("goalsCoefficients");
		String[] firstTeamPlayerCoefficients = request.getParameterValues("firstTeamPlayerCoefficients");
		String[] secondTeamPlayerCoefficients = request.getParameterValues("secondTeamPlayerCoefficients");
		String[] firstTeamPlayerId = request.getParameterValues("firstTeamPlayerId");
		String[] secondTeamPlayerId = request.getParameterValues("secondTeamPlayerId");
		coefficientService = new CoefficientService();
		boolean coeffUpdated=coefficientService.updateCoefficients(gameId, resultCoefficients, goalsCoefficients,
				firstTeamPlayerCoefficients, secondTeamPlayerCoefficients, firstTeamPlayerId, secondTeamPlayerId);
		if(coeffUpdated){
			response.sendRedirect("matches");
		}else{
			response.sendError(500);
		}
	}

}
