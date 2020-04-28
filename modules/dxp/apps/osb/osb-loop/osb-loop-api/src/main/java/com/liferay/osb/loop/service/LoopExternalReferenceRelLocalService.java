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

package com.liferay.osb.loop.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.model.LoopExternalReferenceRel;
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
 * Provides the local service interface for LoopExternalReferenceRel. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Ethan Bustad
 * @see LoopExternalReferenceRelLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface LoopExternalReferenceRelLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopExternalReferenceRelLocalServiceUtil} to access the loop external reference rel local service. Add custom service methods to <code>com.liferay.osb.loop.service.impl.LoopExternalReferenceRelLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the loop external reference rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopExternalReferenceRel the loop external reference rel
	 * @return the loop external reference rel that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public LoopExternalReferenceRel addLoopExternalReferenceRel(
		LoopExternalReferenceRel loopExternalReferenceRel);

	/**
	 * Creates a new loop external reference rel with the primary key. Does not add the loop external reference rel to the database.
	 *
	 * @param loopExternalReferenceRelId the primary key for the new loop external reference rel
	 * @return the new loop external reference rel
	 */
	@Transactional(enabled = false)
	public LoopExternalReferenceRel createLoopExternalReferenceRel(
		long loopExternalReferenceRelId);

	/**
	 * Deletes the loop external reference rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopExternalReferenceRelId the primary key of the loop external reference rel
	 * @return the loop external reference rel that was removed
	 * @throws PortalException if a loop external reference rel with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public LoopExternalReferenceRel deleteLoopExternalReferenceRel(
			long loopExternalReferenceRelId)
		throws PortalException;

	/**
	 * Deletes the loop external reference rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopExternalReferenceRel the loop external reference rel
	 * @return the loop external reference rel that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public LoopExternalReferenceRel deleteLoopExternalReferenceRel(
		LoopExternalReferenceRel loopExternalReferenceRel);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopExternalReferenceRelModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopExternalReferenceRelModelImpl</code>.
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
	public LoopExternalReferenceRel fetchLoopExternalReferenceRel(
		long loopExternalReferenceRelId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the loop external reference rel with the primary key.
	 *
	 * @param loopExternalReferenceRelId the primary key of the loop external reference rel
	 * @return the loop external reference rel
	 * @throws PortalException if a loop external reference rel with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LoopExternalReferenceRel getLoopExternalReferenceRel(
			long loopExternalReferenceRelId)
		throws PortalException;

	/**
	 * Returns a range of all the loop external reference rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopExternalReferenceRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop external reference rels
	 * @param end the upper bound of the range of loop external reference rels (not inclusive)
	 * @return the range of loop external reference rels
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LoopExternalReferenceRel> getLoopExternalReferenceRels(
		int start, int end);

	/**
	 * Returns the number of loop external reference rels.
	 *
	 * @return the number of loop external reference rels
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLoopExternalReferenceRelsCount();

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
	 * Updates the loop external reference rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param loopExternalReferenceRel the loop external reference rel
	 * @return the loop external reference rel that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public LoopExternalReferenceRel updateLoopExternalReferenceRel(
		LoopExternalReferenceRel loopExternalReferenceRel);

}