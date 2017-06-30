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
 * The utility for the ticket entry local service. This utility wraps {@link com.liferay.osb.service.impl.TicketEntryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntryLocalService
 * @see com.liferay.osb.service.base.TicketEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketEntryLocalServiceImpl
 * @generated
 */
public class TicketEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TicketEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the ticket entry to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntry the ticket entry
	* @return the ticket entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketEntry addTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTicketEntry(ticketEntry);
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
	* Deletes the ticket entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry that was removed
	* @throws PortalException if a ticket entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketEntry deleteTicketEntry(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketEntry(ticketEntryId);
	}

	/**
	* Deletes the ticket entry from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntry the ticket entry
	* @return the ticket entry that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketEntry deleteTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTicketEntry(ticketEntry);
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

	public static com.liferay.osb.model.TicketEntry fetchTicketEntry(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTicketEntry(ticketEntryId);
	}

	/**
	* Returns the ticket entry with the primary key.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry
	* @throws PortalException if a ticket entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketEntry getTicketEntry(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketEntry(ticketEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the ticket entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket entries
	* @param end the upper bound of the range of ticket entries (not inclusive)
	* @return the range of ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TicketEntry> getTicketEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketEntries(start, end);
	}

	/**
	* Returns the number of ticket entries.
	*
	* @return the number of ticket entries
	* @throws SystemException if a system exception occurred
	*/
	public static int getTicketEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketEntriesCount();
	}

	/**
	* Updates the ticket entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketEntry the ticket entry
	* @return the ticket entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketEntry updateTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTicketEntry(ticketEntry);
	}

	/**
	* Updates the ticket entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketEntry the ticket entry
	* @param merge whether to merge the ticket entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the ticket entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TicketEntry updateTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTicketEntry(ticketEntry, merge);
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

	public static com.liferay.osb.model.TicketEntry addTicketEntry(
		long userId, long offeringEntryId, long supportRegionId,
		java.lang.String languageId, long ticketId, java.lang.String subject,
		java.lang.String description, int systemStatus, int status, int weight,
		int escalationLevel, int component, int subcomponent,
		java.util.Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTicketEntry(userId, offeringEntryId, supportRegionId,
			languageId, ticketId, subject, description, systemStatus, status,
			weight, escalationLevel, component, subcomponent,
			ticketInformationFieldsMap, ticketAttachments);
	}

	public static void checkInactiveTicketEntries() throws java.lang.Exception {
		getService().checkInactiveTicketEntries();
	}

	public static void checkOnHoldTicketEntries() throws java.lang.Exception {
		getService().checkOnHoldTicketEntries();
	}

	public static void deleteTicketEntries(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteTicketEntries(accountEntryId);
	}

	public static void escalateTicketEntry(long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().escalateTicketEntry(userId, ticketEntryId);
	}

	public static com.liferay.osb.model.TicketEntry forwardTicketEntry(
		long userId, long ticketEntryId, java.lang.String commentBody)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .forwardTicketEntry(userId, ticketEntryId, commentBody);
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> getTicketEntries(
		java.util.Date modifiedDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketEntries(modifiedDate, start, end);
	}

	public static com.liferay.portal.kernel.util.Tuple getTicketEntries(
		com.liferay.portal.kernel.search.Hits hits)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketEntries(hits);
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> getTicketEntries(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketEntries(accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> getTicketEntries(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketEntries(accountEntryId, start, end, obc);
	}

	public static int getTicketEntriesCount(java.util.Date modifiedDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketEntriesCount(modifiedDate);
	}

	public static int getTicketEntriesCount(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketEntriesCount(accountEntryId);
	}

	public static com.liferay.osb.model.TicketEntry getTicketEntry(
		long accountEntryId, long ticketId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTicketEntry(accountEntryId, ticketId);
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> getTicketFeedbackTicketEntries(
		long userId, int createdGTDay, int createdGTMonth, int createdGTYear,
		int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTicketFeedbackTicketEntries(userId, createdGTDay,
			createdGTMonth, createdGTYear, status);
	}

	public static int[] getUserVisibilities(long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserVisibilities(userId, ticketEntryId);
	}

	public static java.util.List<com.liferay.osb.model.TicketEntry> getValidTicketEntries(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getValidTicketEntries(offeringEntryId);
	}

	public static int getValidTicketEntriesCount(long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getValidTicketEntriesCount(offeringEntryId);
	}

	public static boolean hasParticipant(long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().hasParticipant(userId, ticketEntryId);
	}

	public static boolean hasParticipant(long userId,
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasParticipant(userId, ticketEntry);
	}

	public static boolean hasVisibility(long userId, long ticketEntryId,
		int visibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().hasVisibility(userId, ticketEntryId, visibility);
	}

	public static void reindexTicketEntry(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().reindexTicketEntry(ticketEntryId);
	}

	public static void reindexTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().reindexTicketEntry(ticketEntry);
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
		com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(searchUserId, reportedByUserId, accountEntryId,
			name, accountEntryTiers, satisfiedDueDate, createDateGT,
			createDateLT, content, status, severity, escalationLevel, envOS,
			envDB, envJVM, envAS, envLFR, components, resolution, closedDateGT,
			closedDateLT, dueDateGT, dueDateLT, params, andSearch, start, end,
			sorts);
	}

	public static com.liferay.portal.kernel.search.Hits search(
		long searchUserId, long reportedByUserId, long accountEntryId,
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.search.Sort[] sorts)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(searchUserId, reportedByUserId, accountEntryId,
			keywords, params, start, end, sorts);
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
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
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

	public static java.util.List<com.liferay.osb.model.TicketEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(keywords, params, start, end, obc);
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
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.SystemException {
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

	public static int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(keywords, params);
	}

	public static void sendEmail(long userId, long ticketEntryId,
		com.liferay.osb.model.TicketComment ticketComment,
		java.lang.String action)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().sendEmail(userId, ticketEntryId, ticketComment, action);
	}

	public static void sendEmail(long userId,
		com.liferay.osb.model.TicketEntry ticketEntry,
		com.liferay.osb.model.TicketComment ticketComment,
		java.lang.String action)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().sendEmail(userId, ticketEntry, ticketComment, action);
	}

	public static void syncToJIRA(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().syncToJIRA(ticketEntryId);
	}

	public static com.liferay.osb.model.TicketEntry updateCustomerModifiedDate(
		long userId, long ticketEntryId, java.util.Date customerModifiedDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCustomerModifiedDate(userId, ticketEntryId,
			customerModifiedDate);
	}

	public static com.liferay.osb.model.TicketEntry updatePendingTypes(
		long userId, long ticketEntryId, int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updatePendingTypes(userId, ticketEntryId, pendingTypes);
	}

	public static com.liferay.osb.model.TicketEntry updateTicketEntry(
		long userId, long ticketEntryId, long assigneeUserId,
		long supportRegionId, int dueDateMonth, int dueDateDay,
		int dueDateYear, int dueDateHour, int dueDateMinute)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
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
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTicketEntry(userId, ticketEntryId, reportedByUserId,
			offeringEntryId, supportRegionId, languageId, subject, description,
			reproductionSteps, severity, status, weight, escalationLevel,
			component, subcomponent, subcomponentCustom, resolution,
			dueDateMonth, dueDateDay, dueDateYear, dueDateHour, dueDateMinute,
			ignoreDueDate, ticketInformationFieldsMap, pendingTypes,
			ticketAttachments, serviceContext);
	}

	public static com.liferay.osb.model.TicketEntry updateTicketId(
		long ticketEntryId, long ticketId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTicketId(ticketEntryId, ticketId);
	}

	public static com.liferay.osb.model.TicketEntry updateWorkerModifiedDate(
		long ticketEntryId, java.util.Date workerModifiedDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateWorkerModifiedDate(ticketEntryId, workerModifiedDate);
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

	/**
	 * @deprecated
	 */
	public void setService(TicketEntryLocalService service) {
	}

	private static TicketEntryLocalService _service;
}