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

import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Kyle Bischof
 */
public class LicenseEntryConstants {

	public static final String TYPE_BACKUP = "backup";

	public static final String TYPE_CLUSTER = "cluster";

	public static final String TYPE_DEVELOPER = "developer";

	public static final String TYPE_DEVELOPER_CLUSTER = "developer-cluster";

	public static final String TYPE_ELASTIC = "elastic";

	public static final String TYPE_ENTERPRISE = "enterprise";

	public static final String TYPE_LIMITED = "limited";

	public static final String TYPE_NON_PRODUCTION = "non-production";

	public static final String TYPE_OEM = "oem";

	public static final String TYPE_PER_USER = "per-user";

	public static final String TYPE_PRODUCTION = "production";

	public static final String TYPE_TRIAL = "trial";

	public static final String[] TYPES = {
		TYPE_CLUSTER, TYPE_DEVELOPER, TYPE_DEVELOPER_CLUSTER, TYPE_ELASTIC,
		TYPE_ENTERPRISE, TYPE_LIMITED, TYPE_OEM, TYPE_PER_USER, TYPE_PRODUCTION,
		TYPE_TRIAL
	};

	public static String getPortalVersionLabel(
		int portalVersionMin, int portalVersionMax) {

		StringBundler sb = new StringBundler();

		try {
			ListType listType = ListTypeServiceUtil.getListType(
				portalVersionMin);

			sb.append(listType.getName());
		}
		catch (Exception e) {
			return "N/A";
		}

		try {
			if (portalVersionMax ==
					ProductEntryConstants.PORTAL_VERSION_OTHER) {

				sb.append(StringPool.PLUS);
			}
			else {
				ListType listType = ListTypeServiceUtil.getListType(
					portalVersionMax);

				sb.append(" - ");
				sb.append(listType.getName());
			}
		}
		catch (Exception e) {
			return "N/A";
		}

		return sb.toString();
	}

}