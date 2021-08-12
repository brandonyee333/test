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

package com.liferay.osb.asah.common.upgrade;

import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.entity.Project;
import com.liferay.osb.asah.common.multitenancy.ProjectDog;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.common.util.ReleaseInfo;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import java.util.Objects;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author Michael Bowerman
 * @author André Miranda
 */
@Component
@Profile({"dev", "prod"})
public class UpgradeCheck {

	public boolean checkVersion() throws Exception {
		for (Project project : _projectDog.getProjects()) {
			try {
				ProjectIdThreadLocal.setProjectId(project.getId());

				AsahMarker asahMarker = _asahMarkerDog.fetchAsahMarker(
					"Upgrade", WeDeployDataService.OSB_ASAH_FARO_INFO);

				if (asahMarker == null) {
					continue;
				}

				JSONObject asahMarkerContextJSONObject =
					asahMarker.getContextJSONObject();

				if (!Objects.equals(
						asahMarkerContextJSONObject.optString("version"),
						ReleaseInfo.getVersion())) {

					return false;
				}
			}
			finally {
				ProjectIdThreadLocal.remove();
			}
		}

		return true;
	}

	@PostConstruct
	public void init() throws Exception {
		if (_upgradeState.isComplete()) {
			return;
		}

		if (checkVersion()) {
			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Waiting for upgrade to complete");
		}

		_upgradeState.awaitCompletion();

		if (_log.isInfoEnabled()) {
			_log.info("Upgrade complete");
		}
	}

	private static final Log _log = LogFactory.getLog(UpgradeCheck.class);

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@Autowired
	private ProjectDog _projectDog;

	@Autowired
	private UpgradeState _upgradeState;

}