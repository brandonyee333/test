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

package com.liferay.osb.asah.backend.rest.controller.api.data.source.v1;

import com.liferay.osb.asah.backend.rest.controller.BaseRestController;
import com.liferay.osb.asah.common.faro.info.dog.FaroInfoDataSourceDog;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Geyson Silva
 */
@RequestMapping(produces = "application/json", value = "/api/1.0/data-sources")
@RestController(
	"com.liferay.osb.asah.backend.rest.controller.api.data.source.v1.DataSourcesRestController"
)
public class DataSourcesRestController extends BaseRestController {

	@PostMapping("/{id}/disconnect")
	public String disconnectDataSource(@PathVariable String id)
		throws Exception {

		JSONObject dataSourceJSONObject =
			_faroInfoDataSourceDog.disconnectDataSource(id);

		_sanitize(dataSourceJSONObject);

		return dataSourceJSONObject.toString();
	}

	@GetMapping("/{id}")
	public String getDataSource(@PathVariable String id) throws Exception {
		JSONObject dataSourceJSONObject =
			_faroInfoDataSourceDog.getDataSourceJSONObject(id);

		_sanitize(dataSourceJSONObject);

		return dataSourceJSONObject.toString();
	}

	@PutMapping("/{id}/details")
	public String updateDataSourceDetails(
			@PathVariable String id, @RequestBody String json)
		throws Exception {

		JSONObject dataSourceJSONObject =
			_faroInfoDataSourceDog.updateDataSourceDetails(
				id, new JSONObject(json));

		_sanitize(dataSourceJSONObject);

		return dataSourceJSONObject.toString();
	}

	private void _sanitize(JSONObject dataSourceJSONObject) {
		dataSourceJSONObject.remove("faroBackendSecuritySignature");

		JSONObject credentialsJSONObject = dataSourceJSONObject.optJSONObject(
			"credentials");

		if (credentialsJSONObject != null) {
			credentialsJSONObject.remove("privateKey");
		}
	}

	@Autowired
	private FaroInfoDataSourceDog _faroInfoDataSourceDog;

}