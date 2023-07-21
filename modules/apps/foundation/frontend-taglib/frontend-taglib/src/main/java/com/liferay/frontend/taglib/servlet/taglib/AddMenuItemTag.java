/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.servlet.taglib;

import com.liferay.taglib.util.IncludeTag;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ambrín Chaudhary
 */
public class AddMenuItemTag extends IncludeTag {

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public void setAnchorData(Map<String, Object> anchorData) {
		_anchorData = anchorData;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setUrl(String url) {
		_url = url;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_anchorData = null;
		_id = null;
		_title = null;
		_url = null;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		List<AddMenuItem> addMenuItems =
			(List<AddMenuItem>)request.getAttribute(
				"liferay-frontend:add-menu:addMenuItems");

		if (addMenuItems != null) {
			AddMenuItem addMenuItem = new AddMenuItem(
				_anchorData, _id, _title, _url);

			addMenuItems.add(addMenuItem);
		}
	}

	private Map<String, Object> _anchorData;
	private String _id;
	private String _title;
	private String _url;

}