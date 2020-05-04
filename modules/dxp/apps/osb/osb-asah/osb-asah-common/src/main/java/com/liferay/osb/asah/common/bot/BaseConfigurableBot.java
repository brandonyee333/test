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

import com.liferay.osb.asah.common.bot.exception.InterruptBotException;
import com.liferay.osb.asah.common.bot.nanite.Nanite;
import com.liferay.osb.asah.common.configuration.Configuration;
import com.liferay.osb.asah.common.configuration.ConfigurationManager;
import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class BaseConfigurableBot implements ConfigurableBot {

	@PostConstruct
	public void init() {
		_configurationManager = getConfigurationManager();
		_elasticsearchInvoker = getElasticsearchInvoker();
	}

	public boolean isStop() {
		return _stop;
	}

	@Scheduled(fixedDelay = DateUtil.SECOND, initialDelay = DateUtil.SECOND * 5)
	public void run() {
		if (_delay == 0) {
			for (Configuration configuration :
					_configurationManager.getConfigurations()) {

				try {
					refreshConfiguration(configuration);
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}

			try {
				_run();
			}
			catch (InterruptBotException ibe) {
				if (_log.isInfoEnabled()) {
					_log.info("Bot was interrupted", ibe);
				}

				return;
			}
		}

		_delay++;

		// Delay for one hour

		if (_delay == (60 * 60)) {
			_delay = 0;
		}
	}

	public void stop() {
		stop(null, null);
	}

	@Override
	public void stop(String obsoleteDataSourceId, String staleDataSourceId) {
		_delay = 0;
		_obsoleteDataSourceId = obsoleteDataSourceId;
		_staleDataSourceId = staleDataSourceId;

		_stop = true;

		while (_running && _stop) {
			try {
				Thread.sleep(500);
			}
			catch (InterruptedException ie) {
				_log.error(ie, ie);
			}
		}
	}

	protected abstract List<Nanite> buildNanites(Configuration configuration);

	protected abstract ConfigurationManager getConfigurationManager();

	protected abstract ElasticsearchInvoker getElasticsearchInvoker();

	protected abstract Configuration refreshConfiguration(
			Configuration configuration)
		throws Exception;

	private void _deleteOSBAsahMarker(String osbAsahDataSourceId) {
		try {
			_elasticsearchInvoker.delete(
				"OSBAsahMarkers",
				QueryBuilders.termQuery(
					"osbAsahDataSourceId", osbAsahDataSourceId));
		}
		catch (Exception e) {
			_log.error(
				"Recreate the entire Elasticsearch service because there is " +
					"stale data",
				e);
		}
	}

	private void _run() {
		_running = true;

		if (_stop) {
			if (_obsoleteDataSourceId != null) {
				_deleteOSBAsahMarker(_obsoleteDataSourceId);

				_obsoleteDataSourceId = null;
			}

			if (_staleDataSourceId != null) {
				_deleteOSBAsahMarker(_staleDataSourceId);

				_staleDataSourceId = null;
			}

			_stop = false;
		}

		Configuration[] configurations =
			_configurationManager.getConfigurations();

		try {
			for (Configuration configuration : configurations) {
				try {
					List<Nanite> nanites = buildNanites(configuration);

					for (Nanite nanite : nanites) {
						_run(configuration, nanite);
					}
				}
				catch (InterruptBotException ibe) {
					throw ibe;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
		}
		finally {
			_running = false;
		}
	}

	private void _run(Configuration configuration, Nanite nanite)
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

	private static final Log _log = LogFactory.getLog(
		BaseConfigurableBot.class);

	@Autowired
	private AutowireCapableBeanFactory _autowireCapableBeanFactory;

	private ConfigurationManager _configurationManager;
	private int _delay;
	private ElasticsearchInvoker _elasticsearchInvoker;
	private String _obsoleteDataSourceId;
	private boolean _running;
	private String _staleDataSourceId;
	private boolean _stop;

}