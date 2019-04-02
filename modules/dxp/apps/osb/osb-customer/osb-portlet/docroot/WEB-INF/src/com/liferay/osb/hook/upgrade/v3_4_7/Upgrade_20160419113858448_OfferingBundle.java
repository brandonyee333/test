/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.hook.upgrade.v3_4_7;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.impl.OfferingBundleModelImpl;

/**
 * @author Amos Fong
 */
public class Upgrade_20160419113858448_OfferingBundle
	extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		runSQL(OfferingBundleModelImpl.TABLE_SQL_CREATE);

		runSQL("create unique index IX_DC1ECDCE on OSB_OfferingBundle (name)");

		runSQL(
			OfferingBundleModelImpl.
				MAPPING_TABLE_OSB_OFFERINGBUNDLES_OFFERINGDEFINITIONS_SQL_CREATE);

		runSQL(
			"create index IX_480DB8E2 on " +
				"OSB_OfferingBundles_OfferingDefinitions (offeringBundleId)");
		runSQL(
			"create index IX_5CB2DBB3 on " +
				"OSB_OfferingBundles_OfferingDefinitions " +
					"(offeringDefinitionId)");
	}

}