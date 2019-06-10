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

import com.liferay.osb.hook.upgrade.v5_0_4.Upgrade_20190122115519440_ListType;
import com.liferay.osb.hook.upgrade.v5_0_4.Upgrade_20190124135600134_AccountEnvironment;
import com.liferay.osb.hook.upgrade.v5_0_4.Upgrade_20190129102524044_AuditEntry;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.List;

/**
 * @author Jenny Chen
 */
public class UpgradeProcess_5_0_4 extends BaseUpgradeProcess {

	public List<UpgradeProcess> getManualUpgradeProcessClasses() {
		return ListUtil.toList(new Upgrade_20190129102524044_AuditEntry());
	}

	@Override
	public int getThreshold() {
		return 504;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgrade(Upgrade_20190122115519440_ListType.class);
		upgrade(Upgrade_20190124135600134_AccountEnvironment.class);
	}

}