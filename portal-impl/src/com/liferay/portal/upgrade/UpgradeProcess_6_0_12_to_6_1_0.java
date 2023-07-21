/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade;

import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.upgrade.v6_0_12_to_6_1_0.UpgradeAsset;
import com.liferay.portal.upgrade.v6_0_12_to_6_1_0.UpgradeCompanyId;
import com.liferay.portal.upgrade.v6_0_12_to_6_1_0.UpgradeDocumentLibrary;
import com.liferay.portal.upgrade.v6_0_12_to_6_1_0.UpgradeDynamicDataMapping;
import com.liferay.portal.upgrade.v6_0_12_to_6_1_0.UpgradeMessageBoards;
import com.liferay.portal.upgrade.v6_0_12_to_6_1_0.UpgradePermission;
import com.liferay.portal.upgrade.v6_0_12_to_6_1_0.UpgradePortletPreferences;
import com.liferay.portal.upgrade.v6_0_12_to_6_1_0.UpgradeSchema;
import com.liferay.portal.upgrade.v6_0_12_to_6_1_0.UpgradeUserName;
import com.liferay.portal.upgrade.v6_1_0.UpgradeAdminPortlets;
import com.liferay.portal.upgrade.v6_1_0.UpgradeAssetPublisher;
import com.liferay.portal.upgrade.v6_1_0.UpgradeCamelCasePortletPreferences;
import com.liferay.portal.upgrade.v6_1_0.UpgradeCountry;
import com.liferay.portal.upgrade.v6_1_0.UpgradeGroup;
import com.liferay.portal.upgrade.v6_1_0.UpgradeIFrame;
import com.liferay.portal.upgrade.v6_1_0.UpgradeImageGallery;
import com.liferay.portal.upgrade.v6_1_0.UpgradeJournal;
import com.liferay.portal.upgrade.v6_1_0.UpgradeLayout;
import com.liferay.portal.upgrade.v6_1_0.UpgradeNavigation;
import com.liferay.portal.upgrade.v6_1_0.UpgradeSubscription;
import com.liferay.portal.upgrade.v6_1_0.UpgradeWorkflow;

/**
 * @author Matthew Kong
 */
public class UpgradeProcess_6_0_12_to_6_1_0 extends Pre7UpgradeProcess {

	@Override
	public int getThreshold() {
		return ReleaseInfo.RELEASE_6_1_0_BUILD_NUMBER;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeSchema.class);

		upgrade(UpgradeCompanyId.class);
		upgrade(UpgradeUserName.class);

		upgrade(UpgradeAdminPortlets.class);
		upgrade(UpgradeCamelCasePortletPreferences.class);
		upgrade(UpgradeCountry.class);
		upgrade(UpgradeDocumentLibrary.class);
		upgrade(UpgradeDynamicDataMapping.class);
		upgrade(UpgradeGroup.class);
		upgrade(UpgradeIFrame.class);
		upgrade(UpgradeImageGallery.class);
		upgrade(UpgradeJournal.class);
		upgrade(UpgradeLayout.class);
		upgrade(UpgradeMessageBoards.class);
		upgrade(UpgradeNavigation.class);
		upgrade(UpgradePermission.class);
		upgrade(UpgradePortletPreferences.class);
		upgrade(UpgradeSubscription.class);
		upgrade(UpgradeWorkflow.class);

		upgrade(UpgradeAsset.class);
		upgrade(UpgradeAssetPublisher.class);

		clearIndexesCache();
	}

}