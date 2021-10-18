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

package com.liferay.osb.customer.zendesk.constants;

/**
 * @author Jenny Chen
 */
public interface ZendeskHeatScoreConstants {

	public static final String BUSINESS_PRIORITY = "Business priority";

	public static final String CAS_FIRE = "Fire";

	public static final String CAS_HOT = "Hot";

	public static final String CAS_RISK = "Risk";

	public static final String ENVIRONMENT_PRODUCTION = "Production";

	public static final String PRIORITY_HIGH = "High";

	public static final String PRIORITY_NORMAL = "Normal";

	public static final String PRIORITY_URGENT = "Urgent";

	public static final String SUB_STATUS_COMPLETED = "Completed";

	public static final String SUB_STATUS_DUPLICATE = "Duplicate";

	public static final String SUB_STATUS_INACTIVE = "Inactive";

	public static final String SUB_STATUS_REPRODUCED = "Reproduced";

	public static final String SUB_STATUS_RESOLUTION_PLANNING =
		"Resolution Planning";

	public static final String SUB_STATUS_SOLUTION_PROPOSED =
		"Solution Proposed";

	public static int getAgeScore(long days) {
		if (days <= 3) {
			return 1;
		}
		else if (days <= 6) {
			return 2;
		}
		else if (days <= 9) {
			return 3;
		}
		else if (days <= 12) {
			return 4;
		}

		return 5;
	}

	public static int getBusinessPriorityScore(String businessPriority) {
		if (businessPriority.equals(BUSINESS_PRIORITY)) {
			return 1;
		}

		return 0;
	}

	public static int getCASMetricsScore(String casMetrics) {
		if (casMetrics.equals(CAS_FIRE)) {
			return 10;
		}
		else if (casMetrics.equals(CAS_HOT)) {
			return 4;
		}
		else if (casMetrics.equals(CAS_RISK)) {
			return 2;
		}

		return 0;
	}

	public static int getEnvironmentScore(String environment) {
		if (environment.equals(ENVIRONMENT_PRODUCTION)) {
			return 2;
		}

		return 0;
	}

	public static int getPriorityScore(String priority) {
		if (priority.equals(PRIORITY_HIGH)) {
			return 6;
		}
		else if (priority.equals(PRIORITY_NORMAL)) {
			return 2;
		}
		else if (priority.equals(PRIORITY_URGENT)) {
			return 8;
		}

		return 1;
	}

	public static int getSubStatusScore(String subStatus) {
		if (subStatus.equals(SUB_STATUS_COMPLETED) ||
			subStatus.equals(SUB_STATUS_DUPLICATE) ||
			subStatus.equals(SUB_STATUS_INACTIVE)) {

			return 0;
		}
		else if (subStatus.equals(SUB_STATUS_REPRODUCED) ||
				 subStatus.equals(SUB_STATUS_RESOLUTION_PLANNING)) {

			return 2;
		}
		else if (subStatus.equals(SUB_STATUS_SOLUTION_PROPOSED)) {
			return 1;
		}

		return 3;
	}

}