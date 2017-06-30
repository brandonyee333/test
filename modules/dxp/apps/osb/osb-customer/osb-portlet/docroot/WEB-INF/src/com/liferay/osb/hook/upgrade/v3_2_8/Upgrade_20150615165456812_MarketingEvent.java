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

package com.liferay.osb.hook.upgrade.v3_2_8;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;

/**
 * @author Rachael Koestartyo
 */
public class Upgrade_20150615165456812_MarketingEvent
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20150615165456812L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!tableHasColumn("OSB_MarketingEvent", "imageFileEntryId")) {
			runSQL("alter table OSB_MarketingEvent add imageFileEntryId LONG");
		}

		if (!tableHasColumn("OSB_MarketingEvent", "slidesFileEntryId")) {
			runSQL("alter table OSB_MarketingEvent add slidesFileEntryId LONG");
		}
	}

}