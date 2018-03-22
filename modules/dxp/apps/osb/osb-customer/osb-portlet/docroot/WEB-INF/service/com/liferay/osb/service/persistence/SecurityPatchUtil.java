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

import com.liferay.osb.model.SecurityPatch;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the security patch service. This utility wraps {@link com.liferay.osb.service.persistence.impl.SecurityPatchPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SecurityPatchPersistence
 * @see com.liferay.osb.service.persistence.impl.SecurityPatchPersistenceImpl
 * @generated
 */
@ProviderType
public class SecurityPatchUtil {
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
	public static void clearCache(SecurityPatch securityPatch) {
		getPersistence().clearCache(securityPatch);
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
	public static List<SecurityPatch> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SecurityPatch> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SecurityPatch> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SecurityPatch> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SecurityPatch update(SecurityPatch securityPatch) {
		return getPersistence().update(securityPatch);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SecurityPatch update(SecurityPatch securityPatch,
		ServiceContext serviceContext) {
		return getPersistence().update(securityPatch, serviceContext);
	}

	/**
	* Returns all the security patchs where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @return the matching security patchs
	*/
	public static List<SecurityPatch> findByPortletId(
		java.lang.String portletId) {
		return getPersistence().findByPortletId(portletId);
	}

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
	public static List<SecurityPatch> findByPortletId(
		java.lang.String portletId, int start, int end) {
		return getPersistence().findByPortletId(portletId, start, end);
	}

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
	public static List<SecurityPatch> findByPortletId(
		java.lang.String portletId, int start, int end,
		OrderByComparator<SecurityPatch> orderByComparator) {
		return getPersistence()
				   .findByPortletId(portletId, start, end, orderByComparator);
	}

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
	public static List<SecurityPatch> findByPortletId(
		java.lang.String portletId, int start, int end,
		OrderByComparator<SecurityPatch> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPortletId(portletId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first security patch in the ordered set where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching security patch
	* @throws NoSuchSecurityPatchException if a matching security patch could not be found
	*/
	public static SecurityPatch findByPortletId_First(
		java.lang.String portletId,
		OrderByComparator<SecurityPatch> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSecurityPatchException {
		return getPersistence()
				   .findByPortletId_First(portletId, orderByComparator);
	}

	/**
	* Returns the first security patch in the ordered set where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching security patch, or <code>null</code> if a matching security patch could not be found
	*/
	public static SecurityPatch fetchByPortletId_First(
		java.lang.String portletId,
		OrderByComparator<SecurityPatch> orderByComparator) {
		return getPersistence()
				   .fetchByPortletId_First(portletId, orderByComparator);
	}

	/**
	* Returns the last security patch in the ordered set where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching security patch
	* @throws NoSuchSecurityPatchException if a matching security patch could not be found
	*/
	public static SecurityPatch findByPortletId_Last(
		java.lang.String portletId,
		OrderByComparator<SecurityPatch> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSecurityPatchException {
		return getPersistence()
				   .findByPortletId_Last(portletId, orderByComparator);
	}

	/**
	* Returns the last security patch in the ordered set where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching security patch, or <code>null</code> if a matching security patch could not be found
	*/
	public static SecurityPatch fetchByPortletId_Last(
		java.lang.String portletId,
		OrderByComparator<SecurityPatch> orderByComparator) {
		return getPersistence()
				   .fetchByPortletId_Last(portletId, orderByComparator);
	}

	/**
	* Returns the security patchs before and after the current security patch in the ordered set where portletId = &#63;.
	*
	* @param securityPatchId the primary key of the current security patch
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next security patch
	* @throws NoSuchSecurityPatchException if a security patch with the primary key could not be found
	*/
	public static SecurityPatch[] findByPortletId_PrevAndNext(
		long securityPatchId, java.lang.String portletId,
		OrderByComparator<SecurityPatch> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSecurityPatchException {
		return getPersistence()
				   .findByPortletId_PrevAndNext(securityPatchId, portletId,
			orderByComparator);
	}

	/**
	* Removes all the security patchs where portletId = &#63; from the database.
	*
	* @param portletId the portlet ID
	*/
	public static void removeByPortletId(java.lang.String portletId) {
		getPersistence().removeByPortletId(portletId);
	}

	/**
	* Returns the number of security patchs where portletId = &#63;.
	*
	* @param portletId the portlet ID
	* @return the number of matching security patchs
	*/
	public static int countByPortletId(java.lang.String portletId) {
		return getPersistence().countByPortletId(portletId);
	}

	/**
	* Returns all the security patchs where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @return the matching security patchs
	*/
	public static List<SecurityPatch> findByAEI_PI(long accountEntryId,
		java.lang.String portletId) {
		return getPersistence().findByAEI_PI(accountEntryId, portletId);
	}

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
	public static List<SecurityPatch> findByAEI_PI(long accountEntryId,
		java.lang.String portletId, int start, int end) {
		return getPersistence()
				   .findByAEI_PI(accountEntryId, portletId, start, end);
	}

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
	public static List<SecurityPatch> findByAEI_PI(long accountEntryId,
		java.lang.String portletId, int start, int end,
		OrderByComparator<SecurityPatch> orderByComparator) {
		return getPersistence()
				   .findByAEI_PI(accountEntryId, portletId, start, end,
			orderByComparator);
	}

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
	public static List<SecurityPatch> findByAEI_PI(long accountEntryId,
		java.lang.String portletId, int start, int end,
		OrderByComparator<SecurityPatch> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAEI_PI(accountEntryId, portletId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching security patch
	* @throws NoSuchSecurityPatchException if a matching security patch could not be found
	*/
	public static SecurityPatch findByAEI_PI_First(long accountEntryId,
		java.lang.String portletId,
		OrderByComparator<SecurityPatch> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSecurityPatchException {
		return getPersistence()
				   .findByAEI_PI_First(accountEntryId, portletId,
			orderByComparator);
	}

	/**
	* Returns the first security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching security patch, or <code>null</code> if a matching security patch could not be found
	*/
	public static SecurityPatch fetchByAEI_PI_First(long accountEntryId,
		java.lang.String portletId,
		OrderByComparator<SecurityPatch> orderByComparator) {
		return getPersistence()
				   .fetchByAEI_PI_First(accountEntryId, portletId,
			orderByComparator);
	}

	/**
	* Returns the last security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching security patch
	* @throws NoSuchSecurityPatchException if a matching security patch could not be found
	*/
	public static SecurityPatch findByAEI_PI_Last(long accountEntryId,
		java.lang.String portletId,
		OrderByComparator<SecurityPatch> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSecurityPatchException {
		return getPersistence()
				   .findByAEI_PI_Last(accountEntryId, portletId,
			orderByComparator);
	}

	/**
	* Returns the last security patch in the ordered set where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching security patch, or <code>null</code> if a matching security patch could not be found
	*/
	public static SecurityPatch fetchByAEI_PI_Last(long accountEntryId,
		java.lang.String portletId,
		OrderByComparator<SecurityPatch> orderByComparator) {
		return getPersistence()
				   .fetchByAEI_PI_Last(accountEntryId, portletId,
			orderByComparator);
	}

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
	public static SecurityPatch[] findByAEI_PI_PrevAndNext(
		long securityPatchId, long accountEntryId, java.lang.String portletId,
		OrderByComparator<SecurityPatch> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSecurityPatchException {
		return getPersistence()
				   .findByAEI_PI_PrevAndNext(securityPatchId, accountEntryId,
			portletId, orderByComparator);
	}

	/**
	* Removes all the security patchs where accountEntryId = &#63; and portletId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	*/
	public static void removeByAEI_PI(long accountEntryId,
		java.lang.String portletId) {
		getPersistence().removeByAEI_PI(accountEntryId, portletId);
	}

	/**
	* Returns the number of security patchs where accountEntryId = &#63; and portletId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param portletId the portlet ID
	* @return the number of matching security patchs
	*/
	public static int countByAEI_PI(long accountEntryId,
		java.lang.String portletId) {
		return getPersistence().countByAEI_PI(accountEntryId, portletId);
	}

	/**
	* Caches the security patch in the entity cache if it is enabled.
	*
	* @param securityPatch the security patch
	*/
	public static void cacheResult(SecurityPatch securityPatch) {
		getPersistence().cacheResult(securityPatch);
	}

	/**
	* Caches the security patchs in the entity cache if it is enabled.
	*
	* @param securityPatchs the security patchs
	*/
	public static void cacheResult(List<SecurityPatch> securityPatchs) {
		getPersistence().cacheResult(securityPatchs);
	}

	/**
	* Creates a new security patch with the primary key. Does not add the security patch to the database.
	*
	* @param securityPatchId the primary key for the new security patch
	* @return the new security patch
	*/
	public static SecurityPatch create(long securityPatchId) {
		return getPersistence().create(securityPatchId);
	}

	/**
	* Removes the security patch with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param securityPatchId the primary key of the security patch
	* @return the security patch that was removed
	* @throws NoSuchSecurityPatchException if a security patch with the primary key could not be found
	*/
	public static SecurityPatch remove(long securityPatchId)
		throws com.liferay.osb.exception.NoSuchSecurityPatchException {
		return getPersistence().remove(securityPatchId);
	}

	public static SecurityPatch updateImpl(SecurityPatch securityPatch) {
		return getPersistence().updateImpl(securityPatch);
	}

	/**
	* Returns the security patch with the primary key or throws a {@link NoSuchSecurityPatchException} if it could not be found.
	*
	* @param securityPatchId the primary key of the security patch
	* @return the security patch
	* @throws NoSuchSecurityPatchException if a security patch with the primary key could not be found
	*/
	public static SecurityPatch findByPrimaryKey(long securityPatchId)
		throws com.liferay.osb.exception.NoSuchSecurityPatchException {
		return getPersistence().findByPrimaryKey(securityPatchId);
	}

	/**
	* Returns the security patch with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param securityPatchId the primary key of the security patch
	* @return the security patch, or <code>null</code> if a security patch with the primary key could not be found
	*/
	public static SecurityPatch fetchByPrimaryKey(long securityPatchId) {
		return getPersistence().fetchByPrimaryKey(securityPatchId);
	}

	public static java.util.Map<java.io.Serializable, SecurityPatch> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the security patchs.
	*
	* @return the security patchs
	*/
	public static List<SecurityPatch> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<SecurityPatch> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<SecurityPatch> findAll(int start, int end,
		OrderByComparator<SecurityPatch> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<SecurityPatch> findAll(int start, int end,
		OrderByComparator<SecurityPatch> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the security patchs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of security patchs.
	*
	* @return the number of security patchs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SecurityPatchPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SecurityPatchPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					SecurityPatchPersistence.class.getName());

			ReferenceRegistry.registerReference(SecurityPatchUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static SecurityPatchPersistence _persistence;
}