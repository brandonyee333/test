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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for TokenAuthEntry. This utility wraps
 * <code>com.liferay.osb.loop.token.auth.service.impl.TokenAuthEntryServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Bruno Farache
 * @see TokenAuthEntryService
 * @generated
 */
public class TokenAuthEntryServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.loop.token.auth.service.impl.TokenAuthEntryServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			addTokenAuthEntry(String device)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addTokenAuthEntry(device);
	}

	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			deleteTokenAuthEntry(long tokenAuthEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTokenAuthEntry(tokenAuthEntryId);
	}

	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			deleteTokenAuthEntry(String token)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTokenAuthEntry(token);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List
		<com.liferay.osb.loop.token.auth.model.TokenAuthEntry>
				getTokenAuthEntries(int start, int end)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTokenAuthEntries(start, end);
	}

	public static TokenAuthEntryService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TokenAuthEntryService, TokenAuthEntryService>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TokenAuthEntryService.class);

		ServiceTracker<TokenAuthEntryService, TokenAuthEntryService>
			serviceTracker =
				new ServiceTracker
					<TokenAuthEntryService, TokenAuthEntryService>(
						bundle.getBundleContext(), TokenAuthEntryService.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}