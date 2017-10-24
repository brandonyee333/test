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

package com.liferay.pulpo.connector.de.contacts.test.util;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.messaging.MessageListener;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Assert;

/**
 * @author Cristina González
 */
public class ConnectorTestUtil {

	public static AtomicBoolean registerContactMessageListener(
		final String destinationName, String propertyName,
		final Map<String, String> exceptedProperties) {

		final AtomicBoolean called = new AtomicBoolean();

		MessageBusUtil.registerMessageListener(
			destinationName,
			new MessageListener() {

				@Override
				public void receive(Message message) {
					String payload = (String)message.getPayload();

					try {
						Map<String, String> properties =
							_getPropertyMapFromPayload(payload, propertyName);

						exceptedProperties.forEach(
							(k, v) -> {
								Assert.assertEquals(
									"The contact received has " + k + " " +
										properties.get(k) +
											" but it should have been " + v,
									v, properties.get(k));
							});

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

	private static Map<String, String> _getPropertyMapFromPayload(
			String payload, String propertyName)
		throws JSONException {

		Map<String, String> properties = new HashMap<>();

		JSONObject payloadJSONObject = JSONFactoryUtil.createJSONObject(
			payload);

		JSONArray userPropertiesJSONArray = payloadJSONObject.getJSONArray(
			propertyName);

		if (userPropertiesJSONArray.getJSONArray(0) != null) {
			userPropertiesJSONArray = userPropertiesJSONArray.getJSONArray(0);
		}

		for (int i = 0; i < userPropertiesJSONArray.length(); i++) {
			JSONObject jsonObject = userPropertiesJSONArray.getJSONObject(i);

			properties.put(
				jsonObject.getString("name"), jsonObject.getString("value"));
		}

		return properties;
	}

}