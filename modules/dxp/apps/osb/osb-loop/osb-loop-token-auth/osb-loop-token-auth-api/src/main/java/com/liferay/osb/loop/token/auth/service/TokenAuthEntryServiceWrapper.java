/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.loop.token.auth.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TokenAuthEntryService}.
 *
 * @author Bruno Farache
 * @see TokenAuthEntryService
 * @generated
 */
@ProviderType
public class TokenAuthEntryServiceWrapper implements TokenAuthEntryService,
	ServiceWrapper<TokenAuthEntryService> {
	public TokenAuthEntryServiceWrapper(
		TokenAuthEntryService tokenAuthEntryService) {
		_tokenAuthEntryService = tokenAuthEntryService;
	}

	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry addTokenAuthEntry(
		java.lang.String device)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _tokenAuthEntryService.addTokenAuthEntry(device);
	}

	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry deleteTokenAuthEntry(
		long tokenAuthEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _tokenAuthEntryService.deleteTokenAuthEntry(tokenAuthEntryId);
	}

	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry deleteTokenAuthEntry(
		java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _tokenAuthEntryService.deleteTokenAuthEntry(token);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _tokenAuthEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.loop.token.auth.model.TokenAuthEntry> getTokenAuthEntries(
		int start, int end)
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