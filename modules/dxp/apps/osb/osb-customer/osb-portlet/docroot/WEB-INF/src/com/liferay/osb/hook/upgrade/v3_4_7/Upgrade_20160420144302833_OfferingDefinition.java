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

package com.liferay.osb.hook.upgrade.v3_4_7;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

*/

/**
 * @author Amos Fong
 */
public class Upgrade_20160420144302833_OfferingDefinition
	extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20160420144302833L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL("drop index IX_10B93EA3 on OSB_OfferingDefinition");
		runSQL("drop index IX_32A99633 on OSB_OfferingDefinition");
		runSQL("drop index IX_D7EDC7AC on OSB_OfferingDefinition");

		runSQL("alter table OSB_OfferingEntry add column licenseLifetime LONG");
		runSQL("alter table OSB_OfferingEntry add column supportLifetime LONG");

		StringBundler sb = new StringBundler(6);

		sb.append("update OSB_OfferingEntry inner join ");
		sb.append("OSB_OfferingDefinition on ");
		sb.append("OSB_OfferingDefinition.offeringDefinitionId = ");
		sb.append("OSB_OfferingEntry.offeringDefinitionId set ");
		sb.append("OSB_OfferingEntry.licenseLifetime = ");
		sb.append("OSB_OfferingDefinition.licenseLifetime");

		runSQL(sb.toString());

		sb.setIndex(0);

		sb.append("update OSB_OfferingEntry inner join ");
		sb.append("OSB_OfferingDefinition on ");
		sb.append("OSB_OfferingDefinition.offeringDefinitionId = ");
		sb.append("OSB_OfferingEntry.offeringDefinitionId set ");
		sb.append("OSB_OfferingEntry.supportLifetime = ");
		sb.append("OSB_OfferingDefinition.supportLifetime");

		runSQL(sb.toString());

		runSQL(
			"alter table OSB_OfferingDefinition drop column currencyEntryId");
		runSQL(
			"alter table OSB_OfferingDefinition drop column licenseLifetime");
		runSQL(
			"alter table OSB_OfferingDefinition drop column supportLifetime");
		runSQL("alter table OSB_OfferingDefinition drop column retailPrice");

		runSQL("alter table OSB_OrderEntry drop column currencyEntryId");
		runSQL("alter table OSB_OrderEntry drop column actualPrice");
	}

	 */

}