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

package com.liferay.osb.hook.upgrade.v5_0_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20190122115519440_ListType extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20190122115519440L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			44000, "1.0",
			"com.liferay.osb.model.ProductEntry.commerceAllVersions");
		insertListType(
			44020, "1.1",
			"com.liferay.osb.model.ProductEntry.commerceAllVersions");
		insertListType(
			45000, "1",
			"com.liferay.osb.model.ProductEntry.commerceMajorVersions");
		insertListType(
			46000, "1.0",
			"com.liferay.osb.model.ProductEntry.commerceMinorVersions");
		insertListType(
			46001, "1.1",
			"com.liferay.osb.model.ProductEntry.commerceMinorVersions");
	}

}