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

package com.liferay.osb.hook.upgrade.v5_1_0;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.service.AccountEntryLanguageLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.osb.service.RemoteCorpProjectLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
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

			long classNameId = ClassNameLocalServiceUtil.getClassNameId(
				AccountEntry.class);

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

				boolean externalIdMappers =
					ExternalIdMapperLocalServiceUtil.hasExternalIdMappers(
						classNameId, accountEntryId,
						ExternalIdMapperConstants.TYPE_ZENDESK);

				if (externalIdMappers) {
					Message message = new Message();

					message.put("accountEntryId", accountEntryId);

					MessageBusUtil.sendMessage(
						"liferay/zendesk_account_entry_sync", message);
				}
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

				AccountEntryLanguageLocalServiceUtil.setAccountEntryLanguageIds(
					analyticsCloudAccountEntryId,
					accountEntry.getLanguageIds());

				SupportRegionLocalServiceUtil.setAccountEntrySupportRegions(
					analyticsCloudAccountEntryId,
					accountEntry.getSupportRegionIds());
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

		PreparedStatement ps = null;

		try {
			String sql =
				"update OSB_AccountEntry set name = ?  where (accountEntryId " +
					"= ?)";

			ps = connection.prepareStatement(sql);

			ps.setString(1, name);
			ps.setLong(2, accountEntryId);

			ps.executeUpdate();

			if (corpProjectId > 0) {
				RemoteCorpProjectLocalServiceUtil.updateCorpProject(
					corpProjectId, name);
			}
		}
		finally {
			DataAccess.cleanUp(ps);
		}
	}

}