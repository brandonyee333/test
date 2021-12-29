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

package com.liferay.osb.asah.upgrade.v3_0_10;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchSnapshotManager;
import com.liferay.osb.asah.common.util.ProjectIdThreadLocal;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class SnapshotRepositoryUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		if (StringUtils.isEmpty(System.getenv("LCP_PROJECT_CLUSTER"))) {
			return;
		}

		try {
			_elasticsearchSnapshotManager.createSnapshotRepository(
				ProjectIdThreadLocal.getProjectId());
		}
		catch (Exception exception) {
			_log.error(
				"Unable to update snapshot repository for project " +
					ProjectIdThreadLocal.getProjectId(),
				exception);
		}
	}

	private static final Log _log = LogFactory.getLog(
		SnapshotRepositoryUpgradeStep.class);

	@Autowired
	private ElasticsearchSnapshotManager _elasticsearchSnapshotManager;

}