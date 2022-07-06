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

import com.liferay.osb.asah.common.dog.BQSalesforceAuditEventDog;
import com.liferay.osb.asah.common.dog.BQSalesforceEntityDog;
import com.liferay.osb.asah.common.entity.BQSalesforceAuditEvent;
import com.liferay.osb.asah.common.entity.BQSalesforceEntity;
import com.liferay.osb.asah.common.entity.RunLog;
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

		if (log.isDebugEnabled()) {
			log.debug(
				"Processing audit events for data source ID " + dataSourceId);
		}

		runLogDog.log(
			dataSourceId, this, "STARTED",
			WeDeployDataService.OSB_ASAH_FARO_INFO, "totalOperations",
			_bqSalesforceAuditEventDog.getBQSalesforceAuditEventsCount(
				dataSourceId, BQSalesforceEntity.Type.INDIVIDUAL.getValue()));

		try {
			int page = 0;

			while (true) {
				if (isInterrupted(dataSourceId)) {
					setInterrupted(dataSourceId, false);

					return;
				}

				List<BQSalesforceAuditEvent> bqSalesforceAuditEvents =
					_bqSalesforceAuditEventDog.getBQSalesforceAuditEvents(
						dataSourceId,
						BQSalesforceEntity.Type.INDIVIDUAL.getValue(), page++,
						10000, Sort.desc("id"));

				if (bqSalesforceAuditEvents.isEmpty()) {
					break;
				}

				for (BQSalesforceAuditEvent bqSalesforceAuditEvent :
						bqSalesforceAuditEvents) {

					processSalesforceAuditEvent(bqSalesforceAuditEvent);
				}

				if (bqSalesforceAuditEvents.size() < 10000) {
					break;
				}
			}

			runLogDog.log(
				dataSourceId, this, "COMPLETED",
				WeDeployDataService.OSB_ASAH_FARO_INFO);
		}
		catch (Exception exception) {
			runLogDog.log(
				dataSourceId, this, "FAILED",
				WeDeployDataService.OSB_ASAH_FARO_INFO);

			throw exception;
		}
	}

	protected void processSalesforceAuditEvent(
			BQSalesforceAuditEvent bqSalesforceAuditEvent)
		throws Exception {

		BQSalesforceAuditEvent.Type bqSalesforceAuditEventType =
			bqSalesforceAuditEvent.getType();

		Log log = getLog();

		if ((bqSalesforceAuditEventType == BQSalesforceAuditEvent.Type.ADD) ||
			(bqSalesforceAuditEventType ==
				BQSalesforceAuditEvent.Type.UPDATE)) {

			BQSalesforceEntity individualBQSalesforceEntity =
				_bqSalesforceEntityDog.fetchBQSalesforceEntity(
					bqSalesforceAuditEvent.getDataSourceId(),
					bqSalesforceAuditEvent.getRecordId(),
					BQSalesforceEntity.Type.INDIVIDUAL);

			if (individualBQSalesforceEntity != null) {
				JSONObject individualSalesforceEntityFieldsJSONObject =
					individualBQSalesforceEntity.getFieldsJSONObject();

				processData(
					individualBQSalesforceEntity.getId(),
					individualBQSalesforceEntity.getDataSourceId(),
					individualSalesforceEntityFieldsJSONObject,
					individualSalesforceEntityFieldsJSONObject.optString(
						"email", null));
			}
		}
		else if (bqSalesforceAuditEventType ==
					BQSalesforceAuditEvent.Type.DELETE) {

			JSONObject additionalInfoJSONObject =
				bqSalesforceAuditEvent.getAdditionalInfoJSONObject();

			delete(
				bqSalesforceAuditEvent.getDataSourceId(),
				bqSalesforceAuditEvent.getCreateDate(),
				additionalInfoJSONObject.getString("Email"));
		}
		else if (log.isWarnEnabled()) {
			log.warn(
				"Unknown event type " + bqSalesforceAuditEventType +
					" for audit event " + bqSalesforceAuditEvent.getId());
		}

		_bqSalesforceAuditEventDog.deleteBQSalesforceAuditEvent(
			bqSalesforceAuditEvent);
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

				List<BQSalesforceEntity> individualBQSalesforceEntities =
					_bqSalesforceEntityDog.getBQSalesforceEntities(
						dataSourceId, page++, 10000,
						BQSalesforceEntity.Type.INDIVIDUAL);

				if (individualBQSalesforceEntities.isEmpty()) {
					break;
				}

				for (BQSalesforceEntity individualBQSalesforceEntity :
						individualBQSalesforceEntities) {

					JSONObject fieldsJSONObject =
						individualBQSalesforceEntity.getFieldsJSONObject();

					processData(
						individualBQSalesforceEntity.getId(),
						individualBQSalesforceEntity.getDataSourceId(),
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

				if (individualBQSalesforceEntities.size() < 10000) {
					break;
				}
			}

			runLogDog.log(
				dataSourceId, this, "COMPLETED",
				WeDeployDataService.OSB_ASAH_FARO_INFO, "reprocess", true);
		}
		catch (Exception exception) {
			runLogDog.log(
				dataSourceId, this, "STARTED",
				WeDeployDataService.OSB_ASAH_FARO_INFO, "reprocess", true);

			throw exception;
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

	@Autowired
	private BQSalesforceAuditEventDog _bqSalesforceAuditEventDog;

	@Autowired
	private BQSalesforceEntityDog _bqSalesforceEntityDog;

	private final Map<Long, Boolean> _interruptedMap = new HashMap<>();
	private final Map<Long, Boolean> _runningMap = new HashMap<>();

}