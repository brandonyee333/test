/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.admin.web.internal.upgrade;

import com.liferay.journal.service.JournalArticleResourceLocalService;
import com.liferay.layout.admin.web.internal.upgrade.v_1_0_0.UpgradeLayout;
import com.liferay.layout.admin.web.internal.upgrade.v_1_0_1.UpgradeLayoutType;
import com.liferay.layout.admin.web.internal.upgrade.v_1_0_2.UpgradeLayoutSetTypeSettings;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.upgrade.DummyUpgradeStep;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import java.rmi.registry.Registry;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class LayoutAdminWebUpgrade implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"com.liferay.layout.admin.web", "0.0.0", "1.0.0",
			new DummyUpgradeStep());

		registry.register(
			"com.liferay.layout.admin.web", "0.0.1", "1.0.0",
			new UpgradeLayout());

		registry.register(
			"com.liferay.layout.admin.web", "1.0.0", "1.0.1",
			new UpgradeLayoutType(_journalArticleResourceLocalService));

		registry.register(
			"com.liferay.layout.admin.web", "1.0.1", "1.0.2",
			new UpgradeLayoutSetTypeSettings(
				_groupLocalService, _layoutSetLocalService));
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(unbind = "-")
	protected void setJournalArticleResourceLocalService(
		JournalArticleResourceLocalService journalArticleResourceLocalService) {

		_journalArticleResourceLocalService =
			journalArticleResourceLocalService;
	}

	@Reference(unbind = "-")
	protected void setLayoutSetLocalService(
		LayoutSetLocalService layoutSetLocalService) {

		_layoutSetLocalService = layoutSetLocalService;
	}

	private GroupLocalService _groupLocalService;
	private JournalArticleResourceLocalService
		_journalArticleResourceLocalService;
	private LayoutSetLocalService _layoutSetLocalService;

}