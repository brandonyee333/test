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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Objects;

import org.json.JSONObject;

import org.springframework.stereotype.Component;

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
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

}