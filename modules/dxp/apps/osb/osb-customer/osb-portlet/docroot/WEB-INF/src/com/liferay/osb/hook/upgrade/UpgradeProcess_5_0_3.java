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

package com.liferay.osb.hook.upgrade;

import com.liferay.osb.hook.upgrade.v5_0_3.Upgrade_20190117102736593_PartnerWorker;
import com.liferay.osb.hook.upgrade.v5_0_3.Upgrade_20190117102807158_AccountWorker;
import com.liferay.osb.hook.upgrade.v5_0_3.Upgrade_20190122115519440_ListType;
import com.liferay.osb.hook.upgrade.v5_0_3.Upgrade_20190122121242433_ProductEntry;
import com.liferay.osb.hook.upgrade.v5_0_3.Upgrade_20190124135600134_AccountEnvironment;

/**
 * @author Kyle Bischof
 */
public class UpgradeProcess_5_0_3 extends BaseUpgradeProcess {

	@Override
	public int getThreshold() {
		return 503;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(Upgrade_20190117102807158_AccountWorker.class);
		upgrade(Upgrade_20190117102736593_PartnerWorker.class);
		upgrade(Upgrade_20190122115519440_ListType.class);
		upgrade(Upgrade_20190122121242433_ProductEntry.class);
		upgrade(Upgrade_20190124135600134_AccountEnvironment.class);
	}

}