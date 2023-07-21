/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 */
public class CategorizationFilterTag extends IncludeTag {

	public long[] getGroupIds() {
		return _groupIds;
	}

	public void setAssetType(String assetType) {
		_assetType = assetType;
	}

	public void setGroupIds(long[] groupIds) {
		_groupIds = groupIds;
	}

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_assetType = null;
		_groupIds = null;
		_portletURL = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:categorization-filter:assetType", _assetType);
		request.setAttribute(
			"liferay-ui:categorization-filter:groupIds", _groupIds);
		request.setAttribute(
			"liferay-ui:categorization-filter:portletURL", _portletURL);
	}

	private static final String _PAGE =
		"/html/taglib/ui/categorization_filter/page.jsp";

	private String _assetType;
	private long[] _groupIds;
	private PortletURL _portletURL;

}