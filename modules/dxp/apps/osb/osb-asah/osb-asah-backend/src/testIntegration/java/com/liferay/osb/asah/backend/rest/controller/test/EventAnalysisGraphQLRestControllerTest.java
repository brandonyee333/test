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

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

/**
 * @author Leslie Wong
 */
@DirtiesContext
@SQLResource(resourcePath = "event_analysis_graphql_rest_controller_test.sql")
@TestPropertySource(properties = "osb.asah.postgresql.enabled=true")
public class EventAnalysisGraphQLRestControllerTest
	extends BaseGraphQLRestControllerTestCase {

	@Override
	public String getBodyPath() {
		return "event_analysis_body.json";
	}

	@Override
	public String getExpectedResultPath() {
		return "expected_event_analysis.json";
	}

	@Override
	public String getQueryPath() {
		return "event_analysis_query.graphql";
	}

}