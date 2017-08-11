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

import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.service.base.PartnerEntryServiceBaseImpl;
import com.liferay.osb.service.permission.OSBPartnerEntryPermission;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Shinn Lok
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class PartnerEntryServiceImpl extends PartnerEntryServiceBaseImpl {

	public PartnerEntry getPartnerEntry(long partnerEntryId)
		throws PortalException {

		OSBPartnerEntryPermission.check(
			getPermissionChecker(), partnerEntryId, ActionKeys.VIEW);

		return partnerEntryLocalService.getPartnerEntry(partnerEntryId);
	}

	public List<PartnerEntry> search(
			String code, int[] statuses, LinkedHashMap<String, Object> params,
			boolean andOperator, int start, int end)
		throws PortalException {

		addAccountMembershipParams(params);

		return partnerEntryLocalService.search(
			code, statuses, params, andOperator, start, end);
	}

	public List<PartnerEntry> search(
			String keywords, LinkedHashMap<String, Object> params, int start,
			int end)
		throws PortalException {

		addAccountMembershipParams(params);

		return partnerEntryLocalService.search(keywords, params, start, end);
	}

	public int searchCount(String keywords) throws PortalException {
		LinkedHashMap<String, Object> params = new LinkedHashMap<>();

		addAccountMembershipParams(params);

		return partnerEntryLocalService.searchCount(keywords, params);
	}

	public int searchCount(
			String code, int[] statuses, LinkedHashMap<String, Object> params,
			boolean andOperator)
		throws PortalException {

		addAccountMembershipParams(params);

		return partnerEntryLocalService.searchCount(
			code, statuses, params, andOperator);
	}

	public int searchCount(
			String keywords, LinkedHashMap<String, Object> params)
		throws PortalException {

		addAccountMembershipParams(params);

		return partnerEntryLocalService.searchCount(keywords, params);
	}

	protected void addAccountMembershipParams(
			LinkedHashMap<String, Object> params)
		throws PortalException {

		if (!organizationLocalService.hasUserOrganization(
				getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			params.put("accountEntryMembership", Long.valueOf(getUserId()));
		}
	}

}