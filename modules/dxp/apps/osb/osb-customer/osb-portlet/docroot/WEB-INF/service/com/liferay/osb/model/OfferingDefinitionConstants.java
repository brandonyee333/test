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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;

/**
 * @author Kyle Bischof
 */
public class OfferingDefinitionConstants {

	public static final long LIFETIME_INDEFINITE_VALUE =
		36500 * 1440 * Time.MINUTE;

	public static final Object[][] LIFETIME_VALUES_ARRAY = {
		{1, "1-minute"}, {30 * 1440, "30-days"}, {60 * 1440, "60-days"},
		{90 * 1440, "90-days"}, {150 * 1440, "150-days"},
		{183 * 1440, "6-months"}, {365 * 1440, "1-year"},
		{465 * 1440, "15-months"}, {730 * 1440, "2-years"},
		{1095 * 1440, "3-years"}, {1825 * 1440, "5-years"},
		{36500 * 1440, "indefinite"}
	};

	public static String getCustomLifetimeLabel(long lifetime) {
		StringBundler sb = new StringBundler(7);

		sb.append(StringPool.TILDE);

		long years = lifetime / Time.YEAR;

		if (years > 0) {
			sb.append(StringPool.SPACE);
			sb.append(years);

			if (years == 1) {
				sb.append(" Year");
			}
			else {
				sb.append(" Years");
			}
		}

		long days = (lifetime % Time.YEAR) / Time.DAY;

		if ((years <= 0) || (days > 0)) {
			sb.append(StringPool.SPACE);
			sb.append(days);

			if (days == 1) {
				sb.append(" Day");
			}
			else {
				sb.append(" Days");
			}
		}

		return sb.toString();
	}

	public static String getLifetimeLabel(long lifetime) {
		for (Object[] lifetimeValues : LIFETIME_VALUES_ARRAY) {
			long lifetimeValue = Time.MINUTE * (Integer)lifetimeValues[0];
			String lifetimeLabel = String.valueOf(lifetimeValues[1]);

			if (lifetimeValue == lifetime) {
				return lifetimeLabel;
			}
		}

		return null;
	}

	public static long getLifetimeValue(String label) {
		for (Object[] lifetimeValues : LIFETIME_VALUES_ARRAY) {
			long lifetimeValue = Time.MINUTE * (Integer)lifetimeValues[0];
			String lifetimeLabel = String.valueOf(lifetimeValues[1]);

			if (lifetimeLabel.equals(label)) {
				return lifetimeValue;
			}
		}

		return 0;
	}

	public static String getMaxConcurrentUsersLabel(long maxConcurrentUsers) {
		if (maxConcurrentUsers <= 0) {
			return "unlimited";
		}

		return String.valueOf(maxConcurrentUsers);
	}

	public static String getMaxUsersLabel(long maxUsers) {
		if (maxUsers <= 0) {
			return "unlimited";
		}

		return String.valueOf(maxUsers);
	}

}