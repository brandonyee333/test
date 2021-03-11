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

package com.liferay.osb.asah.batch.curator.bot.scheduling;

import com.liferay.osb.asah.batch.curator.bot.nanite.IndividualSegmentActivityFieldsNanite;
import com.liferay.osb.asah.batch.curator.bot.nanite.Nanite;
import com.liferay.osb.asah.common.model.AsahTask;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

/**
 * @author Michael Bowerman
 * @author Leslie Wong
 * @author André Miranda
 */
public class OSBAsahTaskRunnable implements Runnable {

	public OSBAsahTaskRunnable(
		AsahTask asahTask, boolean force,
		OSBAsahTaskManager osbAsahTaskManager) {

		_force = force;
		_osbAsahTaskManager = osbAsahTaskManager;

		_asahTaskId = asahTask.getId();
		_contextJSONObject = new JSONObject(asahTask.getContext());
		_naniteClassNames = new String[] {asahTask.getClassName()};

		_projectId = asahTask.getProjectId();

		if (StringUtils.isBlank(_projectId)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Defaulting to project ID " +
						ProjectIdThreadLocal.getProjectId());
			}

			_projectId = ProjectIdThreadLocal.getProjectId();
		}
	}

	public OSBAsahTaskRunnable(
		AsahTask asahTask, OSBAsahTaskManager osbAsahTaskManager) {

		this(asahTask, false, osbAsahTaskManager);
	}

	public OSBAsahTaskRunnable(
		OSBAsahTaskManager osbAsahTaskManager, String projectId,
		String... naniteClassNames) {

		_osbAsahTaskManager = osbAsahTaskManager;
		_projectId = projectId;
		_naniteClassNames = naniteClassNames;

		_contextJSONObject = null;
		_force = false;
		_asahTaskId = null;
	}

	public String[] getNaniteClassNames() {
		return Arrays.copyOf(_naniteClassNames, _naniteClassNames.length);
	}

	public Long getOSBAsahTaskId() {
		return _asahTaskId;
	}

	public String getProjectId() {
		return _projectId;
	}

	public boolean isForce() {
		return _force;
	}

	@Override
	public void run() {
		ProjectIdThreadLocal.forProject(_projectId, this::_run);
	}

	private void _run() {
		for (String naniteClassName : _naniteClassNames) {
			Nanite nanite = _osbAsahTaskManager.getNanite(naniteClassName);

			if (nanite == null) {
				_log.error(
					"Unable to get nanite with class name " + naniteClassName);

				continue;
			}

			if (((!_force &&
				  (nanite instanceof IndividualSegmentActivityFieldsNanite)) ||
				 nanite.isLogRunEnabled()) &&
				_osbAsahTaskManager.checkNanite(naniteClassName)) {

				_osbAsahTaskManager.deleteOSBAsahTask(_asahTaskId);

				continue;
			}

			long start = System.currentTimeMillis();

			try {
				nanite.logStart(_contextJSONObject);

				nanite.run(_contextJSONObject);

				nanite.logCompleted(
					_contextJSONObject, System.currentTimeMillis() - start,
					String.valueOf(_asahTaskId));
			}
			catch (Exception e) {
				_log.error(e, e);

				nanite.logFailed(
					_contextJSONObject, System.currentTimeMillis() - start,
					String.valueOf(_asahTaskId), e);
			}
			finally {
				_osbAsahTaskManager.deleteOSBAsahTask(_asahTaskId);
			}
		}
	}

	private static final Log _log = LogFactory.getLog(
		OSBAsahTaskRunnable.class);

	private final JSONObject _contextJSONObject;
	private final boolean _force;
	private final String[] _naniteClassNames;
	private final Long _asahTaskId;
	private final OSBAsahTaskManager _osbAsahTaskManager;
	private String _projectId;

}