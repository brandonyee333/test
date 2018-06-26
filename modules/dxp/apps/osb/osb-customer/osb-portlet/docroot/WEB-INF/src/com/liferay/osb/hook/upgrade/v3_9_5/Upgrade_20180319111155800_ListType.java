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

package com.liferay.osb.hook.upgrade.v3_9_5;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TicketEntryConstants;

/**
 * @author Kyle Bischof
 */
public class Upgrade_20180319111155800_ListType extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20180319111155800L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(
			"update ListType set name = 'elasticsearch-2.x' where listTypeId " +
				"= " + TicketEntryConstants.ENV_SEARCH_ELASTICSEARCH_2_X);
		runSQL(
			"update ListType set name = 'kibana-4.x' where listTypeId = " +
				TicketEntryConstants.ENV_SEARCH_KIBANA_4_X);
		runSQL(
			"update ListType set name = 'marvel-2.x' where listTypeId = " +
				TicketEntryConstants.ENV_SEARCH_MARVEL_2_X);

		insertListType(
			40006, "elasticsearch-6.x",
			"com.liferay.osb.model.TicketEntry.envSearch");
		insertListType(
			40007, "kibana-6.x", "com.liferay.osb.model.TicketEntry.envSearch");
		insertListType(
			40008, "x-pack-6.x", "com.liferay.osb.model.TicketEntry.envSearch");
	}

}