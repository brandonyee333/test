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
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.Product;
import com.liferay.osb.asah.dataflow.ingestion.dxp.entity.PubsubMessageAttributes;

import org.apache.beam.sdk.values.KV;

/**
 * @author Riccardo Ferrari
 */
public class ProductParserPTransform extends BaseParserPTransform<Product> {

	@Override
	public Class<Product> getEntity() {
		return Product.class;
	}

	@Override
	protected Product doParse(KV<PubsubMessageAttributes, String> element)
		throws Exception {

		Product product = ObjectMapperUtil.readValue(
			Product.class, element.getValue());

		PubsubMessageAttributes pubsubMessageAttributes = element.getKey();

		product.channelId = product.catalogId;
		product.dataSourceId = pubsubMessageAttributes.getDataSourceId();
		product.projectId = pubsubMessageAttributes.getProjectId();
		product.uploadDate = pubsubMessageAttributes.getUploadTime();
		product.uploadType = pubsubMessageAttributes.getUploadType();

		return product;
	}

}