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

import com.liferay.osb.asah.test.util.annotation.BQSQLResource;
import com.liferay.osb.asah.test.util.configuration.JDBCTestConfiguration;

import org.springframework.context.annotation.Import;

/**
 * @author Alejo Ceballos
 * @author Marcos Martins
 */
@BQSQLResource(
	resourcePath = "test_events_by_user_session_graphql_rest_controller_test.sql"
)
@Import(JDBCTestConfiguration.class)
public class EventsByUserSessionGraphQLRestControllerTest
	extends BaseGraphQLRestControllerTestCase {

	@Override
	public String getBodyPath() {
		return "events_by_session_body.json";
	}

	@Override
	public String getExpectedResultPath() {
		return "events_by_sessions_expected_result.json";
	}

	@Override
	public String getQueryPath() {
		return "events_by_session_query.graphql";
	}

}