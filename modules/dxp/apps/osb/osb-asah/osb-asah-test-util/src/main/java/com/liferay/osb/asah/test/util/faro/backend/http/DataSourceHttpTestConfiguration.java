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

package com.liferay.osb.asah.test.util.faro.backend.http;

import com.liferay.osb.asah.common.http.DataSourceHttp;
import com.liferay.osb.asah.common.json.JSONUtil;

import org.json.JSONArray;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Rachael Koestartyo
 */
@TestConfiguration
public class DataSourceHttpTestConfiguration {

	@Bean
	@Primary
	public DataSourceHttp dataSourceHttp() {
		DataSourceHttp dataSourceHttp = Mockito.mock(DataSourceHttp.class);

		Mockito.doAnswer(
			invocation -> {
				JSONArray jsonArray = JSONUtil.putAll(
					JSONUtil.put(
						"name", "Birthdate"
					).put(
						"values", JSONUtil.put("0")
					),
					JSONUtil.put(
						"name", "Email"
					).put(
						"values", JSONUtil.put("test@liferay.com")
					),
					JSONUtil.put(
						"name", "LastName"
					).put(
						"values", JSONUtil.put("Test1")
					));

				return new ResponseEntity<>(
					jsonArray.toString(), HttpStatus.OK);
			}
		).when(
			dataSourceHttp
		).getSalesforceAccountsFields(
			ArgumentMatchers.any(), ArgumentMatchers.anyInt(),
			ArgumentMatchers.anyInt()
		);

		Mockito.doAnswer(
			invocation -> {
				JSONArray jsonArray = JSONUtil.putAll(
					JSONUtil.put(
						"name", "birthday"
					).put(
						"values", JSONUtil.put("0")
					),
					JSONUtil.put(
						"name", "emailAddress"
					).put(
						"values", JSONUtil.put("test@salesforce.com")
					),
					JSONUtil.put(
						"name", "familyName"
					).put(
						"values", JSONUtil.put("Test1")
					));

				return new ResponseEntity<>(
					jsonArray.toString(), HttpStatus.OK);
			}
		).when(
			dataSourceHttp
		).getSalesforceUsersFields(
			ArgumentMatchers.any(), ArgumentMatchers.anyInt(),
			ArgumentMatchers.anyInt()
		);

		return dataSourceHttp;
	}

}