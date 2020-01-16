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

package com.liferay.osb.customer.koroneiki.subscriber;

import com.liferay.osb.customer.admin.constants.WorkflowConstants;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.ExternalLink;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Kyle Bischof
 */
public abstract class BaseMessageSubscriber implements MessageSubscriber {

	public void receive(Message message) {
		try {
			doReceive(message);
		}
		catch (Exception e) {
			_log.error(message);

			_log.error(e, e);
		}
	}

	protected abstract void doReceive(Message message) throws Exception;

	protected String getDossieraAccountKey(ExternalLink[] externalLinks) {
		for (ExternalLink externalLink : externalLinks) {
			String domain = externalLink.getDomain();

			if (domain.equals("dossiera")) {
				String entityName = externalLink.getEntityName();

				if (entityName.equals("account")) {
					return externalLink.getEntityId();
				}
			}
		}

		return StringPool.BLANK;
	}

	protected int getStatus(String label) {
		String statusLabel = StringUtil.toLowerCase(
			StringUtil.replace(label, CharPool.SPACE, CharPool.DASH));

		return WorkflowConstants.getLabelStatus(statusLabel);
	}

	protected void sendMessage(
		String destination, String topic, String payload) {

		com.liferay.portal.kernel.messaging.Message message =
			new com.liferay.portal.kernel.messaging.Message();

		message.put("topic", topic);
		message.setPayload(payload);

		MessageBusUtil.sendMessage(destination, message);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageSubscriber.class);

}