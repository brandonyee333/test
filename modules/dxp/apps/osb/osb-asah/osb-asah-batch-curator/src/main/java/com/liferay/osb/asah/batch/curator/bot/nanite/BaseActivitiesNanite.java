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

import com.liferay.osb.asah.common.date.DateUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

/**
 * @author Michael Bowerman
 */
public abstract class BaseActivitiesNanite extends BaseNanite {

	public static void setAnalyticsConfigured(boolean analyticsConfigured) {
		_analyticsConfigured = analyticsConfigured;
	}

	public long getInterval() {
		return DateUtil.MINUTE;
	}

	public abstract void run() throws Exception;

	@Override
	public void run(JSONObject contextJSONObject) throws Exception {
		while (true) {
			synchronized (this) {
				if (isActive()) {
					return;
				}

				setActive(true);
			}

			while (_analyticsConfigured) {
				try {
					run();
				}
				catch (Exception e) {
					_log.error(e, e);
				}

				Thread.sleep(getInterval());
			}

			cleanUp();

			setActive(false);

			if (!_analyticsConfigured) {
				break;
			}
		}
	}

	protected abstract void cleanUp() throws Exception;

	protected abstract boolean isActive();

	protected abstract void setActive(boolean running);

	private static final Log _log = LogFactory.getLog(
		BaseIndividualsNanite.class);

	private static boolean _analyticsConfigured;

}