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
 * Provides a wrapper for {@link SupportTeamLanguageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamLanguageLocalService
 * @generated
 */
@ProviderType
public class SupportTeamLanguageLocalServiceWrapper
	implements SupportTeamLanguageLocalService,
		ServiceWrapper<SupportTeamLanguageLocalService> {
	public SupportTeamLanguageLocalServiceWrapper(
		SupportTeamLanguageLocalService supportTeamLanguageLocalService) {
		_supportTeamLanguageLocalService = supportTeamLanguageLocalService;
	}

	/**
	* Adds the support team language to the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeamLanguage the support team language
	* @return the support team language that was added
	*/
	@Override
	public com.liferay.osb.model.SupportTeamLanguage addSupportTeamLanguage(
		com.liferay.osb.model.SupportTeamLanguage supportTeamLanguage) {
		return _supportTeamLanguageLocalService.addSupportTeamLanguage(supportTeamLanguage);
	}

	/**
	* Creates a new support team language with the primary key. Does not add the support team language to the database.
	*
	* @param supportTeamLanguageId the primary key for the new support team language
	* @return the new support team language
	*/
	@Override
	public com.liferay.osb.model.SupportTeamLanguage createSupportTeamLanguage(
		long supportTeamLanguageId) {
		return _supportTeamLanguageLocalService.createSupportTeamLanguage(supportTeamLanguageId);
	}

	/**
	* Deletes the support team language from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeamLanguage the support team language
	* @return the support team language that was removed
	*/
	@Override
	public com.liferay.osb.model.SupportTeamLanguage deleteSupportTeamLanguage(
		com.liferay.osb.model.SupportTeamLanguage supportTeamLanguage) {
		return _supportTeamLanguageLocalService.deleteSupportTeamLanguage(supportTeamLanguage);
	}

	/**
	* Deletes the support team language with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeamLanguageId the primary key of the support team language
	* @return the support team language that was removed
	* @throws PortalException if a support team language with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportTeamLanguage deleteSupportTeamLanguage(
		long supportTeamLanguageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportTeamLanguageLocalService.deleteSupportTeamLanguage(supportTeamLanguageId);
	}

	@Override
	public com.liferay.osb.model.SupportTeamLanguage fetchSupportTeamLanguage(
		long supportTeamLanguageId) {
		return _supportTeamLanguageLocalService.fetchSupportTeamLanguage(supportTeamLanguageId);
	}

	/**
	* Returns the support team language with the primary key.
	*
	* @param supportTeamLanguageId the primary key of the support team language
	* @return the support team language
	* @throws PortalException if a support team language with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportTeamLanguage getSupportTeamLanguage(
		long supportTeamLanguageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportTeamLanguageLocalService.getSupportTeamLanguage(supportTeamLanguageId);
	}

	/**
	* Updates the support team language in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportTeamLanguage the support team language
	* @return the support team language that was updated
	*/
	@Override
	public com.liferay.osb.model.SupportTeamLanguage updateSupportTeamLanguage(
		com.liferay.osb.model.SupportTeamLanguage supportTeamLanguage) {
		return _supportTeamLanguageLocalService.updateSupportTeamLanguage(supportTeamLanguage);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _supportTeamLanguageLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportTeamLanguageLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _supportTeamLanguageLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportTeamLanguageLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportTeamLanguageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of support team languages.
	*
	* @return the number of support team languages
	*/
	@Override
	public int getSupportTeamLanguagesCount() {
		return _supportTeamLanguageLocalService.getSupportTeamLanguagesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportTeamLanguageLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _supportTeamLanguageLocalService.getOSGiServiceIdentifier();
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
		return _supportTeamLanguageLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _supportTeamLanguageLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _supportTeamLanguageLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the support team languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support team languages
	* @param end the upper bound of the range of support team languages (not inclusive)
	* @return the range of support team languages
	*/
	@Override
	public java.util.List<com.liferay.osb.model.SupportTeamLanguage> getSupportTeamLanguages(
		int start, int end) {
		return _supportTeamLanguageLocalService.getSupportTeamLanguages(start,
			end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportTeamLanguage> getSupportTeamLanguages(
		long supportTeamId) {
		return _supportTeamLanguageLocalService.getSupportTeamLanguages(supportTeamId);
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
		return _supportTeamLanguageLocalService.dynamicQueryCount(dynamicQuery);
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
		return _supportTeamLanguageLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void setSupportTeamLanguageIds(long supportTeamId,
		java.lang.String[] languageIds) {
		_supportTeamLanguageLocalService.setSupportTeamLanguageIds(supportTeamId,
			languageIds);
	}

	@Override
	public SupportTeamLanguageLocalService getWrappedService() {
		return _supportTeamLanguageLocalService;
	}

	@Override
	public void setWrappedService(
		SupportTeamLanguageLocalService supportTeamLanguageLocalService) {
		_supportTeamLanguageLocalService = supportTeamLanguageLocalService;
	}

	private SupportTeamLanguageLocalService _supportTeamLanguageLocalService;
}