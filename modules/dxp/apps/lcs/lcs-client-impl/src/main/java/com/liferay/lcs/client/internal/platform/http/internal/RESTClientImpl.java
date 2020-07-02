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

package com.liferay.lcs.client.internal.platform.http.internal;

import com.liferay.lcs.client.internal.platform.http.BaseRESTClientImpl;
import com.liferay.lcs.client.internal.platform.http.RESTClientTransportException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.security.KeyStore;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.nio.reactor.IOReactorException;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
@Component(factory = "RESTClient", service = {})
public class RESTClientImpl extends BaseRESTClientImpl {

	@Override
	public void afterPropertiesSet() throws IOReactorException {
		super.afterPropertiesSet();
	}

	@Activate
	protected void activate(Map<String, Object> properties)
		throws IOReactorException {

		_setHeaders(getString("headers", properties));

		setClassLoader((ClassLoader)properties.get("classLoader"));
		setHostName(getString("hostName", properties));
		setHostPort(GetterUtil.getInteger(getString("hostPort", properties)));
		setKeyStore((KeyStore)properties.get("keyStore"));
		setLogin(getString("login", properties));

		if (properties.containsKey("maxAttempts")) {
			setMaxAttempts(
				GetterUtil.getInteger(getString("maxAttempts", properties)));
		}

		setPassword(getString("password", properties));
		setProtocol(getString("protocol", properties));

		if (properties.containsKey("proxyAuthType")) {
			setProxyAuthType(getString("proxyAuthType", properties));
			setProxyDomain(getString("proxyDomain", properties));
			setProxyWorkstation(getString("proxyWorkstation", properties));
		}

		if (properties.containsKey("proxyHostName")) {
			setProxyHostName(getString("proxyHostName", properties));
			setProxyHostPort(
				GetterUtil.getInteger(getString("proxyHostPort", properties)));
			setProxyLogin(getString("proxyLogin", properties));
			setProxyPassword(getString("proxyPassword", properties));
		}

		afterPropertiesSet();
	}

	@Deactivate
	protected void deactivate() {
		super.destroy();
	}

	protected String getString(String key, Map<String, Object> properties) {
		if (!properties.containsKey(key)) {
			return null;
		}

		return String.valueOf(properties.get(key));
	}

	@Override
	protected void signRequest(HttpRequestBase httpRequestBase)
		throws RESTClientTransportException.SigningFailure {
	}

	private void _setHeaders(String headersString) {
		if (headersString == null) {
			return;
		}

		headersString = headersString.trim();

		if (headersString.length() < 3) {
			return;
		}

		Map<String, String> headers = new HashMap<>();

		for (String header : headersString.split(";")) {
			String[] headerParts = header.split("=");

			if (headerParts.length != 2) {
				if (_log.isDebugEnabled()) {
					_log.debug("Ignoring invalid header " + header);
				}

				continue;
			}

			headers.put(headerParts[0], headerParts[1]);
		}

		setHeaders(headers);
	}

	private static final Log _log = LogFactoryUtil.getLog(RESTClientImpl.class);

}