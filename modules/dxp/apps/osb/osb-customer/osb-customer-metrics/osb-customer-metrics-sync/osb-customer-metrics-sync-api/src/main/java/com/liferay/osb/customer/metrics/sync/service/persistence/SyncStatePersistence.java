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

package com.liferay.osb.customer.metrics.sync.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.metrics.sync.exception.NoSuchSyncStateException;
import com.liferay.osb.customer.metrics.sync.model.SyncState;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the sync state service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.customer.metrics.sync.service.persistence.impl.SyncStatePersistenceImpl
 * @see SyncStateUtil
 * @generated
 */
@ProviderType
public interface SyncStatePersistence extends BasePersistence<SyncState> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SyncStateUtil} to access the sync state persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, SyncState> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	* Returns the sync state where modelName = &#63; or throws a {@link NoSuchSyncStateException} if it could not be found.
	*
	* @param modelName the model name
	* @return the matching sync state
	* @throws NoSuchSyncStateException if a matching sync state could not be found
	*/
	public SyncState findByModelName(String modelName)
		throws NoSuchSyncStateException;

	/**
	* Returns the sync state where modelName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param modelName the model name
	* @return the matching sync state, or <code>null</code> if a matching sync state could not be found
	*/
	public SyncState fetchByModelName(String modelName);

	/**
	* Returns the sync state where modelName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param modelName the model name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching sync state, or <code>null</code> if a matching sync state could not be found
	*/
	public SyncState fetchByModelName(String modelName,
		boolean retrieveFromCache);

	/**
	* Removes the sync state where modelName = &#63; from the database.
	*
	* @param modelName the model name
	* @return the sync state that was removed
	*/
	public SyncState removeByModelName(String modelName)
		throws NoSuchSyncStateException;

	/**
	* Returns the number of sync states where modelName = &#63;.
	*
	* @param modelName the model name
	* @return the number of matching sync states
	*/
	public int countByModelName(String modelName);

	/**
	* Caches the sync state in the entity cache if it is enabled.
	*
	* @param syncState the sync state
	*/
	public void cacheResult(SyncState syncState);

	/**
	* Caches the sync states in the entity cache if it is enabled.
	*
	* @param syncStates the sync states
	*/
	public void cacheResult(java.util.List<SyncState> syncStates);

	/**
	* Creates a new sync state with the primary key. Does not add the sync state to the database.
	*
	* @param syncStateId the primary key for the new sync state
	* @return the new sync state
	*/
	public SyncState create(long syncStateId);

	/**
	* Removes the sync state with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncStateId the primary key of the sync state
	* @return the sync state that was removed
	* @throws NoSuchSyncStateException if a sync state with the primary key could not be found
	*/
	public SyncState remove(long syncStateId) throws NoSuchSyncStateException;

	public SyncState updateImpl(SyncState syncState);

	/**
	* Returns the sync state with the primary key or throws a {@link NoSuchSyncStateException} if it could not be found.
	*
	* @param syncStateId the primary key of the sync state
	* @return the sync state
	* @throws NoSuchSyncStateException if a sync state with the primary key could not be found
	*/
	public SyncState findByPrimaryKey(long syncStateId)
		throws NoSuchSyncStateException;

	/**
	* Returns the sync state with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param syncStateId the primary key of the sync state
	* @return the sync state, or <code>null</code> if a sync state with the primary key could not be found
	*/
	public SyncState fetchByPrimaryKey(long syncStateId);

	/**
	* Returns all the sync states.
	*
	* @return the sync states
	*/
	public java.util.List<SyncState> findAll();

	/**
	* Returns a range of all the sync states.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync states
	* @param end the upper bound of the range of sync states (not inclusive)
	* @return the range of sync states
	*/
	public java.util.List<SyncState> findAll(int start, int end);

	/**
	* Returns an ordered range of all the sync states.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync states
	* @param end the upper bound of the range of sync states (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of sync states
	*/
	public java.util.List<SyncState> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncState> orderByComparator);

	/**
	* Returns an ordered range of all the sync states.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SyncStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync states
	* @param end the upper bound of the range of sync states (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of sync states
	*/
	public java.util.List<SyncState> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SyncState> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the sync states from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of sync states.
	*
	* @return the number of sync states
	*/
	public int countAll();
}