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
public class PngImageTag extends IncludeTag {

	public void setHeight(String height) {
		_height = height;
	}

	public void setImage(String image) {
		_image = image;
	}

	public void setWidth(String width) {
		_width = width;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_height = null;
		_image = null;
		_width = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:png_image:height", _height);
		request.setAttribute("liferay-ui:png_image:image", _image);
		request.setAttribute("liferay-ui:png_image:width", _width);
	}

	private static final String _PAGE = "/html/taglib/ui/png_image/page.jsp";

	private String _height;
	private String _image;
	private String _width;

}