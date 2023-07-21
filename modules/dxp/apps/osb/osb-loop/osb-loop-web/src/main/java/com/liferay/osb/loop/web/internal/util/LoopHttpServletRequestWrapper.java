/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.web.internal.util;

import com.liferay.osb.loop.web.internal.configuration.LoopWebConfigurationValues;
import com.liferay.portal.kernel.servlet.DynamicServletRequest;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Timothy Bell
 */
public class LoopHttpServletRequestWrapper extends HttpServletRequestWrapper {

	public LoopHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);

		if (request instanceof DynamicServletRequest) {
			setHeaders(LoopRequestHeaderRegistryUtil.getRequestHeaders());

			LoopRequestHeaderRegistryUtil.unregisterRequestHeaders();
		}
		else {
			setHeaders(LoopHttpServletRequestUtil.getRequestHeaders(request));
		}

		if (!LoopHttpServletRequestUtil.isMobileRequest(
				getHeader(HttpHeaders.USER_AGENT))) {

			List<String> headerValues = new ArrayList<>();

			headerValues.add(
				String.valueOf(LoopWebConfigurationValues.API_VERSION_CURRENT));

			_headerMap.put("api-version", headerValues);
		}
	}

	@Override
	public String getHeader(String name) {
		List<String> values = _headerMap.get(StringUtil.toLowerCase(name));

		if (ListUtil.isEmpty(values)) {
			return StringPool.BLANK;
		}

		return values.get(0);
	}

	@Override
	public Enumeration<String> getHeaderNames() {
		return Collections.enumeration(_headerMap.keySet());
	}

	@Override
	public Enumeration<String> getHeaders(String name) {
		List<String> values = _headerMap.get(StringUtil.toLowerCase(name));

		if (values == null) {
			values = Collections.emptyList();
		}

		return Collections.enumeration(values);
	}

	@Override
	public int getIntHeader(String name) {
		return GetterUtil.getInteger(getHeader(name));
	}

	protected void setHeaders(Map<String, List<String>> headersMap) {
		_headerMap = headersMap;
	}

	private Map<String, List<String>> _headerMap = new HashMap<>();

}