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
import com.liferay.osb.customer.zendesk.model.listener.synchronizer.AccountCustomerSynchronizer;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = ModelListener.class)
public class AccountCustomerModelListener
	extends BaseModelListener<AccountCustomer> {

	@Override
	public void onAfterCreate(AccountCustomer accountCustomer)
		throws ModelListenerException {

		try {
			AccountEntry accountEntry = accountCustomer.getAccountEntry();

			if (accountEntry.isActiveSupport()) {
				_accountCustomerSynchronizer.add(accountCustomer);

				_accountCustomerSynchronizer.addOrganizationSubscription(
					accountCustomer);
			}
			else if (accountCustomer.isClosedWatcher()) {
				_accountCustomerSynchronizer.add(accountCustomer);
			}
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onAfterRemove(AccountCustomer accountCustomer)
		throws ModelListenerException {

		try {
			User user = _userLocalService.fetchUser(
				accountCustomer.getUserId());

			if (user == null) {
				return;
			}

			_accountCustomerSynchronizer.reassignTickets(accountCustomer);

			_accountCustomerSynchronizer.remove(accountCustomer);
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onAfterUpdate(AccountCustomer accountCustomer)
		throws ModelListenerException {

		try {
			if (accountCustomer.isClosedWatcher()) {
				_accountCustomerSynchronizer.add(accountCustomer);
			}
			else {
				_accountCustomerSynchronizer.update(accountCustomer);
			}
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Override
	public void onBeforeUpdate(AccountCustomer accountCustomer)
		throws ModelListenerException {

		try {
			AccountCustomer oldAccountCustomer =
				AccountCustomerLocalServiceUtil.getAccountCustomer(
					accountCustomer.getAccountCustomerId());

			if (oldAccountCustomer.isClosedWatcher() &&
				!accountCustomer.isClosedWatcher()) {

				_accountCustomerSynchronizer.remove(oldAccountCustomer);
			}
		}
		catch (Exception e) {
			_log.error(e);

			throw new ZendeskIntegrationException(e);
		}
	}

	@Reference(
		target = "(module.service.lifecycle=osb.portlet.initialized)",
		unbind = "-"
	)
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		AccountCustomerModelListener.class);

	@Reference
	private AccountCustomerSynchronizer _accountCustomerSynchronizer;

	@Reference
	private UserLocalService _userLocalService;

}