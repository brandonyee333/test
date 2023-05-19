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

package com.liferay.osb.customer.admin.service.persistence;

import com.liferay.osb.customer.admin.model.AccountAttachment;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the account attachment service. This utility wraps <code>com.liferay.osb.customer.admin.service.persistence.impl.AccountAttachmentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountAttachmentPersistence
 * @generated
 */
public class AccountAttachmentUtil {

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
	public static void clearCache(AccountAttachment accountAttachment) {
		getPersistence().clearCache(accountAttachment);
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
	public static Map<Serializable, AccountAttachment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AccountAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccountAttachment update(
		AccountAttachment accountAttachment) {

		return getPersistence().update(accountAttachment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccountAttachment update(
		AccountAttachment accountAttachment, ServiceContext serviceContext) {

		return getPersistence().update(accountAttachment, serviceContext);
	}

	/**
	 * Returns all the account attachments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account attachments
	 */
	public static List<AccountAttachment> findByAccountEntryId(
		long accountEntryId) {

		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns a range of all the account attachments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of matching account attachments
	 */
	public static List<AccountAttachment> findByAccountEntryId(
		long accountEntryId, int start, int end) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account attachments
	 */
	public static List<AccountAttachment> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account attachments
	 */
	public static List<AccountAttachment> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	public static AccountAttachment findByAccountEntryId_First(
			long accountEntryId,
			OrderByComparator<AccountAttachment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountAttachmentException {

		return getPersistence().findByAccountEntryId_First(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public static AccountAttachment fetchByAccountEntryId_First(
		long accountEntryId,
		OrderByComparator<AccountAttachment> orderByComparator) {

		return getPersistence().fetchByAccountEntryId_First(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	public static AccountAttachment findByAccountEntryId_Last(
			long accountEntryId,
			OrderByComparator<AccountAttachment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountAttachmentException {

		return getPersistence().findByAccountEntryId_Last(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public static AccountAttachment fetchByAccountEntryId_Last(
		long accountEntryId,
		OrderByComparator<AccountAttachment> orderByComparator) {

		return getPersistence().fetchByAccountEntryId_Last(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the account attachments before and after the current account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountAttachmentId the primary key of the current account attachment
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account attachment
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	public static AccountAttachment[] findByAccountEntryId_PrevAndNext(
			long accountAttachmentId, long accountEntryId,
			OrderByComparator<AccountAttachment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountAttachmentException {

		return getPersistence().findByAccountEntryId_PrevAndNext(
			accountAttachmentId, accountEntryId, orderByComparator);
	}

	/**
	 * Removes all the account attachments where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	public static void removeByAccountEntryId(long accountEntryId) {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns the number of account attachments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account attachments
	 */
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the matching account attachments
	 */
	public static List<AccountAttachment> findByAEI_API(
		long accountEntryId, long accountProjectId) {

		return getPersistence().findByAEI_API(accountEntryId, accountProjectId);
	}

	/**
	 * Returns a range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of matching account attachments
	 */
	public static List<AccountAttachment> findByAEI_API(
		long accountEntryId, long accountProjectId, int start, int end) {

		return getPersistence().findByAEI_API(
			accountEntryId, accountProjectId, start, end);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account attachments
	 */
	public static List<AccountAttachment> findByAEI_API(
		long accountEntryId, long accountProjectId, int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator) {

		return getPersistence().findByAEI_API(
			accountEntryId, accountProjectId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account attachments
	 */
	public static List<AccountAttachment> findByAEI_API(
		long accountEntryId, long accountProjectId, int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAEI_API(
			accountEntryId, accountProjectId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	public static AccountAttachment findByAEI_API_First(
			long accountEntryId, long accountProjectId,
			OrderByComparator<AccountAttachment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountAttachmentException {

		return getPersistence().findByAEI_API_First(
			accountEntryId, accountProjectId, orderByComparator);
	}

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public static AccountAttachment fetchByAEI_API_First(
		long accountEntryId, long accountProjectId,
		OrderByComparator<AccountAttachment> orderByComparator) {

		return getPersistence().fetchByAEI_API_First(
			accountEntryId, accountProjectId, orderByComparator);
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	public static AccountAttachment findByAEI_API_Last(
			long accountEntryId, long accountProjectId,
			OrderByComparator<AccountAttachment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountAttachmentException {

		return getPersistence().findByAEI_API_Last(
			accountEntryId, accountProjectId, orderByComparator);
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public static AccountAttachment fetchByAEI_API_Last(
		long accountEntryId, long accountProjectId,
		OrderByComparator<AccountAttachment> orderByComparator) {

		return getPersistence().fetchByAEI_API_Last(
			accountEntryId, accountProjectId, orderByComparator);
	}

	/**
	 * Returns the account attachments before and after the current account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountAttachmentId the primary key of the current account attachment
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account attachment
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	public static AccountAttachment[] findByAEI_API_PrevAndNext(
			long accountAttachmentId, long accountEntryId,
			long accountProjectId,
			OrderByComparator<AccountAttachment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountAttachmentException {

		return getPersistence().findByAEI_API_PrevAndNext(
			accountAttachmentId, accountEntryId, accountProjectId,
			orderByComparator);
	}

	/**
	 * Removes all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 */
	public static void removeByAEI_API(
		long accountEntryId, long accountProjectId) {

		getPersistence().removeByAEI_API(accountEntryId, accountProjectId);
	}

	/**
	 * Returns the number of account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the number of matching account attachments
	 */
	public static int countByAEI_API(
		long accountEntryId, long accountProjectId) {

		return getPersistence().countByAEI_API(
			accountEntryId, accountProjectId);
	}

	/**
	 * Returns all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @return the matching account attachments
	 */
	public static List<AccountAttachment> findByAEI_API_T(
		long accountEntryId, long accountProjectId, int type) {

		return getPersistence().findByAEI_API_T(
			accountEntryId, accountProjectId, type);
	}

	/**
	 * Returns a range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of matching account attachments
	 */
	public static List<AccountAttachment> findByAEI_API_T(
		long accountEntryId, long accountProjectId, int type, int start,
		int end) {

		return getPersistence().findByAEI_API_T(
			accountEntryId, accountProjectId, type, start, end);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account attachments
	 */
	public static List<AccountAttachment> findByAEI_API_T(
		long accountEntryId, long accountProjectId, int type, int start,
		int end, OrderByComparator<AccountAttachment> orderByComparator) {

		return getPersistence().findByAEI_API_T(
			accountEntryId, accountProjectId, type, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account attachments
	 */
	public static List<AccountAttachment> findByAEI_API_T(
		long accountEntryId, long accountProjectId, int type, int start,
		int end, OrderByComparator<AccountAttachment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAEI_API_T(
			accountEntryId, accountProjectId, type, start, end,
			orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	public static AccountAttachment findByAEI_API_T_First(
			long accountEntryId, long accountProjectId, int type,
			OrderByComparator<AccountAttachment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountAttachmentException {

		return getPersistence().findByAEI_API_T_First(
			accountEntryId, accountProjectId, type, orderByComparator);
	}

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public static AccountAttachment fetchByAEI_API_T_First(
		long accountEntryId, long accountProjectId, int type,
		OrderByComparator<AccountAttachment> orderByComparator) {

		return getPersistence().fetchByAEI_API_T_First(
			accountEntryId, accountProjectId, type, orderByComparator);
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	public static AccountAttachment findByAEI_API_T_Last(
			long accountEntryId, long accountProjectId, int type,
			OrderByComparator<AccountAttachment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountAttachmentException {

		return getPersistence().findByAEI_API_T_Last(
			accountEntryId, accountProjectId, type, orderByComparator);
	}

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public static AccountAttachment fetchByAEI_API_T_Last(
		long accountEntryId, long accountProjectId, int type,
		OrderByComparator<AccountAttachment> orderByComparator) {

		return getPersistence().fetchByAEI_API_T_Last(
			accountEntryId, accountProjectId, type, orderByComparator);
	}

	/**
	 * Returns the account attachments before and after the current account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountAttachmentId the primary key of the current account attachment
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account attachment
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	public static AccountAttachment[] findByAEI_API_T_PrevAndNext(
			long accountAttachmentId, long accountEntryId,
			long accountProjectId, int type,
			OrderByComparator<AccountAttachment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountAttachmentException {

		return getPersistence().findByAEI_API_T_PrevAndNext(
			accountAttachmentId, accountEntryId, accountProjectId, type,
			orderByComparator);
	}

	/**
	 * Removes all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 */
	public static void removeByAEI_API_T(
		long accountEntryId, long accountProjectId, int type) {

		getPersistence().removeByAEI_API_T(
			accountEntryId, accountProjectId, type);
	}

	/**
	 * Returns the number of account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @return the number of matching account attachments
	 */
	public static int countByAEI_API_T(
		long accountEntryId, long accountProjectId, int type) {

		return getPersistence().countByAEI_API_T(
			accountEntryId, accountProjectId, type);
	}

	/**
	 * Returns the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; or throws a <code>NoSuchAccountAttachmentException</code> if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	public static AccountAttachment findByAEI_API_FN_T(
			long accountEntryId, long accountProjectId, String fileName,
			int type)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountAttachmentException {

		return getPersistence().findByAEI_API_FN_T(
			accountEntryId, accountProjectId, fileName, type);
	}

	/**
	 * Returns the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public static AccountAttachment fetchByAEI_API_FN_T(
		long accountEntryId, long accountProjectId, String fileName, int type) {

		return getPersistence().fetchByAEI_API_FN_T(
			accountEntryId, accountProjectId, fileName, type);
	}

	/**
	 * Returns the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public static AccountAttachment fetchByAEI_API_FN_T(
		long accountEntryId, long accountProjectId, String fileName, int type,
		boolean useFinderCache) {

		return getPersistence().fetchByAEI_API_FN_T(
			accountEntryId, accountProjectId, fileName, type, useFinderCache);
	}

	/**
	 * Removes the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the account attachment that was removed
	 */
	public static AccountAttachment removeByAEI_API_FN_T(
			long accountEntryId, long accountProjectId, String fileName,
			int type)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountAttachmentException {

		return getPersistence().removeByAEI_API_FN_T(
			accountEntryId, accountProjectId, fileName, type);
	}

	/**
	 * Returns the number of account attachments where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the number of matching account attachments
	 */
	public static int countByAEI_API_FN_T(
		long accountEntryId, long accountProjectId, String fileName, int type) {

		return getPersistence().countByAEI_API_FN_T(
			accountEntryId, accountProjectId, fileName, type);
	}

	/**
	 * Caches the account attachment in the entity cache if it is enabled.
	 *
	 * @param accountAttachment the account attachment
	 */
	public static void cacheResult(AccountAttachment accountAttachment) {
		getPersistence().cacheResult(accountAttachment);
	}

	/**
	 * Caches the account attachments in the entity cache if it is enabled.
	 *
	 * @param accountAttachments the account attachments
	 */
	public static void cacheResult(List<AccountAttachment> accountAttachments) {
		getPersistence().cacheResult(accountAttachments);
	}

	/**
	 * Creates a new account attachment with the primary key. Does not add the account attachment to the database.
	 *
	 * @param accountAttachmentId the primary key for the new account attachment
	 * @return the new account attachment
	 */
	public static AccountAttachment create(long accountAttachmentId) {
		return getPersistence().create(accountAttachmentId);
	}

	/**
	 * Removes the account attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment that was removed
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	public static AccountAttachment remove(long accountAttachmentId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountAttachmentException {

		return getPersistence().remove(accountAttachmentId);
	}

	public static AccountAttachment updateImpl(
		AccountAttachment accountAttachment) {

		return getPersistence().updateImpl(accountAttachment);
	}

	/**
	 * Returns the account attachment with the primary key or throws a <code>NoSuchAccountAttachmentException</code> if it could not be found.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	public static AccountAttachment findByPrimaryKey(long accountAttachmentId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountAttachmentException {

		return getPersistence().findByPrimaryKey(accountAttachmentId);
	}

	/**
	 * Returns the account attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment, or <code>null</code> if a account attachment with the primary key could not be found
	 */
	public static AccountAttachment fetchByPrimaryKey(
		long accountAttachmentId) {

		return getPersistence().fetchByPrimaryKey(accountAttachmentId);
	}

	/**
	 * Returns all the account attachments.
	 *
	 * @return the account attachments
	 */
	public static List<AccountAttachment> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the account attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of account attachments
	 */
	public static List<AccountAttachment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the account attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account attachments
	 */
	public static List<AccountAttachment> findAll(
		int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of account attachments
	 */
	public static List<AccountAttachment> findAll(
		int start, int end,
		OrderByComparator<AccountAttachment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the account attachments from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of account attachments.
	 *
	 * @return the number of account attachments
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AccountAttachmentPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		AccountAttachmentPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile AccountAttachmentPersistence _persistence;

}