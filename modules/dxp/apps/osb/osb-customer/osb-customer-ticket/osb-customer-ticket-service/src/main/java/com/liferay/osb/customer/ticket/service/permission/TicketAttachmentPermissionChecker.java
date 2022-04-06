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

package com.liferay.osb.customer.ticket.service.permission;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.customer.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamWebService;
import com.liferay.osb.customer.ticket.constants.TicketActionKeys;
import com.liferay.osb.customer.ticket.constants.TicketAttachmentConstants;
import com.liferay.osb.customer.ticket.model.TicketAttachment;
import com.liferay.osb.customer.ticket.repository.FileRepository;
import com.liferay.osb.customer.ticket.repository.FileRepositoryManager;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = {})
public class TicketAttachmentPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker,
			TicketAttachment ticketAttachment, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, ticketAttachment, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker,
			TicketAttachment ticketAttachment, String actionId)
		throws PortalException {

		if (actionId.equals(TicketActionKeys.DELETE)) {
			if (permissionChecker.isOmniadmin()) {
				return true;
			}

			if (permissionChecker.getUserId() == ticketAttachment.getUserId()) {
				return true;
			}

			return false;
		}

		if (actionId.equals(TicketActionKeys.UPDATE)) {
			if ((permissionChecker.getUserId() ==
					ticketAttachment.getUserId()) &&
				ticketAttachment.isRegionRestricted()) {

				return true;
			}

			return false;
		}

		if (ticketAttachment.getUserRole() ==
				TicketAttachmentConstants.USER_ROLE_CUSTOMER) {

			return containsCustomerUploaded(
				permissionChecker, ticketAttachment);
		}

		return containsLiferayUploaded(permissionChecker, ticketAttachment);
	}

	protected static boolean containsCustomerUploaded(
			PermissionChecker permissionChecker,
			TicketAttachment ticketAttachment)
		throws PortalException {

		AccountEntry accountEntry = _accountEntryLocalService.getAccountEntry(
			ticketAttachment.getAccountEntryId());

		User user = permissionChecker.getUser();

		try {
			if (!permissionChecker.isOmniadmin() &&
				!_organizationLocalService.hasUserOrganization(
					permissionChecker.getUserId(),
					OSBCustomerConstants.
						ORGANIZATION_DIVISION_SUBSCRIPTION_SERVICES_ID) &&
				!isPartner(
					accountEntry.getKoroneikiAccountKey(), user.getUuid())) {

				return false;
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		if (ticketAttachment.isRegionRestricted()) {
			FileRepository fileRepository =
				_fileRepositoryManager.getFileRepository(
					ticketAttachment.getFileRepositoryId());

			for (Organization organization : user.getOrganizations()) {
				if (ArrayUtil.contains(
						fileRepository.getAccessOrganizationNames(),
						organization.getName())) {

					return true;
				}
			}

			return false;
		}

		return true;
	}

	protected static boolean containsLiferayUploaded(
			PermissionChecker permissionChecker,
			TicketAttachment ticketAttachment)
		throws PortalException {

		if (permissionChecker.isOmniadmin() ||
			_organizationLocalService.hasUserOrganization(
				permissionChecker.getUserId(),
				OSBCustomerConstants.
					ORGANIZATION_DIVISION_SUBSCRIPTION_SERVICES_ID)) {

			return true;
		}

		AccountEntry accountEntry = _accountEntryLocalService.getAccountEntry(
			ticketAttachment.getAccountEntryId());

		User user = permissionChecker.getUser();

		try {
			List<ContactRole> contactRoles =
				_contactRoleWebService.getAccountContactRoles(
					accountEntry.getKoroneikiAccountKey(), user.getUuid(), 1,
					100);

			if (!contactRoles.isEmpty()) {
				return true;
			}

			if (isPartner(
					accountEntry.getKoroneikiAccountKey(), user.getUuid())) {

				return true;
			}
		}
		catch (Exception e) {
			throw new PortalException(e);
		}

		return false;
	}

	protected static boolean isPartner(
			String koroneikiAccountKey, String userUuid)
		throws Exception {

		List<Team> teams = _teamWebService.getAssignedTeams(
			koroneikiAccountKey);

		for (Team team : teams) {
			TeamRole[] teamRoles = team.getTeamRoles();

			if (teamRoles == null) {
				continue;
			}

			for (TeamRole teamRole : teamRoles) {
				String name = teamRole.getName();

				if (name.equals(TeamRoleConstants.NAME_FIRST_LINE_SUPPORT)) {
					List<ContactRole> contactRoles =
						_contactRoleWebService.getTeamContactRoles(
							team.getKey(), userUuid, 1, 100);

					if (!contactRoles.isEmpty()) {
						return true;
					}
				}
			}
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setAccountEntryLocalService(
		AccountEntryLocalService accountEntryLocalService) {

		_accountEntryLocalService = accountEntryLocalService;
	}

	@Reference(unbind = "-")
	protected void setContactRoleWebService(
		ContactRoleWebService contactRoleWebService) {

		_contactRoleWebService = contactRoleWebService;
	}

	@Reference(unbind = "-")
	protected void setFileRepositoryManager(
		FileRepositoryManager fileRepositoryManager) {

		_fileRepositoryManager = fileRepositoryManager;
	}

	@Reference(unbind = "-")
	protected void setOrganizationLocalService(
		OrganizationLocalService organizationLocalService) {

		_organizationLocalService = organizationLocalService;
	}

	@Reference(unbind = "-")
	protected void setTeamWebService(TeamWebService teamWebService) {
		_teamWebService = teamWebService;
	}

	private static AccountEntryLocalService _accountEntryLocalService;
	private static ContactRoleWebService _contactRoleWebService;
	private static FileRepositoryManager _fileRepositoryManager;
	private static OrganizationLocalService _organizationLocalService;
	private static TeamWebService _teamWebService;

}