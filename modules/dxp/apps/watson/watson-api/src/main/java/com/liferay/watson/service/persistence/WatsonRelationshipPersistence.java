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

import com.liferay.watson.exception.NoSuchRelationshipException;
import com.liferay.watson.model.WatsonRelationship;

/**
 * The persistence interface for the watson relationship service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see com.liferay.watson.service.persistence.impl.WatsonRelationshipPersistenceImpl
 * @see WatsonRelationshipUtil
 * @generated
 */
@ProviderType
public interface WatsonRelationshipPersistence extends BasePersistence<WatsonRelationship> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonRelationshipUtil} to access the watson relationship persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the watson relationship in the entity cache if it is enabled.
	*
	* @param watsonRelationship the watson relationship
	*/
	public void cacheResult(WatsonRelationship watsonRelationship);

	/**
	* Caches the watson relationships in the entity cache if it is enabled.
	*
	* @param watsonRelationships the watson relationships
	*/
	public void cacheResult(
		java.util.List<WatsonRelationship> watsonRelationships);

	/**
	* Creates a new watson relationship with the primary key. Does not add the watson relationship to the database.
	*
	* @param watsonRelationshipId the primary key for the new watson relationship
	* @return the new watson relationship
	*/
	public WatsonRelationship create(long watsonRelationshipId);

	/**
	* Removes the watson relationship with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonRelationshipId the primary key of the watson relationship
	* @return the watson relationship that was removed
	* @throws NoSuchRelationshipException if a watson relationship with the primary key could not be found
	*/
	public WatsonRelationship remove(long watsonRelationshipId)
		throws NoSuchRelationshipException;

	public WatsonRelationship updateImpl(WatsonRelationship watsonRelationship);

	/**
	* Returns the watson relationship with the primary key or throws a {@link NoSuchRelationshipException} if it could not be found.
	*
	* @param watsonRelationshipId the primary key of the watson relationship
	* @return the watson relationship
	* @throws NoSuchRelationshipException if a watson relationship with the primary key could not be found
	*/
	public WatsonRelationship findByPrimaryKey(long watsonRelationshipId)
		throws NoSuchRelationshipException;

	/**
	* Returns the watson relationship with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonRelationshipId the primary key of the watson relationship
	* @return the watson relationship, or <code>null</code> if a watson relationship with the primary key could not be found
	*/
	public WatsonRelationship fetchByPrimaryKey(long watsonRelationshipId);

	@Override
	public java.util.Map<java.io.Serializable, WatsonRelationship> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the watson relationships.
	*
	* @return the watson relationships
	*/
	public java.util.List<WatsonRelationship> findAll();

	/**
	* Returns a range of all the watson relationships.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonRelationshipModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson relationships
	* @param end the upper bound of the range of watson relationships (not inclusive)
	* @return the range of watson relationships
	*/
	public java.util.List<WatsonRelationship> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson relationships.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonRelationshipModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson relationships
	* @param end the upper bound of the range of watson relationships (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson relationships
	*/
	public java.util.List<WatsonRelationship> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonRelationship> orderByComparator);

	/**
	* Returns an ordered range of all the watson relationships.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonRelationshipModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson relationships
	* @param end the upper bound of the range of watson relationships (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson relationships
	*/
	public java.util.List<WatsonRelationship> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonRelationship> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson relationships from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson relationships.
	*
	* @return the number of watson relationships
	*/
	public int countAll();
}