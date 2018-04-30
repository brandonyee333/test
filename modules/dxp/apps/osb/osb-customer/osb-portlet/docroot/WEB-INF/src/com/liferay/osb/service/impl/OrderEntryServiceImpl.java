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

import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.OrderEntry;
import com.liferay.osb.service.base.OrderEntryServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.auth.PrincipalException;

import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService
public class OrderEntryServiceImpl extends OrderEntryServiceBaseImpl {

	public List<OrderEntry> getOrderEntries(long corpProjectId)
		throws PortalException {

		validateJSONWebServicePermissions();

		AccountEntry accountEntry = accountEntryPersistence.findByCorpProjectId(
			corpProjectId);

		return orderEntryPersistence.findByAccountEntryId(
			accountEntry.getAccountEntryId());
	}

	public OrderEntry getOrderEntry(String uuid) throws PortalException {
		validateJSONWebServicePermissions();

		return orderEntryLocalService.getOrderEntry(uuid);
	}

	protected void validateJSONWebServicePermissions() throws PortalException {
		if (!roleLocalService.hasUserRole(
				getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			throw new PrincipalException();
		}
	}

}