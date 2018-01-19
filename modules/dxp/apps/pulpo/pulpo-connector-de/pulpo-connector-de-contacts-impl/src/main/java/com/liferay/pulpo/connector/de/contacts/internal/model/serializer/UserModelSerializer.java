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
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.CustomFieldStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.EmailAddressModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.ExpandoBridgeStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.GroupModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.OrganizationModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.PhoneModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.RoleModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.TeamModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.UserGroupModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.UserJsonWrapperStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.UserModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.internal.model.serializer.std.WebsiteModelStdSerializer;
import com.liferay.pulpo.connector.de.contacts.model.serializer.CustomFieldSerializer;
import com.liferay.pulpo.connector.de.contacts.model.serializer.Serializer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristina González
 */
@Component(
	immediate = true,
	property = {"className=com.liferay.portal.kernel.model.User"},
	service = Serializer.class
)
public class UserModelSerializer implements Serializer<User> {

	@Override
	public String writeAsString(User user) {
		try {
			return _getObjectMapper().writeValueAsString(
				new UserJsonWrapper(user, _getCustomFields(user)));
		}
		catch (JsonProcessingException jpe) {
			throw new RuntimeException(jpe);
		}
	}

	private List<Object> _getCustomFields(User user) {
		List<CustomFieldSerializer> customFieldSerializers =
			_customFieldSerializerRegistry.getCustomFieldSerializers();

		Stream<CustomFieldSerializer> customFieldSerializerStream =
			customFieldSerializers.stream();

		return customFieldSerializerStream.filter(
			entry -> entry.getCustomField(user) != null
		).map(
			entry -> entry.getCustomField(user)
		).collect(
			Collectors.toList()
		);
	}

	private ObjectMapper _getObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();

		SimpleModule module = new SimpleModule();

		module.addSerializer(
			UserJsonWrapper.class, new UserJsonWrapperStdSerializer());
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

		List<CustomFieldSerializer> customFieldSerializers =
			_customFieldSerializerRegistry.getCustomFieldSerializers();

		for (CustomFieldSerializer customFieldSerializer :
				customFieldSerializers) {

			module.addSerializer(
				customFieldSerializer.getCustomFieldClass(),
				new CustomFieldStdSerializer<>(customFieldSerializer));
		}

		mapper.registerModule(module);

		return mapper;
	}

	@Reference
	private CustomFieldSerializerRegistry _customFieldSerializerRegistry;

	@Reference
	private UserLocalService _userLocalService;

}