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

import com.liferay.osb.loop.token.auth.comparator.TokenAuthEntryCreateDateComparator;
import com.liferay.osb.loop.token.auth.constants.ActionKeys;
import com.liferay.osb.loop.token.auth.model.TokenAuthEntry;
import com.liferay.osb.loop.token.auth.service.base.TokenAuthEntryServiceBaseImpl;
import com.liferay.osb.loop.token.auth.service.permission.TokenAuthPermission;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Bruno Farache
 */
public class TokenAuthEntryServiceImpl extends TokenAuthEntryServiceBaseImpl {

	@Override
	public TokenAuthEntry addTokenAuthEntry(String device)
		throws PortalException {

		TokenAuthPermission.check(getPermissionChecker(), ActionKeys.ADD_TOKEN);

		return tokenAuthEntryLocalService.addTokenAuthEntry(
			getUserId(), device);
	}

	@Override
	public TokenAuthEntry deleteTokenAuthEntry(long tokenAuthEntryId)
		throws PortalException {

		TokenAuthPermission.check(
			getPermissionChecker(), tokenAuthEntryId, ActionKeys.DELETE);

		return tokenAuthEntryLocalService.deleteTokenAuthEntry(
			tokenAuthEntryId);
	}

	@Override
	public TokenAuthEntry deleteTokenAuthEntry(String token)
		throws PortalException {

		TokenAuthEntry tokenAuthEntry =
			tokenAuthEntryLocalService.getTokenAuthEntry(token);

		return deleteTokenAuthEntry(tokenAuthEntry.getTokenAuthEntryId());
	}

	@Override
	public List<TokenAuthEntry> getTokenAuthEntries(int start, int end)
		throws PortalException {

		boolean hasManageTokensPermission = TokenAuthPermission.contains(
			getPermissionChecker(), ActionKeys.MANAGE_TOKENS);

		TokenAuthEntryCreateDateComparator orderByComparator =
			new TokenAuthEntryCreateDateComparator();

		if (hasManageTokensPermission) {
			return tokenAuthEntryLocalService.getTokenAuthEntries(
				start, end, orderByComparator);
		}

		return tokenAuthEntryLocalService.getTokenAuthEntries(
			getUserId(), start, end, orderByComparator);
	}

}