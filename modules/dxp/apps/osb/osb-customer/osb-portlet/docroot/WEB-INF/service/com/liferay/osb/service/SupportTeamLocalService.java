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

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.SupportTeam;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for SupportTeam. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamLocalServiceUtil
 * @see com.liferay.osb.service.base.SupportTeamLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SupportTeamLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SupportTeamLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportTeamLocalServiceUtil} to access the support team local service. Add custom service methods to {@link com.liferay.osb.service.impl.SupportTeamLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasAccountEntrySupportTeams(long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasSupportRegionSupportTeams(long supportRegionId);

	/**
	* Adds the support team to the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SupportTeam addSupportTeam(SupportTeam supportTeam);

	public SupportTeam addSupportTeam(long userId, long parentSupportTeamId,
		long supportLaborId, long locationSupportRegionId,
		java.lang.String name, java.lang.String description, int type)
		throws PortalException;

	/**
	* Creates a new support team with the primary key. Does not add the support team to the database.
	*
	* @param supportTeamId the primary key for the new support team
	* @return the new support team
	*/
	public SupportTeam createSupportTeam(long supportTeamId);

	/**
	* Deletes the support team from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public SupportTeam deleteSupportTeam(SupportTeam supportTeam);

	/**
	* Deletes the support team with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeamId the primary key of the support team
	* @return the support team that was removed
	* @throws PortalException if a support team with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public SupportTeam deleteSupportTeam(long supportTeamId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SupportTeam fetchSupportTeam(long supportTeamId);

	/**
	* Returns the support team with the primary key.
	*
	* @param supportTeamId the primary key of the support team
	* @return the support team
	* @throws PortalException if a support team with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SupportTeam getSupportTeam(long supportTeamId)
		throws PortalException;

	/**
	* Updates the support team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public SupportTeam updateSupportTeam(SupportTeam supportTeam);

	public SupportTeam updateSupportTeam(long supportTeamId,
		long parentSupportTeamId, long supportLaborId,
		long locationSupportRegionId, java.lang.String name,
		java.lang.String description, int type, long[] accountEntryIds,
		long[] supportRegionIds) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAccountEntrySupportTeamsCount(long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSupportRegionSupportTeamsCount(long supportRegionId);

	/**
	* Returns the number of support teams.
	*
	* @return the number of support teams
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSupportTeamsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String keywords);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String name, java.lang.Integer type,
		boolean andOperator);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportTeam> getAccountEntrySupportTeams(long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportTeam> getAccountEntrySupportTeams(long accountEntryId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportTeam> getAccountEntrySupportTeams(long accountEntryId,
		int start, int end, OrderByComparator<SupportTeam> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportTeam> getChildSupportTeams(long supportTeamId,
		boolean recursive);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportTeam> getSupportLaborSupportTeams(long supportLaborId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportTeam> getSupportRegionSupportTeams(long supportRegionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId, int start, int end,
		OrderByComparator<SupportTeam> orderByComparator);

	/**
	* Returns a range of all the support teams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support teams
	* @param end the upper bound of the range of support teams (not inclusive)
	* @return the range of support teams
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportTeam> getSupportTeams(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportTeam> getSupportTeams(int start, int end,
		OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportTeam> getUserRoleSupportTeams(long userId, int role);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportTeam> search(java.lang.String keywords, int start,
		int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<SupportTeam> search(java.lang.String name,
		java.lang.Integer type, boolean andSearch, int start, int end,
		OrderByComparator obc);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* Returns the accountEntryIds of the account entries associated with the support team.
	*
	* @param supportTeamId the supportTeamId of the support team
	* @return long[] the accountEntryIds of account entries associated with the support team
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getAccountEntryPrimaryKeys(long supportTeamId);

	/**
	* Returns the supportRegionIds of the support regions associated with the support team.
	*
	* @param supportTeamId the supportTeamId of the support team
	* @return long[] the supportRegionIds of support regions associated with the support team
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getSupportRegionPrimaryKeys(long supportTeamId);

	public void addAccountEntrySupportTeam(long accountEntryId,
		SupportTeam supportTeam);

	public void addAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId);

	public void addAccountEntrySupportTeams(long accountEntryId,
		List<SupportTeam> supportTeams);

	public void addAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds);

	public void addSupportRegionSupportTeam(long supportRegionId,
		SupportTeam supportTeam);

	public void addSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId);

	public void addSupportRegionSupportTeams(long supportRegionId,
		List<SupportTeam> supportTeams);

	public void addSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds);

	public void clearAccountEntrySupportTeams(long accountEntryId);

	public void clearSupportRegionSupportTeams(long supportRegionId);

	public void deleteAccountEntrySupportTeam(long accountEntryId,
		SupportTeam supportTeam);

	public void deleteAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId);

	public void deleteAccountEntrySupportTeams(long accountEntryId,
		List<SupportTeam> supportTeams);

	public void deleteAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds);

	public void deleteSupportRegionSupportTeam(long supportRegionId,
		SupportTeam supportTeam);

	public void deleteSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId);

	public void deleteSupportRegionSupportTeams(long supportRegionId,
		List<SupportTeam> supportTeams);

	public void deleteSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds);

	public void setAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds);

	public void setChildSupportTeams(long parentSupportTeamId,
		long[] childSupportTeamIds) throws PortalException;

	public void setSupportLaborId(long supportLaborId, long[] supportTeamIds)
		throws PortalException;

	public void setSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds);
}