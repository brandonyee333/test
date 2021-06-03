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

import com.liferay.osb.asah.common.dog.CSVIndividualDog;
import com.liferay.osb.asah.common.dog.FieldMappingDog;
import com.liferay.osb.asah.common.entity.CSVIndividual;
import com.liferay.osb.asah.common.entity.FieldMapping;
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
public class CSVIndividualsNanite extends BaseIndividualsNanite {

	@Override
	protected Log getLog() {
		return LogFactory.getLog(CSVIndividualsNanite.class);
	}

	@Override
	protected boolean isInterrupted(Long dataSourceId) {
		return _interruptedMap.getOrDefault(dataSourceId, false);
	}

	@Override
	protected boolean isRunning(Long dataSourceId) {
		return _runningMap.getOrDefault(dataSourceId, false);
	}

	protected void processCSVIndividual(CSVIndividual csvIndividual)
		throws Exception {

		FieldMapping emailFieldMapping = _fieldMappingDog.fetchFieldMapping(
			"demographics", "email", "individual");

		if (emailFieldMapping == null) {
			return;
		}

		Map<String, String> dataSourceFieldNames =
			emailFieldMapping.getDataSourceFieldNames();

		String emailDataSourceFieldName = dataSourceFieldNames.getOrDefault(
			String.valueOf(csvIndividual.getDataSourceId()), null);

		if (emailDataSourceFieldName == null) {
			return;
		}

		JSONObject fieldsJSONObject = csvIndividual.getFieldsJSONObject();

		processData(
			csvIndividual.getDataSourceIndividualPK(),
			csvIndividual.getDataSourceId(), fieldsJSONObject,
			fieldsJSONObject.optString(emailDataSourceFieldName, null));
	}

	@Override
	protected void processDataSourceAuditEvents(Long dataSourceId) {
		throw new UnsupportedOperationException();
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

				List<CSVIndividual> csvIndividuals =
					_csvIndividualDog.getCSVIndividuals(
						dataSourceId, page++, 50, Sort.desc("id"));

				if (csvIndividuals.isEmpty()) {
					break;
				}

				for (CSVIndividual csvIndividual : csvIndividuals) {
					processCSVIndividual(csvIndividual);

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
	private CSVIndividualDog _csvIndividualDog;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	private final Map<Long, Boolean> _interruptedMap = new HashMap<>();
	private final Map<Long, Boolean> _runningMap = new HashMap<>();

}