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

package com.liferay.osb.service.impl;

import com.liferay.osb.model.SecurityPatch;
import com.liferay.osb.service.base.SecurityPatchServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.auth.PrincipalException;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class SecurityPatchServiceImpl extends SecurityPatchServiceBaseImpl {

	public SecurityPatch getSecurityPatch(long securityPatchId)
		throws PortalException {

		SecurityPatch securityPatch =
			securityPatchLocalService.getSecurityPatch(securityPatchId);

		checkPermission(securityPatch.getAccountEntryId());

		return securityPatch;
	}

	protected void checkPermission(long accountEntryId) throws PortalException {
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