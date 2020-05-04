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

/**
 * @author Brian Wing Shun Chan
 * @author Rachael Koestartyo
 */
public class OAuth2Response {

	public String getAuthEndpoint() {
		return _authEndpoint;
	}

	public String getOAuthAccessToken() {
		return _oAuthAccessToken;
	}

	public String getServiceEndpoint() {
		return _serviceEndpoint;
	}

	public void setAuthEndpoint(String authEndpoint) {
		_authEndpoint = authEndpoint;
	}

	public void setOAuthAccessToken(String oAuthAccessToken) {
		_oAuthAccessToken = oAuthAccessToken;
	}

	public void setServiceEndpoint(String serviceEndpoint) {
		_serviceEndpoint = serviceEndpoint;
	}

	private String _authEndpoint;
	private String _oAuthAccessToken;
	private String _serviceEndpoint;

}