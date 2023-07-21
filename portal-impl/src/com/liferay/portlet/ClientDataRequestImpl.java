/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet;

import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.portlet.ClientDataRequest;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class ClientDataRequestImpl
	extends PortletRequestImpl implements ClientDataRequest {

	@Override
	public String getCharacterEncoding() {
		return getHttpServletRequest().getCharacterEncoding();
	}

	@Override
	public int getContentLength() {
		return getHttpServletRequest().getContentLength();
	}

	@Override
	public String getContentType() {
		return getHttpServletRequest().getContentType();
	}

	@Override
	public String getMethod() {
		return getHttpServletRequest().getMethod();
	}

	@Override
	public InputStream getPortletInputStream() throws IOException {
		_checkContentType();

		return getHttpServletRequest().getInputStream();
	}

	@Override
	public BufferedReader getReader()
		throws IOException, UnsupportedEncodingException {

		_calledGetReader = true;

		_checkContentType();

		return getHttpServletRequest().getReader();
	}

	@Override
	public void setCharacterEncoding(String enc)
		throws UnsupportedEncodingException {

		if (_calledGetReader) {
			throw new IllegalStateException();
		}

		getHttpServletRequest().setCharacterEncoding(enc);
	}

	private void _checkContentType() {
		if (StringUtil.equalsIgnoreCase(getMethod(), HttpMethods.POST) &&
			StringUtil.equalsIgnoreCase(
				getContentType(),
				ContentTypes.APPLICATION_X_WWW_FORM_URLENCODED)) {

			throw new IllegalStateException();
		}
	}

	private boolean _calledGetReader;

}