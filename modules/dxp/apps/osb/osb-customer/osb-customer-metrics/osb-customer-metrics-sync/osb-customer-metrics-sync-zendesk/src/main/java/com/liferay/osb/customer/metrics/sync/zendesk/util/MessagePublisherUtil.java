/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.zendesk.util;

import com.liferay.osb.customer.zendesk.connector.service.ZendeskRequest;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.publishing.MessagePublisher;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = MessagePublisherUtil.class)
public class MessagePublisherUtil {

	public void sendMetricsMessage(JSONObject jsonObject)
		throws PortalException {

		try {
			_messagePublisher.publish(
				"metrics.update", new Message(jsonObject.toString()));
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	public void sendZendeskMessage(ZendeskRequest zendeskRequest)
		throws PortalException {

		try {
			_messagePublisher.publish(
				"zendesk.service", zendeskRequest.getMessage());
		}
		catch (Exception e) {
			throw new PortalException(e);
		}
	}

	@Reference
	private MessagePublisher _messagePublisher;

}