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

import com.liferay.watson.exception.NoSuchVehicleAuditException;
import com.liferay.watson.model.WatsonVehicleAudit;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson vehicle audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonVehicleAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonVehicleAuditPersistence extends BasePersistence<WatsonVehicleAudit> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonVehicleAuditUtil} to access the watson vehicle audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonVehicleAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	* Caches the watson vehicle audit in the entity cache if it is enabled.
	*
	* @param watsonVehicleAudit the watson vehicle audit
	*/
	public void cacheResult(WatsonVehicleAudit watsonVehicleAudit);

	/**
	* Caches the watson vehicle audits in the entity cache if it is enabled.
	*
	* @param watsonVehicleAudits the watson vehicle audits
	*/
	public void cacheResult(
		java.util.List<WatsonVehicleAudit> watsonVehicleAudits);

	/**
	* Creates a new watson vehicle audit with the primary key. Does not add the watson vehicle audit to the database.
	*
	* @param watsonVehicleAuditId the primary key for the new watson vehicle audit
	* @return the new watson vehicle audit
	*/
	public WatsonVehicleAudit create(long watsonVehicleAuditId);

	/**
	* Removes the watson vehicle audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonVehicleAuditId the primary key of the watson vehicle audit
	* @return the watson vehicle audit that was removed
	* @throws NoSuchVehicleAuditException if a watson vehicle audit with the primary key could not be found
	*/
	public WatsonVehicleAudit remove(long watsonVehicleAuditId)
		throws NoSuchVehicleAuditException;

	public WatsonVehicleAudit updateImpl(WatsonVehicleAudit watsonVehicleAudit);

	/**
	* Returns the watson vehicle audit with the primary key or throws a <code>NoSuchVehicleAuditException</code> if it could not be found.
	*
	* @param watsonVehicleAuditId the primary key of the watson vehicle audit
	* @return the watson vehicle audit
	* @throws NoSuchVehicleAuditException if a watson vehicle audit with the primary key could not be found
	*/
	public WatsonVehicleAudit findByPrimaryKey(long watsonVehicleAuditId)
		throws NoSuchVehicleAuditException;

	/**
	* Returns the watson vehicle audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonVehicleAuditId the primary key of the watson vehicle audit
	* @return the watson vehicle audit, or <code>null</code> if a watson vehicle audit with the primary key could not be found
	*/
	public WatsonVehicleAudit fetchByPrimaryKey(long watsonVehicleAuditId);

	/**
	* Returns all the watson vehicle audits.
	*
	* @return the watson vehicle audits
	*/
	public java.util.List<WatsonVehicleAudit> findAll();

	/**
	* Returns a range of all the watson vehicle audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonVehicleAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson vehicle audits
	* @param end the upper bound of the range of watson vehicle audits (not inclusive)
	* @return the range of watson vehicle audits
	*/
	public java.util.List<WatsonVehicleAudit> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson vehicle audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonVehicleAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson vehicle audits
	* @param end the upper bound of the range of watson vehicle audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson vehicle audits
	*/
	public java.util.List<WatsonVehicleAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonVehicleAudit> orderByComparator);

	/**
	* Returns an ordered range of all the watson vehicle audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonVehicleAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson vehicle audits
	* @param end the upper bound of the range of watson vehicle audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson vehicle audits
	*/
	public java.util.List<WatsonVehicleAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonVehicleAudit> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson vehicle audits from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson vehicle audits.
	*
	* @return the number of watson vehicle audits
	*/
	public int countAll();
}