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

package com.liferay.osb.asah.upgrade.v2_13_0.test;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.spring.resource.ResourceUtil;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;
import com.liferay.osb.asah.test.util.elasticsearch.ElasticsearchIndex;
import com.liferay.osb.asah.test.util.spring.OSBAsahSpringJUnit4ClassRunner;
import com.liferay.osb.asah.upgrade.spring.OSBAsahUpgradeSpringBootApplication;
import com.liferay.osb.asah.upgrade.v2_13_0.SalesforceAuditEventUpgradeStep;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.skyscreamer.jsonassert.JSONAssert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Marcellus Tavares
 */
@RunWith(OSBAsahSpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OSBAsahUpgradeSpringBootApplication.class)
public class SalesforceAuditEventUpgradeStepTest {

	@ElasticsearchIndex(
		name = "audit-events", resourcePath = "old_audit_events.json",
		weDeployDataService = WeDeployDataService.OSB_ASAH_SALESFORCE_RAW
	)
	@Test
	public void testUpgrade() throws Exception {
		_salesforceAuditEventUpgradeStep.upgrade("");

		JSONAssert.assertEquals(
			ResourceUtil.readResourceToJSONArray(
				"dependencies/osbasahsalesforceraw/new_audit_events.json",
				this),
			_elasticsearchInvoker.get("audit-events"), false);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _elasticsearchInvoker;

	@Autowired
	private SalesforceAuditEventUpgradeStep _salesforceAuditEventUpgradeStep;

}