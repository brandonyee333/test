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

package com.liferay.osb.asah.backend.ext.alfa.rest.controller.api.data.source.v1;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

/**
 * @author Shinn Lok
 */
@RequestMapping("/api/alfa/1.0/sample")
@RestController(
	"com.liferay.osb.asah.backend.ext.alfa.rest.controller.api.data.source.v1.SampleRestController"
)
public class SampleRestController {

	@GetMapping("/**")
	public String forward(HttpServletRequest httpServletRequest)
		throws Exception {

		HttpClientBuilder httpClientBuilder = HttpClients.custom();

		CredentialsProvider credentialsProvider =
			new BasicCredentialsProvider();

		credentialsProvider.setCredentials(
			AuthScope.ANY,
			new UsernamePasswordCredentials(_username, _password));

		httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);

		HttpClient httpClient = httpClientBuilder.build();

		ResourceUrlProvider resourceUrlProvider =
			(ResourceUrlProvider)httpServletRequest.getAttribute(
				ResourceUrlProvider.class.getCanonicalName());

		PathMatcher pathMatcher = resourceUrlProvider.getPathMatcher();

		String path = pathMatcher.extractPathWithinPattern(
			(String)httpServletRequest.getAttribute(
				HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE),
			(String)httpServletRequest.getAttribute(
				HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE));

		String queryString = httpServletRequest.getQueryString();

		if (queryString != null) {
			path += "?" + queryString;
		}

		HttpResponse httpResponse = httpClient.execute(
			new HttpGet("https://postman-echo.com/" + path));

		return EntityUtils.toString(httpResponse.getEntity());
	}

	@Value("${osb.asah.alfa.postman.password:}")
	private String _password;

	@Value("${osb.asah.alfa.postman.username:}")
	private String _username;

}