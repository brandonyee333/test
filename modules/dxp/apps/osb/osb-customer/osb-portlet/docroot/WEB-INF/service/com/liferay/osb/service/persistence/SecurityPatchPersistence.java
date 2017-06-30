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

import com.liferay.osb.model.SecurityPatch;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the security patch service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecurityPatchPersistenceImpl
 * @see SecurityPatchUtil
 * @generated
 */
public interface SecurityPatchPersistence extends BasePersistence<SecurityPatch> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SecurityPatchUtil} to access the security patch persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the security patch in the entity cache if it is enabled.
	*
	* @param securityPatch the security patch
	*/
	public void cacheResult(com.liferay.osb.model.SecurityPatch securityPatch);

	/**
	* Caches the security patchs in the entity cache if it is enabled.
	*
	* @param securityPatchs the security patchs
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.SecurityPatch> securityPatchs);

	/**
	* Creates a new security patch with the primary key. Does not add the security patch to the database.
	*
	* @param securityPatchId the primary key for the new security patch
	* @return the new security patch
	*/
	public com.liferay.osb.model.SecurityPatch create(long securityPatchId);

	/**
	* Removes the security patch with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param securityPatchId the primary key of the security patch
	* @return the security patch that was removed
	* @throws com.liferay.osb.NoSuchSecurityPatchException if a security patch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SecurityPatch remove(long securityPatchId)
		throws com.liferay.osb.NoSuchSecurityPatchException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.SecurityPatch updateImpl(
		com.liferay.osb.model.SecurityPatch securityPatch, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the security patch with the primary key or throws a {@link com.liferay.osb.NoSuchSecurityPatchException} if it could not be found.
	*
	* @param securityPatchId the primary key of the security patch
	* @return the security patch
	* @throws com.liferay.osb.NoSuchSecurityPatchException if a security patch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SecurityPatch findByPrimaryKey(
		long securityPatchId)
		throws com.liferay.osb.NoSuchSecurityPatchException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the security patch with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param securityPatchId the primary key of the security patch
	* @return the security patch, or <code>null</code> if a security patch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SecurityPatch fetchByPrimaryKey(
		long securityPatchId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the security patchs where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @return the matching security patchs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SecurityPatch> findByPortletId(
		java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the security patchs where portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param portletId the portlet ID
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @return the range of matching security patchs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SecurityPatch> findByPortletId(
		java.lang.String portletId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the security patchs where portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param portletId the portlet ID
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching security patchs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SecurityPatch> findByPortletId(
		java.lang.String portletId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first security patch in the ordered set where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching security patch
	* @throws com.liferay.osb.NoSuchSecurityPatchException if a matching security patch could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SecurityPatch findByPortletId_First(
		java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSecurityPatchException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first security patch in the ordered set where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching security patch, or <code>null</code> if a matching security patch could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SecurityPatch fetchByPortletId_First(
		java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last security patch in the ordered set where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching security patch
	* @throws com.liferay.osb.NoSuchSecurityPatchException if a matching security patch could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SecurityPatch findByPortletId_Last(
		java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSecurityPatchException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last security patch in the ordered set where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching security patch, or <code>null</code> if a matching security patch could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SecurityPatch fetchByPortletId_Last(
		java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the security patchs before and after the current security patch in the ordered set where portletId = &#63;.
	*
	* @param securityPatchId the primary key of the current security patch
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next security patch
	* @throws com.liferay.osb.NoSuchSecurityPatchException if a security patch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SecurityPatch[] findByPortletId_PrevAndNext(
		long securityPatchId, java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSecurityPatchException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @return the matching security patchs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SecurityPatch> findByAEI_PI(
		long accountEntryId, java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @return the range of matching security patchs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SecurityPatch> findByAEI_PI(
		long accountEntryId, java.lang.String portletId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching security patchs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SecurityPatch> findByAEI_PI(
		long accountEntryId, java.lang.String portletId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching security patch
	* @throws com.liferay.osb.NoSuchSecurityPatchException if a matching security patch could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SecurityPatch findByAEI_PI_First(
		long accountEntryId, java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSecurityPatchException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching security patch, or <code>null</code> if a matching security patch could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SecurityPatch fetchByAEI_PI_First(
		long accountEntryId, java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching security patch
	* @throws com.liferay.osb.NoSuchSecurityPatchException if a matching security patch could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SecurityPatch findByAEI_PI_Last(
		long accountEntryId, java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSecurityPatchException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching security patch, or <code>null</code> if a matching security patch could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SecurityPatch fetchByAEI_PI_Last(
		long accountEntryId, java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the security patchs before and after the current security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param securityPatchId the primary key of the current security patch
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next security patch
	* @throws com.liferay.osb.NoSuchSecurityPatchException if a security patch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SecurityPatch[] findByAEI_PI_PrevAndNext(
		long securityPatchId, long accountEntryId, java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSecurityPatchException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the security patchs.
	*
	* @return the security patchs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SecurityPatch> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the security patchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @return the range of security patchs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SecurityPatch> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the security patchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of security patchs
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SecurityPatch> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the security patchs where portletId = &#63; from the database.
	*
	* @param portletId the portlet ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByPortletId(java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the security patchs where accountEntryId = &#63; and portletId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAEI_PI(long accountEntryId, java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the security patchs from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of security patchs where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @return the number of matching security patchs
	* @throws SystemException if a system exception occurred
	*/
	public int countByPortletId(java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of security patchs where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @return the number of matching security patchs
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_PI(long accountEntryId, java.lang.String portletId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of security patchs.
	*
	* @return the number of security patchs
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}