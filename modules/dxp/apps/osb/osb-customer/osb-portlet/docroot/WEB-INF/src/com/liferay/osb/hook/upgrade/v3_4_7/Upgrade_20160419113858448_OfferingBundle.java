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
import com.liferay.osb.model.impl.OfferingBundleModelImpl;

*/

/**
 * @author Amos Fong
 */
public class Upgrade_20160419113858448_OfferingBundle extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20160419113858448L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(OfferingBundleModelImpl.TABLE_SQL_CREATE);

		runSQL("create unique index IX_DC1ECDCE on OSB_OfferingBundle (name)");

		runSQL(
			OfferingBundleModelImpl.MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_SQL_CREATE);

		runSQL(
			"create index IX_480DB8E2 on " +
				"OSB_OfferingBundles_OfferingDefinitions (offeringBundleId)");
		runSQL(
			"create index IX_5CB2DBB3 on " +
				"OSB_OfferingBundles_OfferingDefinitions " +
					"(offeringDefinitionId)");
	}

}

*/

}