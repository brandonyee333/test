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

package com.liferay.watson.login.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.watson.login.model.WatsonTokenAuthEntry;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for WatsonTokenAuthEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Steven Smith
 * @see WatsonTokenAuthEntryLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface WatsonTokenAuthEntryLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonTokenAuthEntryLocalServiceUtil} to access the watson token auth entry local service. Add custom service methods to <code>com.liferay.watson.login.service.impl.WatsonTokenAuthEntryLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public WatsonTokenAuthEntry addWatsonTokenAuthEntry(
		User user, String token, String loginIP);

	/**
	 * Adds the watson token auth entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonTokenAuthEntry the watson token auth entry
	 * @return the watson token auth entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public WatsonTokenAuthEntry addWatsonTokenAuthEntry(
		WatsonTokenAuthEntry watsonTokenAuthEntry);

	/**
	 * Creates a new watson token auth entry with the primary key. Does not add the watson token auth entry to the database.
	 *
	 * @param watsonTokenAuthEntryId the primary key for the new watson token auth entry
	 * @return the new watson token auth entry
	 */
	@Transactional(enabled = false)
	public WatsonTokenAuthEntry createWatsonTokenAuthEntry(
		long watsonTokenAuthEntryId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	 * Deletes the watson token auth entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonTokenAuthEntryId the primary key of the watson token auth entry
	 * @return the watson token auth entry that was removed
	 * @throws PortalException if a watson token auth entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public WatsonTokenAuthEntry deleteWatsonTokenAuthEntry(
			long watsonTokenAuthEntryId)
		throws PortalException;

	/**
	 * Deletes the watson token auth entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonTokenAuthEntry the watson token auth entry
	 * @return the watson token auth entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public WatsonTokenAuthEntry deleteWatsonTokenAuthEntry(
		WatsonTokenAuthEntry watsonTokenAuthEntry);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.login.model.impl.WatsonTokenAuthEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.login.model.impl.WatsonTokenAuthEntryModelImpl</code>.
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

	public void extendWatsonTokenAuthEntry(User user, String loginIP);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WatsonTokenAuthEntry fetchWatsonTokenAuthEntry(
		long watsonTokenAuthEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WatsonTokenAuthEntry fetchWatsonTokenAuthEntry(User user);

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
	 * Returns a range of all the watson token auth entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.login.model.impl.WatsonTokenAuthEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson token auth entries
	 * @param end the upper bound of the range of watson token auth entries (not inclusive)
	 * @return the range of watson token auth entries
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WatsonTokenAuthEntry> getWatsonTokenAuthEntries(
		int start, int end);

	/**
	 * Returns the number of watson token auth entries.
	 *
	 * @return the number of watson token auth entries
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWatsonTokenAuthEntriesCount();

	/**
	 * Returns the watson token auth entry with the primary key.
	 *
	 * @param watsonTokenAuthEntryId the primary key of the watson token auth entry
	 * @return the watson token auth entry
	 * @throws PortalException if a watson token auth entry with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WatsonTokenAuthEntry getWatsonTokenAuthEntry(
			long watsonTokenAuthEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasAuthenticatedSession(User user);

	public void invalidateWatsonAuthToken(User user);

	/**
	 * Updates the watson token auth entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonTokenAuthEntry the watson token auth entry
	 * @return the watson token auth entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public WatsonTokenAuthEntry updateWatsonTokenAuthEntry(
		WatsonTokenAuthEntry watsonTokenAuthEntry);

	public int updateWatsonTokenAuthEntryStatus(User user, String loginIP);

	public int updateWatsonTokenAuthEntryStatus(
		User user, String token, String loginIP);

}