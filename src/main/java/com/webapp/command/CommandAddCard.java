package com.webapp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.webapp.manager.ConstantManager;
import com.webapp.manager.ResourceManager;
import com.webapp.service.AccountService;
import com.webapp.service.CreditCardService;
import com.webapp.util.StringValidator;

public class CommandAddCard implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getMethod();
		String page = null;
		String accountId = request.getParameter("accountId");
		if (method.equalsIgnoreCase(ConstantManager.GET.name())) {
			request.setAttribute("accountId", accountId);
			page = ResourceManager.getInstance().getProperty(ResourceManager.ADD_CARD);
		} else if (method.equalsIgnoreCase(ConstantManager.POST.name())) {
			String number = request.getParameter("number");
			String cvv = request.getParameter("cvv");
			String validity = request.getParameter("validity");
			if (number.isEmpty() || !StringValidator.checkCardNumber(number)) {
				request.setAttribute("numberError", true);
				request.setAttribute("numberValue", number);
				request.setAttribute("cvvValue", cvv);
				request.setAttribute("validityValue", validity);
				request.setAttribute("accountId", accountId);
				page = ResourceManager.getInstance().getProperty(ResourceManager.ADD_CARD);
			} else if (cvv.isEmpty() || StringValidator.checkCvv(cvv) == false) {
				request.setAttribute("cvvError", true);
				request.setAttribute("numberValue", number);
				request.setAttribute("cvvValue", cvv);
				request.setAttribute("validityValue", validity);
				request.setAttribute("accountId", accountId);
				page = ResourceManager.getInstance().getProperty(ResourceManager.ADD_CARD);
			} else if (validity.isEmpty() || validity == null) {
				request.setAttribute("validityError", true);
				request.setAttribute("numberValue", number);
				request.setAttribute("cvvValue", cvv);
				request.setAttribute("validityValue", validity);
				request.setAttribute("accountId", accountId);
				page = ResourceManager.getInstance().getProperty(ResourceManager.ADD_CARD);
			} else {
				int status = CreditCardService.getInstance().addNewCard(number, cvv, validity, accountId);
				if (status == 0) {
					request.setAttribute("cardError", true);
					request.setAttribute("numberValue", number);
					request.setAttribute("cvvValue", cvv);
					request.setAttribute("validityValue", validity);
					request.setAttribute("accountId", accountId);
					page = ResourceManager.getInstance().getProperty(ResourceManager.ADD_CARD);
				} else {
					String userId = (String) request.getSession().getAttribute("userId");
					if (userId != null)
						request.setAttribute("accounts",
								AccountService.getInstance().findAllAccountsByUserId(Integer.parseInt(userId)));
					if (accountId != null)
						request.setAttribute("cards",
								CreditCardService.getInstance().findCardsByAccountId(Integer.parseInt(accountId)));
					page = ResourceManager.getInstance().getProperty(ResourceManager.SHOW_CARDS);
				}
			}
		}

		return page;
	}

}
