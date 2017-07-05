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

import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.service.base.OfferingEntryServiceBaseImpl;
import com.liferay.osb.service.permission.OSBOfferingEntryPermission;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.security.permission.ActionKeys;

import java.util.Iterator;
import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class OfferingEntryServiceImpl extends OfferingEntryServiceBaseImpl {

	public List<OfferingEntry> getAccountEntryOfferingEntries(
			long accountEntryId)
		throws PortalException, SystemException {

		List<OfferingEntry> offeringEntries =
			offeringEntryLocalService.getAccountEntryOfferingEntries(
				accountEntryId);

		return filterOfferingEntries(offeringEntries);
	}

	public List<OfferingEntry> getOrderEntryOfferingEntries(long orderEntryId)
		throws PortalException, SystemException {

		List<OfferingEntry> offeringEntries =
			offeringEntryLocalService.getOrderEntryOfferingEntries(
				orderEntryId);

		return filterOfferingEntries(offeringEntries);
	}

	public OfferingEntry updateOfferingEntry(
			long offeringEntryId, long accountEntryId, long orderEntryId,
			long productEntryId, long supportResponseId,
			String productDescription, int type, int version, boolean licenses,
			long licenseLifetime, long maxConcurrentUsers, long maxUsers,
			boolean supportTickets, long supportLifetime, int sizing,
			int quantity)
		throws PortalException, SystemException {

		OSBOfferingEntryPermission.check(
			getPermissionChecker(), offeringEntryId, ActionKeys.UPDATE);

		return offeringEntryLocalService.updateOfferingEntry(
			getUserId(), offeringEntryId, accountEntryId, orderEntryId,
			productEntryId, supportResponseId, productDescription, type,
			version, licenses, licenseLifetime, maxConcurrentUsers, maxUsers,
			supportTickets, supportLifetime, sizing, quantity);
	}

	public OfferingEntry updateStatus(long offeringEntryId, int status)
		throws PortalException, SystemException {

		OSBOfferingEntryPermission.check(
			getPermissionChecker(), offeringEntryId, ActionKeys.UPDATE);

		return offeringEntryLocalService.updateStatus(
			getUserId(), offeringEntryId, status);
	}

	protected List<OfferingEntry> filterOfferingEntries(
			List<OfferingEntry> offeringEntries)
		throws PortalException, SystemException {

		List<OfferingEntry> filteredOfferingEntries = ListUtil.copy(
			offeringEntries);

		Iterator<OfferingEntry> itr = filteredOfferingEntries.iterator();

		while (itr.hasNext()) {
			OfferingEntry offeringEntry = itr.next();

			if (!OSBOfferingEntryPermission.contains(
					getPermissionChecker(), offeringEntry, ActionKeys.VIEW)) {

				itr.remove();
			}
		}

		return filteredOfferingEntries;
	}

}