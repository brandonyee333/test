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

package com.liferay.osb.asah.stream.curator.bot.nanite.base.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.stream.curator.spring.OSBAsahCuratorSpringBootApplication;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author André Miranda
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahCuratorSpringBootApplication.class)
public class BaseNaniteTest {

	@ElasticsearchIndex(
		configurationPath = "default_base_index_configuration.json",
		name = "default-base", resourcePath = "default_base_info_old.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "analytics-events",
		resourcePath = "default_base_raw_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_RAW
	)
	@ElasticsearchIndex(
		name = "individual-segments",
		resourcePath = "default_base_individual_segments.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "default_base_individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "OSBAsahMarkers",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testIndividualSegments() throws Exception {
		_defaultBaseNanite.run();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"default_base_info_new_segments.json", this),
			elasticsearchInvoker.get("default-base"), false);
	}

	@ElasticsearchIndex(
		configurationPath = "default_base_index_configuration.json",
		name = "default-base", resourcePath = "default_base_info_old.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@ElasticsearchIndex(
		name = "analytics-events", resourcePath = "default_base_raw_urls.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_RAW
	)
	@Test
	public void testMergeURLs() throws Exception {
		_defaultBaseNanite.run();

		ElasticsearchInvoker elasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"default_base_info_new_urls.json", this),
			elasticsearchInvoker.get("default-base"), false);
	}

	@Autowired
	private DefaultBaseNanite _defaultBaseNanite;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}