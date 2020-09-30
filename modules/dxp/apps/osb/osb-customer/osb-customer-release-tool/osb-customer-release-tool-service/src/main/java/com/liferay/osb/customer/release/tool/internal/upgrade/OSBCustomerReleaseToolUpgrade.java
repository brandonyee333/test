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

package com.liferay.osb.customer.release.tool.internal.upgrade;

import com.liferay.osb.customer.release.tool.internal.upgrade.v1_0_1.UpgradeJIRAComponent;
import com.liferay.osb.customer.release.tool.service.JIRAComponentLocalService;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class OSBCustomerReleaseToolUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.osb.customer.release.tool.service", "1.0.0", "1.0.1",
			new UpgradeJIRAComponent(_jiraComponentLocalService));
	}

	@Reference
	private JIRAComponentLocalService _jiraComponentLocalService;

}