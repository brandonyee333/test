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

import com.liferay.osb.exception.NoSuchPartnerWorkerException;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.service.base.PartnerWorkerLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;

import java.util.List;

/**
 * @author Amos Fong
 */
public class PartnerWorkerLocalServiceImpl
	extends PartnerWorkerLocalServiceBaseImpl {

	public void addPartnerWorkers(
			long[] userIds, long partnerEntryId, int[] roles,
			int[] notifications)
		throws PortalException, SystemException {

		for (int i = 0; i < userIds.length; i++) {
			long userId = userIds[i];

			PartnerWorker partnerWorker = partnerWorkerPersistence.fetchByU_PEI(
				userId, partnerEntryId);

			if (partnerWorker == null) {
				long partnerWorkerId = counterLocalService.increment();

				partnerWorker = partnerWorkerPersistence.create(
					partnerWorkerId);

				partnerWorker.setUserId(userId);
				partnerWorker.setPartnerEntryId(partnerEntryId);
			}

			partnerWorker.setRole(roles[i]);
			partnerWorker.setNotifications(notifications[i]);

			partnerWorkerPersistence.update(partnerWorker, false);
		}

		for (long userId : userIds) {
			assignOrganizations(userId);
		}
	}

	public void deletePartnerWorkers(long userId)
		throws PortalException, SystemException {

		try {
			partnerWorkerPersistence.removeByUserId(userId);

			unassignOrganizations(userId);
		}
		catch (NoSuchPartnerWorkerException nstwe) {
		}
	}

	public void deletePartnerWorkers(long[] userIds, long partnerEntryId)
		throws PortalException, SystemException {

		for (long userId : userIds) {
			try {
				partnerWorkerPersistence.removeByU_PEI(userId, partnerEntryId);

				unassignOrganizations(userId);
			}
			catch (NoSuchPartnerWorkerException nstwe) {
			}
		}
	}

	public PartnerWorker fetchPartnerWorker(long userId, long partnerEntryId)
		throws SystemException {

		return partnerWorkerPersistence.fetchByU_PEI(userId, partnerEntryId);
	}

	public PartnerWorker getPartnerWorker(long userId, long partnerEntryId)
		throws PortalException, SystemException {

		return partnerWorkerPersistence.findByU_PEI(userId, partnerEntryId);
	}

	public List<PartnerWorker> getPartnerWorkers(long partnerEntryId)
		throws SystemException {

		return partnerWorkerPersistence.findByPartnerEntryId(partnerEntryId);
	}

	public List<PartnerWorker> getPartnerWorkers(long partnerEntryId, int role)
		throws SystemException {

		return partnerWorkerPersistence.findByPEI_R(partnerEntryId, role);
	}

	public List<PartnerWorker> getUserPartnerWorkers(long userId)
		throws SystemException {

		return partnerWorkerPersistence.findByUserId(userId);
	}

	public boolean hasPartnerWorker(long userId) throws SystemException {
		if (partnerWorkerPersistence.countByUserId(userId) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasPartnerWorker(long userId, long partnerEntryId)
		throws SystemException {

		if (partnerWorkerPersistence.countByU_PEI(userId, partnerEntryId) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasPartnerWorkerRole(long userId, int role)
		throws SystemException {

		List<PartnerWorker> partnerWorkers = getUserPartnerWorkers(userId);

		for (PartnerWorker partnerWorker : partnerWorkers) {
			if (partnerWorker.getRole() == role) {
				return true;
			}
		}

		return false;
	}

	protected void assignOrganizations(long userId)
		throws PortalException, SystemException {

		if (!organizationLocalService.hasUserOrganization(
				userId, OSBConstants.ORGANIZATION_PARTNER_ID) &&
			RoleLocalServiceUtil.hasUserRole(
				userId, OSBConstants.ROLE_VERIFIED_USER_ID)) {

			userLocalService.addOrganizationUsers(
				OSBConstants.ORGANIZATION_PARTNER_ID, new long[] {userId});
		}
	}

	protected void unassignOrganizations(long userId)
		throws PortalException, SystemException {

		if (organizationLocalService.hasUserOrganization(
				userId, OSBConstants.ORGANIZATION_PARTNER_ID)) {

			userLocalService.unsetOrganizationUsers(
				OSBConstants.ORGANIZATION_PARTNER_ID, new long[] {userId});
		}
	}

}