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

import com.liferay.osb.customer.zendesk.synchronizer.constants.ZendeskDestinationNames;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.AccountSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactRoleSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactSerDes;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
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
 * @author Kyle Bischof
 */
@Component(
	immediate = true,
	property = "destination.name=" + ZendeskDestinationNames.ACCOUNT_CONTACT_SYNC,
	service = MessageListener.class
)
public class ContactMessageListener extends BaseMessageListener {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
				"liferay/zendesk_account_contact_sync");

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

		Account account = AccountSerDes.toDTO(jsonObject.getString("account"));
		Contact contact = ContactSerDes.toDTO(jsonObject.getString("contact"));
		ContactRole contactRole = ContactRoleSerDes.toDTO(
			jsonObject.getString("contactRole"));

		String topic = message.getString("topic");

		if (topic.equals("koroeniki.account.contact.assigned")) {
			onContactAssign();
		}
		else if (topic.equals("koroneiki.account.contact.unassigned")) {
			onContactUnassign();
		}
		else if (topic.equals("koroneiki.account.contactrole.assigned")) {
			onContactRoleAssign();
		}
		else {
			onContactRoleUnassign();
		}
	}

	protected void onContactAssign() {

		// TODO

	}

	protected void onContactRoleAssign() {

		// TODO

	}

	protected void onContactRoleUnassign() {

		// TODO

	}

	protected void onContactUnassign() {

		// TODO

	}

	private volatile BundleContext _bundleContext;

	@Reference
	private DestinationFactory _destinationFactory;

	@Reference
	private JSONFactory _jsonFactory;

	private ServiceRegistration<Destination> _serviceRegistration;

}