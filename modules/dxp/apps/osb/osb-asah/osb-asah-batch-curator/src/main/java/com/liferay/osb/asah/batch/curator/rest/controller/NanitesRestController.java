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
import com.liferay.osb.asah.batch.curator.bot.nanite.BaseActivitiesNanite;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import org.json.JSONArray;
import org.json.JSONObject;

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
	public void post(@RequestBody String osbAsahTaskJSON) {
		_osbAsahBatchCuratorBot.executeOSBAsahTask(
			false, new JSONObject(osbAsahTaskJSON));
	}

	@PostMapping("/analytics")
	public void refreshAnalytics() {
		BaseActivitiesNanite.setAnalyticsConfigured(
			_faroInfoDataSourceDog.isAnalyticsConfigured());
	}

	@PostMapping("/run")
	public void run(@RequestBody String json) {
		_osbAsahBatchCuratorBot.run(
			JSONUtil.toStringArray(new JSONArray(json)));
	}

	@PostMapping("/schedule")
	public void schedule(@RequestBody String osbAsahTaskJSON) {
		_osbAsahBatchCuratorBot.scheduleOSBAsahTask(
			new JSONObject(osbAsahTaskJSON));
	}

	@PostMapping("/unschedule")
	public void unschedule(@RequestBody String osbAsahTaskJSON) {
		_osbAsahBatchCuratorBot.unscheduleOSBAsahTask(
			new JSONObject(osbAsahTaskJSON));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

	@Autowired
	private OSBAsahBatchCuratorBot _osbAsahBatchCuratorBot;

}