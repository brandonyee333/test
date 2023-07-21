/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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