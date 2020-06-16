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

import com.liferay.osb.testray.model.TestrayRequirement;
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
 * Provides the local service interface for TestrayRequirement. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Ethan Bustad
 * @see TestrayRequirementLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface TestrayRequirementLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TestrayRequirementLocalServiceUtil} to access the testray requirement local service. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestrayRequirementLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void addTestrayCaseTestrayRequirement(
		long testrayCaseId, long testrayRequirementId);

	public void addTestrayCaseTestrayRequirement(
		long testrayCaseId, TestrayRequirement testrayRequirement);

	public void addTestrayCaseTestrayRequirements(
		long testrayCaseId, List<TestrayRequirement> testrayRequirements);

	public void addTestrayCaseTestrayRequirements(
		long testrayCaseId, long[] testrayRequirementIds);

	/**
	 * Adds the testray requirement to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirement the testray requirement
	 * @return the testray requirement that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public TestrayRequirement addTestrayRequirement(
		TestrayRequirement testrayRequirement);

	public void clearTestrayCaseTestrayRequirements(long testrayCaseId);

	/**
	 * Creates a new testray requirement with the primary key. Does not add the testray requirement to the database.
	 *
	 * @param testrayRequirementId the primary key for the new testray requirement
	 * @return the new testray requirement
	 */
	@Transactional(enabled = false)
	public TestrayRequirement createTestrayRequirement(
		long testrayRequirementId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public void deleteTestrayCaseTestrayRequirement(
		long testrayCaseId, long testrayRequirementId);

	public void deleteTestrayCaseTestrayRequirement(
		long testrayCaseId, TestrayRequirement testrayRequirement);

	public void deleteTestrayCaseTestrayRequirements(
		long testrayCaseId, List<TestrayRequirement> testrayRequirements);

	public void deleteTestrayCaseTestrayRequirements(
		long testrayCaseId, long[] testrayRequirementIds);

	/**
	 * Deletes the testray requirement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement that was removed
	 * @throws PortalException if a testray requirement with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public TestrayRequirement deleteTestrayRequirement(
			long testrayRequirementId)
		throws PortalException;

	/**
	 * Deletes the testray requirement from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirement the testray requirement
	 * @return the testray requirement that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public TestrayRequirement deleteTestrayRequirement(
		TestrayRequirement testrayRequirement);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRequirementModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRequirementModelImpl</code>.
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
	public TestrayRequirement fetchTestrayRequirement(
		long testrayRequirementId);

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
	 * Returns the testrayCaseIds of the testray cases associated with the testray requirement.
	 *
	 * @param testrayRequirementId the testrayRequirementId of the testray requirement
	 * @return long[] the testrayCaseIds of testray cases associated with the testray requirement
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getTestrayCasePrimaryKeys(long testrayRequirementId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayRequirement> getTestrayCaseTestrayRequirements(
		long testrayCaseId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayRequirement> getTestrayCaseTestrayRequirements(
		long testrayCaseId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayRequirement> getTestrayCaseTestrayRequirements(
		long testrayCaseId, int start, int end,
		OrderByComparator<TestrayRequirement> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestrayCaseTestrayRequirementsCount(long testrayCaseId);

	/**
	 * Returns the testray requirement with the primary key.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement
	 * @throws PortalException if a testray requirement with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public TestrayRequirement getTestrayRequirement(long testrayRequirementId)
		throws PortalException;

	/**
	 * Returns a range of all the testray requirements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRequirementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray requirements
	 * @param end the upper bound of the range of testray requirements (not inclusive)
	 * @return the range of testray requirements
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TestrayRequirement> getTestrayRequirements(int start, int end);

	/**
	 * Returns the number of testray requirements.
	 *
	 * @return the number of testray requirements
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTestrayRequirementsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayCaseTestrayRequirement(
		long testrayCaseId, long testrayRequirementId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasTestrayCaseTestrayRequirements(long testrayCaseId);

	public void setTestrayCaseTestrayRequirements(
		long testrayCaseId, long[] testrayRequirementIds);

	/**
	 * Updates the testray requirement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirement the testray requirement
	 * @return the testray requirement that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public TestrayRequirement updateTestrayRequirement(
		TestrayRequirement testrayRequirement);

}