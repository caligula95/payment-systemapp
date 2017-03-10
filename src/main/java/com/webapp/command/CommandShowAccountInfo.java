package com.webapp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.entity.User;
import com.webapp.manager.ResourceManager;
import com.webapp.service.AccountService;
import com.webapp.service.CreditCardService;
import com.webapp.service.PaymentService;

public class CommandShowAccountInfo implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		User user = (User) request.getSession().getAttribute("currentUser");
		String accountId = request.getParameter("accountId");
		request.setAttribute("showAccouts", true);
		request.setAttribute("accounts", AccountService.getInstance().findAllAccountsByUserId(user.getUserId()));
		if (accountId != null) {
			request.setAttribute("payments",
					PaymentService.getInstance().findAllPaymentsByAccountId(Integer.parseInt(accountId)));
			request.setAttribute("cards",
					CreditCardService.getInstance().findCardsByAccountId(Integer.parseInt(accountId)));
		}
		return ResourceManager.getInstance().getProperty(ResourceManager.HOME);
	}

}
