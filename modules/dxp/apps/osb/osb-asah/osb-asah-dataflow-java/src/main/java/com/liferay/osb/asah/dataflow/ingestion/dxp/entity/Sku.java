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

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

/**
 * @author Riccardo Ferrari
 */
@DefaultSchema(JavaFieldSchema.class)
public class Sku implements Serializable {

	@Nullable
	public String cost;

	@Nullable
	public Boolean discontinued;

	@Nullable
	public String displayDate;

	@Nullable
	public String expirationDate;

	@Nullable
	public String externalReferenceCode;

	@Nullable
	public String gtin;

	@Nullable
	public Long id;

	@Nullable
	public String manufacturerPartNumber;

	@Nullable
	public Boolean published;

	@Nullable
	public Boolean purchasable;

	public String sku;

}