/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.sharing.internal.upgrade.v1_1_0;

import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;

/**
 * @author Calvin Keum
 */
public class UpgradeAssetSharingPortletId extends BaseUpgradePortletId {

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{
				"1_WAR_assetsharingportlet",
				"com_liferay_osb_loop_asset_sharing_portlet_" +
					"LoopAssetSharingPortlet"
			}
		};
	}

}