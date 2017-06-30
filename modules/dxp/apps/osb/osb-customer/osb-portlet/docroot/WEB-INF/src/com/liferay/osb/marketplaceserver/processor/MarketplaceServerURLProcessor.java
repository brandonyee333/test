/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.marketplaceserver.processor;

import com.liferay.compat.portal.kernel.util.HttpUtil;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Douglas Wong
 */
public class MarketplaceServerURLProcessor {

	public String process() {
		String url = _url;

		if (url.contains(StringPool.AMPERSAND_ENCODED)) {
			url = url.replaceAll(
				StringPool.AMPERSAND_ENCODED, StringPool.AMPERSAND);
		}

		if (isAlreadyProcessed(url)) {
			return url;
		}

		return getClientURLPath() + getClientQueryString(url);
	}

	public void setClientAuthToken(String clientAuthToken) {
		_clientAuthToken = clientAuthToken;
	}

	public void setClientPortletId(String clientPortletId) {
		_clientPortletId = clientPortletId;

		_clientPortletNamespace = PortalUtil.getPortletNamespace(
			_clientPortletId);
	}

	public void setClientURL(String clientURL) {
		String clientQueryString = HttpUtil.getQueryString(clientURL);

		Map<String, String[]> clientParameterMap = HttpUtil.getParameterMap(
			clientQueryString);

		Set<Map.Entry<String, String[]>> set = clientParameterMap.entrySet();

		Iterator<Map.Entry<String, String[]>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, String[]> entry = iterator.next();

			String key = entry.getKey();

			if (key.startsWith(_clientPortletNamespace)) {
				iterator.remove();
			}
		}

		_clientParameterMap = Collections.unmodifiableMap(clientParameterMap);

		_clientURL = clientURL;
	}

	public void setRedirect(boolean redirect) {
		_redirect = redirect;
	}

	public void setServerPortletNamespace(String serverPortletNamespace) {
		_serverPortletNamespace = serverPortletNamespace;
	}

	public void setURL(String url) {
		_url = url;
	}

	protected String getClientQueryString(String url) {
		Map<String, String[]> clientParameterMap =
			new LinkedHashMap<String, String[]>();

		MapUtil.copy(_clientParameterMap, clientParameterMap);

		String queryString = HttpUtil.getQueryString(url);

		Map<String, String[]> parameterMap = HttpUtil.getParameterMap(
			queryString);

		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			putClientParameterMapEntry(
				clientParameterMap, entry.getKey(), entry.getValue());
		}

		if (_redirect) {
			putClientParameterMapEntry(
				clientParameterMap, "p_p_id", new String[]{_clientPortletId});
		}

		clientParameterMap.put(
			"p_p_state",
			new String[] {LiferayWindowState.EXCLUSIVE.toString()});

		return HttpUtil.parameterMapToString(clientParameterMap);
	}

	protected String getClientURLPath() {
		int x = _clientURL.indexOf(StringPool.QUESTION);

		return _clientURL.substring(0, x);
	}

	protected boolean isAlreadyProcessed(String url) {
		if (url.startsWith(getClientURLPath())) {
			return true;
		}
		else {
			return false;
		}
	}

	protected void putClientParameterMapEntry(
		Map<String, String[]> clientParameterMap, String name, String[] value) {

		if (name.equals("cur") || name.matches("tabs[0-9]+")) {
			name = _clientPortletNamespace.concat(name);
		}
		else if (name.equals(_serverPortletNamespace.concat("mvcPath"))) {
			name = _clientPortletNamespace.concat("remoteMVCPath");
		}
		else if (name.startsWith(_serverPortletNamespace)) {
			name = name.replace(
				_serverPortletNamespace, _clientPortletNamespace);
		}
		else if (name.equals("p_auth")) {
			value = new String[] {_clientAuthToken};
		}
		else if (name.equals("p_p_state")) {
			name = _clientPortletNamespace.concat("remoteWindowState");
		}
		else if (!name.startsWith(_clientPortletNamespace) &&
				 !name.equals("p_p_id") &&
				 !name.equals("p_p_lifecycle") &&
				 !name.equals("p_p_resource_id")) {

			return;
		}

		clientParameterMap.put(name, value);
	}

	private String _clientAuthToken;
	private Map<String, String[]> _clientParameterMap;
	private String _clientPortletId;
	private String _clientPortletNamespace;
	private String _clientURL;
	private boolean _redirect = false;
	private String _serverPortletNamespace;
	private String _url;

}