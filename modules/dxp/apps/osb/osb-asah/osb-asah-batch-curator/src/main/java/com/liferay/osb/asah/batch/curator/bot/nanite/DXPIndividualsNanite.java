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
import com.liferay.osb.asah.common.json.JSONUtil;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class DXPIndividualsNanite extends BaseIndividualsNanite {

	@Override
	@PostConstruct
	public void init() {
		super.init();

		_dxpRawElasticsearchInvoker = elasticsearchInvokerFactory.forDXPRaw();
	}

	@Override
	protected String getAuditEventDataIdFieldName() {
		return "userId";
	}

	@Override
	protected String getAuditEventEmail(JSONObject auditEventJSONObject) {
		JSONObject additionalInfoJSONObject = new JSONObject(
			auditEventJSONObject.getString("additionalInfo"));

		return additionalInfoJSONObject.getString("email");
	}

	@Override
	protected String getAuditEventsCollectionName() {
		return "faro-audit-events";
	}

	@Override
	protected String getDataCollectionName() {
		return "users";
	}

	@Override
	protected String getDataIdFieldName() {
		return "userId";
	}

	@Override
	protected ElasticsearchInvoker getDataSourceElasticsearchInvoker() {
		return _dxpRawElasticsearchInvoker;
	}

	@Override
	protected String getDataSourceType() {
		return "LIFERAY";
	}

	@Override
	protected JSONObject getEmptyDataJSONObject() {
		return JSONUtil.put("contact", new JSONObject());
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

		processData(
			dataJSONObject.optString("uuid", null),
			dataJSONObject.getString("osbAsahDataSourceId"), dataJSONObject,
			dataJSONObject.getString("emailAddress"));
	}

	@Override
	protected void setInterrupted(String dataSourceId, boolean interrupted) {
		_interruptedMap.put(dataSourceId, interrupted);
	}

	@Override
	protected void setRunning(String dataSourceId, boolean running) {
		_runningMap.put(dataSourceId, running);
	}

	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;
	private final Map<String, Boolean> _interruptedMap = new HashMap<>();
	private final Map<String, Boolean> _runningMap = new HashMap<>();

}