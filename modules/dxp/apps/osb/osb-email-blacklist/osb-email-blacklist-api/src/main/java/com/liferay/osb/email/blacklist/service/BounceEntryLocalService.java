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

package com.liferay.osb.email.blacklist.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.email.blacklist.model.BounceEntry;
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

import java.util.Date;
import java.util.List;

/**
 * Provides the local service interface for BounceEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Jamie Sammons
 * @see BounceEntryLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface BounceEntryLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BounceEntryLocalServiceUtil} to access the bounce entry local service. Add custom service methods to <code>com.liferay.osb.email.blacklist.service.impl.BounceEntryLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the bounce entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param bounceEntry the bounce entry
	 * @return the bounce entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public BounceEntry addBounceEntry(BounceEntry bounceEntry);

	public BounceEntry addBounceEntry(
			String emailAddress, Date bounceDate, String bounceType,
			String bounceSubtype)
		throws PortalException;

	/**
	 * Creates a new bounce entry with the primary key. Does not add the bounce entry to the database.
	 *
	 * @param bounceEntryId the primary key for the new bounce entry
	 * @return the new bounce entry
	 */
	@Transactional(enabled = false)
	public BounceEntry createBounceEntry(long bounceEntryId);

	/**
	 * Deletes the bounce entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bounceEntry the bounce entry
	 * @return the bounce entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public BounceEntry deleteBounceEntry(BounceEntry bounceEntry);

	/**
	 * Deletes the bounce entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bounceEntryId the primary key of the bounce entry
	 * @return the bounce entry that was removed
	 * @throws PortalException if a bounce entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public BounceEntry deleteBounceEntry(long bounceEntryId)
		throws PortalException;

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.email.blacklist.model.impl.BounceEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.email.blacklist.model.impl.BounceEntryModelImpl</code>.
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
	public BounceEntry fetchBounceEntry(long bounceEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BounceEntry> getBounceEntries(
		Date bounceDateLT, int start, int end,
		OrderByComparator<BounceEntry> obc);

	/**
	 * Returns a range of all the bounce entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.email.blacklist.model.impl.BounceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bounce entries
	 * @param end the upper bound of the range of bounce entries (not inclusive)
	 * @return the range of bounce entries
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<BounceEntry> getBounceEntries(int start, int end);

	/**
	 * Returns the number of bounce entries.
	 *
	 * @return the number of bounce entries
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getBounceEntriesCount();

	/**
	 * Returns the bounce entry with the primary key.
	 *
	 * @param bounceEntryId the primary key of the bounce entry
	 * @return the bounce entry
	 * @throws PortalException if a bounce entry with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BounceEntry getBounceEntry(long bounceEntryId)
		throws PortalException;

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
	 * Updates the bounce entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param bounceEntry the bounce entry
	 * @return the bounce entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public BounceEntry updateBounceEntry(BounceEntry bounceEntry);

}