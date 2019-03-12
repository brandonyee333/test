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

package com.liferay.osb.util;

import com.liferay.portal.kernel.util.Constants;

/**
 * @author Amos Fong
 */
public class SalesforceConstants {

	public static final String OPPORTUNITY_STAGE_CLOSED_LOST = "Closed Lost";

	public static final String OPPORTUNITY_STAGE_CLOSED_WON = "Closed Won";

	public static final int OPPORTUNITY_TYPE_EXISTING_BUSINESS = 1;

	public static final int OPPORTUNITY_TYPE_NEW_BUSINESS = 2;

	public static final int OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS = 3;

	public static final int OPPORTUNITY_TYPE_RENEWAL = 4;

	public static final String PRODUCT_TYPE_ADD_TO = "Add to";

	public static final String PRODUCT_TYPE_NEW = "New";

	public static final String PRODUCT_TYPE_RENEWAL_DOWNGRADE =
		"Renewal Downgrade";

	public static final String PRODUCT_TYPE_RENEWAL_MIGRATION =
		"Renewal Migration";

	public static final String PRODUCT_TYPE_RENEWAL_UPGRADE = "Renewal Upgrade";

	public static final String PROJECT_RECORD_TYPE_ANALYTICS_CLOUD_ID =
		"0120g0000009u0wAAA";

	public static String getOpportunityTaskName(
		int opportunityType, String action) {

		String taskName = getOpportunityTypeLabel(opportunityType);

		if (action.equals(Constants.UPDATE)) {
			taskName += "-update";
		}

		return taskName;
	}

	public static String getOpportunityTypeLabel(int opportunityType) {
		if (opportunityType == OPPORTUNITY_TYPE_EXISTING_BUSINESS) {
			return "existing-business";
		}
		else if (opportunityType == OPPORTUNITY_TYPE_NEW_BUSINESS) {
			return "new-business";
		}
		else if (opportunityType ==
					OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS) {

			return "new-project-existing-business";
		}
		else if (opportunityType == OPPORTUNITY_TYPE_RENEWAL) {
			return "renewal";
		}
		else {
			return "unknown-opportunity";
		}
	}

}