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

package com.liferay.osb.hook.upgrade.v3_0_5;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

*/

/**
 * @author Douglas Wong
 */
public class Upgrade_20140710141954379_AppVersion extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20140710141954379L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (tableHasColumn("OSB_AppVersion", "versionOrder")) {
			return;
		}

		runSQL(
			"ALTER TABLE OSB_AppVersion ADD COLUMN versionOrder INTEGER " +
				"DEFAULT 0");

		StringBundler sb = new StringBundler(10);

		sb.append("UPDATE OSB_AppVersion appVersion1 INNER JOIN ");
		sb.append("(SELECT appVersion2.appEntryId, appVersion2.appVersionId, ");
		sb.append("COUNT(appVersion3.appVersionId) AS versionOrder FROM ");
		sb.append("OSB_AppVersion appVersion2 INNER JOIN OSB_AppVersion ");
		sb.append("appVersion3 ON appVersion2.appEntryId = ");
		sb.append("appVersion3.appEntryId WHERE appVersion2.createDate > ");
		sb.append("appVersion3.createDate GROUP BY appVersion2.appEntryId, ");
		sb.append("appVersion2.appVersionId) TEMP ON ");
		sb.append("appVersion1.appVersionId = TEMP.appVersionId SET ");
		sb.append("appVersion1.versionOrder = TEMP.versionOrder");

		runSQL(sb.toString());
	}

	 */

}