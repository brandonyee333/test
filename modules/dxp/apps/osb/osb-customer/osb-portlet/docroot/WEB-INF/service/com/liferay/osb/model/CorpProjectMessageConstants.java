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

/**
 * @author Amos Fong
 */
public class CorpProjectMessageConstants {

	public static final String AUTOMATED_TEMPLATE_PAST_DUE = "pastDue";

	public static final String AUTOMATED_TEMPLATE_WARNING = "warning";

	public static final int SEVERITY_LEVEL_INFO = 3;

	public static final int SEVERITY_LEVEL_URGENT = 1;

	public static final int SEVERITY_LEVEL_WARNING = 2;

	public static final int TYPE_AUTOMATED_SUBSCRIPTION_STATUS = 2;

	public static final int TYPE_SUBSCRIPTION_STATUS = 1;

	public static String getSeverityLevelLabel(int severityLevel) {
		if (severityLevel == SEVERITY_LEVEL_INFO) {
			return "info";
		}
		else if (severityLevel == SEVERITY_LEVEL_URGENT) {
			return "urgent";
		}
		else if (severityLevel == SEVERITY_LEVEL_WARNING) {
			return "warning";
		}

		return "N/A";
	}

	public static String getTypeLabel(int type) {
		if (type == TYPE_AUTOMATED_SUBSCRIPTION_STATUS) {
			return "automated-subscription-status";
		}
		else if (type == TYPE_SUBSCRIPTION_STATUS) {
			return "subscription-status";
		}

		return "N/A";
	}

}