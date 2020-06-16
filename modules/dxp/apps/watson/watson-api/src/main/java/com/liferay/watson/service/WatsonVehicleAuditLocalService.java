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
import com.liferay.watson.model.WatsonVehicleAudit;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for WatsonVehicleAudit. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Steven Smith
 * @see WatsonVehicleAuditLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface WatsonVehicleAuditLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonVehicleAuditLocalServiceUtil} to access the watson vehicle audit local service. Add custom service methods to <code>com.liferay.watson.service.impl.WatsonVehicleAuditLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the watson vehicle audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicleAudit the watson vehicle audit
	 * @return the watson vehicle audit that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public WatsonVehicleAudit addWatsonVehicleAudit(
		WatsonVehicleAudit watsonVehicleAudit);

	/**
	 * Creates a new watson vehicle audit with the primary key. Does not add the watson vehicle audit to the database.
	 *
	 * @param watsonVehicleAuditId the primary key for the new watson vehicle audit
	 * @return the new watson vehicle audit
	 */
	@Transactional(enabled = false)
	public WatsonVehicleAudit createWatsonVehicleAudit(
		long watsonVehicleAuditId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	 * Deletes the watson vehicle audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicleAuditId the primary key of the watson vehicle audit
	 * @return the watson vehicle audit that was removed
	 * @throws PortalException if a watson vehicle audit with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public WatsonVehicleAudit deleteWatsonVehicleAudit(
			long watsonVehicleAuditId)
		throws PortalException;

	/**
	 * Deletes the watson vehicle audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicleAudit the watson vehicle audit
	 * @return the watson vehicle audit that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public WatsonVehicleAudit deleteWatsonVehicleAudit(
		WatsonVehicleAudit watsonVehicleAudit);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonVehicleAuditModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonVehicleAuditModelImpl</code>.
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
	public WatsonVehicleAudit fetchWatsonVehicleAudit(
		long watsonVehicleAuditId);

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
	 * Returns the watson vehicle audit with the primary key.
	 *
	 * @param watsonVehicleAuditId the primary key of the watson vehicle audit
	 * @return the watson vehicle audit
	 * @throws PortalException if a watson vehicle audit with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WatsonVehicleAudit getWatsonVehicleAudit(long watsonVehicleAuditId)
		throws PortalException;

	/**
	 * Returns a range of all the watson vehicle audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonVehicleAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson vehicle audits
	 * @param end the upper bound of the range of watson vehicle audits (not inclusive)
	 * @return the range of watson vehicle audits
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WatsonVehicleAudit> getWatsonVehicleAudits(int start, int end);

	/**
	 * Returns the number of watson vehicle audits.
	 *
	 * @return the number of watson vehicle audits
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWatsonVehicleAuditsCount();

	/**
	 * Updates the watson vehicle audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicleAudit the watson vehicle audit
	 * @return the watson vehicle audit that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public WatsonVehicleAudit updateWatsonVehicleAudit(
		WatsonVehicleAudit watsonVehicleAudit);

}