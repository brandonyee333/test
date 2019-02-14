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

import com.liferay.watson.exception.NoSuchChildAuditException;
import com.liferay.watson.model.WatsonChildAudit;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson child audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonChildAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonChildAuditPersistence extends BasePersistence<WatsonChildAudit> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonChildAuditUtil} to access the watson child audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonChildAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	* Caches the watson child audit in the entity cache if it is enabled.
	*
	* @param watsonChildAudit the watson child audit
	*/
	public void cacheResult(WatsonChildAudit watsonChildAudit);

	/**
	* Caches the watson child audits in the entity cache if it is enabled.
	*
	* @param watsonChildAudits the watson child audits
	*/
	public void cacheResult(java.util.List<WatsonChildAudit> watsonChildAudits);

	/**
	* Creates a new watson child audit with the primary key. Does not add the watson child audit to the database.
	*
	* @param watsonChildAuditId the primary key for the new watson child audit
	* @return the new watson child audit
	*/
	public WatsonChildAudit create(long watsonChildAuditId);

	/**
	* Removes the watson child audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonChildAuditId the primary key of the watson child audit
	* @return the watson child audit that was removed
	* @throws NoSuchChildAuditException if a watson child audit with the primary key could not be found
	*/
	public WatsonChildAudit remove(long watsonChildAuditId)
		throws NoSuchChildAuditException;

	public WatsonChildAudit updateImpl(WatsonChildAudit watsonChildAudit);

	/**
	* Returns the watson child audit with the primary key or throws a <code>NoSuchChildAuditException</code> if it could not be found.
	*
	* @param watsonChildAuditId the primary key of the watson child audit
	* @return the watson child audit
	* @throws NoSuchChildAuditException if a watson child audit with the primary key could not be found
	*/
	public WatsonChildAudit findByPrimaryKey(long watsonChildAuditId)
		throws NoSuchChildAuditException;

	/**
	* Returns the watson child audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonChildAuditId the primary key of the watson child audit
	* @return the watson child audit, or <code>null</code> if a watson child audit with the primary key could not be found
	*/
	public WatsonChildAudit fetchByPrimaryKey(long watsonChildAuditId);

	/**
	* Returns all the watson child audits.
	*
	* @return the watson child audits
	*/
	public java.util.List<WatsonChildAudit> findAll();

	/**
	* Returns a range of all the watson child audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonChildAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson child audits
	* @param end the upper bound of the range of watson child audits (not inclusive)
	* @return the range of watson child audits
	*/
	public java.util.List<WatsonChildAudit> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson child audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonChildAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson child audits
	* @param end the upper bound of the range of watson child audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson child audits
	*/
	public java.util.List<WatsonChildAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonChildAudit> orderByComparator);

	/**
	* Returns an ordered range of all the watson child audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonChildAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson child audits
	* @param end the upper bound of the range of watson child audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson child audits
	*/
	public java.util.List<WatsonChildAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonChildAudit> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson child audits from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson child audits.
	*
	* @return the number of watson child audits
	*/
	public int countAll();
}