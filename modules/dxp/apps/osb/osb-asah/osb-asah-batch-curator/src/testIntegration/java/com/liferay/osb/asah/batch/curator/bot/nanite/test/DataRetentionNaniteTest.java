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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.repository.BQIndividualRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Matthew Kong
 */
public class DataRetentionNaniteTest
	extends BaseNaniteTestCase implements OSBAsahTestExecutionListenersContext {

	@Disabled
	@ElasticsearchIndex(
		name = "blogs", resourcePath = "data_retention_blogs.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_INFO
	)
	@Test
	public void testDeleteAssets() throws Exception {
		_dataRetentionNanite.run(null);

		Assertions.assertArrayEquals(
			new String[] {"101", "102"},
			JSONUtil.toStringArray(
				_cerebroInfoElasticsearchInvoker.get("blogs"), "id"));
	}

	@Disabled
	@RepositoryResource(
		repositoryClass = BQIndividualRepository.class,
		resourcePath = "osbasahfaroinfo/data_retention_individuals.json"
	)
	@Test
	public void testDeleteIndividuals() throws Exception {
		_dataRetentionNanite.run(null);

		Assertions.assertArrayEquals(
			new String[] {"101", "104", "105"},
			JSONUtil.toStringArray(
				faroInfoElasticsearchInvoker.get("individuals"), "id"));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@Autowired
	private DataRetentionNanite _dataRetentionNanite;

}