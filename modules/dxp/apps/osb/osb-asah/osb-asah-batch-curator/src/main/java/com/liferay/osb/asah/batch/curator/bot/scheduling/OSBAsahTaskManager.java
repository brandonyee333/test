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
import com.liferay.osb.asah.common.dog.AsahTaskDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.model.AsahTask;
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

	public void deleteOSBAsahTask(Long asahTaskId) {
		_asahTaskDog.deleteAsahTaskById(asahTaskId);
	}

	public void executeOSBAsahTask(AsahTask asahTask, boolean force) {
		if (Objects.equals(
			asahTask.getClassName(), "UpdateDynamicMembershipsNanite")) {

			_osbAsahTaskScheduler.executeUpdateDynamicMembershipsNanite(
				new OSBAsahTaskRunnable(asahTask, false, this));
		}
		else {
			_osbAsahTaskScheduler.execute(
				new OSBAsahTaskRunnable(asahTask, force, this));
		}
	}

	public void executeOSBAsahTasks() {
		try {
			List<AsahTask> asahTasks = _asahTaskDog.getImmediateAsahTasks();

			for (AsahTask asahTask : asahTasks) {
				executeOSBAsahTask(asahTask, true);
			}
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
			List<AsahTask> asahTasks = _asahTaskDog.getScheduledAsahTasks();

			for (AsahTask asahTask : asahTasks) {
				unscheduleOSBAsahTask(asahTask);
			}
		}
		catch (Exception e) {
			_log.error("Unable to unschedule existing tasks", e);
		}

		_asahTaskDog.deleteAllAsahTask();
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

	public void scheduleOSBAsahTask(AsahTask asahTask) {
		Nanite nanite = getNanite(asahTask.getClassName());

		if (nanite == null) {
			throw new IllegalArgumentException(
				"Unable to schedule nanite with class name " +
					asahTask.getClassName());
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				String.format(
					"Scheduling task %s according cron expression %s",
					asahTask.getId(), asahTask.getCronExpression()));
		}

		_osbAsahTaskScheduler.schedule(
			asahTask.getCronExpression(),
			new OSBAsahTaskRunnable(asahTask, this),
			String.valueOf(asahTask.getId()));
	}

	public void scheduleOSBAsahTasks() {
		try {
			List<AsahTask> asahTasks = _asahTaskDog.getScheduledAsahTasks();

			for (AsahTask asahTask : asahTasks) {
				scheduleOSBAsahTask(asahTask);
			}
		}
		catch (Exception e) {
			_log.error("Unable to schedule existing tasks on startup", e);
		}
	}

	public void unscheduleOSBAsahTask(AsahTask asahTask) {
		_osbAsahTaskScheduler.unschedule(String.valueOf(asahTask.getId()));
	}

	private static final Log _log = LogFactory.getLog(OSBAsahTaskManager.class);

	@Autowired
	private AsahTaskDog _asahTaskDog;

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