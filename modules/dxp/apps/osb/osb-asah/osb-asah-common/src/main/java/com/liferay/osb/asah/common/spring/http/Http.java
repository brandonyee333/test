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

package com.liferay.osb.asah.common.spring.http;

import com.liferay.osb.asah.common.spring.http.client.OSBAsahClientHttpRequestInterceptor;
import com.liferay.osb.asah.common.spring.http.client.OSBAsahHttpComponentsClientHttpRequestFactory;
import com.liferay.osb.asah.common.spring.http.client.OSBAsahResponseErrorHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.security.oauth.common.OAuthCodec;
import org.springframework.security.oauth.common.signature.CoreOAuthSignatureMethodFactory;
import org.springframework.security.oauth.common.signature.OAuthSignatureMethod;
import org.springframework.security.oauth.common.signature.PlainTextSignatureMethod;
import org.springframework.security.oauth.common.signature.SaltedConsumerSecret;
import org.springframework.security.oauth.common.signature.SharedConsumerSecret;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.common.signature.SignatureSecret;
import org.springframework.security.oauth.common.signature.UnsupportedSignatureMethodException;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.OAuthConsumerToken;
import org.springframework.security.oauth.consumer.OAuthSecurityContextHolder;
import org.springframework.security.oauth.consumer.OAuthSecurityContextImpl;
import org.springframework.security.oauth.consumer.client.CoreOAuthConsumerSupport;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Vishal Reddy
 * @author Rachael Koestartyo
 */
@Component
public class Http {

	public String exchange(
		String url, String path, HttpMethod httpMethod, Object body) {

		ResponseEntity<String> responseEntity = exchangeResponseEntity(
			url, path, httpMethod, body);

		return responseEntity.getBody();
	}

	public String exchange(
		String url, String path, HttpMethod httpMethod, Object body,
		BasicAuthorizationInterceptor basicAuthorizationInterceptor) {

		ResponseEntity<String> responseEntity = exchangeResponseEntity(
			url, path, httpMethod, body, basicAuthorizationInterceptor);

		return responseEntity.getBody();
	}

	public String exchange(
		String url, String path, HttpMethod httpMethod, Object body,
		HttpHeaders httpHeaders) {

		ResponseEntity<String> responseEntity = exchangeResponseEntity(
			url, path, httpMethod, body, httpHeaders);

		return responseEntity.getBody();
	}

	public String exchange(
		String url, String path, HttpMethod httpMethod, Object body,
		String oAuthAccessToken) {

		ResponseEntity<String> responseEntity = exchangeResponseEntity(
			url, path, httpMethod, body, oAuthAccessToken);

		return responseEntity.getBody();
	}

	public String exchange(
		String url, String path, HttpMethod httpMethod, Object body,
		String oAuthAccessSecret, String oAuthAccessToken,
		String oAuthConsumerKey, String oAuthConsumerSecret) {

		ResponseEntity<String> responseEntity = exchangeResponseEntity(
			url, path, httpMethod, body, oAuthAccessSecret, oAuthAccessToken,
			oAuthConsumerKey, oAuthConsumerSecret);

		return responseEntity.getBody();
	}

	public String exchangeIfUp(
		String url, String path, HttpMethod httpMethod, Object body) {

		try {
			return exchange(url, path, httpMethod, body);
		}
		catch (ResourceAccessException rae) {
			Throwable throwable = rae.getCause();

			if (!(throwable instanceof UnknownHostException)) {
				throw rae;
			}

			if (_log.isWarnEnabled()) {
				_log.warn("Unable to reach " + url + ": " + rae);
			}
		}

		return null;
	}

	public ResponseEntity<String> exchangeResponseEntity(
		String url, String path, HttpMethod httpMethod, Object body) {

		if (_log.isDebugEnabled()) {
			_log.debug("Exchange " + httpMethod + " " + body);
		}

		RestTemplate restTemplate = new RestTemplate();

		_configureRestTemplate(restTemplate);

		return _exchangeResponseEntity(
			restTemplate, url, path, httpMethod, _getHttpEntity(body, null));
	}

