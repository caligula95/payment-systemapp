package com.webapp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.manager.ResourceManager;
import com.webapp.service.AccountService;
import com.webapp.service.CreditCardService;

public class CommandDeleteCard implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cardId = request.getParameter("cardId");
		CreditCardService.getInstance().deleteCardById(Integer.parseInt(cardId));
		String userId = (String) request.getSession().getAttribute("userId");
		if (userId != null)
			request.setAttribute("accounts",
					AccountService.getInstance().findAllAccountsByUserId(Integer.parseInt(userId)));
		return ResourceManager.getInstance().getProperty(ResourceManager.SHOW_CARDS);
	}

}
