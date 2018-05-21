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

package com.liferay.osb.hook.upgrade.v3_5_9;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;

/**
 * @author Amos Fong
 */
public class Upgrade_20160920173413713_LicenseKey extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160920173413713L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (!hasColumn("OSB_LicenseKey", "productEntryId")) {
			runSQL("alter table OSB_LicenseKey add column productEntryId LONG");
		}

		if (!hasColumn("OSB_LicenseKey", "supportResponseId")) {
			runSQL(
				"alter table OSB_LicenseKey add column supportResponseId LONG");
		}

		if (hasColumn("OSB_LicenseKey", "offeringDefinitionId")) {
			StringBundler sb = new StringBundler(8);

			sb.append("update OSB_LicenseKey inner join ");
			sb.append("OSB_OfferingDefinition on ");
			sb.append("OSB_OfferingDefinition.offeringDefinitionId = ");
			sb.append("OSB_LicenseKey.offeringDefinitionId set ");
			sb.append("OSB_LicenseKey.productEntryId = ");
			sb.append("OSB_OfferingDefinition.productEntryId, ");
			sb.append("OSB_LicenseKey.supportResponseId = ");
			sb.append("OSB_OfferingDefinition.supportResponseId");

			runSQL(sb.toString());

			runSQL(
				"alter table OSB_LicenseKey drop column offeringDefinitionId");
		}
	}

}