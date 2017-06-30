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

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.OfferingEntryConstants;
import com.liferay.osb.model.impl.AccountEntryLanguageImpl;
import com.liferay.osb.service.AccountEntryLanguageLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.SupportRegionLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class Upgrade_20160811141901211_AccountEntry extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20160811141901211L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable("OSB_AccountEntryLanguage")) {
			return;
		}

		runSQL(AccountEntryLanguageImpl.TABLE_SQL_CREATE);

		runSQL(
			"create index IX_8B166398 on OSB_AccountEntryLanguage " +
				"(accountEntryId)");

		List<AccountEntry> accountEntries =
			AccountEntryLocalServiceUtil.getAccountEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (AccountEntry accountEntry : accountEntries) {
			updateAccountEntry(accountEntry.getAccountEntryId());
		}
	}

	protected long getSupportRegionId(String name, String languageId) {
		if (languageId.equals(AccountEntryConstants.LANGUAGE_ID_ENGLISH)) {
			String[] supportResponseNameParts = StringUtil.split(
				name, StringPool.SPACE);

			String supportResponseRegion = supportResponseNameParts[0];

			if (supportResponseRegion.equals("Americas")) {
				return OSBConstants.SUPPORT_REGION_US_ID;
			}
			else if (supportResponseRegion.equals("Asia")) {
				return OSBConstants.SUPPORT_REGION_CHINA_ID;
			}
			else if (supportResponseRegion.equals("Australia")) {
				return 42442481;
			}
			else if (supportResponseRegion.equals("Europe")) {
				return OSBConstants.SUPPORT_REGION_HUNGARY_ID;
			}
			else if (supportResponseRegion.equals("India")) {
				return 42356498;
			}
		}
		else if (languageId.equals(AccountEntryConstants.LANGUAGE_ID_CHINESE)) {
			return OSBConstants.SUPPORT_REGION_CHINA_ID;
		}
		else if (languageId.equals(
					AccountEntryConstants.LANGUAGE_ID_JAPANESE)) {

			return 45637701;
		}
		else if (languageId.equals(
					AccountEntryConstants.LANGUAGE_ID_PORTUGUESE)) {

			return 42356516;
		}
		else if (languageId.equals(AccountEntryConstants.LANGUAGE_ID_SPANISH)) {
			return 42356507;
		}
		else if (languageId.equals("All")) {
			return OSBConstants.SUPPORT_REGION_HUNGARY_ID;
		}

		return 0;
	}

	protected void updateAccountEntry(long accountEntryId) throws Exception {
		Set<String> languageIds = new HashSet<String>();
		Set<Long> supportRegionIds = new HashSet<Long>();

		Set<String> inactiveLanguageIds = new HashSet<String>();
		Set<Long> inactiveSupportRegionIds = new HashSet<Long>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		StringBundler sb = new StringBundler(9);

		sb.append("select OSB_SupportResponse.name, ");
		sb.append("OSB_SupportResponse.languageId, OSB_OfferingEntry.status ");
		sb.append("from OSB_OfferingEntry inner join OSB_OfferingDefinition ");
		sb.append("on OSB_OfferingDefinition.offeringDefinitionId = ");
		sb.append("OSB_OfferingEntry.offeringDefinitionId inner join ");
		sb.append("OSB_SupportResponse on ");
		sb.append("OSB_SupportResponse.supportResponseId = ");
		sb.append("OSB_OfferingDefinition.supportResponseId where ");
		sb.append("OSB_OfferingEntry.accountEntryId = ?");

		try {
			con = DataAccess.getConnection();

			ps = con.prepareStatement(sb.toString());

			ps.setLong(1, accountEntryId);

			rs = ps.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String languageId = rs.getString("languageId");
				int status = rs.getInt("status");

				long supportRegionId = getSupportRegionId(name, languageId);

				if (status == OfferingEntryConstants.STATUS_ACTIVE) {
					languageIds.add(languageId);

					if (supportRegionId > 0) {
						supportRegionIds.add(supportRegionId);
					}
				}
				else {
					inactiveLanguageIds.add(languageId);

					if (supportRegionId > 0) {
						inactiveSupportRegionIds.add(supportRegionId);
					}
				}
			}
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}

		if (languageIds.isEmpty()) {
			languageIds.addAll(inactiveLanguageIds);
		}

		if (languageIds.isEmpty()) {
			languageIds.add(AccountEntryConstants.LANGUAGE_ID_ENGLISH);
		}

		AccountEntryLanguageLocalServiceUtil.setAccountEntryLanguageIds(
			accountEntryId, languageIds.toArray(new String[0]));

		if (SupportRegionLocalServiceUtil.getAccountEntrySupportRegionsCount(
				accountEntryId) <= 0) {

			if (supportRegionIds.isEmpty()) {
				supportRegionIds.addAll(inactiveSupportRegionIds);
			}

			if (supportRegionIds.isEmpty()) {
				supportRegionIds.add(_SUPPORT_REGION_NOT_AVAILABLE_ID);
			}

			SupportRegionLocalServiceUtil.setAccountEntrySupportRegions(
				accountEntryId,
				ArrayUtil.toArray(supportRegionIds.toArray(new Long[0])));
		}
	}

	private static final long _SUPPORT_REGION_NOT_AVAILABLE_ID = 78237012;

}