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

package com.liferay.osb.customer.zendesk.synchronizer.listener.model;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamWebService;
import com.liferay.osb.customer.zendesk.exception.PartnerWorkerRemovalException;
import com.liferay.osb.customer.zendesk.synchronizer.AccountSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.CustomerSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.UserSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.exception.AccountCustomerRemovalException;
import com.liferay.osb.customer.zendesk.synchronizer.exception.ZendeskIntegrationException;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.customer.zendesk.web.service.ZendeskUserWebService;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = ModelListener.class)
public class UserModelListener extends BaseModelListener<User> {

	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			if (associationClassName.equals(Organization.class.getName())) {
				long userId = GetterUtil.getLong(classPK);
				long organizationId = GetterUtil.getLong(associationClassPK);

				if ((organizationId ==
						OSBCustomerConstants.
							ORGANIZATION_LIFERAY_CONTRACTOR_ID) ||
					(organizationId ==
						OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

					User user = _userLocalService.getUser(userId);

					_userSynchronizer.update(user, null);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onAfterRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			if (associationClassName.equals(Organization.class.getName())) {
				long userId = GetterUtil.getLong(classPK);
				long organizationId = GetterUtil.getLong(associationClassPK);

				if ((organizationId ==
						OSBCustomerConstants.
							ORGANIZATION_LIFERAY_CONTRACTOR_ID) ||
					(organizationId ==
						OSBCustomerConstants.ORGANIZATION_LIFERAY_INC_ID)) {

					User user = _userLocalService.getUser(userId);

					_userSynchronizer.updateTags(user);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onAfterUpdate(User user) throws ModelListenerException {
		try {
			User oldUser = _oldUser.get();

			String oldEmailAddress = oldUser.getEmailAddress();

			if (!oldEmailAddress.equals(user.getEmailAddress())) {
				_userSynchronizer.updateEmailAddress(
					user.getUserId(), user.getEmailAddress());
			}

			String oldFirstName = oldUser.getFirstName();
			String oldLastName = oldUser.getLastName();

			if (!oldFirstName.equals(user.getFirstName()) ||
				!oldLastName.equals(user.getLastName())) {

				_userSynchronizer.update(user, null);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onBeforeRemove(User user) {
		try {
			long zendeskUserId = _zendeskMapperUtil.fetchZendeskUserId(
				user.getUserId());

			if (zendeskUserId <= 0) {
				return;
			}

			StringBundler sb = new StringBundler(3);

			sb.append("customerContactEmailAddresses/any(s:s eq '");
			sb.append(user.getEmailAddress());
			sb.append("')");

			List<Account> accounts = _accountWebService.search(
				StringPool.BLANK, sb.toString(), 1, 1000, StringPool.BLANK);

			for (Account account : accounts) {
				AccountEntry accountEntry =
					_accountEntryLocalService.fetchKoroneikiAccountEntry(
						account.getKey());

				if (accountEntry == null) {
					continue;
				}

				try {
					_accountSynchronizer.reassignTickets(
						account.getKey(), accountEntry, user);
				}
				catch (AccountCustomerRemovalException acre) {
					_accountSynchronizer.closeZendeskTickets(
						account, accountEntry, null);
				}
			}

			TeamRole teamRole = _teamRoleWebService.fetchTeamRole(
				TeamRole.Type.ACCOUNT.toString(),
				TeamRoleConstants.NAME_FIRST_LINE_SUPPORT);

			if (teamRole == null) {
				return;
			}

			sb = new StringBundler(5);

			sb.append("contactEmailAddresses/any(s:s eq '");
			sb.append(user.getEmailAddress());
			sb.append("') and accountKeysTeamRoleKeys/any(s:contains(s, '");
			sb.append(teamRole.getKey());
			sb.append("'))");

			List<Team> teams = _teamWebService.search(
				StringPool.BLANK, sb.toString(), 1, 1000, StringPool.BLANK);

			for (Team team : teams) {
				StringBundler assignedAccountsSB = new StringBundler(5);

				assignedAccountsSB.append(
					"assignedTeamKeyTeamRoleKeys/any(s:s eq '");
				assignedAccountsSB.append(team.getKey());
				assignedAccountsSB.append(StringPool.UNDERLINE);
				assignedAccountsSB.append(teamRole.getKey());
				assignedAccountsSB.append("')");

				List<Account> assignedAccounts = _accountWebService.search(
					StringPool.BLANK, assignedAccountsSB.toString(), 1, 1000,
					StringPool.BLANK);

				for (Account account : assignedAccounts) {
					AccountEntry accountEntry =
						_accountEntryLocalService.fetchKoroneikiAccountEntry(
							account.getKey());

					if (accountEntry == null) {
						continue;
					}

					try {
						_accountSynchronizer.reassignTickets(
							accountEntry, team, user);
					}
					catch (PartnerWorkerRemovalException pwre) {
						_accountSynchronizer.closeZendeskTickets(
							account, accountEntry, user);
					}
				}
			}

			_zendeskUserWebService.deleteZendeskUser(zendeskUserId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onBeforeUpdate(User user) throws ModelListenerException {
		try {
			_oldUser.set(_userLocalService.getUser(user.getUserId()));
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		UserModelListener.class);

	private static final ThreadLocal<User> _oldUser =
		new CentralizedThreadLocal<>(UserModelListener.class + "._oldUser");

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountSynchronizer _accountSynchronizer;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private CustomerSynchronizer _customerSynchronizer;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

	@Reference
	private TeamWebService _teamWebService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UserSynchronizer _userSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

	@Reference(target = "(async=true)")
	private ZendeskUserWebService _zendeskUserWebService;

}