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
import com.liferay.osb.asah.common.dog.JobRunDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.Job;
import com.liferay.osb.asah.common.model.JobParameter;
import com.liferay.osb.asah.common.model.JobRunStatus;
import com.liferay.osb.asah.common.model.JobType;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Riccardo Ferrari
 */
@Component
@ConditionalOnGoogleApplicationCredentials
public class CommerceProductRecommendationNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		Job job = _objectMapper.convertValue(
			contextJSONObject.getJSONObject("job"), Job.class);

		String sparkJobId = _dataprocSparkManager.submitJob(
			Arrays.asList(
				"--lcp-project-id", ProjectIdThreadLocal.getProjectId()),
			"commerce_application.yaml",
			_collectJobSparkJars(job.getJobParameters()),
			_jobTypeApplicationClassNameMap.get(job.getJobType()),
			_collectJobParameters(job.getJobParameters()));

		_jobRunDog.addJobRun(
			JSONUtil.put("sparkJobId", sparkJobId), job, JobRunStatus.UNKNOWN,
			null, null);
	}

	@Override
	protected Log getLog() {
		return LogFactory.getLog(CommerceProductRecommendationNanite.class);
	}

	private Map<String, String> _collectJobParameters(
		Set<JobParameter> jobParameters) {

		Map<String, String> jobParametersMap = new HashMap<>();

		jobParametersMap.put(
			"spark.executor.extraJavaOptions", "-XX:ThreadStackSize=8192");
		jobParametersMap.put(
			"spark.serializer", "org.apache.spark.serializer.KryoSerializer");

		for (JobParameter jobParameter : jobParameters) {
			if (Objects.equals(jobParameter.getName(), "spark:jars")) {
				continue;
			}

			jobParametersMap.put(
				jobParameter.getName(), jobParameter.getValue());
		}

		return jobParametersMap;
	}

	private List<String> _collectJobSparkJars(Set<JobParameter> jobParameters) {
		for (JobParameter jobParameter : jobParameters) {
			if (Objects.equals(jobParameter.getName(), "spark:jars")) {
				String value = jobParameter.getValue();

				return Arrays.asList(value.split(","));
			}
		}

		return Collections.emptyList();
	}

	@Autowired
	private DataprocSparkManager _dataprocSparkManager;

	@Autowired
	private JobRunDog _jobRunDog;

	private final Map<JobType, String> _jobTypeApplicationClassNameMap =
		new HashMap<JobType, String>() {
			{
				put(
					JobType.COMMERCE_PRODUCT_RECOMMENDATION_FREQUENT_PATTERN,
					"liferay.commerce.recommend." +
						"FrequentPatternRecommendationApplication");
				put(
					JobType.COMMERCE_PRODUCT_RECOMMENDATION_PRODUCT_CONTENT,
					"liferay.commerce.recommend." +
						"ProductContentRecommendationApplication");
				put(
					JobType.COMMERCE_PRODUCT_RECOMMENDATION_USER_INTERACTION,
					"liferay.commerce.recommend." +
						"UserInteractionRecommendationApplication");
				put(
					JobType.COMMERCE_REVENUE_FORECAST_ACCOUNT,
					"liferay.commerce.forecast.AccountForecastApplication");
				put(
					JobType.COMMERCE_REVENUE_FORECAST_ACCOUNT_CATEGORY,
					"liferay.commerce.forecast." +
						"AccountCategoryForecastApplication");
			}
		};

	@Autowired
	private ObjectMapper _objectMapper;

}