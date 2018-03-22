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
 * Provides a wrapper for {@link TicketEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntryLocalService
 * @generated
 */
@ProviderType
public class TicketEntryLocalServiceWrapper implements TicketEntryLocalService,
	ServiceWrapper<TicketEntryLocalService> {
	public TicketEntryLocalServiceWrapper(
		TicketEntryLocalService ticketEntryLocalService) {
		_ticketEntryLocalService = ticketEntryLocalService;
	}

	@Override
	public boolean hasParticipant(long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.hasParticipant(userId, ticketEntryId);
	}

	/**
	* Adds the ticket entry to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntry the ticket entry
	* @return the ticket entry that was added
	*/
	@Override
	public com.liferay.osb.model.TicketEntry addTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry) {
		return _ticketEntryLocalService.addTicketEntry(ticketEntry);
	}

	@Override
	public com.liferay.osb.model.TicketEntry addTicketEntry(long userId,
		long offeringEntryId, long supportRegionId,
		java.lang.String languageId, long ticketId, java.lang.String subject,
		java.lang.String description, int systemStatus, int status, int weight,
		int escalationLevel, int component, int subcomponent,
		java.util.Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.addTicketEntry(userId, offeringEntryId,
			supportRegionId, languageId, ticketId, subject, description,
			systemStatus, status, weight, escalationLevel, component,
			subcomponent, ticketInformationFieldsMap, ticketAttachments);
	}

	/**
	* Creates a new ticket entry with the primary key. Does not add the ticket entry to the database.
	*
	* @param ticketEntryId the primary key for the new ticket entry
	* @return the new ticket entry
	*/
	@Override
	public com.liferay.osb.model.TicketEntry createTicketEntry(
		long ticketEntryId) {
		return _ticketEntryLocalService.createTicketEntry(ticketEntryId);
	}

	/**
	* Deletes the ticket entry from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntry the ticket entry
	* @return the ticket entry that was removed
	* @throws PortalException
	*/
	@Override
	public com.liferay.osb.model.TicketEntry deleteTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.deleteTicketEntry(ticketEntry);
	}

	/**
	* Deletes the ticket entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry that was removed
	* @throws PortalException if a ticket entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketEntry deleteTicketEntry(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.deleteTicketEntry(ticketEntryId);
	}

	@Override
	public com.liferay.osb.model.TicketEntry fetchTicketEntry(
		long ticketEntryId) {
		return _ticketEntryLocalService.fetchTicketEntry(ticketEntryId);
	}

	@Override
	public com.liferay.osb.model.TicketEntry forwardTicketEntry(long userId,
		long ticketEntryId, java.lang.String commentBody)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.forwardTicketEntry(userId,
			ticketEntryId, commentBody);
	}

	@Override
	public com.liferay.osb.model.TicketEntry getTicketEntry(
		long accountEntryId, long ticketId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.getTicketEntry(accountEntryId, ticketId);
	}

	/**
	* Returns the ticket entry with the primary key.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry
	* @throws PortalException if a ticket entry with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.TicketEntry getTicketEntry(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.getTicketEntry(ticketEntryId);
	}

	@Override
	public com.liferay.osb.model.TicketEntry updateCustomerModifiedDate(
		long userId, long ticketEntryId, java.util.Date customerModifiedDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.updateCustomerModifiedDate(userId,
			ticketEntryId, customerModifiedDate);
	}

	@Override
	public com.liferay.osb.model.TicketEntry updatePendingTypes(long userId,
		long ticketEntryId, int[] pendingTypes)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.updatePendingTypes(userId,
			ticketEntryId, pendingTypes);
	}

	/**
	* Updates the ticket entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketEntry the ticket entry
	* @return the ticket entry that was updated
	*/
	@Override
	public com.liferay.osb.model.TicketEntry updateTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry) {
		return _ticketEntryLocalService.updateTicketEntry(ticketEntry);
	}

	@Override
	public com.liferay.osb.model.TicketEntry updateTicketEntry(long userId,
		long ticketEntryId, long assigneeUserId, long supportRegionId,
		int dueDateMonth, int dueDateDay, int dueDateYear, int dueDateHour,
		int dueDateMinute)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.updateTicketEntry(userId,
			ticketEntryId, assigneeUserId, supportRegionId, dueDateMonth,
			dueDateDay, dueDateYear, dueDateHour, dueDateMinute);
	}

	@Override
	public com.liferay.osb.model.TicketEntry updateTicketEntry(long userId,
		long ticketEntryId, long reportedByUserId, long offeringEntryId,
		long supportRegionId, java.lang.String languageId,
		java.lang.String subject, java.lang.String description,
		java.lang.String reproductionSteps, int severity, int status,
		int weight, int escalationLevel, int component, int subcomponent,
		java.lang.String subcomponentCustom, int resolution, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute,
		boolean ignoreDueDate,
		java.util.Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		int[] pendingTypes,
		java.util.List<com.liferay.osb.model.TicketAttachment> ticketAttachments,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.updateTicketEntry(userId,
			ticketEntryId, reportedByUserId, offeringEntryId, supportRegionId,
			languageId, subject, description, reproductionSteps, severity,
			status, weight, escalationLevel, component, subcomponent,
			subcomponentCustom, resolution, dueDateMonth, dueDateDay,
			dueDateYear, dueDateHour, dueDateMinute, ignoreDueDate,
			ticketInformationFieldsMap, pendingTypes, ticketAttachments,
			serviceContext);
	}

	@Override
	public com.liferay.osb.model.TicketEntry updateWorkerModifiedDate(
		long ticketEntryId, java.util.Date workerModifiedDate)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.updateWorkerModifiedDate(ticketEntryId,
			workerModifiedDate);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _ticketEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketEntryLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _ticketEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(long searchUserId,
		long reportedByUserId, long accountEntryId, java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.search.Sort[] sorts) {
		return _ticketEntryLocalService.search(searchUserId, reportedByUserId,
			accountEntryId, keywords, params, start, end, sorts);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits search(long searchUserId,
		long reportedByUserId, long accountEntryId, java.lang.String name,
		int[] accountEntryTiers, java.lang.Boolean satisfiedDueDate,
		java.util.Date createDateGT, java.util.Date createDateLT,
		java.lang.String content, int[] status, int[] severity,
		int[] escalationLevel, long[] envOS, long[] envDB, long[] envJVM,
		long[] envAS, long[] envLFR, int[] components, int[] resolution,
		java.util.Date closedDateGT, java.util.Date closedDateLT,
		java.util.Date dueDateGT, java.util.Date dueDateLT,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.search.Sort[] sorts) {
		return _ticketEntryLocalService.search(searchUserId, reportedByUserId,
			accountEntryId, name, accountEntryTiers, satisfiedDueDate,
			createDateGT, createDateLT, content, status, severity,
			escalationLevel, envOS, envDB, envJVM, envAS, envLFR, components,
			resolution, closedDateGT, closedDateLT, dueDateGT, dueDateLT,
			params, andSearch, start, end, sorts);
	}

	@Override
	public com.liferay.portal.kernel.util.Tuple getTicketEntries(
		com.liferay.portal.kernel.search.Hits hits)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.getTicketEntries(hits);
	}

	/**
	* Returns the number of ticket entries.
	*
	* @return the number of ticket entries
	*/
	@Override
	public int getTicketEntriesCount() {
		return _ticketEntryLocalService.getTicketEntriesCount();
	}

	@Override
	public int getTicketEntriesCount(java.util.Date modifiedDate) {
		return _ticketEntryLocalService.getTicketEntriesCount(modifiedDate);
	}

	@Override
	public int getValidTicketEntriesCount(long offeringEntryId) {
		return _ticketEntryLocalService.getValidTicketEntriesCount(offeringEntryId);
	}

	@Override
	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return _ticketEntryLocalService.searchCount(keywords, params);
	}

	@Override
	public int searchCount(long reportedByUserId, java.lang.String name,
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
		return _ticketEntryLocalService.searchCount(reportedByUserId, name,
			accountEntryTiers, satisfiedDueDate, createDateGTDay,
			createDateGTMonth, createDateGTYear, createDateLTDay,
			createDateLTMonth, createDateLTYear, subject, description, body,
			status, severity, weights, escalationLevel, envOS, envDB, envJVM,
			envAS, envLFR, components, resolution, closedDateGTDay,
			closedDateGTMonth, closedDateGTYear, closedDateLTDay,
			closedDateLTMonth, closedDateLTYear, dueDateGTDay, dueDateGTMonth,
			dueDateGTYear, dueDateLTDay, dueDateLTMonth, dueDateLTYear, params,
			andSearch);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _ticketEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _ticketEntryLocalService.getOSGiServiceIdentifier();
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
		return _ticketEntryLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _ticketEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _ticketEntryLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	@Override
	public java.util.List<com.liferay.osb.model.TicketEntry> getTicketEntries(
		int start, int end) {
		return _ticketEntryLocalService.getTicketEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketEntry> getTicketEntries(
		java.util.Date modifiedDate, int start, int end) {
		return _ticketEntryLocalService.getTicketEntries(modifiedDate, start,
			end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketEntry> getTicketFeedbackTicketEntries(
		long userId, int createdGTDay, int createdGTMonth, int createdGTYear,
		int status) throws com.liferay.portal.kernel.exception.PortalException {
		return _ticketEntryLocalService.getTicketFeedbackTicketEntries(userId,
			createdGTDay, createdGTMonth, createdGTYear, status);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _ticketEntryLocalService.search(keywords, params, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.TicketEntry> search(
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
		return _ticketEntryLocalService.search(reportedByUserId, name,
			accountEntryTiers, satisfiedDueDate, createDateGTDay,
			createDateGTMonth, createDateGTYear, createDateLTDay,
			createDateLTMonth, createDateLTYear, subject, description, body,
			status, severity, weights, escalationLevel, envOS, envDB, envJVM,
			envAS, envLFR, components, resolution, closedDateGTDay,
			closedDateGTMonth, closedDateGTYear, closedDateLTDay,
			closedDateLTMonth, closedDateLTYear, dueDateGTDay, dueDateGTMonth,
			dueDateGTYear, dueDateLTDay, dueDateLTMonth, dueDateLTYear, params,
			andSearch, start, end, obc);
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
		return _ticketEntryLocalService.dynamicQueryCount(dynamicQuery);
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
		return _ticketEntryLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void checkInactiveTicketEntries() throws java.lang.Exception {
		_ticketEntryLocalService.checkInactiveTicketEntries();
	}

	@Override
	public void checkOnHoldTicketEntries() throws java.lang.Exception {
		_ticketEntryLocalService.checkOnHoldTicketEntries();
	}

	@Override
	public void deleteTicketEntries(long accountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketEntryLocalService.deleteTicketEntries(accountEntryId);
	}

	@Override
	public void escalateTicketEntry(long userId, long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketEntryLocalService.escalateTicketEntry(userId, ticketEntryId);
	}

	@Override
	public void reindexTicketEntry(
		com.liferay.osb.model.TicketEntry ticketEntry)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketEntryLocalService.reindexTicketEntry(ticketEntry);
	}

	@Override
	public void reindexTicketEntry(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketEntryLocalService.reindexTicketEntry(ticketEntryId);
	}

	@Override
	public void sendEmail(long userId,
		com.liferay.osb.model.TicketEntry ticketEntry,
		com.liferay.osb.model.TicketComment ticketComment,
		java.lang.String action)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketEntryLocalService.sendEmail(userId, ticketEntry, ticketComment,
			action);
	}

	@Override
	public void sendEmail(long userId, long ticketEntryId,
		com.liferay.osb.model.TicketComment ticketComment,
		java.lang.String action)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketEntryLocalService.sendEmail(userId, ticketEntryId,
			ticketComment, action);
	}

	@Override
	public void syncToJIRA(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		_ticketEntryLocalService.syncToJIRA(ticketEntryId);
	}

	@Override
	public TicketEntryLocalService getWrappedService() {
		return _ticketEntryLocalService;
	}

	@Override
	public void setWrappedService(
		TicketEntryLocalService ticketEntryLocalService) {
		_ticketEntryLocalService = ticketEntryLocalService;
	}

	private TicketEntryLocalService _ticketEntryLocalService;
}