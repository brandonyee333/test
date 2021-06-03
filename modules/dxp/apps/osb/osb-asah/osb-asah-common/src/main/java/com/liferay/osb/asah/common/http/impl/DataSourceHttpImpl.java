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

package com.liferay.osb.asah.common.http.impl;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.http.DataSourceHttp;
import com.liferay.osb.asah.common.spring.annotation.MonolithExclude;
import com.liferay.osb.asah.common.spring.http.Http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Shinn Lok
 */
@Component
@MonolithExclude
public class DataSourceHttpImpl implements DataSourceHttp {

	@Override
	public ResponseEntity<String> getSalesforceAccountsFields(
		Long id, int end, int start) {

		return _http.exchangeResponseEntity(
			ServiceConstants.URL_SALESFORCE_EXTRACTOR,
			"/accounts/fields?dataSourceId=" + id + "&end=" + end + "&start=" +
				start,
			HttpMethod.GET, "");
	}

	@Override
	public ResponseEntity<String> getSalesforceOwner(Long id) {
		return _http.exchangeResponseEntity(
			ServiceConstants.URL_SALESFORCE_EXTRACTOR,
			"/salesforce-users?dataSourceId=" + id, HttpMethod.GET, "");
	}

	@Override
	public ResponseEntity<String> getSalesforceUsersFields(
		Long id, int end, int start) {

		return _http.exchangeResponseEntity(
			ServiceConstants.URL_SALESFORCE_EXTRACTOR,
			"/salesforce-users/fields?dataSourceId=" + id + "&end=" + end +
				"&start=" + start,
			HttpMethod.GET, "");
	}

	@Autowired
	private Http _http;

}