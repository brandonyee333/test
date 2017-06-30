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
 * The utility for the currency entry local service. This utility wraps {@link com.liferay.osb.service.impl.CurrencyEntryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CurrencyEntryLocalService
 * @see com.liferay.osb.service.base.CurrencyEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.CurrencyEntryLocalServiceImpl
 * @generated
 */
public class CurrencyEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.CurrencyEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the currency entry to the database. Also notifies the appropriate model listeners.
	*
	* @param currencyEntry the currency entry
	* @return the currency entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry addCurrencyEntry(
		com.liferay.osb.model.CurrencyEntry currencyEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addCurrencyEntry(currencyEntry);
	}

	/**
	* Creates a new currency entry with the primary key. Does not add the currency entry to the database.
	*
	* @param currencyEntryId the primary key for the new currency entry
	* @return the new currency entry
	*/
	public static com.liferay.osb.model.CurrencyEntry createCurrencyEntry(
		long currencyEntryId) {
		return getService().createCurrencyEntry(currencyEntryId);
	}

	/**
	* Deletes the currency entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry that was removed
	* @throws PortalException if a currency entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry deleteCurrencyEntry(
		long currencyEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCurrencyEntry(currencyEntryId);
	}

	/**
	* Deletes the currency entry from the database. Also notifies the appropriate model listeners.
	*
	* @param currencyEntry the currency entry
	* @return the currency entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry deleteCurrencyEntry(
		com.liferay.osb.model.CurrencyEntry currencyEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCurrencyEntry(currencyEntry);
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

	public static com.liferay.osb.model.CurrencyEntry fetchCurrencyEntry(
		long currencyEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchCurrencyEntry(currencyEntryId);
	}

	/**
	* Returns the currency entry with the primary key.
	*
	* @param currencyEntryId the primary key of the currency entry
	* @return the currency entry
	* @throws PortalException if a currency entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry getCurrencyEntry(
		long currencyEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCurrencyEntry(currencyEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.osb.model.CurrencyEntry> getCurrencyEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCurrencyEntries(start, end);
	}

	/**
	* Returns the number of currency entries.
	*
	* @return the number of currency entries
	* @throws SystemException if a system exception occurred
	*/
	public static int getCurrencyEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCurrencyEntriesCount();
	}

	/**
	* Updates the currency entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param currencyEntry the currency entry
	* @return the currency entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry updateCurrencyEntry(
		com.liferay.osb.model.CurrencyEntry currencyEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCurrencyEntry(currencyEntry);
	}

	/**
	* Updates the currency entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param currencyEntry the currency entry
	* @param merge whether to merge the currency entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the currency entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CurrencyEntry updateCurrencyEntry(
		com.liferay.osb.model.CurrencyEntry currencyEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCurrencyEntry(currencyEntry, merge);
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

	public static com.liferay.osb.model.CurrencyEntry addCurrencyEntry(
		long countryId, java.lang.String currencyCode,
		boolean marketplaceEnabled, double marketplaceMinPrice)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCurrencyEntry(countryId, currencyCode,
			marketplaceEnabled, marketplaceMinPrice);
	}

	public static com.liferay.osb.model.CurrencyEntry fetchCurrencyEntry(
		java.lang.String currencyCode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchCurrencyEntry(currencyCode);
	}

	public static java.util.List<com.liferay.osb.model.CurrencyEntry> getCurrencyEntries(
		boolean marketplaceEnabled)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCurrencyEntries(marketplaceEnabled);
	}

	public static com.liferay.osb.model.CurrencyEntry getCurrencyEntry(
		java.lang.String currencyCode)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCurrencyEntry(currencyCode);
	}

	public static com.liferay.osb.model.CurrencyEntry updateCurrencyEntry(
		long currencyEntryId, long countryId, java.lang.String currencyCode,
		boolean marketplaceEnabled, double marketplaceMinPrice)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCurrencyEntry(currencyEntryId, countryId,
			currencyCode, marketplaceEnabled, marketplaceMinPrice);
	}

	public static void clearService() {
		_service = null;
	}

	public static CurrencyEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CurrencyEntryLocalService.class.getName());

			if (invokableLocalService instanceof CurrencyEntryLocalService) {
				_service = (CurrencyEntryLocalService)invokableLocalService;
			}
			else {
				_service = new CurrencyEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(CurrencyEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(CurrencyEntryLocalService service) {
	}

	private static CurrencyEntryLocalService _service;
}