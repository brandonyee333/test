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