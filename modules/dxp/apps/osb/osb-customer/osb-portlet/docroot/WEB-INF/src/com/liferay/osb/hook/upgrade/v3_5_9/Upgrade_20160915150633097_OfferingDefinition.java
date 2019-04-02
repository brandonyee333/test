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
public class Upgrade_20160915150633097_OfferingDefinition
	extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		if (hasIndex("OSB_OfferingDefinition", "IX_19CF8762")) {
			runSQL("drop index IX_19CF8762 on OSB_OfferingDefinition");
		}

		if (hasIndex("OSB_OfferingDefinition", "IX_2EE67FDD")) {
			runSQL("drop index IX_2EE67FDD on OSB_OfferingDefinition");
		}

		if (hasColumn("OSB_OfferingDefinition", "modifiedUserId")) {
			runSQL(
				"alter table OSB_OfferingDefinition drop column " +
					"modifiedUserId");
		}

		if (hasColumn("OSB_OfferingDefinition", "modifiedUserName")) {
			runSQL(
				"alter table OSB_OfferingDefinition drop column " +
					"modifiedUserName");
		}

		if (!hasColumn("OSB_OfferingDefinition", "licenses")) {
			runSQL(
				"alter table OSB_OfferingDefinition add column licenses " +
					"BOOLEAN");
		}

		if (hasColumn("OSB_OfferingDefinition", "unlimitedServers")) {
			runSQL(
				"alter table OSB_OfferingDefinition change column " +
					"unlimitedServers unlimitedLicenses BOOLEAN");
		}

		if (hasColumn("OSB_OfferingDefinition", "unlimitedSupportTickets")) {
			runSQL(
				"alter table OSB_OfferingDefinition drop column " +
					"supportTickets");
			runSQL(
				"alter table OSB_OfferingDefinition change column " +
					"unlimitedSupportTickets supportTickets BOOLEAN");
		}

		if (hasColumn("OSB_OfferingDefinition", "trainingCredits")) {
			runSQL(
				"alter table OSB_OfferingDefinition drop column " +
					"trainingCredits");
		}

		if (hasColumn("OSB_OfferingDefinition", "servers")) {
			StringBundler sb = new StringBundler(4);

			sb.append("update OSB_OfferingDefinition set ");
			sb.append("OSB_OfferingDefinition.licenses = true where ");
			sb.append("OSB_OfferingDefinition.servers > 0 or ");
			sb.append("OSB_OfferingDefinition.unlimitedLicenses = true");

			runSQL(sb.toString());

			runSQL("alter table OSB_OfferingDefinition drop column servers");
		}

		if (!hasIndex("OSB_OfferingDefinition", "IX_CFDC51BF")) {
			runSQL(
				"create index IX_CFDC51BF on OSB_OfferingDefinition " +
					"(productEntryId, supportResponseId, productDescription, " +
						"licenses, unlimitedLicenses, supportTickets)");
		}
	}

}