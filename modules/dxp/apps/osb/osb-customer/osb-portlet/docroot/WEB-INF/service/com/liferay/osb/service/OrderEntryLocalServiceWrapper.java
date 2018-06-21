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
 * Provides a wrapper for {@link OrderEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see OrderEntryLocalService
 * @generated
 */
@ProviderType
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
	*/
	@Override
	public com.liferay.osb.model.OrderEntry addOrderEntry(
		com.liferay.osb.model.OrderEntry orderEntry) {
		return _orderEntryLocalService.addOrderEntry(orderEntry);
	}

	@Override
	public com.liferay.osb.model.OrderEntry addOrderEntry(long userId,
		long accountEntryId, java.lang.String purchaseOrderKey,
		int startDateMonth, int startDateDay, int startDateYear,
		boolean prorated, int actualStartDateMonth, int actualStartDateDay,
		int actualStartDateYear, int status,
		java.lang.String salesforceOpportunityKey,
		java.util.List<com.liferay.osb.model.OfferingEntry> offeringEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderEntryLocalService.addOrderEntry(userId, accountEntryId,
			purchaseOrderKey, startDateMonth, startDateDay, startDateYear,
			prorated, actualStartDateMonth, actualStartDateDay,
			actualStartDateYear, status, salesforceOpportunityKey,
			offeringEntries);
	}

	/**
	* Creates a new order entry with the primary key. Does not add the order entry to the database.
	*
	* @param orderEntryId the primary key for the new order entry
	* @return the new order entry
	*/
	@Override
	public com.liferay.osb.model.OrderEntry createOrderEntry(long orderEntryId) {
		return _orderEntryLocalService.createOrderEntry(orderEntryId);
	}

	/**
	* Deletes the order entry from the database. Also notifies the appropriate model listeners.
	*
	* @param orderEntry the order entry
	* @return the order entry that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.osb.model.OrderEntry deleteOrderEntry(
		com.liferay.osb.model.OrderEntry orderEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderEntryLocalService.deleteOrderEntry(orderEntry);
	}

	/**
	* Deletes the order entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param orderEntryId the primary key of the order entry
	* @return the order entry that was removed
	* @throws PortalException if a order entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.OrderEntry deleteOrderEntry(long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderEntryLocalService.deleteOrderEntry(orderEntryId);
	}

	@Override
	public com.liferay.osb.model.OrderEntry fetchOrderEntry(long orderEntryId) {
		return _orderEntryLocalService.fetchOrderEntry(orderEntryId);
	}

	@Override
	public com.liferay.osb.model.OrderEntry getOrderEntry(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderEntryLocalService.getOrderEntry(uuid);
	}

	/**
	* Returns the order entry with the primary key.
	*
	* @param orderEntryId the primary key of the order entry
	* @return the order entry
	* @throws PortalException if a order entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.OrderEntry getOrderEntry(long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderEntryLocalService.getOrderEntry(orderEntryId);
	}

	@Override
	public com.liferay.osb.model.OrderEntry renewOrderEntry(long userId,
		long orderEntryId, int renewCount)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderEntryLocalService.renewOrderEntry(userId, orderEntryId,
			renewCount);
	}

	/**
	* Updates the order entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param orderEntry the order entry
	* @return the order entry that was updated
	*/
	@Override
	public com.liferay.osb.model.OrderEntry updateOrderEntry(
		com.liferay.osb.model.OrderEntry orderEntry) {
		return _orderEntryLocalService.updateOrderEntry(orderEntry);
	}

	@Override
	public com.liferay.osb.model.OrderEntry updateOrderEntry(long userId,
		long orderEntryId, long accountEntryId,
		java.lang.String purchaseOrderKey, int startDateMonth,
		int startDateDay, int startDateYear, boolean prorated,
		int actualStartDateMonth, int actualStartDateDay,
		int actualStartDateYear, java.lang.String salesforceOpportunityKey,
		java.util.List<com.liferay.osb.model.OfferingEntry> offeringEntries)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderEntryLocalService.updateOrderEntry(userId, orderEntryId,
			accountEntryId, purchaseOrderKey, startDateMonth, startDateDay,
			startDateYear, prorated, actualStartDateMonth, actualStartDateDay,
			actualStartDateYear, salesforceOpportunityKey, offeringEntries);
	}

	@Override
	public com.liferay.osb.model.OrderEntry updateStatus(long userId,
		long orderEntryId, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderEntryLocalService.updateStatus(userId, orderEntryId,
			status, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _orderEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _orderEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _orderEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of order entries.
	*
	* @return the number of order entries
	*/
	@Override
	public int getOrderEntriesCount() {
		return _orderEntryLocalService.getOrderEntriesCount();
	}

	@Override
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
		boolean andOperator) {
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

	@Override
	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return _orderEntryLocalService.searchCount(keywords, params);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _orderEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _orderEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.model.OrderEntry> addOrderEntriesWithWorkflow(
		java.lang.String salesforceOpportunityKey,
		com.liferay.osb.model.AccountEntry accountEntry,
		com.liferay.osb.model.CorpProject corpProject,
		com.liferay.osb.model.PartnerEntry partnerEntry,
		com.liferay.portal.kernel.model.Address address,
		com.liferay.osb.model.AccountWorker accountWorker,
		java.util.List<com.liferay.osb.model.OrderEntry> orderEntries,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _orderEntryLocalService.addOrderEntriesWithWorkflow(salesforceOpportunityKey,
			accountEntry, corpProject, partnerEntry, address, accountWorker,
			orderEntries, serviceContext);
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
		return _orderEntryLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _orderEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _orderEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.model.OrderEntry> getAccountEntryOrderEntries(
		long accountEntryId) {
		return _orderEntryLocalService.getAccountEntryOrderEntries(accountEntryId);
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
	@Override
	public java.util.List<com.liferay.osb.model.OrderEntry> getOrderEntries(
		int start, int end) {
		return _orderEntryLocalService.getOrderEntries(start, end);
	}

	@Override
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
		com.liferay.portal.kernel.util.OrderByComparator obc) {
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

	@Override
	public java.util.List<com.liferay.osb.model.OrderEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _orderEntryLocalService.search(keywords, params, start, end, obc);
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
		return _orderEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _orderEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void checkOrderEntries() {
		_orderEntryLocalService.checkOrderEntries();
	}

	@Override
	public OrderEntryLocalService getWrappedService() {
		return _orderEntryLocalService;
	}

	@Override
	public void setWrappedService(OrderEntryLocalService orderEntryLocalService) {
		_orderEntryLocalService = orderEntryLocalService;
	}

	private OrderEntryLocalService _orderEntryLocalService;
}