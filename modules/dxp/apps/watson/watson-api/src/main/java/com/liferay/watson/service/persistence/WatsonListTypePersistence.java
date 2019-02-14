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

import com.liferay.watson.exception.NoSuchListTypeException;
import com.liferay.watson.model.WatsonListType;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson list type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonListTypeUtil
 * @generated
 */
@ProviderType
public interface WatsonListTypePersistence extends BasePersistence<WatsonListType> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonListTypeUtil} to access the watson list type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonListType> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	* Caches the watson list type in the entity cache if it is enabled.
	*
	* @param watsonListType the watson list type
	*/
	public void cacheResult(WatsonListType watsonListType);

	/**
	* Caches the watson list types in the entity cache if it is enabled.
	*
	* @param watsonListTypes the watson list types
	*/
	public void cacheResult(java.util.List<WatsonListType> watsonListTypes);

	/**
	* Creates a new watson list type with the primary key. Does not add the watson list type to the database.
	*
	* @param watsonListTypeId the primary key for the new watson list type
	* @return the new watson list type
	*/
	public WatsonListType create(long watsonListTypeId);

	/**
	* Removes the watson list type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonListTypeId the primary key of the watson list type
	* @return the watson list type that was removed
	* @throws NoSuchListTypeException if a watson list type with the primary key could not be found
	*/
	public WatsonListType remove(long watsonListTypeId)
		throws NoSuchListTypeException;

	public WatsonListType updateImpl(WatsonListType watsonListType);

	/**
	* Returns the watson list type with the primary key or throws a <code>NoSuchListTypeException</code> if it could not be found.
	*
	* @param watsonListTypeId the primary key of the watson list type
	* @return the watson list type
	* @throws NoSuchListTypeException if a watson list type with the primary key could not be found
	*/
	public WatsonListType findByPrimaryKey(long watsonListTypeId)
		throws NoSuchListTypeException;

	/**
	* Returns the watson list type with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonListTypeId the primary key of the watson list type
	* @return the watson list type, or <code>null</code> if a watson list type with the primary key could not be found
	*/
	public WatsonListType fetchByPrimaryKey(long watsonListTypeId);

	/**
	* Returns all the watson list types.
	*
	* @return the watson list types
	*/
	public java.util.List<WatsonListType> findAll();

	/**
	* Returns a range of all the watson list types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list types
	* @param end the upper bound of the range of watson list types (not inclusive)
	* @return the range of watson list types
	*/
	public java.util.List<WatsonListType> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson list types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list types
	* @param end the upper bound of the range of watson list types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson list types
	*/
	public java.util.List<WatsonListType> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonListType> orderByComparator);

	/**
	* Returns an ordered range of all the watson list types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson list types
	* @param end the upper bound of the range of watson list types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson list types
	*/
	public java.util.List<WatsonListType> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonListType> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson list types from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson list types.
	*
	* @return the number of watson list types
	*/
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();
}