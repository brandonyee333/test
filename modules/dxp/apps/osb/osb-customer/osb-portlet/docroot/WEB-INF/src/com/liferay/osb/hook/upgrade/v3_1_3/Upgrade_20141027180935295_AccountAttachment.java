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

package com.liferay.osb.hook.upgrade.v3_1_3;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20141027180935295_AccountAttachment
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20141027180935295L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"alter table OSB_AccountAttachment change fileSize fileSize LONG");
	}

}