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

package com.liferay.osb.asah.test.util.faro;

import com.liferay.osb.asah.common.elasticsearch.impl.TimeOrderedUuidGenerator;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.test.util.util.RandomTestUtil;

import org.json.JSONObject;

/**
 * @author Matthew Kong
 */
public class DXPRawTestUtil {

	public static JSONObject buildOrganizationJSONObject(String dataSourceId) {
		String name = RandomTestUtil.randomString();
		long organizationId = RandomTestUtil.randomNumber();

		return JSONUtil.put(
			"id", _timeOrderedUuidGenerator.generateId()
		).put(
			"modifiedDate", String.valueOf(System.currentTimeMillis())
		).put(
			"name", name
		).put(
			"nameTreePath", name
		).put(
			"organizationId", organizationId
		).put(
			"osbAsahDataSourceId", dataSourceId
		).put(
			"parentName", ""
		).put(
			"parentOrganizationId", 0
		).put(
			"treePath", "/" + organizationId + "/"
		).put(
			"type", "organization"
		);
	}

	private static final TimeOrderedUuidGenerator _timeOrderedUuidGenerator =
		new TimeOrderedUuidGenerator();

}