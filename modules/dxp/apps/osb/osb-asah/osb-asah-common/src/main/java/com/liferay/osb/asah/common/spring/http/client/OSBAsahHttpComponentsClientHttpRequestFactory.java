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

import java.net.URI;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

/**
 * @author Shinn Lok
 */
public class OSBAsahHttpComponentsClientHttpRequestFactory
	extends HttpComponentsClientHttpRequestFactory {

	@Override
	protected HttpUriRequest createHttpUriRequest(
		HttpMethod httpMethod, URI uri) {

		if (httpMethod == HttpMethod.GET) {
			return new HttpEntityEnclosingRequestBase() {
				{
					setURI(uri);
				}

				@Override
				public String getMethod() {
					return HttpMethod.GET.name();
				}

			};
		}

		return super.createHttpUriRequest(httpMethod, uri);
	}

}