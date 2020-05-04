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

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class SalesforceIndividualsNanite extends BaseIndividualsNanite {

	@Override
	@PostConstruct
	public void init() {
		super.init();

		_salesforceRawElasticsearchInvoker =
			elasticsearchInvokerFactory.forSalesforceRaw();
	}

	@Override
	protected String getAuditEventDataIdFieldName() {
		return "recordId";
	}

	@Override
	protected String getAuditEventEmail(JSONObject auditEventJSONObject) {
		JSONObject additionalInfoJSONObject =
			auditEventJSONObject.getJSONObject("additionalInfo");

		return additionalInfoJSONObject.getString("Email");
	}

	@Override
	protected String getAuditEventsCollectionName() {
		return "audit-events";
	}

	@Override
	protected QueryBuilder getAuditEventsDataSourceIdQueryBuilder(
		String dataSourceId) {

		return BoolQueryBuilderUtil.filter(
			QueryBuilders.termQuery("osbAsahDataSourceId", dataSourceId)
		).filter(
			QueryBuilders.termQuery("typeName", "individuals")
		);
	}

	@Override
	protected String getDataCollectionName() {
		return "individuals";
	}

	@Override
	protected String getDataIdFieldName() {
		return "id";
	}

	@Override
	protected ElasticsearchInvoker getDataSourceElasticsearchInvoker() {
		return _salesforceRawElasticsearchInvoker;
	}

	@Override
	protected String getDataSourceType() {
		return "SALESFORCE";
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
			dataJSONObject.getString("id"),
			dataJSONObject.getString("osbAsahDataSourceId"), dataJSONObject,
			dataJSONObject.optString("email", null));
	}

	@Override
	protected void reprocessUpdateDataSource(String dataSourceId) {
	}

	protected void setInterrupted(String dataSourceId, boolean interrupted) {
		_interruptedMap.put(dataSourceId, interrupted);
	}

	@Override
	protected void setRunning(String dataSourceId, boolean running) {
		_runningMap.put(dataSourceId, running);
	}

	private final Map<String, Boolean> _interruptedMap = new HashMap<>();
	private final Map<String, Boolean> _runningMap = new HashMap<>();
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}