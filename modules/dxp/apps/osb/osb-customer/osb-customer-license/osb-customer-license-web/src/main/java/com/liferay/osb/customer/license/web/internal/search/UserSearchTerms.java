/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.web.internal.search;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import javax.portlet.PortletRequest;

/**
 * @author Kyle Bischof
 */
public class UserSearchTerms extends UserDisplayTerms {

	public UserSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		emailAddress = ParamUtil.getString(portletRequest, EMAIL_ADDRESS);
		firstName = ParamUtil.getString(portletRequest, FIRST_NAME);
		lastName = ParamUtil.getString(portletRequest, LAST_NAME);
		middleName = ParamUtil.getString(portletRequest, MIDDLE_NAME);
		organizationId = ParamUtil.getLong(portletRequest, ORGANIZATION_ID);
		roleId = ParamUtil.getLong(portletRequest, ROLE_ID);
		screenName = ParamUtil.getString(portletRequest, SCREEN_NAME);
		status = ParamUtil.getInteger(
			portletRequest, STATUS, WorkflowConstants.STATUS_APPROVED);
		userGroupId = ParamUtil.getLong(portletRequest, USER_GROUP_ID);
	}

}