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

import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;

/**
 * @author Leslie Wong
 */
@ElasticsearchIndex(
	name = "data-sources", resourcePath = "data_sources_4.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
public class DataSourceGraphQLRestControllerTest
	extends BaseGraphQLRestControllerTestCase {

	@Override
	public String getBodyPath() {
		return "data_source_body.json";
	}

	@Override
	public String getExpectedResultPath() {
		return "data_source_expected_result.json";
	}

	@Override
	public String getQueryPath() {
		return "data_source_query.graphql";
	}

}