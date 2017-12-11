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

package com.liferay.osb.customer.web.internal.upgrade.v1_1_0;

import com.liferay.journal.service.persistence.JournalArticlePersistence;
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
			"update JournalArticle set DDMStructureKey = '" + newStructureKey +
				"' where DDMStructureKey = '" + oldStructureKey + "'");
	}

	protected void upgradeTemplateKeys(
			String oldTemplateKey, String newTemplateKey)
		throws Exception {

		runSQL(
			"update JournalArticle set DDMTemplateKey = '" + newTemplateKey +
				"' where DDMTemplateKey = '" + oldTemplateKey + "'");
	}

	private final JournalArticlePersistence _journalArticlePersistence;

}