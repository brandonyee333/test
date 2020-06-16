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
 * Provides a wrapper for {@link TestrayComponentLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayComponentLocalService
 * @generated
 */
@ProviderType
public class TestrayComponentLocalServiceWrapper
	implements TestrayComponentLocalService,
		ServiceWrapper<TestrayComponentLocalService> {
	public TestrayComponentLocalServiceWrapper(
		TestrayComponentLocalService testrayComponentLocalService) {
		_testrayComponentLocalService = testrayComponentLocalService;
	}

	@Override
	public boolean hasTestrayCaseTestrayComponent(long testrayCaseId,
		long testrayComponentId) {
		return _testrayComponentLocalService.hasTestrayCaseTestrayComponent(testrayCaseId,
			testrayComponentId);
	}

	@Override
	public boolean hasTestrayCaseTestrayComponents(long testrayCaseId) {
		return _testrayComponentLocalService.hasTestrayCaseTestrayComponents(testrayCaseId);
	}

	/**
	* Adds the testray component to the database. Also notifies the appropriate model listeners.
	*
	* @param testrayComponent the testray component
	* @return the testray component that was added
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayComponent addTestrayComponent(
		com.liferay.osb.testray.model.TestrayComponent testrayComponent) {
		return _testrayComponentLocalService.addTestrayComponent(testrayComponent);
	}

	/**
	* Creates a new testray component with the primary key. Does not add the testray component to the database.
	*
	* @param testrayComponentId the primary key for the new testray component
	* @return the new testray component
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayComponent createTestrayComponent(
		long testrayComponentId) {
		return _testrayComponentLocalService.createTestrayComponent(testrayComponentId);
	}

	/**
	* Deletes the testray component from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayComponent the testray component
	* @return the testray component that was removed
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayComponent deleteTestrayComponent(
		com.liferay.osb.testray.model.TestrayComponent testrayComponent) {
		return _testrayComponentLocalService.deleteTestrayComponent(testrayComponent);
	}

	/**
	* Deletes the testray component with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayComponentId the primary key of the testray component
	* @return the testray component that was removed
	* @throws PortalException if a testray component with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayComponent deleteTestrayComponent(
		long testrayComponentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayComponentLocalService.deleteTestrayComponent(testrayComponentId);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayComponent fetchTestrayComponent(
		long testrayComponentId) {
		return _testrayComponentLocalService.fetchTestrayComponent(testrayComponentId);
	}

	/**
	* Returns the testray component with the primary key.
	*
	* @param testrayComponentId the primary key of the testray component
	* @return the testray component
	* @throws PortalException if a testray component with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayComponent getTestrayComponent(
		long testrayComponentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayComponentLocalService.getTestrayComponent(testrayComponentId);
	}

	/**
	* Updates the testray component in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testrayComponent the testray component
	* @return the testray component that was updated
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayComponent updateTestrayComponent(
		com.liferay.osb.testray.model.TestrayComponent testrayComponent) {
		return _testrayComponentLocalService.updateTestrayComponent(testrayComponent);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _testrayComponentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayComponentLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _testrayComponentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayComponentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayComponentLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getTestrayCaseTestrayComponentsCount(long testrayCaseId) {
		return _testrayComponentLocalService.getTestrayCaseTestrayComponentsCount(testrayCaseId);
	}

	/**
	* Returns the number of testray components.
	*
	* @return the number of testray components
	*/
	@Override
	public int getTestrayComponentsCount() {
		return _testrayComponentLocalService.getTestrayComponentsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _testrayComponentLocalService.getOSGiServiceIdentifier();
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
		return _testrayComponentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _testrayComponentLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _testrayComponentLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayComponent> getTestrayCaseTestrayComponents(
		long testrayCaseId) {
		return _testrayComponentLocalService.getTestrayCaseTestrayComponents(testrayCaseId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayComponent> getTestrayCaseTestrayComponents(
		long testrayCaseId, int start, int end) {
		return _testrayComponentLocalService.getTestrayCaseTestrayComponents(testrayCaseId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayComponent> getTestrayCaseTestrayComponents(
		long testrayCaseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.testray.model.TestrayComponent> orderByComparator) {
		return _testrayComponentLocalService.getTestrayCaseTestrayComponents(testrayCaseId,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the testray components.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayComponentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray components
	* @param end the upper bound of the range of testray components (not inclusive)
	* @return the range of testray components
	*/
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayComponent> getTestrayComponents(
		int start, int end) {
		return _testrayComponentLocalService.getTestrayComponents(start, end);
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
		return _testrayComponentLocalService.dynamicQueryCount(dynamicQuery);
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
		return _testrayComponentLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Returns the testrayCaseIds of the testray cases associated with the testray component.
	*
	* @param testrayComponentId the testrayComponentId of the testray component
	* @return long[] the testrayCaseIds of testray cases associated with the testray component
	*/
	@Override
	public long[] getTestrayCasePrimaryKeys(long testrayComponentId) {
		return _testrayComponentLocalService.getTestrayCasePrimaryKeys(testrayComponentId);
	}

	@Override
	public void addTestrayCaseTestrayComponent(long testrayCaseId,
		com.liferay.osb.testray.model.TestrayComponent testrayComponent) {
		_testrayComponentLocalService.addTestrayCaseTestrayComponent(testrayCaseId,
			testrayComponent);
	}

	@Override
	public void addTestrayCaseTestrayComponent(long testrayCaseId,
		long testrayComponentId) {
		_testrayComponentLocalService.addTestrayCaseTestrayComponent(testrayCaseId,
			testrayComponentId);
	}

	@Override
	public void addTestrayCaseTestrayComponents(long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestrayComponent> testrayComponents) {
		_testrayComponentLocalService.addTestrayCaseTestrayComponents(testrayCaseId,
			testrayComponents);
	}

	@Override
	public void addTestrayCaseTestrayComponents(long testrayCaseId,
		long[] testrayComponentIds) {
		_testrayComponentLocalService.addTestrayCaseTestrayComponents(testrayCaseId,
			testrayComponentIds);
	}

	@Override
	public void clearTestrayCaseTestrayComponents(long testrayCaseId) {
		_testrayComponentLocalService.clearTestrayCaseTestrayComponents(testrayCaseId);
	}

	@Override
	public void deleteTestrayCaseTestrayComponent(long testrayCaseId,
		com.liferay.osb.testray.model.TestrayComponent testrayComponent) {
		_testrayComponentLocalService.deleteTestrayCaseTestrayComponent(testrayCaseId,
			testrayComponent);
	}

	@Override
	public void deleteTestrayCaseTestrayComponent(long testrayCaseId,
		long testrayComponentId) {
		_testrayComponentLocalService.deleteTestrayCaseTestrayComponent(testrayCaseId,
			testrayComponentId);
	}

	@Override
	public void deleteTestrayCaseTestrayComponents(long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestrayComponent> testrayComponents) {
		_testrayComponentLocalService.deleteTestrayCaseTestrayComponents(testrayCaseId,
			testrayComponents);
	}

	@Override
	public void deleteTestrayCaseTestrayComponents(long testrayCaseId,
		long[] testrayComponentIds) {
		_testrayComponentLocalService.deleteTestrayCaseTestrayComponents(testrayCaseId,
			testrayComponentIds);
	}

	@Override
	public void setTestrayCaseTestrayComponents(long testrayCaseId,
		long[] testrayComponentIds) {
		_testrayComponentLocalService.setTestrayCaseTestrayComponents(testrayCaseId,
			testrayComponentIds);
	}

	@Override
	public TestrayComponentLocalService getWrappedService() {
		return _testrayComponentLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayComponentLocalService testrayComponentLocalService) {
		_testrayComponentLocalService = testrayComponentLocalService;
	}

	private TestrayComponentLocalService _testrayComponentLocalService;
}