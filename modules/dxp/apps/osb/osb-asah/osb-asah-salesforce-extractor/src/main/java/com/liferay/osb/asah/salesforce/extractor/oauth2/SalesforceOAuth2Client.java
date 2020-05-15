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

package com.liferay.osb.asah.salesforce.extractor.oauth2;

import com.liferay.osb.asah.common.oauth2.BaseOAuth2Client;
import com.liferay.osb.asah.salesforce.extractor.configuration.impl.SalesforceExtractorRuntimeConfigurationImpl;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author Brian Wing Shun Chan
 * @author Rachael Koestartyo
 */
@Component
public class SalesforceOAuth2Client extends BaseOAuth2Client {

	public String getPath() {
		return _PATH;
	}

	public String post(
		String clientId, String clientSecret, String refreshToken, String url) {

		ResponseEntity<String> responseEntity = post(
			clientId, clientSecret, "/services/oauth2/token", refreshToken,
			url);

		if (!Objects.equals(responseEntity.getStatusCode(), HttpStatus.OK)) {
			throw new HttpClientErrorException(responseEntity.getStatusCode());
		}

		return responseEntity.getBody();
	}

	public void refreshOAuthToken(
			SalesforceExtractorRuntimeConfigurationImpl
				salesforceExtractorRuntimeConfigurationImpl)
		throws Exception {

		String responseJSON = post(
			salesforceExtractorRuntimeConfigurationImpl.
				getSalesforceOAuthClientId(),
			salesforceExtractorRuntimeConfigurationImpl.
				getSalesforceOAuthClientSecret(),
			salesforceExtractorRuntimeConfigurationImpl.
				getSalesforceOAuthRefreshToken(),
			salesforceExtractorRuntimeConfigurationImpl.getSalesforceURL());

		if (StringUtils.isEmpty(responseJSON)) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to parse refreshed access token");
			}

			throw new Exception();
		}

		JSONObject responseJSONObject = new JSONObject(responseJSON);

		OAuth2Response oAuth2Response = toResponse(responseJSONObject);

		salesforceExtractorRuntimeConfigurationImpl.setSalesforceAuthEndpoint(
			oAuth2Response.getAuthEndpoint());
		salesforceExtractorRuntimeConfigurationImpl.
			setSalesforceServiceEndpoint(oAuth2Response.getServiceEndpoint());

		salesforceExtractorRuntimeConfigurationImpl.
			setSalesforceOAuthAccessToken(
				responseJSONObject.getString("access_token"));
	}

	public OAuth2Response toResponse(JSONObject responseJSONObject) {
		OAuth2Response oAuth2Response = new OAuth2Response();

		String id = responseJSONObject.getString("id");

		oAuth2Response.setAuthEndpoint(
			id.substring(0, id.indexOf(".com") + 4) + _PATH);

		oAuth2Response.setServiceEndpoint(
			responseJSONObject.getString("instance_url") + _PATH);

		return oAuth2Response;
	}

	private static final String _PATH = "/services/Soap/u/39.0";

	private static final Log _log = LogFactory.getLog(
		SalesforceOAuth2Client.class);

}