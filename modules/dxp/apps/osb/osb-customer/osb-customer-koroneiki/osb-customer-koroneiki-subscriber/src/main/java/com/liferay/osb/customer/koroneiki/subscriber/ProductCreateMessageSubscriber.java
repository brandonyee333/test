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

import com.liferay.osb.customer.admin.service.ProductEntryLocalService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductSerDes;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=koroneiki.product.create",
	service = ProductCreateMessageSubscriber.class
)
public class ProductCreateMessageSubscriber implements MessageSubscriber {

	public void receive(Message message) {
		try {
			Product product = ProductSerDes.toDTO((String)message.getPayload());

			_productEntryLocalService.addProductEntry(
				OSBCustomerConstants.USER_DEFAULT_USER_ID, product.getKey(),
				product.getName(), 0, 0, null, null);
		}
		catch (Exception e) {
			_log.error(message);

			_log.error(e, e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductCreateMessageSubscriber.class);

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

}