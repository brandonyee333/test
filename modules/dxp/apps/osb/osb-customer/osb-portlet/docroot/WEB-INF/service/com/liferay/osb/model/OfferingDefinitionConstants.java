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

import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Kyle Bischof
 */
public class OfferingDefinitionConstants {

	public static final long LIFETIME_INDEFINITE_VALUE =
		36500 * 1440 * Time.MINUTE;

	public static final Object[][] LIFETIME_VALUES_ARRAY = new Object[][] {
		new Object[] {1, "1-minute"},
		new Object[] {30 * 1440, "30-days"},
		new Object[] {60 * 1440, "60-days"},
		new Object[] {90 * 1440, "90-days"},
		new Object[] {150 * 1440, "150-days"},
		new Object[] {183 * 1440, "6-months"},
		new Object[] {365 * 1440, "1-year"},
		new Object[] {465 * 1440, "15-months"},
		new Object[] {730 * 1440, "2-years"},
		new Object[] {1095 * 1440, "3-years"},
		new Object[] {1825 * 1440, "5-years"},
		new Object[] {36500 * 1440, "indefinite"}
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
		else {
			return String.valueOf(maxConcurrentUsers);
		}
	}

	public static String getMaxUsersLabel(long maxUsers) {
		if (maxUsers <= 0) {
			return "unlimited";
		}
		else {
			return String.valueOf(maxUsers);
		}
	}

}