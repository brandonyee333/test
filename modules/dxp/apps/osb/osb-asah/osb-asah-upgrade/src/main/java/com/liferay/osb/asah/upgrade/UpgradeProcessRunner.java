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

package com.liferay.osb.asah.upgrade;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.upgrade.UpgradeState;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class UpgradeProcessRunner {

	public void run() throws Exception {
		String currentVersion = _getCurrentVersion();

		List<UpgradeStep> upgradeSteps = _upgradeProcess.getUpgradeSteps(
			currentVersion);

		while (!upgradeSteps.isEmpty()) {
			String toVersionString = _upgradeProcess.getToVersionString(
				currentVersion);

			_run(upgradeSteps, toVersionString);

			currentVersion = _saveCurrentVersion(toVersionString);

			upgradeSteps = _upgradeProcess.getUpgradeSteps(currentVersion);
		}

		_upgradeState.complete();
	}

	private String _getCurrentVersion() {
		JSONObject jsonObject = _faroInfoElasticsearchInvoker.fetch(
			"OSBAsahMarkers", "Upgrade");

		if (jsonObject == null) {
			return null;
		}

		return jsonObject.getString("version");
	}

	private void _run(List<UpgradeStep> upgradeSteps, String version)
		throws Exception {

		for (UpgradeStep upgradeStep : upgradeSteps) {
			Class<? extends UpgradeStep> upgradeStepClass =
				upgradeStep.getClass();

			if (_log.isInfoEnabled()) {
				_log.info("Starting " + upgradeStepClass.getCanonicalName());
			}

			upgradeStep.upgrade(version);

			if (_log.isInfoEnabled()) {
				_log.info("Finished " + upgradeStepClass.getCanonicalName());
			}
		}
	}

	private String _saveCurrentVersion(String versionString) {
		_faroInfoElasticsearchInvoker.save(
			"OSBAsahMarkers",
			JSONUtil.put(
				"id", "Upgrade"
			).put(
				"version", versionString
			));

		return versionString;
	}

	private static final Log _log = LogFactory.getLog(
		UpgradeProcessRunner.class);

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private UpgradeProcess _upgradeProcess;

	@Autowired
	private UpgradeState _upgradeState;

}