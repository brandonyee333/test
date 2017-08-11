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

package com.liferay.osb.hook.upgrade.v3_2_3;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.marketplace.util.ECDocumentItemExtraSettings;
import com.liferay.osb.marketplace.util.ECommerceConstants;
import com.liferay.osb.model.AssetAuditConstants;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.model.User;

*/

/**
 * @author Joan Kim
 */
public class Upgrade_20150402081827591_ECDocumentEntry extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

	/*@Override
	public long getTimestamp() {
		return 20150402081827591L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(28);

		sb.append("insert into ECommerce_ECDocumentEntry (ecDocumentEntryId, ");
		sb.append("groupId, companyId, userId, userName, createDate, ");
		sb.append("modifiedDate, storeName, vendorClassNameId, ");
		sb.append("vendorClassPK, type_, billingUserId, billingUserName, ");
		sb.append("billingEmailAddress, shippingAddressStreet1, ");
		sb.append("shippingAddressStreet2, shippingAddressCity, ");
		sb.append("shippingAddressZip, shippingAddressRegionId, ");
		sb.append("shippingAddressCountryId, paidDate, fulfillmentDate, ");
		sb.append("paymentProcessor, taxProcessor, taxProcessed, ");
		sb.append("ecAccessKeyId, subtotal, taxAmount, total, extraSettings, ");
		sb.append("status, statusDate) values (");

		long ecDocumentEntryId = CounterLocalServiceUtil.increment();

		sb.append(ecDocumentEntryId);

		sb.append(", ");
		sb.append(OSBConstants.GROUP_GUEST_ID);
		sb.append(", ");
		sb.append(OSBConstants.COMPANY_ID);
		sb.append(", 43241905, 'Max Chichayev', '2015-03-02 20:54:35', ");
		sb.append("'2015-03-02 20:54:35', 'Liferay Marketplace', ");
		sb.append("12291273, 37640005, 1, 43241905, 'Max Chichayev', ");
		sb.append("'mchichayev@mail.ru', 'Trasko', '8 ul sokolinoq gory', ");
		sb.append("'Moscow', '105275', 13043, 13, '2015-03-02 20:54:40', ");
		sb.append("'2015-03-12 20:54:40', '");
		sb.append(getPaymentProcessor());
		sb.append("', '");
		sb.append(getTaxProcessor());
		sb.append("', 1, 0, 10.99, 0, 10.99, '");
		sb.append(getECDocumentExtraSettingsString());
		sb.append("', 1, '2015-03-02 20:54:45')");

		runSQL(sb.toString());

		sb = new StringBundler(19);

		sb.append("insert into ECommerce_ECDocumentItem (ecDocumentItemId, ");
		sb.append("groupId, companyId, createDate, ecDocumentEntryId, ");
		sb.append("ecProductEntryId, ecProductTypeId, lineNumber, ");
		sb.append("classNameId, classPK, name, currencyCode, unitPrice, ");
		sb.append("quantity, taxExempt, taxAmount, extraSettings) values (");
		sb.append(CounterLocalServiceUtil.increment());
		sb.append(", ");
		sb.append(OSBConstants.GROUP_GUEST_ID);
		sb.append(", ");
		sb.append(OSBConstants.COMPANY_ID);
		sb.append(", '2015-03-02 20:54:35', ");
		sb.append(ecDocumentEntryId);
		sb.append(", 0, ");
		sb.append(ECommerceConstants.EC_PRODUCT_TYPE_ID_MARKETPLACE_APP);
		sb.append(", 1, 11327279, 48485080, '");
		sb.append(getECDocumentItemName());
		sb.append("', 'USD', 10.99, 1, 1, 0, '");
		sb.append(getECDocumentItemExtraSettingsString());
		sb.append("')");

		runSQL(sb.toString());
	}

	protected String getECDocumentExtraSettingsString() {
		UnicodeProperties extraSettingsProperties = new UnicodeProperties(true);

		extraSettingsProperties.setProperty(
			"marketplaceAppEntryId", String.valueOf(39477884));
		extraSettingsProperties.setProperty(
			"marketplaceCountryId", String.valueOf(13));
		extraSettingsProperties.setProperty(
			"marketplaceDeveloperEntryId", String.valueOf(37640014));
		extraSettingsProperties.setProperty(
			"marketplaceDomain",
			String.valueOf(AssetAuditConstants.DOMAIN_LIFERAY));
		extraSettingsProperties.setProperty(
			"marketplaceEulaContractEntryId", String.valueOf(35705481));
		extraSettingsProperties.setProperty(
			"marketplaceOwnerClassName", User.class.getName());
		extraSettingsProperties.setProperty(
			"marketplaceOwnerClassPK", String.valueOf(43241905));
		extraSettingsProperties.setProperty(
			"marketplaceProductType", "app-entry");
		extraSettingsProperties.setProperty("marketplacePurchaseType", "new");
		extraSettingsProperties.setProperty(
			"marketplaceResale", String.valueOf(false));
		extraSettingsProperties.setProperty(
			"marketplaceTosContractEntryId", String.valueOf(35716109));
		extraSettingsProperties.setProperty(
			"payPalPayKey", "AP-9AX19301LG520215B");
		extraSettingsProperties.setProperty(
			"payPalVendorEmailAddress", "reigo@amivarius.com");
		extraSettingsProperties.setProperty(
			"vendorAmount", String.valueOf(8.79));

		return extraSettingsProperties.toString();
	}

	protected String getECDocumentItemExtraSettingsString() {
		ECDocumentItemExtraSettings ecDocumentItemExtraSettings =
			new ECDocumentItemExtraSettings();

		ecDocumentItemExtraSettings.setItemType("license");
		ecDocumentItemExtraSettings.setPurchaseType("new");
		ecDocumentItemExtraSettings.setUsageType(
			AssetLicenseConstants.USAGE_TYPE_STANDARD);

		return ecDocumentItemExtraSettings.toString();
	}

	protected String getECDocumentItemName() {
		return
			"14 Additional Basic Layouts Pack - 3 standard instance, perpetual";
	}

	protected String getPaymentProcessor() {
		return "com.liferay.ecommerce.payment.processor.PayPalPaymentProcessor";
	}

	protected String getTaxProcessor() {
		return "com.liferay.ecommerce.tax.processor.AvalaraTaxProcessor";
	}

	 */

}