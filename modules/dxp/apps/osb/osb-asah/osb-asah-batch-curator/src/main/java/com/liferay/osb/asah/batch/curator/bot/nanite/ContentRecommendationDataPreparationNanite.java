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

package com.liferay.osb.asah.batch.curator.bot.nanite;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.batch.curator.bot.nanite.dataproc.DataprocSparkManager;
import com.liferay.osb.asah.common.dog.JobDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Job;
import com.liferay.osb.asah.common.model.JobRun;
import com.liferay.osb.asah.common.model.JobRunStatus;
import com.liferay.osb.asah.common.model.JobRunsMonthlyStatistics;
import com.liferay.osb.asah.common.repository.JobRunRepository;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@ConditionalOnGoogleApplicationCredentials
public class ContentRecommendationDataPreparationNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		Job job = _objectMapper.convertValue(
			contextJSONObject.getJSONObject("job"), Job.class);

		if (_getCurrentMonthJobRunsCount(job.getId()) >= _maxMonthlyJobRuns) {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Unable to run job ID %s because this run surpasses " +
							"the maximum allowed monthly runs threshold",
						job.getId()));
			}

			return;
		}

		Date date = new Date();
		String jobRunRunDataPeriod = contextJSONObject.optString(
			"runDataPeriod", String.valueOf(job.getJobRunDataPeriod()));

		JobRun jobRun = new JobRun();

		jobRun.setContextJSONObject(
			JSONUtil.put("runDataPeriod", jobRunRunDataPeriod));
		jobRun.setCreatedDate(date);
		jobRun.setJobId(job.getId());
		jobRun.setJobRunStatus(JobRunStatus.RUNNING);
		jobRun.setJobType(String.valueOf(job.getJobType()));
		jobRun.setLastUpdatedDate(date);
		jobRun.setStep("DATA_PREPARATION");
		jobRun.setTrigger(contextJSONObject.optString("trigger", "SCHEDULE"));

		jobRun = _jobRunRepository.save(jobRun);

		_dataprocSparkManager.submitJob(
			Arrays.asList(
				"--job-id", String.valueOf(job.getId()), "--job-parameters",
				String.valueOf(
					_objectMapper.convertValue(
						job.getJobParameters(), JSONArray.class)),
				"--job-run-id", String.valueOf(jobRun.getId()),
				"--job-run-data-period", jobRunRunDataPeriod, "--job-run-step",
				jobRun.getStep(), "--lcp-project-id",
				ProjectIdThreadLocal.getProjectId()),
			"content_recommendation.yaml", Collections.emptyList(),
			"liferay.content_recommendation.ContentRecommendationApplication",
			Collections.emptyMap());
	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(
			ContentRecommendationDataPreparationNanite.class);
	}

	private long _getCurrentMonthJobRunsCount(Long jobId) {
		JobRunsMonthlyStatistics jobRunsMonthlyStatistics =
			_jobDog.getJobRunsMonthlyStatistics(jobId);

		return jobRunsMonthlyStatistics.getCompletedJobRuns() +
			jobRunsMonthlyStatistics.getPublishedJobRuns();
	}

	private static final Log _log = LogFactory.getLog(
		ContentRecommendationDataPreparationNanite.class);

	@Autowired
	private DataprocSparkManager _dataprocSparkManager;

	@Autowired
	private JobDog _jobDog;

	@Autowired
	private JobRunRepository _jobRunRepository;

	@Value("${osb.asah.content.recommendation.max.monthly.job.runs:10}")
	private int _maxMonthlyJobRuns;

	@Autowired
	private ObjectMapper _objectMapper;

}