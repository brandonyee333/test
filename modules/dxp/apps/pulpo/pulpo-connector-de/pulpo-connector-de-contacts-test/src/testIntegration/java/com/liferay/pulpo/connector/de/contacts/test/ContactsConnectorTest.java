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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.IsEqual.equalTo;

import static org.valid4j.matchers.jsonpath.JsonPathMatchers.hasJsonPath;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.service.ExpandoColumnLocalServiceUtil;
import com.liferay.expando.kernel.service.ExpandoTableLocalServiceUtil;
import com.liferay.portal.kernel.image.ImageBag;
import com.liferay.portal.kernel.image.ImageToolUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Country;
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
import com.liferay.portal.kernel.model.UserGroup;
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
import com.liferay.portal.kernel.service.UserGroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.service.WebsiteLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.OrganizationTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.RoleTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserGroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portlet.expando.util.test.ExpandoTestUtil;
import com.liferay.pulpo.connector.de.constants.ConnectorTransactionOperation;
import com.liferay.pulpo.connector.de.contacts.constants.IndividualChunksDestinationNames;
import com.liferay.pulpo.connector.de.contacts.model.serializer.CustomFieldSerializer;
import com.liferay.pulpo.connector.de.contacts.test.custom.field.CustomFieldExampleSerializer;
import com.liferay.pulpo.connector.de.contacts.test.custom.field.CustomFieldExampleService;
import com.liferay.pulpo.connector.de.contacts.test.custom.field.model.CustomFieldExample;
import com.liferay.pulpo.connector.de.contacts.test.util.ConnectorTestUtil;
import com.liferay.pulpo.connector.de.model.ConnectorTransaction;
import com.liferay.pulpo.connector.de.service.ConnectorTransactionLocalServiceUtil;

import java.awt.image.RenderedImage;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import java.net.URL;

import java.sql.Date;

import java.text.DateFormat;

import java.time.LocalDate;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

/**
 * @author Eduardo Garcia
 */
@RunWith(Arquillian.class)
public class ContactsConnectorTest {

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
		address.setCompanyId(_user.getCompanyId());

		Consumer<String> validation = payload -> {
			assertThat(
				payload,
				hasJsonPath(
					"addresses",
					contains(hasJsonPath("city", equalTo(address.getCity())))));
			assertThat(
				payload,
				hasJsonPath(
					"addresses",
					contains(
						hasJsonPath(
							"country",
							equalTo(address.getCountry().getName())))));
			assertThat(
				payload,
				hasJsonPath(
					"addresses",
					contains(
						hasJsonPath(
							"region",
							equalTo(address.getRegion().getName())))));
			assertThat(
				payload,
				hasJsonPath(
					"addresses",
					contains(
						hasJsonPath(
							"street1", equalTo(address.getStreet1())))));
			assertThat(
				payload,
				hasJsonPath(
					"addresses",
					contains(
						hasJsonPath(
							"street2", equalTo(address.getStreet2())))));
			assertThat(
				payload,
				hasJsonPath(
					"addresses",
					contains(
						hasJsonPath(
							"street3", equalTo(address.getStreet3())))));
			assertThat(
				payload,
				hasJsonPath(
					"addresses",
					contains(hasJsonPath("zip", equalTo(address.getZip())))));
		};

