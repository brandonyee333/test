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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ExternalIdMapperLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ExternalIdMapperLocalService
 * @generated
 */
public class ExternalIdMapperLocalServiceWrapper
	implements ExternalIdMapperLocalService,
		ServiceWrapper<ExternalIdMapperLocalService> {
	public ExternalIdMapperLocalServiceWrapper(
		ExternalIdMapperLocalService externalIdMapperLocalService) {
		_externalIdMapperLocalService = externalIdMapperLocalService;
	}

	/**
	* Adds the external ID mapper to the database. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapper the external ID mapper
	* @return the external ID mapper that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper addExternalIdMapper(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.addExternalIdMapper(externalIdMapper);
	}

	/**
	* Creates a new external ID mapper with the primary key. Does not add the external ID mapper to the database.
	*
	* @param externalIdMapperId the primary key for the new external ID mapper
	* @return the new external ID mapper
	*/
	public com.liferay.osb.model.ExternalIdMapper createExternalIdMapper(
		long externalIdMapperId) {
		return _externalIdMapperLocalService.createExternalIdMapper(externalIdMapperId);
	}

	/**
	* Deletes the external ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapperId the primary key of the external ID mapper
	* @return the external ID mapper that was removed
	* @throws PortalException if a external ID mapper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper deleteExternalIdMapper(
		long externalIdMapperId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.deleteExternalIdMapper(externalIdMapperId);
	}

	/**
	* Deletes the external ID mapper from the database. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapper the external ID mapper
	* @return the external ID mapper that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper deleteExternalIdMapper(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.deleteExternalIdMapper(externalIdMapper);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _externalIdMapperLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.dynamicQuery(dynamicQuery);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.ExternalIdMapper fetchExternalIdMapper(
		long externalIdMapperId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.fetchExternalIdMapper(externalIdMapperId);
	}

	/**
	* Returns the external ID mapper with the primary key.
	*
	* @param externalIdMapperId the primary key of the external ID mapper
	* @return the external ID mapper
	* @throws PortalException if a external ID mapper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper getExternalIdMapper(
		long externalIdMapperId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.getExternalIdMapper(externalIdMapperId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> getExternalIdMappers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.getExternalIdMappers(start, end);
	}

	/**
	* Returns the number of external ID mappers.
	*
	* @return the number of external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public int getExternalIdMappersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.getExternalIdMappersCount();
	}

	/**
	* Updates the external ID mapper in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapper the external ID mapper
	* @return the external ID mapper that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper updateExternalIdMapper(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.updateExternalIdMapper(externalIdMapper);
	}

	/**
	* Updates the external ID mapper in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapper the external ID mapper
	* @param merge whether to merge the external ID mapper with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the external ID mapper that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper updateExternalIdMapper(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.updateExternalIdMapper(externalIdMapper,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _externalIdMapperLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_externalIdMapperLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _externalIdMapperLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.ExternalIdMapper addExternalIdMapper(
		long classNameId, long classPK, int type, java.lang.String externalId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.addExternalIdMapper(classNameId,
			classPK, type, externalId);
	}

	public void deleteExternalIdMapper(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		_externalIdMapperLocalService.deleteExternalIdMapper(classNameId,
			classPK, type);
	}

	public java.util.List<com.liferay.osb.model.ExternalIdMapper> getExternalIdMappers(
		long classNameId, int type, java.lang.String externalId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.getExternalIdMappers(classNameId,
			type, externalId);
	}

	public java.util.List<com.liferay.osb.model.ExternalIdMapper> getExternalIdMappers(
		long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.getExternalIdMappers(classNameId,
			classPK, type);
	}

	public com.liferay.osb.model.ExternalIdMapper updateExternalIdMapper(
		long externalIdMapperId, long classNameId, long classPK, int type,
		java.lang.String externalId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _externalIdMapperLocalService.updateExternalIdMapper(externalIdMapperId,
			classNameId, classPK, type, externalId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public ExternalIdMapperLocalService getWrappedExternalIdMapperLocalService() {
		return _externalIdMapperLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedExternalIdMapperLocalService(
		ExternalIdMapperLocalService externalIdMapperLocalService) {
		_externalIdMapperLocalService = externalIdMapperLocalService;
	}

	public ExternalIdMapperLocalService getWrappedService() {
		return _externalIdMapperLocalService;
	}

	public void setWrappedService(
		ExternalIdMapperLocalService externalIdMapperLocalService) {
		_externalIdMapperLocalService = externalIdMapperLocalService;
	}

	private ExternalIdMapperLocalService _externalIdMapperLocalService;
}