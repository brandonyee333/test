/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.constants;

/**
 * @author Kyle Bischof
 */
public class AccountAttachmentConstants {

	public static final int TYPE_NORMAL = 0;

	public static final int TYPE_OEM_INSTRUCTIONS = 1;

	public static String getTypeLabel(int type) {
		if (type == TYPE_NORMAL) {
			return "normal";
		}
		else if (type == TYPE_OEM_INSTRUCTIONS) {
			return "oem-instructions";
		}

		return "none";
	}

}