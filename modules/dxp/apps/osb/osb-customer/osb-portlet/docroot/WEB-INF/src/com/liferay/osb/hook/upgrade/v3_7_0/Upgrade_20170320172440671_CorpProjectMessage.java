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

package com.liferay.osb.hook.upgrade.v3_7_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Kyle Bischof
 */
public class Upgrade_20170320172440671_CorpProjectMessage
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20170320172440671L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasIndex("OSB_CorpProjectMessage", "IX_73BC3756")) {
			return;
		}

		runSQL("create index IX_73BC3756 on OSB_CorpProjectMessage (type_)");
	}

}

*/

}