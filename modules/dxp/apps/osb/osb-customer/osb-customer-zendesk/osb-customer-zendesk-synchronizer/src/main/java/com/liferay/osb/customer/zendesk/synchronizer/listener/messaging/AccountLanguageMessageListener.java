/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.synchronizer.listener.messaging;

import com.liferay.osb.customer.admin.constants.WorkflowConstants;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.zendesk.constants.ZendeskDestinationNames;
import com.liferay.osb.customer.zendesk.synchronizer.AccountSynchronizer;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Yuanyuan Huang
 */
@Component(
	immediate = true,
	property = "destination.name=" + ZendeskDestinationNames.ACCOUNT_LANGUAGE_SYNC,
	service = MessageListener.class
)
public class AccountLanguageMessageListener extends BaseMessageListener {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
				ZendeskDestinationNames.ACCOUNT_LANGUAGE_SYNC);

		Destination destination = _destinationFactory.createDestination(
			destinationConfiguration);

		Dictionary<String, Object> dictionary = new HashMapDictionary<>();

		dictionary.put("destination.name", destination.getName());

		_serviceRegistration = _bundleContext.registerService(
			Destination.class, destination, dictionary);
	}

	@Deactivate
	protected void deactivate() {
		if (_serviceRegistration != null) {
			Destination destination = _bundleContext.getService(
				_serviceRegistration.getReference());

			_serviceRegistration.unregister();

			destination.destroy();
		}

		_bundleContext = null;
	}

	@Override
	protected void doReceive(Message message) throws Exception {
		String koroneikiAccountKey = (String)message.get("koroneikiAccountKey");

		AccountEntry accountEntry =
			_accountEntryLocalService.fetchKoroneikiAccountEntry(
				koroneikiAccountKey);

		if ((accountEntry != null) && isActiveTicketSupport(accountEntry)) {
			Account account = _accountWebService.fetchAccount(
				koroneikiAccountKey);

			_accountSynchronizer.update(account, accountEntry);
		}
	}

	protected boolean isActiveTicketSupport(AccountEntry accountEntry) {
		if (accountEntry.getStatus() != WorkflowConstants.STATUS_APPROVED) {
			return false;
		}

		if (!accountEntry.isActiveTicketSupport()) {
			return false;
		}

		return true;
	}

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountSynchronizer _accountSynchronizer;

	@Reference
	private AccountWebService _accountWebService;

	private volatile BundleContext _bundleContext;

	@Reference
	private DestinationFactory _destinationFactory;

	private ServiceRegistration<Destination> _serviceRegistration;

}