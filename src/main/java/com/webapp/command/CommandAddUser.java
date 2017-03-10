package com.webapp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.manager.ConstantManager;
import com.webapp.manager.ResourceManager;
import com.webapp.service.UserService;
import com.webapp.util.StringValidator;

public class CommandAddUser implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = null;
		String method = request.getMethod();
		if (method.equalsIgnoreCase(ConstantManager.GET.name()))
			page = ResourceManager.getInstance().getProperty(ResourceManager.ADDUSER);
		else if (method.equalsIgnoreCase(ConstantManager.POST.name())) {
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			String phone = request.getParameter("phone");
			page = ResourceManager.getInstance().getProperty(ResourceManager.ADDUSER);
			if (name == null || name.isEmpty()) {
				request.setAttribute("nameValue", name);
				request.setAttribute("surnameValue", surname);
				request.setAttribute("phoneValue", phone);
				request.setAttribute("nameError", true);
				return page;
			} else if (surname == null || surname.isEmpty()) {
				request.setAttribute("nameValue", name);
				request.setAttribute("surnameValue", surname);
				request.setAttribute("phoneValue", phone);
				request.setAttribute("surnameError", true);
				return page;
			} else if (phone == null || phone.isEmpty() || !StringValidator.checkPhoneNumber(phone)) {
				request.setAttribute("nameValue", name);
				request.setAttribute("surnameValue", surname);
				request.setAttribute("phoneValue", phone);
				request.setAttribute("phoneError", true);
				return page;
			}
			int status = UserService.getInstance().addNewUser(name, surname, phone);
			if (status != 0) {
				request.setAttribute("showUser", true);
				request.setAttribute("user", UserService.getInstance().findUserById(status));
				page = ResourceManager.getInstance().getProperty(ResourceManager.ADMIN);
			} else {
				request.setAttribute("errorOccurred", true);
				page = ResourceManager.getInstance().getProperty(ResourceManager.ADDUSER);
			}
		}
		return page;
	}

}
