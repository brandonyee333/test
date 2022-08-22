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

package com.liferay.osb.asah.batch.curator.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.JobRunDog;
import com.liferay.osb.asah.common.entity.JobRun;
import com.liferay.osb.asah.common.model.JobRunStatus;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.storage.impl.GoogleStorageArchiver;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.io.File;
import java.io.FileInputStream;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 /**
 * @author Marcellus Tavares
 */
@Component
@ConditionalOnGoogleApplicationCredentials
public class ContentRecommendationDataprocMessageProcessor
	implements DataprocMessageProcessor {

	@Override
	public String getApplicationId() {
		return "content_recommendation";
	}

	@Override
	public void process(JSONObject messageJSONObject) {
		String operation = messageJSONObject.getString("operation");

		if (Objects.equals(operation, "UpdateJobRun")) {
			JobRun jobRun = _objectMapper.convertValue(
				messageJSONObject.getJSONObject("body"), JobRun.class);

			jobRun.setModifiedLocalDateTime(LocalDateTime.now(ZoneOffset.UTC));

			_jobRunDog.updateJobRun(jobRun);
		}
		else if (Objects.equals(operation, "PublishJobRun")) {
			_publishJobRun(
				messageJSONObject.getLong("jobId"),
				messageJSONObject.getLong("jobRunId"));
		}
	}

	private void _completeJobRun(JobRun jobRun) {
		jobRun.setJobRunStatus(JobRunStatus.COMPLETED);
		jobRun.setModifiedLocalDateTime(LocalDateTime.now(ZoneOffset.UTC));

		_jobRunDog.updateJobRun(jobRun);
	}

	private void _publishJobRun(JobRun jobRun) {
		jobRun.setCompletedDate(DateUtil.newDate());
		jobRun.setJobRunStatus(JobRunStatus.PUBLISHED);
		jobRun.setModifiedLocalDateTime(LocalDateTime.now(ZoneOffset.UTC));
	}

	private void _publishJobRun(Long jobId, Long jobRunId) {
		JobRun jobRun = _jobRunDog.getJobRun(jobRunId);
		JobRun lastPublishedJobRun = _jobRunDog.fetchLatestJobRun(
			jobId, JobRunStatus.PUBLISHED);

		try {
			File sparkJobResultFile = _googleStorageArchiver.readSparkJobResult(
				StringUtils.replace(
					_contentRecommendationsBucketTemplate, "{region}",
					System.getenv("LCP_PROJECT_CLUSTER")),
				String.format("%d/%d", jobId, jobRunId),
				ProjectIdThreadLocal.getProjectId(),
				DateUtil.toUTCDate(jobRun.getCreateLocalDateTime()), "");

			if (sparkJobResultFile == null) {
				_log.error(
					"There is no content recommendation data writen for job " +
						"run ID " + jobRunId);

				return;
			}

			_replaceRecommendedItems(sparkJobResultFile);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		_completeJobRun(lastPublishedJobRun);

		_publishJobRun(jobRun);
	}

	private void _replaceRecommendedItems(File sparkJobResultFile)
		throws Exception {

		try (ZipInputStream zipInputStream = new ZipInputStream(
				new FileInputStream(sparkJobResultFile))) {

			ZipEntry nextEntry = zipInputStream.getNextEntry();

			// TODO Replace recommended items

		}
	}

	private static final Log _log = LogFactory.getLog(
		ContentRecommendationDataprocMessageProcessor.class);

	@Value(
		"${osb.asah.content.recommendations.google.bucket:analytics-cloud-content-recommendations-{region}}}"
	)
	private String _contentRecommendationsBucketTemplate;

	@Autowired
	private GoogleStorageArchiver _googleStorageArchiver;

	@Autowired
	private JobRunDog _jobRunDog;

	@Autowired
	private ObjectMapper _objectMapper;

}