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

package com.liferay.osb.asah.backend.graphql;

import com.liferay.osb.asah.common.prometheus.PrometheusUtil;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;

import graphql.introspection.IntrospectionQuery;

import io.prometheus.client.Histogram;
import io.prometheus.client.SimpleTimer;

import java.nio.charset.Charset;

import java.time.LocalDate;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Marcellus Tavares
 */
@CrossOrigin
@RequestMapping("/graphql")
@RestController
public class GraphQLRestController {

	public static boolean skipCache(
		String query, Map<String, Object> variables) {

		if (!query.startsWith("{pagesCount") &&
			!query.startsWith("query CustomAssetsList") &&
			!query.startsWith("query IndividualMetrics")) {

			JSONObject variablesJSONObject = new JSONObject(variables);

			if (variablesJSONObject.has("rangeEnd")) {
				LocalDate currentLocalDate = LocalDate.now();
				LocalDate rangeEndLocalDate = LocalDate.parse(
					variablesJSONObject.getString("rangeEnd"));

				return currentLocalDate.isEqual(rangeEndLocalDate);
			}
			else if (variablesJSONObject.optInt("rangeKey") > 0) {
				return false;
			}
		}

		return true;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/schema")
	public String get() throws Exception {
		ExecutionResult executionResult = _graphQL.execute(
			IntrospectionQuery.INTROSPECTION_QUERY);

		return _graphQLSerializer.toString(executionResult);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "")
	public String post(HttpServletRequest httpServletRequest) throws Exception {
		String body = IOUtils.toString(
			httpServletRequest.getInputStream(), Charset.defaultCharset());

		if (_log.isDebugEnabled()) {
			_log.debug("Post body: " + body);
		}

		return _getGraphQLExecutionResult(_graphQLSerializer.fromString(body));
	}

	private String _getGraphQLExecutionResult(GraphQLRequest graphQLRequest)
		throws Exception {

		String query = graphQLRequest.getQuery();
		Map<String, Object> variables = graphQLRequest.getVariables();

		boolean skipCache = skipCache(query, variables);

		String cacheKey = query + "#" + variables;

		if ((_cacheManager != null) && !skipCache) {
			if (_cache == null) {
				_cache = _cacheManager.getCache("getGraphQLExecutionResult");
			}

			if (_cache != null) {
				Cache.ValueWrapper cacheValueWrapper = _cache.get(cacheKey);

				if (cacheValueWrapper != null) {
					return String.valueOf(cacheValueWrapper.get());
				}
			}
		}

		SimpleTimer simpleTimer = new SimpleTimer();

		String operationName = null;

		try {
			ExecutionInput.Builder builder = ExecutionInput.newExecutionInput();

			operationName = graphQLRequest.getOperationName();

			ExecutionInput executionInput = builder.context(
				new HashMap<String, Object>()
			).operationName(
				operationName
			).query(
				graphQLRequest.getQuery()
			).variables(
				graphQLRequest.getVariables()
			).build();

			ExecutionResult executionResult = _graphQL.execute(executionInput);

			String executionResultString = _graphQLSerializer.toString(
				executionResult);

			if ((_cache != null) && !skipCache) {
				_cache.put(cacheKey, executionResultString);
			}

			return executionResultString;
		}
		finally {
			if (operationName == null) {
				operationName = "unknown";
			}

			Histogram.Child child = _graphQLRequestsHistogram.labels(
				operationName);

			child.observe(simpleTimer.elapsedSeconds());
		}
	}

	private static final Log _log = LogFactory.getLog(
		GraphQLRestController.class);

	private static final Histogram _graphQLRequestsHistogram =
		PrometheusUtil.histogram(
			"backend_graphql_request_seconds",
			"The number of seconds taken to process the GraphQL requests",
			"operation");

	private Cache _cache;

	@Autowired(required = false)
	private CacheManager _cacheManager;

	@Autowired
	private GraphQL _graphQL;

	@Autowired
	private GraphQLSerializer _graphQLSerializer;

}