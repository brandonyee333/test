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
 * The utility for the offering entry local service. This utility wraps {@link com.liferay.osb.service.impl.OfferingEntryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingEntryLocalService
 * @see com.liferay.osb.service.base.OfferingEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.OfferingEntryLocalServiceImpl
 * @generated
 */
public class OfferingEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.OfferingEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the offering entry to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry addOfferingEntry(
		com.liferay.osb.model.OfferingEntry offeringEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addOfferingEntry(offeringEntry);
	}

	/**
	* Creates a new offering entry with the primary key. Does not add the offering entry to the database.
	*
	* @param offeringEntryId the primary key for the new offering entry
	* @return the new offering entry
	*/
	public static com.liferay.osb.model.OfferingEntry createOfferingEntry(
		long offeringEntryId) {
		return getService().createOfferingEntry(offeringEntryId);
	}

	/**
	* Deletes the offering entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry that was removed
	* @throws PortalException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry deleteOfferingEntry(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOfferingEntry(offeringEntryId);
	}

	/**
	* Deletes the offering entry from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry deleteOfferingEntry(
		com.liferay.osb.model.OfferingEntry offeringEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOfferingEntry(offeringEntry);
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

	public static com.liferay.osb.model.OfferingEntry fetchOfferingEntry(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchOfferingEntry(offeringEntryId);
	}

	/**
	* Returns the offering entry with the primary key.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry
	* @throws PortalException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry getOfferingEntry(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOfferingEntry(offeringEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the offering entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> getOfferingEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOfferingEntries(start, end);
	}

	/**
	* Returns the number of offering entries.
	*
	* @return the number of offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static int getOfferingEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOfferingEntriesCount();
	}

	/**
	* Updates the offering entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry updateOfferingEntry(
		com.liferay.osb.model.OfferingEntry offeringEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOfferingEntry(offeringEntry);
	}

	/**
	* Updates the offering entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @param merge whether to merge the offering entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the offering entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry updateOfferingEntry(
		com.liferay.osb.model.OfferingEntry offeringEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOfferingEntry(offeringEntry, merge);
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

	public static com.liferay.osb.model.OfferingEntry addOfferingEntry(
		long userId, long accountEntryId, long orderEntryId,
		long productEntryId, long supportResponseId,
		java.lang.String productDescription, int type, int version,
		boolean licenses, long licenseLifetime, long maxConcurrentUsers,
		long maxUsers, boolean supportTickets, long supportLifetime,
		int sizing, int quantity, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addOfferingEntry(userId, accountEntryId, orderEntryId,
			productEntryId, supportResponseId, productDescription, type,
			version, licenses, licenseLifetime, maxConcurrentUsers, maxUsers,
			supportTickets, supportLifetime, sizing, quantity, status);
	}

	public static void checkOfferingEntries() throws java.lang.Exception {
		getService().checkOfferingEntries();
	}

	public static java.util.List<com.liferay.osb.model.OfferingEntry> getAccountEntryOfferingEntries(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntryOfferingEntries(accountEntryId);
	}

	public static int getAccountEntryOfferingEntriesCount(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntryOfferingEntriesCount(accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.OfferingEntry> getOrderEntryOfferingEntries(
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrderEntryOfferingEntries(orderEntryId);
	}

	public static boolean hasActiveTrialOfferingEntry(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasActiveTrialOfferingEntry(userId);
	}

	public static java.util.List<com.liferay.osb.model.OfferingEntry> search(
		long userId, long accountEntryId, int[] types, int[] statuses,
		int supportEndDateGTDay, int supportEndDateGTMonth,
		int supportEndDateGTYear, int supportEndDateLTDay,
		int supportEndDateLTMonth, int supportEndDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(userId, accountEntryId, types, statuses,
			supportEndDateGTDay, supportEndDateGTMonth, supportEndDateGTYear,
			supportEndDateLTDay, supportEndDateLTMonth, supportEndDateLTYear,
			params, andSearch, start, end, obc);
	}

	public static int searchCount(long userId, long accountEntryId,
		int[] types, int[] statuses, int supportEndDateGTDay,
		int supportEndDateGTMonth, int supportEndDateGTYear,
		int supportEndDateLTDay, int supportEndDateLTMonth,
		int supportEndDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(userId, accountEntryId, types, statuses,
			supportEndDateGTDay, supportEndDateGTMonth, supportEndDateGTYear,
			supportEndDateLTDay, supportEndDateLTMonth, supportEndDateLTYear,
			params, andSearch);
	}

	public static com.liferay.osb.model.OfferingEntry updateOfferingEntry(
		long userId, long offeringEntryId, long accountEntryId,
		long orderEntryId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, int type, int version,
		boolean licenses, long licenseLifetime, long maxConcurrentUsers,
		long maxUsers, boolean supportTickets, long supportLifetime,
		int sizing, int quantity)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateOfferingEntry(userId, offeringEntryId,
			accountEntryId, orderEntryId, productEntryId, supportResponseId,
			productDescription, type, version, licenses, licenseLifetime,
			maxConcurrentUsers, maxUsers, supportTickets, supportLifetime,
			sizing, quantity);
	}

	public static com.liferay.osb.model.OfferingEntry updateStatus(
		long userId, long offeringEntryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateStatus(userId, offeringEntryId, status);
	}

	public static void clearService() {
		_service = null;
	}

	public static OfferingEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					OfferingEntryLocalService.class.getName());

			if (invokableLocalService instanceof OfferingEntryLocalService) {
				_service = (OfferingEntryLocalService)invokableLocalService;
			}
			else {
				_service = new OfferingEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(OfferingEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(OfferingEntryLocalService service) {
	}

	private static OfferingEntryLocalService _service;
}