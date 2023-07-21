/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.web.internal.upgrade.v1_1_0;

import com.liferay.journal.service.persistence.JournalArticlePersistence;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Amos Fong
 */
public class UpgradeJournalArticles extends UpgradeProcess {

	public UpgradeJournalArticles(
		JournalArticlePersistence journalArticlePersistence) {

		_journalArticlePersistence = journalArticlePersistence;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeStructureKeys(
			"ARTICLE-DISPLAY", "KNOWLEDGE-BASE-THEME---ARTICLE-DISPLAY");
		upgradeStructureKeys(
			"CENTER-BLOCKS", "KNOWLEDGE-BASE-THEME---CENTER-BLOCKS");
		upgradeStructureKeys(
			"HOME-PAGE-SEARCH-BAR",
			"KNOWLEDGE-BASE-THEME---HOME-PAGE-SEARCH-BAR");
		upgradeStructureKeys("KB-BANNER", "KNOWLEDGE-BASE-THEME---KB-BANNER");
		upgradeStructureKeys(
			"LOWER-BLOCKS", "KNOWLEDGE-BASE-THEME---LOWER-BLOCKS");

		upgradeTemplateKeys(
			"ARTICLE-DISPLAY", "KNOWLEDGE-BASE-THEME---ARTICLE-DISPLAY");
		upgradeTemplateKeys(
			"CENTER-BLOCKS", "KNOWLEDGE-BASE-THEME---CENTER-BLOCKS");
		upgradeTemplateKeys(
			"HOME-PAGE-SEARCH-BAR",
			"KNOWLEDGE-BASE-THEME---HOME-PAGE-SEARCH-BAR");
		upgradeTemplateKeys("KB-BANNER", "KNOWLEDGE-BASE-THEME---KB-BANNER");
		upgradeTemplateKeys(
			"LOWER-BLOCKS", "KNOWLEDGE-BASE-THEME---LOWER-BLOCKS");

		_journalArticlePersistence.clearCache();
	}

	protected void upgradeStructureKeys(
			String oldStructureKey, String newStructureKey)
		throws Exception {

		runSQL(
			StringBundler.concat(
				"update JournalArticle set DDMStructureKey = '",
				newStructureKey, "' where DDMStructureKey = '", oldStructureKey,
				"'"));
	}

	protected void upgradeTemplateKeys(
			String oldTemplateKey, String newTemplateKey)
		throws Exception {

		runSQL(
			StringBundler.concat(
				"update JournalArticle set DDMTemplateKey = '", newTemplateKey,
				"' where DDMTemplateKey = '", oldTemplateKey, "'"));
	}

	private final JournalArticlePersistence _journalArticlePersistence;

}