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

import com.liferay.osb.testray.model.TestraySuite;
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
 * Provides the local service interface for TestraySuite. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Ethan Bustad
 * @see TestraySuiteLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface TestraySuiteLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestraySuiteLocalServiceUtil} to access the testray suite local service. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestraySuiteLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void addTestrayCaseTestraySuite(
		long testrayCaseId, long testraySuiteId);

	public void addTestrayCaseTestraySuite(
		long testrayCaseId, TestraySuite testraySuite);

	public void addTestrayCaseTestraySuites(
		long testrayCaseId, List<TestraySuite> testraySuites);

	public void addTestrayCaseTestraySuites(
		long testrayCaseId, long[] testraySuiteIds);

	/**
	 * Adds the testray suite to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySuite the testray suite
	 * @return the testray suite that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public TestraySuite addTestraySuite(TestraySuite testraySuite);

	public void clearTestrayCaseTestraySuites(long testrayCaseId);

	/**
	 * Creates a new testray suite with the primary key. Does not add the testray suite to the database.
	 *
	 * @param testraySuiteId the primary key for the new testray suite
	 * @return the new testray suite
	 */
	@Transactional(enabled = false)
	public TestraySuite createTestraySuite(long testraySuiteId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public void deleteTestrayCaseTestraySuite(
		long testrayCaseId, long testraySuiteId);

	public void deleteTestrayCaseTestraySuite(
		long testrayCaseId, TestraySuite testraySuite);

	public void deleteTestrayCaseTestraySuites(
		long testrayCaseId, List<TestraySuite> testraySuites);

	public void deleteTestrayCaseTestraySuites(
		long testrayCaseId, long[] testraySuiteIds);

	/**
	 * Deletes the testray suite with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySuiteId the primary key of the testray suite
	 * @return the testray suite that was removed
	 * @throws PortalException if a testray suite with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public TestraySuite deleteTestraySuite(long testraySuiteId)
		throws PortalException;

	/**
	 * Deletes the testray suite from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySuite the testray suite
	 * @return the testray suite that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public TestraySuite deleteTestraySuite(TestraySuite testraySuite);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestraySuiteModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestraySuiteModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

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
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TestraySuite fetchTestraySuite(long testraySuiteId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Returns the testrayCaseIds of the testray cases associated with the testray suite.
	 *
	 * @param testraySuiteId the testraySuiteId of the testray suite
	 * @return long[] the testrayCaseIds of testray cases associated with the testray suite
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTestrayCasePrimaryKeys(long testraySuiteId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestraySuite> getTestrayCaseTestraySuites(long testrayCaseId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestraySuite> getTestrayCaseTestraySuites(
		long testrayCaseId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestraySuite> getTestrayCaseTestraySuites(
		long testrayCaseId, int start, int end,
		OrderByComparator<TestraySuite> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestrayCaseTestraySuitesCount(long testrayCaseId);

	/**
	 * Returns the testray suite with the primary key.
	 *
	 * @param testraySuiteId the primary key of the testray suite
	 * @return the testray suite
	 * @throws PortalException if a testray suite with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TestraySuite getTestraySuite(long testraySuiteId)
		throws PortalException;

	/**
	 * Returns a range of all the testray suites.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestraySuiteModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray suites
	 * @param end the upper bound of the range of testray suites (not inclusive)
	 * @return the range of testray suites
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestraySuite> getTestraySuites(int start, int end);

	/**
	 * Returns the number of testray suites.
	 *
	 * @return the number of testray suites
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestraySuitesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayCaseTestraySuite(
		long testrayCaseId, long testraySuiteId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayCaseTestraySuites(long testrayCaseId);

	public void setTestrayCaseTestraySuites(
		long testrayCaseId, long[] testraySuiteIds);

	/**
	 * Updates the testray suite in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testraySuite the testray suite
	 * @return the testray suite that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public TestraySuite updateTestraySuite(TestraySuite testraySuite);

}