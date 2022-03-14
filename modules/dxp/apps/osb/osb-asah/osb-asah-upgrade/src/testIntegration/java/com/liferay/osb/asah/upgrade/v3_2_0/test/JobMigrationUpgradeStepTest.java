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

package com.liferay.osb.asah.upgrade.v3_2_0.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.AsahTask;
import com.liferay.osb.asah.common.entity.Job;
import com.liferay.osb.asah.common.repository.AsahTaskRepository;
import com.liferay.osb.asah.common.repository.JobRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.v3_2_0.JobMigrationUpgradeStep;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.json.JSONArray;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Robson Pastor
 */
public class JobMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_jobRepository.deleteAll();
		_asahTaskRepository.deleteAll();

		_elasticsearchIndexManager.delete("test_osbasahfaroinfo_jobs");

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				"dependencies/jobs_index_configuration.json", this),
			"test_osbasahfaroinfo_jobs");

		_elasticsearchIndexManager.addAlias(
			"test_osbasahfaroinfo_jobs_alias", "test_osbasahfaroinfo_jobs");

		AsahTask asahTask = new AsahTask("Foo", null, "test");

		asahTask.setId(450553576847486527L);
		asahTask.setIsNew(Boolean.TRUE);

		_asahTaskRepository.save(asahTask);
	}

	@AfterEach
	public void tearDown() throws Exception {
		_elasticsearchIndexManager.delete("test_osbasahfaroinfo_jobs");
		_jobRepository.deleteAll();
		_asahTaskRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/jobs.json", this)));

		Assertions.assertFalse(jsonArray.isEmpty());

		_elasticsearchInvoker.add("jobs", jsonArray);

		_jobMigrationUpgradeStep.upgrade("");

		List<Object> jsonObjectList = jsonArray.toList();

		Stream<Object> stream = jsonObjectList.stream();

		Assertions.assertEquals(
			stream.map(
				object -> _objectMapper.convertValue(object, Job.class)
			).collect(
				Collectors.toList()
			),
			_jobRepository.findAll());

		Assertions.assertTrue(_jobMigrationUpgradeStep.isSequenceSync());
	}

	@Autowired
	private AsahTaskRepository _asahTaskRepository;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private JobMigrationUpgradeStep _jobMigrationUpgradeStep;

	@Autowired
	private JobRepository _jobRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}