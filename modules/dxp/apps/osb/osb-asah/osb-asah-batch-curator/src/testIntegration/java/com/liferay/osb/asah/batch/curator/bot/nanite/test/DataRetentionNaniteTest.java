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

package com.liferay.osb.asah.batch.curator.bot.nanite.test;

import com.liferay.osb.asah.batch.curator.bot.nanite.DataRetentionNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Matthew Kong
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(
	classes = {
		CerebroQueueHttpTestConfiguration.class,
		OSBAsahBatchCuratorSpringBootApplication.class
	}
)
public class DataRetentionNaniteTest extends BaseNaniteTestCase {

	@Before
	public void setUp() throws Exception {
		super.setUp();

		_cerebroInfoElasticsearchInvoker =
			_elasticsearchInvokerFactory.forCerebroInfo();
	}

	@ElasticsearchIndex(
		name = "blogs", resourcePath = "data-retention-blogs.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testDeleteAssets() throws Exception {
		_dataRetentionNanite.run(null);

		Assert.assertArrayEquals(
			new String[] {"101", "102"},
			JSONUtil.toStringArray(
				_cerebroInfoElasticsearchInvoker.get("blogs"), "id"));
	}

	@ElasticsearchIndex(
		name = "individuals", resourcePath = "data-retention-individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteIndividuals() throws Exception {
		_dataRetentionNanite.run(null);

		Assert.assertArrayEquals(
			new String[] {"100", "101", "104", "105"},
			JSONUtil.toStringArray(
				faroInfoElasticsearchInvoker.get("individuals"), "id"));
	}

	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private DataRetentionNanite _dataRetentionNanite;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}