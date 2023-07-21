/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.internal.upgrade.v1_1_0;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Timothy Bell
 */
public class UpgradeRelease extends BaseUpgradeClassName {

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append("delete from Release_ where servletContextName = '");
		sb.append(_OLD_SERVLET_CONTEXT_NAME);
		sb.append(StringPool.APOSTROPHE);

		runSQL(sb.toString());
	}

	private static final String _OLD_SERVLET_CONTEXT_NAME =
		"asset-entry-set-portlet";

}