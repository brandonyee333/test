/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.internal.servlet.ServletContextUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.taglib.util.IncludeTag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * @author Ambrín Chaudhary
 */
public class AddMenuTag extends IncludeTag {

	@Override
	public int doEndTag() throws JspException {
		List<AddMenuItem> addMenuItems =
			(List<AddMenuItem>)request.getAttribute(
				"liferay-frontend:add-menu:addMenuItems");

		if (ListUtil.isEmpty(addMenuItems)) {
			return SKIP_BODY;
		}

		return super.doEndTag();
	}

	@Override
	public int doStartTag() {
		request.setAttribute(
			"liferay-frontend:add-menu:addMenuItems", _addMenuItems);

		return EVAL_BODY_INCLUDE;
	}

	public void setAddMenuItems(List<AddMenuItem> addMenuItems) {
		_addMenuItems = addMenuItems;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);

		servletContext = ServletContextUtil.getServletContext();
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_addMenuItems = new ArrayList<>();
	}

	@Override
	protected String getPage() {
		return "/add_menu/page.jsp";
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		List<AddMenuItem> addMenuItems =
			(List<AddMenuItem>)request.getAttribute(
				"liferay-frontend:add-menu:addMenuItems");

		request.setAttribute(
			"liferay-frontend:add-menu:addMenuItems", addMenuItems);
	}

	private List<AddMenuItem> _addMenuItems = new ArrayList<>();

}