/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.token.auth.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TokenAuthEntryService}.
 *
 * @author Bruno Farache
 * @see TokenAuthEntryService
 * @generated
 */
public class TokenAuthEntryServiceWrapper
	implements ServiceWrapper<TokenAuthEntryService>, TokenAuthEntryService {

	public TokenAuthEntryServiceWrapper(
		TokenAuthEntryService tokenAuthEntryService) {

		_tokenAuthEntryService = tokenAuthEntryService;
	}

	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			addTokenAuthEntry(String device)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tokenAuthEntryService.addTokenAuthEntry(device);
	}

	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			deleteTokenAuthEntry(long tokenAuthEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tokenAuthEntryService.deleteTokenAuthEntry(tokenAuthEntryId);
	}

	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			deleteTokenAuthEntry(String token)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tokenAuthEntryService.deleteTokenAuthEntry(token);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _tokenAuthEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.loop.token.auth.model.TokenAuthEntry>
			getTokenAuthEntries(int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tokenAuthEntryService.getTokenAuthEntries(start, end);
	}

	@Override
	public TokenAuthEntryService getWrappedService() {
		return _tokenAuthEntryService;
	}

	@Override
	public void setWrappedService(TokenAuthEntryService tokenAuthEntryService) {
		_tokenAuthEntryService = tokenAuthEntryService;
	}

	private TokenAuthEntryService _tokenAuthEntryService;

}