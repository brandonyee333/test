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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.SecurityPatch;
import com.liferay.osb.service.base.SecurityPatchServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.security.auth.PrincipalException;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class SecurityPatchServiceImpl extends SecurityPatchServiceBaseImpl {

	public SecurityPatch getSecurityPatch(long securityPatchId)
		throws PortalException, SystemException {

		SecurityPatch securityPatch =
			securityPatchLocalService.getSecurityPatch(securityPatchId);

		checkPermission(securityPatch.getAccountEntryId());

		return securityPatch;
	}

	protected void checkPermission(long accountEntryId)
		throws PortalException, SystemException {

		if (organizationLocalService.hasUserOrganization(
				getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return;
		}

		if (!accountCustomerLocalService.hasAccountCustomer(
				getUserId(), accountEntryId)) {

			throw new PrincipalException();
		}
	}

}