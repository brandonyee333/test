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
public class AsahTaskRunnable implements Runnable {

	public AsahTaskRunnable(
		AsahTask asahTask, AsahTaskManager asahTaskManager) {

		this(asahTask, false, asahTaskManager);
	}

	public AsahTaskRunnable(
		AsahTask asahTask, boolean force, AsahTaskManager asahTaskManager) {

		_force = force;
		_asahTaskManager = asahTaskManager;

		_asahTaskId = asahTask.getId();
		_contextJSONObject = asahTask.getContextJSONObject();
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

	public AsahTaskRunnable(
		AsahTaskManager asahTaskManager, String projectId,
		String... naniteClassNames) {

		_asahTaskManager = asahTaskManager;
		_projectId = projectId;
		_naniteClassNames = naniteClassNames;

		_contextJSONObject = null;
		_force = false;
		_asahTaskId = null;
	}

	public Long getAsahTaskId() {
		return _asahTaskId;
	}

	public String[] getNaniteClassNames() {
		return Arrays.copyOf(_naniteClassNames, _naniteClassNames.length);
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
			Nanite nanite = _asahTaskManager.getNanite(naniteClassName);

			if (nanite == null) {
				_log.error(
					"Unable to get nanite with class name " + naniteClassName);

				continue;
			}

			if (((!_force &&
				  (nanite instanceof IndividualSegmentActivityFieldsNanite)) ||
				 nanite.isLogRunEnabled()) &&
				_asahTaskManager.checkNanite(naniteClassName)) {

				_asahTaskManager.deleteAsahTask(_asahTaskId);

				continue;
			}

			long start = System.currentTimeMillis();

			try {
				nanite.logStart(_contextJSONObject);

				nanite.run(_contextJSONObject);

				nanite.logCompleted(
					String.valueOf(_asahTaskId), _contextJSONObject,
					System.currentTimeMillis() - start);
			}
			catch (Exception e) {
				_log.error(e, e);

				nanite.logFailed(
					String.valueOf(_asahTaskId), _contextJSONObject,
					System.currentTimeMillis() - start, e);
			}
			finally {
				_asahTaskManager.deleteAsahTask(_asahTaskId);
			}
		}
	}

	private static final Log _log = LogFactory.getLog(AsahTaskRunnable.class);

	private final Long _asahTaskId;
	private final AsahTaskManager _asahTaskManager;
	private final JSONObject _contextJSONObject;
	private final boolean _force;
	private final String[] _naniteClassNames;
	private String _projectId;

}