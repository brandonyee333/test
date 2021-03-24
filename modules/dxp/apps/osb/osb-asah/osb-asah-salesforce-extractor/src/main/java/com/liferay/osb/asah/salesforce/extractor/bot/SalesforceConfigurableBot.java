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

package com.liferay.osb.asah.salesforce.extractor.bot;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.configuration.Configuration;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.model.DataSource;
import com.liferay.osb.asah.common.model.Project;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.prometheus.PrometheusUtil;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.salesforce.extractor.bot.nanite.Nanite;
import com.liferay.osb.asah.salesforce.extractor.bot.nanite.SalesforceExtractorIndividualsNanite;
import com.liferay.osb.asah.salesforce.extractor.bot.nanite.SalesforceExtractorNanite;
import com.liferay.osb.asah.salesforce.extractor.configuration.SalesforceExtractorConfiguration;
import com.liferay.osb.asah.salesforce.extractor.configuration.impl.SalesforceExtractorConfigurationManagerImpl;

import io.prometheus.client.Gauge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Rachael Koestartyo
 */
@Component
@EnableScheduling
@Profile("!test")
public class SalesforceConfigurableBot {

	public SalesforceBotRunnable getSalesforceBotRunnable(String projectId) {
		return _salesforceBotRunnables.get(new Project(projectId));
	}

	public boolean isStop() {
		Project project = new Project(ProjectIdThreadLocal.getProjectId());

		SalesforceBotRunnable salesforceBotRunnable =
			_salesforceBotRunnables.get(project);

		if (salesforceBotRunnable != null) {
			return salesforceBotRunnable.isStop();
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

		_monitorThreadPool();
	}

	public void setTableNames(String[] tableNames) {
		if (tableNames != null) {
			_tableNames = Arrays.copyOf(tableNames, tableNames.length);
		}
	}

	protected List<Nanite> buildNanites(Configuration configuration) {
		return new ArrayList<Nanite>() {
			{
				SalesforceExtractorConfiguration
					salesforceExtractorConfiguration =
						(SalesforceExtractorConfiguration)configuration;

				// Optimal order

				add(
					new SalesforceExtractorNanite(
						salesforceExtractorConfiguration, _tableNames));
				add(
					new SalesforceExtractorIndividualsNanite(
						salesforceExtractorConfiguration));
			}
		};
	}

	protected Configuration refreshConfiguration(Configuration configuration) {
		SalesforceExtractorConfiguration salesforceExtractorConfiguration =
			(SalesforceExtractorConfiguration)configuration;

		DataSource existingDataSource = _dataSourceDog.getDataSource(
			Long.valueOf(salesforceExtractorConfiguration.getDataSourceId()));

		DataSource newDataSource =
			_salesforceExtractorConfigurationManagerImpl.refresh(
				existingDataSource);

		if (Objects.equals(existingDataSource, newDataSource)) {
			return configuration;
		}

		DataSource configurationsDataSource = _dataSourceDog.updateDataSource(
			newDataSource);

		return _salesforceExtractorConfigurationManagerImpl.updateConfiguration(
			configurationsDataSource);
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

	private void _monitorThreadPool() {
		Gauge.Child child = _threadPoolActiveGauge.labels(
			ClassUtils.getSimpleName(getClass()));

		child.set(_threadPoolExecutor.getActiveCount());

		child = _threadPoolSizeGauge.labels(
			ClassUtils.getSimpleName(getClass()));

		child.set(_threadPoolExecutor.getPoolSize());
	}

	private void _resizeThreadPoolSize() {
		Collection<ScheduledFuture<?>> scheduledFutures =
			_scheduledProjects.values();

		if (_threadPoolExecutor.getCorePoolSize() != scheduledFutures.size()) {
			_threadPoolExecutor.setCorePoolSize(scheduledFutures.size());
		}
	}

	private void _scheduleProjects(Collection<Project> projects) {
		for (Project project : projects) {
			SalesforceBotRunnable salesforceBotRunnable =
				new SalesforceBotRunnable(
					_salesforceExtractorConfigurationManagerImpl,
					_salesforceRawElasticsearchInvoker, project, this);

			_salesforceBotRunnables.put(project, salesforceBotRunnable);
			_scheduledProjects.put(
				project,
				_scheduledExecutorService.scheduleWithFixedDelay(
					salesforceBotRunnable, 1, 1, TimeUnit.SECONDS));

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

			_salesforceBotRunnables.remove(project);
			_scheduledProjects.remove(project);

			if (_log.isInfoEnabled()) {
				_log.info("Cancelled scheduling of project " + project.getId());
			}
		}
	}

	private static final Log _log = LogFactory.getLog(
		SalesforceConfigurableBot.class);

	private static final Gauge _threadPoolActiveGauge = PrometheusUtil.gauge(
		"extractor_thread_pool_active",
		"The number of active threads in extractor's thread pool", "bot");
	private static final Gauge _threadPoolSizeGauge = PrometheusUtil.gauge(
		"extractor_thread_pool_size",
		"The number of threads in extractor's thread pool", "bot");

	@Autowired
	private AutowireCapableBeanFactory _autowireCapableBeanFactory;

	@Autowired
	private DataSourceDog _dataSourceDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ObjectMapper _objectMapper;

	@Autowired
	private ProjectDog _projectDog;

	private final Map<Project, SalesforceBotRunnable> _salesforceBotRunnables =
		new HashMap<>();

	@Autowired
	private SalesforceExtractorConfigurationManagerImpl
		_salesforceExtractorConfigurationManagerImpl;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

	private final ScheduledExecutorService _scheduledExecutorService =
		Executors.newScheduledThreadPool(1);
	private final Map<Project, ScheduledFuture<?>> _scheduledProjects =
		new HashMap<>();
	private String[] _tableNames;
	private final ThreadPoolExecutor _threadPoolExecutor =
		(ThreadPoolExecutor)_scheduledExecutorService;

}