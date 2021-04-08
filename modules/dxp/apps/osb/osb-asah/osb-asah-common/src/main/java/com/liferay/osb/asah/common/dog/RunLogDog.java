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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.SortBuilderUtil;
import com.liferay.osb.asah.common.json.JSONUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortOrder;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class RunLogDog {

	public JSONObject fetchLatestRunLogJSONObject(
		Long dataSourceId, ElasticsearchInvoker elasticsearchInvoker,
		String naniteClassName) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("naniteClassName", naniteClassName));

		if (dataSourceId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery(
					"dataSourceId", String.valueOf(dataSourceId)));
		}
		else {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.existsQuery("dataSourceId")));
		}

		JSONArray runLogsJSONArray = new JSONArray(
			elasticsearchInvoker.get(
				"run-logs",
				searchSourceBuilder -> {
					searchSourceBuilder.query(boolQueryBuilder);
					searchSourceBuilder.size(1);
					searchSourceBuilder.sort(
						SortBuilderUtil.fieldSort(
							"dateLogged", SortOrder.DESC));
				}));

		if (runLogsJSONArray.length() == 0) {
			return null;
		}

		return runLogsJSONArray.getJSONObject(0);
	}

	public JSONObject log(
		String dataSourceId, Object nanite, boolean overwritePreviousRunLog,
		String status, ElasticsearchInvoker elasticsearchInvoker,
		Object... jsonObjectKeyValuePairs) {

		Class<?> clazz = nanite.getClass();

		String className = clazz.getSimpleName();

		if (!className.endsWith("Nanite")) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unexpected class name " + className);
			}

			return null;
		}

		if ((jsonObjectKeyValuePairs.length % 2) != 0) {
			_log.error("JSON object key value pairs must be an even length");

			return null;
		}

		try {
			JSONObject existingRunLogJSONObject = null;

			if (overwritePreviousRunLog) {
				existingRunLogJSONObject = _fetchRunLogJSONObject(
					dataSourceId, elasticsearchInvoker, className, status);
			}

			JSONObject runLogJSONObject = JSONUtil.put(
				"dataSourceId", dataSourceId
			).put(
				"dateLogged", DateUtil.newDateString()
			).put(
				"naniteClassName", className
			).put(
				"status", status
			);

			for (int i = 0; i < jsonObjectKeyValuePairs.length; i += 2) {
				runLogJSONObject.put(
					String.valueOf(jsonObjectKeyValuePairs[i]),
					jsonObjectKeyValuePairs[i + 1]);
			}

			if (existingRunLogJSONObject == null) {
				return elasticsearchInvoker.add("run-logs", runLogJSONObject);
			}

			return elasticsearchInvoker.replace(
				"run-logs",
				runLogJSONObject.put(
					"id", existingRunLogJSONObject.getString("id")));
		}
		catch (Exception e) {
			_log.error("Unable to add run log for " + className, e);
		}

		return null;
	}

	public JSONObject log(
		String dataSourceId, Object nanite, String status,
		ElasticsearchInvoker elasticsearchInvoker,
		Object... jsonObjectKeyValuePairs) {

		return log(
			dataSourceId, nanite, true, status, elasticsearchInvoker,
			jsonObjectKeyValuePairs);
	}

	private JSONObject _fetchRunLogJSONObject(
		String dataSourceId, ElasticsearchInvoker elasticsearchInvoker,
		String naniteClassName, String status) {

		BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("naniteClassName", naniteClassName)
		).filter(
			QueryBuilders.termQuery("status", status)
		);

		if (dataSourceId != null) {
			boolQueryBuilder.filter(
				QueryBuilders.termQuery("dataSourceId", dataSourceId));
		}
		else {
			boolQueryBuilder.filter(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.existsQuery("dataSourceId")));
		}

		return elasticsearchInvoker.fetch("run-logs", boolQueryBuilder);
	}

	private static final Log _log = LogFactory.getLog(RunLogDog.class);

}