/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.customer.web.internal.upgrade;

import com.liferay.dynamic.data.mapping.service.persistence.DDMStructurePersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplatePersistence;
import com.liferay.journal.service.persistence.JournalArticlePersistence;
import com.liferay.osb.customer.web.internal.upgrade.v1_0_0.UpgradeDDMTemplates;
import com.liferay.osb.customer.web.internal.upgrade.v1_0_0.UpgradeSubscription;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.persistence.SubscriptionPersistence;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class OSBCustomerWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.osb.customer.web", "0.0.0", "1.0.0",
			new UpgradeDDMTemplates(
				_ddmStructurePersistence, _ddmTemplatePersistence,
				_journalArticlePersistence, _portletPreferencesLocalService),
			new UpgradeSubscription(_portal, _subscriptionPersistence));
	}

	@Reference
	private DDMStructurePersistence _ddmStructurePersistence;

	@Reference
	private DDMTemplatePersistence _ddmTemplatePersistence;

	@Reference
	private JournalArticlePersistence _journalArticlePersistence;

	@Reference
	private Portal _portal;

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

	@Reference
	private SubscriptionPersistence _subscriptionPersistence;

}