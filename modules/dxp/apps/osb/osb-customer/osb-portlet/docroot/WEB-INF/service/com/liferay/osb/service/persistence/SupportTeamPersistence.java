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

import com.liferay.osb.model.SupportTeam;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the support team service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamPersistenceImpl
 * @see SupportTeamUtil
 * @generated
 */
public interface SupportTeamPersistence extends BasePersistence<SupportTeam> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportTeamUtil} to access the support team persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the support team in the entity cache if it is enabled.
	*
	* @param supportTeam the support team
	*/
	public void cacheResult(com.liferay.osb.model.SupportTeam supportTeam);

	/**
	* Caches the support teams in the entity cache if it is enabled.
	*
	* @param supportTeams the support teams
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams);

	/**
	* Creates a new support team with the primary key. Does not add the support team to the database.
	*
	* @param supportTeamId the primary key for the new support team
	* @return the new support team
	*/
	public com.liferay.osb.model.SupportTeam create(long supportTeamId);

	/**
	* Removes the support team with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeamId the primary key of the support team
	* @return the support team that was removed
	* @throws com.liferay.osb.NoSuchSupportTeamException if a support team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam remove(long supportTeamId)
		throws com.liferay.osb.NoSuchSupportTeamException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.SupportTeam updateImpl(
		com.liferay.osb.model.SupportTeam supportTeam, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support team with the primary key or throws a {@link com.liferay.osb.NoSuchSupportTeamException} if it could not be found.
	*
	* @param supportTeamId the primary key of the support team
	* @return the support team
	* @throws com.liferay.osb.NoSuchSupportTeamException if a support team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam findByPrimaryKey(
		long supportTeamId)
		throws com.liferay.osb.NoSuchSupportTeamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support team with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportTeamId the primary key of the support team
	* @return the support team, or <code>null</code> if a support team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam fetchByPrimaryKey(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the support teams where parentSupportTeamId = &#63;.
	*
	* @param parentSupportTeamId the parent support team ID
	* @return the matching support teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> findByParentSupportTeamId(
		long parentSupportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the support teams where parentSupportTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentSupportTeamId the parent support team ID
	* @param start the lower bound of the range of support teams
	* @param end the upper bound of the range of support teams (not inclusive)
	* @return the range of matching support teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> findByParentSupportTeamId(
		long parentSupportTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the support teams where parentSupportTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentSupportTeamId the parent support team ID
	* @param start the lower bound of the range of support teams
	* @param end the upper bound of the range of support teams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> findByParentSupportTeamId(
		long parentSupportTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first support team in the ordered set where parentSupportTeamId = &#63;.
	*
	* @param parentSupportTeamId the parent support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support team
	* @throws com.liferay.osb.NoSuchSupportTeamException if a matching support team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam findByParentSupportTeamId_First(
		long parentSupportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportTeamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first support team in the ordered set where parentSupportTeamId = &#63;.
	*
	* @param parentSupportTeamId the parent support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support team, or <code>null</code> if a matching support team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam fetchByParentSupportTeamId_First(
		long parentSupportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last support team in the ordered set where parentSupportTeamId = &#63;.
	*
	* @param parentSupportTeamId the parent support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support team
	* @throws com.liferay.osb.NoSuchSupportTeamException if a matching support team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam findByParentSupportTeamId_Last(
		long parentSupportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportTeamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last support team in the ordered set where parentSupportTeamId = &#63;.
	*
	* @param parentSupportTeamId the parent support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support team, or <code>null</code> if a matching support team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam fetchByParentSupportTeamId_Last(
		long parentSupportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support teams before and after the current support team in the ordered set where parentSupportTeamId = &#63;.
	*
	* @param supportTeamId the primary key of the current support team
	* @param parentSupportTeamId the parent support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support team
	* @throws com.liferay.osb.NoSuchSupportTeamException if a support team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam[] findByParentSupportTeamId_PrevAndNext(
		long supportTeamId, long parentSupportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportTeamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the support teams where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @return the matching support teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> findBySupportLaborId(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the support teams where supportLaborId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportLaborId the support labor ID
	* @param start the lower bound of the range of support teams
	* @param end the upper bound of the range of support teams (not inclusive)
	* @return the range of matching support teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> findBySupportLaborId(
		long supportLaborId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the support teams where supportLaborId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportLaborId the support labor ID
	* @param start the lower bound of the range of support teams
	* @param end the upper bound of the range of support teams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> findBySupportLaborId(
		long supportLaborId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first support team in the ordered set where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support team
	* @throws com.liferay.osb.NoSuchSupportTeamException if a matching support team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam findBySupportLaborId_First(
		long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportTeamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first support team in the ordered set where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support team, or <code>null</code> if a matching support team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam fetchBySupportLaborId_First(
		long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last support team in the ordered set where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support team
	* @throws com.liferay.osb.NoSuchSupportTeamException if a matching support team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam findBySupportLaborId_Last(
		long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportTeamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last support team in the ordered set where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support team, or <code>null</code> if a matching support team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam fetchBySupportLaborId_Last(
		long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support teams before and after the current support team in the ordered set where supportLaborId = &#63;.
	*
	* @param supportTeamId the primary key of the current support team
	* @param supportLaborId the support labor ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support team
	* @throws com.liferay.osb.NoSuchSupportTeamException if a support team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam[] findBySupportLaborId_PrevAndNext(
		long supportTeamId, long supportLaborId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportTeamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support team where name = &#63; or throws a {@link com.liferay.osb.NoSuchSupportTeamException} if it could not be found.
	*
	* @param name the name
	* @return the matching support team
	* @throws com.liferay.osb.NoSuchSupportTeamException if a matching support team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam findByName(java.lang.String name)
		throws com.liferay.osb.NoSuchSupportTeamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support team where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching support team, or <code>null</code> if a matching support team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam fetchByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support team where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching support team, or <code>null</code> if a matching support team could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam fetchByName(
		java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the support teams.
	*
	* @return the support teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the support teams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support teams
	* @param end the upper bound of the range of support teams (not inclusive)
	* @return the range of support teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the support teams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support teams
	* @param end the upper bound of the range of support teams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support teams
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the support teams where parentSupportTeamId = &#63; from the database.
	*
	* @param parentSupportTeamId the parent support team ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByParentSupportTeamId(long parentSupportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the support teams where supportLaborId = &#63; from the database.
	*
	* @param supportLaborId the support labor ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySupportLaborId(long supportLaborId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the support team where name = &#63; from the database.
	*
	* @param name the name
	* @return the support team that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam removeByName(java.lang.String name)
		throws com.liferay.osb.NoSuchSupportTeamException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the support teams from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support teams where parentSupportTeamId = &#63;.
	*
	* @param parentSupportTeamId the parent support team ID
	* @return the number of matching support teams
	* @throws SystemException if a system exception occurred
	*/
	public int countByParentSupportTeamId(long parentSupportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support teams where supportLaborId = &#63;.
	*
	* @param supportLaborId the support labor ID
	* @return the number of matching support teams
	* @throws SystemException if a system exception occurred
	*/
	public int countBySupportLaborId(long supportLaborId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support teams where name = &#63;.
	*
	* @param name the name
	* @return the number of matching support teams
	* @throws SystemException if a system exception occurred
	*/
	public int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support teams.
	*
	* @return the number of support teams
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account entries associated with the support team.
	*
	* @param pk the primary key of the support team
	* @return the account entries associated with the support team
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account entries associated with the support team.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the support team
	* @param start the lower bound of the range of support teams
	* @param end the upper bound of the range of support teams (not inclusive)
	* @return the range of account entries associated with the support team
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account entries associated with the support team.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the support team
	* @param start the lower bound of the range of support teams
	* @param end the upper bound of the range of support teams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account entries associated with the support team
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account entries associated with the support team.
	*
	* @param pk the primary key of the support team
	* @return the number of account entries associated with the support team
	* @throws SystemException if a system exception occurred
	*/
	public int getAccountEntriesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the account entry is associated with the support team.
	*
	* @param pk the primary key of the support team
	* @param accountEntryPK the primary key of the account entry
	* @return <code>true</code> if the account entry is associated with the support team; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsAccountEntry(long pk, long accountEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the support team has any account entries associated with it.
	*
	* @param pk the primary key of the support team to check for associations with account entries
	* @return <code>true</code> if the support team has any account entries associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsAccountEntries(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the support team and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param accountEntryPK the primary key of the account entry
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntry(long pk, long accountEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the support team and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param accountEntry the account entry
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntry(long pk,
		com.liferay.osb.model.AccountEntry accountEntry)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the support team and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param accountEntryPKs the primary keys of the account entries
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntries(long pk, long[] accountEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the support team and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param accountEntries the account entries
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntries(long pk,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Clears all associations between the support team and its account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team to clear the associated account entries from
	* @throws SystemException if a system exception occurred
	*/
	public void clearAccountEntries(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the support team and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param accountEntryPK the primary key of the account entry
	* @throws SystemException if a system exception occurred
	*/
	public void removeAccountEntry(long pk, long accountEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the support team and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param accountEntry the account entry
	* @throws SystemException if a system exception occurred
	*/
	public void removeAccountEntry(long pk,
		com.liferay.osb.model.AccountEntry accountEntry)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the support team and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param accountEntryPKs the primary keys of the account entries
	* @throws SystemException if a system exception occurred
	*/
	public void removeAccountEntries(long pk, long[] accountEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the support team and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param accountEntries the account entries
	* @throws SystemException if a system exception occurred
	*/
	public void removeAccountEntries(long pk,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the account entries associated with the support team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param accountEntryPKs the primary keys of the account entries to be associated with the support team
	* @throws SystemException if a system exception occurred
	*/
	public void setAccountEntries(long pk, long[] accountEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the account entries associated with the support team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param accountEntries the account entries to be associated with the support team
	* @throws SystemException if a system exception occurred
	*/
	public void setAccountEntries(long pk,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the support regions associated with the support team.
	*
	* @param pk the primary key of the support team
	* @return the support regions associated with the support team
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the support regions associated with the support team.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the support team
	* @param start the lower bound of the range of support teams
	* @param end the upper bound of the range of support teams (not inclusive)
	* @return the range of support regions associated with the support team
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the support regions associated with the support team.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the support team
	* @param start the lower bound of the range of support teams
	* @param end the upper bound of the range of support teams (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support regions associated with the support team
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support regions associated with the support team.
	*
	* @param pk the primary key of the support team
	* @return the number of support regions associated with the support team
	* @throws SystemException if a system exception occurred
	*/
	public int getSupportRegionsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the support region is associated with the support team.
	*
	* @param pk the primary key of the support team
	* @param supportRegionPK the primary key of the support region
	* @return <code>true</code> if the support region is associated with the support team; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsSupportRegion(long pk, long supportRegionPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the support team has any support regions associated with it.
	*
	* @param pk the primary key of the support team to check for associations with support regions
	* @return <code>true</code> if the support team has any support regions associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsSupportRegions(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the support team and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param supportRegionPK the primary key of the support region
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegion(long pk, long supportRegionPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the support team and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param supportRegion the support region
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the support team and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param supportRegionPKs the primary keys of the support regions
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegions(long pk, long[] supportRegionPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Adds an association between the support team and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param supportRegions the support regions
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Clears all associations between the support team and its support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team to clear the associated support regions from
	* @throws SystemException if a system exception occurred
	*/
	public void clearSupportRegions(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the support team and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param supportRegionPK the primary key of the support region
	* @throws SystemException if a system exception occurred
	*/
	public void removeSupportRegion(long pk, long supportRegionPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the support team and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param supportRegion the support region
	* @throws SystemException if a system exception occurred
	*/
	public void removeSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the support team and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param supportRegionPKs the primary keys of the support regions
	* @throws SystemException if a system exception occurred
	*/
	public void removeSupportRegions(long pk, long[] supportRegionPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the association between the support team and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param supportRegions the support regions
	* @throws SystemException if a system exception occurred
	*/
	public void removeSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the support regions associated with the support team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param supportRegionPKs the primary keys of the support regions to be associated with the support team
	* @throws SystemException if a system exception occurred
	*/
	public void setSupportRegions(long pk, long[] supportRegionPKs)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Sets the support regions associated with the support team, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support team
	* @param supportRegions the support regions to be associated with the support team
	* @throws SystemException if a system exception occurred
	*/
	public void setSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws com.liferay.portal.kernel.exception.SystemException;
}