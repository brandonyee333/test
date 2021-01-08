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

import com.liferay.osb.asah.batch.curator.bot.nanite.DataControlNanite;
import com.liferay.osb.asah.batch.curator.spring.OSBAsahBatchCuratorSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataControlTaskStatus;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.queue.http.CerebroQueueHttpTestConfiguration;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author Matthew Kong
 */
@ContextConfiguration(classes = OSBAsahBatchCuratorSpringBootApplication.class)
@Import(CerebroQueueHttpTestConfiguration.class)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
public class DataControlNaniteTest extends BaseNaniteTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_dxpRawElasticsearchInvoker = _elasticsearchInvokerFactory.forDXPRaw();
		_exportPath = Files.createTempDirectory("export");
		_salesforceRawElasticsearchInvoker =
			_elasticsearchInvokerFactory.forSalesforceRaw();
	}

	@After
	public void tearDown() {
		File folder = _exportPath.toFile();

		File[] files = folder.listFiles();

		if (files != null) {
			for (File file : files) {
				if (!file.delete()) {
					_log.error(
						"Unable to delete file " + file.getAbsolutePath());
				}
			}
		}

		if (!folder.delete()) {
			_log.error("Unable to delete folder " + folder.getAbsolutePath());
		}
	}

	@ElasticsearchIndex(
		name = "data-control-tasks", resourcePath = "data-control-tasks.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_SALESFORCE_RAW
	)
	@ElasticsearchIndex(
		name = "users", resourcePath = "users.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_DXP_RAW
	)
	@Test
	public void test() throws Exception {
		_dataControlNanite.run(JSONUtil.put("path", _exportPath.toString()));

		JSONArray dataControlTasksJSONArray = faroInfoElasticsearchInvoker.get(
			"data-control-tasks", QueryBuilders.matchAllQuery());

		for (int i = 0; i < dataControlTasksJSONArray.length(); i++) {
			JSONObject dataControlTask =
				dataControlTasksJSONArray.getJSONObject(i);

			Assert.assertNotNull(dataControlTask.getString("completeDate"));
			Assert.assertNotNull(dataControlTask.getString("startDate"));
			Assert.assertEquals(
				DataControlTaskStatus.COMPLETED.toString(),
				dataControlTask.getString("status"));
		}

		JSONArray suppressionsJSONArray = faroInfoElasticsearchInvoker.get(
			"suppressions", QueryBuilders.matchAllQuery());

		Assert.assertEquals(1, suppressionsJSONArray.length());

		JSONObject suppressionJSONObject = suppressionsJSONArray.getJSONObject(
			0);

		Assert.assertEquals(
			"jane.doe@liferay.com",
			suppressionJSONObject.getString("emailAddress"));

		Assert.assertNull(
			_dxpRawElasticsearchInvoker.fetch(
				"users",
				QueryBuilders.termQuery(
					"emailAddress", "john.doe@liferay.com")));
		Assert.assertNull(
			faroInfoElasticsearchInvoker.fetch(
				"individuals",
				QueryBuilders.termQuery(
					"demographics.email.value", "john.doe@liferay.com")));
		Assert.assertNull(
			_salesforceRawElasticsearchInvoker.fetch(
				"individuals",
				QueryBuilders.termQuery("email", "john.doe@liferay.com")));

		Path path = Paths.get(_exportPath.toString(), "1.zip");

		File file = path.toFile();

		Assert.assertTrue(file.length() > 2000);
	}

	private static final Log _log = LogFactory.getLog(
		DataControlNaniteTest.class);

	@Autowired
	private DataControlNanite _dataControlNanite;

	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private Path _exportPath;
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}