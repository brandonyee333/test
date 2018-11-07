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
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.remote.web.WebRESTWebServiceUtil;
import com.liferay.osb.service.base.PartnerWorkerLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;

import java.util.List;

/**
 * @author Amos Fong
 * @author Brent Krone-Schmidt
 * @author Rachael Koestartyo
 */
public class PartnerWorkerLocalServiceImpl
	extends PartnerWorkerLocalServiceBaseImpl {

	public void addPartnerWorker(
			long partnerEntryId, String emailAddress, int role,
			int notifications)
		throws PortalException {

		validateDossieraAccountKey(partnerEntryId);

		User user = userLocalService.fetchUserByEmailAddress(
			OSBConstants.COMPANY_ID, emailAddress);

		long partnerWorkerId = counterLocalService.increment();

		PartnerWorker partnerWorker = partnerWorkerPersistence.create(
			partnerWorkerId);

		partnerWorker.setUserId(user.getUserId());
		partnerWorker.setPartnerEntryId(partnerEntryId);
		partnerWorker.setRole(role);
		partnerWorker.setNotifications(notifications);

		partnerWorkerPersistence.update(partnerWorker);

		assignOrganizations(user.getUserId());

		assignCorpEntryOrganizations(user.getUserId(), partnerEntryId);
	}

	@Override
	public PartnerWorker deletePartnerWorker(long partnerWorkerId)
		throws PortalException {

		PartnerWorker partnerWorker =
			partnerWorkerPersistence.fetchByPrimaryKey(partnerWorkerId);

		validateDossieraAccountKey(partnerWorker.getPartnerEntryId());

		unassignOrganizations(partnerWorker.getUserId());

		unassignCorpEntryOrganizations(
			partnerWorker.getUserId(), partnerWorker.getPartnerEntryId());

		return partnerWorkerPersistence.remove(partnerWorkerId);
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

	public void syncPartnerWorkers(
			long partnerEntryId, String oldDossieraAccountKey,
			String newDossieraAccountKey)
		throws PortalException {

		List<PartnerWorker> partnerWorkers = getPartnerWorkers(partnerEntryId);

		for (PartnerWorker partnerWorker : partnerWorkers) {
			unassignCorpEntryOrganizations(
				partnerWorker.getUserId(), oldDossieraAccountKey);

			assignCorpEntryOrganizations(
				partnerWorker.getUserId(), partnerEntryId,
				newDossieraAccountKey);
		}
	}

	public void updatePartnerWorker(
			long partnerEntryId, long partnerWorkerId, int role,
			int notifications)
		throws PortalException {

		validateDossieraAccountKey(partnerEntryId);

		PartnerWorker partnerWorker =
			partnerWorkerPersistence.fetchByPrimaryKey(partnerWorkerId);

		partnerWorker.setRole(role);
		partnerWorker.setNotifications(notifications);

		partnerWorkerPersistence.update(partnerWorker);

		assignOrganizations(partnerWorker.getUserId());

		if (partnerWorker.getRole() != role) {
			assignCorpEntryOrganizations(
				partnerWorker.getUserId(), partnerEntryId);
		}
	}

	protected void assignCorpEntryOrganizations(
			long userId, long partnerEntryId)
		throws PortalException {

		PartnerEntry partnerEntry = partnerEntryLocalService.fetchPartnerEntry(
			partnerEntryId);

		if (partnerEntry == null) {
			return;
		}

		assignCorpEntryOrganizations(
			userId, partnerEntryId, partnerEntry.getDossieraAccountKey());
	}

	protected void assignCorpEntryOrganizations(
			long userId, long partnerEntryId, String dossieraAccountKey)
		throws PortalException {

		Role osbCorpSalesRole = roleLocalService.getRole(
			OSBConstants.ROLE_OSB_CORP_SALES_REPRESENTATIVE_ID);

		User user = userLocalService.getUser(userId);

		WebRESTWebServiceUtil.putCorpEntriesUser(
			dossieraAccountKey, user.getUuid());

		WebRESTWebServiceUtil.putCorpEntriesUserRole(
			dossieraAccountKey, user.getUuid(), osbCorpSalesRole.getUuid());

		PartnerWorker partnerWorker = getPartnerWorker(userId, partnerEntryId);

		long roleId = PartnerWorkerConstants.getCorpEntryRoleId(
			partnerWorker.getRole());

		if (roleId == 0) {
			for (long curRoleId :
					PartnerWorkerConstants.OSB_CORP_ENTRY_ROLE_IDS) {

				Role role = roleLocalService.getRole(curRoleId);

				WebRESTWebServiceUtil.deleteCorpEntriesUserRole(
					dossieraAccountKey, user.getUuid(), role.getUuid());
			}
		}
		else {
			Role role = roleLocalService.getRole(roleId);

			WebRESTWebServiceUtil.putCorpEntriesUserRole(
				dossieraAccountKey, user.getUuid(), role.getUuid());
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

		unassignCorpEntryOrganizations(
			userId, partnerEntry.getDossieraAccountKey());
	}

	protected void unassignCorpEntryOrganizations(
			long userId, String dossieraAccountKey)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		WebRESTWebServiceUtil.deleteCorpEntriesUser(
			dossieraAccountKey, user.getUuid());
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
	}

	protected void validateDossieraAccountKeys(
			List<PartnerWorker> partnerWorkers)
		throws PortalException {

		for (PartnerWorker partnerWorker : partnerWorkers) {
			validateDossieraAccountKey(partnerWorker.getPartnerEntryId());
		}
	}

}