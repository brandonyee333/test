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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for OfferingEntry. This utility wraps
 * {@link com.liferay.osb.service.impl.OfferingEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingEntryLocalService
 * @see com.liferay.osb.service.base.OfferingEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.OfferingEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class OfferingEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.OfferingEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasActiveSupportOfferingEntry(long accountEntryId) {
		return getService().hasActiveSupportOfferingEntry(accountEntryId);
	}

	public static boolean hasActiveTrialOfferingEntry(long userId) {
		return getService().hasActiveTrialOfferingEntry(userId);
	}

	/**
	* Adds the offering entry to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was added
	*/
	public static com.liferay.osb.model.OfferingEntry addOfferingEntry(
		com.liferay.osb.model.OfferingEntry offeringEntry) {
		return getService().addOfferingEntry(offeringEntry);
	}

	public static com.liferay.osb.model.OfferingEntry addOfferingEntry(
		long userId, long accountEntryId, long orderEntryId,
		long productEntryId, long supportResponseId,
		java.lang.String productDescription, int type, int version,
		boolean licenses, long licenseLifetime, long maxConcurrentUsers,
		long maxUsers, boolean supportTickets, long supportLifetime,
		int sizing, int quantity, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addOfferingEntry(userId, accountEntryId, orderEntryId,
			productEntryId, supportResponseId, productDescription, type,
			version, licenses, licenseLifetime, maxConcurrentUsers, maxUsers,
			supportTickets, supportLifetime, sizing, quantity, status);
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
	* Deletes the offering entry from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was removed
	* @throws PortalException
	*/
	public static com.liferay.osb.model.OfferingEntry deleteOfferingEntry(
		com.liferay.osb.model.OfferingEntry offeringEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOfferingEntry(offeringEntry);
	}

	/**
	* Deletes the offering entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry that was removed
	* @throws PortalException if a offering entry with the primary key could not be found
	*/
	public static com.liferay.osb.model.OfferingEntry deleteOfferingEntry(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOfferingEntry(offeringEntryId);
	}

	public static com.liferay.osb.model.OfferingEntry fetchOfferingEntry(
		long offeringEntryId) {
		return getService().fetchOfferingEntry(offeringEntryId);
	}

	/**
	* Returns the offering entry with the primary key.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry
	* @throws PortalException if a offering entry with the primary key could not be found
	*/
	public static com.liferay.osb.model.OfferingEntry getOfferingEntry(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOfferingEntry(offeringEntryId);
	}

	/**
	* Updates the offering entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringEntry the offering entry
	* @return the offering entry that was updated
	*/
	public static com.liferay.osb.model.OfferingEntry updateOfferingEntry(
		com.liferay.osb.model.OfferingEntry offeringEntry) {
		return getService().updateOfferingEntry(offeringEntry);
	}

	public static com.liferay.osb.model.OfferingEntry updateOfferingEntry(
		long userId, long offeringEntryId, long accountEntryId,
		long orderEntryId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, int type, int version,
		boolean licenses, long licenseLifetime, long maxConcurrentUsers,
		long maxUsers, boolean supportTickets, long supportLifetime,
		int sizing, int quantity)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateOfferingEntry(userId, offeringEntryId,
			accountEntryId, orderEntryId, productEntryId, supportResponseId,
			productDescription, type, version, licenses, licenseLifetime,
			maxConcurrentUsers, maxUsers, supportTickets, supportLifetime,
			sizing, quantity);
	}

	public static com.liferay.osb.model.OfferingEntry updateStatus(
		long userId, long offeringEntryId, int status)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateStatus(userId, offeringEntryId, status);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static int getAccountEntryOfferingEntriesCount(long accountEntryId) {
		return getService().getAccountEntryOfferingEntriesCount(accountEntryId);
	}

	/**
	* Returns the number of offering entries.
	*
	* @return the number of offering entries
	*/
	public static int getOfferingEntriesCount() {
		return getService().getOfferingEntriesCount();
	}

	public static int searchCount(long userId, long accountEntryId,
		int[] types, int[] statuses, int supportEndDateGTDay,
		int supportEndDateGTMonth, int supportEndDateGTYear,
		int supportEndDateLTDay, int supportEndDateLTMonth,
		int supportEndDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch) {
		return getService()
				   .searchCount(userId, accountEntryId, types, statuses,
			supportEndDateGTDay, supportEndDateGTMonth, supportEndDateGTYear,
			supportEndDateLTDay, supportEndDateLTMonth, supportEndDateLTYear,
			params, andSearch);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.osb.model.OfferingEntry> getAccountEntryOfferingEntries(
		long accountEntryId) {
		return getService().getAccountEntryOfferingEntries(accountEntryId);
	}

	/**
	* Returns a range of all the offering entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of offering entries
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> getOfferingEntries(
		int start, int end) {
		return getService().getOfferingEntries(start, end);
	}

	public static java.util.List<com.liferay.osb.model.OfferingEntry> getOrderEntryOfferingEntries(
		long orderEntryId) {
		return getService().getOrderEntryOfferingEntries(orderEntryId);
	}

	public static java.util.List<com.liferay.osb.model.OfferingEntry> search(
		long userId, long accountEntryId, int[] types, int[] statuses,
		int supportEndDateGTDay, int supportEndDateGTMonth,
		int supportEndDateGTYear, int supportEndDateLTDay,
		int supportEndDateLTMonth, int supportEndDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .search(userId, accountEntryId, types, statuses,
			supportEndDateGTDay, supportEndDateGTMonth, supportEndDateGTYear,
			supportEndDateLTDay, supportEndDateLTMonth, supportEndDateLTYear,
			params, andSearch, start, end, obc);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void checkOfferingEntries() throws java.lang.Exception {
		getService().checkOfferingEntries();
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

	private static OfferingEntryLocalService _service;
}