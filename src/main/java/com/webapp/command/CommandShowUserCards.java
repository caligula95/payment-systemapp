package com.webapp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.manager.ResourceManager;
import com.webapp.service.AccountService;
import com.webapp.service.CreditCardService;

public class CommandShowUserCards implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accountId = request.getParameter("accountId");
		String userId = (String) request.getSession().getAttribute("userId");
		if (userId != null)
			request.setAttribute("accounts",
					AccountService.getInstance().findAllAccountsByUserId(Integer.parseInt(userId)));
		if (accountId != null)
			request.setAttribute("cards",
					CreditCardService.getInstance().findCardsByAccountId(Integer.parseInt(accountId)));
		return ResourceManager.getInstance().getProperty(ResourceManager.SHOW_CARDS);
	}

}
