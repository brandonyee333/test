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

import com.liferay.watson.exception.NoSuchVehicleException;
import com.liferay.watson.model.WatsonVehicle;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson vehicle service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonVehicleUtil
 * @generated
 */
@ProviderType
public interface WatsonVehiclePersistence extends BasePersistence<WatsonVehicle> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonVehicleUtil} to access the watson vehicle persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonVehicle> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	* Caches the watson vehicle in the entity cache if it is enabled.
	*
	* @param watsonVehicle the watson vehicle
	*/
	public void cacheResult(WatsonVehicle watsonVehicle);

	/**
	* Caches the watson vehicles in the entity cache if it is enabled.
	*
	* @param watsonVehicles the watson vehicles
	*/
	public void cacheResult(java.util.List<WatsonVehicle> watsonVehicles);

	/**
	* Creates a new watson vehicle with the primary key. Does not add the watson vehicle to the database.
	*
	* @param watsonVehicleId the primary key for the new watson vehicle
	* @return the new watson vehicle
	*/
	public WatsonVehicle create(long watsonVehicleId);

	/**
	* Removes the watson vehicle with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonVehicleId the primary key of the watson vehicle
	* @return the watson vehicle that was removed
	* @throws NoSuchVehicleException if a watson vehicle with the primary key could not be found
	*/
	public WatsonVehicle remove(long watsonVehicleId)
		throws NoSuchVehicleException;

	public WatsonVehicle updateImpl(WatsonVehicle watsonVehicle);

	/**
	* Returns the watson vehicle with the primary key or throws a <code>NoSuchVehicleException</code> if it could not be found.
	*
	* @param watsonVehicleId the primary key of the watson vehicle
	* @return the watson vehicle
	* @throws NoSuchVehicleException if a watson vehicle with the primary key could not be found
	*/
	public WatsonVehicle findByPrimaryKey(long watsonVehicleId)
		throws NoSuchVehicleException;

	/**
	* Returns the watson vehicle with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonVehicleId the primary key of the watson vehicle
	* @return the watson vehicle, or <code>null</code> if a watson vehicle with the primary key could not be found
	*/
	public WatsonVehicle fetchByPrimaryKey(long watsonVehicleId);

	/**
	* Returns all the watson vehicles.
	*
	* @return the watson vehicles
	*/
	public java.util.List<WatsonVehicle> findAll();

	/**
	* Returns a range of all the watson vehicles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonVehicleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson vehicles
	* @param end the upper bound of the range of watson vehicles (not inclusive)
	* @return the range of watson vehicles
	*/
	public java.util.List<WatsonVehicle> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson vehicles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonVehicleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson vehicles
	* @param end the upper bound of the range of watson vehicles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson vehicles
	*/
	public java.util.List<WatsonVehicle> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonVehicle> orderByComparator);

	/**
	* Returns an ordered range of all the watson vehicles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonVehicleModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson vehicles
	* @param end the upper bound of the range of watson vehicles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson vehicles
	*/
	public java.util.List<WatsonVehicle> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonVehicle> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson vehicles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson vehicles.
	*
	* @return the number of watson vehicles
	*/
	public int countAll();
}