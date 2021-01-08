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

package com.liferay.osb.asah.common.faro.info.dog;

import com.liferay.osb.asah.common.http.NanitesHttp;
import com.liferay.osb.asah.common.json.JSONUtil;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Eddie Olson
 */
@Component
public class FaroInfoOSBAsahTaskDog extends BaseFaroInfoDog {

	public void addOSBAsahTask(String className, JSONObject contextJSONObject) {
		JSONObject osbAsahTaskJSONObject = elasticsearchInvoker.add(
			"OSBAsahTasks",
			JSONUtil.put(
				"className", className
			).put(
				"context", contextJSONObject
			));

		_nanitesHttp.executeOSBAsahTask(osbAsahTaskJSONObject);
	}

	public void scheduleOSBAsahTask(
		String className, JSONObject contextJSONObject, String cronExpression) {

		JSONObject osbAsahTaskJSONObject = elasticsearchInvoker.add(
			"OSBAsahTasks",
			JSONUtil.put(
				"className", className
			).put(
				"context", contextJSONObject
			).put(
				"cronExpression", cronExpression
			));

		_nanitesHttp.scheduleOSBAsahTask(osbAsahTaskJSONObject);
	}

	@Autowired
	private NanitesHttp _nanitesHttp;

}