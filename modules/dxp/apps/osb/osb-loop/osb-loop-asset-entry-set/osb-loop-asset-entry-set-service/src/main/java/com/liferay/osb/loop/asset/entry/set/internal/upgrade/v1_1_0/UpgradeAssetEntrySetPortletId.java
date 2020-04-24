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

package com.liferay.osb.loop.asset.entry.set.internal.upgrade.v1_1_0;

import com.liferay.osb.loop.asset.entry.set.constants.AssetEntrySetPortletKeys;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Timothy Bell
 */
public class UpgradeAssetEntrySetPortletId extends BaseUpgradePortletId {

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(9);

		sb.append("update Repository set name = '");
		sb.append(AssetEntrySetPortletKeys.ASSET_ENTRY_SET);
		sb.append("', portletId = '");
		sb.append(AssetEntrySetPortletKeys.ASSET_ENTRY_SET);
		sb.append("' where name = '");
		sb.append(_OLD_PORTLET_ID);
		sb.append("' and portletId = '");
		sb.append(_OLD_PORTLET_ID);
		sb.append(StringPool.APOSTROPHE);

		runSQL(sb.toString());

		super.doUpgrade();
	}

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {
			{_OLD_PORTLET_ID, AssetEntrySetPortletKeys.ASSET_ENTRY_SET}
		};
	}

	private static final String _OLD_PORTLET_ID = "1_WAR_assetentrysetportlet";

}