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

import com.google.api.client.util.Objects;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.dog.SuppressionDog;
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoIndividualDog;
import com.liferay.osb.asah.common.faro.info.util.FaroInfoIndividualUtil;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.dog.RunLogger;

import java.text.ParseException;

import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Shinn Lok
 */
public abstract class BaseIndividualsNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		String dataSourceId = contextJSONObject.getString("dataSourceId");
		String type = contextJSONObject.getString("type");

		if (type.equals("audit-events")) {
			while (isRunning(dataSourceId)) {
			}

			setRunning(dataSourceId, true);

			processDataSourceAuditEvents(dataSourceId);

			setRunning(dataSourceId, false);
		}
		else if (type.equals("reprocess")) {
			synchronized (this) {
				if (isRunning(dataSourceId)) {
					setInterrupted(dataSourceId, true);

					while (isRunning(dataSourceId)) {
					}
				}

				setRunning(dataSourceId, true);
			}

			reprocessUpdateDataSource(dataSourceId);

			setRunning(dataSourceId, false);
		}
	}

	protected void delete(
			String dataSourceId, Date deletionDate, String emailAddress)
		throws Exception {

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals",
			QueryBuilders.termQuery("demographics.email.value", emailAddress));

		if (individualJSONObject == null) {
			return;
		}

		JSONArray dataSourceIndividualPKsJSONArray =
			individualJSONObject.getJSONArray("dataSourceIndividualPKs");

		if (dataSourceIndividualPKsJSONArray.length() == 1) {
			JSONArray individualPKsJSONArray =
				FaroInfoIndividualUtil.getIndividualPKsJSONArray(
					dataSourceId, dataSourceIndividualPKsJSONArray);

			if (individualPKsJSONArray.length() > 0) {
				_faroInfoIndividualDog.deleteIndividual(
					deletionDate, individualJSONObject.getString("id"));
			}

			return;
		}

		_faroInfoIndividualDog.removeDataSourceIndividualPKs(
			individualJSONObject, Long.valueOf(dataSourceId));

		_faroInfoIndividualDog.updateIndividual(
			null, getEmptyDataJSONObject(),
			_dataSourceDog.getDataSource(Long.valueOf(dataSourceId)),
			individualJSONObject);
	}

	protected String getAuditEventDataIdFieldName() {
		return null;
	}

	protected Date getAuditEventDate(JSONObject auditEventJSONObject) {
		try {
			return DateUtil.toUTCDate(
				auditEventJSONObject.getString("dateCreated"));
		}
		catch (ParseException pe) {
			return null;
		}
	}

	protected String getAuditEventEmail(JSONObject auditEventJSONObject) {
		return null;
	}

	protected String getAuditEventsCollectionName() {
		return null;
	}

	protected QueryBuilder getAuditEventsDataSourceIdQueryBuilder(
		String dataSourceId) {

		return QueryBuilders.termQuery(
			getDataSourceIdFieldName(), dataSourceId);
	}

	protected abstract String getDataCollectionName();

	protected String getDataIdFieldName() {
		return null;
	}

	protected abstract ElasticsearchInvoker getDataSourceElasticsearchInvoker();

	protected String getDataSourceIdFieldName() {
		return "osbAsahDataSourceId";
	}

	protected abstract String getDataSourceType();

	protected JSONObject getEmptyDataJSONObject() {
		return new JSONObject();
	}

	protected abstract boolean isInterrupted(String dataSourceId);

	protected abstract boolean isRunning(String dataSourceId);

	protected void processAuditEventJSONObject(JSONObject auditEventJSONObject)
		throws Exception {

		ElasticsearchInvoker dataSourceElasticsearchInvoker =
			getDataSourceElasticsearchInvoker();

		String dataSourceIdFieldName = getDataSourceIdFieldName();

		String eventType = auditEventJSONObject.getString("eventType");

		Log log = getLog();

		if (eventType.equals("ADD") || eventType.equals("UPDATE")) {
			JSONObject dataJSONObject = dataSourceElasticsearchInvoker.fetch(
				getDataCollectionName(),
				BoolQueryBuilderUtil.filter(
					QueryBuilders.termQuery(
						dataSourceIdFieldName,
						auditEventJSONObject.getString(dataSourceIdFieldName))
				).filter(
					QueryBuilders.termQuery(
						getDataIdFieldName(),
						auditEventJSONObject.get(
							getAuditEventDataIdFieldName()))
				));

			if (dataJSONObject != null) {
				processDataJSONObject(dataJSONObject);
			}
		}
		else if (eventType.equals("DELETE")) {
			delete(
				auditEventJSONObject.getString(dataSourceIdFieldName),
				getAuditEventDate(auditEventJSONObject),
				getAuditEventEmail(auditEventJSONObject));
		}
		else if (log.isWarnEnabled()) {
			log.warn(
				"Unknown event type " + eventType + " for audit event " +
					auditEventJSONObject.getString("id"));
		}

		dataSourceElasticsearchInvoker.delete(
			getAuditEventsCollectionName(),
			auditEventJSONObject.getString("id"));
	}

	protected void processData(
			String dataId, String dataSourceId, JSONObject dataJSONObject,
			String emailAddress)
		throws Exception {

		if ((emailAddress == null) ||
			_suppressionDog.isSuppressed(emailAddress, null)) {

			return;
		}

		DataSource dataSource = _dataSourceDog.getDataSource(
			Long.valueOf(dataSourceId));

		if (Objects.equal(dataSource.getState(), "IN_PROGRESS_DELETING")) {
			Log log = getLog();

			if (log.isWarnEnabled()) {
				log.warn("Unable to get data source " + dataSourceId);
			}

			return;
		}

		emailAddress = StringUtils.lowerCase(emailAddress);

		JSONObject individualJSONObject = faroInfoElasticsearchInvoker.fetch(
			"individuals",
			BoolQueryBuilderUtil.should(
				QueryBuilders.termQuery(
					"demographics.email.value", emailAddress)
			).should(
				QueryBuilders.termQuery(
					"emailAddressHashed", DigestUtils.sha256Hex(emailAddress))
			));

		if (individualJSONObject == null) {
			_faroInfoIndividualDog.addIndividual(
				dataId, dataJSONObject, dataSource);
		}
		else {
			_faroInfoIndividualDog.updateIndividual(
				dataId, dataJSONObject, dataSource, individualJSONObject);
		}
	}

	protected abstract void processDataJSONObject(JSONObject dataJSONObject)
		throws Exception;

	protected void processDataSourceAuditEvents(String dataSourceId)
		throws Exception {

		Log log = getLog();

		if (log.isInfoEnabled()) {
			log.info(
				"Processing audit events for data source ID " + dataSourceId);
		}

		ElasticsearchInvoker dataSourceElasticsearchInvoker =
			getDataSourceElasticsearchInvoker();

		_runLogger.log(
			dataSourceId, this, "STARTED", faroInfoElasticsearchInvoker,
			"totalOperations",
			dataSourceElasticsearchInvoker.count(
				getAuditEventsCollectionName(),
				getAuditEventsDataSourceIdQueryBuilder(dataSourceId)));

		try {
			JSONArrayIterator.of(
				getAuditEventsCollectionName(),
				getDataSourceElasticsearchInvoker(),
				auditEventJSONObject -> {
					try {
						if (isInterrupted(dataSourceId)) {
							setInterrupted(dataSourceId, false);

							return JSONArrayIterator.INTERRUPT;
						}

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
				getAuditEventsDataSourceIdQueryBuilder(dataSourceId)
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

	protected void reprocessUpdateDataSource(String dataSourceId)
		throws Exception {

		JSONObject runLogJSONObject = _runLogger.log(
			dataSourceId, this, "STARTED", faroInfoElasticsearchInvoker,
			"processedOperations", 0, "reprocess", true);

		String runLogId = runLogJSONObject.getString("id");

		try {
			JSONArrayIterator.of(
				getDataCollectionName(), getDataSourceElasticsearchInvoker(),
				dataJSONObject -> {
					try {
						if (isInterrupted(dataSourceId)) {
							setInterrupted(dataSourceId, false);

							return JSONArrayIterator.INTERRUPT;
						}

						processDataJSONObject(dataJSONObject);

						int processedOperations =
							runLogJSONObject.getInt("processedOperations") + 1;

						runLogJSONObject.put(
							"processedOperations", processedOperations);

						faroInfoElasticsearchInvoker.update(
							"run-logs", runLogId,
							JSONUtil.put(
								"processedOperations", processedOperations));
					}
					catch (Exception e) {
						return e;
					}

					return null;
				}
			).setMonitoringConsumers(
				this::monitorProcessedCount, this::monitorQueueSize
			).setQueryBuilder(
				QueryBuilders.termQuery(
					getDataSourceIdFieldName(), dataSourceId)
			).iterate();

			_runLogger.log(
				dataSourceId, this, "COMPLETED", faroInfoElasticsearchInvoker,
				"reprocess", true);
		}
		catch (Exception e) {
			_runLogger.log(
				dataSourceId, this, "STARTED", faroInfoElasticsearchInvoker,
				"reprocess", true);

			throw e;
		}
	}

	protected abstract void setInterrupted(
		String dataSourceId, boolean interrupted);

	protected abstract void setRunning(String dataSourceId, boolean running);

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private FaroInfoIndividualDog _faroInfoIndividualDog;

	@Autowired
	private RunLogger _runLogger;

	@Autowired
	private SuppressionDog _suppressionDog;

}