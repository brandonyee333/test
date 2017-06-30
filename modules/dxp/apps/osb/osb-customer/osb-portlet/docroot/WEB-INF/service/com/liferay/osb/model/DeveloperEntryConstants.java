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

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Douglas Wong
 */
public class DeveloperEntryConstants {

	public static final String[] DEVELOPER_TAX_DOCUMENT_NAMES = {
		"articles-of-incorporation", "business-license",
		"certificate-of-formation", "charter-documents", "form-w-9",
		"operating-agreement", "partnership-papers",
		"reseller-or-vendor-license"
	};

	public static final double FATCA_WITHOLDING_PERCENTAGE_DEFAULT = -1;

	public static final String[] PAID_APP_DEVELOPER_TAX_DOCUMENT_NAMES = {
		"form-w-9", "form-w-8ben", "form-w-8ben-e"
	};

	public static final int TYPE_COMPANY = 2;

	public static final String TYPE_COMPANY_LABEL = "company";

	public static final int TYPE_USER = 1;

	public static final String TYPE_USER_LABEL = "user";

	public static String toTypeLabel(int type) {
		if (type == TYPE_COMPANY) {
			return TYPE_COMPANY_LABEL;
		}
		else if (type == TYPE_USER) {
			return TYPE_USER_LABEL;
		}

		return StringPool.BLANK;
	}

}