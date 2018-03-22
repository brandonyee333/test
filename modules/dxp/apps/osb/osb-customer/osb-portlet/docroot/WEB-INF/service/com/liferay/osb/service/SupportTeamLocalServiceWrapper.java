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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SupportTeamLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamLocalService
 * @generated
 */
@ProviderType
public class SupportTeamLocalServiceWrapper implements SupportTeamLocalService,
	ServiceWrapper<SupportTeamLocalService> {
	public SupportTeamLocalServiceWrapper(
		SupportTeamLocalService supportTeamLocalService) {
		_supportTeamLocalService = supportTeamLocalService;
	}

	@Override
	public boolean hasAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId) {
		return _supportTeamLocalService.hasAccountEntrySupportTeam(accountEntryId,
			supportTeamId);
	}

	@Override
	public boolean hasAccountEntrySupportTeams(long accountEntryId) {
		return _supportTeamLocalService.hasAccountEntrySupportTeams(accountEntryId);
	}

	@Override
	public boolean hasSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId) {
		return _supportTeamLocalService.hasSupportRegionSupportTeam(supportRegionId,
			supportTeamId);
	}

	@Override
	public boolean hasSupportRegionSupportTeams(long supportRegionId) {
		return _supportTeamLocalService.hasSupportRegionSupportTeams(supportRegionId);
	}

	/**
	* Adds the support team to the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was added
	*/
	@Override
	public com.liferay.osb.model.SupportTeam addSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam) {
		return _supportTeamLocalService.addSupportTeam(supportTeam);
	}

	@Override
	public com.liferay.osb.model.SupportTeam addSupportTeam(long userId,
		long parentSupportTeamId, long supportLaborId,
		long locationSupportRegionId, java.lang.String name,
		java.lang.String description, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportTeamLocalService.addSupportTeam(userId,
			parentSupportTeamId, supportLaborId, locationSupportRegionId, name,
			description, type);
	}

	/**
	* Creates a new support team with the primary key. Does not add the support team to the database.
	*
	* @param supportTeamId the primary key for the new support team
	* @return the new support team
	*/
	@Override
	public com.liferay.osb.model.SupportTeam createSupportTeam(
		long supportTeamId) {
		return _supportTeamLocalService.createSupportTeam(supportTeamId);
	}

	/**
	* Deletes the support team from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was removed
	*/
	@Override
	public com.liferay.osb.model.SupportTeam deleteSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam) {
		return _supportTeamLocalService.deleteSupportTeam(supportTeam);
	}

	/**
	* Deletes the support team with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeamId the primary key of the support team
	* @return the support team that was removed
	* @throws PortalException if a support team with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportTeam deleteSupportTeam(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportTeamLocalService.deleteSupportTeam(supportTeamId);
	}

	@Override
	public com.liferay.osb.model.SupportTeam fetchSupportTeam(
		long supportTeamId) {
		return _supportTeamLocalService.fetchSupportTeam(supportTeamId);
	}

	/**
	* Returns the support team with the primary key.
	*
	* @param supportTeamId the primary key of the support team
	* @return the support team
	* @throws PortalException if a support team with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.SupportTeam getSupportTeam(long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportTeamLocalService.getSupportTeam(supportTeamId);
	}

	/**
	* Updates the support team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was updated
	*/
	@Override
	public com.liferay.osb.model.SupportTeam updateSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam) {
		return _supportTeamLocalService.updateSupportTeam(supportTeam);
	}

	@Override
	public com.liferay.osb.model.SupportTeam updateSupportTeam(
		long supportTeamId, long parentSupportTeamId, long supportLaborId,
		long locationSupportRegionId, java.lang.String name,
		java.lang.String description, int type, long[] accountEntryIds,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportTeamLocalService.updateSupportTeam(supportTeamId,
			parentSupportTeamId, supportLaborId, locationSupportRegionId, name,
			description, type, accountEntryIds, supportRegionIds);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _supportTeamLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _supportTeamLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _supportTeamLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportTeamLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportTeamLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getAccountEntrySupportTeamsCount(long accountEntryId) {
		return _supportTeamLocalService.getAccountEntrySupportTeamsCount(accountEntryId);
	}

	@Override
	public int getSupportRegionSupportTeamsCount(long supportRegionId) {
		return _supportTeamLocalService.getSupportRegionSupportTeamsCount(supportRegionId);
	}

	/**
	* Returns the number of support teams.
	*
	* @return the number of support teams
	*/
	@Override
	public int getSupportTeamsCount() {
		return _supportTeamLocalService.getSupportTeamsCount();
	}

	@Override
	public int searchCount(java.lang.String keywords) {
		return _supportTeamLocalService.searchCount(keywords);
	}

	@Override
	public int searchCount(java.lang.String name, java.lang.Integer type,
		boolean andOperator) {
		return _supportTeamLocalService.searchCount(name, type, andOperator);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _supportTeamLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _supportTeamLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _supportTeamLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _supportTeamLocalService.dynamicQuery(dynamicQuery, start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _supportTeamLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId) {
		return _supportTeamLocalService.getAccountEntrySupportTeams(accountEntryId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId, int start, int end) {
		return _supportTeamLocalService.getAccountEntrySupportTeams(accountEntryId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.SupportTeam> orderByComparator) {
		return _supportTeamLocalService.getAccountEntrySupportTeams(accountEntryId,
			start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportTeam> getChildSupportTeams(
		long supportTeamId, boolean recursive) {
		return _supportTeamLocalService.getChildSupportTeams(supportTeamId,
			recursive);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportLaborSupportTeams(
		long supportLaborId) {
		return _supportTeamLocalService.getSupportLaborSupportTeams(supportLaborId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId) {
		return _supportTeamLocalService.getSupportRegionSupportTeams(supportRegionId);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId, int start, int end) {
		return _supportTeamLocalService.getSupportRegionSupportTeams(supportRegionId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.SupportTeam> orderByComparator) {
		return _supportTeamLocalService.getSupportRegionSupportTeams(supportRegionId,
			start, end, orderByComparator);
	}

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
	@Override
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		int start, int end) {
		return _supportTeamLocalService.getSupportTeams(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _supportTeamLocalService.getSupportTeams(start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportTeam> getUserRoleSupportTeams(
		long userId, int role) {
		return _supportTeamLocalService.getUserRoleSupportTeams(userId, role);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportTeam> search(
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _supportTeamLocalService.search(keywords, start, end, obc);
	}

	@Override
	public java.util.List<com.liferay.osb.model.SupportTeam> search(
		java.lang.String name, java.lang.Integer type, boolean andSearch,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return _supportTeamLocalService.search(name, type, andSearch, start,
			end, obc);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _supportTeamLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _supportTeamLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Returns the accountEntryIds of the account entries associated with the support team.
	*
	* @param supportTeamId the supportTeamId of the support team
	* @return long[] the accountEntryIds of account entries associated with the support team
	*/
	@Override
	public long[] getAccountEntryPrimaryKeys(long supportTeamId) {
		return _supportTeamLocalService.getAccountEntryPrimaryKeys(supportTeamId);
	}

	/**
	* Returns the supportRegionIds of the support regions associated with the support team.
	*
	* @param supportTeamId the supportTeamId of the support team
	* @return long[] the supportRegionIds of support regions associated with the support team
	*/
	@Override
	public long[] getSupportRegionPrimaryKeys(long supportTeamId) {
		return _supportTeamLocalService.getSupportRegionPrimaryKeys(supportTeamId);
	}

	@Override
	public void addAccountEntrySupportTeam(long accountEntryId,
		com.liferay.osb.model.SupportTeam supportTeam) {
		_supportTeamLocalService.addAccountEntrySupportTeam(accountEntryId,
			supportTeam);
	}

	@Override
	public void addAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId) {
		_supportTeamLocalService.addAccountEntrySupportTeam(accountEntryId,
			supportTeamId);
	}

	@Override
	public void addAccountEntrySupportTeams(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams) {
		_supportTeamLocalService.addAccountEntrySupportTeams(accountEntryId,
			supportTeams);
	}

	@Override
	public void addAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds) {
		_supportTeamLocalService.addAccountEntrySupportTeams(accountEntryId,
			supportTeamIds);
	}

	@Override
	public void addSupportRegionSupportTeam(long supportRegionId,
		com.liferay.osb.model.SupportTeam supportTeam) {
		_supportTeamLocalService.addSupportRegionSupportTeam(supportRegionId,
			supportTeam);
	}

	@Override
	public void addSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId) {
		_supportTeamLocalService.addSupportRegionSupportTeam(supportRegionId,
			supportTeamId);
	}

	@Override
	public void addSupportRegionSupportTeams(long supportRegionId,
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams) {
		_supportTeamLocalService.addSupportRegionSupportTeams(supportRegionId,
			supportTeams);
	}

	@Override
	public void addSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds) {
		_supportTeamLocalService.addSupportRegionSupportTeams(supportRegionId,
			supportTeamIds);
	}

	@Override
	public void clearAccountEntrySupportTeams(long accountEntryId) {
		_supportTeamLocalService.clearAccountEntrySupportTeams(accountEntryId);
	}

	@Override
	public void clearSupportRegionSupportTeams(long supportRegionId) {
		_supportTeamLocalService.clearSupportRegionSupportTeams(supportRegionId);
	}

	@Override
	public void deleteAccountEntrySupportTeam(long accountEntryId,
		com.liferay.osb.model.SupportTeam supportTeam) {
		_supportTeamLocalService.deleteAccountEntrySupportTeam(accountEntryId,
			supportTeam);
	}

	@Override
	public void deleteAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId) {
		_supportTeamLocalService.deleteAccountEntrySupportTeam(accountEntryId,
			supportTeamId);
	}

	@Override
	public void deleteAccountEntrySupportTeams(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams) {
		_supportTeamLocalService.deleteAccountEntrySupportTeams(accountEntryId,
			supportTeams);
	}

	@Override
	public void deleteAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds) {
		_supportTeamLocalService.deleteAccountEntrySupportTeams(accountEntryId,
			supportTeamIds);
	}

	@Override
	public void deleteSupportRegionSupportTeam(long supportRegionId,
		com.liferay.osb.model.SupportTeam supportTeam) {
		_supportTeamLocalService.deleteSupportRegionSupportTeam(supportRegionId,
			supportTeam);
	}

	@Override
	public void deleteSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId) {
		_supportTeamLocalService.deleteSupportRegionSupportTeam(supportRegionId,
			supportTeamId);
	}

	@Override
	public void deleteSupportRegionSupportTeams(long supportRegionId,
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams) {
		_supportTeamLocalService.deleteSupportRegionSupportTeams(supportRegionId,
			supportTeams);
	}

	@Override
	public void deleteSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds) {
		_supportTeamLocalService.deleteSupportRegionSupportTeams(supportRegionId,
			supportTeamIds);
	}

	@Override
	public void setAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds) {
		_supportTeamLocalService.setAccountEntrySupportTeams(accountEntryId,
			supportTeamIds);
	}

	@Override
	public void setChildSupportTeams(long parentSupportTeamId,
		long[] childSupportTeamIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_supportTeamLocalService.setChildSupportTeams(parentSupportTeamId,
			childSupportTeamIds);
	}

	@Override
	public void setSupportLaborId(long supportLaborId, long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		_supportTeamLocalService.setSupportLaborId(supportLaborId,
			supportTeamIds);
	}

	@Override
	public void setSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds) {
		_supportTeamLocalService.setSupportRegionSupportTeams(supportRegionId,
			supportTeamIds);
	}

	@Override
	public SupportTeamLocalService getWrappedService() {
		return _supportTeamLocalService;
	}

	@Override
	public void setWrappedService(
		SupportTeamLocalService supportTeamLocalService) {
		_supportTeamLocalService = supportTeamLocalService;
	}

	private SupportTeamLocalService _supportTeamLocalService;
}