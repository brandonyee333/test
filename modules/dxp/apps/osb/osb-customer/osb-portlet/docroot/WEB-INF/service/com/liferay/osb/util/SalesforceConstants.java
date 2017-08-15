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

package com.liferay.osb.util;

import com.liferay.portal.kernel.util.Constants;

/**
 * @author Amos Fong
 */
public class SalesforceConstants {

	public static final String OPPORTUNITY_STAGE_CLOSED_WON = "Closed Won";

	public static final int OPPORTUNITY_TYPE_EXISTING_BUSINESS = 1;

	public static final int OPPORTUNITY_TYPE_NEW_BUSINESS = 2;

	public static final int OPPORTUNITY_TYPE_NEW_PROJECT_EXISTING_BUSINESS = 3;

	public static final int OPPORTUNITY_TYPE_RENEWAL = 4;

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