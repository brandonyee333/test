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

import com.liferay.osb.asah.batch.curator.bot.nanite.dataproc.DataprocSparkManager;
import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

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
		JSONObject jobJSONObject = contextJSONObject.getJSONObject("job");

		String jobId = jobJSONObject.getString("id");

		if (_getCurrentMonthJobRunsCount(jobId) >= _maxMonthlyJobRuns) {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Unable to run job ID %s because this run surpasses " +
							"the maximum allowed monthly runs threshold",
						jobId));
			}

			return;
		}

		String dateString = DateUtil.newUTCDateString();
		String jobRunRunDataPeriod = contextJSONObject.optString(
			"runDataPeriod", jobJSONObject.getString("runDataPeriod"));

		JSONObject jobRunJSONObject = _faroInfoElasticsearchInvoker.add(
			"job-runs",
			JSONUtil.put(
				"context", JSONUtil.put("runDataPeriod", jobRunRunDataPeriod)
			).put(
				"createdDate", dateString
			).put(
				"job",
				JSONUtil.put(
					"id", jobJSONObject.getString("id")
				).put(
					"type", jobJSONObject.getString("type")
				)
			).put(
				"lastUpdatedDate", dateString
			).put(
				"status", "RUNNING"
			).put(
				"step", "DATA_PREPARATION"
			).put(
				"trigger", contextJSONObject.optString("trigger", "SCHEDULE")
			));

		_dataprocSparkManager.submitJob(
			Arrays.asList(
				"--job-id", jobJSONObject.getString("id"), "--job-parameters",
				String.valueOf(jobJSONObject.getJSONArray("parameters")),
				"--job-run-id", jobRunJSONObject.getString("id"),
				"--job-run-data-period", jobRunRunDataPeriod, "--job-run-step",
				jobRunJSONObject.getString("step"), "--lcp-project-id",
				ServiceConstants.LCP_PROJECT_ID),
			"content_recommendation.yaml", Collections.emptyList(),
			"liferay.content_recommendation.ContentRecommendationApplication",
			Collections.emptyMap());
	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(
			ContentRecommendationDataPreparationNanite.class);
	}

	private long _getCurrentMonthJobRunsCount(String jobId) {
		return _faroInfoElasticsearchInvoker.count(
			"job-runs",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("job.id", jobId)
			).filter(
				QueryBuilders.termsQuery("status", "COMPLETED", "PUBLISHED")
			).filter(
				QueryBuilders.rangeQuery(
					"completedDate"
				).gte(
					"now/M"
				).lte(
					"now"
				)
			));
	}

	private static final Log _log = LogFactory.getLog(
		ContentRecommendationDataPreparationNanite.class);

	@Autowired
	private DataprocSparkManager _dataprocSparkManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Value("${osb.asah.content.recommendation.max.monthly.job.runs:10}")
	private int _maxMonthlyJobRuns;

}