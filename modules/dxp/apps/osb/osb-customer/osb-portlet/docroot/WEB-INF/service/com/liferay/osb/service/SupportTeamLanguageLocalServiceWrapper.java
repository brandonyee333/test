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
 * This class is a wrapper for {@link SupportTeamLanguageLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportTeamLanguageLocalService
 * @generated
 */
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
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeamLanguage addSupportTeamLanguage(
		com.liferay.osb.model.SupportTeamLanguage supportTeamLanguage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLanguageLocalService.addSupportTeamLanguage(supportTeamLanguage);
	}

	/**
	* Creates a new support team language with the primary key. Does not add the support team language to the database.
	*
	* @param supportTeamLanguageId the primary key for the new support team language
	* @return the new support team language
	*/
	public com.liferay.osb.model.SupportTeamLanguage createSupportTeamLanguage(
		long supportTeamLanguageId) {
		return _supportTeamLanguageLocalService.createSupportTeamLanguage(supportTeamLanguageId);
	}

	/**
	* Deletes the support team language with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeamLanguageId the primary key of the support team language
	* @return the support team language that was removed
	* @throws PortalException if a support team language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeamLanguage deleteSupportTeamLanguage(
		long supportTeamLanguageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLanguageLocalService.deleteSupportTeamLanguage(supportTeamLanguageId);
	}

	/**
	* Deletes the support team language from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeamLanguage the support team language
	* @return the support team language that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeamLanguage deleteSupportTeamLanguage(
		com.liferay.osb.model.SupportTeamLanguage supportTeamLanguage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLanguageLocalService.deleteSupportTeamLanguage(supportTeamLanguage);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportTeamLanguageLocalService.dynamicQuery();
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
		return _supportTeamLanguageLocalService.dynamicQuery(dynamicQuery);
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
		return _supportTeamLanguageLocalService.dynamicQuery(dynamicQuery,
			start, end);
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
		return _supportTeamLanguageLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
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
		return _supportTeamLanguageLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.SupportTeamLanguage fetchSupportTeamLanguage(
		long supportTeamLanguageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLanguageLocalService.fetchSupportTeamLanguage(supportTeamLanguageId);
	}

	/**
	* Returns the support team language with the primary key.
	*
	* @param supportTeamLanguageId the primary key of the support team language
	* @return the support team language
	* @throws PortalException if a support team language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeamLanguage getSupportTeamLanguage(
		long supportTeamLanguageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLanguageLocalService.getSupportTeamLanguage(supportTeamLanguageId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLanguageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the support team languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support team languages
	* @param end the upper bound of the range of support team languages (not inclusive)
	* @return the range of support team languages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeamLanguage> getSupportTeamLanguages(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLanguageLocalService.getSupportTeamLanguages(start,
			end);
	}

	/**
	* Returns the number of support team languages.
	*
	* @return the number of support team languages
	* @throws SystemException if a system exception occurred
	*/
	public int getSupportTeamLanguagesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLanguageLocalService.getSupportTeamLanguagesCount();
	}

	/**
	* Updates the support team language in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportTeamLanguage the support team language
	* @return the support team language that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeamLanguage updateSupportTeamLanguage(
		com.liferay.osb.model.SupportTeamLanguage supportTeamLanguage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLanguageLocalService.updateSupportTeamLanguage(supportTeamLanguage);
	}

	/**
	* Updates the support team language in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportTeamLanguage the support team language
	* @param merge whether to merge the support team language with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the support team language that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeamLanguage updateSupportTeamLanguage(
		com.liferay.osb.model.SupportTeamLanguage supportTeamLanguage,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLanguageLocalService.updateSupportTeamLanguage(supportTeamLanguage,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _supportTeamLanguageLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_supportTeamLanguageLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportTeamLanguageLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	public java.util.List<com.liferay.osb.model.SupportTeamLanguage> getSupportTeamLanguages(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLanguageLocalService.getSupportTeamLanguages(supportTeamId);
	}

	public void setSupportTeamLanguageIds(long supportTeamId,
		java.lang.String[] languageIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLanguageLocalService.setSupportTeamLanguageIds(supportTeamId,
			languageIds);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SupportTeamLanguageLocalService getWrappedSupportTeamLanguageLocalService() {
		return _supportTeamLanguageLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSupportTeamLanguageLocalService(
		SupportTeamLanguageLocalService supportTeamLanguageLocalService) {
		_supportTeamLanguageLocalService = supportTeamLanguageLocalService;
	}

	public SupportTeamLanguageLocalService getWrappedService() {
		return _supportTeamLanguageLocalService;
	}

	public void setWrappedService(
		SupportTeamLanguageLocalService supportTeamLanguageLocalService) {
		_supportTeamLanguageLocalService = supportTeamLanguageLocalService;
	}

	private SupportTeamLanguageLocalService _supportTeamLanguageLocalService;
}