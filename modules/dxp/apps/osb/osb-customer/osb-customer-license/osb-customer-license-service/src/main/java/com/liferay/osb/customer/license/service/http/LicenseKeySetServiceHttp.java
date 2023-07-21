/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.service.http;

import com.liferay.osb.customer.license.service.LicenseKeySetServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>LicenseKeySetServiceUtil</code> service
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
 * @see LicenseKeySetServiceSoap
 * @generated
 */
public class LicenseKeySetServiceHttp {

	public static com.liferay.osb.customer.license.model.LicenseKeySet
			deleteLicenseKeySet(
				HttpPrincipal httpPrincipal, long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeySetServiceUtil.class, "deleteLicenseKeySet",
				_deleteLicenseKeySetParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, licenseKeySetId);

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

			return (com.liferay.osb.customer.license.model.LicenseKeySet)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySet
			getLicenseKeySet(HttpPrincipal httpPrincipal, long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeySetServiceUtil.class, "getLicenseKeySet",
				_getLicenseKeySetParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, licenseKeySetId);

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

			return (com.liferay.osb.customer.license.model.LicenseKeySet)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.osb.customer.license.model.LicenseKeySet
			updateLicenseKeySet(
				HttpPrincipal httpPrincipal, long licenseKeySetId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				LicenseKeySetServiceUtil.class, "updateLicenseKeySet",
				_updateLicenseKeySetParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, licenseKeySetId, name);

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

			return (com.liferay.osb.customer.license.model.LicenseKeySet)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LicenseKeySetServiceHttp.class);

	private static final Class<?>[] _deleteLicenseKeySetParameterTypes0 =
		new Class[] {long.class};
	private static final Class<?>[] _getLicenseKeySetParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[] _updateLicenseKeySetParameterTypes2 =
		new Class[] {long.class, String.class};

}