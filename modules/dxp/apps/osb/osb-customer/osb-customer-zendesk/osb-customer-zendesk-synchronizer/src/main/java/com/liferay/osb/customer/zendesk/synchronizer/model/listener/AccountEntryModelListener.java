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

package com.liferay.osb.customer.zendesk.synchronizer.model.listener;

import com.liferay.osb.customer.admin.constants.ExternalIdMapperConstants;
import com.liferay.osb.customer.admin.constants.WorkflowConstants;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.admin.service.ExternalIdMapperLocalService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.customer.zendesk.synchronizer.AccountSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.UserSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.exception.ZendeskIntegrationException;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.DateUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class AccountEntryModelListener extends BaseModelListener<AccountEntry> {

	@Override
	public void onAfterCreate(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			if (!isActiveSupport(accountEntry)) {
				return;
			}

			Account account = _accountWebService.getAccount(
				accountEntry.getKoroneikiAccountKey());

			_accountSynchronizer.update(account, accountEntry);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onAfterUpdate(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			AccountEntry oldAccountEntry = _oldAccountEntry.get();

			if ((!_zendeskOrganization.get() &&
				 !isActiveSupport(accountEntry)) ||
				(accountEntry.getAccountEntryId() ==
					OSBCustomerConstants.ACCOUNT_ENTRY_LRDCOM_ID)) {

				return;
			}

			Account account = _accountWebService.getAccount(
				accountEntry.getKoroneikiAccountKey());

			if (isUpdateAccountEntry(oldAccountEntry, accountEntry)) {
				_accountSynchronizer.update(account, accountEntry);
			}

			if (oldAccountEntry.isActiveTicketSupport() &&
				!accountEntry.isActiveTicketSupport()) {

				_accountSynchronizer.closeZendeskTickets(
					account, accountEntry, null);

				List<Contact> contacts =
					_contactWebService.getAccountCustomerContacts(
						account.getKey(), 1, 1000);

				for (Contact contact : contacts) {
					User user = _userIdentityProvider.fetchUserByEmailAddress(
						contact.getEmailAddress());

					if (user == null) {
						continue;
					}

					_userSynchronizer.updateTags(user);
				}
			}

			if (isActiveSupport(oldAccountEntry) &&
				!isActiveSupport(accountEntry)) {

				_accountSynchronizer.removeCustomers(account, accountEntry);
			}

			if (isActiveSupport(accountEntry) &&
				((oldAccountEntry.isActiveTicketSupport() !=
					accountEntry.isActiveTicketSupport()) ||
				 (isActiveSupport(oldAccountEntry) != isActiveSupport(
					 accountEntry)))) {

				_accountSynchronizer.addCustomers(account, accountEntry);
			}
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onBeforeUpdate(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			AccountEntry oldAccountEntry =
				_accountEntryLocalService.getAccountEntry(
					accountEntry.getAccountEntryId());

			_oldAccountEntry.set(oldAccountEntry);

			_zendeskOrganization.set(hasZendeskOrganization(accountEntry));
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new ZendeskIntegrationException(e);
		}
	}

	protected boolean hasZendeskOrganization(AccountEntry accountEntry)
		throws PortalException {

		long classNameId = _classNameLocalService.getClassNameId(
			AccountEntry.class);

		boolean externalIdMappers =
			_externalIdMapperLocalService.hasExternalIdMappers(
				classNameId, accountEntry.getAccountEntryId(),
				ExternalIdMapperConstants.TYPE_ZENDESK);

		if (externalIdMappers) {
			return true;
		}

		return false;
	}

	protected boolean isActiveSupport(AccountEntry accountEntry) {
		if (accountEntry.getStatus() != WorkflowConstants.STATUS_APPROVED) {
			return false;
		}

		if (!accountEntry.isActiveSupport()) {
			return false;
		}

		return true;
	}

	protected boolean isUpdateAccountEntry(
		AccountEntry oldAccountEntry, AccountEntry accountEntry) {

		if (!DateUtil.equals(
				oldAccountEntry.getLastZendeskAuditDate(),
				accountEntry.getLastZendeskAuditDate())) {

			return false;
		}

		if (isActiveSupport(accountEntry)) {
			return true;
		}

		if (isActiveSupport(oldAccountEntry) &&
			!isActiveSupport(accountEntry)) {

			return true;
		}

		String oldAccountEntryCode = oldAccountEntry.getCode();
		String oldAccountEntryName = oldAccountEntry.getName();

		if (!oldAccountEntryCode.equals(accountEntry.getCode()) ||
			!oldAccountEntryName.equals(accountEntry.getName())) {

			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountEntryModelListener.class);

	private static final ThreadLocal<AccountEntry> _oldAccountEntry =
		new CentralizedThreadLocal<>(
			AccountEntryModelListener.class + "._oldAccountEntry");
	private static final ThreadLocal<Boolean> _zendeskOrganization =
		new CentralizedThreadLocal<>(
			AccountEntryModelListener.class + "._zendeskOrganization");

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountSynchronizer _accountSynchronizer;

	@Reference
	private AccountWebService _accountWebService;

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private ExternalIdMapperLocalService _externalIdMapperLocalService;

	@Reference(target = "(provider=web)")
	private UserIdentityProvider _userIdentityProvider;

	@Reference
	private UserSynchronizer _userSynchronizer;

}