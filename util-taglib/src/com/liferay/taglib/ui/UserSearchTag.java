/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.taglib.util.IncludeTag;

import java.util.LinkedHashMap;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class UserSearchTag extends IncludeTag {

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	public void setRowChecker(RowChecker rowChecker) {
		_rowChecker = rowChecker;
	}

	public void setUserParams(LinkedHashMap<String, Object> userParams) {
		_userParams = userParams;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_portletURL = null;
		_rowChecker = null;
		_userParams = null;
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:user-search:portletURL", _portletURL);
		request.setAttribute("liferay-ui:user-search:rowChecker", _rowChecker);
		request.setAttribute("liferay-ui:user-search:userParams", _userParams);
	}

	private static final String _END_PAGE =
		"/html/taglib/ui/user_search/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/ui/user_search/start.jsp";

	private PortletURL _portletURL;
	private RowChecker _rowChecker;
	private LinkedHashMap<String, Object> _userParams;

}