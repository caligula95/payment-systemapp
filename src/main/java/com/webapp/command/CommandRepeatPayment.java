package com.webapp.command;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.entity.Payment;
import com.webapp.entity.User;
import com.webapp.manager.ConstantManager;
import com.webapp.manager.ResourceManager;
import com.webapp.persistence.factory.MySqlDaoFactory;
import com.webapp.service.AccountService;
import com.webapp.service.PaymentService;

public class CommandRepeatPayment implements ICommand {
	private static final double PERCENT = 0.01;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		String paymentId = request.getParameter("paymentId");
		User user = (User) request.getSession().getAttribute("currentUser");
		Payment payment = null;
		String number = null;
		BigDecimal sum = null;
		String appointment = null;
		Integer accountId = null;
		if (paymentId != null) {
			payment = MySqlDaoFactory.createPaymentDao().findPaymentByPaymentId(Integer.parseInt(paymentId));
			number = payment.getCardNumber();
			sum = payment.getSum();
			appointment = payment.getAppointment();
			accountId = payment.getAccountId();
		}
		if (method.equalsIgnoreCase(ConstantManager.GET.name())) {
			request.setAttribute("accounts", AccountService.getInstance().findAllAccountsByUserId(user.getUserId()));
			request.setAttribute("numberValue", number);
			request.setAttribute("summaValue", sum);
			request.setAttribute("appointmentValue", appointment);
			return ResourceManager.getInstance().getProperty(ResourceManager.MAKE_PAYMENT);
		} else if (method.equalsIgnoreCase(ConstantManager.POST.name())) {
			PaymentService.getInstance().formPayment(sum, number, accountId, PERCENT, appointment);
		}

		return ResourceManager.getInstance().getProperty(ResourceManager.HOME);
	}

}
