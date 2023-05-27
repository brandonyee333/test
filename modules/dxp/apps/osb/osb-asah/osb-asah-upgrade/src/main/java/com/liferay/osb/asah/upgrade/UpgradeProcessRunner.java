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

import com.liferay.osb.asah.common.dog.ProjectDog;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
@ConditionalOnProperty(
	matchIfMissing = true, value = "osb.asah.upgrade.process.enabled"
)
public class UpgradeProcessRunner {

	public void run() {
		if (_log.isInfoEnabled()) {
			_log.info("Upgrade started");
		}

		runProjectUpgrades();

		if (_log.isInfoEnabled()) {
			_log.info("Upgrade finished");
		}
	}

	public void runProjectUpgrades() {
		for (Project project : _projectDog.getProjects()) {
			try {
				ProjectIdThreadLocal.setProjectId(project.getId());

				if (_log.isInfoEnabled()) {
					_log.info(
						"Checking upgrades for project: " + project.getId());
				}

				_run(project);

				if (_log.isInfoEnabled()) {
					_log.info(
						"Finished upgrades for project: " + project.getId());
				}
			}
			catch (Exception exception) {
				_log.error(
					"Failed upgrades for project: " + project.getId(),
					exception);
			}
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

	private void _run(Project project) throws Exception {
		String currentVersion = project.getVersion();

		List<UpgradeStep> upgradeSteps = _upgradeProcess.getUpgradeSteps(
			currentVersion);

		while (!upgradeSteps.isEmpty()) {
			String toVersionString = _upgradeProcess.getToVersionString(
				currentVersion);

			_run(upgradeSteps, toVersionString);

			currentVersion = _updateProjectVersion(
				project.getId(), toVersionString);

			upgradeSteps = _upgradeProcess.getUpgradeSteps(currentVersion);
		}
	}

	private String _updateProjectVersion(String projectId, String version) {
		_projectDog.updateVersion(projectId, version);

		return version;
	}

	private static final Log _log = LogFactory.getLog(
		UpgradeProcessRunner.class);

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private UpgradeProcess _upgradeProcess;

}