/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.PortalIncludeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Sergio González
 */
public class AppViewToolbarTag extends TagSupport {

	@Override
	public int doEndTag() throws JspException {
		try {
			HttpServletRequest request =
				(HttpServletRequest)pageContext.getRequest();

			request.setAttribute(
				"liferay-ui:app_view_toolbar:includeDisplayStyle",
				_includeDisplayStyle);

			PortalIncludeUtil.include(pageContext, getEndPage());

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request =
				(HttpServletRequest)pageContext.getRequest();

			request.setAttribute(
				"liferay-ui:app_view_toolbar:includeSelectAll",
				_includeSelectAll);
			request.setAttribute(
				"liferay-ui:app_view_toolbar:searchJsp", _searchJsp);

			PortalIncludeUtil.include(pageContext, getStartPage());

			return EVAL_BODY_INCLUDE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public void setEndPage(String endPage) {
		_endPage = endPage;
	}

	public void setIncludeDisplayStyle(boolean includeDisplayStyle) {
		_includeDisplayStyle = includeDisplayStyle;
	}

	public void setIncludeSelectAll(boolean includeSelectAll) {
		_includeSelectAll = includeSelectAll;
	}

	public void setSearchJsp(String searchJsp) {
		_searchJsp = searchJsp;
	}

	public void setStartPage(String startPage) {
		_startPage = startPage;
	}

	protected String getEndPage() {
		if (Validator.isNull(_endPage)) {
			return _END_PAGE;
		}

		return _endPage;
	}

	protected String getStartPage() {
		if (Validator.isNull(_startPage)) {
			return _START_PAGE;
		}

		return _startPage;
	}

	private static final String _END_PAGE =
		"/html/taglib/ui/app_view_toolbar/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/ui/app_view_toolbar/start.jsp";

	private String _endPage;
	private boolean _includeDisplayStyle;
	private boolean _includeSelectAll;
	private String _searchJsp;
	private String _startPage;

}