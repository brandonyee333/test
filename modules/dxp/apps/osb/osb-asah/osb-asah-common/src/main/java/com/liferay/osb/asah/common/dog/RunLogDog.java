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

package com.liferay.osb.asah.common.dog;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.model.RunLog;
import com.liferay.osb.asah.common.repository.RunLogRepository;
import com.liferay.osb.asah.common.spring.http.exception.OSBAsahException;

import java.util.Date;
import java.util.Optional;

import javax.annotation.Nullable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 */
@Component
public class RunLogDog {

	public RunLog fetchLatestRunLog(
		@Nullable Long dataSourceId, ElasticsearchInvoker elasticsearchInvoker,
		String naniteClassName, @Nullable String status) {

		Optional<RunLog> runLogOptional =
			_runLogRepository.
				findByDataSourceIdAndNaniteClassNameAndStatusOrderByDateLoggedDesc(
					Optional.ofNullable(dataSourceId), naniteClassName,
					Optional.ofNullable(status));

		return runLogOptional.orElse(null);
	}

	public RunLog getRunLog(Long runLogId) {
		Optional<RunLog> runLogOptional = _runLogRepository.findById(runLogId);

		return runLogOptional.orElseThrow(
			() -> new OSBAsahException(
				HttpStatus.BAD_REQUEST,
				"There is no run log with ID " + runLogId));
	}

	public RunLog log(
		Long dataSourceId, Object nanite, boolean overwritePreviousRunLog,
		String status, ElasticsearchInvoker elasticsearchInvoker,
		Object... jsonObjectKeyValuePairs) {

		Class<?> clazz = nanite.getClass();

		String className = clazz.getSimpleName();

		if (!className.endsWith("Nanite")) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unexpected class name " + className);
			}

			return null;
		}

		if ((jsonObjectKeyValuePairs.length % 2) != 0) {
			_log.error("JSON object key value pairs must be an even length");

			return null;
		}

		RunLog existingRunLog = null;

		if (overwritePreviousRunLog) {
			existingRunLog = fetchLatestRunLog(
				dataSourceId, elasticsearchInvoker, className, status);
		}

		RunLog runLog = new RunLog();

		if (existingRunLog != null) {
			runLog.setId(existingRunLog.getId());
		}

		runLog.setDataSourceId(dataSourceId);
		runLog.setDateLogged(new Date());
		runLog.setNaniteClassName(className);
		runLog.setStatus(status);

		JSONObject contextJSONObject = new JSONObject();

		for (int i = 0; i < jsonObjectKeyValuePairs.length; i += 2) {
			contextJSONObject.put(
				String.valueOf(jsonObjectKeyValuePairs[i]),
				jsonObjectKeyValuePairs[i + 1]);
		}

		runLog.setContextJSONObject(contextJSONObject);

		return _runLogRepository.save(runLog);
	}

	public RunLog log(
		Long dataSourceId, Object nanite, String status,
		ElasticsearchInvoker elasticsearchInvoker,
		Object... jsonObjectKeyValuePairs) {

		return log(
			dataSourceId, nanite, true, status, elasticsearchInvoker,
			jsonObjectKeyValuePairs);
	}

	public void updateRunLogContextJSONObject(
		JSONObject contextJSONObject, Long runLogId) {

		RunLog runLog = getRunLog(runLogId);

		runLog.setContextJSONObject(contextJSONObject);

		_runLogRepository.save(runLog);
	}

	private static final Log _log = LogFactory.getLog(RunLogDog.class);

	@Autowired
	private RunLogRepository _runLogRepository;

}