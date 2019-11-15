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

package com.liferay.osb.customer.web.internal.upgrade;

import com.liferay.dynamic.data.mapping.service.persistence.DDMStructurePersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplatePersistence;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.service.persistence.JournalArticlePersistence;
import com.liferay.osb.customer.web.internal.upgrade.v1_0_0.UpgradeDDMTemplates;
import com.liferay.osb.customer.web.internal.upgrade.v1_0_0.UpgradeResourceAction;
import com.liferay.osb.customer.web.internal.upgrade.v1_0_0.UpgradeSubscription;
import com.liferay.osb.customer.web.internal.upgrade.v1_0_1.UpgradeJournalArticle;
import com.liferay.osb.customer.web.internal.upgrade.v1_1_0.UpgradeJournalArticles;
import com.liferay.osb.customer.web.internal.upgrade.v1_2_0.UpgradeJournalArticleURLTitle;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.persistence.ResourceActionPersistence;
import com.liferay.portal.kernel.service.persistence.ResourcePermissionPersistence;
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
			new UpgradeResourceAction(
				_resourceActionPersistence, _resourcePermissionPersistence),
			new UpgradeSubscription(_portal, _subscriptionPersistence));

		registry.register(
			"com.liferay.osb.customer.web", "1.0.0", "1.0.1",
			new UpgradeJournalArticle(_journalArticlePersistence));

		registry.register(
			"com.liferay.osb.customer.web", "1.0.1", "1.1.0",
			new UpgradeJournalArticles(_journalArticlePersistence));

		registry.register(
			"com.liferay.osb.customer.web", "1.1.0", "1.2.0",
			new UpgradeJournalArticleURLTitle(
				_journalArticleLocalService, _journalArticlePersistence));
	}

	@Reference
	private DDMStructurePersistence _ddmStructurePersistence;

	@Reference
	private DDMTemplatePersistence _ddmTemplatePersistence;

	@Reference
	private JournalArticleLocalService _journalArticleLocalService;

	@Reference
	private JournalArticlePersistence _journalArticlePersistence;

	@Reference
	private Portal _portal;

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

	@Reference
	private ResourceActionPersistence _resourceActionPersistence;

	@Reference
	private ResourcePermissionPersistence _resourcePermissionPersistence;

	@Reference
	private SubscriptionPersistence _subscriptionPersistence;

}