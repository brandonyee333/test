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
import static org.hamcrest.core.IsNull.notNullValue;

import static org.valid4j.matchers.jsonpath.JsonPathMatchers.hasJsonPath;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.pulpo.connector.de.contacts.constants.IndividualChunksDestinationNames;
import com.liferay.pulpo.connector.de.contacts.demo.service.ContactsDemoProcessor;
import com.liferay.pulpo.connector.de.contacts.test.util.ConnectorTestUtil;
import com.liferay.registry.Filter;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceTracker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Cristina González
 */
@RunWith(Arquillian.class)
public class ContactsDemoProcessorTest {

	@BeforeClass
	public static void setUpClass() {
		Registry registry = RegistryUtil.getRegistry();

		StringBundler sb = new StringBundler(3);

		sb.append("(&(objectClass=");
		sb.append(ContactsDemoProcessor.class.getName());
		sb.append("))");

		Filter filter = registry.getFilter(sb.toString());

		_serviceTracker = registry.trackServices(filter);

		_serviceTracker.open();
	}

	@AfterClass
	public static void tearDownClass() {
		_serviceTracker.close();
	}

	@Before
	public void setUp() throws Exception {
		ServiceTestUtil.initPermissions();
	}

	@Test
	public void testCreateUser() throws Exception {
		ContactsDemoProcessor contactsDemoProcessor =
			_serviceTracker.getService();

		Consumer<String> validation = payload -> {
			assertThat(payload, hasJsonPath("firstName", notNullValue()));
		};

		BlockingQueue<String> result =
			ConnectorTestUtil.registerContactMessageListener(
				_DESTINATION, validation);

		contactsDemoProcessor.createUsers(1, TestPropsValues.getCompanyId());

		String message = result.poll(3, TimeUnit.SECONDS);

		Assert.assertNotNull(
			"The " + _DESTINATION +
				" message has not been send to the message bus",
			message);

		Assert.assertEquals(
			"Error in the reception of the " + _DESTINATION +
				"message ",
			"OK", message);
	}

	private static final String _DESTINATION =
		IndividualChunksDestinationNames.ADD + "_" +
			System.getenv(PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME);

	private static ServiceTracker<ContactsDemoProcessor, ContactsDemoProcessor>
		_serviceTracker;

}