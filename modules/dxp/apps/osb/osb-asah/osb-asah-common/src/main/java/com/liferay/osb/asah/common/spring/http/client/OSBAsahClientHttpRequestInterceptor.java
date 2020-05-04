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

package com.liferay.osb.asah.common.spring.http.client;

import java.io.IOException;

import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author Shinn Lok
 */
public class OSBAsahClientHttpRequestInterceptor
	implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(
			HttpRequest httpRequest, byte[] body,
			ClientHttpRequestExecution clientHttpRequestExecution)
		throws IOException {

		HttpHeaders httpHeaders = httpRequest.getHeaders();

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		httpHeaders.putIfAbsent(
			HttpHeaders.USER_AGENT,
			Collections.singletonList("LiferayAnalyticsCloud"));

		return clientHttpRequestExecution.execute(httpRequest, body);
	}

}