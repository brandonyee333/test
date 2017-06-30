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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the support response service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportResponsePersistenceImpl
 * @see SupportResponseUtil
 * @generated
 */
public interface SupportResponsePersistence extends BasePersistence<SupportResponse> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportResponseUtil} to access the support response persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the support response in the entity cache if it is enabled.
	*
	* @param supportResponse the support response
	*/
	public void cacheResult(
		com.liferay.osb.model.SupportResponse supportResponse);

	/**
	* Caches the support responses in the entity cache if it is enabled.
	*
	* @param supportResponses the support responses
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.SupportResponse> supportResponses);

	/**
	* Creates a new support response with the primary key. Does not add the support response to the database.
	*
	* @param supportResponseId the primary key for the new support response
	* @return the new support response
	*/
	public com.liferay.osb.model.SupportResponse create(long supportResponseId);

	/**
	* Removes the support response with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response that was removed
	* @throws com.liferay.osb.NoSuchSupportResponseException if a support response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportResponse remove(long supportResponseId)
		throws com.liferay.osb.NoSuchSupportResponseException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.SupportResponse updateImpl(
		com.liferay.osb.model.SupportResponse supportResponse, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support response with the primary key or throws a {@link com.liferay.osb.NoSuchSupportResponseException} if it could not be found.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response
	* @throws com.liferay.osb.NoSuchSupportResponseException if a support response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportResponse findByPrimaryKey(
		long supportResponseId)
		throws com.liferay.osb.NoSuchSupportResponseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support response with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response, or <code>null</code> if a support response with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportResponse fetchByPrimaryKey(
		long supportResponseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support response where name = &#63; or throws a {@link com.liferay.osb.NoSuchSupportResponseException} if it could not be found.
	*
	* @param name the name
	* @return the matching support response
	* @throws com.liferay.osb.NoSuchSupportResponseException if a matching support response could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportResponse findByName(
		java.lang.String name)
		throws com.liferay.osb.NoSuchSupportResponseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support response where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching support response, or <code>null</code> if a matching support response could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportResponse fetchByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support response where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching support response, or <code>null</code> if a matching support response could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportResponse fetchByName(
		java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the support responses where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @return the matching support responses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportResponse> findBySupportLevel(
		int supportLevel)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.SupportResponse> findBySupportLevel(
		int supportLevel, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.SupportResponse> findBySupportLevel(
		int supportLevel, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first support response in the ordered set where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support response
	* @throws com.liferay.osb.NoSuchSupportResponseException if a matching support response could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportResponse findBySupportLevel_First(
		int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportResponseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first support response in the ordered set where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support response, or <code>null</code> if a matching support response could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportResponse fetchBySupportLevel_First(
		int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last support response in the ordered set where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support response
	* @throws com.liferay.osb.NoSuchSupportResponseException if a matching support response could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportResponse findBySupportLevel_Last(
		int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportResponseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last support response in the ordered set where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support response, or <code>null</code> if a matching support response could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportResponse fetchBySupportLevel_Last(
		int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.SupportResponse[] findBySupportLevel_PrevAndNext(
		long supportResponseId, int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportResponseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the support responses.
	*
	* @return the support responses
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportResponse> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.SupportResponse> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.SupportResponse> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the support response where name = &#63; from the database.
	*
	* @param name the name
	* @return the support response that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportResponse removeByName(
		java.lang.String name)
		throws com.liferay.osb.NoSuchSupportResponseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the support responses where supportLevel = &#63; from the database.
	*
	* @param supportLevel the support level
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySupportLevel(int supportLevel)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the support responses from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support responses where name = &#63;.
	*
	* @param name the name
	* @return the number of matching support responses
	* @throws SystemException if a system exception occurred
	*/
	public int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support responses where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @return the number of matching support responses
	* @throws SystemException if a system exception occurred
	*/
	public int countBySupportLevel(int supportLevel)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support responses.
	*
	* @return the number of support responses
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}