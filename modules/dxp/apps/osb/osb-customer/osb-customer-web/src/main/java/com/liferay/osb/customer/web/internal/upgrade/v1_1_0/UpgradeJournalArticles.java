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