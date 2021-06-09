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

import com.liferay.osb.asah.test.util.annotation.SQLResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.junit.runner.RunWith;

/**
 * @author Leslie Wong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SQLResource(
	resourcePath = "blocked_custom_event_definitions_graphql_rest_controller_test.sql"
)
public class BlockedCustomEventDefinitionsGraphQLRestControllerTest
	extends BaseGraphQLRestControllerTestCase {

	@Override
	public String getBodyPath() {
		return "blocked_custom_event_definitions_body.json";
	}

	@Override
	public String getExpectedResultPath() {
		return "blocked_custom_event_definitions_expected_result.json";
	}

	@Override
	public String getQueryPath() {
		return "blocked_custom_event_definitions_query.graphql";
	}

}