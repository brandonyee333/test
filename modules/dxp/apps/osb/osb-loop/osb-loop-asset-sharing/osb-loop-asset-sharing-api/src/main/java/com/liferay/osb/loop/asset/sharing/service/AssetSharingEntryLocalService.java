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

package com.liferay.osb.loop.asset.sharing.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.asset.sharing.model.AssetSharingEntry;
import com.liferay.osb.loop.asset.sharing.service.persistence.AssetSharingEntryPK;
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
import java.util.Map;
import java.util.Set;

/**
 * Provides the local service interface for AssetSharingEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see AssetSharingEntryLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface AssetSharingEntryLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetSharingEntryLocalServiceUtil} to access the asset sharing entry local service. Add custom service methods to <code>com.liferay.osb.loop.asset.sharing.service.impl.AssetSharingEntryLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public void addAssetSharingEntries(
		long classNameId, long classPK,
		Map<Long, Set<Long>> sharedToClassPKsMap);

	/**
	 * Adds the asset sharing entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetSharingEntry the asset sharing entry
	 * @return the asset sharing entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public AssetSharingEntry addAssetSharingEntry(
		AssetSharingEntry assetSharingEntry);

	public void addAssetSharingEntry(
		long classNameId, long classPK, long sharedToClassNameId,
		long sharedToClassPK);

	/**
	 * Creates a new asset sharing entry with the primary key. Does not add the asset sharing entry to the database.
	 *
	 * @param assetSharingEntryPK the primary key for the new asset sharing entry
	 * @return the new asset sharing entry
	 */
	@Transactional(enabled = false)
	public AssetSharingEntry createAssetSharingEntry(
		AssetSharingEntryPK assetSharingEntryPK);

	public void deleteAssetSharingEntries(long classNameId, long classPK);

	/**
	 * Deletes the asset sharing entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetSharingEntry the asset sharing entry
	 * @return the asset sharing entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public AssetSharingEntry deleteAssetSharingEntry(
		AssetSharingEntry assetSharingEntry);

	/**
	 * Deletes the asset sharing entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetSharingEntryPK the primary key of the asset sharing entry
	 * @return the asset sharing entry that was removed
	 * @throws PortalException if a asset sharing entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public AssetSharingEntry deleteAssetSharingEntry(
			AssetSharingEntryPK assetSharingEntryPK)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public void deleteSharedToAssetSharingEntries(
		long sharedToClassNameId, long sharedToClassPK);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.asset.sharing.model.impl.AssetSharingEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.asset.sharing.model.impl.AssetSharingEntryModelImpl</code>.
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
	public AssetSharingEntry fetchAssetSharingEntry(
		AssetSharingEntryPK assetSharingEntryPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Returns a range of all the asset sharing entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.asset.sharing.model.impl.AssetSharingEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset sharing entries
	 * @param end the upper bound of the range of asset sharing entries (not inclusive)
	 * @return the range of asset sharing entries
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetSharingEntry> getAssetSharingEntries(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetSharingEntry> getAssetSharingEntries(
		long classNameId, long classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetSharingEntry> getAssetSharingEntries(
		long classNameId, long classPK, long sharedToClassNameId);

	/**
	 * Returns the number of asset sharing entries.
	 *
	 * @return the number of asset sharing entries
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetSharingEntriesCount();

	/**
	 * Returns the asset sharing entry with the primary key.
	 *
	 * @param assetSharingEntryPK the primary key of the asset sharing entry
	 * @return the asset sharing entry
	 * @throws PortalException if a asset sharing entry with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AssetSharingEntry getAssetSharingEntry(
			AssetSharingEntryPK assetSharingEntryPK)
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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetSharingEntry> getSharedToAssetSharingEntries(
		long sharedToClassNameId, long sharedToClassPK, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AssetSharingEntry> getSharedToAssetSharingEntries(
		long classNameId, long sharedToClassNameId, long sharedToClassPK,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSharedToAssetSharingEntriesCount(
		long sharedToClassNameId, long sharedToClassPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getSharedToAssetSharingEntriesCount(
		long classNameId, long sharedToClassNameId, long sharedToClassPK);

	/**
	 * Updates the asset sharing entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param assetSharingEntry the asset sharing entry
	 * @return the asset sharing entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public AssetSharingEntry updateAssetSharingEntry(
		AssetSharingEntry assetSharingEntry);

}