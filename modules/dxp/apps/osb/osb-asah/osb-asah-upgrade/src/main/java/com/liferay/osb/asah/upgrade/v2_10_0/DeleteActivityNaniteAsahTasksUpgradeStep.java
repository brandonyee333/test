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

package com.liferay.osb.asah.upgrade.v2_10_0;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.stereotype.Component;

/**
 * @author Marcellus Tavares
 */
@Component
public class DeleteActivityNaniteAsahTasksUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) throws Exception {
		_elasticsearchInvoker.delete(
			"OSBAsahTasks",
			QueryBuilders.termQuery("className", "ActivitiesNanite"));
		_elasticsearchInvoker.delete(
			"OSBAsahTasks",
			QueryBuilders.termQuery(
				"className", "IndividualActivityFieldsNanite"));
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}