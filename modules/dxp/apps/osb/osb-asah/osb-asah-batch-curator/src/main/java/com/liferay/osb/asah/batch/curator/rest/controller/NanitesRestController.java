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

package com.liferay.osb.asah.batch.curator.rest.controller;

import com.liferay.osb.asah.batch.curator.bot.OSBAsahBatchCuratorBot;
import com.liferay.osb.asah.batch.curator.bot.nanite.IndividualSegmentActivityFieldsNanite;
import com.liferay.osb.asah.batch.curator.bot.scheduling.AsahTaskManager;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.AsahTask;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Michael Bowerman
 */
@Profile("!test")
@RequestMapping("/nanites")
@RestController
public class NanitesRestController {

	@PostMapping
	public void post(@RequestBody AsahTask asahTask) {
		_asahTaskManager.executeAsahTask(asahTask, false);
	}

	@PostMapping("/analytics")
	public void refreshAnalytics() {
		_individualSegmentActivityFieldsNanite.setAnalyticsConfigured(
			_dataSourceDog.isAnalyticsConfigured());
	}

	@PostMapping("/remove-schedule")
	public void removeSchedule() {
		_osbAsahBatchCuratorBot.removeNanitesSchedule(
			ProjectIdThreadLocal.getProjectId());
	}

	@PostMapping("/reschedule")
	public void reschedule() {
		_osbAsahBatchCuratorBot.rescheduleNanites(
			ProjectIdThreadLocal.getProjectId());
	}

	@PostMapping("/run")
	public void run(@RequestBody String json) {
		_asahTaskManager.runNanites(
			JSONUtil.toStringArray(new JSONArray(json)));
	}

	@PostMapping("/schedule")
	public void schedule(@RequestBody AsahTask asahTask) {
		_asahTaskManager.scheduleAsahTask(asahTask);
	}

	@PostMapping("/unschedule")
	public void unschedule(@RequestBody AsahTask asahTask) {
		_asahTaskManager.unscheduleAsahTask(asahTask);
	}

	@Autowired
	private AsahTaskManager _asahTaskManager;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private IndividualSegmentActivityFieldsNanite
		_individualSegmentActivityFieldsNanite;

	@Autowired
	private OSBAsahBatchCuratorBot _osbAsahBatchCuratorBot;

}