/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class SocialBookmarksSettingsTag extends IncludeTag {

	public void setDisplayPosition(String displayPosition) {
		_displayPosition = displayPosition;
	}

	public void setDisplayStyle(String displayStyle) {
		_displayStyle = displayStyle;
	}

	public void setEnabled(boolean enabled) {
		_enabled = enabled;
	}

	public void setTypes(String types) {
		_types = types;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_displayPosition = null;
		_displayStyle = null;
		_enabled = false;
		_types = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:social-bookmarks-settings:displayPosition",
			_displayPosition);
		request.setAttribute(
			"liferay-ui:social-bookmarks-settings:displayStyle", _displayStyle);
		request.setAttribute(
			"liferay-ui:social-bookmarks-settings:enabled",
			String.valueOf(_enabled));
		request.setAttribute(
			"liferay-ui:social-bookmarks-settings:types", _types);
	}

	private static final String _PAGE =
		"/html/taglib/ui/social_bookmarks_settings/page.jsp";

	private String _displayPosition;
	private String _displayStyle;
	private boolean _enabled;
	private String _types;

}