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
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.taglib.util.IncludeTag;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Kevin Tan
 */
public class DateTag extends IncludeTag {

	public void setHideTime(boolean hideTime) {
		_hideTime = hideTime;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setRelative(boolean relative) {
		_relative = relative;
	}

	public void setValue(Date value) {
		_value = value;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_hideTime = false;
		_relative = false;
		_value = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("testray:date:hideTime", _hideTime);
		request.setAttribute("testray:date:id", _getId());
		request.setAttribute("testray:date:relative", _relative);
		request.setAttribute("testray:date:value", _value);
	}

	private String _getId() {
		return PortalUtil.generateRandomKey(request, "testray_date") +
			StringPool.UNDERLINE + "testrayDate";
	}

	private static final String _PAGE = "/date/page.jsp";

	private boolean _hideTime;
	private boolean _relative;
	private Date _value;

}