/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.model.impl;

import com.liferay.osb.customer.admin.constants.AuditEntryConstants;
import com.liferay.osb.customer.admin.constants.VisibilityConstants;

/**
 * @author Alan Zhang
 */
public class AuditEntryImpl extends AuditEntryBaseImpl {

	public AuditEntryImpl() {
	}

	public String getActionLabel() {
		return AuditEntryConstants.getActionLabel(getAction());
	}

	public String getFieldClassNameIdLabel() {
		return AuditEntryConstants.getFieldClassNameIdLabel(
			getFieldClassNameId());
	}

	public String getFieldLabel() {
		return AuditEntryConstants.getFieldLabel(getField());
	}

	public String getVisibilityLabel() {
		return VisibilityConstants.toLabel(getVisibility());
	}

}