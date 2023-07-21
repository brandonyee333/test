/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.web.internal.configuration;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Amos Fong
 */
public class LicenseWebConfigurationValues {

	public static final String PROVISIONING_ACCOUNT_LINK = GetterUtil.getString(
		LicenseWebConfigurationUtil.get("provisioning.account.link"));

	public static final String PROVISIONING_PRODUCT_PURCHASE_LINK =
		GetterUtil.getString(
			LicenseWebConfigurationUtil.get(
				"provisioning.product.purchase.link"));

	public static String getProvisioningAccountLink(
		String koroneikiAccountKey) {

		return StringUtil.replace(
			PROVISIONING_ACCOUNT_LINK, "[$ACCOUNT_KEY$]", koroneikiAccountKey);
	}

	public static String getProvisioningProductPurchaseLink(
		String koroneikiAccountKey, String koroneikiProductKey,
		String koroneikiProductPurchaseKey) {

		String provisioningProductPurchaseLink = StringUtil.replace(
			PROVISIONING_PRODUCT_PURCHASE_LINK, "[$ACCOUNT_KEY$]",
			koroneikiAccountKey);

		provisioningProductPurchaseLink = StringUtil.replace(
			provisioningProductPurchaseLink, "[$PRODUCT_KEY$]",
			koroneikiProductKey);

		return StringUtil.replace(
			provisioningProductPurchaseLink, "[$PRODUCT_PURCHASE_KEY$]",
			koroneikiProductPurchaseKey);
	}

}