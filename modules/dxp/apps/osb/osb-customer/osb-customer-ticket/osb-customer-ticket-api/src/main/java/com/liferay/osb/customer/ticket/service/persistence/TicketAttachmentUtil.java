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

package com.liferay.osb.customer.ticket.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.ticket.model.TicketAttachment;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the ticket attachment service. This utility wraps {@link com.liferay.osb.customer.ticket.service.persistence.impl.TicketAttachmentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentPersistence
 * @see com.liferay.osb.customer.ticket.service.persistence.impl.TicketAttachmentPersistenceImpl
 * @generated
 */
@ProviderType
public class TicketAttachmentUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(TicketAttachment ticketAttachment) {
		getPersistence().clearCache(ticketAttachment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TicketAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TicketAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TicketAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TicketAttachment update(TicketAttachment ticketAttachment) {
		return getPersistence().update(ticketAttachment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TicketAttachment update(TicketAttachment ticketAttachment,
		ServiceContext serviceContext) {
		return getPersistence().update(ticketAttachment, serviceContext);
	}

	/**
	* Returns all the ticket attachments where zendeskTicketId = &#63;.
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @return the matching ticket attachments
	*/
	public static List<TicketAttachment> findByZendeskTicketId(
		long zendeskTicketId) {
		return getPersistence().findByZendeskTicketId(zendeskTicketId);
	}

	/**
	* Returns a range of all the ticket attachments where zendeskTicketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByZendeskTicketId(
		long zendeskTicketId, int start, int end) {
		return getPersistence()
				   .findByZendeskTicketId(zendeskTicketId, start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByZendeskTicketId(
		long zendeskTicketId, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .findByZendeskTicketId(zendeskTicketId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket attachments
	*/
	public static List<TicketAttachment> findByZendeskTicketId(
		long zendeskTicketId, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByZendeskTicketId(zendeskTicketId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket attachment in the ordered set where zendeskTicketId = &#63;.
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByZendeskTicketId_First(
		long zendeskTicketId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.customer.ticket.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByZendeskTicketId_First(zendeskTicketId,
			orderByComparator);
	}

	/**
	* Returns the first ticket attachment in the ordered set where zendeskTicketId = &#63;.
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByZendeskTicketId_First(
		long zendeskTicketId,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByZendeskTicketId_First(zendeskTicketId,
			orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where zendeskTicketId = &#63;.
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment
	* @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	*/
	public static TicketAttachment findByZendeskTicketId_Last(
		long zendeskTicketId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.customer.ticket.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByZendeskTicketId_Last(zendeskTicketId,
			orderByComparator);
	}

	/**
	* Returns the last ticket attachment in the ordered set where zendeskTicketId = &#63;.
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	*/
	public static TicketAttachment fetchByZendeskTicketId_Last(
		long zendeskTicketId,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByZendeskTicketId_Last(zendeskTicketId,
			orderByComparator);
	}

	/**
	* Returns the ticket attachments before and after the current ticket attachment in the ordered set where zendeskTicketId = &#63;.
	*
	* @param ticketAttachmentId the primary key of the current ticket attachment
	* @param zendeskTicketId the zendesk ticket ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket attachment
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public static TicketAttachment[] findByZendeskTicketId_PrevAndNext(
		long ticketAttachmentId, long zendeskTicketId,
		OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.customer.ticket.exception.NoSuchTicketAttachmentException {
		return getPersistence()
				   .findByZendeskTicketId_PrevAndNext(ticketAttachmentId,
			zendeskTicketId, orderByComparator);
	}

	/**
	* Removes all the ticket attachments where zendeskTicketId = &#63; from the database.
	*
	* @param zendeskTicketId the zendesk ticket ID
	*/
	public static void removeByZendeskTicketId(long zendeskTicketId) {
		getPersistence().removeByZendeskTicketId(zendeskTicketId);
	}

	/**
	* Returns the number of ticket attachments where zendeskTicketId = &#63;.
	*
	* @param zendeskTicketId the zendesk ticket ID
	* @return the number of matching ticket attachments
	*/
	public static int countByZendeskTicketId(long zendeskTicketId) {
		return getPersistence().countByZendeskTicketId(zendeskTicketId);
	}

	/**
	* Caches the ticket attachment in the entity cache if it is enabled.
	*
	* @param ticketAttachment the ticket attachment
	*/
	public static void cacheResult(TicketAttachment ticketAttachment) {
		getPersistence().cacheResult(ticketAttachment);
	}

	/**
	* Caches the ticket attachments in the entity cache if it is enabled.
	*
	* @param ticketAttachments the ticket attachments
	*/
	public static void cacheResult(List<TicketAttachment> ticketAttachments) {
		getPersistence().cacheResult(ticketAttachments);
	}

	/**
	* Creates a new ticket attachment with the primary key. Does not add the ticket attachment to the database.
	*
	* @param ticketAttachmentId the primary key for the new ticket attachment
	* @return the new ticket attachment
	*/
	public static TicketAttachment create(long ticketAttachmentId) {
		return getPersistence().create(ticketAttachmentId);
	}

	/**
	* Removes the ticket attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment that was removed
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public static TicketAttachment remove(long ticketAttachmentId)
		throws com.liferay.osb.customer.ticket.exception.NoSuchTicketAttachmentException {
		return getPersistence().remove(ticketAttachmentId);
	}

	public static TicketAttachment updateImpl(TicketAttachment ticketAttachment) {
		return getPersistence().updateImpl(ticketAttachment);
	}

	/**
	* Returns the ticket attachment with the primary key or throws a {@link NoSuchTicketAttachmentException} if it could not be found.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment
	* @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	*/
	public static TicketAttachment findByPrimaryKey(long ticketAttachmentId)
		throws com.liferay.osb.customer.ticket.exception.NoSuchTicketAttachmentException {
		return getPersistence().findByPrimaryKey(ticketAttachmentId);
	}

	/**
	* Returns the ticket attachment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketAttachmentId the primary key of the ticket attachment
	* @return the ticket attachment, or <code>null</code> if a ticket attachment with the primary key could not be found
	*/
	public static TicketAttachment fetchByPrimaryKey(long ticketAttachmentId) {
		return getPersistence().fetchByPrimaryKey(ticketAttachmentId);
	}

	public static java.util.Map<java.io.Serializable, TicketAttachment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ticket attachments.
	*
	* @return the ticket attachments
	*/
	public static List<TicketAttachment> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ticket attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @return the range of ticket attachments
	*/
	public static List<TicketAttachment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ticket attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket attachments
	*/
	public static List<TicketAttachment> findAll(int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket attachments
	* @param end the upper bound of the range of ticket attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ticket attachments
	*/
	public static List<TicketAttachment> findAll(int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket attachments from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ticket attachments.
	*
	* @return the number of ticket attachments
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TicketAttachmentPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TicketAttachmentPersistence, TicketAttachmentPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TicketAttachmentPersistence.class);

		ServiceTracker<TicketAttachmentPersistence, TicketAttachmentPersistence> serviceTracker =
			new ServiceTracker<TicketAttachmentPersistence, TicketAttachmentPersistence>(bundle.getBundleContext(),
				TicketAttachmentPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}