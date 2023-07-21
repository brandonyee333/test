/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.internal.upgrade.v1_1_0;

import com.liferay.osb.loop.constants.LoopPortletKeys;
import com.liferay.portal.kernel.upgrade.BaseUpgradePortletId;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Calvin Keum
 */
public class UpgradeLoopPortletId extends BaseUpgradePortletId {

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(9);

		sb.append("update Repository set name = '");
		sb.append(LoopPortletKeys.LOOP);
		sb.append("', portletId = '");
		sb.append(LoopPortletKeys.LOOP);
		sb.append("' where name = '");
		sb.append(_OLD_PORTLET_KEY);
		sb.append("' and portletId = '");
		sb.append(_OLD_PORTLET_KEY);
		sb.append(StringPool.APOSTROPHE);

		runSQL(sb.toString());

		super.doUpgrade();
	}

	@Override
	protected String[][] getRenamePortletIdsArray() {
		return new String[][] {{_OLD_PORTLET_KEY, LoopPortletKeys.LOOP}};
	}

	private static final String _OLD_PORTLET_KEY = "1_WAR_loopportlet";

}