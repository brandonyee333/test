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

package com.liferay.osb.asah.backend.dog.test;

import com.liferay.osb.asah.backend.dog.DataControlTaskDog;
import com.liferay.osb.asah.backend.model.DataControlTask;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.model.DataControlTaskStatus;
import com.liferay.osb.asah.common.model.DataControlTaskType;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.io.File;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Matthew Kong
 */
@ElasticsearchIndex(
	name = "data-control-tasks", resourcePath = "data_control_tasks.json",
	weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
)
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class DataControlTaskDogTest {

	@Before
	public void setUp() throws Exception {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();

		_tempPath = Files.createTempDirectory("temp");
	}

	@After
	public void tearDown() {
		File folder = _tempPath.toFile();

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

	@Test
	public void testAddDataControlTasksFile() throws Exception {
		String content = "test1@liferay.com\ntest2@liferay.com";

		Path path = Files.write(
			Paths.get(_tempPath + "/test.csv"),
			content.getBytes(StandardCharsets.UTF_8));

		_dataControlTaskDog.addDataControlTasks(
			null, Paths.get(_tempPath.toString(), "test.csv"), "1000",
			Collections.singletonList(DataControlTaskType.SUPPRESS.toString()));

		JSONArray jsonArray = _elasticsearchInvoker.get(
			"data-control-tasks", QueryBuilders.matchAllQuery());

		Assert.assertEquals(6, jsonArray.length());

		JSONObject dataControlTaskJSONObject = jsonArray.getJSONObject(4);

		Assert.assertEquals(
			"test1@liferay.com",
			dataControlTaskJSONObject.getString("emailAddress"));

		dataControlTaskJSONObject = jsonArray.getJSONObject(5);

		Assert.assertEquals(
			"test2@liferay.com",
			dataControlTaskJSONObject.getString("emailAddress"));

		File file = path.toFile();

		if (!file.delete()) {
			_log.error("Unable to delete file " + file.getAbsolutePath());
		}
	}

	@ElasticsearchIndex(
		name = "suppressions", resourcePath = "suppressions.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testAddUnsuppressDataControlTasks() {
		_dataControlTaskDog.addDataControlTasks(
			Collections.singletonList("test@liferay.com"), null, "1000",
			Collections.singletonList(
				DataControlTaskType.UNSUPPRESS.toString()));

		JSONObject suppressionJSONObject = _elasticsearchInvoker.fetch(
			"suppressions",
			QueryBuilders.termQuery("emailAddress", "test@liferay.com"));

		Assert.assertEquals(
			DataControlTaskStatus.PENDING.toString(),
			suppressionJSONObject.getString("dataControlTaskStatus"));
	}

	@Test
	public void testGetDataControlTaskResultBagBatch() {
		ResultBag<DataControlTask> resultBag =
			_dataControlTaskDog.getDataControlTaskResultBag(
				"102", null, null, 10, Sort.desc("createDate"), 0, null, null);

		_checkResults(
			2, Arrays.asList("jane.doe@liferay.com", "test@liferay.com"),
			resultBag);
	}

	@Test
	public void testGetDataControlTaskResultBagCombination() {
		ResultBag<DataControlTask> resultBag =
			_dataControlTaskDog.getDataControlTaskResultBag(
				"101", "liferay", 30, 10, Sort.desc("createDate"), 0,
				Collections.singletonList(
					DataControlTaskStatus.COMPLETED.toString()),
				Collections.singletonList(
					DataControlTaskType.SUPPRESS.toString()));

		_checkResults(
			1, Collections.singletonList("john.doe@liferay.com"), resultBag);
	}

	@Test
	public void testGetDataControlTaskResultBagPagination() {
		ResultBag<DataControlTask> resultBag =
			_dataControlTaskDog.getDataControlTaskResultBag(
				null, null, null, 1, Sort.desc("createDate"), 1, null, null);

		_checkResults(
			4, Collections.singletonList("test@liferay.com"), resultBag);
	}

	@Test
	public void testGetDataControlTaskResultBagRange() {
		ResultBag<DataControlTask> resultBag =
			_dataControlTaskDog.getDataControlTaskResultBag(
				null, null, 30, 10, Sort.desc("createDate"), 0, null, null);

		_checkResults(
			3,
			Arrays.asList(
				"jane.doe@liferay.com", "test@liferay.com",
				"john.doe@liferay.com"),
			resultBag);
	}

	@Test
	public void testGetDataControlTaskResultBagSearch() {
		ResultBag<DataControlTask> resultBag =
			_dataControlTaskDog.getDataControlTaskResultBag(
				null, "doe", null, 10, Sort.desc("createDate"), 0, null, null);

		_checkResults(
			2, Arrays.asList("jane.doe@liferay.com", "john.doe@liferay.com"),
			resultBag);
	}

	@Test
	public void testGetDataControlTaskResultBagStatus() {
		ResultBag<DataControlTask> resultBag =
			_dataControlTaskDog.getDataControlTaskResultBag(
				null, null, null, 10, Sort.desc("createDate"), 0,
				Collections.singletonList(
					DataControlTaskStatus.PENDING.toString()),
				null);

		_checkResults(
			1, Collections.singletonList("jane.doe@liferay.com"), resultBag);
	}

	@Test
	public void testGetDataControlTaskResultBagTypes() {
		ResultBag<DataControlTask> resultBag =
			_dataControlTaskDog.getDataControlTaskResultBag(
				null, null, null, 10, Sort.desc("createDate"), 0, null,
				Collections.singletonList(
					DataControlTaskType.UNSUPPRESS.toString()));

		_checkResults(
			1, Collections.singletonList("test@liferay.com"), resultBag);
	}

	private void _checkResults(
		long expectedTotal, List<String> expectedResults,
		ResultBag<DataControlTask> resultBag) {

		Assert.assertEquals(expectedTotal, resultBag.getTotal());

		List<DataControlTask> results = resultBag.getResults();

		Stream<DataControlTask> stream = results.stream();

		Assert.assertEquals(
			expectedResults,
			stream.map(
				DataControlTask::getEmailAddress
			).collect(
				Collectors.toList()
			));
	}

	private static final Log _log = LogFactory.getLog(
		DataControlTaskDogTest.class);

	@Autowired
	private DataControlTaskDog _dataControlTaskDog;

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

	private Path _tempPath;

}