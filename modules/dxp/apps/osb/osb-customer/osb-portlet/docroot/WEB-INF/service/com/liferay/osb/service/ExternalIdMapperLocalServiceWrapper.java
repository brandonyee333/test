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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ExternalIdMapperLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ExternalIdMapperLocalService
 * @generated
 */
@ProviderType
public class ExternalIdMapperLocalServiceWrapper
	implements ExternalIdMapperLocalService,
		ServiceWrapper<ExternalIdMapperLocalService> {
	public ExternalIdMapperLocalServiceWrapper(
		ExternalIdMapperLocalService externalIdMapperLocalService) {
		_externalIdMapperLocalService = externalIdMapperLocalService;
	}

	@Override
	public boolean hasExternalIdMappers(long classNameId, long classPK, int type) {
		return _externalIdMapperLocalService.hasExternalIdMappers(classNameId,
			classPK, type);
	}

	/**
	* Adds the external ID mapper to the database. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapper the external ID mapper
	* @return the external ID mapper that was added
	*/
	@Override
	public com.liferay.osb.model.ExternalIdMapper addExternalIdMapper(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper) {
		return _externalIdMapperLocalService.addExternalIdMapper(externalIdMapper);
	}

	@Override
	public com.liferay.osb.model.ExternalIdMapper addExternalIdMapper(
		long classNameId, long classPK, int type, java.lang.String externalId) {
		return _externalIdMapperLocalService.addExternalIdMapper(classNameId,
			classPK, type, externalId);
	}

	/**
	* Creates a new external ID mapper with the primary key. Does not add the external ID mapper to the database.
	*
	* @param externalIdMapperId the primary key for the new external ID mapper
	* @return the new external ID mapper
	*/
	@Override
	public com.liferay.osb.model.ExternalIdMapper createExternalIdMapper(
		long externalIdMapperId) {
		return _externalIdMapperLocalService.createExternalIdMapper(externalIdMapperId);
	}

	/**
	* Deletes the external ID mapper from the database. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapper the external ID mapper
	* @return the external ID mapper that was removed
	*/
	@Override
	public com.liferay.osb.model.ExternalIdMapper deleteExternalIdMapper(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper) {
		return _externalIdMapperLocalService.deleteExternalIdMapper(externalIdMapper);
	}

	/**
	* Deletes the external ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapperId the primary key of the external ID mapper
	* @return the external ID mapper that was removed
	* @throws PortalException if a external ID mapper with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.ExternalIdMapper deleteExternalIdMapper(
		long externalIdMapperId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _externalIdMapperLocalService.deleteExternalIdMapper(externalIdMapperId);
	}

	@Override
	public com.liferay.osb.model.ExternalIdMapper fetchExternalIdMapper(
		long externalIdMapperId) {
		return _externalIdMapperLocalService.fetchExternalIdMapper(externalIdMapperId);
	}

	/**
	* Returns the external ID mapper with the primary key.
	*
	* @param externalIdMapperId the primary key of the external ID mapper
	* @return the external ID mapper
	* @throws PortalException if a external ID mapper with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.ExternalIdMapper getExternalIdMapper(
		long externalIdMapperId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _externalIdMapperLocalService.getExternalIdMapper(externalIdMapperId);
	}

	/**
	* Updates the external ID mapper in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapper the external ID mapper
	* @return the external ID mapper that was updated
	*/
	@Override
	public com.liferay.osb.model.ExternalIdMapper updateExternalIdMapper(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper) {
		return _externalIdMapperLocalService.updateExternalIdMapper(externalIdMapper);
	}

	@Override
	public com.liferay.osb.model.ExternalIdMapper updateExternalIdMapper(
		long externalIdMapperId, long classNameId, long classPK, int type,
		java.lang.String externalId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _externalIdMapperLocalService.updateExternalIdMapper(externalIdMapperId,
			classNameId, classPK, type, externalId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _externalIdMapperLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _externalIdMapperLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _externalIdMapperLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _externalIdMapperLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _externalIdMapperLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of external ID mappers.
	*
	* @return the number of external ID mappers
	*/
	@Override
	public int getExternalIdMappersCount() {
		return _externalIdMapperLocalService.getExternalIdMappersCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _externalIdMapperLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _externalIdMapperLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _externalIdMapperLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _externalIdMapperLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _externalIdMapperLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the external ID mappers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @return the range of external ID mappers
	*/
	@Override
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> getExternalIdMappers(
		int start, int end) {
		return _externalIdMapperLocalService.getExternalIdMappers(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> getExternalIdMappers(
		long classNameId, int type, java.lang.String externalId) {
		return _externalIdMapperLocalService.getExternalIdMappers(classNameId,
			type, externalId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> getExternalIdMappers(
		long classNameId, long classPK, int type) {
		return _externalIdMapperLocalService.getExternalIdMappers(classNameId,
			classPK, type);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _externalIdMapperLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _externalIdMapperLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void deleteExternalIdMapper(long classNameId, long classPK, int type) {
		_externalIdMapperLocalService.deleteExternalIdMapper(classNameId,
			classPK, type);
	}

	@Override
	public ExternalIdMapperLocalService getWrappedService() {
		return _externalIdMapperLocalService;
	}

	@Override
	public void setWrappedService(
		ExternalIdMapperLocalService externalIdMapperLocalService) {
		_externalIdMapperLocalService = externalIdMapperLocalService;
	}

	private ExternalIdMapperLocalService _externalIdMapperLocalService;
}