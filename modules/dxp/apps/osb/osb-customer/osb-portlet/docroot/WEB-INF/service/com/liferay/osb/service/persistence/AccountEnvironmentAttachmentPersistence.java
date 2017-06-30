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

import com.liferay.osb.model.AccountEnvironmentAttachment;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the account environment attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachmentPersistenceImpl
 * @see AccountEnvironmentAttachmentUtil
 * @generated
 */
public interface AccountEnvironmentAttachmentPersistence extends BasePersistence<AccountEnvironmentAttachment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEnvironmentAttachmentUtil} to access the account environment attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the account environment attachment in the entity cache if it is enabled.
	*
	* @param accountEnvironmentAttachment the account environment attachment
	*/
	public void cacheResult(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment);

	/**
	* Caches the account environment attachments in the entity cache if it is enabled.
	*
	* @param accountEnvironmentAttachments the account environment attachments
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AccountEnvironmentAttachment> accountEnvironmentAttachments);

	/**
	* Creates a new account environment attachment with the primary key. Does not add the account environment attachment to the database.
	*
	* @param accountEnvironmentAttachmentId the primary key for the new account environment attachment
	* @return the new account environment attachment
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment create(
		long accountEnvironmentAttachmentId);

	/**
	* Removes the account environment attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	* @return the account environment attachment that was removed
	* @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment remove(
		long accountEnvironmentAttachmentId)
		throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AccountEnvironmentAttachment updateImpl(
		com.liferay.osb.model.AccountEnvironmentAttachment accountEnvironmentAttachment,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environment attachment with the primary key or throws a {@link com.liferay.osb.NoSuchAccountEnvironmentAttachmentException} if it could not be found.
	*
	* @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	* @return the account environment attachment
	* @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment findByPrimaryKey(
		long accountEnvironmentAttachmentId)
		throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environment attachment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	* @return the account environment attachment, or <code>null</code> if a account environment attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment fetchByPrimaryKey(
		long accountEnvironmentAttachmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account environment attachments where accountEnvironmentId = &#63;.
	*
	* @param accountEnvironmentId the account environment ID
	* @return the matching account environment attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account environment attachments where accountEnvironmentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEnvironmentId the account environment ID
	* @param start the lower bound of the range of account environment attachments
	* @param end the upper bound of the range of account environment attachments (not inclusive)
	* @return the range of matching account environment attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account environment attachments where accountEnvironmentId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEnvironmentId the account environment ID
	* @param start the lower bound of the range of account environment attachments
	* @param end the upper bound of the range of account environment attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account environment attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironmentAttachment> findByAccountEnvironmentId(
		long accountEnvironmentId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	*
	* @param accountEnvironmentId the account environment ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account environment attachment
	* @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment findByAccountEnvironmentId_First(
		long accountEnvironmentId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	*
	* @param accountEnvironmentId the account environment ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment fetchByAccountEnvironmentId_First(
		long accountEnvironmentId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	*
	* @param accountEnvironmentId the account environment ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account environment attachment
	* @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment findByAccountEnvironmentId_Last(
		long accountEnvironmentId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	*
	* @param accountEnvironmentId the account environment ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment fetchByAccountEnvironmentId_Last(
		long accountEnvironmentId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environment attachments before and after the current account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	*
	* @param accountEnvironmentAttachmentId the primary key of the current account environment attachment
	* @param accountEnvironmentId the account environment ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account environment attachment
	* @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment[] findByAccountEnvironmentId_PrevAndNext(
		long accountEnvironmentAttachmentId, long accountEnvironmentId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or throws a {@link com.liferay.osb.NoSuchAccountEnvironmentAttachmentException} if it could not be found.
	*
	* @param accountEnvironmentId the account environment ID
	* @param fileName the file name
	* @return the matching account environment attachment
	* @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment findByAEI_FN(
		long accountEnvironmentId, java.lang.String fileName)
		throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountEnvironmentId the account environment ID
	* @param fileName the file name
	* @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment fetchByAEI_FN(
		long accountEnvironmentId, java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountEnvironmentId the account environment ID
	* @param fileName the file name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment fetchByAEI_FN(
		long accountEnvironmentId, java.lang.String fileName,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchAccountEnvironmentAttachmentException} if it could not be found.
	*
	* @param accountEnvironmentId the account environment ID
	* @param type the type
	* @return the matching account environment attachment
	* @throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment findByAEI_T(
		long accountEnvironmentId, int type)
		throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountEnvironmentId the account environment ID
	* @param type the type
	* @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment fetchByAEI_T(
		long accountEnvironmentId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountEnvironmentId the account environment ID
	* @param type the type
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment fetchByAEI_T(
		long accountEnvironmentId, int type, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account environment attachments.
	*
	* @return the account environment attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironmentAttachment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account environment attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account environment attachments
	* @param end the upper bound of the range of account environment attachments (not inclusive)
	* @return the range of account environment attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironmentAttachment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account environment attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account environment attachments
	* @param end the upper bound of the range of account environment attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account environment attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironmentAttachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account environment attachments where accountEnvironmentId = &#63; from the database.
	*
	* @param accountEnvironmentId the account environment ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAccountEnvironmentId(long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; from the database.
	*
	* @param accountEnvironmentId the account environment ID
	* @param fileName the file name
	* @return the account environment attachment that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment removeByAEI_FN(
		long accountEnvironmentId, java.lang.String fileName)
		throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the account environment attachment where accountEnvironmentId = &#63; and type = &#63; from the database.
	*
	* @param accountEnvironmentId the account environment ID
	* @param type the type
	* @return the account environment attachment that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironmentAttachment removeByAEI_T(
		long accountEnvironmentId, int type)
		throws com.liferay.osb.NoSuchAccountEnvironmentAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account environment attachments from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account environment attachments where accountEnvironmentId = &#63;.
	*
	* @param accountEnvironmentId the account environment ID
	* @return the number of matching account environment attachments
	* @throws SystemException if a system exception occurred
	*/
	public int countByAccountEnvironmentId(long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account environment attachments where accountEnvironmentId = &#63; and fileName = &#63;.
	*
	* @param accountEnvironmentId the account environment ID
	* @param fileName the file name
	* @return the number of matching account environment attachments
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_FN(long accountEnvironmentId,
		java.lang.String fileName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account environment attachments where accountEnvironmentId = &#63; and type = &#63;.
	*
	* @param accountEnvironmentId the account environment ID
	* @param type the type
	* @return the number of matching account environment attachments
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_T(long accountEnvironmentId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account environment attachments.
	*
	* @return the number of account environment attachments
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}