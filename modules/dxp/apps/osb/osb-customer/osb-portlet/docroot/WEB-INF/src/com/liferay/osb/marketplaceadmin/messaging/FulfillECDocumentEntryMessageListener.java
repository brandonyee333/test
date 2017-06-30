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

package com.liferay.osb.marketplaceadmin.messaging;

import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.ecommerce.service.ECDocumentEntryLocalServiceUtil;
import com.liferay.osb.marketplace.util.ECDocumentEntryExtraSettings;
import com.liferay.osb.marketplace.util.ECommerceConstants;
import com.liferay.osb.marketplace.util.MarketplaceEmailUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetReceiptConstants;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Ryan Park
 */
public class FulfillECDocumentEntryMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("fulfilled", Boolean.FALSE);
		params.put(
			"paidDateLT", new Date(System.currentTimeMillis() - Time.MINUTE));
		params.put("storeName", ECommerceConstants.STORE_NAME_MARKETPLACE);

		List<ECDocumentEntry> ecDocumentEntries =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntries(
				OSBConstants.GROUP_GUEST_ID, params, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		for (ECDocumentEntry ecDocumentEntry : ecDocumentEntries) {
			_log.error(
				"Failed to fulfill document #" +
					ecDocumentEntry.getEcDocumentEntryId() +
						"; automatically fulfilling the purchase");

			ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
				new ECDocumentEntryExtraSettings(ecDocumentEntry);

			String productType = ecDocumentEntryExtraSettings.getProductType();

			if (productType.equals("developer-subscription")) {
				DeveloperEntryServiceUtil.updateSubscription(
					ecDocumentEntryExtraSettings.getDeveloperEntryId(),
					new Date(System.currentTimeMillis() + Time.YEAR),
					WorkflowConstants.STATUS_DRAFT);
			}
			else {
				AssetReceiptLocalServiceUtil.purchaseAssets(
					ecDocumentEntry.getUserId(),
					ecDocumentEntryExtraSettings.getOwnerClassName(),
					ecDocumentEntryExtraSettings.getOwnerClassPK(),
					ecDocumentEntryExtraSettings.getLegalEntityName(),
					AppEntry.class.getName(),
					ecDocumentEntryExtraSettings.getAppEntryId(),
					AssetReceiptConstants.TYPE_PURCHASE_APP,
					ecDocumentEntry.getEcDocumentEntryId(),
					ecDocumentEntryExtraSettings.getCountryId(),
					ecDocumentEntryExtraSettings.getDomain());

				MarketplaceEmailUtil.sendPurchaseNotificationEmail(
					ecDocumentEntry);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		FulfillECDocumentEntryMessageListener.class);

}