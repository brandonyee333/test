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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for TokenAuthEntry. This utility wraps
 * {@link com.liferay.osb.loop.token.auth.service.impl.TokenAuthEntryServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Bruno Farache
 * @see TokenAuthEntryService
 * @see com.liferay.osb.loop.token.auth.service.base.TokenAuthEntryServiceBaseImpl
 * @see com.liferay.osb.loop.token.auth.service.impl.TokenAuthEntryServiceImpl
 * @generated
 */
@ProviderType
public class TokenAuthEntryServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.loop.token.auth.service.impl.TokenAuthEntryServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry addTokenAuthEntry(
		java.lang.String device)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addTokenAuthEntry(device);
	}

	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry deleteTokenAuthEntry(
		long tokenAuthEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTokenAuthEntry(tokenAuthEntryId);
	}

	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry deleteTokenAuthEntry(
		java.lang.String token)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTokenAuthEntry(token);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<com.liferay.osb.loop.token.auth.model.TokenAuthEntry> getTokenAuthEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTokenAuthEntries(start, end);
	}

	public static TokenAuthEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TokenAuthEntryService, TokenAuthEntryService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TokenAuthEntryService.class);

		ServiceTracker<TokenAuthEntryService, TokenAuthEntryService> serviceTracker =
			new ServiceTracker<TokenAuthEntryService, TokenAuthEntryService>(bundle.getBundleContext(),
				TokenAuthEntryService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}