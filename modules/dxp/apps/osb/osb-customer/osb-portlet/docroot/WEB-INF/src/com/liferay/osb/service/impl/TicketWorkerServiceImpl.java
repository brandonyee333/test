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

import com.liferay.osb.exception.RequiredTicketWorkerException;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.service.base.TicketWorkerServiceBaseImpl;
import com.liferay.osb.service.permission.OSBTicketEntryPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.List;

/**
 * @author Amos Fong
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class TicketWorkerServiceImpl extends TicketWorkerServiceBaseImpl {

	public List<TicketWorker> addTicketWorkers(
			long[] userIds, long ticketEntryId, long[] sourceClassNameIds,
			long[] sourceClassPKs, int[] roles, long primaryUserId)
		throws PortalException {

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntryId,
			OSBActionKeys.ASSIGN_WORKERS);

		return ticketWorkerLocalService.addTicketWorkers(
			getUserId(), userIds, ticketEntryId, sourceClassNameIds,
			sourceClassPKs, roles, primaryUserId);
	}

	public void deleteTicketWorkers(
			long[] userIds, long ticketEntryId, long primaryUserId)
		throws PortalException {

		OSBTicketEntryPermission.check(
			getPermissionChecker(), ticketEntryId,
			OSBActionKeys.ASSIGN_WORKERS);

		ticketWorkerLocalService.deleteTicketWorkers(
			getUserId(), userIds, ticketEntryId, primaryUserId);
	}

	public List<TicketWorker> updateTicketWorkers(
			long[] addUserIds, int[] addRoles, long[] removeUserIds,
			long ticketEntryId, long[] sourceClassNameIds,
			long[] sourceClassPKs, long primaryUserId)
		throws PortalException {

		if (addUserIds.length == 0) {
			boolean hasTicketWorkers = false;

			List<TicketWorker> ticketWorkers =
				ticketWorkerLocalService.getTicketWorkers(ticketEntryId);

			for (TicketWorker ticketWorker : ticketWorkers) {
				if (!ArrayUtil.contains(
						removeUserIds, ticketWorker.getUserId())) {

					hasTicketWorkers = true;

					break;
				}
			}

			if (!hasTicketWorkers) {
				throw new RequiredTicketWorkerException();
			}
		}

		if (ArrayUtil.contains(removeUserIds, primaryUserId)) {
			primaryUserId = 0;
		}

		deleteTicketWorkers(removeUserIds, ticketEntryId, primaryUserId);

		return addTicketWorkers(
			addUserIds, ticketEntryId, sourceClassNameIds, sourceClassPKs,
			addRoles, primaryUserId);
	}

}