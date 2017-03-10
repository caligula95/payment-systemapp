package com.webapp.manager;

import java.util.ResourceBundle;

/**
 * Resource manager for dealing with pages path
 */
public class ResourceManager {
	
	private static ResourceManager instance;
	
	private ResourceBundle resourceBundle;
	
	private final static String BUNDLE_NAME = "configuration";
	
	public static final String DRIVER = "DRIVER";
	public static final String URL = "URL";
	public static final String USER = "USER";
	public static final String PASSWORD = "PASSWORD";
	public static final String HOME = "HOME";
	public static final String INDEX = "INDEX";
	public static final String ADMIN = "ADMIN";
	public static final String ERROR = "ERROR";
	public static final String ADDUSER = "ADDUSER";
	public static final String MAKE_PAYMENT = "MAKE_PAYMENT";
	public static final String REGISTRATION = "REGISTRATION";
	public static final String SHOW_CARDS = "SHOW_CARDS";
	public static final String ADD_CARD = "ADD_CARD";

	private ResourceManager() {

	}

	public static ResourceManager getInstance() {
		if (instance == null) {
			instance = new ResourceManager();
			instance.resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
		}
		return instance;
	}

	/**
	 * get property from resource bundle by key
	 * @param key
	 * @return
	 */
	public String getProperty(String key) {
		return (String) resourceBundle.getObject(key);
	}
}
