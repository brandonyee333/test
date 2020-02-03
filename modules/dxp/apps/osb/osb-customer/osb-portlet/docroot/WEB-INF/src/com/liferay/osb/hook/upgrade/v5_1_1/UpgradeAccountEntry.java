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

package com.liferay.osb.hook.upgrade.v5_1_1;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.RemoteCorpProjectLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
				"select accountEntryId, corpProjectId, dossieraAccountKey, " +
					"name from OSB_AccountEntry where (type_ = ?)";

			ps = connection.prepareStatement(sql);

			ps.setInt(1, AccountEntryConstants.TYPE_ANALYTICS_CLOUD_BASIC);

			rs = ps.executeQuery();

			while (rs.next()) {
				long accountEntryId = rs.getLong("accountEntryId");
				long corpProjectId = rs.getLong("corpProjectId");
				String dossieraAccountKey = rs.getString("dossieraAccountKey");
				String name = rs.getString("name");

				if (Validator.isNotNull(dossieraAccountKey)) {
					updateAccountEntry(accountEntryId, dossieraAccountKey);
				}

				if (name.contains("Basic AC Tier")) {
					name = StringUtil.replace(
						name, "Basic AC Tier", "- Analytics Cloud");

					updateAccountEntryName(accountEntryId, corpProjectId, name);
				}

				Thread.sleep(300);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected boolean isDuplicateName(String name) throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql = "select count(*) from OSB_AccountEntry where name = ?";

			ps = connection.prepareStatement(sql);

			ps.setString(1, name);

			rs = ps.executeQuery();

			while (rs.next()) {
				int count = rs.getInt(1);

				if (count > 0) {
					return true;
				}
			}

			return false;
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void updateAccountEntry(
			long analyticsCloudAccountEntryId, String dossieraAccountKey)
		throws Exception {

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String sql =
				"select accountEntryId from OSB_AccountEntry where " +
					"(dossieraAccountKey = ?) and (type_ <> ?)";

			ps = connection.prepareStatement(sql);

			ps.setString(1, dossieraAccountKey);
			ps.setInt(2, AccountEntryConstants.TYPE_ANALYTICS_CLOUD_BASIC);

			rs = ps.executeQuery();

			if (rs.next()) {
				long accountEntryId = rs.getLong("accountEntryId");

				AccountEntry accountEntry =
					AccountEntryLocalServiceUtil.getAccountEntry(
						accountEntryId);

				AccountEntry analyticsCloudAccountEntry =
					AccountEntryLocalServiceUtil.getAccountEntry(
						analyticsCloudAccountEntryId);

				Address address = accountEntry.getAddress();

				AccountEntryLocalServiceUtil.updateAccountEntry(
					OSBConstants.USER_DEFAULT_USER_ID,
					analyticsCloudAccountEntry.getAccountEntryId(),
					analyticsCloudAccountEntry.getCorpProjectUuid(),
					analyticsCloudAccountEntry.getCorpProjectId(),
					analyticsCloudAccountEntry.getDossieraAccountKey(),
					analyticsCloudAccountEntry.getCorpEntryName(),
					analyticsCloudAccountEntry.getName(),
					analyticsCloudAccountEntry.getCode(),
					analyticsCloudAccountEntry.getType(),
					accountEntry.getIndustry(),
					analyticsCloudAccountEntry.getPartnerEntryId(),
					analyticsCloudAccountEntry.getPartnerManagedSupport(),
					analyticsCloudAccountEntry.getTier(),
					analyticsCloudAccountEntry.getMaxCustomers(),
					analyticsCloudAccountEntry.getInstructions(),
					analyticsCloudAccountEntry.getNotes(),
					accountEntry.getLanguageIds(),
					accountEntry.getSupportRegionIds(), address.getAddressId(),
					address.getStreet1(), address.getStreet2(),
					address.getStreet3(), address.getCity(), address.getZip(),
					address.getRegionId(), address.getCountryId(),
					analyticsCloudAccountEntry.getEWSADossieraProjectKey());
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void updateAccountEntryName(
			long accountEntryId, long corpProjectId, String name)
		throws Exception {

		int count = 1;

		while (isDuplicateName(name)) {
			name = name + StringPool.SPACE + count;

			count++;
		}

		AccountEntry accountEntry =
			AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

		accountEntry.setName(name);

		AccountEntryLocalServiceUtil.updateAccountEntry(accountEntry);

		if (corpProjectId > 0) {
			RemoteCorpProjectLocalServiceUtil.updateCorpProject(
				corpProjectId, name);
		}
	}

}