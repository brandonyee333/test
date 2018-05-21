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