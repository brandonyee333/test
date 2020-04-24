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

package com.liferay.osb.loop.token.auth.internal.auto.login;

import com.auth0.jwt.JWTExpiredException;
import com.auth0.jwt.JWTVerifier;

import com.liferay.osb.loop.token.auth.constants.TokenAuthConstants;
import com.liferay.osb.loop.token.auth.exception.ExpiredTokenException;
import com.liferay.osb.loop.token.auth.internal.util.TokenAuthValues;
import com.liferay.osb.loop.token.auth.model.TokenAuthEntry;
import com.liferay.osb.loop.token.auth.service.TokenAuthEntryLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.AccessControlContext;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifier;
import com.liferay.portal.kernel.security.auth.verifier.AuthVerifierResult;
import com.liferay.portal.kernel.security.auto.login.AutoLogin;
import com.liferay.portal.kernel.security.auto.login.BaseAutoLogin;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Farache
 */
@Component(immediate = true, service = AutoLogin.class)
public class TokenAuthAutoLogin extends BaseAutoLogin implements AuthVerifier {

	@Override
	public String getAuthType() {
		return TokenAuthAutoLogin.class.getSimpleName();
	}

	@Override
	public AuthVerifierResult verify(
			AccessControlContext accessControlContext, Properties properties)
		throws AuthException {

		AuthVerifierResult authVerifierResult = new AuthVerifierResult();

		HttpServletRequest request = accessControlContext.getRequest();

		String token = getToken(request);

		if (Validator.isNotNull(token)) {
			try {
				Map<String, Object> claims = verifyJWT(token);

				verifyRevoked(request, token, claims);

				String[] credentials = getCredentials(claims);

				authVerifierResult.setPassword(credentials[1]);

				authVerifierResult.setState(AuthVerifierResult.State.SUCCESS);
				authVerifierResult.setUserId(
					GetterUtil.getLong(credentials[0]));

				return authVerifierResult;
			}
			catch (ExpiredTokenException ete) {
				deleteExpiredToken(ete);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
			}
		}

		return authVerifierResult;
	}

	protected void deleteExpiredToken(ExpiredTokenException ete)
		throws ExpiredTokenException {

		if (_log.isDebugEnabled()) {
			_log.debug(ete, ete);
		}

		try {
			TokenAuthEntry tokenAuthEntry =
				_tokenAuthEntryLocalService.getTokenAuthEntry(
					GetterUtil.getLong(ete.getToken()));

			_tokenAuthEntryLocalService.deleteTokenAuthEntry(
				tokenAuthEntry.getTokenAuthEntryId());
		}
		catch (Exception e) {
		}

		throw ete;
	}

	@Override
	protected String[] doLogin(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		if (!isAllowedURL(request)) {
			return null;
		}

		String token = getToken(request);

		if (Validator.isNotNull(token)) {
			try {
				Map<String, Object> claims = verifyJWT(token);

				verifyRevoked(request, token, claims);

				return getCredentials(claims);
			}
			catch (ExpiredTokenException ete) {
				deleteExpiredToken(ete);
			}
			catch (Exception e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
			}
		}

		return null;
	}

	protected String[] getCredentials(Map<String, Object> claims)
		throws Exception {

		long userId = (Integer)claims.get(TokenAuthConstants.CLAIM_USER_ID);

		User user = _userLocalService.getUserById(userId);

		String[] credentials = new String[3];

		credentials[0] = String.valueOf(user.getUserId());
		credentials[1] = user.getPassword();
		credentials[2] = Boolean.TRUE.toString();

		return credentials;
	}

	protected String getToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");

		if (Validator.isNull(authorization) ||
			!authorization.startsWith("Bearer ")) {

			return null;
		}

		return authorization.substring(7);
	}

	protected boolean isAllowedURL(HttpServletRequest request) {
		if (ArrayUtil.isEmpty(
				TokenAuthValues.TOKEN_AUTHENTICATION_URLS_INCLUDES)) {

			return true;
		}

		String contextPath = _portal.getPathContext();

		String path = StringUtil.toLowerCase(request.getRequestURI());

		if (!contextPath.equals(StringPool.SLASH) &&
			path.contains(contextPath)) {

			path = path.substring(contextPath.length());
		}

		if (ArrayUtil.contains(
				TokenAuthValues.TOKEN_AUTHENTICATION_URLS_INCLUDES, path)) {

			return true;
		}

		return false;
	}

	protected boolean isRevokable(Map<String, Object> claims) {
		return (Boolean)claims.get(TokenAuthConstants.CLAIM_REVOKABLE);
	}

	protected Map<String, Object> verifyJWT(String token) throws Exception {
		JWTVerifier jwtVerifier = new JWTVerifier(TokenAuthValues.TOKEN_SECRET);

		try {
			return jwtVerifier.verify(token);
		}
		catch (JWTExpiredException jwtee) {
			throw new ExpiredTokenException(token, jwtee);
		}
	}

	protected void verifyRevoked(
			HttpServletRequest request, String token,
			Map<String, Object> claims)
		throws Exception {

		if (!isRevokable(claims)) {
			return;
		}

		TokenAuthEntry tokenAuthEntry =
			_tokenAuthEntryLocalService.getTokenAuthEntry(token);

		_tokenAuthEntryLocalService.updateLoginDate(
			tokenAuthEntry.getTokenAuthEntryId(), request.getRemoteAddr());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TokenAuthAutoLogin.class);

	@Reference
	private Portal _portal;

	@Reference
	private TokenAuthEntryLocalService _tokenAuthEntryLocalService;

	@Reference
	private UserLocalService _userLocalService;

}