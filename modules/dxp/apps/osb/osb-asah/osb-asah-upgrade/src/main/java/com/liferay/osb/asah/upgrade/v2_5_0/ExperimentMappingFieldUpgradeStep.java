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

package com.liferay.osb.asah.upgrade.v2_5_0;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class ExperimentMappingFieldUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		_elasticsearchIndexManager.updateMapping(
			"experiments",
			JSONUtil.put(
				"properties",
				JSONUtil.put("dxpGroupId", JSONUtil.put("type", "keyword"))
			).toString(),
			"experiments", WeDeployDataService.OSB_ASAH_FARO_INFO);
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

}