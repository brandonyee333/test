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

import com.liferay.watson.exception.NoSuchIncidentRelException;
import com.liferay.watson.model.WatsonIncidentRel;

/**
 * The persistence interface for the watson incident rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see com.liferay.watson.service.persistence.impl.WatsonIncidentRelPersistenceImpl
 * @see WatsonIncidentRelUtil
 * @generated
 */
@ProviderType
public interface WatsonIncidentRelPersistence extends BasePersistence<WatsonIncidentRel> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonIncidentRelUtil} to access the watson incident rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the watson incident rel in the entity cache if it is enabled.
	*
	* @param watsonIncidentRel the watson incident rel
	*/
	public void cacheResult(WatsonIncidentRel watsonIncidentRel);

	/**
	* Caches the watson incident rels in the entity cache if it is enabled.
	*
	* @param watsonIncidentRels the watson incident rels
	*/
	public void cacheResult(
		java.util.List<WatsonIncidentRel> watsonIncidentRels);

	/**
	* Creates a new watson incident rel with the primary key. Does not add the watson incident rel to the database.
	*
	* @param watsonIncidentRelId the primary key for the new watson incident rel
	* @return the new watson incident rel
	*/
	public WatsonIncidentRel create(long watsonIncidentRelId);

	/**
	* Removes the watson incident rel with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonIncidentRelId the primary key of the watson incident rel
	* @return the watson incident rel that was removed
	* @throws NoSuchIncidentRelException if a watson incident rel with the primary key could not be found
	*/
	public WatsonIncidentRel remove(long watsonIncidentRelId)
		throws NoSuchIncidentRelException;

	public WatsonIncidentRel updateImpl(WatsonIncidentRel watsonIncidentRel);

	/**
	* Returns the watson incident rel with the primary key or throws a {@link NoSuchIncidentRelException} if it could not be found.
	*
	* @param watsonIncidentRelId the primary key of the watson incident rel
	* @return the watson incident rel
	* @throws NoSuchIncidentRelException if a watson incident rel with the primary key could not be found
	*/
	public WatsonIncidentRel findByPrimaryKey(long watsonIncidentRelId)
		throws NoSuchIncidentRelException;

	/**
	* Returns the watson incident rel with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonIncidentRelId the primary key of the watson incident rel
	* @return the watson incident rel, or <code>null</code> if a watson incident rel with the primary key could not be found
	*/
	public WatsonIncidentRel fetchByPrimaryKey(long watsonIncidentRelId);

	@Override
	public java.util.Map<java.io.Serializable, WatsonIncidentRel> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the watson incident rels.
	*
	* @return the watson incident rels
	*/
	public java.util.List<WatsonIncidentRel> findAll();

	/**
	* Returns a range of all the watson incident rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incident rels
	* @param end the upper bound of the range of watson incident rels (not inclusive)
	* @return the range of watson incident rels
	*/
	public java.util.List<WatsonIncidentRel> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson incident rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incident rels
	* @param end the upper bound of the range of watson incident rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson incident rels
	*/
	public java.util.List<WatsonIncidentRel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonIncidentRel> orderByComparator);

	/**
	* Returns an ordered range of all the watson incident rels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentRelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incident rels
	* @param end the upper bound of the range of watson incident rels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson incident rels
	*/
	public java.util.List<WatsonIncidentRel> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonIncidentRel> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson incident rels from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson incident rels.
	*
	* @return the number of watson incident rels
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}