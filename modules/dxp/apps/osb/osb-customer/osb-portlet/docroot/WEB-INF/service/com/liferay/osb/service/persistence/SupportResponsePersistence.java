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

import com.liferay.osb.exception.NoSuchSupportResponseException;
import com.liferay.osb.model.SupportResponse;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the support response service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.SupportResponsePersistenceImpl
 * @see SupportResponseUtil
 * @generated
 */
@ProviderType
public interface SupportResponsePersistence extends BasePersistence<SupportResponse> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportResponseUtil} to access the support response persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the support response where name = &#63; or throws a {@link NoSuchSupportResponseException} if it could not be found.
	*
	* @param name the name
	* @return the matching support response
	* @throws NoSuchSupportResponseException if a matching support response could not be found
	*/
	public SupportResponse findByName(java.lang.String name)
		throws NoSuchSupportResponseException;

	/**
	* Returns the support response where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching support response, or <code>null</code> if a matching support response could not be found
	*/
	public SupportResponse fetchByName(java.lang.String name);

	/**
	* Returns the support response where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching support response, or <code>null</code> if a matching support response could not be found
	*/
	public SupportResponse fetchByName(java.lang.String name,
		boolean retrieveFromCache);

	/**
	* Removes the support response where name = &#63; from the database.
	*
	* @param name the name
	* @return the support response that was removed
	*/
	public SupportResponse removeByName(java.lang.String name)
		throws NoSuchSupportResponseException;

	/**
	* Returns the number of support responses where name = &#63;.
	*
	* @param name the name
	* @return the number of matching support responses
	*/
	public int countByName(java.lang.String name);

	/**
	* Returns all the support responses where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @return the matching support responses
	*/
	public java.util.List<SupportResponse> findBySupportLevel(int supportLevel);

	/**
	* Returns a range of all the support responses where supportLevel = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportLevel the support level
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @return the range of matching support responses
	*/
	public java.util.List<SupportResponse> findBySupportLevel(
		int supportLevel, int start, int end);

	/**
	* Returns an ordered range of all the support responses where supportLevel = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportLevel the support level
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support responses
	*/
	public java.util.List<SupportResponse> findBySupportLevel(
		int supportLevel, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportResponse> orderByComparator);

	/**
	* Returns an ordered range of all the support responses where supportLevel = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportLevel the support level
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching support responses
	*/
	public java.util.List<SupportResponse> findBySupportLevel(
		int supportLevel, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportResponse> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first support response in the ordered set where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support response
	* @throws NoSuchSupportResponseException if a matching support response could not be found
	*/
	public SupportResponse findBySupportLevel_First(int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator<SupportResponse> orderByComparator)
		throws NoSuchSupportResponseException;

	/**
	* Returns the first support response in the ordered set where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support response, or <code>null</code> if a matching support response could not be found
	*/
	public SupportResponse fetchBySupportLevel_First(int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator<SupportResponse> orderByComparator);

	/**
	* Returns the last support response in the ordered set where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support response
	* @throws NoSuchSupportResponseException if a matching support response could not be found
	*/
	public SupportResponse findBySupportLevel_Last(int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator<SupportResponse> orderByComparator)
		throws NoSuchSupportResponseException;

	/**
	* Returns the last support response in the ordered set where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support response, or <code>null</code> if a matching support response could not be found
	*/
	public SupportResponse fetchBySupportLevel_Last(int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator<SupportResponse> orderByComparator);

	/**
	* Returns the support responses before and after the current support response in the ordered set where supportLevel = &#63;.
	*
	* @param supportResponseId the primary key of the current support response
	* @param supportLevel the support level
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support response
	* @throws NoSuchSupportResponseException if a support response with the primary key could not be found
	*/
	public SupportResponse[] findBySupportLevel_PrevAndNext(
		long supportResponseId, int supportLevel,
		com.liferay.portal.kernel.util.OrderByComparator<SupportResponse> orderByComparator)
		throws NoSuchSupportResponseException;

	/**
	* Removes all the support responses where supportLevel = &#63; from the database.
	*
	* @param supportLevel the support level
	*/
	public void removeBySupportLevel(int supportLevel);

	/**
	* Returns the number of support responses where supportLevel = &#63;.
	*
	* @param supportLevel the support level
	* @return the number of matching support responses
	*/
	public int countBySupportLevel(int supportLevel);

	/**
	* Caches the support response in the entity cache if it is enabled.
	*
	* @param supportResponse the support response
	*/
	public void cacheResult(SupportResponse supportResponse);

	/**
	* Caches the support responses in the entity cache if it is enabled.
	*
	* @param supportResponses the support responses
	*/
	public void cacheResult(java.util.List<SupportResponse> supportResponses);

	/**
	* Creates a new support response with the primary key. Does not add the support response to the database.
	*
	* @param supportResponseId the primary key for the new support response
	* @return the new support response
	*/
	public SupportResponse create(long supportResponseId);

	/**
	* Removes the support response with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response that was removed
	* @throws NoSuchSupportResponseException if a support response with the primary key could not be found
	*/
	public SupportResponse remove(long supportResponseId)
		throws NoSuchSupportResponseException;

	public SupportResponse updateImpl(SupportResponse supportResponse);

	/**
	* Returns the support response with the primary key or throws a {@link NoSuchSupportResponseException} if it could not be found.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response
	* @throws NoSuchSupportResponseException if a support response with the primary key could not be found
	*/
	public SupportResponse findByPrimaryKey(long supportResponseId)
		throws NoSuchSupportResponseException;

	/**
	* Returns the support response with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response, or <code>null</code> if a support response with the primary key could not be found
	*/
	public SupportResponse fetchByPrimaryKey(long supportResponseId);

	@Override
	public java.util.Map<java.io.Serializable, SupportResponse> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the support responses.
	*
	* @return the support responses
	*/
	public java.util.List<SupportResponse> findAll();

	/**
	* Returns a range of all the support responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @return the range of support responses
	*/
	public java.util.List<SupportResponse> findAll(int start, int end);

	/**
	* Returns an ordered range of all the support responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support responses
	*/
	public java.util.List<SupportResponse> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportResponse> orderByComparator);

	/**
	* Returns an ordered range of all the support responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of support responses
	*/
	public java.util.List<SupportResponse> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportResponse> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the support responses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of support responses.
	*
	* @return the number of support responses
	*/
	public int countAll();
}