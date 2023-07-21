/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.distributed.messaging.subscriber.google.pubsub;

import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
public abstract class BaseMessageSubscriber implements MessageSubscriber {

	@Override
	public void receive(Message message) {
		try {
			try {
				JSONObject jsonObject = jsonFactory.createJSONObject(
					(String)message.getPayload());

				doReceive(jsonObject);
			}
			catch (JSONException jsonException) {
				JSONArray jsonArray = jsonFactory.createJSONArray(
					(String)message.getPayload());

				for (int i = 0; i < jsonArray.length(); i++) {
					doReceive(jsonArray.getJSONObject(i));
				}
			}
		}
		catch (Exception e) {
			_log.error(message);

			_log.error(e, e);
		}
	}

	protected abstract void doReceive(JSONObject jsonObject) throws Exception;

	@Reference
	protected JSONFactory jsonFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageSubscriber.class);

}