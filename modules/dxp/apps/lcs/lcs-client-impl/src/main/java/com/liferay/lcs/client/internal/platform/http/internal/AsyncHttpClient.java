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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.Closeable;
import java.io.IOException;

import java.util.concurrent.Future;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.protocol.HttpContext;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public class AsyncHttpClient implements Closeable {

	public AsyncHttpClient(
		CloseableHttpAsyncClient closeableHttpAsyncClient, int maxAttempts) {

		_closeableHttpAsyncClient = closeableHttpAsyncClient;

		if (maxAttempts < 1) {
			_maxAttempts = 1;
		}
		else {
			_maxAttempts = maxAttempts;
		}
	}

	@Override
	public void close() throws IOException {
		_closeableHttpAsyncClient.close();
	}

	public Future<HttpResponse> execute(
		HttpHost httpHost, HttpRequest httpRequest) {

		return execute(httpHost, httpRequest, HttpClientContext.create());
	}

	public Future<HttpResponse> execute(
		final HttpHost httpHost, HttpRequest httpRequest,
		HttpContext httpContext) {

		for (int i = 1; i <= _maxAttempts; i++) {
			if ((_maxAttempts == 1) || (_maxAttempts == i)) {
				return _closeableHttpAsyncClient.execute(
					httpHost, httpRequest, httpContext, null);
			}

			try {
				Future<HttpResponse> httpResponseFuture =
					_closeableHttpAsyncClient.execute(
						httpHost, httpRequest, httpContext, null);

				httpResponseFuture.get();

				return httpResponseFuture;
			}
			catch (Exception exception) {
				if (_log.isTraceEnabled()) {
					_log.trace(
						"Unable to execute HTTP request in attempt " + i,
						exception);
				}

				try {
					Thread.sleep(100L);
				}
				catch (InterruptedException interruptedException) {
					_log.error("Interrupted", interruptedException);

					if (_log.isInfoEnabled()) {
						_log.info(
							"Aborting executing after " + i + " attempts");
					}
				}
			}
		}

		throw new RuntimeException(
			"Unable to execute HTTP request after " + _maxAttempts +
				" attempts");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AsyncHttpClient.class);

	private final CloseableHttpAsyncClient _closeableHttpAsyncClient;
	private final int _maxAttempts;

}