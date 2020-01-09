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

package com.liferay.osb.customer.admin.internal.upgrade.v1_0_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Amos Fong
 */
public class UpgradeSchema extends UpgradeProcess {

	public UpgradeSchema() {
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"alter table OSB_AccountEntry add column koroneikiAccountKey " +
				"varchar(75)");
		runSQL(
			"alter table OSB_ProductEntry add column koroneikiProductKey " +
				"varchar(75)");
	}

}