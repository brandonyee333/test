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

package com.liferay.osb.testray.issue.engine.jira.web.internal.util;

import com.liferay.portal.kernel.util.Http;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.xml.bind.DatatypeConverter;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.OAuthConstants;
import org.scribe.model.Token;
import org.scribe.services.RSASha1SignatureService;
import org.scribe.services.SignatureService;

/**
 * @author Ethan Bustad
 */
public class JiraApiHelper extends DefaultApi10a {

	public JiraApiHelper(Http http) {
		_http = http;
	}

	@Override
	public String getAccessTokenEndpoint() {
		return _ACCESS_TOKEN_ENDPOINT_URL;
	}

	@Override
	public String getAuthorizationUrl(Token token) {
		return _http.addParameter(
			_AUTHORIZATION_URL, OAuthConstants.TOKEN, token.getToken());
	}

	@Override
	public String getRequestTokenEndpoint() {
		return _REQUEST_TOKEN_ENDPOINT_URL;
	}

	@Override
	public SignatureService getSignatureService() {
		return new RSASha1SignatureService(_getPrivateKey());
	}

	private PrivateKey _getPrivateKey() {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
				DatatypeConverter.parseBase64Binary(_RSA_PRIVATE_KEY));

			return keyFactory.generatePrivate(privateKeySpec);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static final String _ACCESS_TOKEN_ENDPOINT_URL =
		TestrayIssueEngineJiraWebValues.JIRA_OAUTH_URL + "access-token";

	private static final String _AUTHORIZATION_URL =
		TestrayIssueEngineJiraWebValues.JIRA_OAUTH_URL + "authorize";

	private static final String _REQUEST_TOKEN_ENDPOINT_URL =
		TestrayIssueEngineJiraWebValues.JIRA_OAUTH_URL + "request-token";

	private static final String _RSA_PRIVATE_KEY =
		TestrayIssueEngineJiraWebValues.JIRA_OAUTH_RSA_PRIVATE_KEY;

	private final Http _http;

}