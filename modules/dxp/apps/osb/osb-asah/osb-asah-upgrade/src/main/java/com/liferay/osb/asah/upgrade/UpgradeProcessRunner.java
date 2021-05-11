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

import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.upgrade.UpgradeState;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
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
		List<Project> projects = _projectDog.getProjects();

		for (Project project : projects) {
			try {
				ProjectIdThreadLocal.setProjectId(project.getId());

				if (_log.isInfoEnabled()) {
					_log.info(
						"Checking upgrades for project: " + project.getId());
				}

				_run();

				if (_log.isInfoEnabled()) {
					_log.info(
						"Finished upgrades for project: " + project.getId());
				}
			}
			finally {
				ProjectIdThreadLocal.remove();
			}
		}

		_upgradeState.complete();
	}

	private String _getCurrentVersion(AsahMarker asahMarker) {
		if (asahMarker == null) {
			return null;
		}

		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		return asahMarkerContextJSONObject.getString("version");
	}

	private String _getCurrentVersionOldFormat() {
		JSONObject jsonObject = _faroInfoElasticsearchInvoker.fetch(
			"OSBAsahMarkers", "Upgrade");

		if (jsonObject == null) {
			return null;
		}

		return jsonObject.optString("version", null);
	}

	private void _run() throws Exception {
		String currentVersion = _getCurrentVersionOldFormat();

		if (currentVersion != null) {
			_asahMarkerDog.deleteAsahMarker(
				"Upgrade", WeDeployDataService.OSB_ASAH_FARO_INFO);

			_saveCurrentVersion(null, currentVersion);
		}

		AsahMarker asahMarker = _asahMarkerDog.fetchAsahMarker(
			"Upgrade", WeDeployDataService.OSB_ASAH_FARO_INFO);

		currentVersion = _getCurrentVersion(asahMarker);

		List<UpgradeStep> upgradeSteps = _upgradeProcess.getUpgradeSteps(
			currentVersion);

		while (!upgradeSteps.isEmpty()) {
			String toVersionString = _upgradeProcess.getToVersionString(
				currentVersion);

			_run(upgradeSteps, toVersionString);

			currentVersion = _saveCurrentVersion(asahMarker, toVersionString);

			upgradeSteps = _upgradeProcess.getUpgradeSteps(currentVersion);

			asahMarker = _asahMarkerDog.fetchAsahMarker(
				"Upgrade", WeDeployDataService.OSB_ASAH_FARO_INFO);
		}
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

	private String _saveCurrentVersion(
		AsahMarker asahMarker, String versionString) {

		if (asahMarker == null) {
			_asahMarkerDog.addAsahMarker(
				new AsahMarker(
					"Upgrade", JSONUtil.put("version", versionString)),
				WeDeployDataService.OSB_ASAH_FARO_INFO);
		}
		else {
			JSONObject asahMarkerContextJSONObject =
				asahMarker.getContextJSONObject();

			asahMarkerContextJSONObject.put("version", versionString);

			_asahMarkerDog.updateAsahMarker(
				asahMarker, WeDeployDataService.OSB_ASAH_FARO_INFO);
		}

		return versionString;
	}

	private static final Log _log = LogFactory.getLog(
		UpgradeProcessRunner.class);

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private UpgradeProcess _upgradeProcess;

	@Autowired
	private UpgradeState _upgradeState;

}