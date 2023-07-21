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
public class JournalContentSearchTag extends IncludeTag {

	public void setShowListed(boolean showListed) {
		_showListed = showListed;
	}

	public void setTargetPortletId(String targetPortletId) {
		_targetPortletId = targetPortletId;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_showListed = true;
		_targetPortletId = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:journal-content-search:showListed",
			String.valueOf(_showListed));
		request.setAttribute(
			"liferay-ui:journal-content-search:targetPortletId",
			_targetPortletId);
	}

	private static final String _PAGE =
		"/html/taglib/ui/journal_content_search/page.jsp";

	private boolean _showListed = true;
	private String _targetPortletId;

}