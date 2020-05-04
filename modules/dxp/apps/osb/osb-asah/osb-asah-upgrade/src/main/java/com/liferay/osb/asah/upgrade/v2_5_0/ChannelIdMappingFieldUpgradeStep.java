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
public class ChannelIdMappingFieldUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		_addMappingField("activities", WeDeployDataService.OSB_ASAH_FARO_INFO);
		_addMappingField(
			"assets", "channelIds", WeDeployDataService.OSB_ASAH_FARO_INFO);
		_addMappingField(
			"activity-groups", WeDeployDataService.OSB_ASAH_FARO_INFO);
		_addMappingField(
			"analytics-events", WeDeployDataService.OSB_ASAH_CEREBRO_RAW);
		_addMappingField(
			"blog-clicks", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
		_addMappingField(
			"blog-social-shares", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
		_addMappingField(
			"blog-traffic-sources", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
		_addMappingField("blogs", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
		_addMappingField(
			"custom-asset-dashboards",
			WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
		_addMappingField(
			"custom-assets", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
		_addMappingField(
			"data-sources", WeDeployDataService.OSB_ASAH_FARO_INFO);
		_addMappingField(
			"document-libraries", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
		_addMappingField("experiments", WeDeployDataService.OSB_ASAH_FARO_INFO);
		_addMappingField("forms", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
		_addMappingField(
			"individual-segments", WeDeployDataService.OSB_ASAH_FARO_INFO);
		_addMappingField(
			"individuals", "channelIds",
			WeDeployDataService.OSB_ASAH_FARO_INFO);
		_addMappingField(
			"journal-clicks", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
		_addMappingField("journals", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
		_addMappingField(
			"page-referrers", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
		_addMappingField("pages", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
		_addMappingField(
			"user-sessions", WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
	}

	private void _addMappingField(
		String indexName, String propertyName,
		WeDeployDataService weDeployDataService) {

		_elasticsearchIndexManager.updateMapping(
			_elasticsearchIndexManager.getIndexName(
				indexName, weDeployDataService),
			JSONUtil.put(
				"properties",
				JSONUtil.put(propertyName, JSONUtil.put("type", "keyword"))
			).toString(),
			indexName);
	}

	private void _addMappingField(
		String indexName, WeDeployDataService weDeployDataService) {

		_addMappingField(indexName, "channelId", weDeployDataService);
	}

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

}