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

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Amos Fong
 */
public class UpgradeDDMTemplates extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		ugpradeKey("84109", "CENTER-BLOCKS");
		ugpradeKey("122947", "KB-BANNER");
		ugpradeKey("122950", "LOWER-BLOCKS");
		ugpradeKey("ARTICLE-DISPLAY-6.2.10.1", "ARTICLE-DISPLAY");
		ugpradeKey("HOME-PAGE-SEARCH-BAR-6.2.10.1", "HOME-PAGE-SEARCH-BAR");
	}

	protected void ugpradeKey(String oldKey, String newKey) throws Exception {
		runSQL(
			"update DDMStructure set structureKey = '" + newKey + "' where " +
				"structureKey = '" + oldKey + "'");
		runSQL(
			"update DDMTemplate set templateKey = '" + newKey + "' where " +
				"templateKey = '" + oldKey + "'");
		runSQL(
			"update JournalArticle set DDMStructureKey = '" + newKey + "' " +
				"where DDMStructureKey = '" + oldKey + "'");
		runSQL(
			"update JournalArticle set DDMTemplateKey = '" + newKey + "' " +
				"where DDMTemplateKey = '" + oldKey + "'");
	}

}