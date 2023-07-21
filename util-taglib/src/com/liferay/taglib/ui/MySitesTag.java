/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class MySitesTag extends IncludeTag {

	public void setClassNames(String[] classNames) {
		_classNames = classNames;
	}

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setMax(int max) {
		_max = max;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_classNames = null;
		_cssClass = null;
		_max = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:my_sites:classNames", _classNames);
		request.setAttribute(
			"liferay-ui:my_sites:cssClass", String.valueOf(_cssClass));
		request.setAttribute("liferay-ui:my_sites:max", String.valueOf(_max));
	}

	private static final String _PAGE = "/html/taglib/ui/my_sites/page.jsp";

	private String[] _classNames;
	private String _cssClass;
	private int _max;

}