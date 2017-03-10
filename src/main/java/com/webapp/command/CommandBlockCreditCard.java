package com.webapp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.entity.User;
import com.webapp.manager.ResourceManager;
import com.webapp.service.AccountService;
import com.webapp.service.CreditCardService;

public class CommandBlockCreditCard implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("currentUser");
		String cardNumber = request.getParameter("cardNumber");
		if (cardNumber != null)
			CreditCardService.getInstance().blockCreditCard(cardNumber);
		request.setAttribute("showAccouts", true);
		request.setAttribute("accounts", AccountService.getInstance().findAllAccountsByUserId(user.getUserId()));
		return ResourceManager.getInstance().getProperty(ResourceManager.HOME);
	}

}
