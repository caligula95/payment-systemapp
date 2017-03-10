package com.webapp.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class SessionListener implements HttpSessionListener {
	/**
	 * LOG for this class.
	 */
	private static final Logger LOGGER = Logger.getLogger(SessionListener.class);

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		LOGGER.info("Session with id = " + session.getId() + " started.");
		session.setMaxInactiveInterval(600);
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		HttpSession session = sessionEvent.getSession();
		LOGGER.info("Session with id = " + session.getId() + " ended.");
	}
}
