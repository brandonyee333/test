/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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