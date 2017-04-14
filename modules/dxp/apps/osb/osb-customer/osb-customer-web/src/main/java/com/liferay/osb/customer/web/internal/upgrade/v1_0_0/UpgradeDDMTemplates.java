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

package com.liferay.osb.customer.web.internal.upgrade.v1_0_0;

import com.liferay.dynamic.data.mapping.service.persistence.DDMStructurePersistence;
import com.liferay.dynamic.data.mapping.service.persistence.DDMTemplatePersistence;
import com.liferay.journal.service.persistence.JournalArticlePersistence;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Amos Fong
 */
public class UpgradeDDMTemplates extends UpgradeProcess {

	public UpgradeDDMTemplates(
		DDMStructurePersistence ddmStructurePersistence,
		DDMTemplatePersistence ddmTemplatePersistence,
		JournalArticlePersistence journalArticlePersistence,
		PortletPreferencesLocalService portletPreferencesLocalService) {

		_ddmStructurePersistence = ddmStructurePersistence;
		_ddmTemplatePersistence = ddmTemplatePersistence;
		_journalArticlePersistence = journalArticlePersistence;
		_portletPreferencesLocalService = portletPreferencesLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradePortletPreferences(
			"ARTICLE-DISPLAY-6.2.10.1", "ARTICLE-DISPLAY");

		upgradeStructureKeys("84103", "CENTER-BLOCKS");
		upgradeStructureKeys("122953", "KB-BANNER");
		upgradeStructureKeys("122955", "LOWER-BLOCKS");
		upgradeStructureKeys("ARTICLE-DISPLAY-6.2.10.1", "ARTICLE-DISPLAY");
		upgradeStructureKeys(
			"HOME-PAGE-SEARCH-BAR-6.2.10.1", "HOME-PAGE-SEARCH-BAR");

		upgradeTemplateKeys("84109", "CENTER-BLOCKS");
		upgradeTemplateKeys("122947", "KB-BANNER");
		upgradeTemplateKeys("122950", "LOWER-BLOCKS");
		upgradeTemplateKeys("ARTICLE-DISPLAY-6.2.10.1", "ARTICLE-DISPLAY");
		upgradeTemplateKeys(
			"HOME-PAGE-SEARCH-BAR-6.2.10.1", "HOME-PAGE-SEARCH-BAR");

		_ddmStructurePersistence.clearCache();
		_ddmTemplatePersistence.clearCache();
		_journalArticlePersistence.clearCache();
	}

	protected void upgradePortletPreferences(
			String oldStructureKey, String newStructureKey)
		throws Exception {

		PortletPreferences portletPreferences =
			_portletPreferencesLocalService.fetchPortletPreferences(86044);

		if (portletPreferences == null) {
			return;
		}

		String preferences = portletPreferences.getPreferences();

		portletPreferences.setPreferences(
			preferences.replace(oldStructureKey, newStructureKey));

		_portletPreferencesLocalService.updatePortletPreferences(
			portletPreferences);
	}

	protected void upgradeStructureKeys(
			String oldStructureKey, String newStructureKey)
		throws Exception {

		runSQL(
			"update DDMStructure set structureKey = '" + newStructureKey +
				"' where structureKey = '" + oldStructureKey + "'");
		runSQL(
			"update JournalArticle set DDMStructureKey = '" + newStructureKey +
				"' where DDMStructureKey = '" + oldStructureKey + "'");
	}

	protected void upgradeTemplateKeys(
			String oldTemplateKey, String newTemplateKey)
		throws Exception {

		runSQL(
			"update DDMTemplate set templateKey = '" + newTemplateKey +
				"' where templateKey = '" + oldTemplateKey + "'");
		runSQL(
			"update JournalArticle set DDMTemplateKey = '" + newTemplateKey +
				"' where DDMTemplateKey = '" + oldTemplateKey + "'");
	}

	private final DDMStructurePersistence _ddmStructurePersistence;
	private final DDMTemplatePersistence _ddmTemplatePersistence;
	private final JournalArticlePersistence _journalArticlePersistence;
	private final PortletPreferencesLocalService
		_portletPreferencesLocalService;

}