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
 * Provides a wrapper for {@link SupportResponseLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SupportResponseLocalService
 * @generated
 */
@ProviderType
public class SupportResponseLocalServiceWrapper
	implements SupportResponseLocalService,
		ServiceWrapper<SupportResponseLocalService> {
	public SupportResponseLocalServiceWrapper(
		SupportResponseLocalService supportResponseLocalService) {
		_supportResponseLocalService = supportResponseLocalService;
	}

	/**
	* Adds the support response to the database. Also notifies the appropriate model listeners.
	*
	* @param supportResponse the support response
	* @return the support response that was added
	*/
	@Override
	public com.liferay.osb.model.SupportResponse addSupportResponse(
		com.liferay.osb.model.SupportResponse supportResponse) {
		return _supportResponseLocalService.addSupportResponse(supportResponse);
	}

	@Override
	public com.liferay.osb.model.SupportResponse addSupportResponse(
		long userId, java.lang.String name, int supportLevel,
		int severity1Response, int severity1Resolution, int severity2Response,
		int severity2Resolution, int severity3Response, int severity3Resolution)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportResponseLocalService.addSupportResponse(userId, name,
			supportLevel, severity1Response, severity1Resolution,
			severity2Response, severity2Resolution, severity3Response,
			severity3Resolution);
	}

	/**
	* Creates a new support response with the primary key. Does not add the support response to the database.
	*
	* @param supportResponseId the primary key for the new support response
	* @return the new support response
	*/
	@Override
	public com.liferay.osb.model.SupportResponse createSupportResponse(
		long supportResponseId) {
		return _supportResponseLocalService.createSupportResponse(supportResponseId);
	}

	/**
	* Deletes the support response from the database. Also notifies the appropriate model listeners.
	*
	* @param supportResponse the support response
	* @return the support response that was removed
	*/
	@Override
	public com.liferay.osb.model.SupportResponse deleteSupportResponse(
		com.liferay.osb.model.SupportResponse supportResponse) {
		return _supportResponseLocalService.deleteSupportResponse(supportResponse);
	}

	/**
	* Deletes the support response with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response that was removed
	* @throws PortalException if a support response with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportResponse deleteSupportResponse(
		long supportResponseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportResponseLocalService.deleteSupportResponse(supportResponseId);
	}

	@Override
	public com.liferay.osb.model.SupportResponse fetchSupportResponse(
		long supportResponseId) {
		return _supportResponseLocalService.fetchSupportResponse(supportResponseId);
	}

	@Override
	public com.liferay.osb.model.SupportResponse fetchSupportResponseByName(
		java.lang.String name) {
		return _supportResponseLocalService.fetchSupportResponseByName(name);
	}

	/**
	* Returns the support response with the primary key.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response
	* @throws PortalException if a support response with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportResponse getSupportResponse(
		long supportResponseId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportResponseLocalService.getSupportResponse(supportResponseId);
	}

	@Override
	public com.liferay.osb.model.SupportResponse getSupportResponseByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportResponseLocalService.getSupportResponseByName(name);
	}

	/**
	* Updates the support response in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportResponse the support response
	* @return the support response that was updated
	*/
	@Override
	public com.liferay.osb.model.SupportResponse updateSupportResponse(
		com.liferay.osb.model.SupportResponse supportResponse) {
		return _supportResponseLocalService.updateSupportResponse(supportResponse);
	}

	@Override
	public com.liferay.osb.model.SupportResponse updateSupportResponse(
		long supportResponseId, java.lang.String name, int supportLevel,
		int severity1Response, int severity1Resolution, int severity2Response,
		int severity2Resolution, int severity3Response, int severity3Resolution)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportResponseLocalService.updateSupportResponse(supportResponseId,
			name, supportLevel, severity1Response, severity1Resolution,
			severity2Response, severity2Resolution, severity3Response,
			severity3Resolution);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _supportResponseLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportResponseLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _supportResponseLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportResponseLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportResponseLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of support responses.
	*
	* @return the number of support responses
	*/
	@Override
	public int getSupportResponsesCount() {
		return _supportResponseLocalService.getSupportResponsesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportResponseLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _supportResponseLocalService.getOSGiServiceIdentifier();
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
		return _supportResponseLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _supportResponseLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _supportResponseLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the support responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @return the range of support responses
	*/
	@Override
	public java.util.List<com.liferay.osb.model.SupportResponse> getSupportResponses(
		int start, int end) {
		return _supportResponseLocalService.getSupportResponses(start, end);
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
		return _supportResponseLocalService.dynamicQueryCount(dynamicQuery);
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
		return _supportResponseLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public SupportResponseLocalService getWrappedService() {
		return _supportResponseLocalService;
	}

	@Override
	public void setWrappedService(
		SupportResponseLocalService supportResponseLocalService) {
		_supportResponseLocalService = supportResponseLocalService;
	}

	private SupportResponseLocalService _supportResponseLocalService;
}