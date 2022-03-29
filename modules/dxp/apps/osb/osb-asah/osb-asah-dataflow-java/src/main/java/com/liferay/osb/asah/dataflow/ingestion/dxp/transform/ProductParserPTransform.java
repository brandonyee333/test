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
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.Product;

/**
 * @author Riccardo Ferrari
 */
public class ProductParserPTransform extends BaseParserPTransform<Product> {

	@Override
	protected Product doParse(DXPEntityPubsubMessage dxpEntityPubsubMessage)
		throws Exception {

		Product product = ObjectMapperUtil.readValue(
			Product.class, dxpEntityPubsubMessage.getPayload());

		DXPEntityPubsubMessage.Attributes attributes =
			dxpEntityPubsubMessage.getAttributes();

		product.channelId = product.catalogId;
		product.dataSourceId = attributes.getDataSourceId();
		product.projectId = attributes.getProjectId();
		product.uploadDate = attributes.getUploadTime();
		product.uploadType = attributes.getUploadType();

		return product;
	}

}