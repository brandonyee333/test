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

import org.apache.beam.sdk.schemas.JavaFieldSchema;
import org.apache.beam.sdk.schemas.annotations.DefaultSchema;

/**
 * @author Rachael Koestartyo
 */
@DefaultSchema(JavaFieldSchema.class)
public class DXPEntity extends BaseDXPEntity {

	public String classPK;
	public List<Field> expandoFields;
	public List<Field> fields;
	public String id;
	public String modifiedDate;
	public String type;

}