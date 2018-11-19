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
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

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
			if (!hasActiveSupportOffering(accountEntry)) {
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
				!hasActiveSupportOffering(accountEntry)) {

				return;
			}

			_accountEntrySynchronizer.add(accountEntry);

			_accountEntrySynchronizer.addAccountCustomers(accountEntry);

			if (accountEntry.isPartnerManagedSupport()) {
				_accountEntrySynchronizer.addPartnerManagedSupport(
					accountEntry);
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
			if (!hasZendeskOrganization(accountEntry) &&
				!hasActiveSupportOffering(accountEntry)) {

				return;
			}

			AccountEntry oldAccountEntry =
				AccountEntryLocalServiceUtil.getAccountEntry(
					accountEntry.getAccountEntryId());

			if (oldAccountEntry.getStatus() != accountEntry.getStatus()) {
				if (accountEntry.getStatus() !=
						WorkflowConstants.STATUS_APPROVED) {

					_accountEntrySynchronizer.updateAccountCustomers(
						accountEntry);
				}
			}

			if (oldAccountEntry.isPartnerManagedSupport() &&
				!accountEntry.isPartnerManagedSupport()) {

				_accountEntrySynchronizer.removePartnerManagedSupport(
					oldAccountEntry);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	protected boolean hasActiveSupportOffering(AccountEntry accountEntry) {
		if ((accountEntry.getStatus() ==
				WorkflowConstants.STATUS_APPROVED) &&
			accountEntry.hasActiveSupportOffering()) {

			return true;
		}

		return false;
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

	@Reference
	private AccountEntrySynchronizer _accountEntrySynchronizer;

}