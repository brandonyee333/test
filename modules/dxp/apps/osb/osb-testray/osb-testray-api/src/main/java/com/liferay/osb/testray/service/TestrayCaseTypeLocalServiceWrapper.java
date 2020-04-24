/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.testray.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TestrayCaseTypeLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayCaseTypeLocalService
 * @generated
 */
@ProviderType
public class TestrayCaseTypeLocalServiceWrapper
	implements TestrayCaseTypeLocalService,
			   ServiceWrapper<TestrayCaseTypeLocalService> {

	public TestrayCaseTypeLocalServiceWrapper(
		TestrayCaseTypeLocalService testrayCaseTypeLocalService) {

		_testrayCaseTypeLocalService = testrayCaseTypeLocalService;
	}

	/**
	 * Adds the testray case type to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseType the testray case type
	 * @return the testray case type that was added
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseType addTestrayCaseType(
		com.liferay.osb.testray.model.TestrayCaseType testrayCaseType) {

		return _testrayCaseTypeLocalService.addTestrayCaseType(testrayCaseType);
	}

	@Override
	public void addTestrayTaskTestrayCaseType(
		long testrayTaskId, long testrayCaseTypeId) {

		_testrayCaseTypeLocalService.addTestrayTaskTestrayCaseType(
			testrayTaskId, testrayCaseTypeId);
	}

	@Override
	public void addTestrayTaskTestrayCaseType(
		long testrayTaskId,
		com.liferay.osb.testray.model.TestrayCaseType testrayCaseType) {

		_testrayCaseTypeLocalService.addTestrayTaskTestrayCaseType(
			testrayTaskId, testrayCaseType);
	}

	@Override
	public void addTestrayTaskTestrayCaseTypes(
		long testrayTaskId,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseType>
			testrayCaseTypes) {

		_testrayCaseTypeLocalService.addTestrayTaskTestrayCaseTypes(
			testrayTaskId, testrayCaseTypes);
	}

	@Override
	public void addTestrayTaskTestrayCaseTypes(
		long testrayTaskId, long[] testrayCaseTypeIds) {

		_testrayCaseTypeLocalService.addTestrayTaskTestrayCaseTypes(
			testrayTaskId, testrayCaseTypeIds);
	}

	@Override
	public void clearTestrayTaskTestrayCaseTypes(long testrayTaskId) {
		_testrayCaseTypeLocalService.clearTestrayTaskTestrayCaseTypes(
			testrayTaskId);
	}

	/**
	 * Creates a new testray case type with the primary key. Does not add the testray case type to the database.
	 *
	 * @param testrayCaseTypeId the primary key for the new testray case type
	 * @return the new testray case type
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseType createTestrayCaseType(
		long testrayCaseTypeId) {

		return _testrayCaseTypeLocalService.createTestrayCaseType(
			testrayCaseTypeId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseTypeLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the testray case type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseTypeId the primary key of the testray case type
	 * @return the testray case type that was removed
	 * @throws PortalException if a testray case type with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseType deleteTestrayCaseType(
			long testrayCaseTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseTypeLocalService.deleteTestrayCaseType(
			testrayCaseTypeId);
	}

	/**
	 * Deletes the testray case type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseType the testray case type
	 * @return the testray case type that was removed
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseType deleteTestrayCaseType(
		com.liferay.osb.testray.model.TestrayCaseType testrayCaseType) {

		return _testrayCaseTypeLocalService.deleteTestrayCaseType(
			testrayCaseType);
	}

	@Override
	public void deleteTestrayTaskTestrayCaseType(
		long testrayTaskId, long testrayCaseTypeId) {

		_testrayCaseTypeLocalService.deleteTestrayTaskTestrayCaseType(
			testrayTaskId, testrayCaseTypeId);
	}

	@Override
	public void deleteTestrayTaskTestrayCaseType(
		long testrayTaskId,
		com.liferay.osb.testray.model.TestrayCaseType testrayCaseType) {

		_testrayCaseTypeLocalService.deleteTestrayTaskTestrayCaseType(
			testrayTaskId, testrayCaseType);
	}

	@Override
	public void deleteTestrayTaskTestrayCaseTypes(
		long testrayTaskId,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseType>
			testrayCaseTypes) {

		_testrayCaseTypeLocalService.deleteTestrayTaskTestrayCaseTypes(
			testrayTaskId, testrayCaseTypes);
	}

	@Override
	public void deleteTestrayTaskTestrayCaseTypes(
		long testrayTaskId, long[] testrayCaseTypeIds) {

		_testrayCaseTypeLocalService.deleteTestrayTaskTestrayCaseTypes(
			testrayTaskId, testrayCaseTypeIds);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayCaseTypeLocalService.dynamicQuery();
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

		return _testrayCaseTypeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayCaseTypeLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayCaseTypeLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

		return _testrayCaseTypeLocalService.dynamicQueryCount(dynamicQuery);
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

		return _testrayCaseTypeLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayCaseType fetchTestrayCaseType(
		long testrayCaseTypeId) {

		return _testrayCaseTypeLocalService.fetchTestrayCaseType(
			testrayCaseTypeId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _testrayCaseTypeLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _testrayCaseTypeLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testrayCaseTypeLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseTypeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testray case type with the primary key.
	 *
	 * @param testrayCaseTypeId the primary key of the testray case type
	 * @return the testray case type
	 * @throws PortalException if a testray case type with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseType getTestrayCaseType(
			long testrayCaseTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseTypeLocalService.getTestrayCaseType(
			testrayCaseTypeId);
	}

	/**
	 * Returns a range of all the testray case types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case types
	 * @param end the upper bound of the range of testray case types (not inclusive)
	 * @return the range of testray case types
	 */
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseType>
		getTestrayCaseTypes(int start, int end) {

		return _testrayCaseTypeLocalService.getTestrayCaseTypes(start, end);
	}

	/**
	 * Returns the number of testray case types.
	 *
	 * @return the number of testray case types
	 */
	@Override
	public int getTestrayCaseTypesCount() {
		return _testrayCaseTypeLocalService.getTestrayCaseTypesCount();
	}

	/**
	 * Returns the testrayTaskIds of the testray tasks associated with the testray case type.
	 *
	 * @param testrayCaseTypeId the testrayCaseTypeId of the testray case type
	 * @return long[] the testrayTaskIds of testray tasks associated with the testray case type
	 */
	@Override
	public long[] getTestrayTaskPrimaryKeys(long testrayCaseTypeId) {
		return _testrayCaseTypeLocalService.getTestrayTaskPrimaryKeys(
			testrayCaseTypeId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseType>
		getTestrayTaskTestrayCaseTypes(long testrayTaskId) {

		return _testrayCaseTypeLocalService.getTestrayTaskTestrayCaseTypes(
			testrayTaskId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseType>
		getTestrayTaskTestrayCaseTypes(long testrayTaskId, int start, int end) {

		return _testrayCaseTypeLocalService.getTestrayTaskTestrayCaseTypes(
			testrayTaskId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseType>
		getTestrayTaskTestrayCaseTypes(
			long testrayTaskId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCaseType>
					orderByComparator) {

		return _testrayCaseTypeLocalService.getTestrayTaskTestrayCaseTypes(
			testrayTaskId, start, end, orderByComparator);
	}

	@Override
	public int getTestrayTaskTestrayCaseTypesCount(long testrayTaskId) {
		return _testrayCaseTypeLocalService.getTestrayTaskTestrayCaseTypesCount(
			testrayTaskId);
	}

	@Override
	public boolean hasTestrayTaskTestrayCaseType(
		long testrayTaskId, long testrayCaseTypeId) {

		return _testrayCaseTypeLocalService.hasTestrayTaskTestrayCaseType(
			testrayTaskId, testrayCaseTypeId);
	}

	@Override
	public boolean hasTestrayTaskTestrayCaseTypes(long testrayTaskId) {
		return _testrayCaseTypeLocalService.hasTestrayTaskTestrayCaseTypes(
			testrayTaskId);
	}

	@Override
	public void setTestrayTaskTestrayCaseTypes(
		long testrayTaskId, long[] testrayCaseTypeIds) {

		_testrayCaseTypeLocalService.setTestrayTaskTestrayCaseTypes(
			testrayTaskId, testrayCaseTypeIds);
	}

	/**
	 * Updates the testray case type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseType the testray case type
	 * @return the testray case type that was updated
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseType updateTestrayCaseType(
		com.liferay.osb.testray.model.TestrayCaseType testrayCaseType) {

		return _testrayCaseTypeLocalService.updateTestrayCaseType(
			testrayCaseType);
	}

	@Override
	public TestrayCaseTypeLocalService getWrappedService() {
		return _testrayCaseTypeLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayCaseTypeLocalService testrayCaseTypeLocalService) {

		_testrayCaseTypeLocalService = testrayCaseTypeLocalService;
	}

	private TestrayCaseTypeLocalService _testrayCaseTypeLocalService;

}