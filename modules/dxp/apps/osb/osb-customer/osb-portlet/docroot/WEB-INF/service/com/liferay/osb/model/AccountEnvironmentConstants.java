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

import com.liferay.portal.model.ListType;
import com.liferay.portal.service.ListTypeServiceUtil;

/**
 * @author Kyle Bischof
 */
public class AccountEnvironmentConstants {

	public static final String NOT_AVAILABLE = "N/A";

	public static String getEnvLabel(int envType) {
		try {
			ListType listType = ListTypeServiceUtil.getListType(envType);

			return listType.getName();
		}
		catch (Exception e) {
			return NOT_AVAILABLE;
		}
	}

}