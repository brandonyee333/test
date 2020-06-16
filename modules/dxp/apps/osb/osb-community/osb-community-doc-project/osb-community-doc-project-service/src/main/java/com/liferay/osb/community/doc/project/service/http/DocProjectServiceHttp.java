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

package com.liferay.osb.community.doc.project.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.community.doc.project.service.DocProjectServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link DocProjectServiceUtil} service utility. The
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
 * @author Ryan Park
 * @see DocProjectServiceSoap
 * @see HttpPrincipal
 * @see DocProjectServiceUtil
 * @generated
 */
@ProviderType
public class DocProjectServiceHttp {
	public static com.liferay.osb.community.doc.project.model.DocProject addDocProject(
		HttpPrincipal httpPrincipal, java.lang.String name,
		java.lang.String description, java.lang.String iconFileName,
		java.io.File iconFile, boolean unlisted, java.lang.String type,
		java.lang.String typeSettings, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DocProjectServiceUtil.class,
					"addDocProject", _addDocProjectParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, name,
					description, iconFileName, iconFile, unlisted, type,
					typeSettings, status, serviceContext);

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

			return (com.liferay.osb.community.doc.project.model.DocProject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.osb.community.doc.project.model.DocProject updateDocProject(
		HttpPrincipal httpPrincipal, long docProjectId, java.lang.String name,
		java.lang.String description, java.lang.String iconFileName,
		java.io.File iconFile, boolean unlisted, java.lang.String type,
		java.lang.String typeSettings, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(DocProjectServiceUtil.class,
					"updateDocProject", _updateDocProjectParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					docProjectId, name, description, iconFileName, iconFile,
					unlisted, type, typeSettings, status, serviceContext);

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

			return (com.liferay.osb.community.doc.project.model.DocProject)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DocProjectServiceHttp.class);
	private static final Class<?>[] _addDocProjectParameterTypes0 = new Class[] {
			java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.io.File.class, boolean.class,
			java.lang.String.class, java.lang.String.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateDocProjectParameterTypes1 = new Class[] {
			long.class, java.lang.String.class, java.lang.String.class,
			java.lang.String.class, java.io.File.class, boolean.class,
			java.lang.String.class, java.lang.String.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
}