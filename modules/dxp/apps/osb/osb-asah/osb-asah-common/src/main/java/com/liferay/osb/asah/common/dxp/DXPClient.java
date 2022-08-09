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

package com.liferay.osb.asah.common.dxp;

import com.liferay.osb.asah.common.dog.DataSourceDog;
import com.liferay.osb.asah.common.entity.DataSource;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.DXPVariantSettings;
import com.liferay.osb.asah.common.model.ExperimentStatus;
import com.liferay.osb.asah.common.security.Encryptor;
import com.liferay.osb.asah.common.spring.http.Http;

import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author André Miranda
 */
@Component
public class DXPClient {

	public JSONObject deleteDXPExperiment(
		Long dataSourceId, Long experimentId) {

		String path = String.format(
			"/o/segments-asah/v1.0/experiments/%s", experimentId);

		return _deleteJSONObject(
			_dataSourceDog.getDataSource(dataSourceId), path);
	}

	public JSONObject runDXPExperiment(
		Double confidenceLevel, Long dataSourceId,
		List<DXPVariantSettings> dxpVariantSettingsList, Long experimentId) {

		String path = String.format(
			"/o/segments-asah/v1.0/experiments/%s/run", experimentId);

		JSONArray experimentVariantsJSONArray = new JSONArray();

		Stream<DXPVariantSettings> stream = dxpVariantSettingsList.stream();

		stream.map(
			dxpVariantSettings -> JSONUtil.put(
				"id", dxpVariantSettings.getDXPVariantId()
			).put(
				"trafficSplit", dxpVariantSettings.getTrafficSplit()
			)
		).forEach(
			experimentVariantsJSONArray::put
		);

		return _postJSONObject(
			_dataSourceDog.getDataSource(dataSourceId), path,
			JSONUtil.put(
				"confidenceLevel", confidenceLevel
			).put(
				"experimentVariants", experimentVariantsJSONArray
			));
	}

	public JSONObject updateDXPExperimentStatus(
		Long dataSourceId, Long experimentId, ExperimentStatus experimentStatus,
		String dxpVariantId) {

		String path = String.format(
			"/o/segments-asah/v1.0/experiments/%s/status", experimentId);

		JSONObject bodyJSONObject = JSONUtil.put("status", experimentStatus);

		if (StringUtils.isNotEmpty(dxpVariantId)) {

			// This is the winner variant when the experiment is finished and
			// the published one when the experiment is completed

			bodyJSONObject.put("winnerVariantId", dxpVariantId);
		}

		return _postJSONObject(
			_dataSourceDog.getDataSource(dataSourceId), path, bodyJSONObject);
	}

	private JSONObject _deleteJSONObject(DataSource dataSource, String path) {
		ResponseEntity<String> responseEntity = _exchangeResponseEntity(
			dataSource, path, HttpMethod.DELETE, null);

		if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
			return _getBodyAsJSONObject(responseEntity.getBody());
		}

		throw new HttpClientErrorException(responseEntity.getStatusCode());
	}

	private ResponseEntity<String> _exchangeResponseEntity(
		DataSource dataSource, String path, HttpMethod httpMethod,
		Object body) {

		String credentialType = dataSource.getCredentialType();

		if (credentialType.equals("Basic Authentication")) {
			return _http.exchangeResponseEntity(
				dataSource.getURL(), path, httpMethod, body,
				new BasicAuthorizationInterceptor(
					dataSource.getLogin(), dataSource.getPassword()));
		}

		if (credentialType.equals("Token Authentication")) {
			HttpHeaders httpHeaders = new HttpHeaders() {
				{
					set(
						"Liferay-Analytics-Cloud-Security-Timestamp",
						String.valueOf(System.currentTimeMillis()));
				}
			};

			httpHeaders.set(
				"Liferay-Analytics-Cloud-Security-Signature",
				_encryptor.getSignature(
					httpHeaders, path,
					_encryptor.decrypt(
						dataSource.getURL(), dataSource.getPrivateKey())));

			return _http.exchangeResponseEntity(
				dataSource.getURL(), path, httpMethod, body, httpHeaders);
		}

		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	private JSONObject _getBodyAsJSONObject(String body) {
		if (body == null) {
			return new JSONObject();
		}

		return new JSONObject(body);
	}

	private JSONObject _postJSONObject(
		DataSource dataSource, String path, Object body) {

		ResponseEntity<String> responseEntity = _exchangeResponseEntity(
			dataSource, path, HttpMethod.POST, body);

		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			return _getBodyAsJSONObject(responseEntity.getBody());
		}

		throw new HttpClientErrorException(responseEntity.getStatusCode());
	}

	@Autowired
	private DataSourceDog _dataSourceDog;

	@Autowired
	private Encryptor _encryptor;

	@Autowired
	private Http _http;

}