	public ResponseEntity<String> exchangeResponseEntity(
		String url, String path, HttpMethod httpMethod, Object body,
		BasicAuthorizationInterceptor basicAuthorizationInterceptor) {

		if (_log.isDebugEnabled()) {
			_log.debug("Get " + url);
		}

		RestTemplate restTemplate = new RestTemplate();

		_configureRestTemplate(restTemplate);

		List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors =
			restTemplate.getInterceptors();

		clientHttpRequestInterceptors.add(basicAuthorizationInterceptor);

		return _exchangeResponseEntity(
			restTemplate, url, path, httpMethod, _getHttpEntity(body, null));
	}

	public ResponseEntity<String> exchangeResponseEntity(
		String url, String path, HttpMethod httpMethod, Object body,
		HttpHeaders httpHeaders) {

		if (_log.isDebugEnabled()) {
			_log.debug("Get " + url);
		}

		RestTemplate restTemplate = new RestTemplate();

		_configureRestTemplate(restTemplate);

		return _exchangeResponseEntity(
			restTemplate, url, path, httpMethod,
			_getHttpEntity(body, httpHeaders));
	}

	public ResponseEntity<String> exchangeResponseEntity(
		String url, String path, HttpMethod httpMethod, Object body,
		String oAuthAccessToken) {

		OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(
			new BaseOAuth2ProtectedResourceDetails(),
			new DefaultOAuth2ClientContext(
				new DefaultOAuth2AccessToken(oAuthAccessToken)));

		_configureRestTemplate(oAuth2RestTemplate);

		return _exchangeResponseEntity(
			oAuth2RestTemplate, url, path, httpMethod,
			_getHttpEntity(body, null));
	}

	public ResponseEntity<String> exchangeResponseEntity(
		String url, String path, HttpMethod httpMethod, Object body,
		String username, String password) {

		return exchangeResponseEntity(
			url, path, httpMethod, body,
			new BasicAuthorizationInterceptor(username, password));
	}

	public ResponseEntity<String> exchangeResponseEntity(
		String url, String path, HttpMethod httpMethod, Object body,
		String oAuthAccessSecret, String oAuthAccessToken,
		String oAuthConsumerKey, String oAuthConsumerSecret) {

		if (_log.isDebugEnabled()) {
			_log.debug("Get " + url);
		}

		OAuthConsumerToken oAuthConsumerToken = new OAuthConsumerToken() {
			{
				setAccessToken(true);
				setResourceId("oAuthConsumerToken");
				setSecret(oAuthAccessSecret);
				setValue(oAuthAccessToken);
			}
		};

		Map<String, OAuthConsumerToken> oAuthConsumerTokens =
			new HashMap<String, OAuthConsumerToken>() {
				{
					put(oAuthConsumerToken.getResourceId(), oAuthConsumerToken);
				}
			};

		OAuthSecurityContextHolder.setContext(
			new OAuthSecurityContextImpl() {
				{
					setAccessTokens(oAuthConsumerTokens);
				}
			});

		return _exchangeResponseEntity(
			_getOAuthRestTemplate(
				new BaseProtectedResourceDetails() {
					{
						setConsumerKey(oAuthConsumerKey);
						setId("oAuthConsumerToken");
						setSharedSecret(
							new SharedConsumerSecretImpl(oAuthConsumerSecret));

						if (_isSecure(url)) {
							setSignatureMethod(
								PlainTextSignatureMethod.SIGNATURE_NAME);
						}
					}
				}),
			url, path, httpMethod, _getHttpEntity(body, null));
	}

	private void _configureRestTemplate(RestTemplate restTemplate) {
		List<ClientHttpRequestInterceptor> clientHttpRequestInterceptors =
			restTemplate.getInterceptors();

		clientHttpRequestInterceptors.add(
			new OSBAsahClientHttpRequestInterceptor());

		restTemplate.setErrorHandler(new OSBAsahResponseErrorHandler());
		restTemplate.setRequestFactory(
			new OSBAsahHttpComponentsClientHttpRequestFactory());
	}

