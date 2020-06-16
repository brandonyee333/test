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

package com.liferay.osb.testray.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TestrayTeamLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayTeamLocalService
 * @generated
 */
@ProviderType
public class TestrayTeamLocalServiceWrapper implements TestrayTeamLocalService,
	ServiceWrapper<TestrayTeamLocalService> {
	public TestrayTeamLocalServiceWrapper(
		TestrayTeamLocalService testrayTeamLocalService) {
		_testrayTeamLocalService = testrayTeamLocalService;
	}

	/**
	* Adds the testray team to the database. Also notifies the appropriate model listeners.
	*
	* @param testrayTeam the testray team
	* @return the testray team that was added
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayTeam addTestrayTeam(
		com.liferay.osb.testray.model.TestrayTeam testrayTeam) {
		return _testrayTeamLocalService.addTestrayTeam(testrayTeam);
	}

	/**
	* Creates a new testray team with the primary key. Does not add the testray team to the database.
	*
	* @param testrayTeamId the primary key for the new testray team
	* @return the new testray team
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayTeam createTestrayTeam(
		long testrayTeamId) {
		return _testrayTeamLocalService.createTestrayTeam(testrayTeamId);
	}

	/**
	* Deletes the testray team from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayTeam the testray team
	* @return the testray team that was removed
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayTeam deleteTestrayTeam(
		com.liferay.osb.testray.model.TestrayTeam testrayTeam) {
		return _testrayTeamLocalService.deleteTestrayTeam(testrayTeam);
	}

	/**
	* Deletes the testray team with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayTeamId the primary key of the testray team
	* @return the testray team that was removed
	* @throws PortalException if a testray team with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayTeam deleteTestrayTeam(
		long testrayTeamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayTeamLocalService.deleteTestrayTeam(testrayTeamId);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayTeam fetchTestrayTeam(
		long testrayTeamId) {
		return _testrayTeamLocalService.fetchTestrayTeam(testrayTeamId);
	}

	/**
	* Returns the testray team with the primary key.
	*
	* @param testrayTeamId the primary key of the testray team
	* @return the testray team
	* @throws PortalException if a testray team with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayTeam getTestrayTeam(
		long testrayTeamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayTeamLocalService.getTestrayTeam(testrayTeamId);
	}

	/**
	* Updates the testray team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testrayTeam the testray team
	* @return the testray team that was updated
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayTeam updateTestrayTeam(
		com.liferay.osb.testray.model.TestrayTeam testrayTeam) {
		return _testrayTeamLocalService.updateTestrayTeam(testrayTeam);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _testrayTeamLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayTeamLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _testrayTeamLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayTeamLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayTeamLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of testray teams.
	*
	* @return the number of testray teams
	*/
	@Override
	public int getTestrayTeamsCount() {
		return _testrayTeamLocalService.getTestrayTeamsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _testrayTeamLocalService.getOSGiServiceIdentifier();
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
		return _testrayTeamLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _testrayTeamLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _testrayTeamLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the testray teams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray teams
	* @param end the upper bound of the range of testray teams (not inclusive)
	* @return the range of testray teams
	*/
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayTeam> getTestrayTeams(
		int start, int end) {
		return _testrayTeamLocalService.getTestrayTeams(start, end);
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
		return _testrayTeamLocalService.dynamicQueryCount(dynamicQuery);
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
		return _testrayTeamLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public TestrayTeamLocalService getWrappedService() {
		return _testrayTeamLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayTeamLocalService testrayTeamLocalService) {
		_testrayTeamLocalService = testrayTeamLocalService;
	}

	private TestrayTeamLocalService _testrayTeamLocalService;
}