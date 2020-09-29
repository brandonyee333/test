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

import com.liferay.osb.asah.batch.curator.bot.nanite.ml.JobState;
import com.liferay.osb.asah.batch.curator.bot.nanite.ml.SparkManager;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Riccardo Ferrari
 */
@Component
public class SparkManagerMonitoringNanite extends BaseNanite {

	@Override
	@PostConstruct
	public void init() {
		super.init();

		_faroInfoElasticsearchInvoker =
			elasticsearchInvokerFactory.forFaroInfo();
	}

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		JSONArray jsonArray = _faroInfoElasticsearchInvoker.get(
			"job-runs",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termsQuery("status", "running", "unknown")));

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"There are %s running job executions", jsonArray.length()));
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jobRunJSONObject = jsonArray.getJSONObject(i);

			_updateJobRun(jobRunJSONObject);
		}
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private void _updateJobRun(JSONObject jobRunJSONObject) {
		String jobRunId = jobRunJSONObject.optString("jobRunId", null);

		if (jobRunId == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					String.format(
						"No jobRunId for job-run id: %s",
						jobRunJSONObject.getString("id")));
			}

			return;
		}

		jobRunJSONObject.put("lastUpdatedDate", DateUtil.newUTCDateString());

		JobState jobState = _sparkManager.getJobState(jobRunId);

		jobRunJSONObject.put("status", jobState.toString());

		_faroInfoElasticsearchInvoker.update("job-runs", jobRunJSONObject);
	}

	private static final Log _log = LogFactory.getLog(
		SparkManagerMonitoringNanite.class);

	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private SparkManager _sparkManager;

}