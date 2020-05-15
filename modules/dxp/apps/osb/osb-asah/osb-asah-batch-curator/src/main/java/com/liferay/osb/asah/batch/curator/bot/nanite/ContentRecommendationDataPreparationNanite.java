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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
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
				"--job-execution-id", jobExecutionJSONObject.getString("id"),
				"--job-execution-step",
				jobExecutionJSONObject.getString("step"), "--lcp-project-id",
				ServiceConstants.LCP_PROJECT_ID),
			"content_recommendation.yaml",
			"liferay.content_recommendation.ContentRecommendationApplication");
	}

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private SparkManager _sparkManager;

}