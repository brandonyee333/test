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
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoAccountDog;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.run.logger.RunLogger;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author Rachael Koestartyo
 */
@Component
public class SalesforceAccountsNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		processDataSourceAuditEvents(
			contextJSONObject.getString("dataSourceId"));
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	protected void processAuditEventJSONObject(JSONObject auditEventJSONObject)
		throws Exception {

		String eventType = auditEventJSONObject.getString("eventType");

		if (eventType.equals("ADD") || eventType.equals("UPDATE")) {
			JSONObject salesforceAccountJSONObject =
				_salesforceRawElasticsearchInvoker.fetch(
					"Account",
					BoolQueryBuilderUtil.filter(
						QueryBuilders.termQuery(
							"osbAsahDataSourceId",
							auditEventJSONObject.getString(
								"osbAsahDataSourceId"))
					).filter(
						QueryBuilders.termQuery(
							"id", auditEventJSONObject.getString("recordId"))
					));

			if (salesforceAccountJSONObject != null) {
				processSalesforceAccount(salesforceAccountJSONObject);
			}
		}
		else if (eventType.equals("DELETE")) {
			JSONObject accountJSONObject = faroInfoElasticsearchInvoker.fetch(
				"accounts",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						"accountPK", auditEventJSONObject.getString("recordId"))
				).filter(
					QueryBuilders.termQuery(
						"dataSourceId",
						auditEventJSONObject.getString("osbAsahDataSourceId"))
				));

			if (accountJSONObject != null) {
				_faroInfoAccountDog.deleteAccount(accountJSONObject);
			}
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"Unknown event type " + eventType + " for audit event " +
					auditEventJSONObject.getString("id"));
		}

		_salesforceRawElasticsearchInvoker.delete(
			"audit-events", auditEventJSONObject.getString("id"));
	}

	protected void processDataSourceAuditEvents(String dataSourceId)
		throws Exception {

		_runLogger.log(
			dataSourceId, this, "STARTED", faroInfoElasticsearchInvoker,
			"totalOperations",
			_salesforceRawElasticsearchInvoker.count(
				"audit-events",
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("osbAsahDataSourceId", dataSourceId)
				).filter(
					QueryBuilders.termQuery("typeName", "Account")
				)));

		try {
			JSONArrayIterator.of(
				"audit-events", _salesforceRawElasticsearchInvoker,
				auditEventJSONObject -> {
					try {
						processAuditEventJSONObject(auditEventJSONObject);
					}
					catch (Exception e) {
						return e;
					}

					return null;
				}
			).setMonitoringConsumers(
				this::monitorProcessedCount, this::monitorQueueSize
			).setQueryBuilder(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery("osbAsahDataSourceId", dataSourceId)
				).filter(
					QueryBuilders.termQuery("typeName", "Account")
				)
			).iterate();

			_runLogger.log(
				dataSourceId, this, "COMPLETED", faroInfoElasticsearchInvoker);
		}
		catch (Exception e) {
			_runLogger.log(
				dataSourceId, this, "FAILED", faroInfoElasticsearchInvoker);

			throw e;
		}
	}

	protected void processSalesforceAccount(
			JSONObject salesforceAccountJSONObject)
		throws Exception {

		String osbAsahDataSourceId = salesforceAccountJSONObject.getString(
			"osbAsahDataSourceId");

		JSONObject dataSourceJSONObject =
			_faroInfoDataSourceDog.getDataSourceJSONObject(osbAsahDataSourceId);

		if (dataSourceJSONObject == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get data source " + osbAsahDataSourceId);
			}

			return;
		}

		JSONObject accountJSONObject = faroInfoElasticsearchInvoker.fetch(
			"accounts",
			BoolQueryBuilderUtil.filter(
				QueryBuilders.termQuery(
					"accountPK", salesforceAccountJSONObject.getString("id"))
			).filter(
				QueryBuilders.termQuery(
					"dataSourceId", dataSourceJSONObject.getString("id"))
			));

		if (accountJSONObject == null) {
			_faroInfoAccountDog.addAccount(
				salesforceAccountJSONObject, dataSourceJSONObject);
		}
		else {
			_faroInfoAccountDog.updateAccount(
				accountJSONObject, salesforceAccountJSONObject,
				dataSourceJSONObject);
		}
	}

	private static final Log _log = LogFactory.getLog(
		SalesforceAccountsNanite.class);

	@Autowired
	private FaroInfoAccountDog _faroInfoAccountDog;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

	@Autowired
	private RunLogger _runLogger;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}