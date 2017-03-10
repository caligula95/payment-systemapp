package com.webapp.manager;

/**
 * ConstantManager saves constants
 */
public enum ConstantManager {

	GET("GET"), POST("POST"); 
	
	private String value;
	
	ConstantManager(String v) {
		 value = v;
	}
	
	public String getValue() {
		return value;
	}
}
