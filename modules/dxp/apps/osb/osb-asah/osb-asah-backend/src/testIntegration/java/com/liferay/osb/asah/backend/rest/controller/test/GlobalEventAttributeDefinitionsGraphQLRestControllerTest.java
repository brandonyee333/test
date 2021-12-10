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

import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;

import org.springframework.context.annotation.Import;

/**
 * @author Alejo Ceballos
 */
@Import(JDBCTestConfiguration.class)
public class GlobalEventAttributeDefinitionsGraphQLRestControllerTest
	extends BaseGraphQLRestControllerTestCase {

	@Override
	public String getBodyPath() {
		return "global_event_attribute_definition_body.json";
	}

	@Override
	public String getExpectedResultPath() {
		return "global_event_attribute_definition_expected_result.json";
	}

	@Override
	public String getQueryPath() {
		return "global_event_attribute_definitions_query.graphql";
	}

}