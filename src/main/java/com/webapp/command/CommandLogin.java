package com.webapp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.webapp.entity.Role;
import com.webapp.entity.User;
import com.webapp.manager.ResourceManager;
import com.webapp.service.UserService;

public class CommandLogin implements ICommand {
	public static final String LOGIN_PARAMETER = "login";
	public static final String PASSWORD_PARAMETER = "password";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = null;
		String page = null;
		HttpSession session = request.getSession();
		final String login = request.getParameter(LOGIN_PARAMETER);
		String password = request.getParameter(PASSWORD_PARAMETER);
		if (login == null || login.isEmpty()) {
			request.setAttribute("phoneError", true);
			request.setAttribute("phoneValue", login);
			return ResourceManager.getInstance().getProperty(ResourceManager.INDEX);
		} else if (password == null || password.isEmpty()) {
			request.setAttribute("phoneValue", login);
			request.setAttribute("loginError", true);
			return ResourceManager.getInstance().getProperty(ResourceManager.INDEX);
		}
		user = UserService.getInstance().loginUser(login, password);
		if (user != null) {
			session.setAttribute("currentUser", user);
			String role = UserService.getInstance().checkRole(user);
			if (role.equals(Role.ROLE_ADMIN)) {
				page = ResourceManager.getInstance().getProperty(ResourceManager.ADMIN);
			} else if (role.equals(Role.ROLE_CLIENT)) {
				page = ResourceManager.getInstance().getProperty(ResourceManager.HOME);
			}
		} else {
			request.setAttribute("phoneValue", login);
			request.setAttribute("loginError", true);
			page = ResourceManager.getInstance().getProperty(ResourceManager.INDEX);
		}
		return page;
	}

}
