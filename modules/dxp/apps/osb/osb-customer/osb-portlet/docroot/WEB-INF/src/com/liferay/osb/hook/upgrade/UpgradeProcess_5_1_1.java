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

import com.liferay.osb.hook.upgrade.v5_1_1.UpgradeAccountEntry;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jenny Chen
 */
public class UpgradeProcess_5_1_1 extends BaseUpgradeProcess {

	public List<UpgradeProcess> getManualUpgradeProcessClasses() {
		List<UpgradeProcess> manualUpgrades = new ArrayList<>();

		manualUpgrades.add(new UpgradeAccountEntry());

		return manualUpgrades;
	}

	@Override
	public int getThreshold() {
		return 511;
	}

	@Override
	protected void doUpgrade() throws Exception {
	}

}