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

import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.osb.AssetLicenseLicenseTypeException;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Kyle Bischof
 */
public class AssetLicenseConstants {

	public static final String LICENSE_KEY_TYPE_ENTERPRISE = "enterprise";

	public static final String LICENSE_KEY_TYPE_PER_USER = "per-user";

	public static final String LICENSE_KEY_TYPE_PRODUCTION = "production";

	public static final String LICENSE_KEY_TYPE_UNLIMITED = "unlimited";

	public static final int LICENSE_TYPE_ALLOTMENT_UNLIMITED = 0;

	public static final int LICENSE_TYPE_CUSTOM = -2;

	public static final int LICENSE_TYPE_ENTERPRISE = 4;

	public static final int LICENSE_TYPE_FREE = -1;

	public static final int LICENSE_TYPE_PER_USER = 2;

	public static final int LICENSE_TYPE_PRODUCTION = 1;

	public static final int LICENSE_TYPE_UNLIMITED = 3;

	public static final int[] LICENSE_TYPES = {
		LICENSE_TYPE_ENTERPRISE, LICENSE_TYPE_PRODUCTION, LICENSE_TYPE_PER_USER,
		LICENSE_TYPE_UNLIMITED
	};

	public static final long LIFETIME_PERPETUAL = 36500 * Time.DAY;

	public static final long LIFETIME_SUBSCRIPTION = 365 * Time.DAY;

	public static final long LIFETIME_TRIAL = 30 * Time.DAY;

	public static final long[] LIFETIMES =
		{LIFETIME_PERPETUAL, LIFETIME_SUBSCRIPTION, LIFETIME_TRIAL};

	public static final int USAGE_TYPE_DEVELOPER = 2;

	public static final int USAGE_TYPE_STANDARD = 1;

	public static final int USAGE_TYPE_TRIAL = 3;

	public static final int[] USAGE_TYPES =
		{USAGE_TYPE_STANDARD, USAGE_TYPE_DEVELOPER, USAGE_TYPE_TRIAL};

	public static String getLicenseKeyType(int licenseType)
		throws PortalException {

		if (licenseType == LICENSE_TYPE_ENTERPRISE) {
			return LICENSE_KEY_TYPE_ENTERPRISE;
		}
		else if (licenseType == LICENSE_TYPE_PER_USER) {
			return LICENSE_KEY_TYPE_PER_USER;
		}
		else if (licenseType == LICENSE_TYPE_PRODUCTION) {
			return LICENSE_KEY_TYPE_PRODUCTION;
		}
		else if (licenseType == LICENSE_TYPE_UNLIMITED) {
			return LICENSE_KEY_TYPE_UNLIMITED;
		}
		else {
			throw new AssetLicenseLicenseTypeException();
		}
	}

	public static String getLicenseTypeLabel(int licenseType) {
		if (licenseType == LICENSE_TYPE_ENTERPRISE) {
			return "enterprise";
		}
		else if (licenseType == LICENSE_TYPE_FREE) {
			return "free";
		}
		else if (licenseType == LICENSE_TYPE_PER_USER) {
			return "user";
		}
		else if (licenseType == LICENSE_TYPE_PRODUCTION) {
			return "instance";
		}
		else if (licenseType == LICENSE_TYPE_UNLIMITED) {
			return "unlimited";
		}
		else {
			return "N/A";
		}
	}

	public static String getLifetimeLabel(long lifetime) {
		if (lifetime == LIFETIME_PERPETUAL) {
			return "perpetual";
		}
		else if (lifetime == LIFETIME_SUBSCRIPTION) {
			return "subscription";
		}
		else if (lifetime == LIFETIME_TRIAL) {
			return "trial";
		}
		else {
			return "N/A";
		}
	}

	public static String getUsageTypeLabel(int usageType) {
		if (usageType == USAGE_TYPE_DEVELOPER) {
			return "developer";
		}
		else if (usageType == USAGE_TYPE_STANDARD) {
			return "standard";
		}
		else if (usageType == USAGE_TYPE_TRIAL) {
			return "trial";
		}
		else {
			return "N/A";
		}
	}

}