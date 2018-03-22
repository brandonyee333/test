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

import com.liferay.osb.model.TicketAttachment;
import com.liferay.osb.model.TicketComment;
import com.liferay.osb.model.TicketEntry;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Tuple;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides the local service interface for TicketEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see TicketEntryLocalServiceUtil
 * @see com.liferay.osb.service.base.TicketEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketEntryLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface TicketEntryLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketEntryLocalServiceUtil} to access the ticket entry local service. Add custom service methods to {@link com.liferay.osb.service.impl.TicketEntryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasParticipant(long userId, long ticketEntryId)
		throws PortalException;

	/**
	* Adds the ticket entry to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntry the ticket entry
	* @return the ticket entry that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public TicketEntry addTicketEntry(TicketEntry ticketEntry);

	public TicketEntry addTicketEntry(long userId, long offeringEntryId,
		long supportRegionId, java.lang.String languageId, long ticketId,
		java.lang.String subject, java.lang.String description,
		int systemStatus, int status, int weight, int escalationLevel,
		int component, int subcomponent,
		Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		List<TicketAttachment> ticketAttachments) throws PortalException;

	/**
	* Creates a new ticket entry with the primary key. Does not add the ticket entry to the database.
	*
	* @param ticketEntryId the primary key for the new ticket entry
	* @return the new ticket entry
	*/
	public TicketEntry createTicketEntry(long ticketEntryId);

	/**
	* Deletes the ticket entry from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntry the ticket entry
	* @return the ticket entry that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public TicketEntry deleteTicketEntry(TicketEntry ticketEntry)
		throws PortalException;

	/**
	* Deletes the ticket entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry that was removed
	* @throws PortalException if a ticket entry with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public TicketEntry deleteTicketEntry(long ticketEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TicketEntry fetchTicketEntry(long ticketEntryId);

	public TicketEntry forwardTicketEntry(long userId, long ticketEntryId,
		java.lang.String commentBody) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TicketEntry getTicketEntry(long accountEntryId, long ticketId)
		throws PortalException;

	/**
	* Returns the ticket entry with the primary key.
	*
	* @param ticketEntryId the primary key of the ticket entry
	* @return the ticket entry
	* @throws PortalException if a ticket entry with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TicketEntry getTicketEntry(long ticketEntryId)
		throws PortalException;

	public TicketEntry updateCustomerModifiedDate(long userId,
		long ticketEntryId, Date customerModifiedDate)
		throws PortalException;

	public TicketEntry updatePendingTypes(long userId, long ticketEntryId,
		int[] pendingTypes) throws PortalException;

	/**
	* Updates the ticket entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketEntry the ticket entry
	* @return the ticket entry that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public TicketEntry updateTicketEntry(TicketEntry ticketEntry);

	public TicketEntry updateTicketEntry(long userId, long ticketEntryId,
		long assigneeUserId, long supportRegionId, int dueDateMonth,
		int dueDateDay, int dueDateYear, int dueDateHour, int dueDateMinute)
		throws PortalException;

	public TicketEntry updateTicketEntry(long userId, long ticketEntryId,
		long reportedByUserId, long offeringEntryId, long supportRegionId,
		java.lang.String languageId, java.lang.String subject,
		java.lang.String description, java.lang.String reproductionSteps,
		int severity, int status, int weight, int escalationLevel,
		int component, int subcomponent, java.lang.String subcomponentCustom,
		int resolution, int dueDateMonth, int dueDateDay, int dueDateYear,
		int dueDateHour, int dueDateMinute, boolean ignoreDueDate,
		Map<java.lang.Long, java.lang.String> ticketInformationFieldsMap,
		int[] pendingTypes, List<TicketAttachment> ticketAttachments,
		ServiceContext serviceContext) throws PortalException;

	public TicketEntry updateWorkerModifiedDate(long ticketEntryId,
		Date workerModifiedDate) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long searchUserId, long reportedByUserId,
		long accountEntryId, java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, Sort[] sorts);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits search(long searchUserId, long reportedByUserId,
		long accountEntryId, java.lang.String name, int[] accountEntryTiers,
		java.lang.Boolean satisfiedDueDate, Date createDateGT,
		Date createDateLT, java.lang.String content, int[] status,
		int[] severity, int[] escalationLevel, long[] envOS, long[] envDB,
		long[] envJVM, long[] envAS, long[] envLFR, int[] components,
		int[] resolution, Date closedDateGT, Date closedDateLT, Date dueDateGT,
		Date dueDateLT,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, Sort[] sorts);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Tuple getTicketEntries(Hits hits) throws PortalException;

	/**
	* Returns the number of ticket entries.
	*
	* @return the number of ticket entries
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTicketEntriesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTicketEntriesCount(Date modifiedDate);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getValidTicketEntriesCount(long offeringEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TicketEntry> getTicketEntries(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TicketEntry> getTicketEntries(Date modifiedDate, int start,
		int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TicketEntry> getTicketFeedbackTicketEntries(long userId,
		int createdGTDay, int createdGTMonth, int createdGTYear, int status)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TicketEntry> search(java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TicketEntry> search(long reportedByUserId,
		java.lang.String name, int[] accountEntryTiers,
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
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, OrderByComparator obc);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public void checkInactiveTicketEntries() throws java.lang.Exception;

	public void checkOnHoldTicketEntries() throws java.lang.Exception;

	public void deleteTicketEntries(long accountEntryId)
		throws PortalException;

	public void escalateTicketEntry(long userId, long ticketEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void reindexTicketEntry(TicketEntry ticketEntry)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void reindexTicketEntry(long ticketEntryId)
		throws PortalException;

	public void sendEmail(long userId, TicketEntry ticketEntry,
		TicketComment ticketComment, java.lang.String action)
		throws PortalException;

	public void sendEmail(long userId, long ticketEntryId,
		TicketComment ticketComment, java.lang.String action)
		throws PortalException;

	public void syncToJIRA(long ticketEntryId) throws PortalException;
}