		BlockingQueue<ConnectorTestUtil.Result> resultQueue =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION, validation);

		AddressLocalServiceUtil.addAddress(address);

		_user.setModifiedDate(new java.util.Date());

		UserLocalServiceUtil.updateUser(_user);

		ConnectorTestUtil.Result result = resultQueue.poll(3, TimeUnit.SECONDS);

		Assert.assertNotNull(
			"The " + _DESTINATION +
				" message has not been send to the message bus",
			result);

		Assert.assertEquals(
			"Error in the reception of the " + _DESTINATION +
				"message ",
			"OK", result.getMessage());

		Assert.assertTrue(
			"The registered transaction is not valid",
			_validateTransaction(
				_user, result.getConnectorTransactionId(),
				ConnectorTransactionOperation.ADD));
	}

	@Test
	public void testAddCustomField() throws Exception {
		Bundle bundle = FrameworkUtil.getBundle(ContactsConnectorTest.class);

		BundleContext bundleContext = bundle.getBundleContext();

		List<ServiceReference> serviceReferences = new ArrayList<>();

		try {
			ServiceRegistration<CustomFieldExampleService>
				customFieldExampleServiceRegistration =
					(ServiceRegistration<CustomFieldExampleService>)
						bundleContext.registerService(
							CustomFieldExampleService.class.getCanonicalName(),
							new CustomFieldExampleService(), null);

			serviceReferences.add(
				customFieldExampleServiceRegistration.getReference());

			CustomFieldExampleService customFieldExampleService =
				bundleContext.getService(
					customFieldExampleServiceRegistration.getReference());

			CustomFieldExampleSerializer customFieldExampleSerializer =
				new CustomFieldExampleSerializer();

			customFieldExampleSerializer.setCustomFieldExampleService(
				customFieldExampleService);

			ServiceRegistration<CustomFieldSerializer>
				customFieldSerializerServiceRegistration =
					bundleContext.registerService(
						CustomFieldSerializer.class,
						customFieldExampleSerializer, null);

			serviceReferences.add(
				customFieldSerializerServiceRegistration.getReference());

			String value = RandomTestUtil.randomString();

			Consumer<String> validation = payload -> {
				assertThat(
					payload,
					hasJsonPath(
						"customField", hasJsonPath("field", equalTo(value))));
			};

			BlockingQueue<ConnectorTestUtil.Result> resultQueue =
				ConnectorTestUtil.registerContactMessageListener(
					_DESTINATION, validation);

			CustomFieldExample customFieldExample = new CustomFieldExample();

			customFieldExample.setUserId(_user.getUserId());
			customFieldExample.setValue(value);

			customFieldExampleService.addCustomFieldExample(customFieldExample);

			_user.setModifiedDate(new java.util.Date());

			UserLocalServiceUtil.updateUser(_user);

			ConnectorTestUtil.Result result = resultQueue.poll(
				3, TimeUnit.SECONDS);

			Assert.assertNotNull(
				"The " + _DESTINATION +
					" message has not been send to the message bus",
				result);

			Assert.assertEquals(
				"Error in the reception of the " + _DESTINATION +
					"message ",
				"OK", result.getMessage());

			Assert.assertTrue(
				"The registered transaction is not valid",
				_validateTransaction(
					_user, result.getConnectorTransactionId(),
					ConnectorTransactionOperation.ADD));
		}
		finally {
			serviceReferences.forEach(bundleContext::ungetService);
		}
	}

	@Test
	public void testAddEmailAddress() throws Exception {
		Consumer<String> validation = payload -> {
			assertThat(
				payload,
				hasJsonPath(
					"emailAddresses",
					contains(
						hasJsonPath("address", equalTo("mail@liferay.com")))));
		};

		BlockingQueue<ConnectorTestUtil.Result> resultQueue =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION, validation);

		EmailAddressLocalServiceUtil.addEmailAddress(
			_user.getPrimaryKey(), Contact.class.getName(),
			_user.getContact().getPrimaryKey(), "mail@liferay.com",
			_getListTypeId(ListTypeConstants.CONTACT_EMAIL_ADDRESS), false,
			new ServiceContext());

		_user.setModifiedDate(new java.util.Date());

		UserLocalServiceUtil.updateUser(_user);

		ConnectorTestUtil.Result result = resultQueue.poll(3, TimeUnit.SECONDS);

		Assert.assertNotNull(
			"The " + _DESTINATION +
				" message has not been send to the message bus",
			result);

		Assert.assertEquals(
			"Error in the reception of the " + _DESTINATION +
				"message ",
			"OK", result.getMessage());

		Assert.assertTrue(
			"The registered transaction is not valid",
			_validateTransaction(
				_user, result.getConnectorTransactionId(),
				ConnectorTransactionOperation.ADD));
	}

	@Test
	public void testAddExpando() throws Exception {
		ExpandoTable expandoTable =
			ExpandoTableLocalServiceUtil.addDefaultTable(
				TestPropsValues.getCompanyId(), User.class.getName());

		ExpandoColumn expandoColumn = ExpandoColumnLocalServiceUtil.addColumn(
			expandoTable.getTableId(),
			"ExpandoAttributeName" + RandomTestUtil.randomString(),
			ExpandoColumnConstants.STRING);

		Consumer<String> validation = payload -> {
			assertThat(
				payload,
				hasJsonPath(
					"custom#" + expandoColumn.getName(),
					equalTo("ExpandoAttributeValue")));
		};

		BlockingQueue<ConnectorTestUtil.Result> resultQueue =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION, validation);

		ExpandoTestUtil.addValue(
			expandoTable, expandoColumn, _user.getPrimaryKey(),
			"ExpandoAttributeValue");

		_user.setModifiedDate(new java.util.Date());

		UserLocalServiceUtil.updateUser(_user);

		ConnectorTestUtil.Result result = resultQueue.poll(3, TimeUnit.SECONDS);

		Assert.assertNotNull(
			"The " + _DESTINATION +
				" message has not been send to the message bus",
			result);

		Assert.assertEquals(
			"Error in the reception of the " + _DESTINATION +
				"message ",
			"OK", result.getMessage());

		Assert.assertTrue(
			"The registered transaction is not valid",
			_validateTransaction(
				_user, result.getConnectorTransactionId(),
				ConnectorTransactionOperation.ADD));

		ExpandoColumnLocalServiceUtil.deleteColumn(expandoColumn.getColumnId());

		ExpandoTableLocalServiceUtil.deleteExpandoTable(
			expandoTable.getTableId());
	}

	@Test
	public void testAddGroup() throws Exception {
		Group group = GroupTestUtil.addGroup();

		Consumer<String> validation = payload -> {
			assertThat(
				payload,
				hasJsonPath(
					"groups",
					hasItem(
						hasJsonPath(
							"description", equalTo(group.getDescription())))));
		};

		BlockingQueue<ConnectorTestUtil.Result> resultQueue =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION, validation);

		GroupLocalServiceUtil.addUserGroups(
			_user.getUserId(), Arrays.asList(group));

		_user.setModifiedDate(new java.util.Date());

		UserLocalServiceUtil.updateUser(_user);

		ConnectorTestUtil.Result result = resultQueue.poll(3, TimeUnit.SECONDS);

		Assert.assertNotNull(
			"The " + _DESTINATION +
				" message has not been send to the message bus",
			result);

		Assert.assertEquals(
			"Error in the reception of the " + _DESTINATION +
				"message ",
			"OK", result.getMessage());

		Assert.assertTrue(
			"The registered transaction is not valid",
			_validateTransaction(
				_user, result.getConnectorTransactionId(),
				ConnectorTransactionOperation.ADD));

		GroupLocalServiceUtil.deleteGroup(group);
	}

	@Test
	public void testAddOrganization() throws Exception {
		Organization organization = OrganizationTestUtil.addOrganization(true);

		Consumer<String> validation = payload -> {
			assertThat(
				payload,
				hasJsonPath(
					"organizations",
					hasItem(
						hasJsonPath("name", equalTo(organization.getName())))));
		};

		BlockingQueue<ConnectorTestUtil.Result> resultQueue =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION, validation);

		OrganizationLocalServiceUtil.addUserOrganization(
			_user.getUserId(), organization.getOrganizationId());

		_user.setModifiedDate(new java.util.Date());

		UserLocalServiceUtil.updateUser(_user);

		ConnectorTestUtil.Result result = resultQueue.poll(3, TimeUnit.SECONDS);

		Assert.assertNotNull(
			"The " + _DESTINATION +
				" message has not been send to the message bus",
			result);

		Assert.assertEquals(
			"Error in the reception of the " + _DESTINATION +
				"message ",
			"OK", result.getMessage());

		Assert.assertTrue(
			"The registered transaction is not valid",
			_validateTransaction(
				_user, result.getConnectorTransactionId(),
				ConnectorTransactionOperation.ADD));

		OrganizationLocalServiceUtil.deleteUserOrganization(
			_user.getUserId(), organization.getOrganizationId());

		OrganizationLocalServiceUtil.deleteOrganization(organization);
	}

	@Test
	public void testAddPhone() throws Exception {
		Phone phone = PhoneLocalServiceUtil.createPhone(
			RandomTestUtil.nextLong());

		phone.setNumber("555-55-55-55");
		phone.setClassName(Contact.class.getName());
		phone.setClassPK(_user.getContact().getPrimaryKey());
		phone.setCompanyId(_user.getCompanyId());

		Consumer<String> validation = payload -> {
			assertThat(
				payload,
				hasJsonPath(
					"phones",
					hasItem(
						hasJsonPath("number", equalTo(phone.getNumber())))));
		};

		BlockingQueue<ConnectorTestUtil.Result> resultQueue =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION, validation);

		PhoneLocalServiceUtil.addPhone(phone);

		_user.setModifiedDate(new java.util.Date());

		UserLocalServiceUtil.updateUser(_user);

		ConnectorTestUtil.Result result = resultQueue.poll(3, TimeUnit.SECONDS);

		Assert.assertNotNull(
			"The " + _DESTINATION +
				" message has not been send to the message bus",
			result);

		Assert.assertEquals(
			"Error in the reception of the " + _DESTINATION +
				"message ",
			"OK", result.getMessage());

		Assert.assertTrue(
			"The registered transaction is not valid",
			_validateTransaction(
				_user, result.getConnectorTransactionId(),
				ConnectorTransactionOperation.ADD));
	}

	@Test
	public void testAddRole() throws Exception {
		Role role = RoleTestUtil.addRole(RoleConstants.TYPE_REGULAR);

		Consumer<String> validation = payload -> {
			assertThat(
				payload,
				hasJsonPath(
					"roles",
					hasItem(hasJsonPath("name", equalTo(role.getName())))));
		};

		BlockingQueue<ConnectorTestUtil.Result> resultQueue =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION, validation);

		RoleLocalServiceUtil.addUserRole(_user.getUserId(), role.getRoleId());

		_user.setModifiedDate(new java.util.Date());

		UserLocalServiceUtil.updateUser(_user);

		ConnectorTestUtil.Result result = resultQueue.poll(3, TimeUnit.SECONDS);

		Assert.assertNotNull(
			"The " + _DESTINATION +
				" message has not been send to the message bus",
			result);

		Assert.assertEquals(
			"Error in the reception of the " + _DESTINATION +
				"message ",
			"OK", result.getMessage());

		Assert.assertTrue(
			"The registered transaction is not valid",
			_validateTransaction(
				_user, result.getConnectorTransactionId(),
				ConnectorTransactionOperation.ADD));

		RoleLocalServiceUtil.deleteRole(role);
	}

	@Test
	public void testAddTeam() throws Exception {
		Team team = TeamLocalServiceUtil.addTeam(
			_user.getUserId(), _group.getGroupId(),
			RandomTestUtil.randomString(), "", new ServiceContext());

		Consumer<String> validation = payload -> {
			assertThat(
				payload,
				hasJsonPath(
					"teams",
					hasItem(hasJsonPath("name", equalTo(team.getName())))));
		};

		BlockingQueue<ConnectorTestUtil.Result> resultQueue =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION, validation);

		TeamLocalServiceUtil.addUserTeam(_user.getUserId(), team.getTeamId());

		_user.setModifiedDate(new java.util.Date());

		UserLocalServiceUtil.updateUser(_user);

		ConnectorTestUtil.Result result = resultQueue.poll(3, TimeUnit.SECONDS);

		Assert.assertNotNull(
			"The " + _DESTINATION +
				" message has not been send to the message bus",
			result);

		Assert.assertEquals(
			"Error in the reception of the " + _DESTINATION +
				"message ",
			"OK", result.getMessage());

		Assert.assertTrue(
			"The registered transaction is not valid",
			_validateTransaction(
				_user, result.getConnectorTransactionId(),
				ConnectorTransactionOperation.ADD));
	}

	@Test
	public void testAddUserGroup() throws Exception {
		UserGroup userGroup = UserGroupTestUtil.addUserGroup();

		Consumer<String> validation = payload -> {
			assertThat(
				payload,
				hasJsonPath(
					"userGroups",
					hasItem(
						hasJsonPath(
							"description",
							equalTo(userGroup.getDescription())))));
		};

		BlockingQueue<ConnectorTestUtil.Result> resultQueue =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION, validation);

		UserLocalServiceUtil.addUserGroupUser(
			userGroup.getUserGroupId(), _user.getUserId());

		_user.setModifiedDate(new java.util.Date());

		UserLocalServiceUtil.updateUser(_user);

		ConnectorTestUtil.Result result = resultQueue.poll(3, TimeUnit.SECONDS);

		Assert.assertNotNull(
			"The " + _DESTINATION +
				" message has not been send to the message bus",
			result);

		Assert.assertEquals(
			"Error in the reception of the " + _DESTINATION +
				"message ",
			"OK", result.getMessage());

		Assert.assertTrue(
			"The registered transaction is not valid",
			_validateTransaction(
				_user, result.getConnectorTransactionId(),
				ConnectorTransactionOperation.ADD));

		UserGroupLocalServiceUtil.deleteUserUserGroup(
			_user.getUserId(), userGroup.getUserGroupId());

		UserGroupLocalServiceUtil.deleteUserGroup(userGroup.getUserGroupId());
	}

	@Test
	public void testAddWebsite() throws Exception {
		Website website = WebsiteLocalServiceUtil.createWebsite(
			RandomTestUtil.nextLong());

		website.setUserId(_user.getUserId());
		website.setUrl("http://liferay.com");
		website.setClassName(Contact.class.getName());
		website.setClassPK(_user.getContact().getPrimaryKey());
		website.setCompanyId(_user.getCompanyId());

		Consumer<String> validation = payload -> {
			assertThat(
				payload,
				hasJsonPath(
					"websites",
					hasItem(hasJsonPath("url", equalTo(website.getUrl())))));
		};

		BlockingQueue<ConnectorTestUtil.Result> resultQueue =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION, validation);

		WebsiteLocalServiceUtil.addWebsite(website);

		_user.setModifiedDate(new java.util.Date());

		UserLocalServiceUtil.updateUser(_user);

		ConnectorTestUtil.Result result = resultQueue.poll(3, TimeUnit.SECONDS);

		Assert.assertEquals(
			"The " + _DESTINATION +
				" message has not been sent to the message bus",
			"OK", result.getMessage());

		Assert.assertTrue(
			"The registered transaction is not valid",
			_validateTransaction(
				_user, result.getConnectorTransactionId(),
				ConnectorTransactionOperation.ADD));
	}

	@Test
	public void testDeleteUser() throws Exception {
		User user = UserTestUtil.addUser();

		Consumer<String> validation = payload -> {
			assertThat(
				payload,
				hasJsonPath("emailAddress", equalTo(user.getEmailAddress())));
		};

		BlockingQueue<ConnectorTestUtil.Result> resultQueue =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION_DELETE, validation);

		UserLocalServiceUtil.deleteUser(user);

		ConnectorTestUtil.Result result = resultQueue.poll(3, TimeUnit.SECONDS);

		Assert.assertNotNull(
			"The " + _DESTINATION_DELETE +
				" message has not been send to the message bus",
			result);

		Assert.assertEquals(
			"Error in the reception of the " + _DESTINATION_DELETE +
				"message ",
			"OK", result.getMessage());

		Assert.assertTrue(
			"The registered transaction is not valid",
			_validateTransaction(
				user, result.getConnectorTransactionId(),
				ConnectorTransactionOperation.DELETE));
	}

	@Test
	public void testUpdateContact() throws Exception {
		Contact contact = _user.getContact();
		LocalDate localDate = LocalDate.of(1983, 8, 11);

		contact.setBirthday(
			Date.from(
				localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

		DateFormat dateInstance = ISO8601DateFormat.getDateInstance();

		Consumer<String> validation = payload -> {
			assertThat(
				payload,
				hasJsonPath(
					"contact",
					hasJsonPath(
						"birthday",
						equalTo(dateInstance.format(contact.getBirthday())))));

		};

		BlockingQueue<ConnectorTestUtil.Result> resultQueue =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION, validation);

		ContactLocalServiceUtil.updateContact(contact);

		_user.setModifiedDate(new java.util.Date());

		UserLocalServiceUtil.updateUser(_user);

		ConnectorTestUtil.Result result = resultQueue.poll(3, TimeUnit.SECONDS);

		Assert.assertNotNull(
			"The " + _DESTINATION +
				" message has not been send to the message bus",
			result);

		Assert.assertEquals(
			"Error in the reception of the " + _DESTINATION +
				"message ",
			"OK", result.getMessage());

		Assert.assertTrue(
			"The registered transaction is not valid",
			_validateTransaction(
				_user, result.getConnectorTransactionId(),
				ConnectorTransactionOperation.ADD));
	}

	@Test
	public void testUpdateGroup() throws Exception {
		List<Group> groups = _user.getGroups();

		Group group = groups.get(0);

		group.setDescription("Another group description");

		Consumer<String> validation = payload -> {
			assertThat(
				payload,
				hasJsonPath(
					"groups",
					contains(
						hasJsonPath(
							"description", equalTo(group.getDescription())))));
		};

		BlockingQueue<ConnectorTestUtil.Result> resultQueue =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION, validation);

		GroupLocalServiceUtil.updateGroup(group);

		_user.setModifiedDate(new java.util.Date());

		UserLocalServiceUtil.updateUser(_user);

		ConnectorTestUtil.Result result = resultQueue.poll(3, TimeUnit.SECONDS);

		Assert.assertNotNull(
			"The " + _DESTINATION +
				" message has not been send to the message bus",
			result);

		Assert.assertEquals(
			"Error in the reception of the " + _DESTINATION +
				"message ",
			"OK", result.getMessage());

		Assert.assertTrue(
			"The registered transaction is not valid",
			_validateTransaction(
				_user, result.getConnectorTransactionId(),
				ConnectorTransactionOperation.ADD));
	}

	@Test
	public void testUpdateUserEmailAddress() throws Exception {
		Consumer<String> validation = payload -> {
			assertThat(
				payload,
				hasJsonPath("emailAddress", equalTo("mail@liferay.com")));
		};

		BlockingQueue<ConnectorTestUtil.Result> resultQueue =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION, validation);

		_user.setEmailAddress("mail@liferay.com");

		_user = UserLocalServiceUtil.updateUser(_user);

		ConnectorTestUtil.Result result = resultQueue.poll(3, TimeUnit.SECONDS);

		Assert.assertNotNull(
			"The " + _DESTINATION +
				" message has not been send to the message bus",
			result);

		Assert.assertEquals(
			"Error in the reception of the " + _DESTINATION +
				"message ",
			"OK", result.getMessage());

		Assert.assertTrue(
			"The registered transaction is not valid",
			_validateTransaction(
				_user, result.getConnectorTransactionId(),
				ConnectorTransactionOperation.ADD));
	}

	@Test
	public void testUpdateUserPortrait() throws Exception {
		URL url = new URL(
			"https://randomuser.me/api?seed=pulpo-contacts-connector");

		try (InputStream inputStream = url.openStream()) {
			JSONObject rootJSONObject = JSONFactoryUtil.createJSONObject(
				StringUtil.read(inputStream));

			JSONArray resultsJSONArray = rootJSONObject.getJSONArray("results");

			JSONObject contactJSONObject = resultsJSONArray.getJSONObject(0);

			JSONObject picture = contactJSONObject.getJSONObject("picture");

			String profilePictureURL = picture.getString("large");

			final byte[] bytes = _recoverImageFromUrl(profilePictureURL);

			Consumer<String> validation = payload -> {
				try {
					ImageBag imageBag = ImageToolUtil.read(bytes);

					RenderedImage renderedImage = imageBag.getRenderedImage();

					renderedImage = ImageToolUtil.scale(
						renderedImage, 128, 128);

					byte[] bytesAfterScale = ImageToolUtil.getBytes(
						renderedImage, imageBag.getType());

					final String stringBytesAfterScale =
						Base64.getEncoder().encodeToString(bytesAfterScale);

					assertThat(
						payload,
						hasJsonPath(
							"portrait", equalTo(stringBytesAfterScale)));
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			};

			BlockingQueue<ConnectorTestUtil.Result> resultQueue =
				ConnectorTestUtil.registerContactMessageListener(
					_DESTINATION, validation);

			PortalUtil.updateImageId(
				_user, true, bytes, "portraitId", bytes.length, 128, 128);

			_user.setModifiedDate(new java.util.Date());

			_user = UserLocalServiceUtil.updateUser(_user);

			ConnectorTestUtil.Result result = resultQueue.poll(
				3, TimeUnit.SECONDS);

			Assert.assertNotNull(
				"The " + _DESTINATION +
					" message has not been send to the message bus",
				result);

			Assert.assertEquals(
				"Error in the reception of the " + _DESTINATION +
					"message ",
				"OK", result.getMessage());

			Assert.assertTrue(
				"The registered transaction is not valid",
				_validateTransaction(
					_user, result.getConnectorTransactionId(),
					ConnectorTransactionOperation.ADD));
		}
	}

	private static long _getListTypeId(String type) throws Exception {
		List<ListType> listTypes = ListTypeServiceUtil.getListTypes(type);

		ListType listType = listTypes.get(0);

		return listType.getListTypeId();
	}

	private byte[] _recoverImageFromUrl(String urlText) throws Exception {
		URL url = new URL(urlText);
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		try (InputStream inputStream = url.openStream()) {
			int n = 0;

			byte[] buffer = new byte[ 1024 ];

			while (-1 != (n = inputStream.read(buffer))) {
				output.write(buffer, 0, n);
			}
		}

		return output.toByteArray();
	}

	private boolean _validateTransaction(
		User user, long connectorTransactionId, String operation) {

		ConnectorTransaction connectorTransaction =
			ConnectorTransactionLocalServiceUtil.fetchConnectorTransaction(
				connectorTransactionId);

		if (connectorTransaction == null) {
			return false;
		}

		if (!user.getModelClassName().equals(
				connectorTransaction.getClassName())) {

			return false;
		}

		if (user.getUserId() != connectorTransaction.getClassPK()) {
			return false;
		}

		if (!operation.equals(connectorTransaction.getOperation())) {
			return false;
		}

		return true;
	}

	private static final String _DESTINATION =
		IndividualChunksDestinationNames.ADD + "_" +
			System.getenv(PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME);

	private static final String _DESTINATION_DELETE =
		IndividualChunksDestinationNames.DELETE + "_" +
			System.getenv(PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME);

	private Group _group;
	private User _user;

}