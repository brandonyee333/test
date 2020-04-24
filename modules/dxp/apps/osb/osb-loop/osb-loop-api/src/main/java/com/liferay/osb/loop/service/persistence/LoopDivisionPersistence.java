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

package com.liferay.osb.loop.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopDivisionException;
import com.liferay.osb.loop.model.LoopDivision;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the loop division service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.loop.service.persistence.impl.LoopDivisionPersistenceImpl
 * @see LoopDivisionUtil
 * @generated
 */
@ProviderType
public interface LoopDivisionPersistence extends BasePersistence<LoopDivision> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopDivisionUtil} to access the loop division persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the loop division where organizationId = &#63; or throws a {@link NoSuchLoopDivisionException} if it could not be found.
	*
	* @param organizationId the organization ID
	* @return the matching loop division
	* @throws NoSuchLoopDivisionException if a matching loop division could not be found
	*/
	public LoopDivision findByOrganizationId(long organizationId)
		throws NoSuchLoopDivisionException;

	/**
	* Returns the loop division where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param organizationId the organization ID
	* @return the matching loop division, or <code>null</code> if a matching loop division could not be found
	*/
	public LoopDivision fetchByOrganizationId(long organizationId);

	/**
	* Returns the loop division where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param organizationId the organization ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop division, or <code>null</code> if a matching loop division could not be found
	*/
	public LoopDivision fetchByOrganizationId(long organizationId,
		boolean retrieveFromCache);

	/**
	* Removes the loop division where organizationId = &#63; from the database.
	*
	* @param organizationId the organization ID
	* @return the loop division that was removed
	*/
	public LoopDivision removeByOrganizationId(long organizationId)
		throws NoSuchLoopDivisionException;

	/**
	* Returns the number of loop divisions where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @return the number of matching loop divisions
	*/
	public int countByOrganizationId(long organizationId);

	/**
	* Returns all the loop divisions where companyId = &#63; and type = &#63;.
	*
	* @param companyId the company ID
	* @param type the type
	* @return the matching loop divisions
	*/
	public java.util.List<LoopDivision> findByCI_T(long companyId, int type);

	/**
	* Returns a range of all the loop divisions where companyId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param type the type
	* @param start the lower bound of the range of loop divisions
	* @param end the upper bound of the range of loop divisions (not inclusive)
	* @return the range of matching loop divisions
	*/
	public java.util.List<LoopDivision> findByCI_T(long companyId, int type,
		int start, int end);

	/**
	* Returns an ordered range of all the loop divisions where companyId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param type the type
	* @param start the lower bound of the range of loop divisions
	* @param end the upper bound of the range of loop divisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching loop divisions
	*/
	public java.util.List<LoopDivision> findByCI_T(long companyId, int type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopDivision> orderByComparator);

	/**
	* Returns an ordered range of all the loop divisions where companyId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param companyId the company ID
	* @param type the type
	* @param start the lower bound of the range of loop divisions
	* @param end the upper bound of the range of loop divisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching loop divisions
	*/
	public java.util.List<LoopDivision> findByCI_T(long companyId, int type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopDivision> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first loop division in the ordered set where companyId = &#63; and type = &#63;.
	*
	* @param companyId the company ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching loop division
	* @throws NoSuchLoopDivisionException if a matching loop division could not be found
	*/
	public LoopDivision findByCI_T_First(long companyId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LoopDivision> orderByComparator)
		throws NoSuchLoopDivisionException;

	/**
	* Returns the first loop division in the ordered set where companyId = &#63; and type = &#63;.
	*
	* @param companyId the company ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching loop division, or <code>null</code> if a matching loop division could not be found
	*/
	public LoopDivision fetchByCI_T_First(long companyId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LoopDivision> orderByComparator);

	/**
	* Returns the last loop division in the ordered set where companyId = &#63; and type = &#63;.
	*
	* @param companyId the company ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching loop division
	* @throws NoSuchLoopDivisionException if a matching loop division could not be found
	*/
	public LoopDivision findByCI_T_Last(long companyId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LoopDivision> orderByComparator)
		throws NoSuchLoopDivisionException;

	/**
	* Returns the last loop division in the ordered set where companyId = &#63; and type = &#63;.
	*
	* @param companyId the company ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching loop division, or <code>null</code> if a matching loop division could not be found
	*/
	public LoopDivision fetchByCI_T_Last(long companyId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LoopDivision> orderByComparator);

	/**
	* Returns the loop divisions before and after the current loop division in the ordered set where companyId = &#63; and type = &#63;.
	*
	* @param loopDivisionId the primary key of the current loop division
	* @param companyId the company ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next loop division
	* @throws NoSuchLoopDivisionException if a loop division with the primary key could not be found
	*/
	public LoopDivision[] findByCI_T_PrevAndNext(long loopDivisionId,
		long companyId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<LoopDivision> orderByComparator)
		throws NoSuchLoopDivisionException;

	/**
	* Removes all the loop divisions where companyId = &#63; and type = &#63; from the database.
	*
	* @param companyId the company ID
	* @param type the type
	*/
	public void removeByCI_T(long companyId, int type);

	/**
	* Returns the number of loop divisions where companyId = &#63; and type = &#63;.
	*
	* @param companyId the company ID
	* @param type the type
	* @return the number of matching loop divisions
	*/
	public int countByCI_T(long companyId, int type);

	/**
	* Caches the loop division in the entity cache if it is enabled.
	*
	* @param loopDivision the loop division
	*/
	public void cacheResult(LoopDivision loopDivision);

	/**
	* Caches the loop divisions in the entity cache if it is enabled.
	*
	* @param loopDivisions the loop divisions
	*/
	public void cacheResult(java.util.List<LoopDivision> loopDivisions);

	/**
	* Creates a new loop division with the primary key. Does not add the loop division to the database.
	*
	* @param loopDivisionId the primary key for the new loop division
	* @return the new loop division
	*/
	public LoopDivision create(long loopDivisionId);

	/**
	* Removes the loop division with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopDivisionId the primary key of the loop division
	* @return the loop division that was removed
	* @throws NoSuchLoopDivisionException if a loop division with the primary key could not be found
	*/
	public LoopDivision remove(long loopDivisionId)
		throws NoSuchLoopDivisionException;

	public LoopDivision updateImpl(LoopDivision loopDivision);

	/**
	* Returns the loop division with the primary key or throws a {@link NoSuchLoopDivisionException} if it could not be found.
	*
	* @param loopDivisionId the primary key of the loop division
	* @return the loop division
	* @throws NoSuchLoopDivisionException if a loop division with the primary key could not be found
	*/
	public LoopDivision findByPrimaryKey(long loopDivisionId)
		throws NoSuchLoopDivisionException;

	/**
	* Returns the loop division with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopDivisionId the primary key of the loop division
	* @return the loop division, or <code>null</code> if a loop division with the primary key could not be found
	*/
	public LoopDivision fetchByPrimaryKey(long loopDivisionId);

	@Override
	public java.util.Map<java.io.Serializable, LoopDivision> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the loop divisions.
	*
	* @return the loop divisions
	*/
	public java.util.List<LoopDivision> findAll();

	/**
	* Returns a range of all the loop divisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop divisions
	* @param end the upper bound of the range of loop divisions (not inclusive)
	* @return the range of loop divisions
	*/
	public java.util.List<LoopDivision> findAll(int start, int end);

	/**
	* Returns an ordered range of all the loop divisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop divisions
	* @param end the upper bound of the range of loop divisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop divisions
	*/
	public java.util.List<LoopDivision> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopDivision> orderByComparator);

	/**
	* Returns an ordered range of all the loop divisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopDivisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop divisions
	* @param end the upper bound of the range of loop divisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop divisions
	*/
	public java.util.List<LoopDivision> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopDivision> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the loop divisions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of loop divisions.
	*
	* @return the number of loop divisions
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}