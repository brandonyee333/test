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

package com.liferay.osb.hook.upgrade.v3_3_5;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20150921155804447_ProductEntry extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150921155804447L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL("alter table OSB_ProductEntry add column type_ INTEGER");
		runSQL("alter table OSB_ProductEntry add column platform VARCHAR(75)");
		runSQL(
			"alter table OSB_ProductEntry add column platformVersion " +
				"VARCHAR(75)");

		/*runSQL(
			"update OSB_ProductEntry set platform = '" +
				ProductEntryConstants.PLATFORM_LIFERAY_PORTAL + "'");*/
	}

}