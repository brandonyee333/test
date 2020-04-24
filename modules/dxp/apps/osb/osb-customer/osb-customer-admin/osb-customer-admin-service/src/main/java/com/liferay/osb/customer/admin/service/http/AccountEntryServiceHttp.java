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

import com.liferay.osb.customer.admin.service.AccountEntryServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>AccountEntryServiceUtil</code> service
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
 * @see AccountEntryServiceSoap
 * @generated
 */
public class AccountEntryServiceHttp {

	public static com.liferay.osb.customer.admin.model.AccountEntry
			deleteAccountEntry(HttpPrincipal httpPrincipal, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEntryServiceUtil.class, "deleteAccountEntry",
				_deleteAccountEntryParameterTypes0);

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

			return (com.liferay.osb.customer.admin.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			fetchCorpProjectAccountEntry(
				HttpPrincipal httpPrincipal, String corpProjectUuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEntryServiceUtil.class, "fetchCorpProjectAccountEntry",
				_fetchCorpProjectAccountEntryParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, corpProjectUuid);

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

			return (com.liferay.osb.customer.admin.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			fetchKoroneikiAccountEntry(
				HttpPrincipal httpPrincipal, String koroneikiAccountKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEntryServiceUtil.class, "fetchKoroneikiAccountEntry",
				_fetchKoroneikiAccountEntryParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, koroneikiAccountKey);

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

			return (com.liferay.osb.customer.admin.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.osb.customer.admin.model.AccountEntry> getAccountEntries(
				HttpPrincipal httpPrincipal, String userUuid,
				long[] productEntryIds)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEntryServiceUtil.class, "getAccountEntries",
				_getAccountEntriesParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userUuid, productEntryIds);

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
				<com.liferay.osb.customer.admin.model.AccountEntry>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			getAccountEntry(HttpPrincipal httpPrincipal, long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEntryServiceUtil.class, "getAccountEntry",
				_getAccountEntryParameterTypes4);

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

			return (com.liferay.osb.customer.admin.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			getAccountEntryByCode(HttpPrincipal httpPrincipal, String code)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEntryServiceUtil.class, "getAccountEntryByCode",
				_getAccountEntryByCodeParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, code);

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

			return (com.liferay.osb.customer.admin.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			getCorpProjectAccountEntry(
				HttpPrincipal httpPrincipal, String corpProjectUuid)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEntryServiceUtil.class, "getCorpProjectAccountEntry",
				_getCorpProjectAccountEntryParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, corpProjectUuid);

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

			return (com.liferay.osb.customer.admin.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			updateInstructions(
				HttpPrincipal httpPrincipal, long accountEntryId,
				String instructions)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEntryServiceUtil.class, "updateInstructions",
				_updateInstructionsParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, accountEntryId, instructions);

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

			return (com.liferay.osb.customer.admin.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			updateInstructions(
				HttpPrincipal httpPrincipal, String koroneikiAccountKey,
				String instructions)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEntryServiceUtil.class, "updateInstructions",
				_updateInstructionsParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, koroneikiAccountKey, instructions);

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

			return (com.liferay.osb.customer.admin.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.admin.model.AccountEntry
			updateLanguageId(
				HttpPrincipal httpPrincipal, String koroneikiAccountKey,
				String languageId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				AccountEntryServiceUtil.class, "updateLanguageId",
				_updateLanguageIdParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, koroneikiAccountKey, languageId);

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

			return (com.liferay.osb.customer.admin.model.AccountEntry)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		AccountEntryServiceHttp.class);

	private static final Class<?>[] _deleteAccountEntryParameterTypes0 =
		new Class[] {long.class};
	private static final Class<?>[]
		_fetchCorpProjectAccountEntryParameterTypes1 = new Class[] {
			String.class
		};
	private static final Class<?>[] _fetchKoroneikiAccountEntryParameterTypes2 =
		new Class[] {String.class};
	private static final Class<?>[] _getAccountEntriesParameterTypes3 =
		new Class[] {String.class, long[].class};
	private static final Class<?>[] _getAccountEntryParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[] _getAccountEntryByCodeParameterTypes5 =
		new Class[] {String.class};
	private static final Class<?>[] _getCorpProjectAccountEntryParameterTypes6 =
		new Class[] {String.class};
	private static final Class<?>[] _updateInstructionsParameterTypes7 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _updateInstructionsParameterTypes8 =
		new Class[] {String.class, String.class};
	private static final Class<?>[] _updateLanguageIdParameterTypes9 =
		new Class[] {String.class, String.class};

}