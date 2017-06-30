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
import com.liferay.ecommerce.model.ECDocumentEntryConstants;
import com.liferay.ecommerce.service.ECDocumentEntryLocalServiceUtil;
import com.liferay.osb.marketplace.util.ECommerceConstants;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Douglas Wong
 * @author Joan Kim
 */
public class CleanECDocumentEntryMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		Date date = new Date(
			System.currentTimeMillis() - _MARKETPLACE_SALES_ORDER_MAX_AGE);

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		params.put("modifiedDateLT", date);
		params.put("storeName", ECommerceConstants.STORE_NAME_MARKETPLACE);
		params.put("type", ECDocumentEntryConstants.TYPE_SALES_ORDER);

		List<ECDocumentEntry> ecDocumentEntries =
			ECDocumentEntryLocalServiceUtil.getECDocumentEntries(
				OSBConstants.GROUP_GUEST_ID, params, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		for (ECDocumentEntry ecDocumentEntry : ecDocumentEntries) {
			ECDocumentEntryLocalServiceUtil.deleteECDocumentEntry(
				ecDocumentEntry);
		}
	}

	private static final long _MARKETPLACE_SALES_ORDER_MAX_AGE =
		PortletPropsValues.MARKETPLACE_SALES_ORDER_MAX_AGE * Time.DAY;

}