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

package com.liferay.osb.loop.token.auth.service.impl;

import com.auth0.jwt.JWTSigner;

import com.liferay.osb.loop.token.auth.constants.TokenAuthConstants;
import com.liferay.osb.loop.token.auth.exception.NoTokenSecretException;
import com.liferay.osb.loop.token.auth.exception.TokenAuthEntryDeviceException;
import com.liferay.osb.loop.token.auth.internal.util.TokenAuthValues;
import com.liferay.osb.loop.token.auth.model.TokenAuthEntry;
import com.liferay.osb.loop.token.auth.service.base.TokenAuthEntryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.async.Async;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bruno Farache
 */
public class TokenAuthEntryLocalServiceImpl
	extends TokenAuthEntryLocalServiceBaseImpl {

	@Override
	public TokenAuthEntry addTokenAuthEntry(long userId, String device)
		throws PortalException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		validate(device);

		long tokenAuthEntryId = counterLocalService.increment();

		TokenAuthEntry tokenAuthEntry = tokenAuthEntryPersistence.create(
			tokenAuthEntryId);

		tokenAuthEntry.setCompanyId(user.getCompanyId());
		tokenAuthEntry.setUserId(user.getUserId());
		tokenAuthEntry.setUserName(user.getFullName());
		tokenAuthEntry.setCreateDate(now);
		tokenAuthEntry.setDevice(device);
		tokenAuthEntry.setToken(generateToken(now, userId, device));

		if (TokenAuthValues.TOKEN_REVOKABLE) {
			tokenAuthEntry = tokenAuthEntryPersistence.update(tokenAuthEntry);

			resourceLocalService.addResources(
				user.getCompanyId(), 0, user.getUserId(),
				TokenAuthEntry.class.getName(),
				tokenAuthEntry.getTokenAuthEntryId(), false, false, false);
		}

		return tokenAuthEntry;
	}

	@Override
	public TokenAuthEntry deleteTokenAuthEntry(long tokenAuthEntryId)
		throws PortalException {

		TokenAuthEntry tokenAuthEntry =
			tokenAuthEntryPersistence.findByPrimaryKey(tokenAuthEntryId);

		deleteTokenAuthEntry(tokenAuthEntry);

		resourceLocalService.deleteResource(
			tokenAuthEntry.getCompanyId(), TokenAuthEntry.class.getName(),
			ResourceConstants.SCOPE_INDIVIDUAL,
			tokenAuthEntry.getTokenAuthEntryId());

		return tokenAuthEntry;
	}

	@Override
	public List<TokenAuthEntry> getTokenAuthEntries(
			int start, int end, OrderByComparator orderByComparator)
		throws PortalException {

		return tokenAuthEntryPersistence.findAll(start, end, orderByComparator);
	}

	@Override
	public List<TokenAuthEntry> getTokenAuthEntries(
			long userId, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException {

		return tokenAuthEntryPersistence.findByUserId(
			userId, start, end, orderByComparator);
	}

	@Override
	public int getTokenAuthEntriesCount(long userId) {
		return tokenAuthEntryPersistence.countByUserId(userId);
	}

	@Override
	public TokenAuthEntry getTokenAuthEntry(String token)
		throws PortalException {

		return tokenAuthEntryPersistence.findByToken(token);
	}

	@Async
	@Override
	public void updateLoginDate(long tokenAuthEntryId, String loginIP)
		throws PortalException {

		TokenAuthEntry tokenAuthEntry =
			tokenAuthEntryPersistence.findByPrimaryKey(tokenAuthEntryId);

		tokenAuthEntry.setLoginDate(new Date());
		tokenAuthEntry.setLoginIP(loginIP);

		tokenAuthEntryPersistence.update(tokenAuthEntry);
	}

	protected String generateToken(Date createDate, long userId, String device)
		throws NoTokenSecretException {

		long issuedAtTime = createDate.getTime() / 1000L;

		String tokenSecret = TokenAuthValues.TOKEN_SECRET;

		if (Validator.isNull(tokenSecret)) {
			throw new NoTokenSecretException();
		}

		JWTSigner signer = new JWTSigner(tokenSecret);

		Map<String, Object> claims = new HashMap<>();

		claims.put(TokenAuthConstants.CLAIM_DEVICE, device);
		claims.put(TokenAuthConstants.CLAIM_ISSUED_AT_TIME, issuedAtTime);
		claims.put(
			TokenAuthConstants.CLAIM_REVOKABLE,
			TokenAuthValues.TOKEN_REVOKABLE);

		claims.put(TokenAuthConstants.CLAIM_USER_ID, userId);

		long expirationSeconds = TokenAuthValues.TOKEN_EXPIRATION_SECONDS;

		if (expirationSeconds > 0) {
			long exp = issuedAtTime + expirationSeconds;

			claims.put(TokenAuthConstants.CLAIM_EXPIRATION_TIME, exp);
		}

		return signer.sign(claims);
	}

	protected void validate(String device) throws PortalException {
		if (Validator.isNull(device)) {
			throw new TokenAuthEntryDeviceException();
		}
	}

}