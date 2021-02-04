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
import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONArrayIterator;
import com.liferay.osb.asah.common.model.Project;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.run.logger.RunLogger;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

/**
 * @author André Miranda
 */
@Component
public class OSBAsahTaskManager {

	public boolean checkNanite(String naniteClassName) {
		JSONObject latestRunLogJSONObject =
			_runLogger.fetchLatestRunLogJSONObject(
				null, _elasticsearchInvoker, naniteClassName);

		if ((latestRunLogJSONObject != null) &&
			Objects.equals(
				latestRunLogJSONObject.getString("status"), "STARTED")) {

			_log.error("Nanite is already running: " + latestRunLogJSONObject);

			return true;
		}

		return false;
	}

	public void deleteOSBAsahTask(String osbAsahTaskId) {
		if (osbAsahTaskId != null) {
			_elasticsearchInvoker.delete("OSBAsahTasks", osbAsahTaskId);
		}
	}

	public void executeOSBAsahTask(
		boolean force, JSONObject osbAsahTaskJSONObject) {

		String className = osbAsahTaskJSONObject.getString("className");

		if (className.equals("UpdateDynamicMembershipsNanite")) {
			_osbAsahTaskScheduler.executeUpdateDynamicMembershipsNanite(
				new OSBAsahTaskRunnable(false, osbAsahTaskJSONObject, this));
		}
		else {
			_osbAsahTaskScheduler.execute(
				new OSBAsahTaskRunnable(force, osbAsahTaskJSONObject, this));
		}
	}

	public void executeOSBAsahTasks() {
		try {
			JSONArrayIterator.of(
				"OSBAsahTasks", _elasticsearchInvoker,
				osbAsahTaskJSONObject -> {
					executeOSBAsahTask(true, osbAsahTaskJSONObject);

					return null;
				}
			).setQueryBuilder(
				BoolQueryBuilderUtil.mustNot(
					QueryBuilders.existsQuery("cronExpression"))
			).iterate();
		}
		catch (Exception e) {
			_log.error("Unable to run existing tasks on startup", e);
		}
	}

	public Nanite getNanite(String className) {
		return _nanitesMap.get(className);
	}

	@PostConstruct
	public void init() {
		for (Nanite nanite : _nanites) {
			Class<?> clazz = nanite.getClass();

			_nanitesMap.put(ClassUtils.getShortName(clazz), nanite);
		}
	}

	public void removeOSBAsahTasks() {
		try {
			JSONArrayIterator.of(
				"OSBAsahTasks", _elasticsearchInvoker,
				osbAsahTaskJSONObject -> {
					unscheduleOSBAsahTask(osbAsahTaskJSONObject);

					return null;
				}
			).setQueryBuilder(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.existsQuery("cronExpression"))
			).setStopOnExceptions(
				false
			).iterate();
		}
		catch (Exception e) {
			_log.error("Unable to unschedule existing tasks", e);
		}

		_elasticsearchInvoker.delete(
			"OSBAsahTasks", QueryBuilders.matchAllQuery());
	}

	public void runNanites(String... naniteClassNames) {
		_osbAsahTaskScheduler.execute(
			new OSBAsahTaskRunnable(
				this, ProjectIdThreadLocal.getProjectId(), naniteClassNames));
	}

	public void runNanitesForAllProjects(String... naniteClassNames) {
		try {
			List<Project> projects = _projectDog.getProjects();

			for (Project project : projects) {
				_osbAsahTaskScheduler.execute(
					new OSBAsahTaskRunnable(
						this, project.getId(), naniteClassNames));
			}
		}
		catch (Exception e) {
			_log.error("Unable to run nanites for all projects", e);
		}
	}

	public void scheduleOSBAsahTask(JSONObject osbAsahTaskJSONObject) {
		String className = osbAsahTaskJSONObject.getString("className");

		Nanite nanite = getNanite(className);

		if (nanite == null) {
			throw new IllegalArgumentException(
				"Unable to schedule nanite with class name " + className);
		}

		String cronExpression = osbAsahTaskJSONObject.getString(
			"cronExpression");

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Scheduling task %s according cron expression %s",
					osbAsahTaskJSONObject, cronExpression));
		}

		_osbAsahTaskScheduler.schedule(
			cronExpression,
			new OSBAsahTaskRunnable(osbAsahTaskJSONObject, this),
			osbAsahTaskJSONObject.getString("id"));
	}

	public void scheduleOSBAsahTasks() {
		try {
			JSONArrayIterator.of(
				"OSBAsahTasks", _elasticsearchInvoker,
				osbAsahTaskJSONObject -> {
					scheduleOSBAsahTask(osbAsahTaskJSONObject);

					return null;
				}
			).setQueryBuilder(
				BoolQueryBuilderUtil.filter(
					QueryBuilders.existsQuery("cronExpression"))
			).iterate();
		}
		catch (Exception e) {
			_log.error("Unable to schedule existing tasks on startup", e);
		}
	}

	public void unscheduleOSBAsahTask(JSONObject osbAsahTaskJSONObject) {
		_osbAsahTaskScheduler.unschedule(osbAsahTaskJSONObject.getString("id"));
	}

	private static final Log _log = LogFactory.getLog(OSBAsahTaskManager.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private List<Nanite> _nanites;

	private final Map<String, Nanite> _nanitesMap = new HashMap<>();

	@Autowired
	private OSBAsahTaskScheduler _osbAsahTaskScheduler;

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private RunLogger _runLogger;

}