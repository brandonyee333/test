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

package com.liferay.osb.admin.servlet;

import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.ecommerce.model.ECProductEntry;
import com.liferay.ecommerce.model.ECProductType;
import com.liferay.ecommerce.model.ECProductTypeCode;
import com.liferay.ecommerce.service.ECProductEntryLocalServiceUtil;
import com.liferay.ecommerce.service.ECProductTypeCodeLocalServiceUtil;
import com.liferay.ecommerce.service.ECProductTypeLocalServiceUtil;
import com.liferay.osb.marketplace.util.ECommerceConstants;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Region;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.CountryServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RegionServiceUtil;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

/**
 * @author Ryan Park
 * @author Joan Kim
 */
public class AdminServletContextListenerECommerceHelper {

	public static void setup() throws Exception {
		setupAddress();
		setupProcessors();
		setupProducts();
	}

	public static void setupAddress() throws Exception {
		Country country = CountryServiceUtil.getCountryByA3("USA");

		Region region = RegionServiceUtil.getRegion(
			country.getCountryId(), "CA");

		AddressLocalServiceUtil.addAddress(
			OSBConstants.USER_DEFAULT_USER_ID, ECDocumentEntry.class.getName(),
			OSBConstants.GROUP_GUEST_ID, "1400 Montefino Ave.",
			StringPool.BLANK, StringPool.BLANK, "Diamond Bar", "91765",
			region.getRegionId(), country.getCountryId(), 0, false, true);
	}

	public static void setupProcessors() throws Exception {
		Group group = GroupLocalServiceUtil.getGroup(
			OSBConstants.GROUP_GUEST_ID);

		ExpandoBridge expandoBridge = group.getExpandoBridge();

		ExpandoTable expandoTable =
			ExpandoTableLocalServiceUtil.fetchDefaultTable(
				OSBConstants.COMPANY_ID, Group.class.getName());

		if (expandoTable == null) {
			expandoTable = ExpandoTableLocalServiceUtil.addDefaultTable(
				OSBConstants.COMPANY_ID, Group.class.getName());
		}

		// Payment

		if (!expandoBridge.hasAttribute("eCommercePaymentProcessor")) {
			try {
				ExpandoColumnLocalServiceUtil.addColumn(
					expandoTable.getTableId(), "eCommercePaymentProcessor",
					ExpandoColumnConstants.STRING);
			}
			catch (Exception e) {
			}

			String eCommercePaymentProcessor =
				"com.liferay.ecommerce.payment.processor." +
					"PayPalPaymentProcessor";

			expandoBridge.setAttribute(
				"eCommercePaymentProcessor", eCommercePaymentProcessor, false);
		}

		// Tax

		if (!expandoBridge.hasAttribute("eCommerceTaxProcessor")) {
			try {
				ExpandoColumnLocalServiceUtil.addColumn(
					expandoTable.getTableId(), "eCommerceTaxProcessor",
					ExpandoColumnConstants.STRING);
			}
			catch (Exception e) {
			}

			expandoBridge.setAttribute(
				"eCommerceTaxProcessor",
				"com.liferay.ecommerce.tax.processor.AvalaraTaxProcessor",
				false);
		}

		if (!expandoBridge.hasAttribute("eCommerceTaxProcessor#BR")) {
			try {
				ExpandoColumnLocalServiceUtil.addColumn(
					expandoTable.getTableId(), "eCommerceTaxProcessor#BR",
					ExpandoColumnConstants.STRING);
			}
			catch (Exception e) {
			}

			expandoBridge.setAttribute(
				"eCommerceTaxProcessor#BR",
				"com.liferay.ecommerce.tax.processor.DutyFreeTaxProcessor",
				false);
		}

		if (!expandoBridge.hasAttribute("eCommerceTaxProcessor#IN")) {
			try {
				ExpandoColumnLocalServiceUtil.addColumn(
					expandoTable.getTableId(), "eCommerceTaxProcessor#IN",
					ExpandoColumnConstants.STRING);
			}
			catch (Exception e) {
			}

			expandoBridge.setAttribute(
				"eCommerceTaxProcessor#IN",
				"com.liferay.ecommerce.tax.processor.DutyFreeTaxProcessor",
				false);
		}

		if (!expandoBridge.hasAttribute("eCommerceTaxProcessor#KR")) {
			try {
				ExpandoColumnLocalServiceUtil.addColumn(
					expandoTable.getTableId(), "eCommerceTaxProcessor#KR",
					ExpandoColumnConstants.STRING);
			}
			catch (Exception e) {
			}

			expandoBridge.setAttribute(
				"eCommerceTaxProcessor#KR",
				"com.liferay.ecommerce.tax.processor.DutyFreeTaxProcessor",
				false);
		}
	}

