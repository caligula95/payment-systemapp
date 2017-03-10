package com.webapp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.manager.ResourceManager;
import com.webapp.service.UserService;

public class CommandShowAllUsers implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("showUsers", true);
		request.setAttribute("users", UserService.getInstance().findAll());

		return ResourceManager.getInstance().getProperty(ResourceManager.ADMIN);
	}
}
