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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Kevin Tan
 */
public class FilterDateElementTag extends FilterElementTag {

	public void setDayParam(String dayParam) {
		_dayParam = dayParam;
	}

	public void setMonthParam(String monthParam) {
		_monthParam = monthParam;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		setType("date");
	}

	public void setYearParam(String yearParam) {
		_yearParam = yearParam;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_dayParam = null;
		_monthParam = null;
		_yearParam = null;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		super.setAttributes(request);

		request.setAttribute("testray:filter-date-element:dayParam", _dayParam);
		request.setAttribute(
			"testray:filter-date-element:monthParam", _monthParam);
		request.setAttribute(
			"testray:filter-date-element:yearParam", _yearParam);
	}

	private static final String _START_PAGE = "/filter-date-element/start.jsp";

	private String _dayParam;
	private String _monthParam;
	private String _yearParam;

}