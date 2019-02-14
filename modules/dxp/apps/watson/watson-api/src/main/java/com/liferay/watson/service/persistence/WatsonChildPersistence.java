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

import com.liferay.watson.exception.NoSuchChildException;
import com.liferay.watson.model.WatsonChild;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson child service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonChildUtil
 * @generated
 */
@ProviderType
public interface WatsonChildPersistence extends BasePersistence<WatsonChild> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonChildUtil} to access the watson child persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonChild> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	* Caches the watson child in the entity cache if it is enabled.
	*
	* @param watsonChild the watson child
	*/
	public void cacheResult(WatsonChild watsonChild);

	/**
	* Caches the watson childs in the entity cache if it is enabled.
	*
	* @param watsonChilds the watson childs
	*/
	public void cacheResult(java.util.List<WatsonChild> watsonChilds);

	/**
	* Creates a new watson child with the primary key. Does not add the watson child to the database.
	*
	* @param watsonChildId the primary key for the new watson child
	* @return the new watson child
	*/
	public WatsonChild create(long watsonChildId);

	/**
	* Removes the watson child with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonChildId the primary key of the watson child
	* @return the watson child that was removed
	* @throws NoSuchChildException if a watson child with the primary key could not be found
	*/
	public WatsonChild remove(long watsonChildId) throws NoSuchChildException;

	public WatsonChild updateImpl(WatsonChild watsonChild);

	/**
	* Returns the watson child with the primary key or throws a <code>NoSuchChildException</code> if it could not be found.
	*
	* @param watsonChildId the primary key of the watson child
	* @return the watson child
	* @throws NoSuchChildException if a watson child with the primary key could not be found
	*/
	public WatsonChild findByPrimaryKey(long watsonChildId)
		throws NoSuchChildException;

	/**
	* Returns the watson child with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonChildId the primary key of the watson child
	* @return the watson child, or <code>null</code> if a watson child with the primary key could not be found
	*/
	public WatsonChild fetchByPrimaryKey(long watsonChildId);

	/**
	* Returns all the watson childs.
	*
	* @return the watson childs
	*/
	public java.util.List<WatsonChild> findAll();

	/**
	* Returns a range of all the watson childs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonChildModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson childs
	* @param end the upper bound of the range of watson childs (not inclusive)
	* @return the range of watson childs
	*/
	public java.util.List<WatsonChild> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson childs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonChildModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson childs
	* @param end the upper bound of the range of watson childs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson childs
	*/
	public java.util.List<WatsonChild> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonChild> orderByComparator);

	/**
	* Returns an ordered range of all the watson childs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonChildModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson childs
	* @param end the upper bound of the range of watson childs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson childs
	*/
	public java.util.List<WatsonChild> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonChild> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson childs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson childs.
	*
	* @return the number of watson childs
	*/
	public int countAll();
}