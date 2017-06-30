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

package com.liferay.osb.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the external ID mapper local service. This utility wraps {@link com.liferay.osb.service.impl.ExternalIdMapperLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalIdMapperLocalService
 * @see com.liferay.osb.service.base.ExternalIdMapperLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.ExternalIdMapperLocalServiceImpl
 * @generated
 */
public class ExternalIdMapperLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.ExternalIdMapperLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the external ID mapper to the database. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapper the external ID mapper
	* @return the external ID mapper that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ExternalIdMapper addExternalIdMapper(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addExternalIdMapper(externalIdMapper);
	}

	/**
	* Creates a new external ID mapper with the primary key. Does not add the external ID mapper to the database.
	*
	* @param externalIdMapperId the primary key for the new external ID mapper
	* @return the new external ID mapper
	*/
	public static com.liferay.osb.model.ExternalIdMapper createExternalIdMapper(
		long externalIdMapperId) {
		return getService().createExternalIdMapper(externalIdMapperId);
	}

	/**
	* Deletes the external ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapperId the primary key of the external ID mapper
	* @return the external ID mapper that was removed
	* @throws PortalException if a external ID mapper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ExternalIdMapper deleteExternalIdMapper(
		long externalIdMapperId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteExternalIdMapper(externalIdMapperId);
	}

	/**
	* Deletes the external ID mapper from the database. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapper the external ID mapper
	* @return the external ID mapper that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ExternalIdMapper deleteExternalIdMapper(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteExternalIdMapper(externalIdMapper);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.osb.model.ExternalIdMapper fetchExternalIdMapper(
		long externalIdMapperId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchExternalIdMapper(externalIdMapperId);
	}

	/**
	* Returns the external ID mapper with the primary key.
	*
	* @param externalIdMapperId the primary key of the external ID mapper
	* @return the external ID mapper
	* @throws PortalException if a external ID mapper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ExternalIdMapper getExternalIdMapper(
		long externalIdMapperId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getExternalIdMapper(externalIdMapperId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the external ID mappers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @return the range of external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.ExternalIdMapper> getExternalIdMappers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getExternalIdMappers(start, end);
	}

	/**
	* Returns the number of external ID mappers.
	*
	* @return the number of external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public static int getExternalIdMappersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getExternalIdMappersCount();
	}

	/**
	* Updates the external ID mapper in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapper the external ID mapper
	* @return the external ID mapper that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ExternalIdMapper updateExternalIdMapper(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateExternalIdMapper(externalIdMapper);
	}

	/**
	* Updates the external ID mapper in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapper the external ID mapper
	* @param merge whether to merge the external ID mapper with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the external ID mapper that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ExternalIdMapper updateExternalIdMapper(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateExternalIdMapper(externalIdMapper, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.osb.model.ExternalIdMapper addExternalIdMapper(
		long classNameId, long classPK, int type, java.lang.String externalId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addExternalIdMapper(classNameId, classPK, type, externalId);
	}

	public static void deleteExternalIdMapper(long classNameId, long classPK,
		int type) throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteExternalIdMapper(classNameId, classPK, type);
	}

	public static java.util.List<com.liferay.osb.model.ExternalIdMapper> getExternalIdMappers(
		long classNameId, int type, java.lang.String externalId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getExternalIdMappers(classNameId, type, externalId);
	}

	public static java.util.List<com.liferay.osb.model.ExternalIdMapper> getExternalIdMappers(
		long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getExternalIdMappers(classNameId, classPK, type);
	}

	public static com.liferay.osb.model.ExternalIdMapper updateExternalIdMapper(
		long externalIdMapperId, long classNameId, long classPK, int type,
		java.lang.String externalId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateExternalIdMapper(externalIdMapperId, classNameId,
			classPK, type, externalId);
	}

	public static void clearService() {
		_service = null;
	}

	public static ExternalIdMapperLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					ExternalIdMapperLocalService.class.getName());

			if (invokableLocalService instanceof ExternalIdMapperLocalService) {
				_service = (ExternalIdMapperLocalService)invokableLocalService;
			}
			else {
				_service = new ExternalIdMapperLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(ExternalIdMapperLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(ExternalIdMapperLocalService service) {
	}

	private static ExternalIdMapperLocalService _service;
}