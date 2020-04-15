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

package com.liferay.osb.customer.github.remote;

import com.liferay.osb.customer.github.configuration.GitHubConfigurationValues;
import com.liferay.osb.customer.github.service.GitHubWebService;
import com.liferay.petra.json.web.service.client.BaseJSONWebServiceClientImpl;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.nio.charset.Charset;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpMessage;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.nio.reactor.IOReactorException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = GitHubWebService.class)
public class GitHubWebServiceImpl
	extends BaseJSONWebServiceClientImpl implements GitHubWebService {

	@Override
	public JSONObject addCollaborator(String gitHubUserName)
		throws PortalException {

		String url = _URL_GITHUB_REPOSITORY_COLLABORATORS + gitHubUserName;

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("permission", "pull");

		try {
			String response = doPutAsJSON(url, jsonObject.toString(), _headers);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			String message = e.getMessage();

			if (message.contains("API rate limit exceeded")) {
				return null;
			}

			throw new PortalException(e);
		}
	}

	@Override
	public void afterPropertiesSet() throws IOReactorException {
		setMaxAttempts(3);

		super.afterPropertiesSet();
	}

	@Override
	public JSONObject deleteCollaborator(String gitHubUserName)
		throws PortalException {

		try {
			String url = _URL_GITHUB_REPOSITORY_COLLABORATORS + gitHubUserName;

			String response = doDelete(
				url, Collections.<String, String>emptyMap(), _headers);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public String doPutAsJSON(
			String url, String json, Map<String, String> headers)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

		HttpPut httpPut = new HttpPut(url);

		addHeaders(httpPut, headers);

		StringEntity stringEntity = new StringEntity(json, _CHARSET);

		stringEntity.setContentType("application/json");

		httpPut.setEntity(stringEntity);

		return execute(httpPut);
	}

	protected void addHeaders(
		HttpMessage httpMessage, Map<String, String> headers) {

		for (Map.Entry<String, String> entry : headers.entrySet()) {
			httpMessage.addHeader(entry.getKey(), entry.getValue());
		}
	}

	@Override
	protected String execute(HttpRequestBase httpRequestBase)
		throws JSONWebServiceInvocationException,
			   JSONWebServiceTransportException {

		setHostName(
			GitHubConfigurationValues.REMOTE_REST_SERVICE_API_GITHUB_HOST);

		setHostPort(
			GitHubConfigurationValues.REMOTE_REST_SERVICE_API_GITHUB_PORT);

		setProtocol(
			GitHubConfigurationValues.REMOTE_REST_SERVICE_API_GITHUB_PROTOCOL);

		return super.execute(httpRequestBase);
	}

	@Override
	protected void signRequest(HttpRequestBase httpRequestBase) {
	}

	private static final Charset _CHARSET = Charset.forName("UTF-8");

	private static final String _URL_GITHUB_REPOSITORY_COLLABORATORS =
		"/repos/" +
			GitHubConfigurationValues.
				REMOTE_REST_SERVICE_API_GITHUB_REPOSITORY_OWNER + "/" +
					GitHubConfigurationValues.
						REMOTE_REST_SERVICE_API_GITHUB_REPOSITORY_NAME +
							"/collaborators/";

	private static final Map<String, String> _headers =
		new HashMap<String, String>() {
			{
				put(
					"Authorization",
					"token " +
						GitHubConfigurationValues.
							REMOTE_REST_SERVICE_API_GITHUB_TOKEN);
			}
		};

}