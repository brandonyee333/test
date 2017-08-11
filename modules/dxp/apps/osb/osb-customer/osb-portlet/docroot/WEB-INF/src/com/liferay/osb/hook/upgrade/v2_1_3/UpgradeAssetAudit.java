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

package com.liferay.osb.hook.upgrade.v2_1_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

*/

/**
 * @author Ryan Park
 */
public class UpgradeAssetAudit extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	protected void doUpgrade() throws Exception {
		updateAssetAudit();
	}

	protected void updateAssetAudit() throws Exception {
		runSQL("alter table OSB_AssetAudit add column legalEntityName STRING");
		runSQL("alter table OSB_AssetAudit add column vendorClassNameId LONG");
		runSQL("alter table OSB_AssetAudit add column vendorClassPK LONG");

		StringBundler sb = new StringBundler(5);

		sb.append("update OSB_AssetAudit inner join OSB_AppEntry on ");
		sb.append("OSB_AppEntry.appEntryId = OSB_AssetAudit.classPK ");
		sb.append("set OSB_AssetAudit.vendorClassNameId = ");
		sb.append("OSB_AppEntry.ownerClassNameId, ");
		sb.append("OSB_AssetAudit.vendorClassPK = OSB_AppEntry.ownerClassPK");

		runSQL(sb.toString());
	}

	 */

}