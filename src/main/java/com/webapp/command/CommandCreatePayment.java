package com.webapp.command;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.entity.User;
import com.webapp.manager.ConstantManager;
import com.webapp.manager.ResourceManager;
import com.webapp.service.AccountService;
import com.webapp.service.PaymentService;

public class CommandCreatePayment implements ICommand {

	private static final double PERCENT = 0.01;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String page = null;
		PaymentService paymentService = PaymentService.getInstance();
		User user = (User) request.getSession().getAttribute("currentUser");
		String method = request.getMethod();
		if (method.equalsIgnoreCase(ConstantManager.GET.name())) {
			request.setAttribute("accounts", AccountService.getInstance().findAllAccountsByUserId(user.getUserId()));
			page = ResourceManager.getInstance().getProperty(ResourceManager.MAKE_PAYMENT);
		} else if (method.equalsIgnoreCase(ConstantManager.POST.name())) {
			Integer accountId = Integer.parseInt(request.getParameter("account"));
			String number = request.getParameter("number");
			String summa = request.getParameter("summa");
			String appointment = request.getParameter("appointment");
			if (accountId != null && number != null && summa != null) {
				paymentService.formPayment(new BigDecimal(summa), number, accountId, PERCENT, appointment);
			}
			page = ResourceManager.getInstance().getProperty(ResourceManager.HOME);
		}
		return page;
	}

}
