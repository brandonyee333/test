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

package com.liferay.osb.hook.upgrade.v3_1_2;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.portal.kernel.util.BigDecimalUtil;
import com.liferay.ecommerce.access.messaging.ECAccessUtil;
import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.ecommerce.service.ECDocumentEntryLocalServiceUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.util.CurrencyUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

*/

/**
 * @author Douglas Wong
 */
public class Upgrade_20141022153815429_ECDocumentEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20141022153815429L;
	}

	protected static void updateExtraSettingsPropertiesVendorAmount(
			UnicodeProperties extraSettingsProperties,
			ECDocumentEntry ecDocumentEntry)
		throws Exception {

		double vendorAmount = BigDecimalUtil.multiply(
			ecDocumentEntry.getSubtotal(),
			PortletPropsValues.MARKETPLACE_DEVELOPER_PAYMENT_PERCENTAGE);

		vendorAmount = BigDecimalUtil.multiply(vendorAmount, 0.01);

		String currencyCode = ecDocumentEntry.getCurrencyCode();

		long appEntryId = GetterUtil.getLong(
			extraSettingsProperties.getProperty("appEntryId"));

		AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

		Country country = ecDocumentEntry.getShippingAddressCountry();

		String countryA2 = country.getA2();

		if ((appEntry.getLicenseLifetime() ==
				AssetLicenseConstants.LIFETIME_SUBSCRIPTION) &&
			countryA2.equals("US")) {

			DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

			double fatcaWithholdingAmount = BigDecimalUtil.multiply(
				ecDocumentEntry.getSubtotal(),
				developerEntry.getFatcaWithholdingPercentageValue());

			fatcaWithholdingAmount = BigDecimalUtil.multiply(
				fatcaWithholdingAmount, 0.01);

			fatcaWithholdingAmount = CurrencyUtil.scale(
				currencyCode, fatcaWithholdingAmount);

			extraSettingsProperties.setProperty(
				"fatcaWithholdingAmount",
				String.valueOf(fatcaWithholdingAmount));

			double fatcaWithholdingAmountUSD = ECAccessUtil.convertCurrency(
				ecDocumentEntry.getGroupId(), fatcaWithholdingAmount,
				currencyCode, "USD");

			extraSettingsProperties.setProperty(
				"fatcaWithholdingAmountUSD",
				String.valueOf(fatcaWithholdingAmountUSD));

			vendorAmount = BigDecimalUtil.subtract(
				vendorAmount, fatcaWithholdingAmount);
		}
		else {
			extraSettingsProperties.remove("fatcaWithholdingAmount");
			extraSettingsProperties.remove("fatcaWithholdingAmountUSD");
		}

		vendorAmount = CurrencyUtil.scale(currencyCode, vendorAmount);

		extraSettingsProperties.setProperty(
			"vendorAmount", String.valueOf(vendorAmount));

		if (!currencyCode.equals("USD")) {
			double vendorAmountUSD = ECAccessUtil.convertCurrency(
				ecDocumentEntry.getGroupId(), vendorAmount, currencyCode,
				"USD");

			extraSettingsProperties.setProperty(
				"vendorAmountUSD", String.valueOf(vendorAmountUSD));
		}
	}

	@Override
	protected void doUpgrade() throws Exception {
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(
				"select ecDocumentEntryId from ECommerce_ECDocumentEntry " +
					"where status = 1");

			rs = ps.executeQuery();

			while (rs.next()) {
				long ecDocumentEntryId = rs.getLong("ecDocumentEntryId");

				updateECDocumentEntryVendorAmount(ecDocumentEntryId);
			}
		}
		finally {
			DataAccess.cleanUp(ps, rs);
		}
	}

	protected void updateECDocumentEntryVendorAmount(long ecDocumentEntryId)
		throws Exception {

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
				ecDocumentEntryId);

		UnicodeProperties extraSettingsProperties =
			ecDocumentEntry.getExtraSettingsProperties();

		updateExtraSettingsPropertiesVendorAmount(
			extraSettingsProperties, ecDocumentEntry);

		ECDocumentEntryLocalServiceUtil.updateECDocumentEntry(
			ecDocumentEntryId, extraSettingsProperties.toString());
	}

	 */

}