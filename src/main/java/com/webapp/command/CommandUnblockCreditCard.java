package com.webapp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.manager.ResourceManager;
import com.webapp.service.AccountService;
import com.webapp.service.CreditCardService;

public class CommandUnblockCreditCard implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = (String) request.getSession().getAttribute("userId");
		String cardNumber = request.getParameter("cardNumber");
		System.out.println(cardNumber);
		if (cardNumber != null)
			CreditCardService.getInstance().unblockCreditCard(cardNumber);
		request.setAttribute("showAccouts", true);
		if (userId != null)
			request.setAttribute("accounts",
					AccountService.getInstance().findAllAccountsByUserId(Integer.parseInt(userId)));
		return ResourceManager.getInstance().getProperty(ResourceManager.SHOW_CARDS);
	}

}
