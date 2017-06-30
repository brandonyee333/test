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

package com.liferay.osb.marketplaceserver.search;

import com.liferay.compat.portal.kernel.util.Validator;

import java.util.LinkedHashMap;

import javax.portlet.PortletRequest;

/**
 * @author Joan Kim
 * @author Douglas Wong
 */
public class AssetReceiptSearchTerms extends AssetReceiptDisplayTerms {

	public AssetReceiptSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);
	}

	public LinkedHashMap<String, Object> getParams() {
		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();

		if (assetCategoryId > 0) {
			params.put("assetCategoryIds", assetCategoryId);
		}

		if (Validator.isNotNull(assetEntryTitle)) {
			params.put("assetEntryTitle", assetEntryTitle);
		}

		if (filterByCompatibility) {
			params.put("compatibility", compatibility);
		}

		return params;
	}

}