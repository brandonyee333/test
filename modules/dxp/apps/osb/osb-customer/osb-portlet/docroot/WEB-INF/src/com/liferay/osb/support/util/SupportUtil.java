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

package com.liferay.osb.support.util;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Amos Fong
 * @author Máté Thurzó
 */
public class SupportUtil {

	/*
	TODO
	public static boolean hasSyncToLCS(AccountEntry accountEntry)
		throws Exception {

		Date createDate = accountEntry.getCreateDate();

		if (createDate.getTime() <= _SYNC_TO_LCS_TIME) {
			return true;
		}

		return false;
	}
	*/
	
	public static Set<String> getSelfProvisioningProducts(long accountEntryId)
		throws PortalException {

		Set<String> selfProvisioningProducts = new TreeSet<>();

		/*
		TODO
		LinkedHashMap params = new LinkedHashMap();

		params.put("license", StringPool.BLANK);

		List<OfferingEntry> offeringEntries =
			OfferingEntryLocalServiceUtil.search(
				0, accountEntryId,
				new int[] {OfferingEntryConstants.TYPE_REGULAR},
				new int[] {OfferingEntryConstants.STATUS_ACTIVE}, 0, 0, 0, 0, 0,
				0, params, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (OfferingEntry offeringEntry : offeringEntries) {
			ProductEntry productEntry = offeringEntry.getProductEntry();

			if (productEntry.getEnvironment() ==
					ProductEntryConstants.ENVIRONMENT_DEVELOPMENT) {

				continue;
			}

			if (productEntry.isCommerceSubscription()) {
				selfProvisioningProducts.add(
					ProductEntryConstants.ROOT_COMMERCE_SUBSCRIPTION);
			}

			if (productEntry.getType() == ProductEntryConstants.TYPE_PRIMARY) {
				if (productEntry.isDigitalEnterprise() ||
					productEntry.isDXPCloud()) {

					selfProvisioningProducts.add(
						ProductEntryConstants.ROOT_NAME_DIGITAL_ENTERPRISE);
				}

				if (productEntry.isPortal()) {
					selfProvisioningProducts.add(
						ProductEntryConstants.ROOT_NAME_PORTAL);
				}
			}
		}
		*/

		return selfProvisioningProducts;
	}

}