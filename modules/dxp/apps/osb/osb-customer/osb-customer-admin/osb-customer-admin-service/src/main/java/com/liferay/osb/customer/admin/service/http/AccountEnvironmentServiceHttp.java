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

package com.liferay.osb.customer.admin.service.http;

import com.liferay.osb.customer.admin.service.AccountEnvironmentServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>AccountEnvironmentServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
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
 * @generated
 */
public class AccountEnvironmentServiceHttp {

	public static com.liferay.osb.customer.admin.model.AccountEnvironment
			addAccountEnvironment(
				HttpPrincipal httpPrincipal, long accountEntryId,
				long productEntryId, String name, int envOS, String envOSCustom,
				int envDB, int envJVM, int envAS, int envLFR, int envCommerce,
				int envBrowser, int envCS, String envSearch)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEnvironmentServiceUtil.class, "addAccountEnvironment",
				_addAccountEnvironmentParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountEntryId, productEntryId, name, envOS,
				envOSCustom, envDB, envJVM, envAS, envLFR, envCommerce,
				envBrowser, envCS, envSearch);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.customer.admin.model.AccountEnvironment)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEnvironment
			deleteAccountEnvironment(
				HttpPrincipal httpPrincipal, long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEnvironmentServiceUtil.class, "deleteAccountEnvironment",
				_deleteAccountEnvironmentParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountEnvironmentId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.customer.admin.model.AccountEnvironment)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEnvironment
			getAccountEnvironment(
				HttpPrincipal httpPrincipal, long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEnvironmentServiceUtil.class, "getAccountEnvironment",
				_getAccountEnvironmentParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountEnvironmentId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.customer.admin.model.AccountEnvironment)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.AccountEnvironment>
				getAccountEnvironments(
					HttpPrincipal httpPrincipal, long accountEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEnvironmentServiceUtil.class, "getAccountEnvironments",
				_getAccountEnvironmentsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.osb.customer.admin.model.AccountEnvironment>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.Map
		<String,
		 java.util.List
			 <com.liferay.osb.customer.admin.model.AccountEnvironment>>
					getAccountEnvironmentsMap(
						HttpPrincipal httpPrincipal, long accountEntryId)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEnvironmentServiceUtil.class,
				"getAccountEnvironmentsMap",
				_getAccountEnvironmentsMapParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountEntryId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.Map
				<String,
				 java.util.List
					 <com.liferay.osb.customer.admin.model.AccountEnvironment>>)
						 returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEnvironment
			updateAccountEnvironment(
				HttpPrincipal httpPrincipal, long accountEnvironmentId,
				long productEntryId, String name, int envOS, String envOSCustom,
				int envDB, int envJVM, int envAS, int envLFR, int envCommerce,
				int envBrowser, int envCS, String envSearch)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEnvironmentServiceUtil.class, "updateAccountEnvironment",
				_updateAccountEnvironmentParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountEnvironmentId, productEntryId, name, envOS,
				envOSCustom, envDB, envJVM, envAS, envLFR, envCommerce,
				envBrowser, envCS, envSearch);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.osb.customer.admin.model.AccountEnvironment)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AccountEnvironmentServiceHttp.class);

	private static final Class<?>[] _addAccountEnvironmentParameterTypes0 =
		new Class[] {
			long.class, long.class, String.class, int.class, String.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			int.class, String.class
		};
	private static final Class<?>[] _deleteAccountEnvironmentParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _getAccountEnvironmentParameterTypes2 =
		new Class[] {long.class};
	private static final Class<?>[] _getAccountEnvironmentsParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _getAccountEnvironmentsMapParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[] _updateAccountEnvironmentParameterTypes5 =
		new Class[] {
			long.class, long.class, String.class, int.class, String.class,
			int.class, int.class, int.class, int.class, int.class, int.class,
			int.class, String.class
		};

}