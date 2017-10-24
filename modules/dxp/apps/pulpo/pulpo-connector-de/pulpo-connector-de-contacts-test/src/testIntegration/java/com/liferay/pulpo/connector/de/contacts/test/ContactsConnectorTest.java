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

package com.liferay.pulpo.connector.de.contacts.test;

import static com.liferay.pulpo.connector.de.contacts.internal.ContactsConnectorImpl.PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.EmailAddress;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.Team;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.Website;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;
import com.liferay.portal.kernel.service.ContactLocalServiceUtil;
import com.liferay.portal.kernel.service.CountryServiceUtil;
import com.liferay.portal.kernel.service.EmailAddressLocalServiceUtil;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ListTypeServiceUtil;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.PhoneLocalServiceUtil;
import com.liferay.portal.kernel.service.RegionServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.TeamLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WebsiteLocalServiceUtil;
import com.liferay.portal.kernel.service.persistence.EmailAddressUtil;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.OrganizationTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portlet.expando.util.test.ExpandoTestUtil;
import com.liferay.pulpo.connector.de.contacts.constants.ContactsEntryProviderDestinationNames;
import com.liferay.pulpo.connector.de.contacts.test.util.ConnectorTestUtil;

import java.sql.Date;

import java.time.LocalDate;
import java.time.ZoneId;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eduardo Garcia
 */
@RunWith(Arquillian.class)
@Sync
public class ContactsConnectorTest {

	@ClassRule
	@Rule
	public static final SynchronousDestinationTestRule
		synchronousDestinationTestRule =
			SynchronousDestinationTestRule.INSTANCE;

	@Before
	public void setUp() throws Exception {
		ServiceTestUtil.setUser(TestPropsValues.getUser());

		_user = UserTestUtil.addUser();

		_group = GroupTestUtil.addGroup();
	}

	@After
	public void tearDown() throws Exception {

		// Cannot use @DeleteAfterTestRun because LiferayIntegrationTestRule
		// tracks error logs that cannot be excluded with @ExpectedLogs in this
		// case

		if (_user != null) {
			UserLocalServiceUtil.deleteUser(_user);
		}

		if (_group != null) {
			GroupLocalServiceUtil.deleteGroup(_group);
		}
	}

	@Test
	public void testAddAddress() throws Exception {
		Address address = AddressLocalServiceUtil.createAddress(
			RandomTestUtil.nextLong());

		Country netherlands = CountryServiceUtil.getCountryByName(
			"netherlands");

		address.setCountryId(netherlands.getCountryId());

		Region nh = RegionServiceUtil.fetchRegion(
			netherlands.getCountryId(), "NH");

		address.setRegionId(nh.getRegionId());

		address.setCity("Amsterdam");
		address.setStreet1("Damrak 243");
		address.setZip("1012ZJ");

		address.setUserId(_user.getPrimaryKey());

		address.setClassName(Contact.class.getName());
		address.setClassPK(_user.getContact().getPrimaryKey());
		address.setTypeId(_getListTypeId(ListTypeConstants.CONTACT_ADDRESS));

		Map<String, String> exceptedProperties = Collections.unmodifiableMap(
			Stream.of(
				new SimpleEntry<>("Address#city", address.getCity()),
				new SimpleEntry<>(
					"Address#countryId",
					String.valueOf(address.getCountry().getCountryId())),
				new SimpleEntry<>(
					"Address#regionId",
					String.valueOf(address.getRegion().getRegionId())),
				new SimpleEntry<>(
					"Address#street1", String.valueOf(address.getStreet1())),
				new SimpleEntry<>("Address#zip", address.getZip())
			).collect(
				Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)
			)
		);

		AtomicBoolean called = ConnectorTestUtil.registerContactMessageListener(
			_DESTINATION_PROPERTIES, "properties", exceptedProperties);

		AddressLocalServiceUtil.addAddress(address);

