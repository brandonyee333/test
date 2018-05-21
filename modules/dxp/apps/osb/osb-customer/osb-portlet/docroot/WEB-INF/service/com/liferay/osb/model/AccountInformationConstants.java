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

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Alan Zhang
 * @author Amos Fong
 */
public class AccountInformationConstants {

	public static final int[] ACCOUNT_PROJECT_FIELD_IDS = {
		AccountInformationConstants.FIELD_PROJECT_CLIENTS_CONTACTS,
		AccountInformationConstants.FIELD_PROJECT_LIFERAY_CONTACTS,
		AccountInformationConstants.FIELD_PROJECT_TYPE,
		AccountInformationConstants.FIELD_PROJECT_NOTES,
		AccountInformationConstants.FIELD_PROJECT_DEBRIEF
	};

	public static final int FIELD_CONTACT_INFO = 1;

	public static final int FIELD_CONTACT_OVERVIEW = 4;

	public static final int FIELD_FEEDBACK = 7;

	public static final int FIELD_LICENSE_INFO = 8;

	public static final int FIELD_MAJOR_MILESTONES = 5;

	public static final int FIELD_OTHER_INFO = 6;

	public static final int FIELD_PROJECT_CLIENTS_CONTACTS = 11;

	public static final int FIELD_PROJECT_DEBRIEF = 15;

	public static final int FIELD_PROJECT_LIFERAY_CONTACTS = 12;

	public static final int FIELD_PROJECT_NOTES = 14;

	public static final int FIELD_PROJECT_OVERVIEW = 3;

	public static final int FIELD_PROJECT_TYPE = 13;

	public static final int FIELD_REASONS_FOR_STRATEGIC = 2;

	public static final int FIELD_SALESFORCE_URL = 9;

	public static final int FIELD_TRAININGS_ATTENDED = 10;

	public static final String SECTION_ADVOCACY_SPECIALIST =
		"advocacy-specialist-information";

	public static final int[] SECTION_ADVOCACY_SPECIALIST_FIELD_IDS = {
		FIELD_REASONS_FOR_STRATEGIC, FIELD_PROJECT_OVERVIEW,
		FIELD_CONTACT_OVERVIEW, FIELD_MAJOR_MILESTONES, FIELD_OTHER_INFO
	};

	public static final String SECTION_CALL_LOG = "call-log";

	public static final String SECTION_ON_BOARDING = "on-boarding-contact";

	public static final int[] SECTION_ON_BOARDING_FIELD_IDS =
		{FIELD_CONTACT_INFO};

	public static final String SECTION_SALES = "sales-information";

	public static final int[] SECTION_SALES_FIELD_IDS =
		{FIELD_LICENSE_INFO, FIELD_SALESFORCE_URL, FIELD_TRAININGS_ATTENDED};

	public static final String SECTION_SATISFACTION_METER =
		"satisfaction-meter";

	public static final int[] SECTION_SATISFACTION_METER_FIELD_IDS =
		{FIELD_FEEDBACK};

	public static final String[] SECTIONS_CUSTOMER_INFO = {
		SECTION_ON_BOARDING, SECTION_ADVOCACY_SPECIALIST, SECTION_SALES,
		SECTION_SATISFACTION_METER, SECTION_CALL_LOG
	};

	public static String getFieldLabel(int fieldId) {
		if (fieldId == FIELD_CONTACT_INFO) {
			return "contact-information";
		}
		else if (fieldId == FIELD_CONTACT_OVERVIEW) {
			return "contact-overview";
		}
		else if (fieldId == FIELD_FEEDBACK) {
			return "feedback-from-customer";
		}
		else if (fieldId == FIELD_LICENSE_INFO) {
			return "license-information";
		}
		else if (fieldId == FIELD_MAJOR_MILESTONES) {
			return "major-milestones-and-dates";
		}
		else if (fieldId == FIELD_OTHER_INFO) {
			return "other-information";
		}
		else if (fieldId == FIELD_PROJECT_CLIENTS_CONTACTS) {
			return "client-contacts";
		}
		else if (fieldId == FIELD_PROJECT_DEBRIEF) {
			return "debrief-of-project";
		}
		else if (fieldId == FIELD_PROJECT_LIFERAY_CONTACTS) {
			return "liferay-contacts";
		}
		else if (fieldId == FIELD_PROJECT_NOTES) {
			return "general-notes";
		}
		else if (fieldId == FIELD_PROJECT_OVERVIEW) {
			return "project-overview";
		}
		else if (fieldId == FIELD_PROJECT_TYPE) {
			return "type-of-project-project-dates";
		}
		else if (fieldId == FIELD_REASONS_FOR_STRATEGIC) {
			return "reasons-for-strategic";
		}
		else if (fieldId == FIELD_SALESFORCE_URL) {
			return "salesforce-url";
		}
		else if (fieldId == FIELD_TRAININGS_ATTENDED) {
			return "trainings-attended";
		}

		return StringPool.BLANK;
	}

	public static int[] getSectionFieldIds(String section) {
		if (section.equals(SECTION_ADVOCACY_SPECIALIST)) {
			return SECTION_ADVOCACY_SPECIALIST_FIELD_IDS;
		}
		else if (section.equals(SECTION_SALES)) {
			return SECTION_SALES_FIELD_IDS;
		}
		else if (section.equals(SECTION_ON_BOARDING)) {
			return SECTION_ON_BOARDING_FIELD_IDS;
		}
		else if (section.equals(SECTION_SATISFACTION_METER)) {
			return SECTION_SATISFACTION_METER_FIELD_IDS;
		}

		return new int[0];
	}

}