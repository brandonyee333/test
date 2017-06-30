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

/**
 * @author Ryan Park
 * @author Wesley Gong
 */
public class WorkflowConstants
	extends com.liferay.portal.kernel.workflow.WorkflowConstants {

	public static final String CONTEXT_ACCOUNT_ENTRY_NAME =
		"osbAccountEntryName";

	public static final String CONTEXT_EXISTING_ORDER_ENTRY_IDS =
		"osbExistingOrderEntryIds";

	public static final String CONTEXT_MISSING_USERS = "osbMissingUsers";

	public static final String CONTEXT_NEW_ACCOUNT_ENTRY_ATTRIBUTES =
		"osbNewAccountEntryAttributes";

	public static final String CONTEXT_OLD_ACCOUNT_ENTRY_ATTRIBUTES =
		"osbOldAccountEntryAttributes";

	public static final String CONTEXT_ORDER_ENTRIES = "osbOrderEntries";

	public static final String CONTEXT_ORDER_ENTRY = "osbOrderEntry";

	public static final String CONTEXT_SALESFORCE_OPPORTUNITY_ACTION =
		"osbSalesforceOpportunityAction";

	public static final String CONTEXT_SALESFORCE_OPPORTUNITY_KEY =
		"osbSalesforceOpportunityKey";

	public static final String CONTEXT_SALESFORCE_OPPORTUNITY_STAGE_NAME =
		"osbSalesforceOpportunityStageName";

	public static final String CONTEXT_SALESFORCE_OPPORTUNITY_TASK_NAME =
		"osbSalesforceOpportunityTaskName";

	public static final String CONTEXT_SALESFORCE_OPPORTUNITY_TYPE =
		"osbSalesforceOpportunityType";

	public static final String CONTEXT_SUPPORT_REGION_NAME =
		"osbSupportRegionName";

	public static final String CONTEXT_WARNING_MESSAGES = "osbWarningMessages";

	public static final String LABEL_CLOSED = "closed";

	public static final String LABEL_INCOMPLETE_TRAINING_REGISTRATION =
		"incomplete-training-registration";

	public static final String LABEL_PENDING_AUTO_QA = "pending-auto-qa";

	public static final String LABEL_PENDING_QA = "pending-qa";

	public static final String LABEL_PENDING_VALIDATION = "pending-validation";

	public static final String LABEL_REJECTED = "rejected";

	public static final int STATUS_APPROVED_HIDDEN = 102;

	public static final int STATUS_CLOSED = 400;

	public static final int STATUS_INCOMPLETE_TRAINING_REGISTRATION = 200;

	public static final int STATUS_MERGED = 300;

	public static final int STATUS_PENDING_AGREEMENT = 103;

	public static final int STATUS_PENDING_AUTO_QA = 105;

	public static final int STATUS_PENDING_FINAL_REMINDER = 104;

	public static final int STATUS_PENDING_QA = 101;

	public static final int STATUS_PENDING_VALIDATION = 100;

	public static final int STATUS_REJECTED = 500;

	public static String toLabel(int status) {
		if (status == STATUS_CLOSED) {
			return LABEL_CLOSED;
		}
		else if (status == STATUS_INCOMPLETE_TRAINING_REGISTRATION) {
			return LABEL_INCOMPLETE_TRAINING_REGISTRATION;
		}
		else if (status == STATUS_PENDING_AUTO_QA) {
			return LABEL_PENDING_AUTO_QA;
		}
		else if (status == STATUS_PENDING_QA) {
			return LABEL_PENDING_QA;
		}
		else if (status == STATUS_PENDING_VALIDATION) {
			return LABEL_PENDING_VALIDATION;
		}
		else if (status == STATUS_REJECTED) {
			return LABEL_REJECTED;
		}
		else {
			return com.liferay.portal.kernel.workflow.WorkflowConstants.toLabel(
				status);
		}
	}

	public static int toStatus(String label) {
		if (label.equals(LABEL_INCOMPLETE_TRAINING_REGISTRATION)) {
			return STATUS_INCOMPLETE_TRAINING_REGISTRATION;
		}
		else if (label.equals(LABEL_PENDING_QA)) {
			return STATUS_PENDING_QA;
		}
		else if (label.equals(LABEL_PENDING_VALIDATION)) {
			return STATUS_PENDING_VALIDATION;
		}
		else {
			return
				com.liferay.portal.kernel.workflow.WorkflowConstants.toStatus(
					label);
		}
	}

}