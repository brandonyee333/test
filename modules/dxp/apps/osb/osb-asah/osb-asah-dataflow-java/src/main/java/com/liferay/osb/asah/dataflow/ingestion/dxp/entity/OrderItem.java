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

package com.liferay.osb.asah.dataflow.ingestion.dxp.entity;

import java.io.Serializable;

import java.util.Map;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

/**
 * @author Riccardo Ferrari
 */
@DefaultSchema(JavaFieldSchema.class)
public class OrderItem implements Serializable {

	public long cpDefinitionId;
	public String createDate;

	@Nullable
	public Map<String, String> customFields;

	@Nullable
	public String externalReferenceCode;

	@Nullable
	public String finalPrice;

	public long id;

	@Nullable
	public String modifiedDate;

	@Nullable
	public Map<String, String> name;

	@Nullable
	public String options;

	public long parentOrderItemId;
	public long quantity;
	public String sku;

	@Nullable
	public Boolean subscription;

	@Nullable
	public String unitOfMeasure;

	@Nullable
	public String unitPrice;

	public long userId;

}