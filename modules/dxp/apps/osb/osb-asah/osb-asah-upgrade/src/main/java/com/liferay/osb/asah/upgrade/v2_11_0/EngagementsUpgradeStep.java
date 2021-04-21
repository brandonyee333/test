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

package com.liferay.osb.asah.upgrade.v2_11_0;

import com.liferay.osb.asah.common.dog.AsahMarkerDog;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author André Miranda
 */
@Component
public class EngagementsUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		_elasticsearchIndexManager.delete(
			ElasticsearchIndexUtil.getIndexName(
				"engagements", WeDeployDataService.OSB_ASAH_FARO_INFO));

		_elasticsearchInvoker.deleteByQuery(
			QueryBuilders.termsQuery("naniteClassName", _NANITE_CLASS_NAMES),
			true, "run-logs");

		for (String naniteClassName : _NANITE_CLASS_NAMES) {
			_asahMarkerDog.deleteAsahMarker(
				naniteClassName, WeDeployDataService.OSB_ASAH_FARO_INFO);
		}
	}

	private static final String[] _NANITE_CLASS_NAMES = {
		"AccountEngagementScoresNanite", "AssetEngagementScoresNanite",
		"IndividualEngagementScoresNanite",
		"IndividualSegmentEngagementScoresNanite"
	};

	@Autowired
	private AsahMarkerDog _asahMarkerDog;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _elasticsearchInvoker;

}