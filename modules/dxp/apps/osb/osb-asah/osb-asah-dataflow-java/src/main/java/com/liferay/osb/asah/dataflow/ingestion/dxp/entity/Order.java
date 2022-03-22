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

import javax.annotation.Nullable;

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

/**
 * @author Riccardo Ferrari
 */
@DefaultSchema(JavaFieldSchema.class)
public class Order extends BaseDXPEntity {

	public long accountId;

	@Nullable
	public long channelId;

	public String createDate;

	@Nullable
	public String currencyCode;

	@Nullable
	public String externalReferenceCode;

	public long id;
	public String modifiedDate;
	public String orderDate;
	public List<OrderItem> orderItems;
	public long orderStatus;

	@Nullable
	public String orderTypeExternalReferenceCode;

	@Nullable
	public Long orderTypeId;

	@Nullable
	public String paymentMethod;

	@Nullable
	public Long paymentStatus;

	public Long status;

	@Nullable
	public String total;

	@Nullable
	public Long userId;

}