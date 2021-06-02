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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchBulkRequestBuilder;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.storage.impl.GoogleStorageArchiver;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.io.File;
import java.io.FileInputStream;

import java.nio.charset.StandardCharsets;

import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

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
			JSONObject jsonObject = messageJSONObject.getJSONObject("body");

			jsonObject.put("lastUpdatedDate", DateUtil.newUTCDateString());

			_faroInfoElasticsearchInvoker.update(
				"job-runs", messageJSONObject.getString("jobRunId"),
				jsonObject);
		}
		else if (Objects.equals(operation, "PublishJobRun")) {
			_publishJobRun(
				messageJSONObject.getString("jobId"),
				messageJSONObject.getString("jobRunId"));
		}
	}

	private void _completeJobRun(JSONObject jobRunJSONObject) {
		_faroInfoElasticsearchInvoker.update(
			"job-runs",
			jobRunJSONObject.put(
				"lastUpdatedDate", DateUtil.newUTCDateString()
			).put(
				"status", "COMPLETED"
			));
	}

	private void _publishJobRun(JSONObject jobRunJSONObject) {
		String dateString = DateUtil.newUTCDateString();

		_faroInfoElasticsearchInvoker.update(
			"job-runs",
			jobRunJSONObject.put(
				"completedDate", dateString
			).put(
				"lastUpdatedDate", dateString
			).put(
				"status", "PUBLISHED"
			));
	}

	private void _publishJobRun(String jobId, String jobRunId) {
		JSONObject jobRunJSONObject = _faroInfoElasticsearchInvoker.get(
			"job-runs", jobRunId);

		JSONObject lastPublishedJobRunJSONObject =
			_faroInfoElasticsearchInvoker.fetch(
				"job-runs",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("job.id", jobId)
				).filter(
					QueryBuilders.termQuery("status", "PUBLISHED")
				));

		try {
			File sparkJobResultFile = _googleStorageArchiver.readSparkJobResult(
				StringUtils.replace(
					_contentRecommendationsBucketTemplate, "{region}",
					System.getenv("LCP_PROJECT_CLUSTER")),
				String.format("%s/%s", jobId, jobRunId),
				DateUtil.toUTCDate(jobRunJSONObject.getString("createdDate")),
				"");

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

		_completeJobRun(lastPublishedJobRunJSONObject);

		_publishJobRun(jobRunJSONObject);
	}

	private void _replaceRecommendedItems(File sparkJobResultFile)
		throws Exception {

		try (ZipInputStream zipInputStream = new ZipInputStream(
				new FileInputStream(sparkJobResultFile))) {

			ZipEntry nextEntry = zipInputStream.getNextEntry();

			if (nextEntry == null) {
				return;
			}

			ElasticsearchBulkRequestBuilder elasticsearchBulkRequestBuilder =
				_faroInfoElasticsearchInvoker.
					createElasticsearchBulkRequestBuilder();

			List<String> lines = IOUtils.readLines(
				zipInputStream, StandardCharsets.UTF_8);

			for (String line : lines) {
				elasticsearchBulkRequestBuilder.replace(
					"recommended-items", new JSONObject(line));
			}

			if (elasticsearchBulkRequestBuilder.hasActions()) {
				elasticsearchBulkRequestBuilder.get();
			}
		}
	}

	private static final Log _log = LogFactory.getLog(
		ContentRecommendationDataprocMessageProcessor.class);

	@Value(
		"${osb.asah.content.recommendations.google.bucket:analytics-cloud-content-recommendations-{region}}}"
	)
	private String _contentRecommendationsBucketTemplate;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private GoogleStorageArchiver _googleStorageArchiver;

}