	private ResponseEntity<String> _exchangeResponseEntity(
		RestTemplate restTemplate, String url, String path,
		HttpMethod httpMethod, HttpEntity<?> httpEntity) {

		AtomicReference<ResponseEntity<String>> responseEntityAtomicReference =
			new AtomicReference<>();

		String fullURL;

		if (StringUtils.isNotEmpty(path)) {
			fullURL = url.concat(path);
		}
		else {
			fullURL = url;
		}

		_retryTemplate.execute(
			retryContext -> {
				responseEntityAtomicReference.set(
					restTemplate.exchange(
						fullURL, httpMethod, httpEntity, String.class));

				return null;
			});

		return responseEntityAtomicReference.get();
	}

	private HttpEntity<?> _getHttpEntity(Object body, HttpHeaders httpHeaders) {
		if ((body == null) || StringUtils.isEmpty(String.valueOf(body))) {
			return new HttpEntity<>(httpHeaders);
		}

		return new HttpEntity<>(String.valueOf(body), httpHeaders);
	}

	private OAuthRestTemplate _getOAuthRestTemplate(
		BaseProtectedResourceDetails baseProtectedResourceDetails) {

		OAuthRestTemplate oAuthRestTemplate = new OAuthRestTemplate(
			baseProtectedResourceDetails);

		CoreOAuthConsumerSupport coreOAuthConsumerSupport =
			new CoreOAuthConsumerSupport();

		coreOAuthConsumerSupport.setSignatureFactory(
			new OSBAsahCoreOAuthSignatureMethodFactory());

		oAuthRestTemplate.setSupport(coreOAuthConsumerSupport);

		_configureRestTemplate(oAuthRestTemplate);

		return oAuthRestTemplate;
	}

	private boolean _isSecure(String urlString) {
		try {
			URL url = new URL(urlString);

			return StringUtils.equalsIgnoreCase(url.getProtocol(), "https");
		}
		catch (MalformedURLException murle) {
			return false;
		}
	}

	private static final Log _log = LogFactory.getLog(Http.class);

	@Autowired
	private RetryTemplate _retryTemplate;

	private static class OSBAsahCoreOAuthSignatureMethodFactory
		extends CoreOAuthSignatureMethodFactory {

		@Override
		public OAuthSignatureMethod getSignatureMethod(
				String methodName, SignatureSecret signatureSecret,
				String tokenSecret)
			throws UnsupportedSignatureMethodException {

			if (!isSupportPlainText() ||
				!PlainTextSignatureMethod.SIGNATURE_NAME.equals(methodName)) {

				return super.getSignatureMethod(
					methodName, signatureSecret, tokenSecret);
			}

			if (!(signatureSecret instanceof SharedConsumerSecret)) {
				StringBuilder sb = new StringBuilder();

				sb.append("Invalid signature secret ");

				if (signatureSecret == null) {
					sb.append("null");
				}
				else {
					Class<?> clazz = signatureSecret.getClass();

					sb.append(clazz.getName());
				}

				sb.append(" for signature method ");
				sb.append(methodName);

				throw new IllegalArgumentException(sb.toString());
			}

			SharedConsumerSecret sharedConsumerSecret =
				(SharedConsumerSecret)signatureSecret;

			String consumerSecret = sharedConsumerSecret.getConsumerSecret();

			if (consumerSecret == null) {
				consumerSecret = "";
			}

			if (tokenSecret == null) {
				tokenSecret = "";
			}

			Object salt = null;

			if (signatureSecret instanceof SaltedConsumerSecret) {
				SaltedConsumerSecret saltedConsumerSecret =
					(SaltedConsumerSecret)signatureSecret;

				salt = saltedConsumerSecret.getSalt();
			}

			return new PlainTextSignatureMethod(
				OAuthCodec.oauthEncode(consumerSecret) + "&" +
					OAuthCodec.oauthEncode(tokenSecret),
				getPlainTextPasswordEncoder(), salt);
		}

		private OSBAsahCoreOAuthSignatureMethodFactory() {
			setSupportPlainText(true);
		}

	}

}