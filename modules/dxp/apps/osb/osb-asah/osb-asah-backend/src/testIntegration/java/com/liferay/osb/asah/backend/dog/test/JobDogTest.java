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

import com.liferay.osb.asah.backend.dog.JobDog;
import com.liferay.osb.asah.backend.model.Job;
import com.liferay.osb.asah.backend.model.JobParameter;
import com.liferay.osb.asah.backend.model.JobStatus;
import com.liferay.osb.asah.backend.model.JobTrainingFrequency;
import com.liferay.osb.asah.backend.model.JobTrainingPeriod;
import com.liferay.osb.asah.backend.model.JobType;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahBackendSpringBootApplication.class)
public class JobDogTest {

	@ElasticsearchIndex(
		name = "jobs", resourcePath = "jobs-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testAddJob() {
		Job job = _jobDog.addJob(
			new ArrayList<JobParameter>() {
				{
					add(new JobParameter("parameter1", "1.2"));
				}
			},
			JobTrainingFrequency.MANUAL, JobTrainingPeriod.LAST_30_DAYS,
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY,
			"Product Recommendation Job");

		Assert.assertNotNull(job);
		Assert.assertNotNull(job.getId());

		List<JobParameter> jobParameters = job.getJobParameters();

		JobParameter jobParameter = jobParameters.get(0);

		Assert.assertEquals("parameter1", jobParameter.getName());
		Assert.assertEquals("1.2", jobParameter.getValue());

		Assert.assertEquals(
			JobTrainingFrequency.MANUAL, job.getJobTrainingFrequency());
		Assert.assertEquals(
			JobTrainingPeriod.LAST_30_DAYS, job.getJobTrainingPeriod());
		Assert.assertEquals(
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY, job.getJobType());
		Assert.assertEquals("Product Recommendation Job", job.getName());
	}

	@ElasticsearchIndex(
		name = "jobs", resourcePath = "jobs-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteJob() {
		List<Boolean> statuses = _jobDog.deleteJobs(Arrays.asList("1"));

		Assert.assertTrue(statuses.get(0));
	}

	@ElasticsearchIndex(
		name = "jobs", resourcePath = "jobs-info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJob() {
		Job job = _jobDog.getJob("1");

		Assert.assertNotNull(job);
		Assert.assertEquals("Related Content Job", job.getName());
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job-runs-info-1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJobStatusFailed() {
		Assert.assertEquals(JobStatus.FAILED, _jobDog.getJobStatus("1"));
	}

	@Test
	public void testGetJobStatusPending() {
		Job job = _jobDog.addJob(
			Collections.emptyList(), JobTrainingFrequency.MANUAL,
			JobTrainingPeriod.LAST_30_DAYS,
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY, "Job");

		Assert.assertEquals(
			JobStatus.PENDING, _jobDog.getJobStatus(job.getId()));
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job-runs-info-2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJobStatusReady() {
		Assert.assertEquals(JobStatus.READY, _jobDog.getJobStatus("1"));
	}

	@Test
	public void testGetJobStatusScheduled() {
		Job job = _jobDog.addJob(
			Collections.emptyList(), JobTrainingFrequency.EVERY_7_DAYS,
			JobTrainingPeriod.LAST_30_DAYS,
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY, "Job");

		Assert.assertEquals(
			JobStatus.SCHEDULED, _jobDog.getJobStatus(job.getId()));
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job-runs-info-3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJobStatusTraining() {
		Assert.assertEquals(JobStatus.TRAINING, _jobDog.getJobStatus("1"));
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job-runs-info-2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJobTrainingDate() {
		LocalDateTime nowLocalDateTime = LocalDateTime.parse(
			DateUtil.newUTCDateString(),
			DateTimeFormatter.ofPattern(DateUtil.PATTERN_ISO_8601));

		LocalDateTime expectedTrainingLocalDateTime =
			nowLocalDateTime.minusDays(1);

		LocalDateTime trainingLocalDateTime = LocalDateTime.parse(
			_jobDog.getJobTrainingDateString("1"),
			DateTimeFormatter.ofPattern(DateUtil.PATTERN_ISO_8601));

		Assert.assertEquals(
			expectedTrainingLocalDateTime.getDayOfMonth(),
			trainingLocalDateTime.getDayOfMonth());
		Assert.assertEquals(
			expectedTrainingLocalDateTime.getMonthValue(),
			trainingLocalDateTime.getMonthValue());
		Assert.assertEquals(
			expectedTrainingLocalDateTime.getYear(),
			trainingLocalDateTime.getYear());
	}

	@Autowired
	private JobDog _jobDog;

}