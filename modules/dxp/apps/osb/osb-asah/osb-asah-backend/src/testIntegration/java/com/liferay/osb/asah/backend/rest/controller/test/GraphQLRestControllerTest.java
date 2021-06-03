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

package com.liferay.osb.asah.backend.rest.controller.test;

import com.liferay.osb.asah.backend.graphql.GraphQLRestController;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import graphql.ExecutionInput;
import graphql.GraphQL;

import java.time.LocalDate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Leslie Wong
 * @author Shinn Lok
 */
@ContextConfiguration(classes = OSBAsahBackendSpringBootApplication.class)
@EnableCaching
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class GraphQLRestControllerTest {

	@Test
	public void testSkipCacheWithCustomRange1() throws Exception {
		String query = ResourceUtil.readResourceToString(
			"dependencies/cacheable_query.graphql", this);

		LocalDate rangeEndLocalDate = LocalDate.now();

		LocalDate rangeStartLocalDate = rangeEndLocalDate.minusDays(3);

		Map<String, Object> variables = new HashMap<String, Object>() {
			{
				put("interval", "D");
				put("rangeEnd", rangeEndLocalDate.toString());
				put("rangeStart", rangeStartLocalDate.toString());
			}
		};

		_verify(true, "SiteMetrics", query, variables);
	}

	@Test
	public void testSkipCacheWithCustomRange2() throws Exception {
		String query = ResourceUtil.readResourceToString(
			"dependencies/cacheable_query.graphql", this);

		LocalDate localDate = LocalDate.now();

		LocalDate rangeEndLocalDate = localDate.minusDays(7);

		LocalDate rangeStartLocalDate = rangeEndLocalDate.minusDays(3);

		Map<String, Object> variables = new HashMap<String, Object>() {
			{
				put("interval", "D");
				put("rangeEnd", rangeEndLocalDate.toString());
				put("rangeStart", rangeStartLocalDate.toString());
			}
		};

		_verify(false, "SiteMetrics", query, variables);
	}

	@Test
	public void testSkipCacheWithNoncacheableQuery() throws Exception {
		String query = ResourceUtil.readResourceToString(
			"dependencies/noncacheable_query.graphql", this);

		_verify(true, "IndividualMetrics", query, Collections.emptyMap());
	}

	@Test
	public void testSkipCacheWithRangeKey() throws Exception {
		String query = ResourceUtil.readResourceToString(
			"dependencies/cacheable_query.graphql", this);

		_verify(true, "SiteMetrics", query, _getVariables(0));
		_verify(false, "SiteMetrics", query, _getVariables(1));
		_verify(false, "SiteMetrics", query, _getVariables(7));
		_verify(false, "SiteMetrics", query, _getVariables(28));
		_verify(false, "SiteMetrics", query, _getVariables(30));
		_verify(false, "SiteMetrics", query, _getVariables(90));
		_verify(false, "SiteMetrics", query, _getVariables(180));
		_verify(false, "SiteMetrics", query, _getVariables(365));
	}

	private Map<String, Object> _getVariables(int rangeKey) {
		Map<String, Object> variables = new HashMap<>();

		variables.put("interval", "D");
		variables.put("rangeKey", rangeKey);

		return variables;
	}

	private void _verify(
			boolean expectedSkipCache, String operationName, String query,
			Map<String, Object> variables)
		throws Exception {

		for (int i = 0; i < 3; i++) {
			_graphQLRestController.getGraphQLExecutionResult(
				operationName, query, variables);
		}

		if (expectedSkipCache) {
			Mockito.verify(
				_qraphQL, Mockito.times(3)
			).execute(
				ArgumentMatchers.any(ExecutionInput.class)
			);
		}
		else {
			Mockito.verify(
				_qraphQL, Mockito.times(1)
			).execute(
				ArgumentMatchers.any(ExecutionInput.class)
			);
		}

		Mockito.reset(_qraphQL);
	}

	@Autowired
	private GraphQLRestController _graphQLRestController;

	@SpyBean
	private GraphQL _qraphQL;

}