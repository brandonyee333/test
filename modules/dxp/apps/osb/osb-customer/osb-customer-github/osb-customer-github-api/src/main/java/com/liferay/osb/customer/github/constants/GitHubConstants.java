/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.github.constants;

import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Jenny Chen
 */
public class GitHubConstants {

	public static final String PAGE_FIRST = "first";

	public static final String PAGE_LAST = "last";

	public static final String PAGE_NEXT = "next";

	public static final String PAGE_PREV = "prev";

	public static final String PAGE_REL = "rel";

	public static final int[] STATUSES_ACTIVE = {
		WorkflowConstants.STATUS_APPROVED, WorkflowConstants.STATUS_PENDING
	};

}