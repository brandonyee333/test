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

package com.liferay.osb.hook.upgrade.v3_9_2;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20180126163455001_ListType extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20180126163455001L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			28039, "postgresql-9.6",
			"com.liferay.osb.model.AccountEnvironment.envDB");
	}

}