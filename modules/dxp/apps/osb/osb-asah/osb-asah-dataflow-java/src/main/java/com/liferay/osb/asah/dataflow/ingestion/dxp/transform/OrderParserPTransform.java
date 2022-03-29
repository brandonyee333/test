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
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.DXPEntityPubsubMessage;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.Order;

/**
 * @author Riccardo Ferrari
 */
public class OrderParserPTransform extends BaseParserPTransform<Order> {

	@Override
	protected Order doParse(DXPEntityPubsubMessage dxpEntityPubsubMessage)
		throws Exception {

		Order order = ObjectMapperUtil.readValue(
			Order.class, dxpEntityPubsubMessage.getPayload());

		DXPEntityPubsubMessage.Attributes attributes =
			dxpEntityPubsubMessage.getAttributes();

		order.dataSourceId = attributes.getDataSourceId();
		order.projectId = attributes.getProjectId();
		order.uploadDate = attributes.getUploadTime();
		order.uploadType = attributes.getUploadType();

		return order;
	}

}