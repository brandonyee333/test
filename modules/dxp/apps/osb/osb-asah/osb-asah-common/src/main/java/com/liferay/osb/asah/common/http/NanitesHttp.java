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

package com.liferay.osb.asah.common.http;

import com.liferay.osb.asah.common.model.AsahTask;

import org.json.JSONArray;

/**
 * @author Shinn Lok
 */
public interface NanitesHttp {

	public void executeAsahTask(AsahTask asahTask);

	public void refreshAnalytics();

	public void removeSchedule();

	public void rescheduleNanites();

	public void run(JSONArray jsonArray);

	public void scheduleAsahTask(AsahTask asahTask);

	public void unscheduleAsahTask(AsahTask asahTask);

}