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
 * Provides a wrapper for {@link TestrayArchiveLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayArchiveLocalService
 * @generated
 */
@ProviderType
public class TestrayArchiveLocalServiceWrapper
	implements TestrayArchiveLocalService,
			   ServiceWrapper<TestrayArchiveLocalService> {

	public TestrayArchiveLocalServiceWrapper(
		TestrayArchiveLocalService testrayArchiveLocalService) {

		_testrayArchiveLocalService = testrayArchiveLocalService;
	}

	/**
	 * Adds the testray archive to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayArchive the testray archive
	 * @return the testray archive that was added
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayArchive addTestrayArchive(
		com.liferay.osb.testray.model.TestrayArchive testrayArchive) {

		return _testrayArchiveLocalService.addTestrayArchive(testrayArchive);
	}

	/**
	 * Creates a new testray archive with the primary key. Does not add the testray archive to the database.
	 *
	 * @param testrayArchiveId the primary key for the new testray archive
	 * @return the new testray archive
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayArchive createTestrayArchive(
		long testrayArchiveId) {

		return _testrayArchiveLocalService.createTestrayArchive(
			testrayArchiveId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayArchiveLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the testray archive with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayArchiveId the primary key of the testray archive
	 * @return the testray archive that was removed
	 * @throws PortalException if a testray archive with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayArchive deleteTestrayArchive(
			long testrayArchiveId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayArchiveLocalService.deleteTestrayArchive(
			testrayArchiveId);
	}

	/**
	 * Deletes the testray archive from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayArchive the testray archive
	 * @return the testray archive that was removed
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayArchive deleteTestrayArchive(
		com.liferay.osb.testray.model.TestrayArchive testrayArchive) {

		return _testrayArchiveLocalService.deleteTestrayArchive(testrayArchive);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayArchiveLocalService.dynamicQuery();
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

		return _testrayArchiveLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayArchiveModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayArchiveLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayArchiveModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayArchiveLocalService.dynamicQuery(
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

		return _testrayArchiveLocalService.dynamicQueryCount(dynamicQuery);
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

		return _testrayArchiveLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayArchive fetchTestrayArchive(
		long testrayArchiveId) {

		return _testrayArchiveLocalService.fetchTestrayArchive(
			testrayArchiveId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _testrayArchiveLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.osb.testray.model.TestrayArchiveCompressedDataBlobModel
		getCompressedDataBlobModel(java.io.Serializable primaryKey) {

		return _testrayArchiveLocalService.getCompressedDataBlobModel(
			primaryKey);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _testrayArchiveLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testrayArchiveLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayArchiveLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testray archive with the primary key.
	 *
	 * @param testrayArchiveId the primary key of the testray archive
	 * @return the testray archive
	 * @throws PortalException if a testray archive with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayArchive getTestrayArchive(
			long testrayArchiveId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayArchiveLocalService.getTestrayArchive(testrayArchiveId);
	}

	/**
	 * Returns a range of all the testray archives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayArchiveModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray archives
	 * @param end the upper bound of the range of testray archives (not inclusive)
	 * @return the range of testray archives
	 */
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayArchive>
		getTestrayArchives(int start, int end) {

		return _testrayArchiveLocalService.getTestrayArchives(start, end);
	}

	/**
	 * Returns the number of testray archives.
	 *
	 * @return the number of testray archives
	 */
	@Override
	public int getTestrayArchivesCount() {
		return _testrayArchiveLocalService.getTestrayArchivesCount();
	}

	/**
	 * Updates the testray archive in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayArchive the testray archive
	 * @return the testray archive that was updated
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayArchive updateTestrayArchive(
		com.liferay.osb.testray.model.TestrayArchive testrayArchive) {

		return _testrayArchiveLocalService.updateTestrayArchive(testrayArchive);
	}

	@Override
	public TestrayArchiveLocalService getWrappedService() {
		return _testrayArchiveLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayArchiveLocalService testrayArchiveLocalService) {

		_testrayArchiveLocalService = testrayArchiveLocalService;
	}

	private TestrayArchiveLocalService _testrayArchiveLocalService;

}