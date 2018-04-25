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

package com.liferay.osb.hook.upgrade.v3_7_6;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20170605135441570_ProductEntry extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20170605135441570L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		transferAssets(
			_PRODUCT_ENTRY_PARTNER_ID, _PRODUCT_ENTRY_PORTAL_LIMITED_ID);
		transferAssets(
			_PRODUCT_ENTRY_PORTAL_ENTERPRISE_BACKUP_ID,
			_PRODUCT_ENTRY_PORTAL_BACKUP_ID);
		transferAssets(
			_PRODUCT_ENTRY_PORTAL_ENTERPRISE_NON_PROD_ID,
			_PRODUCT_ENTRY_PORTAL_NON_PROD_ID);
	}

	protected void transferAssets(
			long oldProductEntryId, long newProductEntryId)
		throws Exception {

		runSQL(
			"update OSB_AccountEnvironment set productEntryId = " +
				newProductEntryId + " where productEntryId = " +
					oldProductEntryId);
		runSQL(
			"update OSB_LicenseKey set productEntryId = " + newProductEntryId +
				" where productEntryId = " + oldProductEntryId);
		runSQL(
			"update OSB_OfferingEntry set productEntryId = " +
				newProductEntryId + " where productEntryId = " +
					oldProductEntryId);
	}

	private static final long _PRODUCT_ENTRY_PARTNER_ID = 6820501;

	private static final long _PRODUCT_ENTRY_PORTAL_BACKUP_ID = 5968272;

	private static final long _PRODUCT_ENTRY_PORTAL_ENTERPRISE_BACKUP_ID =
		63869128;

	private static final long _PRODUCT_ENTRY_PORTAL_ENTERPRISE_NON_PROD_ID =
		57397429;

	private static final long _PRODUCT_ENTRY_PORTAL_LIMITED_ID = 11342216;

	private static final long _PRODUCT_ENTRY_PORTAL_NON_PROD_ID = 5968274;

}