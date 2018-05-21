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

package com.liferay.osb.admin.asset;

import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Amos Fong
 */
public class OrderEntryAssetRendererFactory
	extends AccountEntryAssetRendererFactory {

	public static final String CLASS_NAME = OrderEntry.class.getName();

	public static final String TYPE = "order";

	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException {

		OrderEntry orderEntry = OrderEntryLocalServiceUtil.getOrderEntry(
			classPK);

		return new OrderEntryAssetRenderer(orderEntry);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getType() {
		return TYPE;
	}

}