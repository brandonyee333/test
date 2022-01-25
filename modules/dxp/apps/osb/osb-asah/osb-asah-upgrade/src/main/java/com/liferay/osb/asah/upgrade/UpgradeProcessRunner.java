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

import com.liferay.osb.asah.common.concurrent.BoundedExecutor;
import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.dog.ProjectDog;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.upgrade.UpgradeState;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.List;

import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class UpgradeProcessRunner {

	public void run() throws Exception {
		runGlobalUpgrades();

		runProjectUpgrades();
	}

	public void runGlobalUpgrades() throws Exception {
		try {
			ProjectIdThreadLocal.setGlobalContext(true);

			if (_log.isInfoEnabled()) {
				_log.info("Checking for global upgrades");
			}

			_run(_globalUpgradeSteps, "");

			if (_log.isInfoEnabled()) {
				_log.info("Finished global upgrades");
			}
		}
		finally {
			ProjectIdThreadLocal.remove();
		}
	}

	public void runProjectUpgrades() throws Exception {
		List<Project> projects = _projectDog.getProjects();

		for (Project project : projects) {
			_boundedExecutor.runAsync(
				() -> {
					try {
						ProjectIdThreadLocal.setProjectId(project.getId());

						if (_log.isInfoEnabled()) {
							_log.info(
								"Checking upgrades for project: " +
									project.getId());
						}

						_run();

						if (_log.isInfoEnabled()) {
							_log.info(
								"Finished upgrades for project: " +
									project.getId());
						}
					}
					catch (Exception exception) {
						_log.error(
							"Failed upgrades for project: " + project.getId(),
							exception);
					}
				});
		}

		_boundedExecutor.awaitPendingTasks();

		_upgradeState.complete();
	}

	@PreDestroy
	private void _destroy() {
		_boundedExecutor.shutdown();
	}

	private AsahMarker _getAsahMarker() {
		AsahMarker asahMarker = _asahMarkerDog.fetchAsahMarker(
			"Upgrade", WeDeployDataService.OSB_ASAH_FARO_INFO);

		if (asahMarker != null) {
			return asahMarker;
		}

		asahMarker = _asahMarkerDog.addAsahMarker(
			new AsahMarker("Upgrade", JSONUtil.put("version", "0.0.0")),
			WeDeployDataService.OSB_ASAH_FARO_INFO);

		asahMarker.setIsNew(Boolean.FALSE);

		return asahMarker;
	}

	private String _getCurrentVersion(AsahMarker asahMarker) {
		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		return asahMarkerContextJSONObject.getString("version");
	}

	private void _run() throws Exception {
		AsahMarker asahMarker = _getAsahMarker();

		String currentVersion = _getCurrentVersion(asahMarker);

		List<UpgradeStep> upgradeSteps = _upgradeProcess.getUpgradeSteps(
			currentVersion);

		while (!upgradeSteps.isEmpty()) {
			String toVersionString = _upgradeProcess.getToVersionString(
				currentVersion);

			_run(upgradeSteps, toVersionString);

			currentVersion = _saveCurrentVersion(asahMarker, toVersionString);

			upgradeSteps = _upgradeProcess.getUpgradeSteps(currentVersion);
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

		JSONObject asahMarkerContextJSONObject =
			asahMarker.getContextJSONObject();

		asahMarkerContextJSONObject.put("version", versionString);

		_asahMarkerDog.updateAsahMarker(
			asahMarker, WeDeployDataService.OSB_ASAH_FARO_INFO);

		return versionString;
	}

	private static final Log _log = LogFactory.getLog(
		UpgradeProcessRunner.class);

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	private final BoundedExecutor _boundedExecutor =
		BoundedExecutor.newBoundedExecutor(15, 10);

	@Autowired
	@Qualifier("globalUpgradeSteps")
	private List<UpgradeStep> _globalUpgradeSteps;

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private UpgradeProcess _upgradeProcess;

	@Autowired
	private UpgradeState _upgradeState;

}