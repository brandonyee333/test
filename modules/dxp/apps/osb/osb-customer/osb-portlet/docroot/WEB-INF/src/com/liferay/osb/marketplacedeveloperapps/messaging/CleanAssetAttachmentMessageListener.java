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

package com.liferay.osb.marketplacedeveloperapps.messaging;

import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.osb.model.AssetAttachmentConstants;
import com.liferay.osb.service.AssetAttachmentLocalServiceUtil;
import com.liferay.osb.util.PortletPropsValues;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;

import java.util.Date;

/**
 * @author Ryan Park
 */
public class CleanAssetAttachmentMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		Date date = new Date(
			System.currentTimeMillis() - _ASSET_ATTACHMENT_UNASSIGNED_MAX_AGE);

		AssetAttachmentLocalServiceUtil.deleteAssetAttachments(
			date, AssetAttachmentConstants.CLASS_NAME_ID_DEFAULT,
			AssetAttachmentConstants.CLASS_PK_DEFAULT);
	}

	private static final long _ASSET_ATTACHMENT_UNASSIGNED_MAX_AGE =
		PortletPropsValues.ASSET_ATTACHMENT_UNASSIGNED_MAX_AGE * Time.DAY;

}