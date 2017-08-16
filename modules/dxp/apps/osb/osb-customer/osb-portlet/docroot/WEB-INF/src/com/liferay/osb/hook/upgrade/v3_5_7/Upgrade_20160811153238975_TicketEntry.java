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

package com.liferay.osb.hook.upgrade.v3_5_7;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Amos Fong
 */
public class Upgrade_20160811153238975_TicketEntry extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160811153238975L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasColumn("OSB_TicketEntry", "languageId")) {
			return;
		}

		runSQL("alter table OSB_TicketEntry add column languageId VARCHAR(75)");

		StringBundler sb = new StringBundler(8);

		sb.append("update OSB_TicketEntry inner join OSB_OfferingDefinition ");
		sb.append("on OSB_OfferingDefinition.offeringDefinitionId = ");
		sb.append("OSB_TicketEntry.offeringDefinitionId inner join ");
		sb.append("OSB_SupportResponse on ");
		sb.append("OSB_SupportResponse.supportResponseId = ");
		sb.append("OSB_OfferingDefinition.supportResponseId set ");
		sb.append("OSB_TicketEntry.languageId = ");
		sb.append("OSB_SupportResponse.languageId");

		runSQL(sb.toString());
	}

}