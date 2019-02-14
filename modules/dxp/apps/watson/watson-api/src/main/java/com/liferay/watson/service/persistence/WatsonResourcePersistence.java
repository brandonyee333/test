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

import com.liferay.watson.exception.NoSuchResourceException;
import com.liferay.watson.model.WatsonResource;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson resource service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonResourceUtil
 * @generated
 */
@ProviderType
public interface WatsonResourcePersistence extends BasePersistence<WatsonResource> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonResourceUtil} to access the watson resource persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonResource> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	* Caches the watson resource in the entity cache if it is enabled.
	*
	* @param watsonResource the watson resource
	*/
	public void cacheResult(WatsonResource watsonResource);

	/**
	* Caches the watson resources in the entity cache if it is enabled.
	*
	* @param watsonResources the watson resources
	*/
	public void cacheResult(java.util.List<WatsonResource> watsonResources);

	/**
	* Creates a new watson resource with the primary key. Does not add the watson resource to the database.
	*
	* @param watsonResourceId the primary key for the new watson resource
	* @return the new watson resource
	*/
	public WatsonResource create(long watsonResourceId);

	/**
	* Removes the watson resource with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonResourceId the primary key of the watson resource
	* @return the watson resource that was removed
	* @throws NoSuchResourceException if a watson resource with the primary key could not be found
	*/
	public WatsonResource remove(long watsonResourceId)
		throws NoSuchResourceException;

	public WatsonResource updateImpl(WatsonResource watsonResource);

	/**
	* Returns the watson resource with the primary key or throws a <code>NoSuchResourceException</code> if it could not be found.
	*
	* @param watsonResourceId the primary key of the watson resource
	* @return the watson resource
	* @throws NoSuchResourceException if a watson resource with the primary key could not be found
	*/
	public WatsonResource findByPrimaryKey(long watsonResourceId)
		throws NoSuchResourceException;

	/**
	* Returns the watson resource with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonResourceId the primary key of the watson resource
	* @return the watson resource, or <code>null</code> if a watson resource with the primary key could not be found
	*/
	public WatsonResource fetchByPrimaryKey(long watsonResourceId);

	/**
	* Returns all the watson resources.
	*
	* @return the watson resources
	*/
	public java.util.List<WatsonResource> findAll();

	/**
	* Returns a range of all the watson resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonResourceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson resources
	* @param end the upper bound of the range of watson resources (not inclusive)
	* @return the range of watson resources
	*/
	public java.util.List<WatsonResource> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonResourceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson resources
	* @param end the upper bound of the range of watson resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson resources
	*/
	public java.util.List<WatsonResource> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonResource> orderByComparator);

	/**
	* Returns an ordered range of all the watson resources.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonResourceModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson resources
	* @param end the upper bound of the range of watson resources (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson resources
	*/
	public java.util.List<WatsonResource> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonResource> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson resources from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson resources.
	*
	* @return the number of watson resources
	*/
	public int countAll();
}