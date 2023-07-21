/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.taglib.servlet.taglib;

import com.liferay.osb.testray.taglib.internal.servlet.ServletContextUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

/**
 * @author Ethan Bustad
 */
public class RichInputTag extends IncludeTag {

	public void setLabel(String label) {
		_label = label;
	}

	public void setName(String name) {
		_name = name;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	public void setSelectedType(String selectedType) {
		_selectedType = selectedType;
	}

	public void setTypes(String[] types) {
		_types = types;
	}

	public void setValue(String value) {
		_value = value;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_label = null;
		_name = null;
		_selectedType = null;
		_types = null;
		_value = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("testray:rich-input:label", _label);
		request.setAttribute("testray:rich-input:name", _name);
		request.setAttribute("testray:rich-input:selectedType", _selectedType);
		request.setAttribute("testray:rich-input:types", _types);
		request.setAttribute("testray:rich-input:value", _value);
	}

	private static final String _PAGE = "/rich-input/page.jsp";

	private String _label;
	private String _name;
	private String _selectedType;
	private String[] _types;
	private String _value;

}