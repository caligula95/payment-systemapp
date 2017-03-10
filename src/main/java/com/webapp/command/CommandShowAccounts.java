package com.webapp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.entity.User;
import com.webapp.manager.ResourceManager;
import com.webapp.service.AccountService;

public class CommandShowAccounts implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("currentUser");
		request.setAttribute("showAccouts", true);
		request.setAttribute("accounts", AccountService.getInstance().findAllAccountsByUserId(user.getUserId()));
		return ResourceManager.getInstance().getProperty(ResourceManager.HOME);
	}

}
