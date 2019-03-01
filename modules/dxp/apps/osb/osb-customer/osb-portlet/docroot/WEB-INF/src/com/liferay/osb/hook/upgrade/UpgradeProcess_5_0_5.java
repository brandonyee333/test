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

import com.liferay.osb.hook.upgrade.v5_0_5.Upgrade_20190226150319201_ExternalIdMapper;
import com.liferay.osb.hook.upgrade.v5_0_5.Upgrade_20190301133748893_ListType;

/**
 * @author Kyle Bischof
 */
public class UpgradeProcess_5_0_5 extends BaseUpgradeProcess {

	@Override
	public int getThreshold() {
		return 505;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(Upgrade_20190226150319201_ExternalIdMapper.class);
		upgrade(Upgrade_20190301133748893_ListType.class);
	}

}