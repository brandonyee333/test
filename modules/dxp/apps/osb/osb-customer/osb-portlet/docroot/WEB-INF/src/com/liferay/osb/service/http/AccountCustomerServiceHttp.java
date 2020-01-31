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

import com.liferay.osb.service.AccountCustomerServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link AccountCustomerServiceUtil} service utility. The
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
 * @see AccountCustomerServiceSoap
 * @see HttpPrincipal
 * @see AccountCustomerServiceUtil
 * @generated
 */
@ProviderType
public class AccountCustomerServiceHttp {
	public static java.util.List<com.liferay.portal.kernel.model.User> getCorpProjectAccountCustomerUsers(
		HttpPrincipal httpPrincipal, java.lang.String corpProjectUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountCustomerServiceUtil.class,
					"getCorpProjectAccountCustomerUsers",
					_getCorpProjectAccountCustomerUsersParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					corpProjectUuid);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<java.lang.String> getCorpProjectAccountCustomerUUIDs(
		HttpPrincipal httpPrincipal, java.lang.String corpProjectUuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountCustomerServiceUtil.class,
					"getCorpProjectAccountCustomerUUIDs",
					_getCorpProjectAccountCustomerUUIDsParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					corpProjectUuid);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<java.lang.String>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.User> getCorpProjectIdAccountCustomerUsers(
		HttpPrincipal httpPrincipal, long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountCustomerServiceUtil.class,
					"getCorpProjectIdAccountCustomerUsers",
					_getCorpProjectIdAccountCustomerUsersParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					corpProjectId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<java.lang.String> getCorpProjectIdAccountCustomerUUIDs(
		HttpPrincipal httpPrincipal, long corpProjectId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountCustomerServiceUtil.class,
					"getCorpProjectIdAccountCustomerUUIDs",
					_getCorpProjectIdAccountCustomerUUIDsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					corpProjectId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<java.lang.String>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AccountCustomerServiceHttp.class);
	private static final Class<?>[] _getCorpProjectAccountCustomerUsersParameterTypes0 =
		new Class[] { java.lang.String.class };
	private static final Class<?>[] _getCorpProjectAccountCustomerUUIDsParameterTypes1 =
		new Class[] { java.lang.String.class };
	private static final Class<?>[] _getCorpProjectIdAccountCustomerUsersParameterTypes2 =
		new Class[] { long.class };
	private static final Class<?>[] _getCorpProjectIdAccountCustomerUUIDsParameterTypes3 =
		new Class[] { long.class };
}