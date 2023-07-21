/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.taglib.servlet.taglib;

import com.liferay.osb.testray.taglib.internal.servlet.ServletContextUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.SessionClicks;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Kevin Tan
 */
public class ConfigureColumnsTag extends IncludeTag {

	@Override
	public int doStartTag() throws JspException {
		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		_columnLabelsSelected = _getSelectedTableColumns(
			request, _sessionKey, _columnLabelsDefault);

		pageContext.setAttribute(_sessionKey, _columnLabelsSelected);

		return super.doStartTag();
	}

	public void setColumnLabels(String[] columnLabels) {
		_columnLabels = columnLabels;
	}

	public void setColumnLabelsDefault(String[] columnLabelsDefault) {
		_columnLabelsDefault = columnLabelsDefault;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setSessionKey(String sessionKey) {
		_sessionKey = sessionKey;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_columnLabels = null;
		_columnLabelsDefault = null;
		_columnLabelsSelected = null;
		_sessionKey = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"testray:configure-columns:columnLabels", _columnLabels);
		request.setAttribute(
			"testray:configure-columns:columnLabelsSelected",
			_columnLabelsSelected);
		request.setAttribute(
			"testray:configure-columns:sessionKey", _sessionKey);
	}

	private List<String> _getSelectedTableColumns(
		HttpServletRequest request, String sessionKey,
		String[] defaultColumns) {

		String selectedTableColumnsString = SessionClicks.get(
			request, sessionKey, null);

		if (Validator.isNotNull(selectedTableColumnsString)) {
			return ListUtil.fromString(
				selectedTableColumnsString, StringPool.COMMA);
		}

		return ListUtil.toList(defaultColumns);
	}

	private static final String _PAGE = "/configure-columns/page.jsp";

	private String[] _columnLabels;
	private String[] _columnLabelsDefault;
	private List<String> _columnLabelsSelected;
	private String _sessionKey;

}