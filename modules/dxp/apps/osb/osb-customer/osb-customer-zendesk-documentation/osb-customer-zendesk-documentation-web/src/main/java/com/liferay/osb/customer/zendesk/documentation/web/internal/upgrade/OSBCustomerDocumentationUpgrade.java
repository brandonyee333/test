/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.documentation.web.internal.upgrade;

import com.liferay.osb.customer.zendesk.documentation.web.internal.upgrade.v1_0_0.UpgradeZendeskCategory;
import com.liferay.osb.customer.zendesk.documentation.web.internal.upgrade.v1_1_0.UpgradeZendeskArticle;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class OSBCustomerDocumentationUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.osb.customer.zendesk.documentation.web", "0.0.0",
			"1.0.0", new UpgradeZendeskCategory());

		registry.register(
			"com.liferay.osb.customer.zendesk.documentation.web", "1.0.0",
			"1.1.0", new UpgradeZendeskArticle());
	}

}