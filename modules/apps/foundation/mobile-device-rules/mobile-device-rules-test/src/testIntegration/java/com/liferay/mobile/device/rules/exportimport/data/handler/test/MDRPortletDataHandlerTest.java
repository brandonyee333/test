/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.exportimport.data.handler.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.mobile.device.rules.constants.MDRPortletKeys;
import com.liferay.mobile.device.rules.exportimport.data.handler.MDRPortletDataHandler;
import com.liferay.mobile.device.rules.model.MDRRuleGroup;
import com.liferay.mobile.device.rules.model.MDRRuleGroupInstance;
import com.liferay.mobile.device.rules.util.test.MDRTestUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.lar.test.BasePortletDataHandlerTestCase;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.util.test.LayoutTestUtil;

import java.util.Map;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Zsolt Berentey
 */
@RunWith(Arquillian.class)
public class MDRPortletDataHandlerTest extends BasePortletDataHandlerTestCase {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Override
	protected void addParameters(Map<String, String[]> parameterMap) {
		addBooleanParameter(
			parameterMap, MDRPortletDataHandler.NAMESPACE, "actions", true);
		addBooleanParameter(
			parameterMap, MDRPortletDataHandler.NAMESPACE, "rules", true);
	}

	@Override
	protected void addStagedModels() throws Exception {
		Layout layout = LayoutTestUtil.addLayout(stagingGroup);

		MDRRuleGroup ruleGroup = MDRTestUtil.addRuleGroup(
			stagingGroup.getGroupId());

		MDRRuleGroupInstance ruleGroupInstance =
			MDRTestUtil.addRuleGroupInstance(
				stagingGroup.getGroupId(), Layout.class.getName(),
				layout.getPlid(), ruleGroup.getRuleGroupId());

		MDRTestUtil.addRule(ruleGroup.getRuleGroupId());

		MDRTestUtil.addAction(ruleGroupInstance.getRuleGroupInstanceId());

		ruleGroupInstance = MDRTestUtil.addRuleGroupInstance(
			stagingGroup.getGroupId(), ruleGroup.getRuleGroupId());

		MDRTestUtil.addAction(ruleGroupInstance.getRuleGroupInstanceId());
	}

	@Override
	protected String getPortletId() {
		return MDRPortletKeys.MOBILE_DEVICE_RULES;
	}

}