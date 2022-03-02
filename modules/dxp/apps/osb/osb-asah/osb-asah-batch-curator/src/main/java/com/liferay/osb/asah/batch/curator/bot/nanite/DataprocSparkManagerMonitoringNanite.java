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

import com.liferay.osb.asah.batch.curator.bot.nanite.dataproc.DataprocJobState;
import com.liferay.osb.asah.batch.curator.bot.nanite.dataproc.DataprocSparkManager;
import com.liferay.osb.asah.common.dog.JobRunDog;
import com.liferay.osb.asah.common.entity.JobRun;
import com.liferay.osb.asah.common.model.JobRunStatus;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Riccardo Ferrari
 */
@Component
@ConditionalOnGoogleApplicationCredentials
public class DataprocSparkManagerMonitoringNanite extends BaseNanite {

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		List<JobRun> jobRuns = _jobRunDog.getJobRuns(
			Arrays.asList(
				JobRunStatus.RUNNING.name(), JobRunStatus.UNKNOWN.name()));

		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"There are %s running job executions", jobRuns.size()));
		}

		for (JobRun jobRun : jobRuns) {
			_updateJobRun(jobRun);
		}
	}

	@Override
	protected Log getLog() {
		return _log;
	}

	private void _updateJobRun(JobRun jobRun) {
		JSONObject contextJSONObject = jobRun.getContextJSONObject();

		String sparkJobId = contextJSONObject.optString("sparkJobId", null);

		if (sparkJobId == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("No sparkJobId for job-run ID" + jobRun.getId());
			}

			return;
		}

		jobRun.setModifiedLocalDateTime(LocalDateTime.now(ZoneOffset.UTC));

		DataprocJobState dataprocJobState = _dataprocSparkManager.getJobState(
			sparkJobId);

		jobRun.setJobRunStatus(JobRunStatus.valueOf(dataprocJobState.name()));

		_jobRunDog.updateJobRun(jobRun);
	}

	private static final Log _log = LogFactory.getLog(
		DataprocSparkManagerMonitoringNanite.class);

	@Autowired
	private DataprocSparkManager _dataprocSparkManager;

	@Autowired
	private JobRunDog _jobRunDog;

}