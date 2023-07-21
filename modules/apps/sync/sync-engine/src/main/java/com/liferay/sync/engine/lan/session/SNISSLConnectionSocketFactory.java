/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.lan.session;

import com.liferay.sync.engine.lan.util.LanClientUtil;

import java.io.IOException;

import java.net.Socket;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.RequestLine;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.protocol.HttpContext;

/**
 * @author Dennis Ju
 */
public class SNISSLConnectionSocketFactory extends SSLConnectionSocketFactory {

	public SNISSLConnectionSocketFactory(
		SSLContext sslContext, HostnameVerifier hostnameVerifier) {

		super(sslContext, hostnameVerifier);
	}

	@Override
	public Socket createLayeredSocket(
			Socket socket, String target, int port, HttpContext httpContext)
		throws IOException {

		HttpClientContext httpClientContext = (HttpClientContext)httpContext;

		HttpRequest httpRequest = httpClientContext.getRequest();

		RequestLine requestLine = httpRequest.getRequestLine();

		String[] parts = StringUtils.split(requestLine.getUri(), "/");

		String sniCompliantLanServerUuid = LanClientUtil.getSNIHostname(
			parts[0]);

		return super.createLayeredSocket(
			socket, sniCompliantLanServerUuid, port, httpContext);
	}

}