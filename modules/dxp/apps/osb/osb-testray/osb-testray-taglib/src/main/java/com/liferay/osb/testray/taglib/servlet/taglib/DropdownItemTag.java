/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.taglib.servlet.taglib;

import com.liferay.osb.testray.taglib.internal.servlet.ServletContextUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Kevin Tan
 */
public class DropdownItemTag extends IncludeTag {

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setIcon(String icon) {
		_icon = icon;
	}

	public void setLabel(String label) {
		_label = label;
	}

	public void setOnClick(String onClick) {
		_onClick = onClick;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setUrl(String url) {
		_url = url;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_cssClass = null;
		_icon = null;
		_label = null;
		_onClick = null;
		_url = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("testray:dropdown-item:cssClass", _cssClass);
		request.setAttribute("testray:dropdown-item:icon", _icon);
		request.setAttribute("testray:dropdown-item:label", _label);
		request.setAttribute("testray:dropdown-item:onClick", _onClick);
		request.setAttribute("testray:dropdown-item:url", _getItemUrl());
	}

	private String _getItemUrl() {
		if (Validator.isNull(_url)) {
			_url = "javascript:;";
		}

		return _url;
	}

	private static final String _PAGE = "/dropdown-item/page.jsp";

	private String _cssClass;
	private String _icon;
	private String _label;
	private String _onClick;
	private String _url;

}