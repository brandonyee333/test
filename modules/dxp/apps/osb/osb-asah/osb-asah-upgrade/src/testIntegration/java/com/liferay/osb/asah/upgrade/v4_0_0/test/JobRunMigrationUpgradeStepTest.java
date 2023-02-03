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

package com.liferay.osb.asah.upgrade.v4_0_0.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.entity.Job;
import com.liferay.osb.asah.common.entity.JobRun;
import com.liferay.osb.asah.common.repository.JobRepository;
import com.liferay.osb.asah.common.repository.JobRunRepository;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.spring.TestExecutionListenerUtil;
import com.liferay.osb.asah.upgrade.OSBAsahUpgradeSpringTestContext;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.upgrade.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.upgrade.v4_0_0.JobRunMigrationUpgradeStep;

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
public class JobRunMigrationUpgradeStepTest
	implements OSBAsahUpgradeSpringTestContext {

	@BeforeEach
	public void setUp() throws Exception {
		ProjectIdThreadLocal.setProjectId("test");

		_jobRunRepository.deleteAll();
		_jobRepository.deleteAll();

		Job job = new Job();

		job.setId(321L);
		job.setIsNew(Boolean.TRUE);

		_jobRepository.save(job);

		_elasticsearchIndexManager.delete("test_osbasahfaroinfo_job-runs");

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				"dependencies/job_runs_index_configuration.json", this),
			"test_osbasahfaroinfo_job-runs");

		_elasticsearchIndexManager.addAlias(
			"test_osbasahfaroinfo_job-runs_alias",
			"test_osbasahfaroinfo_job-runs");
	}

	@AfterEach
	public void tearDown() throws Exception {
		_elasticsearchIndexManager.delete("test_osbasahfaroinfo_job-runs");

		_jobRunRepository.deleteAll();
		_jobRepository.deleteAll();
	}

	@Test
	public void testUpgrade() throws Exception {
		JSONArray jsonArray = new JSONArray(
			TestExecutionListenerUtil.replaceVariables(
				ResourceUtil.readResourceToString(
					"dependencies/job_runs.json", this)));

		Assertions.assertFalse(jsonArray.isEmpty());

		_elasticsearchInvoker.add("job-runs", jsonArray);

		_jobRunMigrationUpgradeStep.upgrade("");

		List<Object> jsonObjectList = jsonArray.toList();

		Stream<Object> stream = jsonObjectList.stream();

		Assertions.assertEquals(
			stream.map(
				object -> _objectMapper.convertValue(object, JobRun.class)
			).collect(
				Collectors.toList()
			),
			_jobRunRepository.findAll());

		Assertions.assertTrue(_jobRunMigrationUpgradeStep.isSequenceSync());
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private JobRepository _jobRepository;

	@Autowired
	private JobRunMigrationUpgradeStep _jobRunMigrationUpgradeStep;

	@Autowired
	private JobRunRepository _jobRunRepository;

	@Autowired
	private ObjectMapper _objectMapper;

}