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

		StringBundler sb = new StringBundler(3);

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