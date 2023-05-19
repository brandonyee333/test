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

import com.liferay.osb.loop.token.auth.model.TokenAuthEntry;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

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
	public static TokenAuthEntry addTokenAuthEntry(String device)
		throws PortalException {

		return getService().addTokenAuthEntry(device);
	}

	public static TokenAuthEntry deleteTokenAuthEntry(long tokenAuthEntryId)
		throws PortalException {

		return getService().deleteTokenAuthEntry(tokenAuthEntryId);
	}

	public static TokenAuthEntry deleteTokenAuthEntry(String token)
		throws PortalException {

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

	public static List<TokenAuthEntry> getTokenAuthEntries(int start, int end)
		throws PortalException {

		return getService().getTokenAuthEntries(start, end);
	}

	public static TokenAuthEntryService getService() {
		return _service;
	}

	public static void setService(TokenAuthEntryService service) {
		_service = service;
	}

	private static volatile TokenAuthEntryService _service;

}