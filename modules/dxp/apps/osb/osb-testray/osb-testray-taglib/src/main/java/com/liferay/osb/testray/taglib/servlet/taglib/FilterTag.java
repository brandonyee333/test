/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.testray.taglib.servlet.taglib;

import com.liferay.osb.testray.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * @author Kevin Tan
 * @author Ethan Bustad
 */
public class FilterTag extends IncludeTag implements BodyTag {

	@Override
	public int doEndTag() throws JspException {
		pageContext.popBody();

		super.doStartTag();

		try {
			JspWriter jspWriter = pageContext.getOut();

			BodyContent bodyContent = getBodyContent();

			jspWriter.print(bodyContent.getString());
		}
		catch (IOException ioe) {
			throw new JspException(ioe);
		}

		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		pageContext.pushBody();

		return EVAL_BODY_BUFFERED;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setPinnable(boolean pinnable) {
		_pinnable = pinnable;
	}

	protected void addFilterElement(FilterElement filterElement) {
		_filterElements.add(filterElement);
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_filterElements.clear();
		_pinnable = true;
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("testray:filter:filterElements", _filterElements);

		request.setAttribute("testray:filter:pinnable", _pinnable);
	}

	private static final String _END_PAGE = "/filter/end.jsp";

	private static final String _START_PAGE = "/filter/start.jsp";

	private final List<FilterElement> _filterElements = new ArrayList<>();
	private boolean _pinnable = true;

}