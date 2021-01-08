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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoFieldMappingDog;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class CSVIndividualsNanite extends BaseIndividualsNanite {

	@Override
	protected String getDataCollectionName() {
		return "csv-individuals";
	}

	@Override
	protected ElasticsearchInvoker getDataSourceElasticsearchInvoker() {
		return faroInfoElasticsearchInvoker;
	}

	@Override
	protected String getDataSourceIdFieldName() {
		return "dataSourceId";
	}

	@Override
	protected String getDataSourceType() {
		return "CSV";
	}

	@Override
	protected boolean isInterrupted(String dataSourceId) {
		return _interruptedMap.getOrDefault(dataSourceId, false);
	}

	@Override
	protected boolean isRunning(String dataSourceId) {
		return _runningMap.getOrDefault(dataSourceId, false);
	}

	@Override
	protected void processDataJSONObject(JSONObject dataJSONObject)
		throws Exception {

		JSONObject emailFieldMappingJSONObject =
			_faroInfoFieldMappingDog.fetchFieldMappingJSONObject(
				"demographics", "email", "individual");

		if (emailFieldMappingJSONObject == null) {
			return;
		}

		String dataSourceId = dataJSONObject.getString("dataSourceId");

		JSONObject dataSourceFieldNamesJSONObject =
			emailFieldMappingJSONObject.getJSONObject("dataSourceFieldNames");

		String emailDataSourceFieldName =
			dataSourceFieldNamesJSONObject.optString(dataSourceId, null);

		if (emailDataSourceFieldName == null) {
			return;
		}

		JSONObject fieldsJSONObject = dataJSONObject.getJSONObject("fields");

		processData(
			dataJSONObject.getString("dataSourceIndividualPK"),
			dataJSONObject.getString("dataSourceId"), dataJSONObject,
			fieldsJSONObject.optString(emailDataSourceFieldName, null));
	}

	@Override
	protected void setInterrupted(String dataSourceId, boolean interrupted) {
		_interruptedMap.put(dataSourceId, interrupted);
	}

	@Override
	protected void setRunning(String dataSourceId, boolean running) {
		_runningMap.put(dataSourceId, running);
	}

	@Autowired
	private FaroInfoFieldMappingDog _faroInfoFieldMappingDog;

	private final Map<String, Boolean> _interruptedMap = new HashMap<>();
	private final Map<String, Boolean> _runningMap = new HashMap<>();

}