		Assert.assertTrue(
			"The " + _DESTINATION_PROPERTIES +
				" message has not been sent to the message bus",
			called.get());
	}

	@Test
	public void testAddEmailAddress() throws Exception {
		EmailAddress emailAddress = EmailAddressUtil.create(
			RandomTestUtil.nextLong());

		emailAddress.setAddress("mail@liferay.com");

		Map<String, String> exceptedProperties = Collections.unmodifiableMap(
			Stream.of(
				new SimpleEntry<>(
					"EmailAddress#address", emailAddress.getAddress())
			).collect(
				Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)
			)
		);

		AtomicBoolean called = ConnectorTestUtil.registerContactMessageListener(
			_DESTINATION_PROPERTIES, "properties", exceptedProperties);

		EmailAddressLocalServiceUtil.addEmailAddress(
			_user.getPrimaryKey(), Contact.class.getName(),
			_user.getContact().getPrimaryKey(), "mail@liferay.com",
			_getListTypeId(ListTypeConstants.CONTACT_EMAIL_ADDRESS), false,
			new ServiceContext());

		Assert.assertTrue(
			"The " + _DESTINATION_PROPERTIES +
				" message has not been sent to the message bus",
			called.get());
	}

	@Test
	public void testAddExpando() throws Exception {
		ExpandoTable table = ExpandoTestUtil.addTable(
			PortalUtil.getClassNameId(User.class),
			"table" + RandomTestUtil.randomString());

		ExpandoColumn column = ExpandoTestUtil.addColumn(
			table, "name " + RandomTestUtil.randomString(),
			ExpandoColumnConstants.STRING);

		String expandoValue = "expandoExample";

		Map<String, String> exceptedProperties = Collections.unmodifiableMap(
			Stream.of(
				new SimpleEntry<>(column.getName(), expandoValue)
			).collect(
				Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)
			)
		);

		AtomicBoolean called = ConnectorTestUtil.registerContactMessageListener(
			_DESTINATION, "userProperties", exceptedProperties);

		ExpandoTestUtil.addValue(
			table, column, _user.getPrimaryKey(), expandoValue);

		Assert.assertTrue(
			"The " + _DESTINATION +
				" message has not been sent to the message bus",
			called.get());
	}

	@Test
	public void testAddGroup() throws Exception {
		Group group = GroupTestUtil.addGroup();

		Map<String, String> exceptedProperties = Collections.unmodifiableMap(
			Stream.of(
				new SimpleEntry<>("Group#description", group.getDescription())
			).collect(
				Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)
			)
		);

		AtomicBoolean called = ConnectorTestUtil.registerContactMessageListener(
			_DESTINATION, "userProperties", exceptedProperties);

		GroupLocalServiceUtil.addUserGroup(
			_user.getUserId(), group.getGroupId());

		Assert.assertTrue(
			"The " + _DESTINATION +
				" message has not been sent to the message bus",
			called.get());

		GroupLocalServiceUtil.deleteGroup(group);
	}

	@Test
	public void testAddOrganization() throws Exception {
		Organization organization = OrganizationTestUtil.addOrganization(true);

		Map<String, String> exceptedProperties = Collections.unmodifiableMap(
			Stream.of(
				new SimpleEntry<>("Organization#name", organization.getName())
			).collect(
				Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)
			)
		);

		AtomicBoolean called = ConnectorTestUtil.registerContactMessageListener(
			_DESTINATION, "userProperties", exceptedProperties);

		OrganizationLocalServiceUtil.addUserOrganization(
			_user.getUserId(), organization.getOrganizationId());

		Assert.assertTrue(
			"The " + _DESTINATION_PROPERTIES +
				" message has not been sent to the message bus",
			called.get());

		OrganizationLocalServiceUtil.deleteUserOrganization(
			_user.getUserId(), organization.getOrganizationId());

		OrganizationLocalServiceUtil.deleteOrganization(organization);
	}

	@Test
	public void testAddPhone() throws Exception {
		Phone phone = PhoneLocalServiceUtil.createPhone(
			RandomTestUtil.nextLong());

		phone.setNumber("555-55-55-55");
		phone.setUserId(_user.getUserId());

		Map<String, String> exceptedProperties = Collections.unmodifiableMap(
			Stream.of(
				new SimpleEntry<>("Phone#number", phone.getNumber())
			).collect(
				Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)
			)
		);

		AtomicBoolean called = ConnectorTestUtil.registerContactMessageListener(
			_DESTINATION_PROPERTIES, "properties", exceptedProperties);

		PhoneLocalServiceUtil.addPhone(phone);

		Assert.assertTrue(
			"The " + _DESTINATION_PROPERTIES +
				" message has not been sent to the message bus",
			called.get());
	}

	@Test
	public void testAddRole() throws Exception {
		Role role = RoleTestUtil.addRole(RoleConstants.TYPE_REGULAR);

		Map<String, String> exceptedProperties = Collections.unmodifiableMap(
			Stream.of(
				new SimpleEntry<>("Role#name", role.getName())
			).collect(
				Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)
			)
		);

		AtomicBoolean called = ConnectorTestUtil.registerContactMessageListener(
			_DESTINATION, "userProperties", exceptedProperties);

		RoleLocalServiceUtil.addUserRole(_user.getUserId(), role.getRoleId());

		Assert.assertTrue(
			"The " + _DESTINATION +
				" message has not been sent to the message bus",
			called.get());

		RoleLocalServiceUtil.deleteRole(role);
	}

	@Test
	public void testAddTeam() throws Exception {
		Team userTeam = TeamLocalServiceUtil.addTeam(
			_user.getUserId(), _group.getGroupId(),
			RandomTestUtil.randomString(), "", new ServiceContext());

		Map<String, String> exceptedProperties = Collections.unmodifiableMap(
			Stream.of(
				new SimpleEntry<>(
					"Team#teamId", String.valueOf(userTeam.getTeamId()))
			).collect(
				Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)
			)
		);

		AtomicBoolean called = ConnectorTestUtil.registerContactMessageListener(
			_DESTINATION, "userProperties", exceptedProperties);

		TeamLocalServiceUtil.addUserTeam(
			_user.getUserId(), userTeam.getTeamId());

		Assert.assertTrue(
			"The " + _DESTINATION +
				" message has not been sent to the message bus",
			called.get());
	}

	@Test
	public void testAddWebsite() throws Exception {
		Website website = WebsiteLocalServiceUtil.createWebsite(
			RandomTestUtil.nextLong());

		website.setUserId(_user.getUserId());
		website.setUrl("http://liferay.com");
		website.setClassName(Contact.class.getName());
		website.setClassPK(_user.getContact().getPrimaryKey());

		Map<String, String> exceptedProperties = Collections.unmodifiableMap(
			Stream.of(
				new SimpleEntry<>("Website#url", website.getUrl())
			).collect(
				Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)
			)
		);

		AtomicBoolean called = ConnectorTestUtil.registerContactMessageListener(
			_DESTINATION_PROPERTIES, "properties", exceptedProperties);

		WebsiteLocalServiceUtil.addWebsite(website);

		Assert.assertTrue(
			"The " + _DESTINATION_PROPERTIES +
				" message has not been sent to the message bus",
			called.get());
	}

	@Test
	public void testUpdateContact() throws Exception {
		Contact contact = _user.getContact();
		LocalDate localDate = LocalDate.of(1983, 8, 11);

		contact.setBirthday(
			Date.from(
				localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		Map<String, String> exceptedProperties = Collections.unmodifiableMap(
			Stream.of(
				new SimpleEntry<>(
					"Contact#birthday", String.valueOf(contact.getBirthday()))
			).collect(
				Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)
			)
		);

		AtomicBoolean called = ConnectorTestUtil.registerContactMessageListener(
			_DESTINATION_PROPERTIES, "properties", exceptedProperties);

		ContactLocalServiceUtil.updateContact(contact);

		Assert.assertTrue(
			"The " + _DESTINATION_PROPERTIES +
				" message has not been sent to the message bus",
			called.get());
	}

	@Test
	public void testUpdateGroup() throws Exception {
		Group group = _user.getGroup();

		group.setDescription("Another group description");

		Map<String, String> exceptedProperties = Collections.unmodifiableMap(
			Stream.of(
				new SimpleEntry<>("Group#description", group.getDescription())
			).collect(
				Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)
			)
		);

		AtomicBoolean called = ConnectorTestUtil.registerContactMessageListener(
			_DESTINATION_PROPERTIES, "properties", exceptedProperties);

		GroupLocalServiceUtil.updateGroup(group);

		Assert.assertTrue(
			"The " + _DESTINATION_PROPERTIES +
				" message has not been sent to the message bus",
			called.get());
	}

	@Test
	public void testUpdateUser() throws Exception {
		String emailAddress = "mail@liferay.com";

		Map<String, String> exceptedProperties = Collections.unmodifiableMap(
			Stream.of(
				new SimpleEntry<>("User#emailAddress", emailAddress)
			).collect(
				Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)
			)
		);

		AtomicBoolean called = ConnectorTestUtil.registerContactMessageListener(
			_DESTINATION, "userProperties", exceptedProperties);

		_user.setEmailAddress(emailAddress);

		_user = UserLocalServiceUtil.updateUser(_user);

		Assert.assertTrue(
			"The " + ContactsEntryProviderDestinationNames.UPDATE +
				" message has not been sent to the message bus",
			called.get());
	}

	private static long _getListTypeId(String type) throws Exception {
		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(type);

		ListType listType = listTypes.get(0);

		return listType.getListTypeId();
	}

	private static final String _DESTINATION =
		ContactsEntryProviderDestinationNames.UPDATE + "_" +
			System.getenv(PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME);

	private static final String _DESTINATION_PROPERTIES =
		ContactsEntryProviderDestinationNames.UPDATE + "_properties_" +
			System.getenv(PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME);

	private Group _group;
	private User _user;

}