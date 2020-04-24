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