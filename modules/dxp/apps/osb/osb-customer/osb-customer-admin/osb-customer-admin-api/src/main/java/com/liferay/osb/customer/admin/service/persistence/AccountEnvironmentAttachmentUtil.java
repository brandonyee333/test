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

import com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the account environment attachment service. This utility wraps <code>com.liferay.osb.customer.admin.service.persistence.impl.AccountEnvironmentAttachmentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachmentPersistence
 * @generated
 */
public class AccountEnvironmentAttachmentUtil {

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
	public static void clearCache(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {

		getPersistence().clearCache(accountEnvironmentAttachment);
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
	public static Map<Serializable, AccountEnvironmentAttachment>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AccountEnvironmentAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountEnvironmentAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountEnvironmentAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccountEnvironmentAttachment update(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {

		return getPersistence().update(accountEnvironmentAttachment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccountEnvironmentAttachment update(
		AccountEnvironmentAttachment accountEnvironmentAttachment,
		ServiceContext serviceContext) {

		return getPersistence().update(
			accountEnvironmentAttachment, serviceContext);
	}

	/**
	 * Returns all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @return the matching account environment attachments
	 */
	public static List<AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId) {

		return getPersistence().findByAccountEnvironmentId(
			accountEnvironmentId);
	}

	/**
	 * Returns a range of all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @return the range of matching account environment attachments
	 */
	public static List<AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId, int start, int end) {

		return getPersistence().findByAccountEnvironmentId(
			accountEnvironmentId, start, end);
	}

	/**
	 * Returns an ordered range of all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account environment attachments
	 */
	public static List<AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId, int start, int end,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator) {

		return getPersistence().findByAccountEnvironmentId(
			accountEnvironmentId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account environment attachments
	 */
	public static List<AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId, int start, int end,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAccountEnvironmentId(
			accountEnvironmentId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 */
	public static AccountEnvironmentAttachment findByAccountEnvironmentId_First(
			long accountEnvironmentId,
			OrderByComparator<AccountEnvironmentAttachment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentAttachmentException {

		return getPersistence().findByAccountEnvironmentId_First(
			accountEnvironmentId, orderByComparator);
	}

	/**
	 * Returns the first account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	public static AccountEnvironmentAttachment
		fetchByAccountEnvironmentId_First(
			long accountEnvironmentId,
			OrderByComparator<AccountEnvironmentAttachment> orderByComparator) {

		return getPersistence().fetchByAccountEnvironmentId_First(
			accountEnvironmentId, orderByComparator);
	}

	/**
	 * Returns the last account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 */
	public static AccountEnvironmentAttachment findByAccountEnvironmentId_Last(
			long accountEnvironmentId,
			OrderByComparator<AccountEnvironmentAttachment> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentAttachmentException {

		return getPersistence().findByAccountEnvironmentId_Last(
			accountEnvironmentId, orderByComparator);
	}

	/**
	 * Returns the last account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	public static AccountEnvironmentAttachment fetchByAccountEnvironmentId_Last(
		long accountEnvironmentId,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator) {

		return getPersistence().fetchByAccountEnvironmentId_Last(
			accountEnvironmentId, orderByComparator);
	}

	/**
	 * Returns the account environment attachments before and after the current account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the current account environment attachment
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 */
	public static AccountEnvironmentAttachment[]
			findByAccountEnvironmentId_PrevAndNext(
				long accountEnvironmentAttachmentId, long accountEnvironmentId,
				OrderByComparator<AccountEnvironmentAttachment>
					orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentAttachmentException {

		return getPersistence().findByAccountEnvironmentId_PrevAndNext(
			accountEnvironmentAttachmentId, accountEnvironmentId,
			orderByComparator);
	}

	/**
	 * Removes all the account environment attachments where accountEnvironmentId = &#63; from the database.
	 *
	 * @param accountEnvironmentId the account environment ID
	 */
	public static void removeByAccountEnvironmentId(long accountEnvironmentId) {
		getPersistence().removeByAccountEnvironmentId(accountEnvironmentId);
	}

	/**
	 * Returns the number of account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @return the number of matching account environment attachments
	 */
	public static int countByAccountEnvironmentId(long accountEnvironmentId) {
		return getPersistence().countByAccountEnvironmentId(
			accountEnvironmentId);
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or throws a <code>NoSuchAccountEnvironmentAttachmentException</code> if it could not be found.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the matching account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 */
	public static AccountEnvironmentAttachment findByAEI_FN(
			long accountEnvironmentId, String fileName)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentAttachmentException {

		return getPersistence().findByAEI_FN(accountEnvironmentId, fileName);
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	public static AccountEnvironmentAttachment fetchByAEI_FN(
		long accountEnvironmentId, String fileName) {

		return getPersistence().fetchByAEI_FN(accountEnvironmentId, fileName);
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	public static AccountEnvironmentAttachment fetchByAEI_FN(
		long accountEnvironmentId, String fileName, boolean useFinderCache) {

		return getPersistence().fetchByAEI_FN(
			accountEnvironmentId, fileName, useFinderCache);
	}

	/**
	 * Removes the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; from the database.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the account environment attachment that was removed
	 */
	public static AccountEnvironmentAttachment removeByAEI_FN(
			long accountEnvironmentId, String fileName)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentAttachmentException {

		return getPersistence().removeByAEI_FN(accountEnvironmentId, fileName);
	}

	/**
	 * Returns the number of account environment attachments where accountEnvironmentId = &#63; and fileName = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the number of matching account environment attachments
	 */
	public static int countByAEI_FN(
		long accountEnvironmentId, String fileName) {

		return getPersistence().countByAEI_FN(accountEnvironmentId, fileName);
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or throws a <code>NoSuchAccountEnvironmentAttachmentException</code> if it could not be found.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the matching account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 */
	public static AccountEnvironmentAttachment findByAEI_T(
			long accountEnvironmentId, int type)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentAttachmentException {

		return getPersistence().findByAEI_T(accountEnvironmentId, type);
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	public static AccountEnvironmentAttachment fetchByAEI_T(
		long accountEnvironmentId, int type) {

		return getPersistence().fetchByAEI_T(accountEnvironmentId, type);
	}

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	public static AccountEnvironmentAttachment fetchByAEI_T(
		long accountEnvironmentId, int type, boolean useFinderCache) {

		return getPersistence().fetchByAEI_T(
			accountEnvironmentId, type, useFinderCache);
	}

	/**
	 * Removes the account environment attachment where accountEnvironmentId = &#63; and type = &#63; from the database.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the account environment attachment that was removed
	 */
	public static AccountEnvironmentAttachment removeByAEI_T(
			long accountEnvironmentId, int type)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentAttachmentException {

		return getPersistence().removeByAEI_T(accountEnvironmentId, type);
	}

	/**
	 * Returns the number of account environment attachments where accountEnvironmentId = &#63; and type = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the number of matching account environment attachments
	 */
	public static int countByAEI_T(long accountEnvironmentId, int type) {
		return getPersistence().countByAEI_T(accountEnvironmentId, type);
	}

	/**
	 * Caches the account environment attachment in the entity cache if it is enabled.
	 *
	 * @param accountEnvironmentAttachment the account environment attachment
	 */
	public static void cacheResult(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {

		getPersistence().cacheResult(accountEnvironmentAttachment);
	}

	/**
	 * Caches the account environment attachments in the entity cache if it is enabled.
	 *
	 * @param accountEnvironmentAttachments the account environment attachments
	 */
	public static void cacheResult(
		List<AccountEnvironmentAttachment> accountEnvironmentAttachments) {

		getPersistence().cacheResult(accountEnvironmentAttachments);
	}

	/**
	 * Creates a new account environment attachment with the primary key. Does not add the account environment attachment to the database.
	 *
	 * @param accountEnvironmentAttachmentId the primary key for the new account environment attachment
	 * @return the new account environment attachment
	 */
	public static AccountEnvironmentAttachment create(
		long accountEnvironmentAttachmentId) {

		return getPersistence().create(accountEnvironmentAttachmentId);
	}

	/**
	 * Removes the account environment attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment that was removed
	 * @throws NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 */
	public static AccountEnvironmentAttachment remove(
			long accountEnvironmentAttachmentId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentAttachmentException {

		return getPersistence().remove(accountEnvironmentAttachmentId);
	}

	public static AccountEnvironmentAttachment updateImpl(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {

		return getPersistence().updateImpl(accountEnvironmentAttachment);
	}

	/**
	 * Returns the account environment attachment with the primary key or throws a <code>NoSuchAccountEnvironmentAttachmentException</code> if it could not be found.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 */
	public static AccountEnvironmentAttachment findByPrimaryKey(
			long accountEnvironmentAttachmentId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEnvironmentAttachmentException {

		return getPersistence().findByPrimaryKey(
			accountEnvironmentAttachmentId);
	}

	/**
	 * Returns the account environment attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment, or <code>null</code> if a account environment attachment with the primary key could not be found
	 */
	public static AccountEnvironmentAttachment fetchByPrimaryKey(
		long accountEnvironmentAttachmentId) {

		return getPersistence().fetchByPrimaryKey(
			accountEnvironmentAttachmentId);
	}

	/**
	 * Returns all the account environment attachments.
	 *
	 * @return the account environment attachments
	 */
	public static List<AccountEnvironmentAttachment> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the account environment attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @return the range of account environment attachments
	 */
	public static List<AccountEnvironmentAttachment> findAll(
		int start, int end) {

		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the account environment attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account environment attachments
	 */
	public static List<AccountEnvironmentAttachment> findAll(
		int start, int end,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account environment attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of account environment attachments
	 */
	public static List<AccountEnvironmentAttachment> findAll(
		int start, int end,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the account environment attachments from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of account environment attachments.
	 *
	 * @return the number of account environment attachments
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AccountEnvironmentAttachmentPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		AccountEnvironmentAttachmentPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile AccountEnvironmentAttachmentPersistence
		_persistence;

}