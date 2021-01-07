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

package com.liferay.osb.asah.upgrade.v0_0_0;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchSnapshotManager;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
public class SnapshotsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		if (StringUtils.isEmpty(System.getenv("LCP_PROJECT_CLUSTER"))) {
			return;
		}

		_elasticsearchSnapshotManager.createSnapshotLifecyclePolicy(
			ServiceConstants.LCP_PROJECT_ID);
	}

	@Autowired
	private ElasticsearchSnapshotManager _elasticsearchSnapshotManager;

}