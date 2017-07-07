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

import com.liferay.osb.exception.NoSuchSecurityPatchException;
import com.liferay.osb.model.SecurityPatch;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the security patch service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.SecurityPatchPersistenceImpl
 * @see SecurityPatchUtil
 * @generated
 */
@ProviderType
public interface SecurityPatchPersistence extends BasePersistence<SecurityPatch> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SecurityPatchUtil} to access the security patch persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the security patchs where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @return the matching security patchs
	*/
	public java.util.List<SecurityPatch> findByPortletId(
		java.lang.String portletId);

	/**
	* Returns a range of all the security patchs where portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param portletId the portlet ID
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @return the range of matching security patchs
	*/
	public java.util.List<SecurityPatch> findByPortletId(
		java.lang.String portletId, int start, int end);

	/**
	* Returns an ordered range of all the security patchs where portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param portletId the portlet ID
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching security patchs
	*/
	public java.util.List<SecurityPatch> findByPortletId(
		java.lang.String portletId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator);

	/**
	* Returns an ordered range of all the security patchs where portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param portletId the portlet ID
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching security patchs
	*/
	public java.util.List<SecurityPatch> findByPortletId(
		java.lang.String portletId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first security patch in the ordered set where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching security patch
	* @throws NoSuchSecurityPatchException if a matching security patch could not be found
	*/
	public SecurityPatch findByPortletId_First(java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator)
		throws NoSuchSecurityPatchException;

	/**
	* Returns the first security patch in the ordered set where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching security patch, or <code>null</code> if a matching security patch could not be found
	*/
	public SecurityPatch fetchByPortletId_First(java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator);

	/**
	* Returns the last security patch in the ordered set where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching security patch
	* @throws NoSuchSecurityPatchException if a matching security patch could not be found
	*/
	public SecurityPatch findByPortletId_Last(java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator)
		throws NoSuchSecurityPatchException;

	/**
	* Returns the last security patch in the ordered set where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching security patch, or <code>null</code> if a matching security patch could not be found
	*/
	public SecurityPatch fetchByPortletId_Last(java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator);

	/**
	* Returns the security patchs before and after the current security patch in the ordered set where portletId = &#63;.
	*
	* @param securityPatchId the primary key of the current security patch
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next security patch
	* @throws NoSuchSecurityPatchException if a security patch with the primary key could not be found
	*/
	public SecurityPatch[] findByPortletId_PrevAndNext(long securityPatchId,
		java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator)
		throws NoSuchSecurityPatchException;

	/**
	* Removes all the security patchs where portletId = &#63; from the database.
	*
	* @param portletId the portlet ID
	*/
	public void removeByPortletId(java.lang.String portletId);

	/**
	* Returns the number of security patchs where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @return the number of matching security patchs
	*/
	public int countByPortletId(java.lang.String portletId);

	/**
	* Returns all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @return the matching security patchs
	*/
	public java.util.List<SecurityPatch> findByAEI_PI(long accountEntryId,
		java.lang.String portletId);

	/**
	* Returns a range of all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @return the range of matching security patchs
	*/
	public java.util.List<SecurityPatch> findByAEI_PI(long accountEntryId,
		java.lang.String portletId, int start, int end);

	/**
	* Returns an ordered range of all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching security patchs
	*/
	public java.util.List<SecurityPatch> findByAEI_PI(long accountEntryId,
		java.lang.String portletId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator);

	/**
	* Returns an ordered range of all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching security patchs
	*/
	public java.util.List<SecurityPatch> findByAEI_PI(long accountEntryId,
		java.lang.String portletId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching security patch
	* @throws NoSuchSecurityPatchException if a matching security patch could not be found
	*/
	public SecurityPatch findByAEI_PI_First(long accountEntryId,
		java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator)
		throws NoSuchSecurityPatchException;

	/**
	* Returns the first security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching security patch, or <code>null</code> if a matching security patch could not be found
	*/
	public SecurityPatch fetchByAEI_PI_First(long accountEntryId,
		java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator);

	/**
	* Returns the last security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching security patch
	* @throws NoSuchSecurityPatchException if a matching security patch could not be found
	*/
	public SecurityPatch findByAEI_PI_Last(long accountEntryId,
		java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator)
		throws NoSuchSecurityPatchException;

	/**
	* Returns the last security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching security patch, or <code>null</code> if a matching security patch could not be found
	*/
	public SecurityPatch fetchByAEI_PI_Last(long accountEntryId,
		java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator);

	/**
	* Returns the security patchs before and after the current security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param securityPatchId the primary key of the current security patch
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next security patch
	* @throws NoSuchSecurityPatchException if a security patch with the primary key could not be found
	*/
	public SecurityPatch[] findByAEI_PI_PrevAndNext(long securityPatchId,
		long accountEntryId, java.lang.String portletId,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator)
		throws NoSuchSecurityPatchException;

	/**
	* Removes all the security patchs where accountEntryId = &#63; and portletId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	*/
	public void removeByAEI_PI(long accountEntryId, java.lang.String portletId);

	/**
	* Returns the number of security patchs where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @return the number of matching security patchs
	*/
	public int countByAEI_PI(long accountEntryId, java.lang.String portletId);

	/**
	* Caches the security patch in the entity cache if it is enabled.
	*
	* @param securityPatch the security patch
	*/
	public void cacheResult(SecurityPatch securityPatch);

	/**
	* Caches the security patchs in the entity cache if it is enabled.
	*
	* @param securityPatchs the security patchs
	*/
	public void cacheResult(java.util.List<SecurityPatch> securityPatchs);

	/**
	* Creates a new security patch with the primary key. Does not add the security patch to the database.
	*
	* @param securityPatchId the primary key for the new security patch
	* @return the new security patch
	*/
	public SecurityPatch create(long securityPatchId);

	/**
	* Removes the security patch with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param securityPatchId the primary key of the security patch
	* @return the security patch that was removed
	* @throws NoSuchSecurityPatchException if a security patch with the primary key could not be found
	*/
	public SecurityPatch remove(long securityPatchId)
		throws NoSuchSecurityPatchException;

	public SecurityPatch updateImpl(SecurityPatch securityPatch);

	/**
	* Returns the security patch with the primary key or throws a {@link NoSuchSecurityPatchException} if it could not be found.
	*
	* @param securityPatchId the primary key of the security patch
	* @return the security patch
	* @throws NoSuchSecurityPatchException if a security patch with the primary key could not be found
	*/
	public SecurityPatch findByPrimaryKey(long securityPatchId)
		throws NoSuchSecurityPatchException;

	/**
	* Returns the security patch with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param securityPatchId the primary key of the security patch
	* @return the security patch, or <code>null</code> if a security patch with the primary key could not be found
	*/
	public SecurityPatch fetchByPrimaryKey(long securityPatchId);

	@Override
	public java.util.Map<java.io.Serializable, SecurityPatch> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the security patchs.
	*
	* @return the security patchs
	*/
	public java.util.List<SecurityPatch> findAll();

	/**
	* Returns a range of all the security patchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @return the range of security patchs
	*/
	public java.util.List<SecurityPatch> findAll(int start, int end);

	/**
	* Returns an ordered range of all the security patchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of security patchs
	*/
	public java.util.List<SecurityPatch> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator);

	/**
	* Returns an ordered range of all the security patchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SecurityPatchModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of security patchs
	* @param end the upper bound of the range of security patchs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of security patchs
	*/
	public java.util.List<SecurityPatch> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SecurityPatch> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the security patchs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of security patchs.
	*
	* @return the number of security patchs
	*/
	public int countAll();
}