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

package com.liferay.osb.asah.batch.curator.bot.nanite.ml;

import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;
import com.liferay.osb.asah.common.spring.http.Http;

import java.util.List;

import org.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author Riccardo Ferrari
 * @author Marcellus Tavares
 */
@Component
@ConditionalOnGoogleApplicationCredentials(matchIfMissing = true)
public class LocalSparkManager implements SparkManager {

	@Override
	public void submitJob(
		List<String> arguments, String configuration, String name) {

		_http.exchange(
			_livyServerURL, "/batches", HttpMethod.POST,
			JSONUtil.put(
				"args",
				_createJobArgumentsJSONArray(arguments, configuration, name)
			).put(
				"file", "local:///opt/osb-asah-spark.py"
			));
	}

	private JSONArray _createJobArgumentsJSONArray(
		List<String> arguments, String configuration, String name) {

		JSONArray jsonArray = new JSONArray();

		jsonArray.put(name);
		jsonArray.put("--configuration");
		jsonArray.put("/opt/" + configuration);

		arguments.forEach(jsonArray::put);

		return jsonArray;
	}

	@Autowired
	private Http _http;

	@Value(
		"${osb.asah.spark.manager.local.livy.server.url:http://localhost:8998}"
	)
	private String _livyServerURL;

}