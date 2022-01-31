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

package com.liferay.osb.asah.publisher.rest.controller.api.data.source.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.entity.DataSource;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Inácio Nery
 * @deprecated As of 3.0.1
 */
@Deprecated
@RequestMapping(produces = "application/json", value = "/api/1.0/data-sources")
@RestController
public class DataSourcesRestController {

	/**
	 * @deprecated As of 3.0.1
	 */
	@Deprecated
	@GetMapping("/{id}")
	public String fetchDataSource(@PathVariable Long id) {
		DataSource dataSource = _dataSourceDog.fetchDataSource(id);

		if (dataSource == null) {
			return null;
		}

		_sanitize(dataSource);

		JSONObject dataSourceJSONObject = _objectMapper.convertValue(
			dataSource, JSONObject.class);

		return dataSourceJSONObject.toString();
	}

	private void _sanitize(DataSource dataSource) {
		dataSource.setFaroBackendSecuritySignature(null);
		dataSource.setPrivateKey(null);
	}

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private ObjectMapper _objectMapper;

}