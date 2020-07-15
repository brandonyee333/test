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

/**
 * @author Amos Fong
 * @author Brent Krone-Schmidt
 * @author Rachael Koestartyo
 */
public class PartnerWorkerLocalServiceImpl {

	/*TODO

	public PartnerWorker addPartnerWorker(
			long partnerEntryId, long userId, int role)
		throws PortalException {

		partnerEntryLocalService.getPartnerEntry(partnerEntryId);

		User user = userLocalService.getUser(userId);

		PartnerWorker partnerWorker = partnerWorkerPersistence.fetchByU_PEI(
			userId, partnerEntryId);

		if (partnerWorker != null) {
			return updatePartnerWorker(
				partnerWorker.getPartnerWorkerId(), role);
		}

		long partnerWorkerId = counterLocalService.increment();

		partnerWorker = partnerWorkerPersistence.create(partnerWorkerId);

		partnerWorker.setUserId(user.getUserId());
		partnerWorker.setPartnerEntryId(partnerEntryId);
		partnerWorker.setRole(role);

		partnerWorker = partnerWorkerPersistence.update(partnerWorker);

		assignOrganizations(user.getUserId());

		assignCorpEntryOrganizations(user.getUserId(), partnerEntryId);

		return partnerWorker;
	}

	public PartnerWorker addPartnerWorker(
			long partnerEntryId, String emailAddress, int role)
		throws PortalException {

		User user = userLocalService.fetchUserByEmailAddress(
			OSBConstants.COMPANY_ID, emailAddress);

		if (user != null) {
			return addPartnerWorker(partnerEntryId, user.getUserId(), role);
		}

		User remoteUser = remoteUserLocalService.fetchUserByEmailAddress(
			emailAddress);

		if (remoteUser == null) {
			throw new NoSuchUserException();
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCreateDate(remoteUser.getCreateDate());
		serviceContext.setUuid(remoteUser.getUuid());

		user = userLocalService.addUser(
			OSBConstants.USER_DEFAULT_USER_ID, OSBConstants.COMPANY_ID, true,
			StringPool.BLANK, StringPool.BLANK, false,
			remoteUser.getScreenName(), remoteUser.getEmailAddress(), 0,
			StringPool.BLANK, remoteUser.getLocale(), remoteUser.getFirstName(),
			remoteUser.getMiddleName(), remoteUser.getLastName(), 0, 0, false,
			0, 1, 1970, StringPool.BLANK, new long[0],
			remoteUser.getOrganizationIds(), remoteUser.getRoleIds(),
			new long[0], false, serviceContext);

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		ExpandoBridge remoteExpandoBridge = remoteUser.getExpandoBridge();

		expandoBridge.setAttributes(remoteExpandoBridge.getAttributes(), false);

		return addPartnerWorker(partnerEntryId, user.getUserId(), role);
	}

	@Override
	public PartnerWorker deletePartnerWorker(long partnerWorkerId)
		throws PortalException {

		PartnerWorker partnerWorker = partnerWorkerPersistence.findByPrimaryKey(
			partnerWorkerId);

		unassignOrganizations(partnerWorker.getUserId());

		unassignCorpEntryOrganizations(
			partnerWorker.getUserId(), partnerWorker.getPartnerEntryId());

		return deletePartnerWorker(partnerWorker);
	}

	public void deletePartnerWorkers(long userId) throws PortalException {
		try {
			List<PartnerWorker> partnerWorkers =
				partnerWorkerPersistence.findByUserId(userId);

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

	public PartnerWorker updatePartnerWorker(long partnerWorkerId, int role)
		throws PortalException {

		PartnerWorker partnerWorker = partnerWorkerPersistence.findByPrimaryKey(
			partnerWorkerId);

		partnerWorker.setRole(role);

		partnerWorker = partnerWorkerPersistence.update(partnerWorker);

		assignOrganizations(partnerWorker.getUserId());

		if (partnerWorker.getRole() != role) {
			assignCorpEntryOrganizations(
				partnerWorker.getUserId(), partnerWorker.getPartnerEntryId());
		}

		return partnerWorker;
	}

	protected void assignCorpEntryOrganizations(
			long userId, long partnerEntryId)
		throws PortalException {

		PartnerEntry partnerEntry = partnerEntryLocalService.fetchPartnerEntry(
			partnerEntryId);

		if ((partnerEntry == null) ||
			(partnerEntry.getParentPartnerEntryId() > 0)) {

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

			remoteUserLocalService.addOrganizationUsers(
				OSBConstants.ORGANIZATION_PARTNER_ID, new long[] {userId});
		}
	}

	protected void unassignCorpEntryOrganizations(
			long userId, long partnerEntryId)
		throws PortalException {

		PartnerEntry partnerEntry = partnerEntryLocalService.fetchPartnerEntry(
			partnerEntryId);

		if ((partnerEntry == null) ||
			(partnerEntry.getParentPartnerEntryId() > 0)) {

			return;
		}

		unassignCorpEntryOrganizations(
			userId, partnerEntry.getDossieraAccountKey());
	}

	protected void unassignCorpEntryOrganizations(
			long userId, String dossieraAccountKey)
		throws PortalException {

		User user = userLocalService.fetchUser(userId);

		if (user != null) {
			WebRESTWebServiceUtil.deleteCorpEntriesUser(
				dossieraAccountKey, user.getUuid());
		}
	}

	protected void unassignOrganizations(long userId) throws PortalException {
		if (organizationLocalService.hasUserOrganization(
				userId, OSBConstants.ORGANIZATION_PARTNER_ID)) {

			remoteUserLocalService.unsetOrganizationUsers(
				OSBConstants.ORGANIZATION_PARTNER_ID, new long[] {userId});
		}
	}

	*/

}