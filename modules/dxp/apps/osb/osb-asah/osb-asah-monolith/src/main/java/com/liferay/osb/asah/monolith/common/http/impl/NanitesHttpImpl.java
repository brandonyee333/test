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

package com.liferay.osb.asah.monolith.common.http.impl;

import com.liferay.osb.asah.batch.curator.rest.controller.NanitesRestController;
import com.liferay.osb.asah.common.http.NanitesHttp;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
@Primary
public class NanitesHttpImpl implements NanitesHttp {

	@Override
	public void executeAsahTask(Long asahTaskId) {
		_nanitesRestController.post(asahTaskId);
	}

	@Override
	public void refreshAnalytics() {
		_nanitesRestController.refreshAnalytics();
	}

	@Override
	public void removeSchedule() {
		_nanitesRestController.removeSchedule();
	}

	@Override
	public void rescheduleNanites() {
		_nanitesRestController.reschedule();
	}

	@Override
	public void run(JSONArray jsonArray) {
		_nanitesRestController.run(jsonArray.toString());
	}

	@Override
	public void scheduleAsahTask(Long asahTaskId) {
		_nanitesRestController.schedule(asahTaskId);
	}

	@Override
	public void unscheduleAsahTask(Long asahTaskId) {
		_nanitesRestController.unschedule(asahTaskId);
	}

	@Autowired
	private NanitesRestController _nanitesRestController;

}