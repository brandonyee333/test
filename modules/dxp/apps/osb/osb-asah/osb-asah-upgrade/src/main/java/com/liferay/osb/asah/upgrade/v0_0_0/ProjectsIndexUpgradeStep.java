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

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class ProjectsIndexUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		if (_elasticsearchIndexManager.exists("projects")) {
			if (_log.isInfoEnabled()) {
				_log.info("Skipping, projects index already exists");
			}

			return;
		}

		_elasticsearchIndexManager.create(
			ResourceUtil.readResourceToString(
				"dependencies/global/projects_index_configuration.json",
				ElasticsearchIndexManager.class),
			"projects");
	}

	private static final Log _log = LogFactory.getLog(
		ProjectsIndexUpgradeStep.class);

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

}