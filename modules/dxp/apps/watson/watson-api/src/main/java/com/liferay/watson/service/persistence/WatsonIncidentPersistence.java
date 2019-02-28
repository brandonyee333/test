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
import com.liferay.watson.exception.NoSuchIncidentException;
import com.liferay.watson.model.WatsonIncident;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson incident service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonIncidentUtil
 * @generated
 */
@ProviderType
public interface WatsonIncidentPersistence
	extends BasePersistence<WatsonIncident> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonIncidentUtil} to access the watson incident persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonIncident> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the watson incident in the entity cache if it is enabled.
	 *
	 * @param watsonIncident the watson incident
	 */
	public void cacheResult(WatsonIncident watsonIncident);

	/**
	 * Caches the watson incidents in the entity cache if it is enabled.
	 *
	 * @param watsonIncidents the watson incidents
	 */
	public void cacheResult(java.util.List<WatsonIncident> watsonIncidents);

	/**
	 * Creates a new watson incident with the primary key. Does not add the watson incident to the database.
	 *
	 * @param watsonIncidentId the primary key for the new watson incident
	 * @return the new watson incident
	 */
	public WatsonIncident create(long watsonIncidentId);

	/**
	 * Removes the watson incident with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentId the primary key of the watson incident
	 * @return the watson incident that was removed
	 * @throws NoSuchIncidentException if a watson incident with the primary key could not be found
	 */
	public WatsonIncident remove(long watsonIncidentId)
		throws NoSuchIncidentException;

	public WatsonIncident updateImpl(WatsonIncident watsonIncident);

	/**
	 * Returns the watson incident with the primary key or throws a <code>NoSuchIncidentException</code> if it could not be found.
	 *
	 * @param watsonIncidentId the primary key of the watson incident
	 * @return the watson incident
	 * @throws NoSuchIncidentException if a watson incident with the primary key could not be found
	 */
	public WatsonIncident findByPrimaryKey(long watsonIncidentId)
		throws NoSuchIncidentException;

	/**
	 * Returns the watson incident with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonIncidentId the primary key of the watson incident
	 * @return the watson incident, or <code>null</code> if a watson incident with the primary key could not be found
	 */
	public WatsonIncident fetchByPrimaryKey(long watsonIncidentId);

	/**
	 * Returns all the watson incidents.
	 *
	 * @return the watson incidents
	 */
	public java.util.List<WatsonIncident> findAll();

	/**
	 * Returns a range of all the watson incidents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonIncidentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incidents
	 * @param end the upper bound of the range of watson incidents (not inclusive)
	 * @return the range of watson incidents
	 */
	public java.util.List<WatsonIncident> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the watson incidents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonIncidentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incidents
	 * @param end the upper bound of the range of watson incidents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson incidents
	 */
	public java.util.List<WatsonIncident> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonIncident>
			orderByComparator);

	/**
	 * Returns an ordered range of all the watson incidents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonIncidentModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incidents
	 * @param end the upper bound of the range of watson incidents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson incidents
	 */
	public java.util.List<WatsonIncident> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonIncident>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the watson incidents from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of watson incidents.
	 *
	 * @return the number of watson incidents
	 */
	public int countAll();

}