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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Riccardo Ferrari
 */
public class PubsubMessageAttributes extends HashMap<String, String> {

	public PubsubMessageAttributes(Map<String, String> attributes) {
		putAll(attributes);
	}

	public long getCount() {
		return Long.parseLong(getOrDefault("count", "0"));
	}

	public String getDataSourceId() {
		return get("dataSourceId");
	}

	public String getProjectId() {
		return get("projectId");
	}

	public String getResourceName() {
		return get("resourceName");
	}

	public String getUploadTime() {
		return get("uploadTime");
	}

	public String getUploadType() {
		return get("uploadType");
	}

	public boolean isLast() {
		return Boolean.parseBoolean(getOrDefault("last", "false"));
	}

}