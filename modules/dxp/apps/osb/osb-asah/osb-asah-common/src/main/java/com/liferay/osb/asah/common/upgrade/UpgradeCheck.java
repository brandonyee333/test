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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;
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
 */
@Component
@MonolithExclude
@Profile("default")
public class UpgradeCheck {

	@PostConstruct
	public void init() {
		if (_upgradeState.isComplete()) {
			return;
		}

		JSONObject osbAsahMarkerJSONObject = _elasticsearchInvoker.fetch(
			"OSBAsahMarkers", "Upgrade");

		if ((osbAsahMarkerJSONObject != null) &&
			Objects.equals(
				osbAsahMarkerJSONObject.optString("version"),
				ReleaseInfo.getVersion())) {

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

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private UpgradeState _upgradeState;

}