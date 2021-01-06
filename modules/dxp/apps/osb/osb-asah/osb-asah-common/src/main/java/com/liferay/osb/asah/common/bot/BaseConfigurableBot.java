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

package com.liferay.osb.asah.common.bot;

import com.liferay.osb.asah.common.bot.nanite.Nanite;
import com.liferay.osb.asah.common.configuration.Configuration;
import com.liferay.osb.asah.common.configuration.ConfigurationManager;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.model.Project;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseConfigurableBot implements ConfigurableBot {

	public boolean isStop() {
		Project project = new Project(ProjectIdThreadLocal.getProjectId());

		BotRunnable botRunnable = _botRunnables.get(project);

		if (botRunnable != null) {
			return botRunnable.isStop();
		}

		return false;
	}

	@Scheduled(fixedDelay = DateUtil.SECOND, initialDelay = DateUtil.SECOND * 5)
	public void run() throws Exception {
		List<Project> projects = _projectDog.getProjects();

		_scheduleProjects(
			CollectionUtils.subtract(projects, _scheduledProjects.keySet()));

		_unscheduleProjects(
			CollectionUtils.subtract(_scheduledProjects.keySet(), projects));

		_resizeThreadPoolSize();
	}

	protected void runNanite(Configuration configuration, Nanite nanite)
		throws Exception {

		_autowireCapableBeanFactory.autowireBeanProperties(
			configuration, AutowireCapableBeanFactory.AUTOWIRE_NO, false);

		_autowireCapableBeanFactory.autowireBeanProperties(
			nanite, AutowireCapableBeanFactory.AUTOWIRE_NO, false);

		Class<?> clazz = nanite.getClass();

		_autowireCapableBeanFactory.initializeBean(
			nanite, clazz.getName() + "#" + configuration.getDataSourceId());

		nanite.run();
	}

	public void stop() {
		stop(null, null);
	}

	@Override
	public void stop(String obsoleteDataSourceId, String staleDataSourceId) {
		Project project = new Project(ProjectIdThreadLocal.getProjectId());

		BotRunnable botRunnable = _botRunnables.get(project);

		if (botRunnable != null) {
			botRunnable.stop(obsoleteDataSourceId, staleDataSourceId);
		}
	}

	protected abstract List<Nanite> buildNanites(Configuration configuration);

	protected abstract ConfigurationManager getConfigurationManager();

	protected abstract ElasticsearchInvoker getElasticsearchInvoker();

	protected abstract Configuration refreshConfiguration(
			Configuration configuration)
		throws Exception;

	private void _resizeThreadPoolSize() {
		Collection<ScheduledFuture<?>> scheduledFutures =
			_scheduledProjects.values();

		if (_threadPoolExecutor.getCorePoolSize() != scheduledFutures.size()) {
			_threadPoolExecutor.setCorePoolSize(scheduledFutures.size());
		}
	}

	private void _scheduleProjects(Collection<Project> projects) {
		for (Project project : projects) {
			BotRunnable botRunnable = new BotRunnable(this, project);

			_botRunnables.put(project, botRunnable);
			_scheduledProjects.put(
				project,
				_scheduledExecutorService.scheduleWithFixedDelay(
					botRunnable, 1, 1, TimeUnit.SECONDS));

			if (_log.isInfoEnabled()) {
				_log.info("Scheduled project " + project.getId());
			}
		}
	}

	private void _unscheduleProjects(Collection<Project> projects) {
		for (Project project : projects) {
			ScheduledFuture<?> scheduledFuture = _scheduledProjects.get(
				project);

			scheduledFuture.cancel(false);

			_botRunnables.remove(project);
			_scheduledProjects.remove(project);

			if (_log.isInfoEnabled()) {
				_log.info("Cancelled scheduling of project " + project.getId());
			}
		}
	}

	private static final Log _log = LogFactory.getLog(
		BaseConfigurableBot.class);

	@Autowired
	private AutowireCapableBeanFactory _autowireCapableBeanFactory;

	private final Map<Project, BotRunnable> _botRunnables = new HashMap<>();

	@Autowired
	private ProjectDog _projectDog;

	private final ScheduledExecutorService _scheduledExecutorService =
		Executors.newScheduledThreadPool(1);
	private final Map<Project, ScheduledFuture<?>> _scheduledProjects =
		new HashMap<>();
	private final ThreadPoolExecutor _threadPoolExecutor =
		(ThreadPoolExecutor)_scheduledExecutorService;

}