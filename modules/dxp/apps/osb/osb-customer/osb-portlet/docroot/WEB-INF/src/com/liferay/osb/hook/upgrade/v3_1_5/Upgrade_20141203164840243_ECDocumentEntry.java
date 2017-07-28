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

package com.liferay.osb.hook.upgrade.v3_1_5;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.util.Validator;
import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.ecommerce.service.ECDocumentEntryLocalServiceUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.List;

*/

/**
 * @author Douglas Wong
 */
public class Upgrade_20141203164840243_ECDocumentEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20141203164840243L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<ECDocumentEntry> ecDocumentEntries =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntries(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (ECDocumentEntry ecDocumentEntry : ecDocumentEntries) {
			UnicodeProperties extraSettingsProperties =
				ecDocumentEntry.getExtraSettingsProperties();

			updateExtraSettingsProperty(
				extraSettingsProperties, "addressId", "marketplaceAddressId");
			updateExtraSettingsProperty(
				extraSettingsProperties, "appEntryId", "marketplaceAppEntryId");
			updateExtraSettingsProperty(
				extraSettingsProperties, "countryId", "marketplaceCountryId");
			updateExtraSettingsProperty(
				extraSettingsProperties, "developerEntryId",
				"marketplaceDeveloperEntryId");
			updateExtraSettingsProperty(
				extraSettingsProperties, "domain", "marketplaceDomain");
			updateExtraSettingsProperty(
				extraSettingsProperties, "endDate", "marketplaceEndDate");
			updateExtraSettingsProperty(
				extraSettingsProperties, "eulaContractEntryId",
				"marketplaceEulaContractEntryId");
			updateExtraSettingsProperty(
				extraSettingsProperties, "fatcaWithholdingAmount",
				"marketplaceFatcaWithholdingAmount");
			updateExtraSettingsProperty(
				extraSettingsProperties, "fatcaWithholdingAmountUSD",
				"marketplaceFatcaWithholdingAmountUSD");
			updateExtraSettingsProperty(
				extraSettingsProperties, "itemType", "marketplaceItemType");
			updateExtraSettingsProperty(
				extraSettingsProperties, "legalEntityName",
				"marketplaceLegalEntityName");
			updateExtraSettingsProperty(
				extraSettingsProperties, "ownerClassName",
				"marketplaceOwnerClassName");
			updateExtraSettingsProperty(
				extraSettingsProperties, "ownerClassPK",
				"marketplaceOwnerClassPK");
			updateExtraSettingsProperty(
				extraSettingsProperties, "purchaseType",
				"marketplacePurchaseType");
			updateExtraSettingsProperty(
				extraSettingsProperties, "resale", "marketplaceResale");
			updateExtraSettingsProperty(
				extraSettingsProperties, "startDate", "marketplaceStartDate");
			updateExtraSettingsProperty(
				extraSettingsProperties, "tosContractEntryId",
				"marketplaceTosContractEntryId");
			updateExtraSettingsProperty(
				extraSettingsProperties, "usageType", "marketplaceUsageType");
			updateExtraSettingsProperty(
				extraSettingsProperties, "vendorAmountUSD",
				"marketplaceVendorAmountUSD");

			ECDocumentEntryLocalServiceUtil.updateECDocumentEntry(
				ecDocumentEntry.getEcDocumentEntryId(),
				extraSettingsProperties.toString());
		}
	}

	protected void updateExtraSettingsProperty(
			UnicodeProperties extraSettingsProperties, String oldKey,
			String newKey)
		throws Exception {

		String value = extraSettingsProperties.remove(oldKey);

		if (Validator.isNull(value) ||
			extraSettingsProperties.containsKey(newKey)) {

			return;
		}

		extraSettingsProperties.put(newKey, value);
	}

}

*/

}