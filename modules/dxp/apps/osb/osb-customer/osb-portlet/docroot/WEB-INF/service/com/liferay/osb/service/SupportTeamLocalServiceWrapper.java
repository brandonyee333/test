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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SupportTeamLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportTeamLocalService
 * @generated
 */
public class SupportTeamLocalServiceWrapper implements SupportTeamLocalService,
	ServiceWrapper<SupportTeamLocalService> {
	public SupportTeamLocalServiceWrapper(
		SupportTeamLocalService supportTeamLocalService) {
		_supportTeamLocalService = supportTeamLocalService;
	}

	/**
	* Adds the support team to the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam addSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.addSupportTeam(supportTeam);
	}

	/**
	* Creates a new support team with the primary key. Does not add the support team to the database.
	*
	* @param supportTeamId the primary key for the new support team
	* @return the new support team
	*/
	public com.liferay.osb.model.SupportTeam createSupportTeam(
		long supportTeamId) {
		return _supportTeamLocalService.createSupportTeam(supportTeamId);
	}

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
			com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.deleteSupportTeam(supportTeamId);
	}

	/**
	* Deletes the support team from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam deleteSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.deleteSupportTeam(supportTeam);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportTeamLocalService.dynamicQuery();
	}

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
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.dynamicQuery(dynamicQuery);
	}

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
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.dynamicQuery(dynamicQuery, start, end);
	}

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
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.SupportTeam fetchSupportTeam(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.fetchSupportTeam(supportTeamId);
	}

	/**
	* Returns the support team with the primary key.
	*
	* @param supportTeamId the primary key of the support team
	* @return the support team
	* @throws PortalException if a support team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam getSupportTeam(long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getSupportTeam(supportTeamId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getPersistedModel(primaryKeyObj);
	}

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
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getSupportTeams(start, end);
	}

	/**
	* Returns the number of support teams.
	*
	* @return the number of support teams
	* @throws SystemException if a system exception occurred
	*/
	public int getSupportTeamsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getSupportTeamsCount();
	}

	/**
	* Updates the support team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeam updateSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.updateSupportTeam(supportTeam);
	}

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
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.updateSupportTeam(supportTeam, merge);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.addAccountEntrySupportTeam(accountEntryId,
			supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntrySupportTeam(long accountEntryId,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.addAccountEntrySupportTeam(accountEntryId,
			supportTeam);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.addAccountEntrySupportTeams(accountEntryId,
			supportTeamIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addAccountEntrySupportTeams(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportTeam> SupportTeams)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.addAccountEntrySupportTeams(accountEntryId,
			SupportTeams);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearAccountEntrySupportTeams(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.clearAccountEntrySupportTeams(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.deleteAccountEntrySupportTeam(accountEntryId,
			supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteAccountEntrySupportTeam(long accountEntryId,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.deleteAccountEntrySupportTeam(accountEntryId,
			supportTeam);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.deleteAccountEntrySupportTeams(accountEntryId,
			supportTeamIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteAccountEntrySupportTeams(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportTeam> SupportTeams)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.deleteAccountEntrySupportTeams(accountEntryId,
			SupportTeams);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getAccountEntrySupportTeams(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getAccountEntrySupportTeams(accountEntryId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getAccountEntrySupportTeams(accountEntryId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public int getAccountEntrySupportTeamsCount(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getAccountEntrySupportTeamsCount(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.hasAccountEntrySupportTeam(accountEntryId,
			supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasAccountEntrySupportTeams(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.hasAccountEntrySupportTeams(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.setAccountEntrySupportTeams(accountEntryId,
			supportTeamIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.addSupportRegionSupportTeam(supportRegionId,
			supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegionSupportTeam(long supportRegionId,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.addSupportRegionSupportTeam(supportRegionId,
			supportTeam);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.addSupportRegionSupportTeams(supportRegionId,
			supportTeamIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addSupportRegionSupportTeams(long supportRegionId,
		java.util.List<com.liferay.osb.model.SupportTeam> SupportTeams)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.addSupportRegionSupportTeams(supportRegionId,
			SupportTeams);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearSupportRegionSupportTeams(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.clearSupportRegionSupportTeams(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.deleteSupportRegionSupportTeam(supportRegionId,
			supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportRegionSupportTeam(long supportRegionId,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.deleteSupportRegionSupportTeam(supportRegionId,
			supportTeam);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.deleteSupportRegionSupportTeams(supportRegionId,
			supportTeamIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteSupportRegionSupportTeams(long supportRegionId,
		java.util.List<com.liferay.osb.model.SupportTeam> SupportTeams)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.deleteSupportRegionSupportTeams(supportRegionId,
			SupportTeams);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getSupportRegionSupportTeams(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getSupportRegionSupportTeams(supportRegionId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getSupportRegionSupportTeams(supportRegionId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public int getSupportRegionSupportTeamsCount(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getSupportRegionSupportTeamsCount(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.hasSupportRegionSupportTeam(supportRegionId,
			supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasSupportRegionSupportTeams(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.hasSupportRegionSupportTeams(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.setSupportRegionSupportTeams(supportRegionId,
			supportTeamIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _supportTeamLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_supportTeamLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportTeamLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.SupportTeam addSupportTeam(long userId,
		long parentSupportTeamId, long supportLaborId,
		long locationSupportRegionId, java.lang.String name,
		java.lang.String description, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.addSupportTeam(userId,
			parentSupportTeamId, supportLaborId, locationSupportRegionId, name,
			description, type);
	}

	public java.util.List<com.liferay.osb.model.SupportTeam> getChildSupportTeams(
		long supportTeamId, boolean recursive)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getChildSupportTeams(supportTeamId,
			recursive);
	}

	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportLaborSupportTeams(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getSupportLaborSupportTeams(supportLaborId);
	}

	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getSupportTeams(start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.SupportTeam> getUserRoleSupportTeams(
		long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.getUserRoleSupportTeams(userId, role);
	}

	public java.util.List<com.liferay.osb.model.SupportTeam> search(
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.search(keywords, start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.SupportTeam> search(
		java.lang.String name, java.lang.Integer type, boolean andSearch,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.search(name, type, andSearch, start,
			end, obc);
	}

	public int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.searchCount(keywords);
	}

	public int searchCount(java.lang.String name, java.lang.Integer type,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.searchCount(name, type, andOperator);
	}

	public void setChildSupportTeams(long parentSupportTeamId,
		long[] childSupportTeamIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.setChildSupportTeams(parentSupportTeamId,
			childSupportTeamIds);
	}

	public void setSupportLaborId(long supportLaborId, long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLocalService.setSupportLaborId(supportLaborId,
			supportTeamIds);
	}

	public com.liferay.osb.model.SupportTeam updateSupportTeam(
		long supportTeamId, long parentSupportTeamId, long supportLaborId,
		long locationSupportRegionId, java.lang.String name,
		java.lang.String description, int type, long[] accountEntryIds,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportTeamLocalService.updateSupportTeam(supportTeamId,
			parentSupportTeamId, supportLaborId, locationSupportRegionId, name,
			description, type, accountEntryIds, supportRegionIds);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public SupportTeamLocalService getWrappedSupportTeamLocalService() {
		return _supportTeamLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedSupportTeamLocalService(
		SupportTeamLocalService supportTeamLocalService) {
		_supportTeamLocalService = supportTeamLocalService;
	}

	public SupportTeamLocalService getWrappedService() {
		return _supportTeamLocalService;
	}

	public void setWrappedService(
		SupportTeamLocalService supportTeamLocalService) {
		_supportTeamLocalService = supportTeamLocalService;
	}

	private SupportTeamLocalService _supportTeamLocalService;
}