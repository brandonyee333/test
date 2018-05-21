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
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Kyle Bischof
 */
public class LicenseKeyConstants {

	public static final String SERVER_ID_CLUSTER = "Cluster";

	public static final String SERVER_ID_DEVELOPER = "Developer";

	public static final String SERVER_ID_ELASTIC = "Elastic";

	public static final String SERVER_ID_ENTERPRISE = "Enterprise";

	public static final String SERVER_ID_OEM = "OEM";

	public static final String SERVER_ID_TRIAL = "TRIAL";

	public static final int STATE_ABSENT = 1;

	public static final int STATE_EXPIRED = 2;

	public static final int STATE_GOOD = 3;

	public static final int STATE_INACTIVE = 4;

	public static final int STATE_INVALID = 5;

	public static final int STATE_OVERLOAD = 6;

	public static int getLicenseVersion(int productVersion) {
		if (productVersion >=
				ProductEntryConstants.DIGITAL_ENTERPRISE_VERSION_7_0_10) {

			return 5;
		}

		if (productVersion >= ProductEntryConstants.PORTAL_VERSION_6_1_20) {
			return 4;
		}

		if (productVersion >= ProductEntryConstants.PORTAL_VERSION_6_1_10) {
			return 3;
		}

		if ((productVersion >= ProductEntryConstants.PORTAL_VERSION_5_2_9) ||
			((productVersion >= ProductEntryConstants.PORTAL_VERSION_5_1_9) &&
			 (productVersion < ProductEntryConstants.PORTAL_VERSION_5_2_4))) {

			return 2;
		}

		return 1;
	}

	public static String getProductVersionLabel(int productVersion) {
		try {
			ListType listType = ListTypeServiceUtil.getListType(productVersion);

			return listType.getName();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	public static String getServerId(String licenseEntryType) {
		if (licenseEntryType.equals(LicenseEntryConstants.TYPE_CLUSTER) ||
			licenseEntryType.equals(
				LicenseEntryConstants.TYPE_DEVELOPER_CLUSTER)) {

			return SERVER_ID_CLUSTER;
		}
		else if (licenseEntryType.equals(
					LicenseEntryConstants.TYPE_DEVELOPER)) {

			return SERVER_ID_DEVELOPER;
		}
		else if (licenseEntryType.equals(LicenseEntryConstants.TYPE_ELASTIC)) {
			return SERVER_ID_ELASTIC;
		}
		else if (licenseEntryType.equals(
					LicenseEntryConstants.TYPE_ENTERPRISE)) {

			return SERVER_ID_ENTERPRISE;
		}
		else if (licenseEntryType.equals(LicenseEntryConstants.TYPE_OEM)) {
			return SERVER_ID_OEM;
		}
		else if (licenseEntryType.equals(LicenseEntryConstants.TYPE_TRIAL)) {
			return SERVER_ID_TRIAL;
		}
		else {
			return StringPool.BLANK;
		}
	}

}