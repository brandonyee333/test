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
import com.liferay.osb.asah.common.http.EmailHttp;
import com.liferay.osb.asah.common.spring.http.Http;

import org.apache.commons.codec.digest.DigestUtils;

import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author Matthew Kong
 */
@Component
public class EmailHttpImpl implements EmailHttp {

	@Override
	public void sendEmail(JSONObject jsonObject) {
		_http.exchangeResponseEntity(
			ServiceConstants.URL_FRONTEND, "/o/email", HttpMethod.POST,
			jsonObject.toString(),
			_getHttpHeaders(ServiceConstants.URL_FRONTEND));
	}

	private HttpHeaders _getHttpHeaders(String url) {
		return new HttpHeaders() {
			{
				set(
					"OSB-Asah-Faro-Backend-Security-Signature",
					DigestUtils.sha256Hex(_osbAsahSecurityToken.concat(url)));
			}
		};
	}

	@Autowired
	private Http _http;

	@Value("${osb.asah.security.token:}")
	private String _osbAsahSecurityToken;

}