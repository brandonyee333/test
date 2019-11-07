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

package com.liferay.osb.hook.upgrade.v5_1_5;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.CorpProject;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.osb.service.OfferingEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Jenny Chen
 */
public class UpgradeAccountEntry extends BaseUpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select accountEntryId, corpProjectUuid from " +
					"OSB_AccountEntry where (type_ = ?)";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, AccountEntryConstants.TYPE_ANALYTICS_CLOUD_BASIC);

			rs = ps.executeQuery();

			while (rs.next()) {
				long accountEntryId = rs.getLong("accountEntryId");
				String corpProjectUuid = rs.getString("corpProjectUuid");

				CorpProject corpProject =
					CorpProjectLocalServiceUtil.fetchCorpProjectByUuid(
						corpProjectUuid);

				if (corpProject != null) {
					List<User> ownerUsers =
						corpProject.getAnalyticsCloudOwners();

					if (ownerUsers.isEmpty()) {
						AccountEntry accountEntry =
							AccountEntryLocalServiceUtil.fetchAccountEntry(
								accountEntryId);

						for (OfferingEntry offeringEntry :
								accountEntry.getOfferingEntries()) {

							offeringEntry.setStatus(
								OfferingEntryConstants.STATUS_CLOSED);

							OfferingEntryLocalServiceUtil.updateOfferingEntry(
								offeringEntry);
						}

						AccountEntryLocalServiceUtil.updateSupportStatus(
							accountEntryId);
					}
				}
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

}