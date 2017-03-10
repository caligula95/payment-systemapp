package com.webapp.tag;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Date tag class for support formated date output on the page
 */
public class DateTag extends TagSupport {

	private static final long serialVersionUID = 9035041191611966003L;

	@Override
	public int doStartTag() throws JspException {
		JspWriter writer = pageContext.getOut();
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		Date date = new Date();
		try {
			writer.write(dateFormat.format(date));
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		return SKIP_BODY;
	}

}
