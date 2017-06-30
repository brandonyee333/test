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
 * This class is a wrapper for {@link CurrencyEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CurrencyEntryLocalService
 * @generated
 */
public class CurrencyEntryLocalServiceWrapper
	implements CurrencyEntryLocalService,
		ServiceWrapper<CurrencyEntryLocalService> {
	public CurrencyEntryLocalServiceWrapper(
		CurrencyEntryLocalService currencyEntryLocalService) {
		_currencyEntryLocalService = currencyEntryLocalService;
	}

	/**
	* Adds the currency entry to the database. Also notifies the appropriate model listeners.
	*
	* @param currencyEntry the currency entry
	* @return the currency entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry addCurrencyEntry(
		com.liferay.osb.model.CurrencyEntry currencyEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.addCurrencyEntry(currencyEntry);
	}

	/**
	* Creates a new currency entry with the primary key. Does not add the currency entry to the database.
	*
	* @param currencyEntryId the primary key for the new currency entry
	* @return the new currency entry
	*/
	public com.liferay.osb.model.CurrencyEntry createCurrencyEntry(
		long currencyEntryId) {
		return _currencyEntryLocalService.createCurrencyEntry(currencyEntryId);
	}

	/**
	* Deletes the currency entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry that was removed
	* @throws PortalException if a currency entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry deleteCurrencyEntry(
		long currencyEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.deleteCurrencyEntry(currencyEntryId);
	}

	/**
	* Deletes the currency entry from the database. Also notifies the appropriate model listeners.
	*
	* @param currencyEntry the currency entry
	* @return the currency entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry deleteCurrencyEntry(
		com.liferay.osb.model.CurrencyEntry currencyEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.deleteCurrencyEntry(currencyEntry);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _currencyEntryLocalService.dynamicQuery();
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
		return _currencyEntryLocalService.dynamicQuery(dynamicQuery);
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
		return _currencyEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _currencyEntryLocalService.dynamicQuery(dynamicQuery, start,
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
		return _currencyEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.CurrencyEntry fetchCurrencyEntry(
		long currencyEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.fetchCurrencyEntry(currencyEntryId);
	}

	/**
	* Returns the currency entry with the primary key.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry
	* @throws PortalException if a currency entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry getCurrencyEntry(
		long currencyEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.getCurrencyEntry(currencyEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the currency entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of currency entries
	* @param end the upper bound of the range of currency entries (not inclusive)
	* @return the range of currency entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CurrencyEntry> getCurrencyEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.getCurrencyEntries(start, end);
	}

	/**
	* Returns the number of currency entries.
	*
	* @return the number of currency entries
	* @throws SystemException if a system exception occurred
	*/
	public int getCurrencyEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.getCurrencyEntriesCount();
	}

	/**
	* Updates the currency entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param currencyEntry the currency entry
	* @return the currency entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry updateCurrencyEntry(
		com.liferay.osb.model.CurrencyEntry currencyEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.updateCurrencyEntry(currencyEntry);
	}

	/**
	* Updates the currency entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param currencyEntry the currency entry
	* @param merge whether to merge the currency entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the currency entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CurrencyEntry updateCurrencyEntry(
		com.liferay.osb.model.CurrencyEntry currencyEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.updateCurrencyEntry(currencyEntry,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _currencyEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_currencyEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _currencyEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.CurrencyEntry addCurrencyEntry(
		long countryId, java.lang.String currencyCode,
		boolean marketplaceEnabled, double marketplaceMinPrice)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.addCurrencyEntry(countryId,
			currencyCode, marketplaceEnabled, marketplaceMinPrice);
	}

	public com.liferay.osb.model.CurrencyEntry fetchCurrencyEntry(
		java.lang.String currencyCode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.fetchCurrencyEntry(currencyCode);
	}

	public java.util.List<com.liferay.osb.model.CurrencyEntry> getCurrencyEntries(
		boolean marketplaceEnabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.getCurrencyEntries(marketplaceEnabled);
	}

	public com.liferay.osb.model.CurrencyEntry getCurrencyEntry(
		java.lang.String currencyCode)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.getCurrencyEntry(currencyCode);
	}

	public com.liferay.osb.model.CurrencyEntry updateCurrencyEntry(
		long currencyEntryId, long countryId, java.lang.String currencyCode,
		boolean marketplaceEnabled, double marketplaceMinPrice)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _currencyEntryLocalService.updateCurrencyEntry(currencyEntryId,
			countryId, currencyCode, marketplaceEnabled, marketplaceMinPrice);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CurrencyEntryLocalService getWrappedCurrencyEntryLocalService() {
		return _currencyEntryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCurrencyEntryLocalService(
		CurrencyEntryLocalService currencyEntryLocalService) {
		_currencyEntryLocalService = currencyEntryLocalService;
	}

	public CurrencyEntryLocalService getWrappedService() {
		return _currencyEntryLocalService;
	}

	public void setWrappedService(
		CurrencyEntryLocalService currencyEntryLocalService) {
		_currencyEntryLocalService = currencyEntryLocalService;
	}

	private CurrencyEntryLocalService _currencyEntryLocalService;
}