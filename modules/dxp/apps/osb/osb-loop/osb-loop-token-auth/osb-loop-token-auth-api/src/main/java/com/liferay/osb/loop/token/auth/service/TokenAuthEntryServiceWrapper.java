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