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

import com.liferay.osb.asah.batch.curator.bot.nanite.BaseActivitiesNanite;
import com.liferay.osb.asah.batch.curator.bot.nanite.Nanite;

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
		boolean force, JSONObject osbAsahTaskJSONObject,
		OSBAsahTaskManager osbAsahTaskManager) {

		_contextJSONObject = osbAsahTaskJSONObject.optJSONObject("context");
		_force = force;

		_naniteClassNames = new String[] {
			osbAsahTaskJSONObject.getString("className")
		};

		_osbAsahTaskId = osbAsahTaskJSONObject.optString("id");
		_osbAsahTaskManager = osbAsahTaskManager;
	}

	public OSBAsahTaskRunnable(
		OSBAsahTaskManager osbAsahTaskManager, String... naniteClassNames) {

		_contextJSONObject = null;
		_force = false;
		_osbAsahTaskManager = osbAsahTaskManager;
		_naniteClassNames = naniteClassNames;
		_osbAsahTaskId = null;
	}

	@Override
	public void run() {
		for (String naniteClassName : _naniteClassNames) {
			Nanite nanite = _osbAsahTaskManager.getNanite(naniteClassName);

			if (nanite == null) {
				_log.error(
					"Unable to get nanite with class name " + naniteClassName);

				continue;
			}

			if (((!_force && (nanite instanceof BaseActivitiesNanite)) ||
				 nanite.isLogRunEnabled()) &&
				_osbAsahTaskManager.checkNanite(naniteClassName)) {

				_osbAsahTaskManager.deleteOSBAsahTask(_osbAsahTaskId);

				continue;
			}

			long start = System.currentTimeMillis();

			try {
				nanite.logStart(_contextJSONObject);

				nanite.run(_contextJSONObject);

				nanite.logCompleted(
					_contextJSONObject, System.currentTimeMillis() - start,
					null);
			}
			catch (Exception e) {
				_log.error(e, e);

				nanite.logFailed(
					_contextJSONObject, System.currentTimeMillis() - start,
					null, e);
			}
			finally {
				_osbAsahTaskManager.deleteOSBAsahTask(_osbAsahTaskId);
			}
		}
	}

	private static final Log _log = LogFactory.getLog(
		OSBAsahTaskRunnable.class);

	private final JSONObject _contextJSONObject;
	private final boolean _force;
	private final String[] _naniteClassNames;
	private final String _osbAsahTaskId;
	private final OSBAsahTaskManager _osbAsahTaskManager;

}