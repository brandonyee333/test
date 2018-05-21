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

package com.liferay.osb.hook.upgrade.v3_1_6;

import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.impl.PartnerEntryModelImpl;

/**
 * @author Sharon Li
 */
public class Upgrade_20141218193532347_PartnerEntries_SupportRegions
	extends BaseUpgradeProcess {

	@Override
	public long getTimestamp() {
		return 20141218193532347L;
	}

	protected void createTablesAndIndexes() throws Exception {
		runSQL(
			PartnerEntryModelImpl.
				MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_SQL_CREATE);

		runSQL(
			"create index IX_A39907C5 on OSB_PartnerEntries_SupportRegions " +
				"(partnerEntryId)");
		runSQL(
			"create index IX_A1C7B702 on OSB_PartnerEntries_SupportRegions " +
				"(supportRegionId)");
	}

	@Override
	protected void doUpgrade() throws Exception {
		if (hasTable(
				PartnerEntryModelImpl.
					MAPPING_TABLE_OSB_PARTNERENTRIES_SUPPORTREGIONS_NAME)) {

			return;
		}

		createTablesAndIndexes();

		populatePartnerData();
	}

	protected void populatePartnerData() throws Exception {
		/*
		long[][] supportResponseIds = {
			{OSBConstants.SUPPORT_REGION_BRAZIL_ID, _ASSET_CATEGORY_BRAZIL_ID},
			{OSBConstants.SUPPORT_REGION_CHINA_ID, _ASSET_CATEGORY_CHINA_ID},
			{
				OSBConstants.SUPPORT_REGION_HUNGARY_ID,
				_ASSET_CATEGORY_HUNGARY_ID
			},
			{OSBConstants.SUPPORT_REGION_INDIA_ID, _ASSET_CATEGORY_INDIA_ID},
			{OSBConstants.SUPPORT_REGION_SPAIN_ID, _ASSET_CATEGORY_SPAIN_ID},
			{OSBConstants.SUPPORT_REGION_US_ID, _ASSET_CATEGORY_US_ID}
		};

		for (long[] supportResponseId : supportResponseIds) {
			StringBundler sb = new StringBundler(14);

			sb.append("insert ignore into OSB_PartnerEntries_SupportRegions ");
			sb.append("(partnerEntryId, supportRegionId) select distinct ");
			sb.append("OSB_PartnerEntry.partnerEntryId, ");
			sb.append(supportResponseId[0]);
			sb.append(" from OSB_PartnerEntry inner join AssetEntry on ");
			sb.append("(AssetEntry.classNameId = ");
			sb.append(_PARTNER_ENTRY_CLASS_NAME_ID);
			sb.append(" and AssetEntry.classPK = ");
			sb.append("OSB_PartnerEntry.partnerEntryId) inner join ");
			sb.append("AssetEntries_AssetCategories on ");
			sb.append("(AssetEntries_AssetCategories.entryId = ");
			sb.append("AssetEntry.entryId) where ");
			sb.append("AssetEntries_AssetCategories.categoryId = ");
			sb.append(supportResponseId[1]);

			runSQL(sb.toString());
		}*/
	}

	private static final long _ASSET_CATEGORY_BRAZIL_ID = 21343273;

	private static final long _ASSET_CATEGORY_CHINA_ID = 21343274;

	private static final long _ASSET_CATEGORY_HUNGARY_ID = 21343275;

	private static final long _ASSET_CATEGORY_INDIA_ID = 21343276;

	private static final long _ASSET_CATEGORY_SPAIN_ID = 21343277;

	private static final long _ASSET_CATEGORY_US_ID = 21343278;

	private static final long _PARTNER_ENTRY_CLASS_NAME_ID = 4840271;

}