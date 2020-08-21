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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Riccardo Ferrari
 */
@Component
public class CommerceProductRecommendationNanite extends BaseNanite {

	@Override
	@PostConstruct
	public void init() {
		super.init();

		_faroInfoElasticsearchInvoker =
			elasticsearchInvokerFactory.forFaroInfo();
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		JSONObject jobJSONObject = contextJSONObject.getJSONObject("job");

		String dateString = DateUtil.newUTCDateString();

		String jobType = jobJSONObject.getString("type");

		JSONObject jobRunJSONObject = _faroInfoElasticsearchInvoker.add(
			"job-runs",
			JSONUtil.put(
				"context", new JSONObject()
			).put(
				"createdDate", dateString
			).put(
				"job",
				JSONUtil.put(
					"id", jobJSONObject.getString("id")
				).put(
					"type", jobType
				)
			).put(
				"lastUpdatedDate", dateString
			).put(
				"status", "RUNNING"
			));

		_sparkManager.submitJob(
			Arrays.asList(
				"--job-run-id", jobRunJSONObject.getString("id"),
				"--lcp-project-id", ServiceConstants.LCP_PROJECT_ID),
			"commerce_application.yaml",
			_collectJobSparkJars(jobJSONObject.getJSONArray("parameters")),
			_jobTypeApplicationClassNameMap.get(jobType),
			new HashMap<String, String>() {
				{
					put(
						"spark.executor.extraJavaOptions",
						"-XX:ThreadStackSize=8192");
					put(
						"spark.serializer",
						"org.apache.spark.serializer.KryoSerializer");
				}
			});
	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(CommerceProductRecommendationNanite.class);
	}

	private List<String> _collectJobSparkJars(
		JSONArray jobParametersJSONArray) {

		for (int i = 0; i < jobParametersJSONArray.length(); i++) {
			JSONObject jobParameterJSONObject =
				jobParametersJSONArray.getJSONObject(i);

			if (Objects.equals(
					jobParameterJSONObject.getString("name"), "spark:jars")) {

				String value = jobParameterJSONObject.getString("value");

				return Arrays.asList(value.split(","));
			}
		}

		return Collections.emptyList();
	}

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;
	private final Map<String, String> _jobTypeApplicationClassNameMap =
		new HashMap<String, String>() {
			{
				put(
					"COMMERCE_PRODUCT_RECOMMENDATION_PRODUCT_CONTENT",
					"liferay.commerce.recommend." +
						"ProductContentRecommendationApplication");
				put(
					"COMMERCE_PRODUCT_RECOMMENDATION_USER_INTERACTION",
					"liferay.commerce.recommend." +
						"UserInteractionRecommendationApplication");
			}
		};

	@Autowired
	private SparkManager _sparkManager;

}