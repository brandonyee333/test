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

package com.liferay.osb.customer.admin.web.internal.portlet.action;

import com.liferay.osb.customer.admin.web.internal.constants.CustomerAdminPortletKeys;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	property = {
		"javax.portlet.name=" + CustomerAdminPortletKeys.ADMIN,
		"mvc.command.name=resyncEntitlement"
	},
	service = MVCActionCommand.class
)
public class ResyncEntitlementMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long organizationId = ParamUtil.getLong(
			actionRequest, "organizationId");

		int usersCount = _userLocalService.getOrganizationUsersCount(
			organizationId, WorkflowConstants.STATUS_ANY);

		Set<Long> userIds = new HashSet<>(usersCount, 1.0F);

		for (int i = 0; i <= ((usersCount / _BATCH_SIZE) + 1); i++) {
			List<User> users = _userLocalService.getOrganizationUsers(
				organizationId, WorkflowConstants.STATUS_ANY, i * _BATCH_SIZE,
				(i * _BATCH_SIZE) + _BATCH_SIZE, null);

			for (User user : users) {
				userIds.add(user.getUserId());
			}
		}

		String filterString = _getFilterString(organizationId);

		long count = _contactWebService.searchCount(filterString);

		for (int i = 1; i <= ((count / _BATCH_SIZE) + 1); i++) {
			List<Contact> contacts = _contactWebService.search(
				filterString, i, _BATCH_SIZE);

			for (Contact contact : contacts) {
				User user = _userIdentityProvider.fetchUserByEmailAddress(
					contact.getEmailAddress());

				if (user == null) {
					continue;
				}

				_userLocalService.addOrganizationUser(
					organizationId, user.getUserId());

				userIds.remove(user.getUserId());
			}
		}

		for (long userId : userIds) {
			_userLocalService.unsetOrganizationUsers(
				organizationId, new long[] {userId});
		}
	}

	private String _getFilterString(long organizationId) throws Exception {
		StringBundler sb = new StringBundler(3);

		sb.append("entitlements/any(s:s eq '");

		Organization organization = _organizationLocalService.getOrganization(
			organizationId);

		String name = organization.getName();

		name = name.replace(
			EntitlementConstants.ORGANIZATION_NAME_PREFIX, StringPool.BLANK);

		sb.append(name);

		sb.append("')");

		return sb.toString();
	}

	private static final int _BATCH_SIZE = 1000;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference(target = "(provider=web)")
	private UserIdentityProvider _userIdentityProvider;

	@Reference
	private UserLocalService _userLocalService;

}