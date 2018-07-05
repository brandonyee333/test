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
import com.liferay.osb.exception.PartnerEntryDossieraAccountKeyException;
import com.liferay.osb.model.PartnerEntry;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.remote.web.WebRESTWebServiceUtil;
import com.liferay.osb.service.base.PartnerWorkerLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amos Fong
 * @author Brent Krone-Schmidt
 * @author Rachael Koestartyo
 */
public class PartnerWorkerLocalServiceImpl
	extends PartnerWorkerLocalServiceBaseImpl {

	public void addPartnerWorkers(
			long[] userIds, long partnerEntryId, int[] roles,
			int[] notifications)
		throws PortalException {

		validateDossieraAccountKey(partnerEntryId);

		List<Long> newUserIds = new ArrayList<>();

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

				newUserIds.add(userId);
			}

			partnerWorker.setRole(roles[i]);
			partnerWorker.setNotifications(notifications[i]);

			partnerWorkerPersistence.update(partnerWorker);
		}

		for (long userId : userIds) {
			assignOrganizations(userId);
		}

		assignCorpEntryOrganizations(newUserIds, partnerEntryId);
	}

	public void deletePartnerWorkers(long userId) throws PortalException {
		try {
			List<PartnerWorker> partnerWorkers =
				partnerWorkerPersistence.findByUserId(userId);

			validateDossieraAccountKeys(partnerWorkers);

			for (PartnerWorker partnerWorker : partnerWorkers) {
				unassignCorpEntryOrganizations(
					userId, partnerWorker.getPartnerEntryId());
			}

			partnerWorkerPersistence.removeByUserId(userId);

			unassignOrganizations(userId);
		}
		catch (NoSuchPartnerWorkerException nspwe) {
		}
	}

	public void deletePartnerWorkers(long[] userIds, long partnerEntryId)
		throws PortalException {

		validateDossieraAccountKey(partnerEntryId);

		for (long userId : userIds) {
			try {
				partnerWorkerPersistence.removeByU_PEI(userId, partnerEntryId);

				unassignCorpEntryOrganizations(userId, partnerEntryId);

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

	protected void assignCorpEntryOrganizations(
			List<Long> userIds, long partnerEntryId)
		throws PortalException {

		PartnerEntry partnerEntry = partnerEntryLocalService.fetchPartnerEntry(
			partnerEntryId);

		if (partnerEntry == null) {
			return;
		}

		Role role = roleLocalService.getRole(
			OSBConstants.ROLE_OSB_CORP_SALES_REPRESENTATIVE_ID);

		for (long userId : userIds) {
			User user = userLocalService.getUser(userId);

			WebRESTWebServiceUtil.putCorpEntriesUser(
				partnerEntry.getDossieraAccountKey(), user.getUuid());

			WebRESTWebServiceUtil.putCorpEntriesUserRole(
				partnerEntry.getDossieraAccountKey(), user.getUuid(),
				role.getUuid());
		}
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

	protected void unassignCorpEntryOrganizations(
			long userId, long partnerEntryId)
		throws PortalException {

		PartnerEntry partnerEntry = partnerEntryLocalService.fetchPartnerEntry(
			partnerEntryId);

		if (partnerEntry == null) {
			return;
		}

		User user = userLocalService.getUser(userId);

		WebRESTWebServiceUtil.deleteCorpEntriesUser(
			partnerEntry.getDossieraAccountKey(), user.getUuid());
	}

	protected void unassignOrganizations(long userId) throws PortalException {
		if (organizationLocalService.hasUserOrganization(
				userId, OSBConstants.ORGANIZATION_PARTNER_ID)) {

			userLocalService.unsetOrganizationUsers(
				OSBConstants.ORGANIZATION_PARTNER_ID, new long[] {userId});
		}
	}

	protected void validateDossieraAccountKey(long partnerEntryId)
		throws PortalException {

		PartnerEntry partnerEntry = partnerEntryLocalService.fetchPartnerEntry(
			partnerEntryId);

		if (partnerEntry == null) {
			throw new PartnerEntryDossieraAccountKeyException();
		}

		CorpEntry corpEntry = corpEntryLocalService.fetchCorpEntry(
			partnerEntry.getDossieraAccountKey());

		if (corpEntry == null) {
			throw new PartnerEntryDossieraAccountKeyException();
		}

		Organization organization = organizationLocalService.fetchOrganization(
			corpEntry.getOrganizationId());

		if (organization == null) {
			throw new PartnerEntryDossieraAccountKeyException();
		}
	}

	protected void validateDossieraAccountKeys(
			List<PartnerWorker> partnerWorkers)
		throws PortalException {

		for (PartnerWorker partnerWorker : partnerWorkers) {
			validateDossieraAccountKey(partnerWorker.getPartnerEntryId());
		}
	}

}