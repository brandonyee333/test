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
 * Provides the local service utility for TicketEntry. This utility wraps
 * {@link com.liferay.osb.service.impl.TicketEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntryLocalService
 * @see com.liferay.osb.service.base.TicketEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class TicketEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasParticipant(long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().hasParticipant(userId, ticketEntryId);
	}

	/**
	* Adds the ticket entry to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntry the ticket entry
	* @return the ticket entry that was added
	*/
	public static com.liferay.osb.model.TicketEntry addTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry) {
		return getService().addTicketEntry(ticketEntry);
	}

	public static com.liferay.osb.model.TicketEntry addTicketEntry(
		long userId, long offeringEntryId, long supportRegionId,
		java.lang.String languageId, long ticketId, java.lang.String subject,
		java.lang.String description, int systemStatus, int status, int weight,
		int escalationLevel, int component, int subcomponent,
		java.util.Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addTicketEntry(userId, offeringEntryId, supportRegionId,
			languageId, ticketId, subject, description, systemStatus, status,
			weight, escalationLevel, component, subcomponent,
			ticketInformationFieldsMap, ticketAttachments);
	}

	/**
	* Creates a new ticket entry with the primary key. Does not add the ticket entry to the database.
	*
	* @param ticketEntryId the primary key for the new ticket entry
	* @return the new ticket entry
	*/
	public static com.liferay.osb.model.TicketEntry createTicketEntry(
		long ticketEntryId) {
		return getService().createTicketEntry(ticketEntryId);
	}

	/**
	* Deletes the ticket entry from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntry the ticket entry
	* @return the ticket entry that was removed
	* @throws PortalException
	*/
	public static com.liferay.osb.model.TicketEntry deleteTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTicketEntry(ticketEntry);
	}

	/**
	* Deletes the ticket entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry that was removed
	* @throws PortalException if a ticket entry with the primary key could not be found
	*/
	public static com.liferay.osb.model.TicketEntry deleteTicketEntry(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTicketEntry(ticketEntryId);
	}

	public static com.liferay.osb.model.TicketEntry fetchTicketEntry(
		long ticketEntryId) {
		return getService().fetchTicketEntry(ticketEntryId);
	}

	public static com.liferay.osb.model.TicketEntry forwardTicketEntry(
		long userId, long ticketEntryId, java.lang.String commentBody)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .forwardTicketEntry(userId, ticketEntryId, commentBody);
	}

	public static com.liferay.osb.model.TicketEntry getTicketEntry(
		long accountEntryId, long ticketId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketEntry(accountEntryId, ticketId);
	}

	/**
	* Returns the ticket entry with the primary key.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry
	* @throws PortalException if a ticket entry with the primary key could not be found
	*/
	public static com.liferay.osb.model.TicketEntry getTicketEntry(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketEntry(ticketEntryId);
	}

	public static com.liferay.osb.model.TicketEntry updateCustomerModifiedDate(
		long userId, long ticketEntryId, java.util.Date customerModifiedDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCustomerModifiedDate(userId, ticketEntryId,
			customerModifiedDate);
	}

	public static com.liferay.osb.model.TicketEntry updatePendingTypes(
		long userId, long ticketEntryId, int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updatePendingTypes(userId, ticketEntryId, pendingTypes);
	}

	/**
	* Updates the ticket entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketEntry the ticket entry
	* @return the ticket entry that was updated
	*/
	public static com.liferay.osb.model.TicketEntry updateTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry) {
		return getService().updateTicketEntry(ticketEntry);
	}

	public static com.liferay.osb.model.TicketEntry updateTicketEntry(
		long userId, long ticketEntryId, long assigneeUserId,
		long supportRegionId, int dueDateMonth, int dueDateDay,
		int dueDateYear, int dueDateHour, int dueDateMinute)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketEntry(userId, ticketEntryId, assigneeUserId,
			supportRegionId, dueDateMonth, dueDateDay, dueDateYear,
			dueDateHour, dueDateMinute);
	}

	public static com.liferay.osb.model.TicketEntry updateTicketEntry(
		long userId, long ticketEntryId, long reportedByUserId,
		long offeringEntryId, long supportRegionId,
		java.lang.String languageId, java.lang.String subject,
		java.lang.String description, java.lang.String reproductionSteps,
		int severity, int status, int weight, int escalationLevel,
		int component, int subcomponent, java.lang.String subcomponentCustom,
		int resolution, int dueDateMonth, int dueDateDay, int dueDateYear,
		int dueDateHour, int dueDateMinute, boolean ignoreDueDate,
		java.util.Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		int[] pendingTypes,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateTicketEntry(userId, ticketEntryId, reportedByUserId,
			offeringEntryId, supportRegionId, languageId, subject, description,
			reproductionSteps, severity, status, weight, escalationLevel,
			component, subcomponent, subcomponentCustom, resolution,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			ignoreDueDate, ticketInformationFieldsMap, pendingTypes,
			ticketAttachments, serviceContext);
	}

	public static com.liferay.osb.model.TicketEntry updateWorkerModifiedDate(
		long ticketEntryId, java.util.Date workerModifiedDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateWorkerModifiedDate(ticketEntryId, workerModifiedDate);
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

	public static com.liferay.portal.kernel.search.Hits search(
		long searchUserId, long reportedByUserId, long accountEntryId,
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.search.Sort[] sorts) {
		return getService()
				   .search(searchUserId, reportedByUserId, accountEntryId,
			keywords, params, start, end, sorts);
	}

	public static com.liferay.portal.kernel.search.Hits search(
		long searchUserId, long reportedByUserId, long accountEntryId,
		java.lang.String name, int[] accountEntryTiers,
		java.lang.Boolean satisfiedDueDate, java.util.Date createDateGT,
		java.util.Date createDateLT, java.lang.String content, int[] status,
		int[] severity, int[] escalationLevel, long[] envOS, long[] envDB,
		long[] envJVM, long[] envAS, long[] envLFR, int[] components,
		int[] resolution, java.util.Date closedDateGT,
		java.util.Date closedDateLT, java.util.Date dueDateGT,
		java.util.Date dueDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.search.Sort[] sorts) {
		return getService()
				   .search(searchUserId, reportedByUserId, accountEntryId,
			name, accountEntryTiers, satisfiedDueDate, createDateGT,
			createDateLT, content, status, severity, escalationLevel, envOS,
			envDB, envJVM, envAS, envLFR, components, resolution, closedDateGT,
			closedDateLT, dueDateGT, dueDateLT, params, andSearch, start, end,
			sorts);
	}

	public static com.liferay.portal.kernel.util.Tuple getTicketEntries(
		com.liferay.portal.kernel.search.Hits hits)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTicketEntries(hits);
	}

	/**
	* Returns the number of ticket entries.
	*
	* @return the number of ticket entries
	*/
	public static int getTicketEntriesCount() {
		return getService().getTicketEntriesCount();
	}

	public static int getTicketEntriesCount(java.util.Date modifiedDate) {
		return getService().getTicketEntriesCount(modifiedDate);
	}

	public static int getValidTicketEntriesCount(long offeringEntryId) {
		return getService().getValidTicketEntriesCount(offeringEntryId);
	}

	public static int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getService().searchCount(keywords, params);
	}

	public static int searchCount(long reportedByUserId, java.lang.String name,
		int[] accountEntryTiers, java.lang.Boolean satisfiedDueDate,
		int createDateGTDay, int createDateGTMonth, int createDateGTYear,
		int createDateLTDay, int createDateLTMonth, int createDateLTYear,
		java.lang.String subject, java.lang.String description,
		java.lang.String body, int[] status, int[] severity, int[] weights,
		int[] escalationLevel, long[] envOS, long[] envDB, long[] envJVM,
		long[] envAS, long[] envLFR, int[] components, int[] resolution,
		int closedDateGTDay, int closedDateGTMonth, int closedDateGTYear,
		int closedDateLTDay, int closedDateLTMonth, int closedDateLTYear,
		int dueDateGTDay, int dueDateGTMonth, int dueDateGTYear,
		int dueDateLTDay, int dueDateLTMonth, int dueDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch) {
		return getService()
				   .searchCount(reportedByUserId, name, accountEntryTiers,
			satisfiedDueDate, createDateGTDay, createDateGTMonth,
			createDateGTYear, createDateLTDay, createDateLTMonth,
			createDateLTYear, subject, description, body, status, severity,
			weights, escalationLevel, envOS, envDB, envJVM, envAS, envLFR,
			components, resolution, closedDateGTDay, closedDateGTMonth,
			closedDateGTYear, closedDateLTDay, closedDateLTMonth,
			closedDateLTYear, dueDateGTDay, dueDateGTMonth, dueDateGTYear,
			dueDateLTDay, dueDateLTMonth, dueDateLTYear, params, andSearch);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Returns a range of all the ticket entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @return the range of ticket entries
	*/
	public static java.util.List<com.liferay.osb.model.TicketEntry> getTicketEntries(
		int start, int end) {
		return getService().getTicketEntries(start, end);
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> getTicketEntries(
		java.util.Date modifiedDate, int start, int end) {
		return getService().getTicketEntries(modifiedDate, start, end);
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> getTicketFeedbackTicketEntries(
		long userId, int createdGTDay, int createdGTMonth, int createdGTYear,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getTicketFeedbackTicketEntries(userId, createdGTDay,
			createdGTMonth, createdGTYear, status);
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService().search(keywords, params, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> search(
		long reportedByUserId, java.lang.String name, int[] accountEntryTiers,
		java.lang.Boolean satisfiedDueDate, int createDateGTDay,
		int createDateGTMonth, int createDateGTYear, int createDateLTDay,
		int createDateLTMonth, int createDateLTYear, java.lang.String subject,
		java.lang.String description, java.lang.String body, int[] status,
		int[] severity, int[] weights, int[] escalationLevel, long[] envOS,
		long[] envDB, long[] envJVM, long[] envAS, long[] envLFR,
		int[] components, int[] resolution, int closedDateGTDay,
		int closedDateGTMonth, int closedDateGTYear, int closedDateLTDay,
		int closedDateLTMonth, int closedDateLTYear, int dueDateGTDay,
		int dueDateGTMonth, int dueDateGTYear, int dueDateLTDay,
		int dueDateLTMonth, int dueDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService()
				   .search(reportedByUserId, name, accountEntryTiers,
			satisfiedDueDate, createDateGTDay, createDateGTMonth,
			createDateGTYear, createDateLTDay, createDateLTMonth,
			createDateLTYear, subject, description, body, status, severity,
			weights, escalationLevel, envOS, envDB, envJVM, envAS, envLFR,
			components, resolution, closedDateGTDay, closedDateGTMonth,
			closedDateGTYear, closedDateLTDay, closedDateLTMonth,
			closedDateLTYear, dueDateGTDay, dueDateGTMonth, dueDateGTYear,
			dueDateLTDay, dueDateLTMonth, dueDateLTYear, params, andSearch,
			start, end, obc);
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

	public static void checkInactiveTicketEntries() throws java.lang.Exception {
		getService().checkInactiveTicketEntries();
	}

	public static void checkOnHoldTicketEntries() throws java.lang.Exception {
		getService().checkOnHoldTicketEntries();
	}

	public static void deleteTicketEntries(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteTicketEntries(accountEntryId);
	}

	public static void escalateTicketEntry(long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().escalateTicketEntry(userId, ticketEntryId);
	}

	public static void reindexTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().reindexTicketEntry(ticketEntry);
	}

	public static void reindexTicketEntry(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().reindexTicketEntry(ticketEntryId);
	}

	public static void sendEmail(long userId,
		com.liferay.osb.model.TicketEntry ticketEntry,
		com.liferay.osb.model.TicketComment ticketComment,
		java.lang.String action)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().sendEmail(userId, ticketEntry, ticketComment, action);
	}

	public static void sendEmail(long userId, long ticketEntryId,
		com.liferay.osb.model.TicketComment ticketComment,
		java.lang.String action)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().sendEmail(userId, ticketEntryId, ticketComment, action);
	}

	public static void syncToJIRA(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().syncToJIRA(ticketEntryId);
	}

	public static void clearService() {
		_service = null;
	}

	public static TicketEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TicketEntryLocalService.class.getName());

			if (invokableLocalService instanceof TicketEntryLocalService) {
				_service = (TicketEntryLocalService)invokableLocalService;
			}
			else {
				_service = new TicketEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TicketEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static TicketEntryLocalService _service;
}