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

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.koroneiki.constants.TeamRoleConstants;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ContactRoleWebService;
import com.liferay.osb.customer.koroneiki.web.service.TeamRoleWebService;
import com.liferay.osb.customer.zendesk.constants.ZendeskDestinationNames;
import com.liferay.osb.customer.zendesk.synchronizer.AccountSynchronizer;
import com.liferay.osb.customer.zendesk.synchronizer.TeamSynchronizer;
import com.liferay.osb.customer.zendesk.util.ZendeskMapperUtil;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Account;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Contact;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ContactRole;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Team;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.TeamRole;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ContactSerDes;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.TeamSerDes;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

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
	property = "destination.name=" + ZendeskDestinationNames.TEAM_CONTACT_ROLE_SYNC,
	service = MessageListener.class
)
public class TeamContactRoleMessageListener extends BaseMessageListener {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;

		DestinationConfiguration destinationConfiguration =
			new DestinationConfiguration(
				DestinationConfiguration.DESTINATION_TYPE_PARALLEL,
				ZendeskDestinationNames.TEAM_CONTACT_ROLE_SYNC);

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

		Team team = TeamSerDes.toDTO(jsonObject.getString("team"));
		Contact contact = ContactSerDes.toDTO(jsonObject.getString("contact"));

		String topic = message.getString("topic");

		if (topic.equals("koroneiki.team.contactrole.assigned")) {
			_teamSynchronizer.add(team, contact);
		}
		else if (topic.equals("koroneiki.team.contactrole.unassigned")) {
			List<ContactRole> contactRoles =
				_contactRoleWebService.getTeamContactRoles(
					team.getKey(), contact.getUuid(), 1, 1000);

			if (contactRoles.isEmpty()) {
				reassignTeamTickets(team, contact);

				_teamSynchronizer.remove(team, contact);
			}
		}
	}

	protected void reassignTeamTickets(Team team, Contact contact)
		throws Exception {

		TeamRole teamRole = _teamRoleWebService.fetchTeamRole(
			TeamRole.Type.ACCOUNT.toString(),
			TeamRoleConstants.NAME_FIRST_LINE_SUPPORT);

		if (teamRole == null) {
			return;
		}

		StringBundler sb = new StringBundler(5);

		sb.append("assignedTeamKeyTeamRoleKeys/any(s:s eq '");
		sb.append(team.getKey());
		sb.append(StringPool.UNDERLINE);
		sb.append(teamRole.getKey());
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

			long zendeskOrganizationId =
				_zendeskMapperUtil.fetchZendeskOrganizationId(
					accountEntry.getAccountEntryId());

			if (zendeskOrganizationId <= 0) {
				continue;
			}

			_accountSynchronizer.reassignTickets(
				accountEntry.getAccountEntryId(), team, contact);
		}
	}

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountSynchronizer _accountSynchronizer;

	@Reference
	private AccountWebService _accountWebService;

	private volatile BundleContext _bundleContext;

	@Reference
	private ContactRoleWebService _contactRoleWebService;

	@Reference
	private DestinationFactory _destinationFactory;

	@Reference
	private JSONFactory _jsonFactory;

	private ServiceRegistration<Destination> _serviceRegistration;

	@Reference
	private TeamRoleWebService _teamRoleWebService;

	@Reference
	private TeamSynchronizer _teamSynchronizer;

	@Reference
	private ZendeskMapperUtil _zendeskMapperUtil;

}