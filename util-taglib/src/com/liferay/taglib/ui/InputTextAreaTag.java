/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.FriendlyURLNormalizerUtil;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class InputTextAreaTag extends IncludeTag {

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setDefaultValue(String defaultValue) {
		_defaultValue = defaultValue;
	}

	public void setDisabled(boolean disabled) {
		_disabled = disabled;
	}

	public void setParam(String param) {
		_param = param;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_cssClass = null;
		_defaultValue = StringPool.BLANK;
		_disabled = false;
		_param = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:input-textarea:cssClass", _cssClass);
		request.setAttribute(
			"liferay-ui:input-textarea:defaultValue", _defaultValue);
		request.setAttribute(
			"liferay-ui:input-textarea:disabled", String.valueOf(_disabled));
		request.setAttribute("liferay-ui:input-textarea:param", _param);
		request.setAttribute(
			"liferay-ui:input-textarea:paramId",
			FriendlyURLNormalizerUtil.normalize(_param));
	}

	private static final String _PAGE =
		"/html/taglib/ui/input_textarea/page.jsp";

	private String _cssClass;
	private String _defaultValue = StringPool.BLANK;
	private boolean _disabled;
	private String _param;

}