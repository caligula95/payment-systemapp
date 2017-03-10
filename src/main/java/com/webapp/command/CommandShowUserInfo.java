package com.webapp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.manager.ResourceManager;
import com.webapp.service.AccountService;

public class CommandShowUserInfo implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		if (userId != null) {
			request.getSession().setAttribute("userId", userId);
			request.setAttribute("accounts",
					AccountService.getInstance().findAllAccountsByUserId(Integer.parseInt(userId)));
		}
		return ResourceManager.getInstance().getProperty(ResourceManager.SHOW_CARDS);
	}

}
