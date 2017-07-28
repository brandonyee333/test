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
import com.liferay.ecommerce.model.ECDocumentItem;
import com.liferay.ecommerce.service.ECDocumentItemLocalServiceUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.List;

*/

/**
 * @author Douglas Wong
 */
public class Upgrade_20141203164557594_ECDocumentItem extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*@Override
	public long getTimestamp() {
		return 20141203164557594L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<ECDocumentItem> ecDocumentItems =
			ECDocumentItemLocalServiceUtil.getECDocumentItems(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (ECDocumentItem ecDocumentItem : ecDocumentItems) {
			UnicodeProperties extraSettingsProperties =
				ecDocumentItem.getExtraSettingsProperties();

			updateExtraSettingsProperty(
				extraSettingsProperties, "endDate", "marketplaceEndDate");
			updateExtraSettingsProperty(
				extraSettingsProperties, "itemType", "marketplaceItemType");
			updateExtraSettingsProperty(
				extraSettingsProperties, "purchaseType",
				"marketplacePurchaseType");
			updateExtraSettingsProperty(
				extraSettingsProperties, "startDate", "marketplaceStartDate");
			updateExtraSettingsProperty(
				extraSettingsProperties, "usageType", "marketplaceUsageType");

			ecDocumentItem.setExtraSettings(extraSettingsProperties.toString());

			ECDocumentItemLocalServiceUtil.updateECDocumentItem(
				ecDocumentItem, false);
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