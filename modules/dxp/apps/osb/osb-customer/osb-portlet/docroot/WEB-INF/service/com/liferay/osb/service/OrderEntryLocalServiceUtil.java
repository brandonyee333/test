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
 * The utility for the order entry local service. This utility wraps {@link com.liferay.osb.service.impl.OrderEntryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OrderEntryLocalService
 * @see com.liferay.osb.service.base.OrderEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.OrderEntryLocalServiceImpl
 * @generated
 */
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OrderEntry addOrderEntry(
		com.liferay.osb.model.OrderEntry orderEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addOrderEntry(orderEntry);
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
	* Deletes the order entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param orderEntryId the primary key of the order entry
	* @return the order entry that was removed
	* @throws PortalException if a order entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OrderEntry deleteOrderEntry(
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOrderEntry(orderEntryId);
	}

	/**
	* Deletes the order entry from the database. Also notifies the appropriate model listeners.
	*
	* @param orderEntry the order entry
	* @return the order entry that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OrderEntry deleteOrderEntry(
		com.liferay.osb.model.OrderEntry orderEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOrderEntry(orderEntry);
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

	public static com.liferay.osb.model.OrderEntry fetchOrderEntry(
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchOrderEntry(orderEntryId);
	}

	/**
	* Returns the order entry with the primary key.
	*
	* @param orderEntryId the primary key of the order entry
	* @return the order entry
	* @throws PortalException if a order entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OrderEntry getOrderEntry(
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrderEntry(orderEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.liferay.osb.model.OrderEntry> getOrderEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrderEntries(start, end);
	}

	/**
	* Returns the number of order entries.
	*
	* @return the number of order entries
	* @throws SystemException if a system exception occurred
	*/
	public static int getOrderEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrderEntriesCount();
	}

	/**
	* Updates the order entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param orderEntry the order entry
	* @return the order entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OrderEntry updateOrderEntry(
		com.liferay.osb.model.OrderEntry orderEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOrderEntry(orderEntry);
	}

	/**
	* Updates the order entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param orderEntry the order entry
	* @param merge whether to merge the order entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the order entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OrderEntry updateOrderEntry(
		com.liferay.osb.model.OrderEntry orderEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOrderEntry(orderEntry, merge);
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

	public static java.util.List<com.liferay.osb.model.OrderEntry> addOrderEntriesWithWorkflow(
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
		return getService()
				   .addOrderEntriesWithWorkflow(salesforceOpportunityKey,
			accountEntry, corpProject, partnerEntry, address, accountWorker,
			orderEntries, serviceContext);
	}

	public static com.liferay.osb.model.OrderEntry addOrderEntry(long userId,
		long accountEntryId, java.lang.String purchaseOrderKey,
		int startDateMonth, int startDateDay, int startDateYear,
		boolean prorated, int actualStartDateMonth, int actualStartDateDay,
		int actualStartDateYear, int status,
		java.lang.String salesforceOpportunityKey,
		java.util.List<com.liferay.osb.model.OfferingEntry> offeringEntries)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addOrderEntry(userId, accountEntryId, purchaseOrderKey,
			startDateMonth, startDateDay, startDateYear, prorated,
			actualStartDateMonth, actualStartDateDay, actualStartDateYear,
			status, salesforceOpportunityKey, offeringEntries);
	}

	public static java.util.List<com.liferay.osb.model.OrderEntry> getAccountEntryOrderEntries(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntryOrderEntries(accountEntryId);
	}

	public static com.liferay.osb.model.OrderEntry getOrderEntry(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrderEntry(uuid);
	}

	public static com.liferay.osb.model.OrderEntry renewOrderEntry(
		long userId, long orderEntryId, int renewCount)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().renewOrderEntry(userId, orderEntryId, renewCount);
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
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
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
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(keywords, params, start, end, obc);
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
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(keywords, params);
	}

	public static com.liferay.osb.model.OrderEntry updateOrderEntry(
		long userId, long orderEntryId, long accountEntryId,
		java.lang.String purchaseOrderKey, int startDateMonth,
		int startDateDay, int startDateYear, boolean prorated,
		int actualStartDateMonth, int actualStartDateDay,
		int actualStartDateYear, java.lang.String salesforceOpportunityKey,
		java.util.List<com.liferay.osb.model.OfferingEntry> offeringEntries)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateOrderEntry(userId, orderEntryId, accountEntryId,
			purchaseOrderKey, startDateMonth, startDateDay, startDateYear,
			prorated, actualStartDateMonth, actualStartDateDay,
			actualStartDateYear, salesforceOpportunityKey, offeringEntries);
	}

	public static com.liferay.osb.model.OrderEntry updateStatus(long userId,
		long orderEntryId, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateStatus(userId, orderEntryId, status, serviceContext);
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

	/**
	 * @deprecated
	 */
	public void setService(OrderEntryLocalService service) {
	}

	private static OrderEntryLocalService _service;
}