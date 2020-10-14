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

package com.liferay.osb.asah.upgrade.v2_9_0.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v2_9_0.AnalyticsEventsUpgradeStep;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Shinn Lok
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class AnalyticsEventsUpgradeStepTest {

	@ElasticsearchIndex(
		name = "analytics-events", resourcePath = "analytics_events.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_CEREBRO_RAW
	)
	@Test
	public void testUpgrade() {
		String indexName = _elasticsearchIndexManager.getIndexName(
			"analytics-events", WeDeployDataService.OSB_ASAH_CEREBRO_RAW);

		Assert.assertTrue(_elasticsearchIndexManager.exists(indexName));

		_analyticsEventsUpgradeStep.upgrade("");

		Assert.assertFalse(_elasticsearchIndexManager.exists(indexName));
	}

	@Autowired
	private AnalyticsEventsUpgradeStep _analyticsEventsUpgradeStep;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

}