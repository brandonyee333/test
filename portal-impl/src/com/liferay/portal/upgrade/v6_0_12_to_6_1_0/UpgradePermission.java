/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v6_0_12_to_6_1_0;

/**
 * @author Alexander Chow
 * @author Connor McKay
 */
public class UpgradePermission
	extends com.liferay.portal.upgrade.v6_1_0.UpgradePermission {

	@Override
	protected void doUpgrade() throws Exception {
		convertResourcePermissions(
			"com.liferay.portlet.bookmarks.model.BookmarksEntry",
			"BookmarksEntry", "entryId");
		convertResourcePermissions(
			"com.liferay.portlet.bookmarks.model.BookmarksFolder",
			"BookmarksFolder", "folderId");
	}

}