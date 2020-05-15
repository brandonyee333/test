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
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;

/**
 * @author André Miranda
 */
@ElasticsearchIndex(
	name = "blogs", resourcePath = "blog_info.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
)
public class BlogMetricsGraphQLRestControllerTest
	extends BaseGraphQLRestControllerTestCase {

	@Override
	public String getBodyPath() {
		return "blog-metrics-body.json";
	}

	@Override
	public String getExpectedResultPath() {
		return "blog-metrics-expected-result.json";
	}

	@Override
	public String getQueryPath() {
		return "blog-metrics-query.graphql";
	}

}