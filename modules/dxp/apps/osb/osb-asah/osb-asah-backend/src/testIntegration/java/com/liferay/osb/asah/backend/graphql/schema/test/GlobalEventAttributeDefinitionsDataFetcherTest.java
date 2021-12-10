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

package com.liferay.osb.asah.backend.graphql.schema.test;

import com.liferay.osb.asah.backend.OSBAsahBackendSpringTestContext;
import com.liferay.osb.asah.backend.dto.EventAttributeDefinitionDTO;
import com.liferay.osb.asah.backend.graphql.schema.GlobalEventAttributeDefinitionsDataFetcher;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * @author Alejo Ceballos
 */
@Import(JDBCTestConfiguration.class)
public class GlobalEventAttributeDefinitionsDataFetcherTest
	implements OSBAsahBackendSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testGet() {
		String[] globalEventAttributeDefinitionNames = {
			"canonicalUrl", "pageTitle", "referrer", "url"
		};

		List<EventAttributeDefinitionDTO> eventAttributeDefinitionsDTOs =
			_globalEventAttributeDefinitionsDataFetcher.get(null);

		Stream<EventAttributeDefinitionDTO> stream =
			eventAttributeDefinitionsDTOs.stream();

		MatcherAssert.assertThat(
			stream.map(
				EventAttributeDefinitionDTO::getName
			).collect(
				Collectors.toList()
			),
			Matchers.containsInAnyOrder(globalEventAttributeDefinitionNames));
	}

	@Autowired
	private GlobalEventAttributeDefinitionsDataFetcher
		_globalEventAttributeDefinitionsDataFetcher;

}