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

package com.liferay.osb.marketplace.messaging;

import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.ecommerce.model.ECDocumentEntry;
import com.liferay.ecommerce.model.ECDocumentEntryConstants;
import com.liferay.ecommerce.service.ECDocumentEntryLocalServiceUtil;
import com.liferay.osb.marketplace.util.ECDocumentEntryExtraSettings;
import com.liferay.osb.marketplace.util.MarketplaceEmailUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetReceiptConstants;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Date;

/**
 * @author Ryan Park
 * @author Joan Kim
 */
public class ECDocumentEntryMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		int status = GetterUtil.getInteger(message.get("status"));

		if (!ECDocumentEntryConstants.isStatusPaid(status)) {
			return;
		}

		long ecDocumentEntryId = GetterUtil.getLong(
			message.get("ecDocumentEntryId"));

		ECDocumentEntry ecDocumentEntry =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
				ecDocumentEntryId);

		ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
			new ECDocumentEntryExtraSettings(ecDocumentEntry);

		if ((ecDocumentEntryExtraSettings.getVendorAmount() > 0) &&
			(status == ECDocumentEntryConstants.STATUS_PAID)) {

			MarketplaceEmailUtil.sendPayoutNotificationEmail(ecDocumentEntry);
		}

		if (ecDocumentEntry.getFulfillmentDate() != null) {
			return;
		}

		ECDocumentEntryLocalServiceUtil.commitECDocumentEntry(
			ecDocumentEntryId);

		String productType = ecDocumentEntryExtraSettings.getProductType();

		if (productType.equals("developer-subscription")) {
			Date now = new Date();

			DeveloperEntryServiceUtil.updateSubscription(
				ecDocumentEntryExtraSettings.getDeveloperEntryId(),
				new Date(now.getTime() + Time.YEAR),
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
				AssetReceiptConstants.TYPE_PURCHASE_APP, ecDocumentEntryId,
				ecDocumentEntryExtraSettings.getCountryId(),
				ecDocumentEntryExtraSettings.getDomain());

			if (ecDocumentEntry.getTotal() > 0) {
				MarketplaceEmailUtil.sendPurchaseNotificationEmail(
					ecDocumentEntry);
			}
		}
	}

}