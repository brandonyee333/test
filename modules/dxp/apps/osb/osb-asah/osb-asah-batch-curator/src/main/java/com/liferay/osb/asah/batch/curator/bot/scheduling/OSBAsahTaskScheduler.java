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
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.run.logger.RunLogger;
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

/**
 * @author André Miranda
 */
@Component
public class OSBAsahTaskScheduler {

	public boolean checkNotRunning(String className) {
		JSONObject latestRunLogJSONObject =
			_runLogger.fetchLatestRunLogJSONObject(
				null, _elasticsearchInvoker, className);

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

	public Nanite getNanite(String className) {
		return _nanitesMap.get(className);
	}

	@PostConstruct
	public void init() {
		for (Nanite nanite : _nanites) {
			Class<?> clazz = nanite.getClass();

			_nanitesMap.put(clazz.getSimpleName(), nanite);
		}
	}

	private static final Log _log = LogFactory.getLog(
		OSBAsahTaskScheduler.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private List<Nanite> _nanites;

	private final Map<String, Nanite> _nanitesMap = new HashMap<>();

	@Autowired
	private RunLogger _runLogger;

}