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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.model.Project;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.elasticsearch.index.query.QueryBuilders;

/**
 * @author Brian Wing Shun Chan
 * @author André Miranda
 */
public class BotRunnable implements Runnable {

	public BotRunnable(
		BaseConfigurableBot baseConfigurableBot, Project project) {

		_baseConfigurableBot = baseConfigurableBot;
		_project = project;
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
					_baseConfigurableBot.getConfigurations();

				for (Configuration configuration : configurations) {
					try {
						_baseConfigurableBot.refreshConfiguration(
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
			ElasticsearchInvoker elasticsearchInvoker =
				_baseConfigurableBot.getElasticsearchInvoker();

			elasticsearchInvoker.delete(
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
					List<Nanite> nanites = _baseConfigurableBot.buildNanites(
						configuration);

					for (Nanite nanite : nanites) {
						_baseConfigurableBot.runNanite(configuration, nanite);
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

	private static final Log _log = LogFactory.getLog(BotRunnable.class);

	private final BaseConfigurableBot _baseConfigurableBot;
	private int _delay;
	private String _obsoleteDataSourceId;
	private final Project _project;
	private boolean _running;
	private String _staleDataSourceId;
	private boolean _stop;

}