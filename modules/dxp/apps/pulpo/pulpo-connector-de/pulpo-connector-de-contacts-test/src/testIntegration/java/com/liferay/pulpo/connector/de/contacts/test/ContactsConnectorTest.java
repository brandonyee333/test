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
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.pulpo.connector.de.contacts.constants.ContactsEntryProviderDestinationNames;

import java.util.concurrent.atomic.AtomicBoolean;

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
		_group = GroupTestUtil.addGroup();

		_user = UserTestUtil.addUser();
	}

	@After
	public void tearDown() throws Exception {

		// Cannot use @DeleteAfterTestRun because LiferayIntegrationTestRule
		// tracks error logs that cannot be excluded with @ExpectedLogs in this
		// case

		GroupLocalServiceUtil.deleteGroup(_group);

		UserLocalServiceUtil.deleteUser(_user);
	}

	@Test
	public void testUpdateUser() throws Exception {
		String emailAddress = "mail@liferay.com";

		AtomicBoolean called = registerContactMessageListener(
			ContactsEntryProviderDestinationNames.UPDATE + "_" +
				_ENVIRONMENT_UNIQUENAME,
			emailAddress);

		_user.setEmailAddress(emailAddress);

		_user = UserLocalServiceUtil.updateUser(_user);

		Assert.assertTrue(
			"The " + ContactsEntryProviderDestinationNames.UPDATE +
				" message has not been sent to the message bus",
			called.get());
	}

	protected static AtomicBoolean registerContactMessageListener(
		final String destinationName, final String emailAddress) {

		final AtomicBoolean called = new AtomicBoolean();

		MessageBusUtil.registerMessageListener(
			destinationName,
			new MessageListener() {

				@Override
				public void receive(Message message) {
					String payload = (String)message.getPayload();

					try {
						String payloadEmailAddress = _getPropertyFromPayload(
							payload, "emailAddress");

						Assert.assertEquals(
							"The contact received has emailAddress " +
								payloadEmailAddress + " but it should have " +
									"been " + emailAddress,
							emailAddress, payloadEmailAddress);

						called.set(true);
					}
					catch (JSONException jsone) {
					}
					finally {
						MessageBusUtil.unregisterMessageListener(
							destinationName, this);
					}
				}

			});

		return called;
	}

	private static String _getPropertyFromPayload(
			String payload, String propertyName)
		throws JSONException {

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			payload);

		JSONArray userPropertiesJSONArray = payloadJSONObject.getJSONArray(
			"userProperties");

		userPropertiesJSONArray = userPropertiesJSONArray.getJSONArray(0);

		String value = StringPool.BLANK;

		for (int i = 0; i < userPropertiesJSONArray.length(); i++) {
			JSONObject jsonObject = userPropertiesJSONArray.getJSONObject(i);

			if (jsonObject.getString("name").contains(propertyName)) {
				value = jsonObject.getString("value");

				break;
			}
		}

		return value;
	}

	private static final String _ENVIRONMENT_UNIQUENAME = System.getenv(
		PULPO_CONTACT_CONNECTOR_ENVIRONMENT_UNIQUENAME);

	private Group _group;
	private User _user;

}