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

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.util.ListUtil;

import graphql.ExecutionResult;
import graphql.GraphQLError;
import graphql.GraphqlErrorHelper;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class GraphQLSerializer {

	public GraphQLRequest fromString(String string) throws IOException {
		return _objectMapper.readValue(string, GraphQLRequest.class);
	}

	public String toString(ExecutionResult executionResult) throws IOException {
		Map<String, Object> map = new HashMap<>();

		map.put("data", executionResult.getData());

		List<GraphQLError> graphQLErrors = executionResult.getErrors();

		if ((graphQLErrors != null) && !graphQLErrors.isEmpty()) {
			map.put(
				"errors",
				ListUtil.map(
					graphQLErrors,
					graphQLError -> {
						Map<String, Object> specification =
							GraphqlErrorHelper.toSpecification(graphQLError);

						JSONObject jsonObject = _objectMapper.convertValue(
							graphQLError, JSONObject.class);

						if (jsonObject.has("exception")) {
							JSONObject exceptionJSONObject =
								jsonObject.getJSONObject("exception");

							if (exceptionJSONObject.has("messageKey")) {
								specification.put(
									"messageKey",
									exceptionJSONObject.getString(
										"messageKey"));
							}
						}

						return specification;
					}));
		}

		return _objectMapper.writeValueAsString(map);
	}

	@Autowired
	private ObjectMapper _objectMapper;

}