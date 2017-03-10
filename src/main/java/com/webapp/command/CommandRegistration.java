package com.webapp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.manager.ConstantManager;
import com.webapp.manager.ResourceManager;
import com.webapp.service.UserService;

public class CommandRegistration implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		String page = null;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String passwordConfirmation = request.getParameter("passwordConfirmation");
		int status = 0;

		if (method.equalsIgnoreCase(ConstantManager.GET.name())) {
			page = ResourceManager.getInstance().getProperty(ResourceManager.REGISTRATION);
		} else if (method.equalsIgnoreCase(ConstantManager.POST.name())) {
			page = ResourceManager.getInstance().getProperty(ResourceManager.REGISTRATION);
			if (phone == null || phone.isEmpty()) {
				request.setAttribute("phoneError", true);
				request.setAttribute("phoneValue", phone);
				request.setAttribute("emailValue", email);
				return page;
			}

			else if (password == null || password.isEmpty()) {
				request.setAttribute("passwordError", true);
				request.setAttribute("phoneValue", phone);
				request.setAttribute("emailValue", email);
				return page;
			}

			else if (!password.equals(passwordConfirmation)) {
				request.setAttribute("passwordConfirmationError", true);
				request.setAttribute("phoneValue", phone);
				request.setAttribute("emailValue", email);
				return page;
			}

			status = UserService.getInstance().registerUser(email, password, phone);

			if (status == 0) {
				request.setAttribute("errorMessage", true);
				request.setAttribute("phoneValue", phone);
				request.setAttribute("emailValue", email);
				page = ResourceManager.getInstance().getProperty(ResourceManager.REGISTRATION);
			}
		}
		return page;
	}

}
