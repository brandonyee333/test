/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.internal.upgrade;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.liferay.portal.workflow.kaleo.internal.upgrade.v1_0_0.UpgradeKaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.internal.upgrade.v1_1_0.UpgradeWorkflowContext;
import com.liferay.portal.workflow.kaleo.internal.upgrade.v1_2_1.UpgradeKaleoLog;
import com.liferay.portal.workflow.kaleo.internal.upgrade.v1_2_1.UpgradeKaleoNotificationRecipient;
import com.liferay.portal.workflow.kaleo.internal.upgrade.v1_3_0.UpgradeClassNames;
import com.liferay.portal.workflow.kaleo.internal.upgrade.v1_3_0.UpgradeKaleoAction;
import com.liferay.portal.workflow.kaleo.internal.upgrade.v1_3_0.UpgradeKaleoDefinition;
import com.liferay.portal.workflow.kaleo.internal.upgrade.v1_3_2.UpgradeKaleoClassNameAndKaleoClassPK;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class KaleoServiceUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.portal.workflow.kaleo.service", "0.0.1", "1.0.0",
			new UpgradeKaleoTaskInstanceToken(),
			new com.liferay.portal.workflow.kaleo.internal.upgrade.v1_0_0.
				UpgradeSchema());

		registry.register(
			"com.liferay.portal.workflow.kaleo.service", "1.0.0", "1.1.0",
			new UpgradeWorkflowContext());

		registry.register(
			"com.liferay.portal.workflow.kaleo.service", "1.1.0", "1.2.0",
			new com.liferay.portal.workflow.kaleo.internal.upgrade.v1_2_0.
				UpgradeSchema());

		registry.register(
			"com.liferay.portal.workflow.kaleo.service", "1.2.0", "1.2.1",
			new UpgradeKaleoLog(), new UpgradeKaleoNotificationRecipient());

		registry.register(
			"com.liferay.portal.workflow.kaleo.service", "1.2.1", "1.3.0",
			new UpgradeClassNames(), new UpgradeKaleoAction(),
			new UpgradeKaleoDefinition());

		registry.register(
			"com.liferay.portal.workflow.kaleo.service", "1.3.0", "1.3.1",
			new com.liferay.portal.workflow.kaleo.internal.upgrade.v1_3_1.
				UpgradeSchema());

		registry.register(
			"com.liferay.portal.workflow.kaleo.service", "1.3.1", "1.3.2",
			new UpgradeKaleoClassNameAndKaleoClassPK());
	}

}