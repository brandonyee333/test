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
 * Provides a wrapper for {@link CurrencyEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CurrencyEntryLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.osb.model.CurrencyEntry addCurrencyEntry(
		com.liferay.osb.model.CurrencyEntry currencyEntry) {
		return _currencyEntryLocalService.addCurrencyEntry(currencyEntry);
	}

	/**
	* Creates a new currency entry with the primary key. Does not add the currency entry to the database.
	*
	* @param currencyEntryId the primary key for the new currency entry
	* @return the new currency entry
	*/
	@Override
	public com.liferay.osb.model.CurrencyEntry createCurrencyEntry(
		long currencyEntryId) {
		return _currencyEntryLocalService.createCurrencyEntry(currencyEntryId);
	}

	/**
	* Deletes the currency entry from the database. Also notifies the appropriate model listeners.
	*
	* @param currencyEntry the currency entry
	* @return the currency entry that was removed
	*/
	@Override
	public com.liferay.osb.model.CurrencyEntry deleteCurrencyEntry(
		com.liferay.osb.model.CurrencyEntry currencyEntry) {
		return _currencyEntryLocalService.deleteCurrencyEntry(currencyEntry);
	}

	/**
	* Deletes the currency entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry that was removed
	* @throws PortalException if a currency entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.CurrencyEntry deleteCurrencyEntry(
		long currencyEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _currencyEntryLocalService.deleteCurrencyEntry(currencyEntryId);
	}

	@Override
	public com.liferay.osb.model.CurrencyEntry fetchCurrencyEntry(
		long currencyEntryId) {
		return _currencyEntryLocalService.fetchCurrencyEntry(currencyEntryId);
	}

	/**
	* Returns the currency entry with the primary key.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry
	* @throws PortalException if a currency entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.CurrencyEntry getCurrencyEntry(
		long currencyEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _currencyEntryLocalService.getCurrencyEntry(currencyEntryId);
	}

	/**
	* Updates the currency entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param currencyEntry the currency entry
	* @return the currency entry that was updated
	*/
	@Override
	public com.liferay.osb.model.CurrencyEntry updateCurrencyEntry(
		com.liferay.osb.model.CurrencyEntry currencyEntry) {
		return _currencyEntryLocalService.updateCurrencyEntry(currencyEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _currencyEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _currencyEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _currencyEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _currencyEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _currencyEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of currency entries.
	*
	* @return the number of currency entries
	*/
	@Override
	public int getCurrencyEntriesCount() {
		return _currencyEntryLocalService.getCurrencyEntriesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _currencyEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _currencyEntryLocalService.getOSGiServiceIdentifier();
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
		return _currencyEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _currencyEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _currencyEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the currency entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.CurrencyEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of currency entries
	* @param end the upper bound of the range of currency entries (not inclusive)
	* @return the range of currency entries
	*/
	@Override
	public java.util.List<com.liferay.osb.model.CurrencyEntry> getCurrencyEntries(
		int start, int end) {
		return _currencyEntryLocalService.getCurrencyEntries(start, end);
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
		return _currencyEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _currencyEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public CurrencyEntryLocalService getWrappedService() {
		return _currencyEntryLocalService;
	}

	@Override
	public void setWrappedService(
		CurrencyEntryLocalService currencyEntryLocalService) {
		_currencyEntryLocalService = currencyEntryLocalService;
	}

	private CurrencyEntryLocalService _currencyEntryLocalService;
}