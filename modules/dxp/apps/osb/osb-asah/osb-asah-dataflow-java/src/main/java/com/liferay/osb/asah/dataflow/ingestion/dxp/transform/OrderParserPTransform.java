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

package com.liferay.osb.asah.dataflow.ingestion.dxp.transform;

import com.liferay.osb.asah.dataflow.common.ObjectMapperUtil;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.Order;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.PubsubMessageAttributes;

import org.apache.beam.sdk.values.KV;

/**
 * @author Riccardo Ferrari
 */
public class OrderParserPTransform extends BaseParserPTransform<Order> {

	@Override
	public Class<Order> getEntity() {
		return Order.class;
	}

	@Override
	protected Order doParse(KV<PubsubMessageAttributes, String> element)
		throws Exception {

		Order order = ObjectMapperUtil.readValue(
			Order.class, element.getValue());

		PubsubMessageAttributes pubsubMessageAttributes = element.getKey();

		order.dataSourceId = pubsubMessageAttributes.getDataSourceId();
		order.projectId = pubsubMessageAttributes.getProjectId();
		order.uploadDate = pubsubMessageAttributes.getUploadTime();
		order.uploadType = pubsubMessageAttributes.getUploadType();

		return order;
	}

}