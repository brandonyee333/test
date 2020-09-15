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

package com.liferay.osb.customer.github.internal.web.service;

import com.liferay.osb.customer.github.configuration.GitHubConfigurationValues;
import com.liferay.osb.customer.github.constants.GitHubConstants;
import com.liferay.osb.customer.github.internal.web.service.search.SearchHitsImpl;
import com.liferay.osb.customer.github.servlet.GitHubHttpHeaders;
import com.liferay.osb.customer.github.web.service.GitHubWebService;
import com.liferay.osb.customer.github.web.service.search.Query;
import com.liferay.osb.customer.github.web.service.search.QueryFactory;
import com.liferay.osb.customer.github.web.service.search.SearchHits;
import com.liferay.petra.json.web.service.client.BaseJSONWebServiceClientImpl;
import com.liferay.petra.json.web.service.client.JSONWebServiceInvocationException;
import com.liferay.petra.json.web.service.client.JSONWebServiceTransportException;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.util.EntityUtils;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = GitHubWebService.class)
public class GitHubWebServiceImpl
	extends BaseJSONWebServiceClientImpl implements GitHubWebService {

	@Override
	public JSONObject addCollaborator(String gitHubUserName)
		throws PortalException {

		String url =
			_URL_GITHUB_REPOSITORY_COLLABORATORS + "/" + gitHubUserName;

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("permission", "pull");

		try {
			String response = _doPutAsJSON(
				url, jsonObject.toString(), _headers);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			String message = e.getMessage();

			if (message.contains("RepositoryInvitation") &&
				message.contains("rate_limit exceeded")) {

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
			String url =
				_URL_GITHUB_REPOSITORY_COLLABORATORS + "/" + gitHubUserName;

			String response = doDelete(
				url, Collections.<String, String>emptyMap(), _headers);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Override
	public Set<String> getCollaborators() throws PortalException {
		Query query = _queryFactory.createQuery();

		query.setPerPage(100);

		Set<String> collaborators = new HashSet<>();

		int page = 1;

		while (page > 0) {
			query.setPage(page);

			SearchHits<String> searchHits = _searchCollaborators(query);

			collaborators.addAll(searchHits.getResults());

			page = searchHits.getNextPage();
		}

		return collaborators;
	}

	@Override
	public JSONObject getUser(String gitHubUserName) throws PortalException {
		try {
			String url = _URL_GITHUB_USERS + gitHubUserName;

			String response = doGet(
				url, Collections.<String, String>emptyMap(), _headers);

			return JSONFactoryUtil.createJSONObject(response);
		}
		catch (Exception e) {
			throw new PortalException(e);
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

	private void _addHeaders(
		HttpMessage httpMessage, Map<String, String> headers) {

		for (Map.Entry<String, String> entry : headers.entrySet()) {
			httpMessage.addHeader(entry.getKey(), entry.getValue());
		}
	}

	private String _doPutAsJSON(
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

	private String _get(
			String url, Map<String, String> parameters,
			Map<String, String> headers)
		throws PortalException {

		try {
			if (!parameters.isEmpty()) {
				String queryString = URLEncodedUtils.format(
					toNameValuePairs(parameters), _CHARSET);

				url += "?" + queryString;
			}

			HttpGet httpGet = new HttpGet(Http.HTTPS_WITH_SLASH + url);

			addHeaders(httpGet, headers);

			HttpClient httpClient = new DefaultHttpClient();

			HttpResponse httpResponse = httpClient.execute(httpGet);

			_setResponseHeaders(httpResponse);

			return EntityUtils.toString(httpResponse.getEntity());
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	private JSONObject _getPageURLs() {
		String headerValue = _responseHeaders.get(GitHubHttpHeaders.LINK);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		if (Validator.isNotNull(headerValue)) {
			String[] linkValue = headerValue.split(StringPool.COMMA);

			for (String value : linkValue) {
				String[] links = value.split(StringPool.SEMICOLON);

				if (links.length < 2) {
					continue;
				}

				String link = links[0].trim();

				if (!link.startsWith(StringPool.LESS_THAN) ||
					!link.endsWith(StringPool.GREATER_THAN)) {

					continue;
				}

				link = link.substring(1, link.length() - 1);

				for (int i = 1; i < links.length; i++) {
					String linkPart = links[i].trim();

					String[] rel = linkPart.split(StringPool.EQUAL);

					if ((rel.length < 2) ||
						!GitHubConstants.REL.equals(rel[0])) {

						continue;
					}

					String relValue = rel[1];

					if ((relValue.startsWith(StringPool.BACK_SLASH) &&
						 relValue.endsWith(StringPool.BACK_SLASH)) ||
						(relValue.startsWith(StringPool.QUOTE) &&
						 relValue.endsWith(StringPool.QUOTE))) {

						relValue = relValue.substring(1, relValue.length() - 1);
					}

					if (relValue.equals(GitHubConstants.FIRST)) {
						jsonObject.put(GitHubConstants.FIRST, link);
					}
					else if (relValue.equals(GitHubConstants.LAST)) {
						jsonObject.put(GitHubConstants.LAST, link);
					}
					else if (relValue.equals(GitHubConstants.NEXT)) {
						jsonObject.put(GitHubConstants.NEXT, link);
					}
					else if (relValue.equals(GitHubConstants.PREV)) {
						jsonObject.put(GitHubConstants.PREV, link);
					}
				}
			}
		}
		else {
			jsonObject.put(
				GitHubConstants.LAST,
				_responseHeaders.get(GitHubHttpHeaders.X_LAST));
			jsonObject.put(
				GitHubConstants.NEXT,
				_responseHeaders.get(GitHubHttpHeaders.X_NEXT));
		}

		return jsonObject;
	}

	private SearchHits<String> _searchCollaborators(Query query)
		throws PortalException {

		try {
			String url =
				GitHubConfigurationValues.REMOTE_REST_SERVICE_API_GITHUB_HOST +
					_URL_GITHUB_REPOSITORY_COLLABORATORS;

			String response = _get(url, query.getParameters(), _headers);

			JSONArray responseJSONArray = JSONFactoryUtil.createJSONArray(
				response);

			return _toCollaboratorSearchHits(responseJSONArray);
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	private void _setResponseHeaders(HttpResponse httpResponse) {
		Map<String, String> responseHeaders = new HashMap<>();

		for (Header header : httpResponse.getAllHeaders()) {
			responseHeaders.put(header.getName(), header.getValue());
		}

		_responseHeaders = responseHeaders;
	}

	private String _toCollaborator(JSONObject jsonObject)
		throws PortalException {

		return jsonObject.getString("login");
	}

	private List<String> _toCollaborators(JSONArray jsonArray)
		throws PortalException {

		List<String> collaborators = new ArrayList<>();

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);

			collaborators.add(_toCollaborator(jsonObject));
		}

		return collaborators;
	}

	private SearchHits<String> _toCollaboratorSearchHits(JSONArray jsonArray)
		throws PortalException {

		SearchHits<String> searchHits = new SearchHitsImpl<>();

		JSONObject jsonObject = _getPageURLs();

		String nextPageURL = jsonObject.getString("next");

		if (Validator.isNotNull(nextPageURL)) {
			String nextPage = _http.getParameter(nextPageURL, "page", false);

			searchHits.setNextPage(GetterUtil.getInteger(nextPage));
		}

		searchHits.setResults(_toCollaborators(jsonArray));

		return searchHits;
	}

	private static final Charset _CHARSET = Charset.forName("UTF-8");

	private static final String _URL_GITHUB_REPOSITORY_COLLABORATORS =
		StringBundler.concat(
			"/repos/",
			GitHubConfigurationValues.
				REMOTE_REST_SERVICE_API_GITHUB_REPOSITORY_OWNER,
			"/",
			GitHubConfigurationValues.
				REMOTE_REST_SERVICE_API_GITHUB_REPOSITORY_NAME,
			"/collaborators");

	private static final String _URL_GITHUB_USERS = "/users/";

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

	@Reference
	private Http _http;

	@Reference
	private QueryFactory _queryFactory;

	private Map<String, String> _responseHeaders = Collections.emptyMap();

}