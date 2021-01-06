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
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseConfigurableBot implements ConfigurableBot {

	@PostConstruct
	public void init() {
		_botRunnable = new BotRunnable(
			this, new Project(ProjectIdThreadLocal.getProjectId()));
	}

	public boolean isStop() {
		return _botRunnable.isStop();
	}

	@Scheduled(fixedDelay = DateUtil.SECOND, initialDelay = DateUtil.SECOND * 5)
	public void run() {
		_botRunnable.run();
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
		_botRunnable.stop(obsoleteDataSourceId, staleDataSourceId);
	}

	protected abstract List<Nanite> buildNanites(Configuration configuration);

	protected abstract ConfigurationManager getConfigurationManager();

	protected abstract ElasticsearchInvoker getElasticsearchInvoker();

	protected abstract Configuration refreshConfiguration(
			Configuration configuration)
		throws Exception;

	private static final Log _log = LogFactory.getLog(
		BaseConfigurableBot.class);

	@Autowired
	private AutowireCapableBeanFactory _autowireCapableBeanFactory;

	private BotRunnable _botRunnable;

}