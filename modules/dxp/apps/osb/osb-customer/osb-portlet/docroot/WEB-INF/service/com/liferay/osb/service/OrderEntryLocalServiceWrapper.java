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
 * This class is a wrapper for {@link OrderEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OrderEntryLocalService
 * @generated
 */
public class OrderEntryLocalServiceWrapper implements OrderEntryLocalService,
	ServiceWrapper<OrderEntryLocalService> {
	public OrderEntryLocalServiceWrapper(
		OrderEntryLocalService orderEntryLocalService) {
		_orderEntryLocalService = orderEntryLocalService;
	}

	/**
	* Adds the order entry to the database. Also notifies the appropriate model listeners.
	*
	* @param orderEntry the order entry
	* @return the order entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry addOrderEntry(
		com.liferay.osb.model.OrderEntry orderEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.addOrderEntry(orderEntry);
	}

	/**
	* Creates a new order entry with the primary key. Does not add the order entry to the database.
	*
	* @param orderEntryId the primary key for the new order entry
	* @return the new order entry
	*/
	public com.liferay.osb.model.OrderEntry createOrderEntry(long orderEntryId) {
		return _orderEntryLocalService.createOrderEntry(orderEntryId);
	}

	/**
	* Deletes the order entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param orderEntryId the primary key of the order entry
	* @return the order entry that was removed
	* @throws PortalException if a order entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry deleteOrderEntry(long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.deleteOrderEntry(orderEntryId);
	}

	/**
	* Deletes the order entry from the database. Also notifies the appropriate model listeners.
	*
	* @param orderEntry the order entry
	* @return the order entry that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry deleteOrderEntry(
		com.liferay.osb.model.OrderEntry orderEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.deleteOrderEntry(orderEntry);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _orderEntryLocalService.dynamicQuery();
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
		return _orderEntryLocalService.dynamicQuery(dynamicQuery);
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
		return _orderEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _orderEntryLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _orderEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.OrderEntry fetchOrderEntry(long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.fetchOrderEntry(orderEntryId);
	}

	/**
	* Returns the order entry with the primary key.
	*
	* @param orderEntryId the primary key of the order entry
	* @return the order entry
	* @throws PortalException if a order entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry getOrderEntry(long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.getOrderEntry(orderEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the order entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of order entries
	* @param end the upper bound of the range of order entries (not inclusive)
	* @return the range of order entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.OrderEntry> getOrderEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.getOrderEntries(start, end);
	}

	/**
	* Returns the number of order entries.
	*
	* @return the number of order entries
	* @throws SystemException if a system exception occurred
	*/
	public int getOrderEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.getOrderEntriesCount();
	}

	/**
	* Updates the order entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param orderEntry the order entry
	* @return the order entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry updateOrderEntry(
		com.liferay.osb.model.OrderEntry orderEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.updateOrderEntry(orderEntry);
	}

	/**
	* Updates the order entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param orderEntry the order entry
	* @param merge whether to merge the order entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the order entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OrderEntry updateOrderEntry(
		com.liferay.osb.model.OrderEntry orderEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.updateOrderEntry(orderEntry, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _orderEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_orderEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _orderEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public java.util.List<com.liferay.osb.model.OrderEntry> addOrderEntriesWithWorkflow(
		java.lang.String salesforceOpportunityKey,
		com.liferay.osb.model.AccountEntry accountEntry,
		com.liferay.osb.model.CorpProject corpProject,
		com.liferay.osb.model.PartnerEntry partnerEntry,
		com.liferay.portal.model.Address address,
		com.liferay.osb.model.AccountWorker accountWorker,
		java.util.List<com.liferay.osb.model.OrderEntry> orderEntries,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.addOrderEntriesWithWorkflow(salesforceOpportunityKey,
			accountEntry, corpProject, partnerEntry, address, accountWorker,
			orderEntries, serviceContext);
	}

	public com.liferay.osb.model.OrderEntry addOrderEntry(long userId,
		long accountEntryId, java.lang.String purchaseOrderKey,
		int startDateMonth, int startDateDay, int startDateYear,
		boolean prorated, int actualStartDateMonth, int actualStartDateDay,
		int actualStartDateYear, int status,
		java.lang.String salesforceOpportunityKey,
		java.util.List<com.liferay.osb.model.OfferingEntry> offeringEntries)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.addOrderEntry(userId, accountEntryId,
			purchaseOrderKey, startDateMonth, startDateDay, startDateYear,
			prorated, actualStartDateMonth, actualStartDateDay,
			actualStartDateYear, status, salesforceOpportunityKey,
			offeringEntries);
	}

	public java.util.List<com.liferay.osb.model.OrderEntry> getAccountEntryOrderEntries(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.getAccountEntryOrderEntries(accountEntryId);
	}

	public com.liferay.osb.model.OrderEntry getOrderEntry(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.getOrderEntry(uuid);
	}

	public com.liferay.osb.model.OrderEntry renewOrderEntry(long userId,
		long orderEntryId, int renewCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.renewOrderEntry(userId, orderEntryId,
			renewCount);
	}

	public java.util.List<com.liferay.osb.model.OrderEntry> search(
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
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.search(createUserId, createDateGTDay,
			createDateGTMonth, createDateGTYear, createDateLTDay,
			createDateLTMonth, createDateLTYear, modifiedUserId,
			modifiedDateGTDay, modifiedDateGTMonth, modifiedDateGTYear,
			modifiedDateLTDay, modifiedDateLTMonth, modifiedDateLTYear,
			accountEntryId, purchaseOrderKey, statuses, startDateGTDay,
			startDateGTMonth, startDateGTYear, startDateLTDay,
			startDateLTMonth, startDateLTYear, prorated, actualStartDateGTDay,
			actualStartDateGTMonth, actualStartDateGTYear,
			actualStartDateLTDay, actualStartDateLTMonth,
			actualStartDateLTYear, params, andOperator, start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.OrderEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.search(keywords, params, start, end, obc);
	}

	public int searchCount(java.lang.Long createUserId, int createDateGTDay,
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
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.searchCount(createUserId,
			createDateGTDay, createDateGTMonth, createDateGTYear,
			createDateLTDay, createDateLTMonth, createDateLTYear,
			modifiedUserId, modifiedDateGTDay, modifiedDateGTMonth,
			modifiedDateGTYear, modifiedDateLTDay, modifiedDateLTMonth,
			modifiedDateLTYear, accountEntryId, purchaseOrderKey, statuses,
			startDateGTDay, startDateGTMonth, startDateGTYear, startDateLTDay,
			startDateLTMonth, startDateLTYear, prorated, actualStartDateGTDay,
			actualStartDateGTMonth, actualStartDateGTYear,
			actualStartDateLTDay, actualStartDateLTMonth,
			actualStartDateLTYear, params, andOperator);
	}

	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.searchCount(keywords, params);
	}

	public com.liferay.osb.model.OrderEntry updateOrderEntry(long userId,
		long orderEntryId, long accountEntryId,
		java.lang.String purchaseOrderKey, int startDateMonth,
		int startDateDay, int startDateYear, boolean prorated,
		int actualStartDateMonth, int actualStartDateDay,
		int actualStartDateYear, java.lang.String salesforceOpportunityKey,
		java.util.List<com.liferay.osb.model.OfferingEntry> offeringEntries)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.updateOrderEntry(userId, orderEntryId,
			accountEntryId, purchaseOrderKey, startDateMonth, startDateDay,
			startDateYear, prorated, actualStartDateMonth, actualStartDateDay,
			actualStartDateYear, salesforceOpportunityKey, offeringEntries);
	}

	public com.liferay.osb.model.OrderEntry updateStatus(long userId,
		long orderEntryId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _orderEntryLocalService.updateStatus(userId, orderEntryId,
			status, serviceContext);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public OrderEntryLocalService getWrappedOrderEntryLocalService() {
		return _orderEntryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedOrderEntryLocalService(
		OrderEntryLocalService orderEntryLocalService) {
		_orderEntryLocalService = orderEntryLocalService;
	}

	public OrderEntryLocalService getWrappedService() {
		return _orderEntryLocalService;
	}

	public void setWrappedService(OrderEntryLocalService orderEntryLocalService) {
		_orderEntryLocalService = orderEntryLocalService;
	}

	private OrderEntryLocalService _orderEntryLocalService;
}