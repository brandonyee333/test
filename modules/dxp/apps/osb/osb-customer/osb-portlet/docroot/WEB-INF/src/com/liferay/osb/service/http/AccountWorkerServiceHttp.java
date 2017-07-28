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

package com.liferay.osb.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.service.AccountWorkerServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link AccountWorkerServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountWorkerServiceSoap
 * @see HttpPrincipal
 * @see AccountWorkerServiceUtil
 * @generated
 */
@ProviderType
public class AccountWorkerServiceHttp {
	public static void addAccountWorkers(HttpPrincipal httpPrincipal,
		long[] userIds, long accountEntryId, int[] roles, int[] notifications)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountWorkerServiceUtil.class,
					"addAccountWorkers", _addAccountWorkersParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, userIds,
					accountEntryId, roles, notifications);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteAccountWorkers(HttpPrincipal httpPrincipal,
		long[] userIds, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountWorkerServiceUtil.class,
					"deleteAccountWorkers", _deleteAccountWorkersParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, userIds,
					accountEntryId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AccountWorkerServiceHttp.class);
	private static final Class<?>[] _addAccountWorkersParameterTypes0 = new Class[] {
			long[].class, long.class, int[].class, int[].class
		};
	private static final Class<?>[] _deleteAccountWorkersParameterTypes1 = new Class[] {
			long[].class, long.class
		};
}