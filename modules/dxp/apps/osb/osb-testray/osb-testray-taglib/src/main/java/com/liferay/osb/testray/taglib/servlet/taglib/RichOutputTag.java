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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Ethan Bustad
 */
public class RichOutputTag extends IncludeTag {

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setType(String type) {
		_type = type;
	}

	public void setValue(String value) {
		_value = value;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_type = null;
		_value = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("testray:rich-output:type", _type);
		request.setAttribute("testray:rich-output:value", _value);
	}

	private static final String _PAGE = "/rich-output/page.jsp";

	private String _type;
	private String _value;

}