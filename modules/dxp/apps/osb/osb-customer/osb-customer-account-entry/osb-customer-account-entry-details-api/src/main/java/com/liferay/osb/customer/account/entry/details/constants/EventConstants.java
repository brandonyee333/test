/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.account.entry.details.constants;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Amos Fong
 */
public class EventConstants {

	public static final int TYPE_DELETE_ATTACHMENT = 3;

	public static final int TYPE_DOWNLOAD_ATTACHMENT = 1;

	public static final int TYPE_UPLOAD_ATTACHMENT = 2;

	public static String getTypeLabel(long type) {
		if (type == TYPE_DELETE_ATTACHMENT) {
			return "delete-attachment";
		}
		else if (type == TYPE_DOWNLOAD_ATTACHMENT) {
			return "download-attachment";
		}
		else if (type == TYPE_UPLOAD_ATTACHMENT) {
			return "upload-attachment";
		}

		return StringPool.BLANK;
	}

}