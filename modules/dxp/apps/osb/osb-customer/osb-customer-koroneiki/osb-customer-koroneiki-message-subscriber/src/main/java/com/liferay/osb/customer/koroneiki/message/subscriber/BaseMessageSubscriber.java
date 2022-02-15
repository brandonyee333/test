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

package com.liferay.osb.customer.koroneiki.message.subscriber;

import com.liferay.osb.customer.admin.service.AccountEntryLocalService;
import com.liferay.osb.customer.identity.management.provider.UserIdentityProvider;
import com.liferay.osb.customer.koroneiki.util.AccountReader;
import com.liferay.osb.customer.koroneiki.web.service.AccountWebService;
import com.liferay.osb.customer.koroneiki.web.service.ProductPurchaseWebService;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.service.component.annotations.Reference;

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

	protected void sendMessage(
		String destination, String topic, String payload) {

		com.liferay.portal.kernel.messaging.Message message =
			new com.liferay.portal.kernel.messaging.Message();

		message.put("topic", topic);
		message.setPayload(payload);

		MessageBusUtil.sendMessage(destination, message);
	}

	@Reference
	protected AccountEntryLocalService accountEntryLocalService;

	@Reference
	protected AccountReader accountReader;

	@Reference
	protected AccountWebService accountWebService;

	@Reference
	protected JSONFactory jsonFactory;

	@Reference
	protected OrganizationLocalService organizationLocalService;

	@Reference
	protected ProductPurchaseWebService productPurchaseWebService;

	@Reference(target = "(provider=okta)")
	protected UserIdentityProvider userIdentityProvider;

	@Reference
	protected UserLocalService userLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseMessageSubscriber.class);

}