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
import com.liferay.osb.asah.common.dog.RunLogger;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

/**
 * @author André Miranda
 */
@Component
public class AsahTaskManager {

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

	public void deleteAsahTask(Long asahTaskId) {
		_asahTaskDog.deleteAsahTask(asahTaskId);
	}

	public void executeAsahTask(AsahTask asahTask, boolean force) {
		if (Objects.equals(
				asahTask.getClassName(), "UpdateDynamicMembershipsNanite")) {

			_asahTaskScheduler.executeUpdateDynamicMembershipsNanite(
				new AsahTaskRunnable(asahTask, this, false));
		}
		else {
			_asahTaskScheduler.execute(
				new AsahTaskRunnable(asahTask, this, force));
		}
	}

	public void executeAsahTask(Long asahTaskId, boolean force) {
		executeAsahTask(_asahTaskDog.getAsahTask(asahTaskId), force);
	}

	public void executeAsahTasks() {
		try {
			List<AsahTask> asahTasks = _asahTaskDog.getImmediateAsahTasks();

			for (AsahTask asahTask : asahTasks) {
				executeAsahTask(asahTask, true);
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

	public void removeAsahTasks() {
		try {
			List<AsahTask> asahTasks = _asahTaskDog.getScheduledAsahTasks();

			for (AsahTask asahTask : asahTasks) {
				unscheduleAsahTask(asahTask.getId());
			}
		}
		catch (Exception e) {
			_log.error("Unable to unschedule existing tasks", e);
		}

		_asahTaskDog.deleteAsahTasks();
	}

	public void runNanites(String... naniteClassNames) {
		_asahTaskScheduler.execute(
			new AsahTaskRunnable(
				this, ProjectIdThreadLocal.getProjectId(), naniteClassNames));
	}

	public void runNanitesForAllProjects(String... naniteClassNames) {
		try {
			List<Project> projects = _projectDog.getProjects();

			for (Project project : projects) {
				_asahTaskScheduler.execute(
					new AsahTaskRunnable(
						this, project.getId(), naniteClassNames));
			}
		}
		catch (Exception e) {
			_log.error("Unable to run nanites for all projects", e);
		}
	}

	public void scheduleAsahTask(AsahTask asahTask) {
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

		_asahTaskScheduler.schedule(
			asahTask.getCronExpression(), new AsahTaskRunnable(asahTask, this),
			String.valueOf(asahTask.getId()));
	}

	public void scheduleAsahTask(Long asahTaskId) {
		scheduleAsahTask(_asahTaskDog.getAsahTask(asahTaskId));
	}

	public void scheduleAsahTasks() {
		try {
			List<AsahTask> asahTasks = _asahTaskDog.getScheduledAsahTasks();

			for (AsahTask asahTask : asahTasks) {
				scheduleAsahTask(asahTask);
			}
		}
		catch (Exception e) {
			_log.error("Unable to schedule existing tasks on startup", e);
		}
	}

	public void unscheduleAsahTask(Long asahTaskId) {
		_asahTaskScheduler.unschedule(String.valueOf(asahTaskId));
	}

	private static final Log _log = LogFactory.getLog(AsahTaskManager.class);

	@Autowired
	private AsahTaskDog _asahTaskDog;

	@Autowired
	private AsahTaskScheduler _asahTaskScheduler;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private List<Nanite> _nanites;

	private final Map<String, Nanite> _nanitesMap = new HashMap<>();

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private RunLogger _runLogger;

}