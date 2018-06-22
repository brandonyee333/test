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

/**
 * @author Ryan Park
 * @author Wesley Gong
 */
public class WorkflowConstants
	extends com.liferay.portal.kernel.workflow.WorkflowConstants {

	public static final String CONTEXT_EXISTING_ORDER_ENTRY_IDS =
		"osbExistingOrderEntryIds";

	public static final String CONTEXT_MISSING_USERS = "osbMissingUsers";

	public static final String CONTEXT_NEW_ACCOUNT_ENTRY_ATTRIBUTES =
		"osbNewAccountEntryAttributes";

	public static final String CONTEXT_NEW_USERS = "osbNewUsers";

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

	public static final String LABEL_PENDING_VALIDATION = "pending-validation";

	public static final String LABEL_REJECTED = "rejected";

	public static final int STATUS_CLOSED = 400;

	public static final int STATUS_PENDING_VALIDATION = 100;

	public static final int STATUS_REJECTED = 500;

	public static int getLabelStatus(String label) {
		if (label.equals(LABEL_PENDING_VALIDATION)) {
			return STATUS_PENDING_VALIDATION;
		}
		else {
			return com.liferay.portal.kernel.workflow.WorkflowConstants.
				getLabelStatus(label);
		}
	}

	public static String getStatusLabel(int status) {
		if (status == STATUS_CLOSED) {
			return LABEL_CLOSED;
		}
		else if (status == STATUS_PENDING_VALIDATION) {
			return LABEL_PENDING_VALIDATION;
		}
		else if (status == STATUS_REJECTED) {
			return LABEL_REJECTED;
		}
		else {
			return com.liferay.portal.kernel.workflow.WorkflowConstants.
				getStatusLabel(status);
		}
	}

}