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
 * This class is a wrapper for {@link AppPricingLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AppPricingLocalService
 * @generated
 */
public class AppPricingLocalServiceWrapper implements AppPricingLocalService,
	ServiceWrapper<AppPricingLocalService> {
	public AppPricingLocalServiceWrapper(
		AppPricingLocalService appPricingLocalService) {
		_appPricingLocalService = appPricingLocalService;
	}

	/**
	* Adds the app pricing to the database. Also notifies the appropriate model listeners.
	*
	* @param appPricing the app pricing
	* @return the app pricing that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricing addAppPricing(
		com.liferay.osb.model.AppPricing appPricing)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPricingLocalService.addAppPricing(appPricing);
	}

	/**
	* Creates a new app pricing with the primary key. Does not add the app pricing to the database.
	*
	* @param appPricingId the primary key for the new app pricing
	* @return the new app pricing
	*/
	public com.liferay.osb.model.AppPricing createAppPricing(long appPricingId) {
		return _appPricingLocalService.createAppPricing(appPricingId);
	}

	/**
	* Deletes the app pricing with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appPricingId the primary key of the app pricing
	* @return the app pricing that was removed
	* @throws PortalException if a app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricing deleteAppPricing(long appPricingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPricingLocalService.deleteAppPricing(appPricingId);
	}

	/**
	* Deletes the app pricing from the database. Also notifies the appropriate model listeners.
	*
	* @param appPricing the app pricing
	* @return the app pricing that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricing deleteAppPricing(
		com.liferay.osb.model.AppPricing appPricing)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPricingLocalService.deleteAppPricing(appPricing);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _appPricingLocalService.dynamicQuery();
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
		return _appPricingLocalService.dynamicQuery(dynamicQuery);
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
		return _appPricingLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _appPricingLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _appPricingLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AppPricing fetchAppPricing(long appPricingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPricingLocalService.fetchAppPricing(appPricingId);
	}

	/**
	* Returns the app pricing with the primary key.
	*
	* @param appPricingId the primary key of the app pricing
	* @return the app pricing
	* @throws PortalException if a app pricing with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricing getAppPricing(long appPricingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPricingLocalService.getAppPricing(appPricingId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPricingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the app pricings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app pricings
	* @param end the upper bound of the range of app pricings (not inclusive)
	* @return the range of app pricings
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AppPricing> getAppPricings(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPricingLocalService.getAppPricings(start, end);
	}

	/**
	* Returns the number of app pricings.
	*
	* @return the number of app pricings
	* @throws SystemException if a system exception occurred
	*/
	public int getAppPricingsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPricingLocalService.getAppPricingsCount();
	}

	/**
	* Updates the app pricing in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appPricing the app pricing
	* @return the app pricing that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricing updateAppPricing(
		com.liferay.osb.model.AppPricing appPricing)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPricingLocalService.updateAppPricing(appPricing);
	}

	/**
	* Updates the app pricing in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appPricing the app pricing
	* @param merge whether to merge the app pricing with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the app pricing that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppPricing updateAppPricing(
		com.liferay.osb.model.AppPricing appPricing, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPricingLocalService.updateAppPricing(appPricing, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _appPricingLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_appPricingLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _appPricingLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.AppPricing addAppPricing(long userId,
		long appVersionId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPricingLocalService.addAppPricing(userId, appVersionId, name);
	}

	public void copyAppPricings(long sourceAppVersionId, long targetAppVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appPricingLocalService.copyAppPricings(sourceAppVersionId,
			targetAppVersionId);
	}

	public void deleteAppPricingByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appPricingLocalService.deleteAppPricingByAppVersionId(appVersionId);
	}

	public java.util.List<com.liferay.osb.model.AppPricing> getAppPricings(
		long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _appPricingLocalService.getAppPricings(appVersionId);
	}

	public com.liferay.osb.model.AppPricing updateAppPricing(
		long appPricingId, java.lang.String name, long currencyEntryId,
		double standardSupportPrice, double developerSupportPrice)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _appPricingLocalService.updateAppPricing(appPricingId, name,
			currencyEntryId, standardSupportPrice, developerSupportPrice);
	}

	public void validateAppPricings(long appVersionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_appPricingLocalService.validateAppPricings(appVersionId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AppPricingLocalService getWrappedAppPricingLocalService() {
		return _appPricingLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAppPricingLocalService(
		AppPricingLocalService appPricingLocalService) {
		_appPricingLocalService = appPricingLocalService;
	}

	public AppPricingLocalService getWrappedService() {
		return _appPricingLocalService;
	}

	public void setWrappedService(AppPricingLocalService appPricingLocalService) {
		_appPricingLocalService = appPricingLocalService;
	}

	private AppPricingLocalService _appPricingLocalService;
}