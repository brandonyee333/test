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

package com.liferay.osb.asah.upgrade.v2_8_0;

import com.liferay.osb.asah.common.elasticsearch.BoolQueryBuilderUtil;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.upgrade.UpgradeStep;

import javax.annotation.PostConstruct;

import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leslie Wong
 */
@Component
public class RunLogCleanupUpgradeStep implements UpgradeStep {

	@Override
	public void upgrade(String version) {
		ElasticsearchInvoker faroInfoElasticsearchInvoker =
			_elasticsearchInvoker;

		faroInfoElasticsearchInvoker.deleteByQuery(
			BoolQueryBuilderUtil.mustNot(
				QueryBuilders.termsQuery(
					"naniteClassName", _RUN_LOG_ENABLED_NANITES)),
			false, "run-logs");
	}

	@PostConstruct
	private void _init() {
		_elasticsearchInvoker = _elasticsearchInvokerFactory.forFaroInfo();
	}

	private static final String[] _RUN_LOG_ENABLED_NANITES = {
		"AccountEngagementScoresNanite", "AssetEngagementScoresNanite",
		"DataRetentionNanite", "DeleteDXPBatchEntitiesNanite",
		"ExperimentNanite", "IndividualEngagementScoresNanite",
		"IndividualInterestScoresNanite",
		"IndividualSegmentEngagementScoresNanite",
		"InterestThresholdScoreNanite", "InterestTopicsNanite",
		"StaleDynamicIndividualSegmentsNanite"
	};

	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private ElasticsearchInvokerFactory _elasticsearchInvokerFactory;

}