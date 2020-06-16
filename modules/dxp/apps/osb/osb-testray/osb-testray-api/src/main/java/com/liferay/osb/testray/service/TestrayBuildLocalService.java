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

import com.liferay.osb.testray.model.TestrayBuild;

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
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for TestrayBuild. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Ethan Bustad
 * @see TestrayBuildLocalServiceUtil
 * @see com.liferay.osb.testray.service.base.TestrayBuildLocalServiceBaseImpl
 * @see com.liferay.osb.testray.service.impl.TestrayBuildLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface TestrayBuildLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayBuildLocalServiceUtil} to access the testray build local service. Add custom service methods to {@link com.liferay.osb.testray.service.impl.TestrayBuildLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayCaseTestrayBuild(long testrayCaseId,
		long testrayBuildId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayCaseTestrayBuilds(long testrayCaseId);

	/**
	* Adds the testray build to the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuild the testray build
	* @return the testray build that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public TestrayBuild addTestrayBuild(TestrayBuild testrayBuild);

	/**
	* Creates a new testray build with the primary key. Does not add the testray build to the database.
	*
	* @param testrayBuildId the primary key for the new testray build
	* @return the new testray build
	*/
	public TestrayBuild createTestrayBuild(long testrayBuildId);

	/**
	* Deletes the testray build from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuild the testray build
	* @return the testray build that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public TestrayBuild deleteTestrayBuild(TestrayBuild testrayBuild);

	/**
	* Deletes the testray build with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuildId the primary key of the testray build
	* @return the testray build that was removed
	* @throws PortalException if a testray build with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public TestrayBuild deleteTestrayBuild(long testrayBuildId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TestrayBuild fetchTestrayBuild(long testrayBuildId);

	/**
	* Returns the testray build with the primary key.
	*
	* @param testrayBuildId the primary key of the testray build
	* @return the testray build
	* @throws PortalException if a testray build with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TestrayBuild getTestrayBuild(long testrayBuildId)
		throws PortalException;

	/**
	* Updates the testray build in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testrayBuild the testray build
	* @return the testray build that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public TestrayBuild updateTestrayBuild(TestrayBuild testrayBuild);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
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

	/**
	* Returns the number of testray builds.
	*
	* @return the number of testray builds
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestrayBuildsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestrayCaseTestrayBuildsCount(long testrayCaseId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayBuildModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayBuildModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns a range of all the testray builds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayBuildModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray builds
	* @param end the upper bound of the range of testray builds (not inclusive)
	* @return the range of testray builds
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayBuild> getTestrayBuilds(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayBuild> getTestrayCaseTestrayBuilds(long testrayCaseId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayBuild> getTestrayCaseTestrayBuilds(long testrayCaseId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayBuild> getTestrayCaseTestrayBuilds(long testrayCaseId,
		int start, int end, OrderByComparator<TestrayBuild> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* Returns the testrayCaseIds of the testray cases associated with the testray build.
	*
	* @param testrayBuildId the testrayBuildId of the testray build
	* @return long[] the testrayCaseIds of testray cases associated with the testray build
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTestrayCasePrimaryKeys(long testrayBuildId);

	public void addTestrayCaseTestrayBuild(long testrayCaseId,
		TestrayBuild testrayBuild);

	public void addTestrayCaseTestrayBuild(long testrayCaseId,
		long testrayBuildId);

	public void addTestrayCaseTestrayBuilds(long testrayCaseId,
		List<TestrayBuild> testrayBuilds);

	public void addTestrayCaseTestrayBuilds(long testrayCaseId,
		long[] testrayBuildIds);

	public void clearTestrayCaseTestrayBuilds(long testrayCaseId);

	public void deleteTestrayCaseTestrayBuild(long testrayCaseId,
		TestrayBuild testrayBuild);

	public void deleteTestrayCaseTestrayBuild(long testrayCaseId,
		long testrayBuildId);

	public void deleteTestrayCaseTestrayBuilds(long testrayCaseId,
		List<TestrayBuild> testrayBuilds);

	public void deleteTestrayCaseTestrayBuilds(long testrayCaseId,
		long[] testrayBuildIds);

	public void setTestrayCaseTestrayBuilds(long testrayCaseId,
		long[] testrayBuildIds);
}