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

import com.liferay.osb.asah.common.configuration.Configuration;
import com.liferay.osb.asah.common.configuration.ConfigurationManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.salesforce.extractor.bot.exception.InterruptBotException;
import com.liferay.osb.asah.salesforce.extractor.bot.nanite.Nanite;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author Brian Wing Shun Chan
 * @author André Miranda
 * @author Rachael Koestartyo
 */
public class SalesforceBotRunnable implements Runnable {

	public SalesforceBotRunnable(
		ConfigurationManager configurationManager,
		ElasticsearchInvoker elasticsearchInvoker, Project project,
		SalesforceConfigurableBot salesforceConfigurableBot) {

		_configurationManager = configurationManager;
		_project = project;
		_salesforceConfigurableBot = salesforceConfigurableBot;

		_salesforceRawElasticsearchInvoker = elasticsearchInvoker;
	}

	public boolean isStop() {
		return _stop;
	}

	@Override
	public void run() {
		try {
			ProjectIdThreadLocal.setProjectId(_project.getId());

			if (_delay == 0) {
				Configuration[] configurations =
					_configurationManager.getConfigurations(_project.getId());

				for (Configuration configuration : configurations) {
					try {
						_salesforceConfigurableBot.refreshConfiguration(
							configuration);
					}
					catch (Exception e) {
						_log.error(e, e);
					}
				}

				try {
					_run(configurations);
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
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

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

	private void _deleteOSBAsahMarker(String osbAsahDataSourceId) {
		try {
			_salesforceRawElasticsearchInvoker.delete(
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

	private void _run(Configuration[] configurations) {
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

		try {
			for (Configuration configuration : configurations) {
				try {
					List<Nanite> nanites =
						_salesforceConfigurableBot.buildNanites(configuration);

					for (Nanite nanite : nanites) {
						_salesforceConfigurableBot.runNanite(
							configuration, nanite);
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

	private static final Log _log = LogFactory.getLog(
		SalesforceBotRunnable.class);

	private final ConfigurationManager _configurationManager;
	private int _delay;
	private String _obsoleteDataSourceId;
	private final Project _project;
	private boolean _running;
	private final SalesforceConfigurableBot _salesforceConfigurableBot;
	private final ElasticsearchInvoker _salesforceRawElasticsearchInvoker;
	private String _staleDataSourceId;
	private boolean _stop;

}