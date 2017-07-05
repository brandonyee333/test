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

package com.liferay.osb.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.AccountEnvironmentAttachment;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the account environment attachment service. This utility wraps {@link com.liferay.osb.service.persistence.impl.AccountEnvironmentAttachmentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachmentPersistence
 * @see com.liferay.osb.service.persistence.impl.AccountEnvironmentAttachmentPersistenceImpl
 * @generated
 */
@ProviderType
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
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return getPersistence()
				   .update(accountEnvironmentAttachment, serviceContext);
	}

	/**
	* Returns all the account environment attachments where accountEnvironmentId = &#63;.
	*
	* @param accountEnvironmentId the account environment ID
	* @return the matching account environment attachments
	*/
	public static List<AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId) {
		return getPersistence().findByAccountEnvironmentId(accountEnvironmentId);
	}

	/**
	* Returns a range of all the account environment attachments where accountEnvironmentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEnvironmentId the account environment ID
	* @param start the lower bound of the range of account environment attachments
	* @param end the upper bound of the range of account environment attachments (not inclusive)
	* @return the range of matching account environment attachments
	*/
	public static List<AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId, int start, int end) {
		return getPersistence()
				   .findByAccountEnvironmentId(accountEnvironmentId, start, end);
	}

	/**
	* Returns an ordered range of all the account environment attachments where accountEnvironmentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return getPersistence()
				   .findByAccountEnvironmentId(accountEnvironmentId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account environment attachments where accountEnvironmentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEnvironmentId the account environment ID
	* @param start the lower bound of the range of account environment attachments
	* @param end the upper bound of the range of account environment attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account environment attachments
	*/
	public static List<AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId, int start, int end,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountEnvironmentId(accountEnvironmentId, start,
			end, orderByComparator, retrieveFromCache);
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
		throws com.liferay.osb.exception.NoSuchAccountEnvironmentAttachmentException {
		return getPersistence()
				   .findByAccountEnvironmentId_First(accountEnvironmentId,
			orderByComparator);
	}

	/**
	* Returns the first account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	*
	* @param accountEnvironmentId the account environment ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	*/
	public static AccountEnvironmentAttachment fetchByAccountEnvironmentId_First(
		long accountEnvironmentId,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEnvironmentId_First(accountEnvironmentId,
			orderByComparator);
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
		throws com.liferay.osb.exception.NoSuchAccountEnvironmentAttachmentException {
		return getPersistence()
				   .findByAccountEnvironmentId_Last(accountEnvironmentId,
			orderByComparator);
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
		return getPersistence()
				   .fetchByAccountEnvironmentId_Last(accountEnvironmentId,
			orderByComparator);
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
	public static AccountEnvironmentAttachment[] findByAccountEnvironmentId_PrevAndNext(
		long accountEnvironmentAttachmentId, long accountEnvironmentId,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEnvironmentAttachmentException {
		return getPersistence()
				   .findByAccountEnvironmentId_PrevAndNext(accountEnvironmentAttachmentId,
			accountEnvironmentId, orderByComparator);
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
		return getPersistence().countByAccountEnvironmentId(accountEnvironmentId);
	}

	/**
	* Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or throws a {@link NoSuchAccountEnvironmentAttachmentException} if it could not be found.
	*
	* @param accountEnvironmentId the account environment ID
	* @param fileName the file name
	* @return the matching account environment attachment
	* @throws NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	*/
	public static AccountEnvironmentAttachment findByAEI_FN(
		long accountEnvironmentId, java.lang.String fileName)
		throws com.liferay.osb.exception.NoSuchAccountEnvironmentAttachmentException {
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
		long accountEnvironmentId, java.lang.String fileName) {
		return getPersistence().fetchByAEI_FN(accountEnvironmentId, fileName);
	}

	/**
	* Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountEnvironmentId the account environment ID
	* @param fileName the file name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	*/
	public static AccountEnvironmentAttachment fetchByAEI_FN(
		long accountEnvironmentId, java.lang.String fileName,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByAEI_FN(accountEnvironmentId, fileName,
			retrieveFromCache);
	}

	/**
	* Removes the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; from the database.
	*
	* @param accountEnvironmentId the account environment ID
	* @param fileName the file name
	* @return the account environment attachment that was removed
	*/
	public static AccountEnvironmentAttachment removeByAEI_FN(
		long accountEnvironmentId, java.lang.String fileName)
		throws com.liferay.osb.exception.NoSuchAccountEnvironmentAttachmentException {
		return getPersistence().removeByAEI_FN(accountEnvironmentId, fileName);
	}

	/**
	* Returns the number of account environment attachments where accountEnvironmentId = &#63; and fileName = &#63;.
	*
	* @param accountEnvironmentId the account environment ID
	* @param fileName the file name
	* @return the number of matching account environment attachments
	*/
	public static int countByAEI_FN(long accountEnvironmentId,
		java.lang.String fileName) {
		return getPersistence().countByAEI_FN(accountEnvironmentId, fileName);
	}

	/**
	* Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or throws a {@link NoSuchAccountEnvironmentAttachmentException} if it could not be found.
	*
	* @param accountEnvironmentId the account environment ID
	* @param type the type
	* @return the matching account environment attachment
	* @throws NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	*/
	public static AccountEnvironmentAttachment findByAEI_T(
		long accountEnvironmentId, int type)
		throws com.liferay.osb.exception.NoSuchAccountEnvironmentAttachmentException {
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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	*/
	public static AccountEnvironmentAttachment fetchByAEI_T(
		long accountEnvironmentId, int type, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByAEI_T(accountEnvironmentId, type, retrieveFromCache);
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
		throws com.liferay.osb.exception.NoSuchAccountEnvironmentAttachmentException {
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
		throws com.liferay.osb.exception.NoSuchAccountEnvironmentAttachmentException {
		return getPersistence().remove(accountEnvironmentAttachmentId);
	}

	public static AccountEnvironmentAttachment updateImpl(
		AccountEnvironmentAttachment accountEnvironmentAttachment) {
		return getPersistence().updateImpl(accountEnvironmentAttachment);
	}

	/**
	* Returns the account environment attachment with the primary key or throws a {@link NoSuchAccountEnvironmentAttachmentException} if it could not be found.
	*
	* @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	* @return the account environment attachment
	* @throws NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	*/
	public static AccountEnvironmentAttachment findByPrimaryKey(
		long accountEnvironmentAttachmentId)
		throws com.liferay.osb.exception.NoSuchAccountEnvironmentAttachmentException {
		return getPersistence().findByPrimaryKey(accountEnvironmentAttachmentId);
	}

	/**
	* Returns the account environment attachment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	* @return the account environment attachment, or <code>null</code> if a account environment attachment with the primary key could not be found
	*/
	public static AccountEnvironmentAttachment fetchByPrimaryKey(
		long accountEnvironmentAttachmentId) {
		return getPersistence().fetchByPrimaryKey(accountEnvironmentAttachmentId);
	}

	public static java.util.Map<java.io.Serializable, AccountEnvironmentAttachment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account environment attachments
	* @param end the upper bound of the range of account environment attachments (not inclusive)
	* @return the range of account environment attachments
	*/
	public static List<AccountEnvironmentAttachment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the account environment attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account environment attachments
	* @param end the upper bound of the range of account environment attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account environment attachments
	*/
	public static List<AccountEnvironmentAttachment> findAll(int start,
		int end,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account environment attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account environment attachments
	* @param end the upper bound of the range of account environment attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of account environment attachments
	*/
	public static List<AccountEnvironmentAttachment> findAll(int start,
		int end,
		OrderByComparator<AccountEnvironmentAttachment> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
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

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AccountEnvironmentAttachmentPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AccountEnvironmentAttachmentPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AccountEnvironmentAttachmentPersistence.class.getName());

			ReferenceRegistry.registerReference(AccountEnvironmentAttachmentUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static AccountEnvironmentAttachmentPersistence _persistence;
}