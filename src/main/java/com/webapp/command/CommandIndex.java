package com.webapp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webapp.entity.User;
import com.webapp.manager.ResourceManager;
import com.webapp.service.UserService;

public class CommandIndex implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;

		HttpSession session = request.getSession(false);
		if (session != null) {

			User user = (User) session.getAttribute("currentUser");
			if (user == null) {
				page = ResourceManager.getInstance().getProperty(ResourceManager.INDEX);
			}
			String role = UserService.getInstance().checkRole(user);
			if (role.equals("admin"))
				page = ResourceManager.getInstance().getProperty(ResourceManager.ADMIN);
			else if (role.equals("client"))
				page = ResourceManager.getInstance().getProperty(ResourceManager.HOME);
		} else
			page = ResourceManager.getInstance().getProperty(ResourceManager.INDEX);
		return page;
	}

}
