/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import java.text.Format;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class CalendarTag extends IncludeTag {

	public void setData(Set<Integer> data) {
		_data = data;
	}

	public void setDay(int day) {
		_day = day;
	}

	public void setHeaderFormat(Format headerFormat) {
		_headerFormat = headerFormat;
	}

	public void setHeaderPattern(String headerPattern) {
		_headerPattern = headerPattern;
	}

	public void setMonth(int month) {
		_month = month;
	}

	public void setShowAllPotentialWeeks(boolean showAllPotentialWeeks) {
		_showAllPotentialWeeks = showAllPotentialWeeks;
	}

	public void setYear(int year) {
		_year = year;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_data = null;
		_day = 0;
		_headerFormat = null;
		_headerPattern = null;
		_month = 0;
		_showAllPotentialWeeks = false;
		_year = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:calendar:data", _data);
		request.setAttribute("liferay-ui:calendar:day", String.valueOf(_day));
		request.setAttribute("liferay-ui:calendar:headerFormat", _headerFormat);
		request.setAttribute(
			"liferay-ui:calendar:headerPattern", _headerPattern);
		request.setAttribute(
			"liferay-ui:calendar:month", String.valueOf(_month));
		request.setAttribute(
			"liferay-ui:calendar:showAllPotentialWeeks",
			String.valueOf(_showAllPotentialWeeks));
		request.setAttribute("liferay-ui:calendar:year", String.valueOf(_year));
	}

	private static final String _PAGE = "/html/taglib/ui/calendar/page.jsp";

	private Set<Integer> _data;
	private int _day;
	private Format _headerFormat;
	private String _headerPattern;
	private int _month;
	private boolean _showAllPotentialWeeks;
	private int _year;

}