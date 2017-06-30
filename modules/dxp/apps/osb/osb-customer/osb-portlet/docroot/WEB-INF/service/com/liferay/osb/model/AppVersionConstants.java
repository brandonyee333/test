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

package com.liferay.osb.model;

import com.liferay.osb.util.WorkflowConstants;

/**
 * @author Kyle Bischof
 */
public class AppVersionConstants {

	public static final int PRODUCT_TYPE_CE = 1;

	public static final int PRODUCT_TYPE_EE = 2;

	public static final int RELEASE_TYPE_METADATA = 2;

	public static final int RELEASE_TYPE_NEW_VERSION = 1;

	public static final int RELEASE_TYPE_PRICING = 3;

	public static final int[] STATUSES_APPROVED = {
		WorkflowConstants.STATUS_APPROVED,
		WorkflowConstants.STATUS_APPROVED_HIDDEN
	};

	public static final int[] STATUSES_PENDING = {
		WorkflowConstants.STATUS_PENDING,
		WorkflowConstants.STATUS_PENDING_AUTO_QA,
		WorkflowConstants.STATUS_PENDING_QA
	};

	public static final int[] STATUSES_UNRELEASED = {
		WorkflowConstants.STATUS_DENIED, WorkflowConstants.STATUS_DRAFT,
		WorkflowConstants.STATUS_PENDING,
		WorkflowConstants.STATUS_PENDING_AUTO_QA,
		WorkflowConstants.STATUS_PENDING_QA
	};

	public static final int[] STATUSES_USER_EDITABLE = {
		WorkflowConstants.STATUS_DENIED, WorkflowConstants.STATUS_DRAFT
	};

	public static final int VERSION_ORDER_FIRST = 0;

	public static final int VERSION_ORDER_LAST = -1;

	public static String getProductTypeLabel(int productType) {
		if (productType == PRODUCT_TYPE_CE) {
			return "ce-only";
		}
		else if (productType == PRODUCT_TYPE_EE) {
			return "ee-only";
		}
		else {
			return "N/A";
		}
	}

}