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