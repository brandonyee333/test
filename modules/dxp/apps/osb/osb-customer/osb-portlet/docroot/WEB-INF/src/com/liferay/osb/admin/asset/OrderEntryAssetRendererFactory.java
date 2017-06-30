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

package com.liferay.osb.admin.asset;

import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.service.OrderEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.asset.model.AssetRenderer;

/**
 * @author Amos Fong
 */
public class OrderEntryAssetRendererFactory
	extends AccountEntryAssetRendererFactory {

	public static final String CLASS_NAME = OrderEntry.class.getName();

	public static final String TYPE = "order";

	public AssetRenderer getAssetRenderer(long classPK, int type)
		throws PortalException, SystemException {

		OrderEntry orderEntry = OrderEntryLocalServiceUtil.getOrderEntry(
			classPK);

		return new OrderEntryAssetRenderer(orderEntry);
	}

	public String getClassName() {
		return CLASS_NAME;
	}

	public String getType() {
		return TYPE;
	}

	@Override
	protected String getIconPath(ThemeDisplay themeDisplay) {
		return themeDisplay.getPathThemeImages() + "/shopping/cart.png";
	}

}