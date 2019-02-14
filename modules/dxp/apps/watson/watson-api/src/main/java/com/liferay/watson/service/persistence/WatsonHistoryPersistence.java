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

package com.liferay.watson.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.watson.exception.NoSuchHistoryException;
import com.liferay.watson.model.WatsonHistory;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonHistoryUtil
 * @generated
 */
@ProviderType
public interface WatsonHistoryPersistence extends BasePersistence<WatsonHistory> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonHistoryUtil} to access the watson history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonHistory> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	* Caches the watson history in the entity cache if it is enabled.
	*
	* @param watsonHistory the watson history
	*/
	public void cacheResult(WatsonHistory watsonHistory);

	/**
	* Caches the watson histories in the entity cache if it is enabled.
	*
	* @param watsonHistories the watson histories
	*/
	public void cacheResult(java.util.List<WatsonHistory> watsonHistories);

	/**
	* Creates a new watson history with the primary key. Does not add the watson history to the database.
	*
	* @param watsonHistoryId the primary key for the new watson history
	* @return the new watson history
	*/
	public WatsonHistory create(long watsonHistoryId);

	/**
	* Removes the watson history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonHistoryId the primary key of the watson history
	* @return the watson history that was removed
	* @throws NoSuchHistoryException if a watson history with the primary key could not be found
	*/
	public WatsonHistory remove(long watsonHistoryId)
		throws NoSuchHistoryException;

	public WatsonHistory updateImpl(WatsonHistory watsonHistory);

	/**
	* Returns the watson history with the primary key or throws a <code>NoSuchHistoryException</code> if it could not be found.
	*
	* @param watsonHistoryId the primary key of the watson history
	* @return the watson history
	* @throws NoSuchHistoryException if a watson history with the primary key could not be found
	*/
	public WatsonHistory findByPrimaryKey(long watsonHistoryId)
		throws NoSuchHistoryException;

	/**
	* Returns the watson history with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonHistoryId the primary key of the watson history
	* @return the watson history, or <code>null</code> if a watson history with the primary key could not be found
	*/
	public WatsonHistory fetchByPrimaryKey(long watsonHistoryId);

	/**
	* Returns all the watson histories.
	*
	* @return the watson histories
	*/
	public java.util.List<WatsonHistory> findAll();

	/**
	* Returns a range of all the watson histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonHistoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson histories
	* @param end the upper bound of the range of watson histories (not inclusive)
	* @return the range of watson histories
	*/
	public java.util.List<WatsonHistory> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonHistoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson histories
	* @param end the upper bound of the range of watson histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson histories
	*/
	public java.util.List<WatsonHistory> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonHistory> orderByComparator);

	/**
	* Returns an ordered range of all the watson histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonHistoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson histories
	* @param end the upper bound of the range of watson histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson histories
	*/
	public java.util.List<WatsonHistory> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonHistory> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson histories from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson histories.
	*
	* @return the number of watson histories
	*/
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();
}