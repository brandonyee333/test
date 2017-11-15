/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.pulpo.connector.de.contacts.internal.model.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.model.Website;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.AddressModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.ContactModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.EmailAddressModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.ExpandoBridgeStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.GroupModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.OrganizationModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.PhoneModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.RoleModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.TeamModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.UserGroupModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.UserModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.WebsiteModelStdSerializer;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristina González
 */
@Component(
	property = {"className=com.liferay.portal.kernel.model.User"},
	service = Serializer.class
)
public class UserModelSerializer implements Serializer<User> {

	@Override
	public String writeAsString(User user) {
		ObjectMapper mapper = new ObjectMapper();

		SimpleModule module = new SimpleModule();

		module.addSerializer(User.class, new UserModelStdSerializer());
		module.addSerializer(Address.class, new AddressModelStdSerializer());
		module.addSerializer(Contact.class, new ContactModelStdSerializer());
		module.addSerializer(
			EmailAddress.class, new EmailAddressModelStdSerializer());
		module.addSerializer(Group.class, new GroupModelStdSerializer());
		module.addSerializer(
			Organization.class, new OrganizationModelStdSerializer());
		module.addSerializer(Phone.class, new PhoneModelStdSerializer());
		module.addSerializer(Team.class, new TeamModelStdSerializer());
		module.addSerializer(Role.class, new RoleModelStdSerializer());
		module.addSerializer(
			UserGroup.class, new UserGroupModelStdSerializer());
		module.addSerializer(Website.class, new WebsiteModelStdSerializer());

		module.addSerializer(
			ExpandoBridge.class, new ExpandoBridgeStdSerializer());

		mapper.registerModule(module);

		try {
			return mapper.writeValueAsString(user);
		}
		catch (JsonProcessingException jpe) {
			throw new RuntimeException(jpe);
		}
	}

	@Reference
	private UserLocalService _userLocalService;

}