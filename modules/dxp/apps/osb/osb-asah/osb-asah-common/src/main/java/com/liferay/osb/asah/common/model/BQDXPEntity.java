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

package com.liferay.osb.asah.common.model;

import java.util.Date;

import org.json.JSONObject;

/**
 * @author Marcos Martins
 */
public interface BQDXPEntity {

	public Long getDataSourceId();

	public String getDataSourceName();

	public String getDXPEntityType();

	public JSONObject getFieldsJSONObject();

	public String getId();

	public String getIdFieldName();

	public Long getIdFieldValue();

	public Date getModifiedDate();

	public String getName();

	public void setDataSourceName(String dataSourceName);

}