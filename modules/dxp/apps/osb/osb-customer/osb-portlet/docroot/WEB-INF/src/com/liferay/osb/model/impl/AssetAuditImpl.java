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

package com.liferay.osb.model.impl;

import com.liferay.compat.portal.kernel.util.HttpUtil;
import com.liferay.osb.model.AssetAuditConstants;
import com.liferay.osb.util.PortletPropsValues;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetAuditImpl extends AssetAuditBaseImpl {

	public static int getDomain(String url) {
		String domain = HttpUtil.getDomain(url);

		if (domain.contains(PortletPropsValues.MARKETPLACE_IN_PRODUCT_DOMAIN)) {
			return AssetAuditConstants.DOMAIN_IN_PRODUCT;
		}
		else {
			return AssetAuditConstants.DOMAIN_LIFERAY;
		}
	}

	public AssetAuditImpl() {
	}

}