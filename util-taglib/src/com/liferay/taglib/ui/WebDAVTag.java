/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class WebDAVTag extends IncludeTag {

	public void setPath(String path) {
		_path = path;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_path = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:webdav:path", _path);
	}

	private static final String _PAGE = "/html/taglib/ui/webdav/page.jsp";

	private String _path;

}