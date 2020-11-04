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

import graphql.ExecutionResult;
import graphql.GraphQL;

import graphql.introspection.IntrospectionQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author André Miranda
 */
@CrossOrigin
@Profile("!prod")
@RequestMapping("/graphql")
@RestController
public class GraphQLSchemaRestController {

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/schema")
	public String get() throws Exception {
		ExecutionResult executionResult = _graphQL.execute(
			IntrospectionQuery.INTROSPECTION_QUERY);

		return _graphQLSerializer.toString(executionResult);
	}

	@Autowired
	private GraphQL _graphQL;

	@Autowired
	private GraphQLSerializer _graphQLSerializer;

}