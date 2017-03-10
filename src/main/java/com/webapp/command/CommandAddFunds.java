package com.webapp.command;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.manager.ResourceManager;
import com.webapp.service.AccountService;

public class CommandAddFunds implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccountService accountService = AccountService.getInstance();
		String accountId = request.getParameter("accountId");
		String summa = request.getParameter("summa");
		String userId = (String) request.getSession().getAttribute("userId");
		;
		if (accountId != null) {
			accountService.addFunds(Integer.parseInt(accountId), new BigDecimal(summa));
		}
		if (userId != null)
			request.setAttribute("accounts",
					AccountService.getInstance().findAllAccountsByUserId(Integer.parseInt(userId)));
		return ResourceManager.getInstance().getProperty(ResourceManager.SHOW_CARDS);
	}

}
