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

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

/**
 * @author Riccardo Ferrari
 */
@DefaultSchema(JavaFieldSchema.class)
public class Product extends BaseDXPEntity {

	public long catalogId;
	public List<Long> categoryIds;

	@Nullable
	public String createDate;

	@Nullable
	public Map<String, String> description;

	@Nullable
	public String displayDate;

	@Nullable
	public String expirationDate;

	@Nullable
	public String externalReferenceCode;

	public long id;

	@Nullable
	public Map<String, String> metaDescription;

	@Nullable
	public Map<String, String> metaKeyword;

	@Nullable
	public Map<String, String> metaTitle;

	@Nullable
	public String modifiedDate;

	public Map<String, String> name;

	@Nullable
	public List<Long> productChannelIds;

	public long productId;

	@Nullable
	public List<ProductOption> productOptions;

	@Nullable
	public List<ProductSpecification> productSpecifications;

	@Nullable
	public String productType;

	@Nullable
	public List<Sku> skus;

	public long status;

	@Nullable
	public Boolean subscriptionEnabled;

	@Nullable
	public List<String> tags;

	@Nullable
	public Map<String, String> urls;

}