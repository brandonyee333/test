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

import com.liferay.osb.model.SupportResponse;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the support response service. This utility wraps {@link SupportResponsePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportResponsePersistence
 * @see SupportResponsePersistenceImpl
 * @generated
 */
public class SupportResponseUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(SupportResponse supportResponse) {
		getPersistence().clearCache(supportResponse);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SupportResponse> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SupportResponse> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SupportResponse> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static SupportResponse update(SupportResponse supportResponse,
		boolean merge) throws SystemException {
		return getPersistence().update(supportResponse, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static SupportResponse update(SupportResponse supportResponse,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(supportResponse, merge, serviceContext);
	}

	/**
	* Caches the support response in the entity cache if it is enabled.
	*
	* @param supportResponse the support response
	*/
	public static void cacheResult(
		com.liferay.osb.model.SupportResponse supportResponse) {
		getPersistence().cacheResult(supportResponse);
	}

	/**
	* Caches the support responses in the entity cache if it is enabled.
	*
	* @param supportResponses the support responses
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.SupportResponse> supportResponses) {
		getPersistence().cacheResult(supportResponses);
	}

	/**
	* Creates a new support response with the primary key. Does not add the support response to the database.
	*
	* @param supportResponseId the primary key for the new support response
	* @return the new support response
	*/
	public static com.liferay.osb.model.SupportResponse create(
		long supportResponseId) {
		return getPersistence().create(supportResponseId);
	}

	/**
	* Removes the support response with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response that was removed
	* @throws com.liferay.osb.NoSuchSupportResponseException if a support response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportResponse remove(
		long supportResponseId)
		throws com.liferay.osb.NoSuchSupportResponseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(supportResponseId);
	}

	public static com.liferay.osb.model.SupportResponse updateImpl(
		com.liferay.osb.model.SupportResponse supportResponse, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(supportResponse, merge);
	}

	/**
	* Returns the support response with the primary key or throws a {@link com.liferay.osb.NoSuchSupportResponseException} if it could not be found.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response
	* @throws com.liferay.osb.NoSuchSupportResponseException if a support response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportResponse findByPrimaryKey(
		long supportResponseId)
		throws com.liferay.osb.NoSuchSupportResponseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(supportResponseId);
	}

	/**
	* Returns the support response with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response, or <code>null</code> if a support response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportResponse fetchByPrimaryKey(
		long supportResponseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(supportResponseId);
	}

	/**
	* Returns the support response where name = &#63; or throws a {@link com.liferay.osb.NoSuchSupportResponseException} if it could not be found.
	*
	* @param name the name
	* @return the matching support response
	* @throws com.liferay.osb.NoSuchSupportResponseException if a matching support response could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportResponse findByName(
		java.lang.String name)
		throws com.liferay.osb.NoSuchSupportResponseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name);
	}

	/**
	* Returns the support response where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching support response, or <code>null</code> if a matching support response could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportResponse fetchByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName(name);
	}

	/**
	* Returns the support response where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching support response, or <code>null</code> if a matching support response could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportResponse fetchByName(
		java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName(name, retrieveFromCache);
	}

	/**
	* Returns all the support responses where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @return the matching support responses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportResponse> findBySupportLevel(
		int supportLevel)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySupportLevel(supportLevel);
	}

	/**
	* Returns a range of all the support responses where supportLevel = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportLevel the support level
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @return the range of matching support responses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportResponse> findBySupportLevel(
		int supportLevel, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySupportLevel(supportLevel, start, end);
	}

	/**
	* Returns an ordered range of all the support responses where supportLevel = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportLevel the support level
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support responses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportResponse> findBySupportLevel(
		int supportLevel, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportLevel(supportLevel, start, end,
			orderByComparator);
	}

	/**
	* Returns the first support response in the ordered set where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support response
	* @throws com.liferay.osb.NoSuchSupportResponseException if a matching support response could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportResponse findBySupportLevel_First(
		int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportResponseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportLevel_First(supportLevel, orderByComparator);
	}

	/**
	* Returns the first support response in the ordered set where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support response, or <code>null</code> if a matching support response could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportResponse fetchBySupportLevel_First(
		int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySupportLevel_First(supportLevel, orderByComparator);
	}

	/**
	* Returns the last support response in the ordered set where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support response
	* @throws com.liferay.osb.NoSuchSupportResponseException if a matching support response could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportResponse findBySupportLevel_Last(
		int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportResponseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportLevel_Last(supportLevel, orderByComparator);
	}

	/**
	* Returns the last support response in the ordered set where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support response, or <code>null</code> if a matching support response could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportResponse fetchBySupportLevel_Last(
		int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySupportLevel_Last(supportLevel, orderByComparator);
	}

	/**
	* Returns the support responses before and after the current support response in the ordered set where supportLevel = &#63;.
	*
	* @param supportResponseId the primary key of the current support response
	* @param supportLevel the support level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support response
	* @throws com.liferay.osb.NoSuchSupportResponseException if a support response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportResponse[] findBySupportLevel_PrevAndNext(
		long supportResponseId, int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportResponseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportLevel_PrevAndNext(supportResponseId,
			supportLevel, orderByComparator);
	}

	/**
	* Returns all the support responses.
	*
	* @return the support responses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportResponse> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the support responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @return the range of support responses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportResponse> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the support responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support responses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportResponse> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the support response where name = &#63; from the database.
	*
	* @param name the name
	* @return the support response that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportResponse removeByName(
		java.lang.String name)
		throws com.liferay.osb.NoSuchSupportResponseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByName(name);
	}

	/**
	* Removes all the support responses where supportLevel = &#63; from the database.
	*
	* @param supportLevel the support level
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySupportLevel(int supportLevel)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySupportLevel(supportLevel);
	}

	/**
	* Removes all the support responses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of support responses where name = &#63;.
	*
	* @param name the name
	* @return the number of matching support responses
	* @throws SystemException if a system exception occurred
	*/
	public static int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByName(name);
	}

	/**
	* Returns the number of support responses where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @return the number of matching support responses
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySupportLevel(int supportLevel)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySupportLevel(supportLevel);
	}

	/**
	* Returns the number of support responses.
	*
	* @return the number of support responses
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SupportResponsePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SupportResponsePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					SupportResponsePersistence.class.getName());

			ReferenceRegistry.registerReference(SupportResponseUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(SupportResponsePersistence persistence) {
	}

	private static SupportResponsePersistence _persistence;
}