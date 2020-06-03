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

import com.liferay.osb.asah.batch.curator.bot.nanite.ml.SparkManager;
import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Arrays;

import javax.annotation.PostConstruct;

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
public class ContentRecommendationDataPreparationNanite extends BaseNanite {

	@Override
	@PostConstruct
	public void init() {
		super.init();

		_faroInfoElasticsearchInvoker =
			elasticsearchInvokerFactory.forFaroInfo();
	}

	@Override
	public void run(JSONObject jobJSONObject) throws Exception {
		String jobId = jobJSONObject.getString("id");

		if (_getCurrentMonthJobRunsCount(jobId) > _maxMonthlyJobRuns) {
			if (_log.isInfoEnabled()) {
				_log.info(
					String.format(
						"Unable to run job ID %s because this run surpasses " +
							"the maximum allowed monthly runs threshold",
						jobId));
			}

			return;
		}

		String now = DateUtil.newUTCDateString();

		JSONObject jobExecutionJSONObject = _faroInfoElasticsearchInvoker.add(
			"job-executions",
			JSONUtil.put(
				"context", new JSONObject()
			).put(
				"createdDate", now
			).put(
				"job", jobJSONObject
			).put(
				"lastUpdatedDate", now
			).put(
				"status", "RUNNING"
			).put(
				"step", "DATA_PREPARATION"
			));

		_sparkManager.submitJob(
			Arrays.asList(
				"--elasticsearch-hostname",
				ServiceConstants.LCP_ENGINE_ELASTICSEARCH_SERVER_IP,
				"--job-execution-id", jobExecutionJSONObject.getString("id"),
				"--lcp-project-id", ServiceConstants.LCP_PROJECT_ID),
			"content_recommendation.yaml",
			"liferay.content_recommendation.ContentRecommendationApplication");
	}

	private long _getCurrentMonthJobRunsCount(String jobId) {
		return _faroInfoElasticsearchInvoker.count(
			"job-executions",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery("job.id", jobId)
			).filter(
				QueryBuilders.termQuery("status", "COMPLETED")
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

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Value("${osb.asah.content.recommendation.max.monthly.job.runs:10}")
	private int _maxMonthlyJobRuns;

	@Autowired
	private SparkManager _sparkManager;

}