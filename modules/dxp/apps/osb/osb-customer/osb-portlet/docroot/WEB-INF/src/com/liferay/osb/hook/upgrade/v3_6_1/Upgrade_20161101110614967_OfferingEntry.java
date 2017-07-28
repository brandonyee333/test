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

package com.liferay.osb.hook.upgrade.v3_6_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

*/

/**
 * @author Amos Fong
 */
public class Upgrade_20161101110614967_OfferingEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20161101110614967L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasIndex("OSB_OfferingEntry", "IX_E0CF6A9F")) {
			runSQL(
				"create index IX_E0CF6A9F on OSB_OfferingEntry " +
					"(supportEndDate)");
		}

		if (!hasIndex("OSB_OfferingEntry", "IX_CE314FD7")) {
			runSQL("create index IX_CE314FD7 on OSB_OfferingEntry (version)");
		}
	}

}

*/

}