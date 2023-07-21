/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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