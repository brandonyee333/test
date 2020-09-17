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