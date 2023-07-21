/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 */
public class LogoSelectorTag extends IncludeTag {

	public void setCurrentLogoURL(String currentLogoURL) {
		_currentLogoURL = currentLogoURL;
	}

	public void setDefaultLogo(boolean defaultLogo) {
		_defaultLogo = defaultLogo;
	}

	public void setDefaultLogoURL(String defaultLogoURL) {
		_defaultLogoURL = defaultLogoURL;
	}

	public void setEditLogoFn(String editLogoFn) {
		_editLogoFn = editLogoFn;
	}

	public void setLogoDisplaySelector(String logoDisplaySelector) {
		_logoDisplaySelector = logoDisplaySelector;
	}

	public void setMaxFileSize(long maxFileSize) {
		_maxFileSize = maxFileSize;
	}

	public void setShowBackground(boolean showBackground) {
		_showBackground = showBackground;
	}

	public void setShowButtons(boolean showButtons) {
		_showButtons = showButtons;
	}

	public void setTempImageFileName(String tempImageFileName) {
		_tempImageFileName = tempImageFileName;
	}

	@Override
	protected void cleanUp() {
		super.cleanUp();

		_currentLogoURL = null;
		_defaultLogo = false;
		_defaultLogoURL = null;
		_editLogoFn = null;
		_logoDisplaySelector = null;
		_maxFileSize = 0;
		_showBackground = true;
		_showButtons = true;
		_tempImageFileName = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:logo-selector:currentLogoURL", _currentLogoURL);
		request.setAttribute(
			"liferay-ui:logo-selector:defaultLogo",
			String.valueOf(_defaultLogo));
		request.setAttribute(
			"liferay-ui:logo-selector:defaultLogoURL", _defaultLogoURL);
		request.setAttribute(
			"liferay-ui:logo-selector:editLogoFn", _editLogoFn);
		request.setAttribute(
			"liferay-ui:logo-selector:logoDisplaySelector",
			_logoDisplaySelector);

		if (_maxFileSize == 0) {
			try {
				_maxFileSize = PrefsPropsUtil.getLong(
					PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE);
			}
			catch (SystemException se) {

				// LPS-52675

				if (_log.isDebugEnabled()) {
					_log.debug(se, se);
				}
			}
		}

		request.setAttribute(
			"liferay-ui:logo-selector:maxFileSize",
			String.valueOf(_maxFileSize));

		request.setAttribute(
			"liferay-ui:logo-selector:showBackground",
			String.valueOf(_showBackground));
		request.setAttribute(
			"liferay-ui:logo-selector:showButtons",
			String.valueOf(_showButtons));
		request.setAttribute(
			"liferay-ui:logo-selector:tempImageFileName", _tempImageFileName);
	}

	private static final String _PAGE =
		"/html/taglib/ui/logo_selector/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		LogoSelectorTag.class);

	private String _currentLogoURL;
	private boolean _defaultLogo;
	private String _defaultLogoURL;
	private String _editLogoFn;
	private String _logoDisplaySelector;
	private long _maxFileSize;
	private boolean _showBackground = true;
	private boolean _showButtons = true;
	private String _tempImageFileName;

}