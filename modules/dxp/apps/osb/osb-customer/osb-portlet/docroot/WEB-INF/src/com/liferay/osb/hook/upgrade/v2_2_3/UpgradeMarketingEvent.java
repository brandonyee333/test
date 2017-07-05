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

package com.liferay.osb.hook.upgrade.v2_2_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.osb.model.impl.MarketingEventModelImpl;

/**
 * @author Rachael Koestartyo
 */
public class UpgradeMarketingEvent extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		updateMarketingEvent();
	}

	protected void updateMarketingEvent() throws Exception {
		if (hasTable("OSB_MarketingEvent")) {
			return;
		}

		runSQL(MarketingEventModelImpl.TABLE_SQL_CREATE);

		runSQL("create index IX_F81473A on OSB_MarketingEvent (type_)");
		runSQL("create index IX_EED5EFA6 on OSB_MarketingEvent (globalRegion)");
	}

}