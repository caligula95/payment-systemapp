package com.webapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.webapp.command.ICommand;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * LOG for this class.
	 */
	private static final Logger LOGGER = Logger.getLogger(Controller.class);

	/**
	 * Main method of this controller.
	 * 
	 * @param request
	 *            request.
	 * @param response
	 *            response.
	 * @throws ServletException
	 * @throws IOException
	 */
	private void process(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		ICommand command = ControllerHelper.getInstance().getCommand(request);
		String path = command.execute(request, response);
		request.getRequestDispatcher(path).forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("===================GET works");
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("===================POST works");
		process(request, response);
	}

}
