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

package com.liferay.osb.hook.upgrade.v5_0_4;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20190124135600134_AccountEnvironment
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20190124135600134L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"alter table OSB_AccountEnvironment add column envCommerce " +
				"INTEGER");
	}

}