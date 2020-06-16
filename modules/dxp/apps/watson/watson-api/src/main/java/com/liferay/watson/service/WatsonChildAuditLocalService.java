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

package com.liferay.watson.service;

import aQute.bnd.annotation.ProviderType;

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
import com.liferay.watson.model.WatsonChildAudit;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for WatsonChildAudit. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Steven Smith
 * @see WatsonChildAuditLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface WatsonChildAuditLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonChildAuditLocalServiceUtil} to access the watson child audit local service. Add custom service methods to <code>com.liferay.watson.service.impl.WatsonChildAuditLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the watson child audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChildAudit the watson child audit
	 * @return the watson child audit that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public WatsonChildAudit addWatsonChildAudit(
		WatsonChildAudit watsonChildAudit);

	/**
	 * Creates a new watson child audit with the primary key. Does not add the watson child audit to the database.
	 *
	 * @param watsonChildAuditId the primary key for the new watson child audit
	 * @return the new watson child audit
	 */
	@Transactional(enabled = false)
	public WatsonChildAudit createWatsonChildAudit(long watsonChildAuditId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	 * Deletes the watson child audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChildAuditId the primary key of the watson child audit
	 * @return the watson child audit that was removed
	 * @throws PortalException if a watson child audit with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public WatsonChildAudit deleteWatsonChildAudit(long watsonChildAuditId)
		throws PortalException;

	/**
	 * Deletes the watson child audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChildAudit the watson child audit
	 * @return the watson child audit that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public WatsonChildAudit deleteWatsonChildAudit(
		WatsonChildAudit watsonChildAudit);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonChildAuditModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonChildAuditModelImpl</code>.
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
	public WatsonChildAudit fetchWatsonChildAudit(long watsonChildAuditId);

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
	 * Returns the watson child audit with the primary key.
	 *
	 * @param watsonChildAuditId the primary key of the watson child audit
	 * @return the watson child audit
	 * @throws PortalException if a watson child audit with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WatsonChildAudit getWatsonChildAudit(long watsonChildAuditId)
		throws PortalException;

	/**
	 * Returns a range of all the watson child audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonChildAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson child audits
	 * @param end the upper bound of the range of watson child audits (not inclusive)
	 * @return the range of watson child audits
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WatsonChildAudit> getWatsonChildAudits(int start, int end);

	/**
	 * Returns the number of watson child audits.
	 *
	 * @return the number of watson child audits
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWatsonChildAuditsCount();

	/**
	 * Updates the watson child audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChildAudit the watson child audit
	 * @return the watson child audit that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public WatsonChildAudit updateWatsonChildAudit(
		WatsonChildAudit watsonChildAudit);

}