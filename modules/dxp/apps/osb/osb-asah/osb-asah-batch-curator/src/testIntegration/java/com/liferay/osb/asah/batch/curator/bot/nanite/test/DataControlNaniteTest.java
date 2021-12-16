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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.DXPEntity;
import com.liferay.osb.asah.common.model.DataControlTaskStatus;
import com.liferay.osb.asah.common.repository.DXPEntityRepository;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;

import java.io.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * @author Matthew Kong
 */
public class DataControlNaniteTest
	extends BaseNaniteTestCase implements OSBAsahTestExecutionListenersContext {

	@BeforeEach
	public void setUp() throws Exception {
		_exportPath = Files.createTempDirectory("export");

		ReflectionTestUtils.setField(
			_dataControlNanite, "_exportPathName", _exportPath.toString());
	}

	@AfterEach
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
		name = "data-control-tasks", resourcePath = "data_control_tasks.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "individuals", resourcePath = "individuals_1.json",
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
		_dataControlNanite.run(null);

		JSONArray dataControlTasksJSONArray = faroInfoElasticsearchInvoker.get(
			"data-control-tasks", QueryBuilders.matchAllQuery());

		for (int i = 0; i < dataControlTasksJSONArray.length(); i++) {
			JSONObject dataControlTask =
				dataControlTasksJSONArray.getJSONObject(i);

			Assertions.assertNotNull(dataControlTask.getString("completeDate"));
			Assertions.assertNotNull(dataControlTask.getString("startDate"));
			Assertions.assertEquals(
				DataControlTaskStatus.COMPLETED.toString(),
				dataControlTask.getString("status"));
		}

		JSONArray suppressionsJSONArray = faroInfoElasticsearchInvoker.get(
			"suppressions", QueryBuilders.matchAllQuery());

		Assertions.assertEquals(1, suppressionsJSONArray.length());

		JSONObject suppressionJSONObject = suppressionsJSONArray.getJSONObject(
			0);

		Assertions.assertEquals(
			"jane.doe@liferay.com",
			suppressionJSONObject.getString("emailAddress"));

		List<DXPEntity> dxpEntities = _dxpEntityRepository.findByFieldsAndType(
			Collections.singletonMap(
				"fields.emailAddress", "john.doe@liferay.com"),
			DXPEntity.Type.USER);

		Assertions.assertTrue(dxpEntities.isEmpty());

		Assertions.assertNull(
			faroInfoElasticsearchInvoker.fetch(
				"individuals",
				QueryBuilders.termQuery(
					"demographics.email.value", "john.doe@liferay.com")));
		Assertions.assertNull(
			_salesforceRawElasticsearchInvoker.fetch(
				"individuals",
				QueryBuilders.termQuery("email", "john.doe@liferay.com")));

		Path path = Paths.get(_exportPath.toString(), "1.zip");

		File file = path.toFile();

		Assertions.assertTrue(file.length() > 2000);
	}

	private static final Log _log = LogFactory.getLog(
		DataControlNaniteTest.class);

	@Autowired
	private DataControlNanite _dataControlNanite;

	@Autowired
	private DXPEntityRepository _dxpEntityRepository;

	private Path _exportPath;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}