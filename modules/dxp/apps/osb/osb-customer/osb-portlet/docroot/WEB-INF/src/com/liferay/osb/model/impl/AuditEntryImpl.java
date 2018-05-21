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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.AuditEntryConstants;
import com.liferay.osb.util.VisibilityConstants;

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