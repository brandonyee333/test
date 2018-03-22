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
import com.liferay.osb.model.TicketSolution;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for TicketSolution. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see TicketSolutionLocalServiceUtil
 * @see com.liferay.osb.service.base.TicketSolutionLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TicketSolutionLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface TicketSolutionLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketSolutionLocalServiceUtil} to access the ticket solution local service. Add custom service methods to {@link com.liferay.osb.service.impl.TicketSolutionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the ticket solution to the database. Also notifies the appropriate model listeners.
	*
	* @param ticketSolution the ticket solution
	* @return the ticket solution that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public TicketSolution addTicketSolution(TicketSolution ticketSolution);

	public TicketSolution addTicketSolution(long userId, long ticketEntryId,
		java.lang.String summary, boolean useCustomerSummary, int issueType,
		java.lang.String solution, int type, boolean customerSpecific,
		boolean environmentSpecific, boolean versionSpecific,
		boolean reviewForKB, int status, int ticketEntrySubcomponent,
		java.lang.String ticketEntrySubcomponentCustom,
		List<java.lang.String> ticketLinkURLs,
		List<java.lang.Integer> ticketLinkTypes,
		List<TicketAttachment> ticketAttachments) throws PortalException;

	/**
	* Creates a new ticket solution with the primary key. Does not add the ticket solution to the database.
	*
	* @param ticketSolutionId the primary key for the new ticket solution
	* @return the new ticket solution
	*/
	public TicketSolution createTicketSolution(long ticketSolutionId);

	/**
	* Deletes the ticket solution from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketSolution the ticket solution
	* @return the ticket solution that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public TicketSolution deleteTicketSolution(TicketSolution ticketSolution);

	/**
	* Deletes the ticket solution with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketSolutionId the primary key of the ticket solution
	* @return the ticket solution that was removed
	* @throws PortalException if a ticket solution with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public TicketSolution deleteTicketSolution(long ticketSolutionId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TicketSolution fetchTicketSolution(long ticketSolutionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TicketSolution getActiveTicketSolution(long ticketEntryId);

	/**
	* Returns the ticket solution with the primary key.
	*
	* @param ticketSolutionId the primary key of the ticket solution
	* @return the ticket solution
	* @throws PortalException if a ticket solution with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TicketSolution getTicketSolution(long ticketSolutionId)
		throws PortalException;

	/**
	* Updates the ticket solution in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param ticketSolution the ticket solution
	* @return the ticket solution that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public TicketSolution updateTicketSolution(TicketSolution ticketSolution);

	public TicketSolution updateTicketSolution(long ticketSolutionId,
		long ticketEntryId, int status, long statusByUserId,
		java.lang.String statusMessage, int statusReason)
		throws PortalException;

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

	/**
	* Returns the number of ticket solutions.
	*
	* @return the number of ticket solutions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTicketSolutionsCount();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the ticket solutions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.TicketSolutionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket solutions
	* @param end the upper bound of the range of ticket solutions (not inclusive)
	* @return the range of ticket solutions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TicketSolution> getTicketSolutions(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TicketSolution> getTicketSolutions(long ticketEntryId);

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

	public void updateStatus(long ticketEntryId, int ticketEntryStatus,
		int resolution);
}