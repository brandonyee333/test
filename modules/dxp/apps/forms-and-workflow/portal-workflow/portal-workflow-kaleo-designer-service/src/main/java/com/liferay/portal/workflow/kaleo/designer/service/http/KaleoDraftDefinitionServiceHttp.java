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

package com.liferay.portal.workflow.kaleo.designer.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.workflow.kaleo.designer.service.KaleoDraftDefinitionServiceUtil;

/**
 * Provides the HTTP utility for the
 * <code>KaleoDraftDefinitionServiceUtil</code> service
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
 * @author Eduardo Lundgren
 * @see KaleoDraftDefinitionServiceSoap
 * @generated
 */
public class KaleoDraftDefinitionServiceHttp {

	public static
		com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition
				addKaleoDraftDefinition(
					HttpPrincipal httpPrincipal, long userId, long groupId,
					String name,
					java.util.Map<java.util.Locale, String> titleMap,
					String content, int version, int draftVersion,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				KaleoDraftDefinitionServiceUtil.class,
				"addKaleoDraftDefinition",
				_addKaleoDraftDefinitionParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, groupId, name, titleMap, content, version,
				draftVersion, serviceContext);

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

			return (com.liferay.portal.workflow.kaleo.designer.model.
				KaleoDraftDefinition)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deleteKaleoDraftDefinitions(
			HttpPrincipal httpPrincipal, String name, int version,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				KaleoDraftDefinitionServiceUtil.class,
				"deleteKaleoDraftDefinitions",
				_deleteKaleoDraftDefinitionsParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, name, version, serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
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
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition
				getKaleoDraftDefinition(
					HttpPrincipal httpPrincipal, String name, int version,
					int draftVersion,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				KaleoDraftDefinitionServiceUtil.class,
				"getKaleoDraftDefinition",
				_getKaleoDraftDefinitionParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, name, version, draftVersion, serviceContext);

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

			return (com.liferay.portal.workflow.kaleo.designer.model.
				KaleoDraftDefinition)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition>
				getKaleoDraftDefinitions(HttpPrincipal httpPrincipal)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				KaleoDraftDefinitionServiceUtil.class,
				"getKaleoDraftDefinitions",
				_getKaleoDraftDefinitionsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey);

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
				<com.liferay.portal.workflow.kaleo.designer.model.
					KaleoDraftDefinition>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition
				getLatestKaleoDraftDefinition(
					HttpPrincipal httpPrincipal, String name, int version,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				KaleoDraftDefinitionServiceUtil.class,
				"getLatestKaleoDraftDefinition",
				_getLatestKaleoDraftDefinitionParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, name, version, serviceContext);

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

			return (com.liferay.portal.workflow.kaleo.designer.model.
				KaleoDraftDefinition)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition>
				getLatestKaleoDraftDefinitions(
					HttpPrincipal httpPrincipal, long companyId, int version,
					int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				KaleoDraftDefinitionServiceUtil.class,
				"getLatestKaleoDraftDefinitions",
				_getLatestKaleoDraftDefinitionsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, version, start, end, orderByComparator);

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
				<com.liferay.portal.workflow.kaleo.designer.model.
					KaleoDraftDefinition>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition>
				getLatestKaleoDraftDefinitions(
					HttpPrincipal httpPrincipal, long companyId,
					String keywords, int version, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				KaleoDraftDefinitionServiceUtil.class,
				"getLatestKaleoDraftDefinitions",
				_getLatestKaleoDraftDefinitionsParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, keywords, version, start, end,
				orderByComparator);

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
				<com.liferay.portal.workflow.kaleo.designer.model.
					KaleoDraftDefinition>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition
				publishKaleoDraftDefinition(
					HttpPrincipal httpPrincipal, long userId, long groupId,
					String name,
					java.util.Map<java.util.Locale, String> titleMap,
					String content,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				KaleoDraftDefinitionServiceUtil.class,
				"publishKaleoDraftDefinition",
				_publishKaleoDraftDefinitionParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, groupId, name, titleMap, content,
				serviceContext);

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

			return (com.liferay.portal.workflow.kaleo.designer.model.
				KaleoDraftDefinition)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		com.liferay.portal.workflow.kaleo.designer.model.KaleoDraftDefinition
				updateKaleoDraftDefinition(
					HttpPrincipal httpPrincipal, long userId, String name,
					java.util.Map<java.util.Locale, String> titleMap,
					String content, int version,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				KaleoDraftDefinitionServiceUtil.class,
				"updateKaleoDraftDefinition",
				_updateKaleoDraftDefinitionParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, name, titleMap, content, version,
				serviceContext);

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

			return (com.liferay.portal.workflow.kaleo.designer.model.
				KaleoDraftDefinition)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		KaleoDraftDefinitionServiceHttp.class);

	private static final Class<?>[] _addKaleoDraftDefinitionParameterTypes0 =
		new Class[] {
			long.class, long.class, String.class, java.util.Map.class,
			String.class, int.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[]
		_deleteKaleoDraftDefinitionsParameterTypes1 = new Class[] {
			String.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _getKaleoDraftDefinitionParameterTypes2 =
		new Class[] {
			String.class, int.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _getKaleoDraftDefinitionsParameterTypes3 =
		new Class[] {};
	private static final Class<?>[]
		_getLatestKaleoDraftDefinitionParameterTypes4 = new Class[] {
			String.class, int.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[]
		_getLatestKaleoDraftDefinitionsParameterTypes5 = new Class[] {
			long.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_getLatestKaleoDraftDefinitionsParameterTypes6 = new Class[] {
			long.class, String.class, int.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_publishKaleoDraftDefinitionParameterTypes7 = new Class[] {
			long.class, long.class, String.class, java.util.Map.class,
			String.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateKaleoDraftDefinitionParameterTypes8 =
		new Class[] {
			long.class, String.class, java.util.Map.class, String.class,
			int.class, com.liferay.portal.kernel.service.ServiceContext.class
		};

}