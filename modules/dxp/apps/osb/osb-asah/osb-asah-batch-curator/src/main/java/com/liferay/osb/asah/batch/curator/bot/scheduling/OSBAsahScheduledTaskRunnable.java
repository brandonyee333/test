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

import com.liferay.osb.asah.batch.curator.bot.nanite.Nanite;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

/**
 * @author Marcellus Tavares
 * @author André Miranda
 */
public class OSBAsahScheduledTaskRunnable implements Runnable {

	public OSBAsahScheduledTaskRunnable(
		Nanite nanite, JSONObject osbAsahTaskJSONObject,
		OSBAsahTaskManager osbAsahTaskManager) {

		_nanite = nanite;
		_osbAsahTaskJSONObject = osbAsahTaskJSONObject;
		_osbAsahTaskManager = osbAsahTaskManager;
	}

	@Override
	public void run() {
		Class<?> clazz = _nanite.getClass();

		if (_nanite.isLogRunEnabled() &&
			_osbAsahTaskManager.checkNanite(clazz.getSimpleName())) {

			return;
		}

		String osbAsahTaskId = _osbAsahTaskJSONObject.getString("id");
		long start = System.currentTimeMillis();

		try {
			JSONObject contextJSONObject = _osbAsahTaskJSONObject.optJSONObject(
				"context");

			_nanite.run(contextJSONObject);

			_nanite.logCompleted(
				contextJSONObject, System.currentTimeMillis() - start,
				osbAsahTaskId);
		}
		catch (Exception e) {
			_log.error(e, e);

			_nanite.logFailed(
				_osbAsahTaskJSONObject.optJSONObject("context"),
				System.currentTimeMillis() - start, osbAsahTaskId, e);
		}
	}

	private static final Log _log = LogFactory.getLog(
		OSBAsahScheduledTaskRunnable.class);

	private final Nanite _nanite;
	private final JSONObject _osbAsahTaskJSONObject;
	private final OSBAsahTaskManager _osbAsahTaskManager;

}