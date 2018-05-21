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