	public static void setupProducts() throws Exception {

		// App

		ECProductType ecProductType =
			ECProductTypeLocalServiceUtil.fetchECProductType(
				OSBConstants.GROUP_GUEST_ID,
				ECommerceConstants.EC_PRODUCT_TYPE_NAME_MARKETPLACE_APP);

		if (ecProductType == null) {
			ecProductType = ECProductTypeLocalServiceUtil.addECProductType(
				OSBConstants.GROUP_GUEST_ID,
				ECommerceConstants.EC_PRODUCT_TYPE_NAME_MARKETPLACE_APP);
		}

		ECommerceConstants.EC_PRODUCT_TYPE_ID_MARKETPLACE_APP =
			ecProductType.getEcProductTypeId();

		ECProductTypeCode ecProductTypeCode =
			ECProductTypeCodeLocalServiceUtil.fetchECProductTypeCode(
				ecProductType.getEcProductTypeId(),
				"com.liferay.ecommerce.tax.processor.AvalaraTaxProcessor");

		if (ecProductTypeCode == null) {
			ECProductTypeCodeLocalServiceUtil.addECProductTypeCode(
				OSBConstants.GROUP_GUEST_ID, ecProductType.getEcProductTypeId(),
				"com.liferay.ecommerce.tax.processor.AvalaraTaxProcessor",
				"EXAMPLE-TAX-CODE");
		}

		// App support

		ecProductType = ECProductTypeLocalServiceUtil.fetchECProductType(
			OSBConstants.GROUP_GUEST_ID,
			ECommerceConstants.EC_PRODUCT_TYPE_NAME_MARKETPLACE_APP_SUPPORT);

		if (ecProductType == null) {
			ecProductType = ECProductTypeLocalServiceUtil.addECProductType(
				OSBConstants.GROUP_GUEST_ID,
				ECommerceConstants.
				EC_PRODUCT_TYPE_NAME_MARKETPLACE_APP_SUPPORT);
		}

		ECommerceConstants.EC_PRODUCT_TYPE_ID_MARKETPLACE_APP_SUPPORT =
			ecProductType.getEcProductTypeId();

		ecProductTypeCode =
			ECProductTypeCodeLocalServiceUtil.fetchECProductTypeCode(
				ecProductType.getEcProductTypeId(),
				"com.liferay.ecommerce.tax.processor.AvalaraTaxProcessor");

		if (ecProductTypeCode == null) {
			ECProductTypeCodeLocalServiceUtil.addECProductTypeCode(
				OSBConstants.GROUP_GUEST_ID, ecProductType.getEcProductTypeId(),
				"com.liferay.ecommerce.tax.processor.AvalaraTaxProcessor",
				"EXAMPLE-TAX-CODE");
		}

		// Developer subscription

		ecProductType = ECProductTypeLocalServiceUtil.fetchECProductType(
			OSBConstants.GROUP_GUEST_ID,
			ECommerceConstants.EC_PRODUCT_TYPE_NAME_MARKETPLACE_SUBSCRIPTION);

		if (ecProductType == null) {
			ecProductType = ECProductTypeLocalServiceUtil.addECProductType(
				OSBConstants.GROUP_GUEST_ID,
				ECommerceConstants.
				EC_PRODUCT_TYPE_NAME_MARKETPLACE_SUBSCRIPTION);
		}

		ECommerceConstants.EC_PRODUCT_TYPE_ID_MARKETPLACE_SUBSCRIPTION =
			ecProductType.getEcProductTypeId();

		ECProductEntry ecProductEntry =
			ECProductEntryLocalServiceUtil.fetchECProductEntry(
				ECommerceConstants.EC_PRODUCT_TYPE_ID_MARKETPLACE_SUBSCRIPTION,
				ECommerceConstants.
				EC_PRODUCT_ENTRY_NAME_MARKETPLACE_SUBSCRIPTION);

		if (ecProductEntry == null) {
			ecProductEntry = ECProductEntryLocalServiceUtil.addECProductEntry(
				OSBConstants.USER_DEFAULT_USER_ID, OSBConstants.GROUP_GUEST_ID,
				ECommerceConstants.EC_PRODUCT_TYPE_ID_MARKETPLACE_SUBSCRIPTION,
				ECommerceConstants.
				EC_PRODUCT_ENTRY_NAME_MARKETPLACE_SUBSCRIPTION, "USD", 99.00);
		}

		ECommerceConstants.EC_PRODUCT_ENTRY_ID_MARKETPLACE_SUBSCRIPTION =
			ecProductEntry.getEcProductEntryId();
	}

}