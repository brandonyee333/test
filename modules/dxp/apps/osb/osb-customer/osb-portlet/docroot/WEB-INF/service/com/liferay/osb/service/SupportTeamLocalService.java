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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the support team local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamLocalServiceUtil
 * @see com.liferay.osb.service.base.SupportTeamLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SupportTeamLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface SupportTeamLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportTeamLocalServiceUtil} to access the support team local service. Add custom service methods to {@link com.liferay.osb.service.impl.SupportTeamLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the support team to the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam addSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new support team with the primary key. Does not add the support team to the database.
	*
	* @param supportTeamId the primary key for the new support team
	* @return the new support team
	*/
	public com.liferay.osb.model.SupportTeam createSupportTeam(
		long supportTeamId);

	/**
	* Deletes the support team with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeamId the primary key of the support team
	* @return the support team that was removed
	* @throws PortalException if a support team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam deleteSupportTeam(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the support team from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam deleteSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.SupportTeam fetchSupportTeam(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support team with the primary key.
	*
	* @param supportTeamId the primary key of the support team
	* @return the support team
	* @throws PortalException if a support team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.SupportTeam getSupportTeam(long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support teams.
	*
	* @return the number of support teams
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSupportTeamsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the support team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam updateSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the support team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @param merge whether to merge the support team with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the support team that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam updateSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntrySupportTeam(long accountEntryId,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntrySupportTeams(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportTeam> SupportTeams)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearAccountEntrySupportTeams(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteAccountEntrySupportTeam(long accountEntryId,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteAccountEntrySupportTeams(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportTeam> SupportTeams)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAccountEntrySupportTeamsCount(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasAccountEntrySupportTeams(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegionSupportTeam(long supportRegionId,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegionSupportTeams(long supportRegionId,
		java.util.List<com.liferay.osb.model.SupportTeam> SupportTeams)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearSupportRegionSupportTeams(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportRegionSupportTeam(long supportRegionId,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportRegionSupportTeams(long supportRegionId,
		java.util.List<com.liferay.osb.model.SupportTeam> SupportTeams)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSupportRegionSupportTeamsCount(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasSupportRegionSupportTeams(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	public com.liferay.osb.model.SupportTeam addSupportTeam(long userId,
		long parentSupportTeamId, long supportLaborId,
		long locationSupportRegionId, java.lang.String name,
		java.lang.String description, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.SupportTeam> getChildSupportTeams(
		long supportTeamId, boolean recursive)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportLaborSupportTeams(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.SupportTeam> getUserRoleSupportTeams(
		long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.SupportTeam> search(
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.SupportTeam> search(
		java.lang.String name, java.lang.Integer type, boolean andSearch,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String name, java.lang.Integer type,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void setChildSupportTeams(long parentSupportTeamId,
		long[] childSupportTeamIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void setSupportLaborId(long supportLaborId, long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.SupportTeam updateSupportTeam(
		long supportTeamId, long parentSupportTeamId, long supportLaborId,
		long locationSupportRegionId, java.lang.String name,
		java.lang.String description, int type, long[] accountEntryIds,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}