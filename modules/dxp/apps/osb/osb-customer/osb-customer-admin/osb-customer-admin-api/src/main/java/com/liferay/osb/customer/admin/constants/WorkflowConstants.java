/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.constants;

/**
 * @author Ryan Park
 * @author Wesley Gong
 */
public class WorkflowConstants
	extends com.liferay.portal.kernel.workflow.WorkflowConstants {

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

		return com.liferay.portal.kernel.workflow.WorkflowConstants.
			getLabelStatus(label);
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

		return com.liferay.portal.kernel.workflow.WorkflowConstants.
			getStatusLabel(status);
	}

}