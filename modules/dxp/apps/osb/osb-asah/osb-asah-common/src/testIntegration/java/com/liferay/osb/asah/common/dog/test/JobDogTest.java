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

package com.liferay.osb.asah.common.dog.test;

import com.liferay.osb.asah.common.OSBAsahCommonSpringTestContext;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.JobDog;
import com.liferay.osb.asah.common.dog.JobRunDog;
import com.liferay.osb.asah.common.entity.Job;
import com.liferay.osb.asah.common.entity.JobParameter;
import com.liferay.osb.asah.common.entity.JobRun;
import com.liferay.osb.asah.common.model.JobRunDataPeriod;
import com.liferay.osb.asah.common.model.JobRunFrequency;
import com.liferay.osb.asah.common.model.JobRunStatus;
import com.liferay.osb.asah.common.model.JobRunsMonthlyStatistics;
import com.liferay.osb.asah.common.model.JobStatus;
import com.liferay.osb.asah.common.model.JobType;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.repository.JobRepository;
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.annotation.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.annotation.RepositoryResource;
import com.liferay.osb.asah.test.util.spring.OSBAsahTestExecutionListenersContext;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

/**
 * @author Marcellus Tavares
 */
public class JobDogTest
	implements OSBAsahCommonSpringTestContext,
			   OSBAsahTestExecutionListenersContext {

	@Test
	public void testAddJob() {
		Job job = _jobDog.addJob(
			SetUtil.of(new JobParameter("parameter1", "1.2")),
			JobRunDataPeriod.LAST_30_DAYS, JobRunFrequency.MANUAL,
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY,
			"Product Recommendation Job", false);

		Assertions.assertNotNull(job);
		Assertions.assertNotNull(job.getId());

		List<JobParameter> jobParameters = new ArrayList<>(
			job.getJobParameters());

		JobParameter jobParameter = jobParameters.get(0);

		Assertions.assertEquals("parameter1", jobParameter.getName());
		Assertions.assertEquals("1.2", jobParameter.getValue());

		Assertions.assertEquals(
			JobRunDataPeriod.LAST_30_DAYS, job.getJobRunDataPeriod());
		Assertions.assertEquals(
			JobRunFrequency.MANUAL, job.getJobRunFrequency());
		Assertions.assertEquals(
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY, job.getJobType());
		Assertions.assertEquals("Product Recommendation Job", job.getName());
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job_runs_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = JobRepository.class,
		resourcePath = "osbasahfaroinfo/jobs.json"
	)
	@Test
	public void testDeleteJob() {
		_jobDog.deleteJobs(Arrays.asList(1L));

		Page<JobRun> jobRunPage = _jobRunDog.getJobRunPage(
			1L, 0, 20, Sort.desc("id"));

		Assertions.assertEquals(0, jobRunPage.getTotalElements());
	}

	@Test
	public void testFetchJob() {
		String jobName = RandomTestUtil.randomString();

		Job job = _jobDog.addJob(
			Collections.emptySet(), JobRunDataPeriod.LAST_30_DAYS,
			JobRunFrequency.MANUAL,
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY, jobName, false);

		Assertions.assertEquals(job, _jobDog.fetchJob(jobName));
	}

	@RepositoryResource(
		repositoryClass = JobRepository.class,
		resourcePath = "osbasahfaroinfo/jobs.json"
	)
	@Test
	public void testGetJob() {
		Job job = _jobDog.getJob(1L);

		Assertions.assertNotNull(job);
		Assertions.assertEquals("Related Content Job", job.getName());
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job_runs_info_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJobRunDate() {
		LocalDateTime nowLocalDateTime = LocalDateTime.parse(
			DateUtil.newDateString(),
			DateTimeFormatter.ofPattern(DateUtil.PATTERN_ISO_8601));

		LocalDateTime expectedRunLocalDateTime = nowLocalDateTime.minusDays(1);

		LocalDateTime runLocalDateTime = LocalDateTime.parse(
			_jobRunDog.fetchLatestJobRunPublishedDateString(1L),
			DateTimeFormatter.ofPattern(DateUtil.PATTERN_ISO_8601));

		Assertions.assertEquals(
			expectedRunLocalDateTime.getDayOfMonth(),
			runLocalDateTime.getDayOfMonth());
		Assertions.assertEquals(
			expectedRunLocalDateTime.getMonthValue(),
			runLocalDateTime.getMonthValue());
		Assertions.assertEquals(
			expectedRunLocalDateTime.getYear(), runLocalDateTime.getYear());
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job_runs_info_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = JobRepository.class,
		resourcePath = "osbasahfaroinfo/jobs.json"
	)
	@Test
	public void testGetJobRunResultBag() {
		Page<JobRun> jobRunPage = _jobRunDog.getJobRunPage(
			1L, 0, 10, Sort.desc("id"));

		Assertions.assertEquals(3, jobRunPage.getTotalElements());

		Assertions.assertEquals(
			Arrays.asList(3L, 2L, 1L),
			_getJobRunsProperty(jobRunPage.getContent(), JobRun::getId));
		Assertions.assertEquals(
			Arrays.asList(
				JobRunStatus.FAILED, JobRunStatus.PUBLISHED,
				JobRunStatus.FAILED),
			_getJobRunsProperty(
				jobRunPage.getContent(), JobRun::getJobRunStatus));
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job_runs_info_4.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@RepositoryResource(
		repositoryClass = JobRepository.class,
		resourcePath = "osbasahfaroinfo/jobs.json"
	)
	@Test
	public void testGetJobRunsMonthlyStatistics() {
		JobRunsMonthlyStatistics jobRunsMonthlyStatistics =
			_jobRunDog.getJobRunsMonthlyStatistics(_jobDog.getJob(1L));

		Page<JobRun> jobRunPage = _jobRunDog.getJobRunPage(
			1L, 0, 20, Sort.desc("id"));

		List<JobRun> jobRuns = jobRunPage.getContent();

		Assertions.assertEquals(
			jobRunsMonthlyStatistics.getCompletedJobRuns(),
			_countCurrentMonthJobRunsByStatus(jobRuns, JobRunStatus.COMPLETED));
		Assertions.assertEquals(
			jobRunsMonthlyStatistics.getFailedJobRuns(),
			_countCurrentMonthJobRunsByStatus(jobRuns, JobRunStatus.FAILED));
		Assertions.assertEquals(
			jobRunsMonthlyStatistics.getRunningJobRuns(),
			_countCurrentMonthJobRunsByStatus(jobRuns, JobRunStatus.RUNNING));
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job_runs_info_1.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJobStatusFailed() {
		Assertions.assertEquals(JobStatus.FAILED, _jobDog.getJobStatus(1L));
	}

	@Test
	public void testGetJobStatusPending() {
		Job job = _jobDog.addJob(
			Collections.emptySet(), JobRunDataPeriod.LAST_30_DAYS,
			JobRunFrequency.MANUAL,
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY, "Job", false);

		Assertions.assertEquals(
			JobStatus.PENDING, _jobDog.getJobStatus(job.getId()));
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job_runs_info_2.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJobStatusReady() {
		Assertions.assertEquals(JobStatus.READY, _jobDog.getJobStatus(1L));
	}

	@ElasticsearchIndex(
		name = "job-runs", resourcePath = "job_runs_info_3.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_FARO_INFO
	)
	@Test
	public void testGetJobStatusRunning() {
		Assertions.assertEquals(JobStatus.RUNNING, _jobDog.getJobStatus(1L));
	}

	@Test
	public void testGetJobStatusScheduled() {
		Job job = _jobDog.addJob(
			Collections.emptySet(), JobRunDataPeriod.LAST_30_DAYS,
			JobRunFrequency.EVERY_7_DAYS,
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY, "Job", false);

		Assertions.assertEquals(
			JobStatus.SCHEDULED, _jobDog.getJobStatus(job.getId()));
	}

	@Test
	public void testUpdateJob() {
		Job job = _jobDog.addJob(
			SetUtil.of(new JobParameter("parameter1", "1.2")),
			JobRunDataPeriod.LAST_30_DAYS, JobRunFrequency.MANUAL,
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY,
			"Product Recommendation Job", false);

		Assertions.assertNotNull(job);
		Assertions.assertNotNull(job.getId());

		job = _jobDog.updateJob(
			job.getId(), SetUtil.of(new JobParameter("parameter1", "1.3")),
			JobRunDataPeriod.LAST_180_DAYS, JobRunFrequency.EVERY_7_DAYS,
			"Product Recommendation Job Updated", false);

		Assertions.assertNotNull(job);
		Assertions.assertNotNull(job.getId());

		List<JobParameter> jobParameters = new ArrayList<>(
			job.getJobParameters());

		JobParameter jobParameter = jobParameters.get(0);

		Assertions.assertEquals("parameter1", jobParameter.getName());
		Assertions.assertEquals("1.3", jobParameter.getValue());

		Assertions.assertEquals(
			JobRunDataPeriod.LAST_180_DAYS, job.getJobRunDataPeriod());
		Assertions.assertEquals(
			JobRunFrequency.EVERY_7_DAYS, job.getJobRunFrequency());
		Assertions.assertEquals(
			JobType.CONTENT_RECOMMENDATION_ITEM_SIMILARITY, job.getJobType());
		Assertions.assertEquals(
			"Product Recommendation Job Updated", job.getName());
	}

	private int _countCurrentMonthJobRunsByStatus(
		List<JobRun> jobRuns, JobRunStatus jobRunStatus) {

		int count = 0;

		LocalDateTime nowLocalDateTime = LocalDateTime.now(ZoneOffset.UTC);

		for (JobRun jobRun : jobRuns) {
			if (jobRun.getJobRunStatus() != jobRunStatus) {
				continue;
			}

			LocalDateTime createDateLocalDateTime =
				jobRun.getCreateLocalDateTime();

			if (createDateLocalDateTime.getMonthValue() ==
					nowLocalDateTime.getMonthValue()) {

				count++;
			}
		}

		return count;
	}

	private <R> List<R> _getJobRunsProperty(
		List<JobRun> jobRuns, Function<JobRun, ? extends R> mapFunction) {

		return ListUtil.map(jobRuns, mapFunction);
	}

	@Autowired
	private JobDog _jobDog;

	@Autowired
	private JobRunDog _jobRunDog;

}