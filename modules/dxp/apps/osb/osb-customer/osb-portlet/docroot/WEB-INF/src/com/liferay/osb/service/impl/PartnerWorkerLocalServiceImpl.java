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

import com.liferay.osb.exception.NoSuchPartnerWorkerException;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.service.base.PartnerWorkerLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Amos Fong
 */
public class PartnerWorkerLocalServiceImpl
	extends PartnerWorkerLocalServiceBaseImpl {

	public void addPartnerWorkers(
			long[] userIds, long partnerEntryId, int[] roles,
			int[] notifications)
		throws PortalException {

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

			partnerWorkerPersistence.update(partnerWorker);
		}

		for (long userId : userIds) {
			assignOrganizations(userId);
		}
	}

	public void deletePartnerWorkers(long userId) throws PortalException {
		try {
			partnerWorkerPersistence.removeByUserId(userId);

			unassignOrganizations(userId);
		}
		catch (NoSuchPartnerWorkerException nspwe) {
		}
	}

	public void deletePartnerWorkers(long[] userIds, long partnerEntryId)
		throws PortalException {

		for (long userId : userIds) {
			try {
				partnerWorkerPersistence.removeByU_PEI(userId, partnerEntryId);

				unassignOrganizations(userId);
			}
			catch (NoSuchPartnerWorkerException nspwe) {
			}
		}
	}

	public PartnerWorker fetchPartnerWorker(long userId, long partnerEntryId) {
		return partnerWorkerPersistence.fetchByU_PEI(userId, partnerEntryId);
	}

	public PartnerWorker getPartnerWorker(long userId, long partnerEntryId)
		throws PortalException {

		return partnerWorkerPersistence.findByU_PEI(userId, partnerEntryId);
	}

	public List<PartnerWorker> getPartnerWorkers(long partnerEntryId) {
		return partnerWorkerPersistence.findByPartnerEntryId(partnerEntryId);
	}

	public List<PartnerWorker> getPartnerWorkers(
		long partnerEntryId, int role) {

		return partnerWorkerPersistence.findByPEI_R(partnerEntryId, role);
	}

	public List<PartnerWorker> getUserPartnerWorkers(long userId) {
		return partnerWorkerPersistence.findByUserId(userId);
	}

	public boolean hasPartnerWorker(long userId) {
		if (partnerWorkerPersistence.countByUserId(userId) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasPartnerWorker(long userId, long partnerEntryId) {
		if (partnerWorkerPersistence.countByU_PEI(userId, partnerEntryId) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasPartnerWorkerRole(long userId, int role) {
		List<PartnerWorker> partnerWorkers = getUserPartnerWorkers(userId);

		for (PartnerWorker partnerWorker : partnerWorkers) {
			if (partnerWorker.getRole() == role) {
				return true;
			}
		}

		return false;
	}

	protected void assignOrganizations(long userId) throws PortalException {
		if (!organizationLocalService.hasUserOrganization(
				userId, OSBConstants.ORGANIZATION_PARTNER_ID) &&
			roleLocalService.hasUserRole(
				userId, OSBConstants.ROLE_VERIFIED_USER_ID)) {

			userLocalService.addOrganizationUsers(
				OSBConstants.ORGANIZATION_PARTNER_ID, new long[] {userId});
		}
	}

	protected void unassignOrganizations(long userId) throws PortalException {
		if (organizationLocalService.hasUserOrganization(
				userId, OSBConstants.ORGANIZATION_PARTNER_ID)) {

			userLocalService.unsetOrganizationUsers(
				OSBConstants.ORGANIZATION_PARTNER_ID, new long[] {userId});
		}
	}

}