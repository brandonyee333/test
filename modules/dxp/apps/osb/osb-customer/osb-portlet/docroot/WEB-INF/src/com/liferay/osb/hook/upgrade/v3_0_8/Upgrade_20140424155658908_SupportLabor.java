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

package com.liferay.osb.hook.upgrade.v3_0_8;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.impl.SupportLaborModelImpl;

*/

/**
 * @author Brent Krone-Schmidt
 */
public class Upgrade_20140424155658908_SupportLabor extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20140424155658908L;
	}

	protected void createSupportLabor() throws Exception {
		if (!hasTable(SupportLaborModelImpl.TABLE_NAME)) {
			runSQL(SupportLaborModelImpl.TABLE_SQL_CREATE);
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		createSupportLabor();
	}

}

*/

}