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

import com.liferay.osb.service.AccountEnvironmentServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link AccountEnvironmentServiceUtil} service utility. The
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
 * @see AccountEnvironmentServiceSoap
 * @see HttpPrincipal
 * @see AccountEnvironmentServiceUtil
 * @generated
 */
@ProviderType
public class AccountEnvironmentServiceHttp {
	public static com.liferay.osb.model.AccountEnvironment addAccountEnvironment(
		HttpPrincipal httpPrincipal, long accountEntryId, long productEntryId,
		java.lang.String name, int envOS, java.lang.String envOSCustom,
		int envDB, int envJVM, int envAS, int envLFR, int envCommerce,
		int envBrowser, int envCS, java.lang.String envSearch,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEnvironmentServiceUtil.class,
					"addAccountEnvironment",
					_addAccountEnvironmentParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountEntryId, productEntryId, name, envOS, envOSCustom,
					envDB, envJVM, envAS, envLFR, envCommerce, envBrowser,
					envCS, envSearch, files, types);

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

			return (com.liferay.osb.model.AccountEnvironment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.AccountEnvironment deleteAccountEnvironment(
		HttpPrincipal httpPrincipal, long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEnvironmentServiceUtil.class,
					"deleteAccountEnvironment",
					_deleteAccountEnvironmentParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountEnvironmentId);

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

			return (com.liferay.osb.model.AccountEnvironment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.AccountEnvironment getAccountEnvironment(
		HttpPrincipal httpPrincipal, long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEnvironmentServiceUtil.class,
					"getAccountEnvironment",
					_getAccountEnvironmentParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountEnvironmentId);

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

			return (com.liferay.osb.model.AccountEnvironment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.osb.model.AccountEnvironment> getAccountEnvironments(
		HttpPrincipal httpPrincipal, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEnvironmentServiceUtil.class,
					"getAccountEnvironments",
					_getAccountEnvironmentsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountEntryId);

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

			return (java.util.List<com.liferay.osb.model.AccountEnvironment>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.Map<java.lang.String, java.util.List<com.liferay.osb.model.AccountEnvironment>> getAccountEnvironmentsMap(
		HttpPrincipal httpPrincipal, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEnvironmentServiceUtil.class,
					"getAccountEnvironmentsMap",
					_getAccountEnvironmentsMapParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountEntryId);

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

			return (java.util.Map<java.lang.String, java.util.List<com.liferay.osb.model.AccountEnvironment>>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.model.AccountEnvironment updateAccountEnvironment(
		HttpPrincipal httpPrincipal, long accountEnvironmentId,
		long productEntryId, java.lang.String name, int envOS,
		java.lang.String envOSCustom, int envDB, int envJVM, int envAS,
		int envLFR, int envCommerce, int envBrowser, int envCS,
		java.lang.String envSearch,
		java.util.List<com.liferay.portal.kernel.util.ObjectValuePair<java.lang.String, java.io.File>> files,
		java.util.List<java.lang.Integer> types)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(AccountEnvironmentServiceUtil.class,
					"updateAccountEnvironment",
					_updateAccountEnvironmentParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					accountEnvironmentId, productEntryId, name, envOS,
					envOSCustom, envDB, envJVM, envAS, envLFR, envCommerce,
					envBrowser, envCS, envSearch, files, types);

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

			return (com.liferay.osb.model.AccountEnvironment)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AccountEnvironmentServiceHttp.class);
	private static final Class<?>[] _addAccountEnvironmentParameterTypes0 = new Class[] {
			long.class, long.class, java.lang.String.class, int.class,
			java.lang.String.class, int.class, int.class, int.class, int.class,
			int.class, int.class, int.class, java.lang.String.class,
			java.util.List.class, java.util.List.class
		};
	private static final Class<?>[] _deleteAccountEnvironmentParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getAccountEnvironmentParameterTypes2 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getAccountEnvironmentsParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getAccountEnvironmentsMapParameterTypes4 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateAccountEnvironmentParameterTypes5 = new Class[] {
			long.class, long.class, java.lang.String.class, int.class,
			java.lang.String.class, int.class, int.class, int.class, int.class,
			int.class, int.class, int.class, java.lang.String.class,
			java.util.List.class, java.util.List.class
		};
}