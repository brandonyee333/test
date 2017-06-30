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

/**
 * @author Kyle Bischof
 */
public class AccountEnvironmentAttachmentConstants {

	public static final int TYPE_PATCH_LEVEL = 2;

	public static final int TYPE_PORTAL_EXT = 1;

	public static String getTypeLabel(int type) {
		if (type == TYPE_PATCH_LEVEL) {
			return "patch-level";
		}
		else if (type == TYPE_PORTAL_EXT) {
			return "portal-ext";
		}
		else {
			return null;
		}
	}

}