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

import com.liferay.osb.asah.common.dog.SalesforceAuditEventDog;
import com.liferay.osb.asah.common.dog.SalesforceEntityDog;
import com.liferay.osb.asah.common.entity.RunLog;
import com.liferay.osb.asah.common.entity.SalesforceAuditEvent;
import com.liferay.osb.asah.common.entity.SalesforceEntity;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class SalesforceIndividualsNanite extends BaseIndividualsNanite {

	@Override
	protected Log getLog() {
		return LogFactory.getLog(SalesforceIndividualsNanite.class);
	}

	@Override
	protected boolean isInterrupted(Long dataSourceId) {
		return _interruptedMap.getOrDefault(dataSourceId, false);
	}

	@Override
	protected boolean isRunning(Long dataSourceId) {
		return _runningMap.getOrDefault(dataSourceId, false);
	}

	@Override
	protected void processDataSourceAuditEvents(Long dataSourceId)
		throws Exception {

		Log log = getLog();

		if (log.isInfoEnabled()) {
			log.info(
				"Processing audit events for data source ID " + dataSourceId);
		}

		runLogDog.log(
			dataSourceId, this, "STARTED",
			WeDeployDataService.OSB_ASAH_FARO_INFO, "totalOperations",
			_salesforceAuditEventDog.getSalesforceAuditEventsCount(
				dataSourceId, SalesforceEntity.Type.INDIVIDUAL.toString()));

		try {
			int page = 0;

			while (true) {
				if (isInterrupted(dataSourceId)) {
					setInterrupted(dataSourceId, false);

					return;
				}

				List<SalesforceAuditEvent> salesforceAuditEvents =
					_salesforceAuditEventDog.getSalesforceAuditEvents(
						dataSourceId,
						SalesforceEntity.Type.INDIVIDUAL.toString(), page++, 50,
						Sort.desc("id"));

				if (salesforceAuditEvents.isEmpty()) {
					break;
				}

				for (SalesforceAuditEvent salesforceAuditEvent :
						salesforceAuditEvents) {

					processSalesforceAuditEvent(salesforceAuditEvent);
				}
			}

			runLogDog.log(
				dataSourceId, this, "COMPLETED",
				WeDeployDataService.OSB_ASAH_FARO_INFO);
		}
		catch (Exception e) {
			runLogDog.log(
				dataSourceId, this, "FAILED",
				WeDeployDataService.OSB_ASAH_FARO_INFO);

			throw e;
		}
	}

	protected void processSalesforceAuditEvent(
			SalesforceAuditEvent salesforceAuditEvent)
		throws Exception {

		SalesforceAuditEvent.Type salesforceAuditEventType =
			salesforceAuditEvent.getType();

		Log log = getLog();

		if ((salesforceAuditEventType == SalesforceAuditEvent.Type.ADD) ||
			(salesforceAuditEventType == SalesforceAuditEvent.Type.UPDATE)) {

			SalesforceEntity individualSalesforceEntity =
				_salesforceEntityDog.fetchSalesforceEntity(
					salesforceAuditEvent.getDataSourceId(),
					salesforceAuditEvent.getRecordId(),
					SalesforceEntity.Type.INDIVIDUAL);

			if (individualSalesforceEntity != null) {
				JSONObject individualSalesforceEntityFieldsJSONObject =
					individualSalesforceEntity.getFieldsJSONObject();

				processData(
					individualSalesforceEntity.getId(),
					individualSalesforceEntity.getDataSourceId(),
					individualSalesforceEntityFieldsJSONObject,
					individualSalesforceEntityFieldsJSONObject.optString(
						"email", null));
			}
		}
		else if (salesforceAuditEventType == SalesforceAuditEvent.Type.DELETE) {
			JSONObject additionalInfoJSONObject =
				salesforceAuditEvent.getAdditionalInfoJSONObject();

			delete(
				salesforceAuditEvent.getDataSourceId(),
				salesforceAuditEvent.getAuditEventDate(),
				additionalInfoJSONObject.getString("Email"));
		}
		else if (log.isWarnEnabled()) {
			log.warn(
				"Unknown event type " + salesforceAuditEventType +
					" for audit event " + salesforceAuditEvent.getId());
		}

		_salesforceAuditEventDog.deleteSalesforceAuditEvent(
			salesforceAuditEvent);
	}

	@Override
	protected void reprocessUpdateDataSource(Long dataSourceId)
		throws Exception {

		RunLog runLog = runLogDog.log(
			dataSourceId, this, "STARTED",
			WeDeployDataService.OSB_ASAH_FARO_INFO, "processedOperations", 0,
			"reprocess", true);

		try {
			int page = 0;

			while (true) {
				if (isInterrupted(dataSourceId)) {
					setInterrupted(dataSourceId, false);

					return;
				}

				List<SalesforceEntity> individualSalesforceEntities =
					_salesforceEntityDog.getSalesforceEntities(
						dataSourceId, page++, 50,
						SalesforceEntity.Type.INDIVIDUAL);

				if (individualSalesforceEntities.isEmpty()) {
					break;
				}

				for (SalesforceEntity individualSalesforceEntity :
						individualSalesforceEntities) {

					JSONObject fieldsJSONObject =
						individualSalesforceEntity.getFieldsJSONObject();

					processData(
						individualSalesforceEntity.getId(),
						individualSalesforceEntity.getDataSourceId(),
						fieldsJSONObject,
						fieldsJSONObject.optString("email", null));

					JSONObject runLogContextJSONObject =
						runLog.getContextJSONObject();

					int processedOperations =
						runLogContextJSONObject.getInt("processedOperations") +
							1;

					runLogContextJSONObject.put(
						"processedOperations", processedOperations);

					runLogDog.updateRunLogContextJSONObject(
						runLogContextJSONObject, runLog.getId(),
						WeDeployDataService.OSB_ASAH_FARO_INFO);
				}
			}

			runLogDog.log(
				dataSourceId, this, "COMPLETED",
				WeDeployDataService.OSB_ASAH_FARO_INFO, "reprocess", true);
		}
		catch (Exception e) {
			runLogDog.log(
				dataSourceId, this, "STARTED",
				WeDeployDataService.OSB_ASAH_FARO_INFO, "reprocess", true);

			throw e;
		}
	}

	@Override
	protected void setInterrupted(Long dataSourceId, boolean interrupted) {
		_interruptedMap.put(dataSourceId, interrupted);
	}

	@Override
	protected void setRunning(Long dataSourceId, boolean running) {
		_runningMap.put(dataSourceId, running);
	}

	private final Map<Long, Boolean> _interruptedMap = new HashMap<>();
	private final Map<Long, Boolean> _runningMap = new HashMap<>();

	@Autowired
	private SalesforceAuditEventDog _salesforceAuditEventDog;

	@Autowired
	private SalesforceEntityDog _salesforceEntityDog;

}