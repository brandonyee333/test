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

package com.liferay.osb.customer.zendesk.connector.rabbitmq.processors;

import com.liferay.osb.customer.rabbitmq.connector.publisher.MessagePublisher;
import com.liferay.osb.customer.zendesk.connector.util.ZendeskHttp;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringPool;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "routing.key=zendesk.service.tag.delete",
	service = ZendeskTagDeleteMessageProcessor.class
)
public class ZendeskTagDeleteMessageProcessor extends BaseMessageProcessor {

	protected void doProcess(JSONObject jsonObject) throws Exception {
		String id = jsonObject.getString("id");
		String resource = jsonObject.getString("resource");

		String endpoint =
			resource + StringPool.SLASH + id + StringPool.SLASH + "tags.json";

		JSONObject tagsJSONObject = jsonObject.getJSONObject("tagsArray");

		JSONObject responseJSONObject = _zendeskHttp.delete(
			endpoint, tagsJSONObject);

		handleResponseErrors(responseJSONObject);
	}

	@Reference
	private MessagePublisher _messagePublisher;

	@Reference
	private ZendeskHttp _zendeskHttp;

}