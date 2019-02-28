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
import com.liferay.watson.exception.NoSuchAddressAuditException;
import com.liferay.watson.model.WatsonAddressAudit;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson address audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonAddressAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonAddressAuditPersistence
	extends BasePersistence<WatsonAddressAudit> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonAddressAuditUtil} to access the watson address audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonAddressAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the watson address audit in the entity cache if it is enabled.
	 *
	 * @param watsonAddressAudit the watson address audit
	 */
	public void cacheResult(WatsonAddressAudit watsonAddressAudit);

	/**
	 * Caches the watson address audits in the entity cache if it is enabled.
	 *
	 * @param watsonAddressAudits the watson address audits
	 */
	public void cacheResult(
		java.util.List<WatsonAddressAudit> watsonAddressAudits);

	/**
	 * Creates a new watson address audit with the primary key. Does not add the watson address audit to the database.
	 *
	 * @param watsonAddressAuditId the primary key for the new watson address audit
	 * @return the new watson address audit
	 */
	public WatsonAddressAudit create(long watsonAddressAuditId);

	/**
	 * Removes the watson address audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonAddressAuditId the primary key of the watson address audit
	 * @return the watson address audit that was removed
	 * @throws NoSuchAddressAuditException if a watson address audit with the primary key could not be found
	 */
	public WatsonAddressAudit remove(long watsonAddressAuditId)
		throws NoSuchAddressAuditException;

	public WatsonAddressAudit updateImpl(WatsonAddressAudit watsonAddressAudit);

	/**
	 * Returns the watson address audit with the primary key or throws a <code>NoSuchAddressAuditException</code> if it could not be found.
	 *
	 * @param watsonAddressAuditId the primary key of the watson address audit
	 * @return the watson address audit
	 * @throws NoSuchAddressAuditException if a watson address audit with the primary key could not be found
	 */
	public WatsonAddressAudit findByPrimaryKey(long watsonAddressAuditId)
		throws NoSuchAddressAuditException;

	/**
	 * Returns the watson address audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonAddressAuditId the primary key of the watson address audit
	 * @return the watson address audit, or <code>null</code> if a watson address audit with the primary key could not be found
	 */
	public WatsonAddressAudit fetchByPrimaryKey(long watsonAddressAuditId);

	/**
	 * Returns all the watson address audits.
	 *
	 * @return the watson address audits
	 */
	public java.util.List<WatsonAddressAudit> findAll();

	/**
	 * Returns a range of all the watson address audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonAddressAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson address audits
	 * @param end the upper bound of the range of watson address audits (not inclusive)
	 * @return the range of watson address audits
	 */
	public java.util.List<WatsonAddressAudit> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the watson address audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonAddressAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson address audits
	 * @param end the upper bound of the range of watson address audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson address audits
	 */
	public java.util.List<WatsonAddressAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonAddressAudit>
			orderByComparator);

	/**
	 * Returns an ordered range of all the watson address audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonAddressAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson address audits
	 * @param end the upper bound of the range of watson address audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson address audits
	 */
	public java.util.List<WatsonAddressAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonAddressAudit>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the watson address audits from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of watson address audits.
	 *
	 * @return the number of watson address audits
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}