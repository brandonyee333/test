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

package com.liferay.osb.customer.zendesk.listeners;

import com.liferay.osb.customer.zendesk.listeners.synchronizer.AccountEntrySynchronizer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.ExternalIdMapperConstants;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.ExternalIdMapperLocalServiceUtil;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;

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

			_accountEntrySynchronizer.add(accountEntry);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterUpdate(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			if (!hasZendeskOrganization(accountEntry) &&
				!accountEntry.getActiveSupport()) {

				return;
			}

			if (_accountEntryActiveTicketSupport.get() &&
				!accountEntry.getActiveTicketSupport()) {

				_accountEntrySynchronizer.closeZendeskTickets(accountEntry);

				_accountEntrySynchronizer.removeObsoleteTags(accountEntry);
			}

			if (_accountEntryActiveSupport.get() &&
				!accountEntry.getActiveSupport()) {

				_accountEntrySynchronizer.removeAccountCustomers(accountEntry);

				_accountEntrySynchronizer.removeObsoleteTags(accountEntry);
			}

			if (accountEntry.getActiveSupport()) {
				_accountEntrySynchronizer.add(accountEntry);

				if ((_accountEntryActiveTicketSupport.get() !=
						accountEntry.getActiveTicketSupport()) ||
					(_accountEntryActiveSupport.get() !=
						accountEntry.getActiveSupport())) {

					_accountEntrySynchronizer.addAccountCustomers(accountEntry);
				}
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onBeforeUpdate(AccountEntry accountEntry)
		throws ModelListenerException {

		try {
			AccountEntry oldAccountEntry =
				AccountEntryLocalServiceUtil.getAccountEntry(
					accountEntry.getAccountEntryId());

			_accountEntryActiveSupport.set(oldAccountEntry.getActiveSupport());
			_accountEntryActiveTicketSupport.set(
				oldAccountEntry.getActiveTicketSupport());

			if (!hasZendeskOrganization(accountEntry) &&
				!accountEntry.getActiveTicketSupport()) {

				return;
			}

			if (accountEntry.getActiveTicketSupport()) {
				if (accountEntry.isPartnerManagedSupport()) {
					if (!oldAccountEntry.isPartnerManagedSupport() ||
						(oldAccountEntry.getPartnerEntryId() !=
							accountEntry.getPartnerEntryId())) {

						_accountEntrySynchronizer.addPartnerManagedSupport(
							accountEntry);
					}
				}
			}

			if ((oldAccountEntry.isPartnerManagedSupport() &&
				 !accountEntry.isPartnerManagedSupport()) ||
				(oldAccountEntry.getPartnerEntryId() !=
					accountEntry.getPartnerEntryId())) {

				_accountEntrySynchronizer.removePartnerManagedSupport(
					oldAccountEntry);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
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

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private static final ThreadLocal<Boolean> _accountEntryActiveSupport =
		new CentralizedThreadLocal<>(
			AccountEntryModelListener.class + "._accountEntryActiveSupport");
	private static final ThreadLocal<Boolean> _accountEntryActiveTicketSupport =
		new CentralizedThreadLocal<>(
			AccountEntryModelListener.class +
				"._accountEntryActiveTicketSupport");

	@Reference
	private AccountEntrySynchronizer _accountEntrySynchronizer;

}