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

package com.liferay.osb.hook.upgrade.v3_7_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEntryConstants;

/**
 * @author Amos Fong
 */
public class Upgrade_20170424172241881_ListType extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20170424172241881L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			35028, "food-services", AccountEntryConstants.LIST_TYPE_INDUSTRY);
	}

}