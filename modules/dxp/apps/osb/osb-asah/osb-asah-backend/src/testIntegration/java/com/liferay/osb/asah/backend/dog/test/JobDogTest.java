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
import com.liferay.osb.asah.backend.model.JobRun;
import com.liferay.osb.asah.backend.model.JobRunDataPeriod;
import com.liferay.osb.asah.backend.model.JobRunFrequency;
import com.liferay.osb.asah.backend.model.JobRunStatus;
import com.liferay.osb.asah.backend.model.JobRunsMonthlyStatistics;
import com.liferay.osb.asah.backend.model.JobStatus;
import com.liferay.osb.asah.backend.model.JobType;
import com.liferay.osb.asah.backend.spring.OSBAsahBackendSpringBootApplication;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.model.ResultBag;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	@Test
	public void testAddJob() {
		Job job = _jobDog.addJob(
			new ArrayList<JobParameter>() {
				{
					add(new JobParameter("parameter1", "1.2"));
				}
			},
			JobRunDataPeriod.LAST_30_DAYS, JobRunFrequency.MANUAL,
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY,
			"Product Recommendation Job", false);

		Assert.assertNotNull(job);
		Assert.assertNotNull(job.getId());

		List<JobParameter> jobParameters = job.getJobParameters();

		JobParameter jobParameter = jobParameters.get(0);

		Assert.assertEquals("parameter1", jobParameter.getName());
		Assert.assertEquals("1.2", jobParameter.getValue());

		Assert.assertEquals(
			JobRunDataPeriod.LAST_30_DAYS, job.getJobRunDataPeriod());
		Assert.assertEquals(JobRunFrequency.MANUAL, job.getJobRunFrequency());
		Assert.assertEquals(
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY, job.getJobType());
		Assert.assertEquals("Product Recommendation Job", job.getName());
	}

	@ElasticsearchIndex(
		name = "jobs", resourcePath = "jobs_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job_runs_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testDeleteJob() {
		List<Boolean> statuses = _jobDog.deleteJobs(Arrays.asList("1"));

		Assert.assertTrue(statuses.get(0));

		ResultBag<JobRun> jobRunResultBag = _jobDog.getJobRunResultBag(
			"1", 20, Sort.desc("id"), 0);

		Assert.assertEquals(0, jobRunResultBag.getTotal());
	}

	@Test
	public void testFetchJob() {
		String jobName = RandomTestUtil.randomString();

		Job job = _jobDog.addJob(
			Collections.emptyList(), JobRunDataPeriod.LAST_30_DAYS,
			JobRunFrequency.MANUAL,
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY, jobName, false);

		Assert.assertEquals(job, _jobDog.fetchJob(jobName));
	}

	@ElasticsearchIndex(
		name = "jobs", resourcePath = "jobs_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJob() {
		Job job = _jobDog.getJob("1");

		Assert.assertNotNull(job);
		Assert.assertEquals("Related Content Job", job.getName());
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job_runs_info_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJobRunDate() {
		LocalDateTime nowLocalDateTime = LocalDateTime.parse(
			DateUtil.newUTCDateString(),
			DateTimeFormatter.ofPattern(DateUtil.PATTERN_ISO_8601));

		LocalDateTime expectedRunLocalDateTime = nowLocalDateTime.minusDays(1);

		LocalDateTime runLocalDateTime = LocalDateTime.parse(
			_jobDog.getJobRunDateString("1"),
			DateTimeFormatter.ofPattern(DateUtil.PATTERN_ISO_8601));

		Assert.assertEquals(
			expectedRunLocalDateTime.getDayOfMonth(),
			runLocalDateTime.getDayOfMonth());
		Assert.assertEquals(
			expectedRunLocalDateTime.getMonthValue(),
			runLocalDateTime.getMonthValue());
		Assert.assertEquals(
			expectedRunLocalDateTime.getYear(), runLocalDateTime.getYear());
	}

	@ElasticsearchIndex(
		name = "jobs", resourcePath = "jobs_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job_runs_info_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJobRunResultBag() {
		ResultBag<JobRun> jobRunResultBag = _jobDog.getJobRunResultBag(
			"1", 10, Sort.desc("id"), 0);

		Assert.assertEquals(3, jobRunResultBag.getTotal());

		Assert.assertEquals(
			Arrays.asList("3", "2", "1"),
			_getJobRunsProperty(jobRunResultBag.getResults(), JobRun::getId));
		Assert.assertEquals(
			Arrays.asList(
				JobRunStatus.FAILED, JobRunStatus.PUBLISHED,
				JobRunStatus.FAILED),
			_getJobRunsProperty(
				jobRunResultBag.getResults(), JobRun::getJobRunStatus));
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job_runs_info_4.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@ElasticsearchIndex(
		name = "jobs", resourcePath = "jobs_info.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJobRunsMonthlyStatistics() {
		JobRunsMonthlyStatistics jobRunsMonthlyStatistics =
			_jobDog.getJobRunsMonthlyStatistics("1");

		ResultBag<JobRun> jobRunResultBag = _jobDog.getJobRunResultBag(
			"1", 20, Sort.desc("id"), 0);

		List<JobRun> jobRuns = jobRunResultBag.getResults();

		Assert.assertEquals(
			jobRunsMonthlyStatistics.getCompletedJobRuns(),
			_countCurrentMonthJobRunsByStatus(jobRuns, JobRunStatus.COMPLETED));
		Assert.assertEquals(
			jobRunsMonthlyStatistics.getFailedJobRuns(),
			_countCurrentMonthJobRunsByStatus(jobRuns, JobRunStatus.FAILED));
		Assert.assertEquals(
			jobRunsMonthlyStatistics.getRunningJobRuns(),
			_countCurrentMonthJobRunsByStatus(jobRuns, JobRunStatus.RUNNING));
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job_runs_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJobStatusFailed() {
		Assert.assertEquals(JobStatus.FAILED, _jobDog.getJobStatus("1"));
	}

	@Test
	public void testGetJobStatusPending() {
		Job job = _jobDog.addJob(
			Collections.emptyList(), JobRunDataPeriod.LAST_30_DAYS,
			JobRunFrequency.MANUAL,
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY, "Job", false);

		Assert.assertEquals(
			JobStatus.PENDING, _jobDog.getJobStatus(job.getId()));
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job_runs_info_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJobStatusReady() {
		Assert.assertEquals(JobStatus.READY, _jobDog.getJobStatus("1"));
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job_runs_info_3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJobStatusRunning() {
		Assert.assertEquals(JobStatus.RUNNING, _jobDog.getJobStatus("1"));
	}

	@Test
	public void testGetJobStatusScheduled() {
		Job job = _jobDog.addJob(
			Collections.emptyList(), JobRunDataPeriod.LAST_30_DAYS,
			JobRunFrequency.EVERY_7_DAYS,
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY, "Job", false);

		Assert.assertEquals(
			JobStatus.SCHEDULED, _jobDog.getJobStatus(job.getId()));
	}

	@Test
	public void testUpdateJob() {
		Job job = _jobDog.addJob(
			new ArrayList<JobParameter>() {
				{
					add(new JobParameter("parameter1", "1.2"));
				}
			},
			JobRunDataPeriod.LAST_30_DAYS, JobRunFrequency.MANUAL,
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY,
			"Product Recommendation Job", false);

		Assert.assertNotNull(job);
		Assert.assertNotNull(job.getId());

		job = _jobDog.updateJob(
			job.getId(),
			new ArrayList<JobParameter>() {
				{
					add(new JobParameter("parameter1", "1.3"));
				}
			},
			JobRunDataPeriod.LAST_180_DAYS, JobRunFrequency.EVERY_7_DAYS,
			"Product Recommendation Job Updated", false);

		Assert.assertNotNull(job);
		Assert.assertNotNull(job.getId());

		List<JobParameter> jobParameters = job.getJobParameters();

		JobParameter jobParameter = jobParameters.get(0);

		Assert.assertEquals("parameter1", jobParameter.getName());
		Assert.assertEquals("1.3", jobParameter.getValue());

		Assert.assertEquals(
			JobRunDataPeriod.LAST_180_DAYS, job.getJobRunDataPeriod());
		Assert.assertEquals(
			JobRunFrequency.EVERY_7_DAYS, job.getJobRunFrequency());
		Assert.assertEquals(
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY, job.getJobType());
		Assert.assertEquals(
			"Product Recommendation Job Updated", job.getName());
	}

	private int _countCurrentMonthJobRunsByStatus(
		List<JobRun> jobRuns, JobRunStatus jobRunStatus) {

		int count = 0;

		LocalDateTime nowLocalDateTime = LocalDateTime.now(ZoneId.of("UTC"));

		for (JobRun jobRun : jobRuns) {
			if (jobRun.getJobRunStatus() != jobRunStatus) {
				continue;
			}

			LocalDateTime createdDateLocalDateTime = DateUtil.toLocalDateTime(
				jobRun.getCreatedDate(), ZoneId.of("UTC"));

			if (createdDateLocalDateTime.getMonthValue() ==
					nowLocalDateTime.getMonthValue()) {

				count++;
			}
		}

		return count;
	}

	private <R> List<R> _getJobRunsProperty(
		List<JobRun> jobRuns, Function<JobRun, ? extends R> mapFunction) {

		Stream<JobRun> stream = jobRuns.stream();

		return stream.map(
			mapFunction
		).collect(
			Collectors.toList()
		);
	}

	@Autowired
	private JobDog _jobDog;

}