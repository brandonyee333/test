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

package com.liferay.lcs.client.internal.platform.http;

import com.fasterxml.jackson.databind.Module;

import java.security.KeyStore;

import java.util.List;

import org.apache.http.NameValuePair;

/**
 * @author Ivica Cardic
 * @author Igor Beslic
 */
public interface RESTClient {

	public void destroy();

	public String doDelete(String url, List<NameValuePair> parameters)
		throws RESTClientInvocationException, RESTClientTransportException;

	public String doDelete(
			String url, List<NameValuePair> parameters,
			List<NameValuePair> headers)
		throws RESTClientInvocationException, RESTClientTransportException;

	public String doDelete(String url, String... parametersArray)
		throws RESTClientInvocationException, RESTClientTransportException;

	public String doGet(
			String url, List<? extends NameValuePair> parameters,
			List<? extends NameValuePair> headers)
		throws RESTClientInvocationException, RESTClientTransportException;

	public String doGet(String url, List<NameValuePair> parameters)
		throws RESTClientInvocationException, RESTClientTransportException;

	public String doGet(String url, String... parametersArray)
		throws RESTClientInvocationException, RESTClientTransportException;

	public <V, T> List<V> doGetToList(
			Class<T> clazz, String url, List<NameValuePair> parameters,
			List<NameValuePair> headers)
		throws RESTClientInvocationException, RESTClientSerializeException,
			   RESTClientTransportException;

	public <V, T> List<V> doGetToList(
			Class<T> clazz, String url, String... parametersArray)
		throws RESTClientInvocationException, RESTClientSerializeException,
			   RESTClientTransportException;

	public <T> T doGetToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws RESTClientInvocationException, RESTClientSerializeException,
			   RESTClientTransportException;

	public String doPost(
			String url, List<? extends NameValuePair> parameters,
			List<? extends NameValuePair> headers)
		throws RESTClientInvocationException, RESTClientTransportException;

	public String doPost(String url, List<NameValuePair> parameters)
		throws RESTClientInvocationException, RESTClientTransportException;

	public String doPost(String url, String... parametersArray)
		throws RESTClientInvocationException, RESTClientTransportException;

	public String doPostAsJSON(String url, Object object)
		throws RESTClientInvocationException, RESTClientSerializeException,
			   RESTClientTransportException;

	public String doPostAsJSON(String url, String json)
		throws RESTClientInvocationException, RESTClientTransportException;

	public String doPostAsJSON(
			String url, String json, List<NameValuePair> headers)
		throws RESTClientInvocationException, RESTClientTransportException;

	public <T> T doPostToObject(
			Class<T> clazz, String url, List<NameValuePair> parameters,
			List<NameValuePair> headers)
		throws RESTClientInvocationException, RESTClientSerializeException,
			   RESTClientTransportException;

	public <T> T doPostToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws RESTClientInvocationException, RESTClientSerializeException,
			   RESTClientTransportException;

	public String doPut(String url, List<NameValuePair> parameters)
		throws RESTClientInvocationException, RESTClientTransportException;

	public String doPut(
			String url, List<NameValuePair> parameters,
			List<NameValuePair> headers)
		throws RESTClientInvocationException, RESTClientTransportException;

	public String doPut(String url, String... parametersArray)
		throws RESTClientInvocationException, RESTClientTransportException;

	public <T> T doPutToObject(
			Class<T> clazz, String url, List<NameValuePair> parameters)
		throws RESTClientInvocationException, RESTClientSerializeException,
			   RESTClientTransportException;

	public <T> T doPutToObject(
			Class<T> clazz, String url, List<NameValuePair> parameters,
			List<NameValuePair> headers)
		throws RESTClientInvocationException, RESTClientSerializeException,
			   RESTClientTransportException;

	public <T> T doPutToObject(
			Class<T> clazz, String url, String... parametersArray)
		throws RESTClientInvocationException, RESTClientSerializeException,
			   RESTClientTransportException;

	public String getHostName();

	public int getHostPort();

	public String getProtocol();

	public void registerModule(Module module);

	public void resetHttpClient();

	public void setHostName(String hostName);

	public void setHostPort(int hostPort);

	public void setKeyStore(KeyStore keyStore);

	public void setLogin(String login);

	public void setMaxAttempts(int maxAttempts);

	public void setOAuthAccessSecret(String oAuthAccessSecret);

	public void setOAuthAccessToken(String oAuthAccessToken);

	public void setOAuthConsumerKey(String oAuthConsumerKey);

	public void setOAuthConsumerSecret(String oAuthConsumerSecret);

	public void setPassword(String password);

	public void setProtocol(String protocol);

}