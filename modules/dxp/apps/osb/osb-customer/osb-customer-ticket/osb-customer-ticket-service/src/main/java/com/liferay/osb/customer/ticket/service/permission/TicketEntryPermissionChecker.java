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
import com.liferay.osb.customer.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.customer.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.customer.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.OrganizationLocalService;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = {})
public class TicketEntryPermissionChecker {

	public static void check(
			PermissionChecker permissionChecker, long accountEntryId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, accountEntryId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, actionId);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long accountEntryId,
			String actionId)
		throws PortalException {

		if (_organizationLocalService.hasUserOrganization(
				permissionChecker.getUserId(),
				OSBCustomerConstants.ORGANIZATION_LIFERAY_CONTRACTOR_ID) ||
			_organizationLocalService.hasUserOrganization(
				permissionChecker.getUserId(),
				OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return true;
		}

		AccountEntry accountEntry = _accountEntryLocalService.getAccountEntry(
			accountEntryId);

		User user = permissionChecker.getUser();

		try {
			List<ContactRole> contactRoles =
				_contactRoleWebService.getAccountContactRoles(
					accountEntry.getKoroneikiAccountKey(), user.getUuid(), 1,
					100);

			for (ContactRole contactRole : contactRoles) {
				String name = contactRole.getName();

				if (name.equals(ContactRoleConstants.NAME_SUPPORT_DEVELOPER)) {
					return true;
				}
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
	private static OrganizationLocalService _organizationLocalService;
	private static TeamWebService _teamWebService;

}