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

package com.liferay.osb.hook.upgrade.v3_4_2;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20160203171140535_OfferingEntry
	extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"alter table OSB_OfferingEntry change processorCoresAllowed " +
				"instanceSize INTEGER");

		runSQL("update OSB_OfferingEntry set instanceSize = 1");
	}

}