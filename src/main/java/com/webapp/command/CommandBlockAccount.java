package com.webapp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.entity.User;
import com.webapp.manager.ResourceManager;
import com.webapp.service.AccountService;

public class CommandBlockAccount implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String accountId = request.getParameter("accountId");
		User user = (User) request.getSession().getAttribute("currentUser");
		System.out.println(user);
		if (accountId != null)
			AccountService.getInstance().blockAccont(Integer.parseInt(accountId));
		request.setAttribute("showAccouts", true);
		request.setAttribute("accounts", AccountService.getInstance().findAllAccountsByUserId(user.getUserId()));
		return ResourceManager.getInstance().getProperty(ResourceManager.HOME);
	}

}
