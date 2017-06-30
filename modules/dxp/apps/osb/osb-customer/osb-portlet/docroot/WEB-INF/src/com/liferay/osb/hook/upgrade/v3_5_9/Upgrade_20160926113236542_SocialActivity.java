/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.hook.upgrade.v3_5_9;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Amos Fong
 */
public class Upgrade_20160926113236542_SocialActivity
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160926113236542L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"delete from SocialActivity where classNameId = 10057 and " +
				"classPK not in (select messageId from MBMessage)");
	}

}