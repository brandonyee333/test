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

import org.json.JSONObject;

/**
 * @author Brian Wing Shun Chan
 */
public interface Nanite {

	public boolean isLogRunEnabled();

	public void logCompleted(
		String asahTaskId, JSONObject contextJSONObject, long duration);

	public void logFailed(
		String asahTaskId, JSONObject contextJSONObject, long duration,
		Throwable throwable);

	public void logStart(JSONObject contextJSONObject);

	public void run(JSONObject contextJSONObject) throws Exception;

}