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
 * Provides the local service utility for OrderEntry. This utility wraps
 * {@link com.liferay.osb.service.impl.OrderEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see OrderEntryLocalService
 * @see com.liferay.osb.service.base.OrderEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.OrderEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class OrderEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.OrderEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the order entry to the database. Also notifies the appropriate model listeners.
	*
	* @param orderEntry the order entry
	* @return the order entry that was added
	*/
	public static com.liferay.osb.model.OrderEntry addOrderEntry(
		com.liferay.osb.model.OrderEntry orderEntry) {
		return getService().addOrderEntry(orderEntry);
	}

	public static com.liferay.osb.model.OrderEntry addOrderEntry(long userId,
		long accountEntryId, java.lang.String purchaseOrderKey,
		int startDateMonth, int startDateDay, int startDateYear,
		boolean prorated, int actualStartDateMonth, int actualStartDateDay,
		int actualStartDateYear, int status,
		java.lang.String salesforceOpportunityKey,
		java.util.List<com.liferay.osb.model.OfferingEntry> offeringEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addOrderEntry(userId, accountEntryId, purchaseOrderKey,
			startDateMonth, startDateDay, startDateYear, prorated,
			actualStartDateMonth, actualStartDateDay, actualStartDateYear,
			status, salesforceOpportunityKey, offeringEntries);
	}

	/**
	* Creates a new order entry with the primary key. Does not add the order entry to the database.
	*
	* @param orderEntryId the primary key for the new order entry
	* @return the new order entry
	*/
	public static com.liferay.osb.model.OrderEntry createOrderEntry(
		long orderEntryId) {
		return getService().createOrderEntry(orderEntryId);
	}

	/**
	* Deletes the order entry from the database. Also notifies the appropriate model listeners.
	*
	* @param orderEntry the order entry
	* @return the order entry that was removed
	* @throws PortalException
	*/
	public static com.liferay.osb.model.OrderEntry deleteOrderEntry(
		com.liferay.osb.model.OrderEntry orderEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOrderEntry(orderEntry);
	}

	/**
	* Deletes the order entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param orderEntryId the primary key of the order entry
	* @return the order entry that was removed
	* @throws PortalException if a order entry with the primary key could not be found
	*/
	public static com.liferay.osb.model.OrderEntry deleteOrderEntry(
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOrderEntry(orderEntryId);
	}

	public static com.liferay.osb.model.OrderEntry fetchOrderEntry(
		long orderEntryId) {
		return getService().fetchOrderEntry(orderEntryId);
	}

	public static com.liferay.osb.model.OrderEntry getOrderEntry(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOrderEntry(uuid);
	}

	/**
	* Returns the order entry with the primary key.
	*
	* @param orderEntryId the primary key of the order entry
	* @return the order entry
	* @throws PortalException if a order entry with the primary key could not be found
	*/
	public static com.liferay.osb.model.OrderEntry getOrderEntry(
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOrderEntry(orderEntryId);
	}

	public static com.liferay.osb.model.OrderEntry renewOrderEntry(
		long userId, long orderEntryId, int renewCount)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().renewOrderEntry(userId, orderEntryId, renewCount);
	}

	/**
	* Updates the order entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param orderEntry the order entry
	* @return the order entry that was updated
	*/
	public static com.liferay.osb.model.OrderEntry updateOrderEntry(
		com.liferay.osb.model.OrderEntry orderEntry) {
		return getService().updateOrderEntry(orderEntry);
	}

	public static com.liferay.osb.model.OrderEntry updateOrderEntry(
		long userId, long orderEntryId, long accountEntryId,
		java.lang.String purchaseOrderKey, int startDateMonth,
		int startDateDay, int startDateYear, boolean prorated,
		int actualStartDateMonth, int actualStartDateDay,
		int actualStartDateYear, java.lang.String salesforceOpportunityKey,
		java.util.List<com.liferay.osb.model.OfferingEntry> offeringEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateOrderEntry(userId, orderEntryId, accountEntryId,
			purchaseOrderKey, startDateMonth, startDateDay, startDateYear,
			prorated, actualStartDateMonth, actualStartDateDay,
			actualStartDateYear, salesforceOpportunityKey, offeringEntries);
	}

	public static com.liferay.osb.model.OrderEntry updateStatus(long userId,
		long orderEntryId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(userId, orderEntryId, status, serviceContext);
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

	/**
	* Returns the number of order entries.
	*
	* @return the number of order entries
	*/
	public static int getOrderEntriesCount() {
		return getService().getOrderEntriesCount();
	}

	public static int searchCount(java.lang.Long createUserId,
		int createDateGTDay, int createDateGTMonth, int createDateGTYear,
		int createDateLTDay, int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		java.lang.Long accountEntryId, java.lang.String purchaseOrderKey,
		int[] statuses, int startDateGTDay, int startDateGTMonth,
		int startDateGTYear, int startDateLTDay, int startDateLTMonth,
		int startDateLTYear, java.lang.Boolean prorated,
		int actualStartDateGTDay, int actualStartDateGTMonth,
		int actualStartDateGTYear, int actualStartDateLTDay,
		int actualStartDateLTMonth, int actualStartDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return getService()
				   .searchCount(createUserId, createDateGTDay,
			createDateGTMonth, createDateGTYear, createDateLTDay,
			createDateLTMonth, createDateLTYear, modifiedUserId,
			modifiedDateGTDay, modifiedDateGTMonth, modifiedDateGTYear,
			modifiedDateLTDay, modifiedDateLTMonth, modifiedDateLTYear,
			accountEntryId, purchaseOrderKey, statuses, startDateGTDay,
			startDateGTMonth, startDateGTYear, startDateLTDay,
			startDateLTMonth, startDateLTYear, prorated, actualStartDateGTDay,
			actualStartDateGTMonth, actualStartDateGTYear,
			actualStartDateLTDay, actualStartDateLTMonth,
			actualStartDateLTYear, params, andOperator);
	}

	public static int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getService().searchCount(keywords, params);
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

	public static java.util.List<com.liferay.osb.model.OrderEntry> addOrderEntriesWithWorkflow(
		java.lang.String salesforceOpportunityKey,
		com.liferay.osb.model.AccountEntry accountEntry,
		com.liferay.osb.model.CorpProject corpProject,
		com.liferay.osb.model.PartnerEntry partnerEntry,
		com.liferay.portal.kernel.model.Address address,
		com.liferay.osb.model.AccountWorker accountWorker,
		java.util.List<com.liferay.osb.model.OrderEntry> orderEntries,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addOrderEntriesWithWorkflow(salesforceOpportunityKey,
			accountEntry, corpProject, partnerEntry, address, accountWorker,
			orderEntries, serviceContext);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OrderEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OrderEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.liferay.osb.model.OrderEntry> getAccountEntryOrderEntries(
		long accountEntryId) {
		return getService().getAccountEntryOrderEntries(accountEntryId);
	}

	/**
	* Returns a range of all the order entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OrderEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of order entries
	* @param end the upper bound of the range of order entries (not inclusive)
	* @return the range of order entries
	*/
	public static java.util.List<com.liferay.osb.model.OrderEntry> getOrderEntries(
		int start, int end) {
		return getService().getOrderEntries(start, end);
	}

	public static java.util.List<com.liferay.osb.model.OrderEntry> search(
		java.lang.Long createUserId, int createDateGTDay,
		int createDateGTMonth, int createDateGTYear, int createDateLTDay,
		int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		java.lang.Long accountEntryId, java.lang.String purchaseOrderKey,
		int[] statuses, int startDateGTDay, int startDateGTMonth,
		int startDateGTYear, int startDateLTDay, int startDateLTMonth,
		int startDateLTYear, java.lang.Boolean prorated,
		int actualStartDateGTDay, int actualStartDateGTMonth,
		int actualStartDateGTYear, int actualStartDateLTDay,
		int actualStartDateLTMonth, int actualStartDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .search(createUserId, createDateGTDay, createDateGTMonth,
			createDateGTYear, createDateLTDay, createDateLTMonth,
			createDateLTYear, modifiedUserId, modifiedDateGTDay,
			modifiedDateGTMonth, modifiedDateGTYear, modifiedDateLTDay,
			modifiedDateLTMonth, modifiedDateLTYear, accountEntryId,
			purchaseOrderKey, statuses, startDateGTDay, startDateGTMonth,
			startDateGTYear, startDateLTDay, startDateLTMonth, startDateLTYear,
			prorated, actualStartDateGTDay, actualStartDateGTMonth,
			actualStartDateGTYear, actualStartDateLTDay,
			actualStartDateLTMonth, actualStartDateLTYear, params, andOperator,
			start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.OrderEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService().search(keywords, params, start, end, obc);
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

	public static void checkOrderEntries() {
		getService().checkOrderEntries();
	}

	public static void clearService() {
		_service = null;
	}

	public static OrderEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					OrderEntryLocalService.class.getName());

			if (invokableLocalService instanceof OrderEntryLocalService) {
				_service = (OrderEntryLocalService)invokableLocalService;
			}
			else {
				_service = new OrderEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(OrderEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static OrderEntryLocalService _service;
}