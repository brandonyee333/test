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

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.marketplace.util.MarketplaceEmailUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AssetReceiptLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.ratings.model.RatingsEntry;
import com.liferay.portlet.ratings.service.RatingsEntryLocalServiceUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Joan Kim
 */
public class SendAppEntryReviewRemindersMessageListener
	extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		Calendar calendar = CalendarFactoryUtil.getCalendar();

		calendar.add(Calendar.DATE, -30);

		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		Date createDateGT = calendar.getTime();

		calendar.add(Calendar.DATE, 1);

		Date createDateLT = calendar.getTime();

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("createDateGT", createDateGT);
		params.put("createDateLT", createDateLT);

		List<AssetReceipt> assetReceipts =
			AssetReceiptLocalServiceUtil.getAssetReceipts(
				PortalUtil.getClassNameId(AppEntry.class), params,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		Map<String, AssetReceipt> assetReceiptsMap =
			new HashMap<String, AssetReceipt>(assetReceipts.size());

		for (AssetReceipt assetReceipt : assetReceipts) {
			String key =
				assetReceipt.getUserId() + StringPool.UNDERLINE +
					assetReceipt.getProductClassPK();

			if (!assetReceiptsMap.containsKey(key)) {
				assetReceiptsMap.put(key, assetReceipt);
			}
		}

		for (Map.Entry<String, AssetReceipt> entry :
				assetReceiptsMap.entrySet()) {

			AssetReceipt assetReceipt = entry.getValue();

			String productClassName = assetReceipt.getProductClassName();

			RatingsEntry ratingsEntry = RatingsEntryLocalServiceUtil.fetchEntry(
				assetReceipt.getUserId(), productClassName,
				assetReceipt.getProductClassPK());

			if (ratingsEntry != null) {
				continue;
			}

			if (productClassName.equals(AppEntry.class.getName())) {
				AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
					assetReceipt.getProductClassPK());

				if (appEntry.isLiferayEEPlugin()) {
					continue;
				}
			}

			MarketplaceEmailUtil.sendAppEntryReviewMail(assetReceipt);
		}
	}

}