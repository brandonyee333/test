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

import com.liferay.osb.hook.upgrade.v2_0_2.UpgradeAccountEntry;
import com.liferay.osb.hook.upgrade.v2_0_2.UpgradeAuditEntry;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Lin Cui
 * @author Alan Zhang
 */
public class UpgradeProcess_2_0_2 extends UpgradeProcess {

	@Override
	public int getThreshold() {
		return 202;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(UpgradeAccountEntry.class);
		upgrade(UpgradeAuditEntry.class);
	}

}