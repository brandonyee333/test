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

import com.liferay.osb.asah.common.dog.BQCSVUserDog;
import com.liferay.osb.asah.common.dog.FieldMappingDog;
import com.liferay.osb.asah.common.entity.BQCSVUser;
import com.liferay.osb.asah.common.entity.FieldMapping;
import com.liferay.osb.asah.common.entity.RunLog;
import com.liferay.osb.asah.common.model.Sort;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class CSVUsersNanite extends BaseIndividualsNanite {

	@Override
	protected Log getLog() {
		return LogFactory.getLog(CSVUsersNanite.class);
	}

	@Override
	protected boolean isInterrupted(Long dataSourceId) {
		return _interruptedMap.getOrDefault(dataSourceId, false);
	}

	@Override
	protected boolean isRunning(Long dataSourceId) {
		return _runningMap.getOrDefault(dataSourceId, false);
	}

	protected void processBQCSVUser(BQCSVUser bqCSVUser) throws Exception {
		FieldMapping emailFieldMapping = _fieldMappingDog.fetchFieldMapping(
			"demographics", "email", "individual");

		if (emailFieldMapping == null) {
			return;
		}

		Map<String, String> dataSourceFieldNames =
			emailFieldMapping.getDataSourceFieldNames();

		String emailDataSourceFieldName = dataSourceFieldNames.getOrDefault(
			String.valueOf(bqCSVUser.getDataSourceId()), null);

		if (emailDataSourceFieldName == null) {
			return;
		}

		JSONObject fieldsJSONObject = bqCSVUser.getFieldsJSONObject();

		String emailAddress = fieldsJSONObject.optString(
			emailDataSourceFieldName, null);

		processData(
			bqCSVUser.getDataSourceUserPK(), bqCSVUser.getDataSourceId(),
			fieldsJSONObject, emailAddress);

		if (!Objects.isNull(emailAddress)) {
			bqCSVUser.setEmailAddress(emailAddress);

			_bqCSVUserDog.updateBQCSVUser(bqCSVUser);
		}
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

				List<BQCSVUser> bqCSVUsers = _bqCSVUserDog.getBQCSVUsers(
					dataSourceId, page++, 10000, Sort.desc("id"));

				if (bqCSVUsers.isEmpty()) {
					break;
				}

				for (BQCSVUser bqCSVUser : bqCSVUsers) {
					processBQCSVUser(bqCSVUser);

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

				if (bqCSVUsers.size() < 10000) {
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
	private BQCSVUserDog _bqCSVUserDog;

	@Autowired
	private FieldMappingDog _fieldMappingDog;

	private final Map<Long, Boolean> _interruptedMap = new HashMap<>();
	private final Map<Long, Boolean> _runningMap = new HashMap<>();

}