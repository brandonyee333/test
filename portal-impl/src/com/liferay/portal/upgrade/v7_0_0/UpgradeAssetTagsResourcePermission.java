/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.v7_0_0;

import com.liferay.asset.kernel.model.AssetTag;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Andrew Betts
 */
public class UpgradeAssetTagsResourcePermission extends UpgradeProcess {

	@Override
	public void doUpgrade() throws Exception {
		deleteResourcePermissions();
	}

	protected void deleteResourcePermissions() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			runSQL(
				StringBundler.concat(
					"delete from ResourcePermission where name = '",
					AssetTag.class.getName(), "' and scope = ",
					String.valueOf(ResourceConstants.SCOPE_INDIVIDUAL)));
		}
	}

}