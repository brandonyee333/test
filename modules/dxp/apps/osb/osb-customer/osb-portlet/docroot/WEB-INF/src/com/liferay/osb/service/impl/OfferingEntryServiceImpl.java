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

import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.service.base.OfferingEntryServiceBaseImpl;
import com.liferay.osb.service.permission.OSBOfferingEntryPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.ActionKeys;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class OfferingEntryServiceImpl extends OfferingEntryServiceBaseImpl {

	public OfferingEntry updateStatus(long offeringEntryId, int status)
		throws PortalException {

		OSBOfferingEntryPermission.check(
			getPermissionChecker(), offeringEntryId, ActionKeys.UPDATE);

		return offeringEntryLocalService.updateStatus(
			getUserId(), offeringEntryId, status);
	}

}