package com.webapp.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.webapp.command.CommandAddAccount;
import com.webapp.command.CommandAddCard;
import com.webapp.command.CommandAddFunds;
import com.webapp.command.CommandAddUser;
import com.webapp.command.CommandShowAllUsers;
import com.webapp.command.CommandBlockAccount;
import com.webapp.command.CommandBlockCreditCard;
import com.webapp.command.CommandCreatePayment;
import com.webapp.command.CommandDeleteCard;
import com.webapp.command.CommandIndex;
import com.webapp.command.CommandLogin;
import com.webapp.command.CommandLogout;
import com.webapp.command.CommandRegistration;
import com.webapp.command.CommandRepeatPayment;
import com.webapp.command.CommandShowAccountInfo;
import com.webapp.command.CommandShowUserCards;
import com.webapp.command.CommandShowUserInfo;
import com.webapp.command.CommandUnblockAccount;
import com.webapp.command.CommandUnblockCreditCard;
import com.webapp.command.CommandShowAccounts;
import com.webapp.command.ICommand;
import com.webapp.entity.User;
import com.webapp.service.UserService;

public class ControllerHelper {

	private static ControllerHelper instance = null;

	/**
	 * Users roles
	 */
	private static final String ADMIN = "admin";
	private static final String CLIENT = "client";
	private static final String ALL = "all";

	/**
	 * Request parameter name for command
	 */
	private static final String COMMAND = "command";

	/**
	 * Action commands
	 */
	HashMap<String, ICommand> commands = new HashMap<String, ICommand>();
	HashMap<String, ICommand> adminCommands = new HashMap<String, ICommand>();
	HashMap<String, ICommand> userCommands = new HashMap<String, ICommand>();

	private ControllerHelper() {

		// Everyone commands
		commands.put("login", new CommandLogin());
		commands.put("registration", new CommandRegistration());
		commands.put("/", new CommandIndex());

		// Admin commands
		adminCommands.put("adduser", new CommandAddUser());
		adminCommands.put("showuserinfo", new CommandShowUserInfo());
		adminCommands.put("unblock", new CommandUnblockAccount());
		adminCommands.put("logout", new CommandLogout());
		adminCommands.put("showAllUsers", new CommandShowAllUsers());
		adminCommands.put("showcards", new CommandShowUserCards());
		adminCommands.put("deleteCard", new CommandDeleteCard());
		adminCommands.put("addAccount", new CommandAddAccount());
		adminCommands.put("add-funds", new CommandAddFunds());
		adminCommands.put("addCard", new CommandAddCard());
		adminCommands.put("unblockCard", new CommandUnblockCreditCard());

		// Client commands
		userCommands.put("blockaccount", new CommandBlockAccount());
		userCommands.put("showinfo", new CommandShowAccountInfo());
		userCommands.put("create-payment", new CommandCreatePayment());
		userCommands.put("logout", new CommandLogout());
		userCommands.put("showAccounts", new CommandShowAccounts());
		userCommands.put("repeatPayment", new CommandRepeatPayment());
		userCommands.put("blockCard", new CommandBlockCreditCard());
	}

	/**
	 * Find command from request
	 * 
	 * @param request
	 * @return
	 */
	public ICommand getCommand(HttpServletRequest request) {
		String role = chechRoleBySession(request);
		ICommand command = null;
		if (role.equals(ALL))
			command = commands.get(request.getParameter(COMMAND));
		else if (role.equals(ADMIN))
			command = adminCommands.get(request.getParameter(COMMAND));
		else if (role.equals(CLIENT))
			command = userCommands.get(request.getParameter(COMMAND));
		if (command == null) {
			command = new CommandIndex();
		}
		return command;
	}

	/**
	 * Find role by session
	 * 
	 * @param request
	 * @return
	 */
	private String chechRoleBySession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		String role = null;
		if (session == null)
			return ALL;
		role = UserService.getInstance().checkRole((User) session.getAttribute("currentUser"));
		return role;
	}

	public static ControllerHelper getInstance() {
		if (instance == null) {
			instance = new ControllerHelper();
		}
		return instance;
	}
}
