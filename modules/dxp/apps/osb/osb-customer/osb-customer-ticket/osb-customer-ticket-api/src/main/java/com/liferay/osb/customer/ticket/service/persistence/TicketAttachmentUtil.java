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

import com.liferay.osb.customer.ticket.model.TicketAttachment;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the ticket attachment service. This utility wraps <code>com.liferay.osb.customer.ticket.service.persistence.impl.TicketAttachmentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketAttachmentPersistence
 * @generated
 */
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
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, TicketAttachment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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
	public static TicketAttachment update(
		TicketAttachment ticketAttachment, ServiceContext serviceContext) {

		return getPersistence().update(ticketAttachment, serviceContext);
	}

	/**
	 * Returns all the ticket attachments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching ticket attachments
	 */
	public static List<TicketAttachment> findByAccountEntryId(
		long accountEntryId) {

		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns a range of all the ticket attachments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	public static List<TicketAttachment> findByAccountEntryId(
		long accountEntryId, int start, int end) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	public static List<TicketAttachment> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	public static List<TicketAttachment> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first ticket attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	public static TicketAttachment findByAccountEntryId_First(
			long accountEntryId,
			OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.customer.ticket.exception.
			NoSuchTicketAttachmentException {

		return getPersistence().findByAccountEntryId_First(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the first ticket attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	public static TicketAttachment fetchByAccountEntryId_First(
		long accountEntryId,
		OrderByComparator<TicketAttachment> orderByComparator) {

		return getPersistence().fetchByAccountEntryId_First(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the last ticket attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	public static TicketAttachment findByAccountEntryId_Last(
			long accountEntryId,
			OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.customer.ticket.exception.
			NoSuchTicketAttachmentException {

		return getPersistence().findByAccountEntryId_Last(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the last ticket attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	public static TicketAttachment fetchByAccountEntryId_Last(
		long accountEntryId,
		OrderByComparator<TicketAttachment> orderByComparator) {

		return getPersistence().fetchByAccountEntryId_Last(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	public static TicketAttachment[] findByAccountEntryId_PrevAndNext(
			long ticketAttachmentId, long accountEntryId,
			OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.customer.ticket.exception.
			NoSuchTicketAttachmentException {

		return getPersistence().findByAccountEntryId_PrevAndNext(
			ticketAttachmentId, accountEntryId, orderByComparator);
	}

	/**
	 * Removes all the ticket attachments where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	public static void removeByAccountEntryId(long accountEntryId) {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns the number of ticket attachments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching ticket attachments
	 */
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	public static List<TicketAttachment> findByZendeskTicketId(
		long zendeskTicketId, int start, int end) {

		return getPersistence().findByZendeskTicketId(
			zendeskTicketId, start, end);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
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

		return getPersistence().findByZendeskTicketId(
			zendeskTicketId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	public static List<TicketAttachment> findByZendeskTicketId(
		long zendeskTicketId, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByZendeskTicketId(
			zendeskTicketId, start, end, orderByComparator, useFinderCache);
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
		throws com.liferay.osb.customer.ticket.exception.
			NoSuchTicketAttachmentException {

		return getPersistence().findByZendeskTicketId_First(
			zendeskTicketId, orderByComparator);
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

		return getPersistence().fetchByZendeskTicketId_First(
			zendeskTicketId, orderByComparator);
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
		throws com.liferay.osb.customer.ticket.exception.
			NoSuchTicketAttachmentException {

		return getPersistence().findByZendeskTicketId_Last(
			zendeskTicketId, orderByComparator);
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

		return getPersistence().fetchByZendeskTicketId_Last(
			zendeskTicketId, orderByComparator);
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
		throws com.liferay.osb.customer.ticket.exception.
			NoSuchTicketAttachmentException {

		return getPersistence().findByZendeskTicketId_PrevAndNext(
			ticketAttachmentId, zendeskTicketId, orderByComparator);
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
	 * Returns all the ticket attachments where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @return the matching ticket attachments
	 */
	public static List<TicketAttachment> findByZTI_T(
		long zendeskTicketId, int type) {

		return getPersistence().findByZTI_T(zendeskTicketId, type);
	}

	/**
	 * Returns a range of all the ticket attachments where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	public static List<TicketAttachment> findByZTI_T(
		long zendeskTicketId, int type, int start, int end) {

		return getPersistence().findByZTI_T(zendeskTicketId, type, start, end);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	public static List<TicketAttachment> findByZTI_T(
		long zendeskTicketId, int type, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {

		return getPersistence().findByZTI_T(
			zendeskTicketId, type, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	public static List<TicketAttachment> findByZTI_T(
		long zendeskTicketId, int type, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByZTI_T(
			zendeskTicketId, type, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first ticket attachment in the ordered set where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	public static TicketAttachment findByZTI_T_First(
			long zendeskTicketId, int type,
			OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.customer.ticket.exception.
			NoSuchTicketAttachmentException {

		return getPersistence().findByZTI_T_First(
			zendeskTicketId, type, orderByComparator);
	}

	/**
	 * Returns the first ticket attachment in the ordered set where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	public static TicketAttachment fetchByZTI_T_First(
		long zendeskTicketId, int type,
		OrderByComparator<TicketAttachment> orderByComparator) {

		return getPersistence().fetchByZTI_T_First(
			zendeskTicketId, type, orderByComparator);
	}

	/**
	 * Returns the last ticket attachment in the ordered set where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment
	 * @throws NoSuchTicketAttachmentException if a matching ticket attachment could not be found
	 */
	public static TicketAttachment findByZTI_T_Last(
			long zendeskTicketId, int type,
			OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.customer.ticket.exception.
			NoSuchTicketAttachmentException {

		return getPersistence().findByZTI_T_Last(
			zendeskTicketId, type, orderByComparator);
	}

	/**
	 * Returns the last ticket attachment in the ordered set where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching ticket attachment, or <code>null</code> if a matching ticket attachment could not be found
	 */
	public static TicketAttachment fetchByZTI_T_Last(
		long zendeskTicketId, int type,
		OrderByComparator<TicketAttachment> orderByComparator) {

		return getPersistence().fetchByZTI_T_Last(
			zendeskTicketId, type, orderByComparator);
	}

	/**
	 * Returns the ticket attachments before and after the current ticket attachment in the ordered set where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * @param ticketAttachmentId the primary key of the current ticket attachment
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next ticket attachment
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	public static TicketAttachment[] findByZTI_T_PrevAndNext(
			long ticketAttachmentId, long zendeskTicketId, int type,
			OrderByComparator<TicketAttachment> orderByComparator)
		throws com.liferay.osb.customer.ticket.exception.
			NoSuchTicketAttachmentException {

		return getPersistence().findByZTI_T_PrevAndNext(
			ticketAttachmentId, zendeskTicketId, type, orderByComparator);
	}

	/**
	 * Returns all the ticket attachments where zendeskTicketId = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param types the types
	 * @return the matching ticket attachments
	 */
	public static List<TicketAttachment> findByZTI_T(
		long zendeskTicketId, int[] types) {

		return getPersistence().findByZTI_T(zendeskTicketId, types);
	}

	/**
	 * Returns a range of all the ticket attachments where zendeskTicketId = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param types the types
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @return the range of matching ticket attachments
	 */
	public static List<TicketAttachment> findByZTI_T(
		long zendeskTicketId, int[] types, int start, int end) {

		return getPersistence().findByZTI_T(zendeskTicketId, types, start, end);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63; and type = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param types the types
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching ticket attachments
	 */
	public static List<TicketAttachment> findByZTI_T(
		long zendeskTicketId, int[] types, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {

		return getPersistence().findByZTI_T(
			zendeskTicketId, types, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ticket attachments where zendeskTicketId = &#63; and type = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param types the types
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching ticket attachments
	 */
	public static List<TicketAttachment> findByZTI_T(
		long zendeskTicketId, int[] types, int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByZTI_T(
			zendeskTicketId, types, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Removes all the ticket attachments where zendeskTicketId = &#63; and type = &#63; from the database.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 */
	public static void removeByZTI_T(long zendeskTicketId, int type) {
		getPersistence().removeByZTI_T(zendeskTicketId, type);
	}

	/**
	 * Returns the number of ticket attachments where zendeskTicketId = &#63; and type = &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param type the type
	 * @return the number of matching ticket attachments
	 */
	public static int countByZTI_T(long zendeskTicketId, int type) {
		return getPersistence().countByZTI_T(zendeskTicketId, type);
	}

	/**
	 * Returns the number of ticket attachments where zendeskTicketId = &#63; and type = any &#63;.
	 *
	 * @param zendeskTicketId the zendesk ticket ID
	 * @param types the types
	 * @return the number of matching ticket attachments
	 */
	public static int countByZTI_T(long zendeskTicketId, int[] types) {
		return getPersistence().countByZTI_T(zendeskTicketId, types);
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
		throws com.liferay.osb.customer.ticket.exception.
			NoSuchTicketAttachmentException {

		return getPersistence().remove(ticketAttachmentId);
	}

	public static TicketAttachment updateImpl(
		TicketAttachment ticketAttachment) {

		return getPersistence().updateImpl(ticketAttachment);
	}

	/**
	 * Returns the ticket attachment with the primary key or throws a <code>NoSuchTicketAttachmentException</code> if it could not be found.
	 *
	 * @param ticketAttachmentId the primary key of the ticket attachment
	 * @return the ticket attachment
	 * @throws NoSuchTicketAttachmentException if a ticket attachment with the primary key could not be found
	 */
	public static TicketAttachment findByPrimaryKey(long ticketAttachmentId)
		throws com.liferay.osb.customer.ticket.exception.
			NoSuchTicketAttachmentException {

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ticket attachments
	 */
	public static List<TicketAttachment> findAll(
		int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ticket attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>TicketAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ticket attachments
	 * @param end the upper bound of the range of ticket attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ticket attachments
	 */
	public static List<TicketAttachment> findAll(
		int start, int end,
		OrderByComparator<TicketAttachment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
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

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TicketAttachmentPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(TicketAttachmentPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile TicketAttachmentPersistence _persistence;

}