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

package com.liferay.osb.customer.zendesk.synchronizer.listener.messaging;

import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.koroneiki.constants.ContactRoleConstants;
import com.liferay.osb.customer.koroneiki.constants.EntitlementConstants;
import com.liferay.osb.customer.koroneiki.web.service.ContactWebService;
import com.liferay.osb.customer.zendesk.constants.ZendeskDestinationNames;
import com.liferay.osb.customer.zendesk.synchronizer.UserSynchronizer;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Entitlement;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.EntitlementSerDes;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.Dictionary;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	immediate = true,
	property = "destination.name=" + ZendeskDestinationNames.ACCOUNT_ENTITLEMENT_SYNC,
	service = MessageListener.class
)
public class EntitlementMessageListener extends BaseMessageListener {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
				ZendeskDestinationNames.ACCOUNT_ENTITLEMENT_SYNC);

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
		JSONObject jsonObject = _jsonFactory.createJSONObject(
			(String)message.getPayload());

		JSONObject accountJSONObject = jsonObject.getJSONObject("account");

		if (accountJSONObject == null) {
			return;
		}

		Entitlement entitlement = EntitlementSerDes.toDTO(
			jsonObject.getString("entitlement"));

		String name = entitlement.getName();

		if (!name.equals(EntitlementConstants.NAME_PARTNER)) {
			return;
		}

		Account account = AccountSerDes.toDTO(accountJSONObject.toString());

		List<Contact> contacts = _contactWebService.getAccountCustomerContacts(
			account.getKey(), 1, 1000);

		for (Contact contact : contacts) {
			User user = _userIdentityProvider.fetchUserByEmailAddress(
				contact.getEmailAddress());

			if (user == null) {
				continue;
			}

			ContactRole[] contactRoles = contact.getContactRoles();

			for (ContactRole contactRole : contactRoles) {
				if (ArrayUtil.contains(
						ContactRoleConstants.PARTNER_CONTACT_ROLES,
						contactRole.getName())) {

					_userSynchronizer.update(user, null);

					break;
				}
			}
		}
	}

	private volatile BundleContext _bundleContext;

	@Reference
	private ContactWebService _contactWebService;

	@Reference
	private DestinationFactory _destinationFactory;

	@Reference
	private JSONFactory _jsonFactory;

	private ServiceRegistration<Destination> _serviceRegistration;

	@Reference(target = "(provider=web)")
	private UserIdentityProvider _userIdentityProvider;

	@Reference
	private UserSynchronizer _userSynchronizer;

}