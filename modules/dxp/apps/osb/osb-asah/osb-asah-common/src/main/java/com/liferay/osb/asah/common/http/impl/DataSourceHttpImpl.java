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
	public ResponseEntity<String> getDXPGroups(
		String id, int end, String name, long parentGroupId, boolean site,
		int start) {

		String path = "/groups?dataSourceId=" + id + "&end=" + end;

		if (name != null) {
			path += "&name=" + name;
		}

		path +=
			"&parentGroupId=" + parentGroupId + "&site=" + site + "&start=" +
				start;

		return _http.exchangeResponseEntity(
			ServiceConstants.URL_DXP_EXTRACTOR, path, HttpMethod.GET, "");
	}

	@Override
	public ResponseEntity<String> getDXPGroups(String id, String json) {
		return _http.exchangeResponseEntity(
			ServiceConstants.URL_DXP_EXTRACTOR, "/groups?dataSourceId=" + id,
			HttpMethod.GET, json);
	}

	@Override
	public ResponseEntity<String> getDXPOrganizations(
		String id, int end, String name, long parentOrganizationId, int start) {

		String path = "/dxp-organizations?dataSourceId=" + id + "&end=" + end;

		if (name != null) {
			path += "&name=" + name;
		}

		path +=
			"&parentOrganizationId=" + parentOrganizationId + "&start=" + start;

		return _http.exchangeResponseEntity(
			ServiceConstants.URL_DXP_EXTRACTOR, path, HttpMethod.GET, "");
	}

	@Override
	public ResponseEntity<String> getDXPOrganizations(String id, String json) {
		return _http.exchangeResponseEntity(
			ServiceConstants.URL_DXP_EXTRACTOR,
			"/dxp-organizations?dataSourceId=" + id, HttpMethod.GET, json);
	}

	public ResponseEntity<String> getDXPOwner(String json) {
		return _http.exchangeResponseEntity(
			ServiceConstants.URL_DXP_EXTRACTOR, "/dxp-users", HttpMethod.GET,
			json);
	}

	@Override
	public ResponseEntity<String> getDXPUserGroups(
		String id, int end, String name, int start) {

		return _http.exchangeResponseEntity(
			ServiceConstants.URL_DXP_EXTRACTOR,
			"/user-groups?dataSourceId=" + id + "&end=" + end + "&name=" +
				name + "&start=" + start,
			HttpMethod.GET, "");
	}

	@Override
	public ResponseEntity<String> getDXPUserGroups(String id, String json) {
		return _http.exchangeResponseEntity(
			ServiceConstants.URL_DXP_EXTRACTOR,
			"/user-groups?dataSourceId=" + id, HttpMethod.GET, json);
	}

	@Override
	public ResponseEntity<String> getDXPUsersFields(
		String id, int end, int start) {

		return _http.exchangeResponseEntity(
			ServiceConstants.URL_DXP_EXTRACTOR,
			"/dxp-users/fields?dataSourceId=" + id + "&end=" + end + "&start=" +
				start,
			HttpMethod.GET, "");
	}

	@Override
	public ResponseEntity<String> getDXPUsersTotal(String id, String json) {
		return _http.exchangeResponseEntity(
			ServiceConstants.URL_DXP_EXTRACTOR,
			"/dxp-users/total?dataSourceId=" + id, HttpMethod.GET, json);
	}

	@Override
	public ResponseEntity<String> getSalesforceAccountsFields(
		String id, int end, int start) {

		return _http.exchangeResponseEntity(
			ServiceConstants.URL_SALESFORCE_EXTRACTOR,
			"/accounts/fields?dataSourceId=" + id + "&end=" + end + "&start=" +
				start,
			HttpMethod.GET, "");
	}

	@Override
	public ResponseEntity<String> getSalesforceOwner(String json) {
		return _http.exchangeResponseEntity(
			ServiceConstants.URL_SALESFORCE_EXTRACTOR, "/salesforce-users",
			HttpMethod.GET, json);
	}

	@Override
	public ResponseEntity<String> getSalesforceUsersFields(
		String id, int end, int start) {

		return _http.exchangeResponseEntity(
			ServiceConstants.URL_SALESFORCE_EXTRACTOR,
			"/salesforce-users/fields?dataSourceId=" + id + "&end=" + end +
				"&start=" + start,
			HttpMethod.GET, "");
	}

	@Autowired
	private Http _http;

}