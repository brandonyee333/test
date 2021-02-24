/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.ticket.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TicketAttachmentLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentLocalService
 * @generated
 */
public class TicketAttachmentLocalServiceWrapper
	implements ServiceWrapper<TicketAttachmentLocalService>,
			   TicketAttachmentLocalService {

	public TicketAttachmentLocalServiceWrapper(
		TicketAttachmentLocalService ticketAttachmentLocalService) {

		_ticketAttachmentLocalService = ticketAttachmentLocalService;
	}

	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment
			addTicketAttachment(
				long userId, long accountEntryId, long zendeskTicketId,
				String fileRepositoryId, String fileName, long fileSize,
				int type, boolean regionRestricted,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ticketAttachmentLocalService.addTicketAttachment(
			userId, accountEntryId, zendeskTicketId, fileRepositoryId, fileName,
			fileSize, type, regionRestricted, serviceContext);
	}

	/**
	 * Adds the ticket attachment to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TicketAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ticketAttachment the ticket attachment
	 * @return the ticket attachment that was added
	 */
	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment
		addTicketAttachment(
			com.liferay.osb.customer.ticket.model.TicketAttachment
				ticketAttachment) {

		return _ticketAttachmentLocalService.addTicketAttachment(
			ticketAttachment);
	}

	/**
	 * Creates a new ticket attachment with the primary key. Does not add the ticket attachment to the database.
	 *
	 * @param ticketAttachmentId the primary key for the new ticket attachment
	 * @return the new ticket attachment
	 */
	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment
		createTicketAttachment(long ticketAttachmentId) {

		return _ticketAttachmentLocalService.createTicketAttachment(
			ticketAttachmentId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ticketAttachmentLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the ticket attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TicketAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ticketAttachmentId the primary key of the ticket attachment
	 * @return the ticket attachment that was removed
	 * @throws PortalException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment
			deleteTicketAttachment(long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ticketAttachmentLocalService.deleteTicketAttachment(
			ticketAttachmentId);
	}

	/**
	 * Deletes the ticket attachment from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TicketAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ticketAttachment the ticket attachment
	 * @return the ticket attachment that was removed
	 */
	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment
		deleteTicketAttachment(
			com.liferay.osb.customer.ticket.model.TicketAttachment
				ticketAttachment) {

		return _ticketAttachmentLocalService.deleteTicketAttachment(
			ticketAttachment);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ticketAttachmentLocalService.dynamicQuery();
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

		return _ticketAttachmentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.ticket.model.impl.TicketAttachmentModelImpl</code>.
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

		return _ticketAttachmentLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.ticket.model.impl.TicketAttachmentModelImpl</code>.
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

		return _ticketAttachmentLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return _ticketAttachmentLocalService.dynamicQueryCount(dynamicQuery);
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

		return _ticketAttachmentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment
		fetchTicketAttachment(long ticketAttachmentId) {

		return _ticketAttachmentLocalService.fetchTicketAttachment(
			ticketAttachmentId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _ticketAttachmentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _ticketAttachmentLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _ticketAttachmentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ticketAttachmentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the ticket attachment with the primary key.
	 *
	 * @param ticketAttachmentId the primary key of the ticket attachment
	 * @return the ticket attachment
	 * @throws PortalException if a ticket attachment with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment
			getTicketAttachment(long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ticketAttachmentLocalService.getTicketAttachment(
			ticketAttachmentId);
	}

	/**
	 * Returns a range of all the ticket attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.ticket.model.impl.TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of ticket attachments
	 */
	@Override
	public java.util.List
		<com.liferay.osb.customer.ticket.model.TicketAttachment>
			getTicketAttachments(int start, int end) {

		return _ticketAttachmentLocalService.getTicketAttachments(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.osb.customer.ticket.model.TicketAttachment>
			getTicketAttachments(long accountEntryId) {

		return _ticketAttachmentLocalService.getTicketAttachments(
			accountEntryId);
	}

	@Override
	public java.util.List
		<com.liferay.osb.customer.ticket.model.TicketAttachment>
			getTicketAttachments(long zendeskTicketId, int[] types) {

		return _ticketAttachmentLocalService.getTicketAttachments(
			zendeskTicketId, types);
	}

	/**
	 * Returns the number of ticket attachments.
	 *
	 * @return the number of ticket attachments
	 */
	@Override
	public int getTicketAttachmentsCount() {
		return _ticketAttachmentLocalService.getTicketAttachmentsCount();
	}

	@Override
	public int getTicketAttachmentsCount(long accountEntryId) {
		return _ticketAttachmentLocalService.getTicketAttachmentsCount(
			accountEntryId);
	}

	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment
			removeRegionRestriction(long ticketAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ticketAttachmentLocalService.removeRegionRestriction(
			ticketAttachmentId);
	}

	/**
	 * Updates the ticket attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TicketAttachmentLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param ticketAttachment the ticket attachment
	 * @return the ticket attachment that was updated
	 */
	@Override
	public com.liferay.osb.customer.ticket.model.TicketAttachment
		updateTicketAttachment(
			com.liferay.osb.customer.ticket.model.TicketAttachment
				ticketAttachment) {

		return _ticketAttachmentLocalService.updateTicketAttachment(
			ticketAttachment);
	}

	@Override
	public TicketAttachmentLocalService getWrappedService() {
		return _ticketAttachmentLocalService;
	}

	@Override
	public void setWrappedService(
		TicketAttachmentLocalService ticketAttachmentLocalService) {

		_ticketAttachmentLocalService = ticketAttachmentLocalService;
	}

	private TicketAttachmentLocalService _ticketAttachmentLocalService;

}