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

/**
 * @author Alejo Ceballos
 */
@SQLResource(
	resourcePath = "event_attribute_value_bag_graphql_rest_controller_test.sql"
)
public class EventAttributeValueBagGraphQLRestControllerTest
	extends BaseGraphQLRestControllerTestCase {

	@Override
	public String getBodyPath() {
		return "event_attribute_value_bag_body.json";
	}

	@Override
	public String getExpectedResultPath() {
		return "event_attribute_value_bag_expected_result.json";
	}

	@Override
	public String getQueryPath() {
		return "event_attribute_value_bag_query.graphql";
	}

}