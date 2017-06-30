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

package com.liferay.osb.model;

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.service.CorpProjectLocalServiceUtil;
import com.liferay.portal.model.User;

/**
 * @author Kyle Bischof
 */
public class AssetReceiptConstants {

	public static final long TYPE_PURCHASE_APP = 0;

	public static String getOwnerName(
		String ownerClassName, long ownerClassPK, String defaultOwnerName) {

		try {
			if (ownerClassName.equals(CorpProject.class.getName())) {
				CorpProject corpProject =
					CorpProjectLocalServiceUtil.getCorpProject(ownerClassPK);

				return corpProject.getName();
			}
			else if (ownerClassName.equals(User.class.getName())) {
				return PortalUtil.getUserName(ownerClassPK, defaultOwnerName);
			}
		}
		catch (Exception e) {
		}

		return defaultOwnerName;
	}

}