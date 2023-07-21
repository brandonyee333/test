/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.koroneiki.message.subscriber;

import com.liferay.osb.customer.admin.service.ProductEntryLocalService;
import com.liferay.osb.customer.constants.OSBCustomerConstants;
import com.liferay.osb.distributed.messaging.Message;
import com.liferay.osb.distributed.messaging.subscribing.MessageSubscriber;
import com.liferay.osb.koroneiki.phloem.rest.client.dto.v1_0.Product;
import com.liferay.osb.koroneiki.phloem.rest.client.serdes.v1_0.ProductSerDes;
import com.liferay.portal.kernel.json.JSONObject;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(
	immediate = true, property = "topic.pattern=koroneiki.product.create",
	service = ProductCreateMessageSubscriber.class
)
public class ProductCreateMessageSubscriber
	extends BaseMessageSubscriber implements MessageSubscriber {

	@Override
	public void doReceive(Message message) throws Exception {
		JSONObject jsonObject = jsonFactory.createJSONObject(
			(String)message.getPayload());

		Product product = ProductSerDes.toDTO(jsonObject.getString("product"));

		_productEntryLocalService.addProductEntry(
			OSBCustomerConstants.USER_DEFAULT_USER_ID, product.getKey(),
			product.getName(), 0, false, false, null, null);
	}

	@Reference
	private ProductEntryLocalService _productEntryLocalService;

}