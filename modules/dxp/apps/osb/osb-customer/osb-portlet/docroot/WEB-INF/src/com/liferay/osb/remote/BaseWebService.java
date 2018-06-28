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

package com.liferay.osb.remote;

import com.liferay.osb.exception.RemoteServiceException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.MapUtil;

import java.io.IOException;

import java.nio.charset.StandardCharsets;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @author Amos Fong
 */
public class BaseWebService {

	public void afterPropertiesSet() {
		HttpClientBuilder httpClientBuilder = HttpClients.custom();

		HttpClientConnectionManager httpClientConnectionManager =
			getPoolingHttpClientConnectionManager();

		httpClientBuilder.setConnectionManager(httpClientConnectionManager);

		try {
			_closeableHttpClient = httpClientBuilder.build();

			_idleConnectionMonitorThread = new IdleConnectionMonitorThread(
				httpClientConnectionManager);

			_idleConnectionMonitorThread.start();

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Configured client for " + _protocol + "://" + _hostName);
			}
		}
		catch (Exception e) {
			_log.error("Unable to configure client", e);
		}
	}

	public void destroy() {
		try {
			_closeableHttpClient.close();
		}
		catch (IOException ioe) {
			_log.error("Unable to close client", ioe);
		}

		_closeableHttpClient = null;

		_idleConnectionMonitorThread.shutdown();
	}

	public String doDelete(String url) throws RemoteServiceException {
		return doDelete(
			url, Collections.<String, String>emptyMap(),
			new HashMap<String, String>());
	}

	public String doDelete(String url, Map<String, String> parameters)
		throws RemoteServiceException {

		return doDelete(url, parameters, new HashMap<String, String>());
	}

	public String doDelete(
			String url, Map<String, String> parameters,
			Map<String, String> headers)
		throws RemoteServiceException {

		HttpHost httpHost = new HttpHost(_hostName, _hostPort, _protocol);

		List<NameValuePair> nameValuePairs = toNameValuePairs(parameters);

		if (!nameValuePairs.isEmpty()) {
			String queryString = URLEncodedUtils.format(
				nameValuePairs, StandardCharsets.UTF_8);

			url += "?" + queryString;
		}

		HttpDelete httpDelete = new HttpDelete(url);

		addHeaders(httpDelete, headers);

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Sending DELETE request to " + httpHost.toString() + url);
			_log.debug("HTTP parameters: " + MapUtil.toString(parameters));
		}

		return execute(httpHost, httpDelete);
	}

	public String doGet(String url) throws RemoteServiceException {
		return doGet(
			url, Collections.<String, String>emptyMap(),
			new HashMap<String, String>());
	}

	public String doGet(String url, Map<String, String> parameters)
		throws RemoteServiceException {

		return doGet(url, parameters, new HashMap<String, String>());
	}

	public String doGet(
			String url, Map<String, String> parameters,
			Map<String, String> headers)
		throws RemoteServiceException {

		HttpHost httpHost = new HttpHost(_hostName, _hostPort, _protocol);

		List<NameValuePair> nameValuePairs = toNameValuePairs(parameters);

		if (!nameValuePairs.isEmpty()) {
			String queryString = URLEncodedUtils.format(
				nameValuePairs, StandardCharsets.UTF_8);

			url += "?" + queryString;
		}

		HttpGet httpGet = new HttpGet(url);

		addHeaders(httpGet, headers);

		if (_log.isDebugEnabled()) {
			_log.debug("Sending GET request to " + httpHost.toString() + url);
			_log.debug("HTTP parameters: " + MapUtil.toString(parameters));
		}

		return execute(httpHost, httpGet);
	}

	public String doPost(String url, Map<String, String> parameters)
		throws RemoteServiceException {

		return doPost(url, parameters, new HashMap<String, String>());
	}

	public String doPost(
			String url, Map<String, String> parameters,
			Map<String, String> headers)
		throws RemoteServiceException {

		HttpHost httpHost = new HttpHost(_hostName, _hostPort, _protocol);

		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> nameValuePairs = toNameValuePairs(parameters);

		HttpEntity httpEntity = new UrlEncodedFormEntity(
			nameValuePairs, StandardCharsets.UTF_8);

		addHeaders(httpPost, headers);

		httpPost.setEntity(httpEntity);

		if (_log.isDebugEnabled()) {
			_log.debug("Sending POST request to " + httpHost.toString() + url);
			_log.debug("HTTP parameters: " + MapUtil.toString(parameters));
		}

		return execute(httpHost, httpPost);
	}

	public String doPut(String url, Map<String, String> parameters)
		throws RemoteServiceException {

		return doPut(url, parameters, new HashMap<String, String>());
	}

	public String doPut(
			String url, Map<String, String> parameters,
			Map<String, String> headers)
		throws RemoteServiceException {

		HttpHost httpHost = new HttpHost(_hostName, _hostPort, _protocol);

		HttpPut httpPut = new HttpPut(url);

		List<NameValuePair> nameValuePairs = toNameValuePairs(parameters);

		HttpEntity httpEntity = new UrlEncodedFormEntity(
			nameValuePairs, StandardCharsets.UTF_8);

		addHeaders(httpPut, headers);

		httpPut.setEntity(httpEntity);

		if (_log.isDebugEnabled()) {
			_log.debug("Sending PUT request to " + httpHost.toString() + url);
			_log.debug("HTTP parameters: " + MapUtil.toString(parameters));
		}

		return execute(httpHost, httpPut);
	}

	public void setHostName(String hostName) {
		_hostName = hostName;
	}

	public void setHostPort(int hostPort) {
		_hostPort = hostPort;
	}

	public void setProtocol(String protocol) {
		_protocol = protocol;
	}

	protected void addHeaders(
		HttpMessage httpMessage, Map<String, String> headers) {

		for (Map.Entry<String, String> entry : headers.entrySet()) {
			httpMessage.addHeader(entry.getKey(), entry.getValue());
		}
	}

	protected String execute(HttpHost httpHost, HttpRequestBase httpRequestBase)
		throws RemoteServiceException {

		try {
			if (_closeableHttpClient == null) {
				afterPropertiesSet();
			}

			HttpResponse httpResponse = _closeableHttpClient.execute(
				httpHost, httpRequestBase);

			StatusLine statusLine = httpResponse.getStatusLine();

			if (statusLine.getStatusCode() ==
					HttpServletResponse.SC_UNAUTHORIZED) {

				throw new RemoteServiceException(
					statusLine.getStatusCode(),
					"Not authorized to access JSON web service");
			}
			else if (statusLine.getStatusCode() >= 400) {
				String message = null;

				if (httpResponse.getEntity() != null) {
					HttpEntity httpEntity = httpResponse.getEntity();

					message = EntityUtils.toString(
						httpEntity, StandardCharsets.UTF_8);
				}

				throw new RemoteServiceException(
					statusLine.getStatusCode(), message);
			}

			return EntityUtils.toString(
				httpResponse.getEntity(), StandardCharsets.UTF_8);
		}
		catch (IOException ioe) {
			throw new RemoteServiceException("Unable to transmit request", ioe);
		}
		finally {
			httpRequestBase.releaseConnection();
		}
	}

	protected PoolingHttpClientConnectionManager
		getPoolingHttpClientConnectionManager() {

		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager =
			new PoolingHttpClientConnectionManager(
				60000, TimeUnit.MILLISECONDS);

		poolingHttpClientConnectionManager.setMaxTotal(20);

		return poolingHttpClientConnectionManager;
	}

	protected List<NameValuePair> toNameValuePairs(
		Map<String, String> parameters) {

		List<NameValuePair> nameValuePairs = new LinkedList<>();

		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			String key = entry.getKey();

			String value = entry.getValue();

			if (value == null) {
				key = "-" + key;

				value = "";
			}

			NameValuePair nameValuePair = new BasicNameValuePair(key, value);

			nameValuePairs.add(nameValuePair);
		}

		return nameValuePairs;
	}

	private static final Log _log = LogFactoryUtil.getLog(BaseWebService.class);

	private CloseableHttpClient _closeableHttpClient;
	private String _hostName;
	private int _hostPort;
	private IdleConnectionMonitorThread _idleConnectionMonitorThread;
	private String _protocol;

	private class IdleConnectionMonitorThread extends Thread {

		public IdleConnectionMonitorThread(
			HttpClientConnectionManager httpClientConnectionManager) {

			_httpClientConnectionManager = httpClientConnectionManager;
		}

		@Override
		public void run() {
			try {
				while (!_shutdown) {
					synchronized (this) {
						wait(5000);

						_httpClientConnectionManager.closeExpiredConnections();

						_httpClientConnectionManager.closeIdleConnections(
							30, TimeUnit.SECONDS);
					}
				}
			}
			catch (InterruptedException ie) {
			}
		}

		public void shutdown() {
			_shutdown = true;

			synchronized (this) {
				notifyAll();
			}
		}

		private final HttpClientConnectionManager _httpClientConnectionManager;
		private volatile boolean _shutdown;

	}

}