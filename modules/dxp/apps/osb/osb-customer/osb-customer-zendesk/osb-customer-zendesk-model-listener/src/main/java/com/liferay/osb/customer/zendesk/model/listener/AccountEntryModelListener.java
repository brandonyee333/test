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

package com.liferay.osb.customer.zendesk.model.listener;

import com.liferay.osb.customer.zendesk.model.listener.exception.ZendeskIntegrationException;
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.AccountEntrySynchronizer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;

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
			if (!accountEntry.getActiveSupport()) {
				return;
			}

			_accountEntrySynchronizer.update(accountEntry);
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onAfterUpdate(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			AccountEntry oldAccountEntry = _oldAccountEntry.get();

			if (!_zendeskOrganization.get() &&
				!accountEntry.isActiveSupport()) {

				return;
			}

			if (isUpdateAccountEntry(oldAccountEntry, accountEntry)) {
				_accountEntrySynchronizer.update(accountEntry);
			}

			if (_zendeskOrganization.get() &&
				((oldAccountEntry.isPartnerManagedSupport() &&
				  !accountEntry.isPartnerManagedSupport()) ||
				 (oldAccountEntry.getPartnerEntryId() !=
					 accountEntry.getPartnerEntryId()))) {

				_accountEntrySynchronizer.removePartnerManagedSupport(
					oldAccountEntry);
			}

			if (oldAccountEntry.isActiveTicketSupport() &&
				!accountEntry.isActiveTicketSupport()) {

				_accountEntrySynchronizer.closeZendeskTickets(accountEntry);

				_accountEntrySynchronizer.removeObsoleteTags(accountEntry);

				_accountEntrySynchronizer.removePartnerManagedSupport(
					oldAccountEntry);
			}

			if (oldAccountEntry.isActiveSupport() &&
				!accountEntry.isActiveSupport()) {

				_accountEntrySynchronizer.removeAccountCustomers(accountEntry);
			}

			if (accountEntry.isActiveSupport()) {
				if ((oldAccountEntry.isActiveTicketSupport() !=
						accountEntry.isActiveTicketSupport()) ||
					(oldAccountEntry.isActiveSupport() !=
						accountEntry.isActiveSupport())) {

					_accountEntrySynchronizer.addAccountCustomers(accountEntry);
				}

				if (accountEntry.isActiveTicketSupport() &&
					accountEntry.isPartnerManagedSupport()) {

					if (!_zendeskOrganization.get() ||
						!oldAccountEntry.isActiveTicketSupport() ||
						!oldAccountEntry.isPartnerManagedSupport() ||
						(oldAccountEntry.getPartnerEntryId() !=
							accountEntry.getPartnerEntryId())) {

						_accountEntrySynchronizer.addPartnerManagedSupport(
							accountEntry);
					}
				}
			}
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onBeforeRemove(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			_accountEntrySynchronizer.remove(accountEntry);
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onBeforeUpdate(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			AccountEntry oldAccountEntry =
				AccountEntryLocalServiceUtil.getAccountEntry(
					accountEntry.getAccountEntryId());

			_oldAccountEntry.set(oldAccountEntry);

			_zendeskOrganization.set(hasZendeskOrganization(accountEntry));
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	protected boolean hasZendeskOrganization(AccountEntry accountEntry)
		throws PortalException {

		long classNameId = ClassNameLocalServiceUtil.getClassNameId(
			AccountEntry.class);

		boolean externalIdMappers =
			ExternalIdMapperLocalServiceUtil.hasExternalIdMappers(
				classNameId, accountEntry.getAccountEntryId(),
				ExternalIdMapperConstants.TYPE_ZENDESK);

		if (externalIdMappers) {
			return true;
		}
		else {
			return false;
		}
	}

	protected boolean isUpdateAccountEntry(
		AccountEntry oldAccountEntry, AccountEntry accountEntry) {

		if (!DateUtil.equals(
				oldAccountEntry.getLastZendeskAuditDate(),
				accountEntry.getLastZendeskAuditDate())) {

			return false;
		}

		if (accountEntry.isActiveSupport()) {
			return true;
		}

		if (oldAccountEntry.isActiveSupport() &&
			!accountEntry.isActiveSupport()) {

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

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
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
	private AccountEntrySynchronizer _accountEntrySynchronizer;

}