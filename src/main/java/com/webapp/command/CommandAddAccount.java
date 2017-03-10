package com.webapp.command;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.manager.ResourceManager;
import com.webapp.service.AccountService;

public class CommandAddAccount implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String balance = request.getParameter("balance");
		String number = request.getParameter("number");
		if (userId != null && balance != null && number != null) {
			AccountService.getInstance().createAccount(number, Integer.parseInt(userId), new BigDecimal(balance));
		}
		if (userId != null) {
			request.setAttribute("accounts",
					AccountService.getInstance().findAllAccountsByUserId(Integer.parseInt(userId)));
			request.getSession().setAttribute("userId", userId);
		}
		return ResourceManager.getInstance().getProperty(ResourceManager.SHOW_CARDS);
	}

}
