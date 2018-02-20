/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.watson.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.watson.exception.NoSuchChildAuditException;
import com.liferay.watson.model.WatsonChildAudit;

/**
 * The persistence interface for the watson child audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see com.liferay.watson.service.persistence.impl.WatsonChildAuditPersistenceImpl
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
	* Returns the watson child audit with the primary key or throws a {@link NoSuchChildAuditException} if it could not be found.
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

	@Override
	public java.util.Map<java.io.Serializable, WatsonChildAudit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonChildAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonChildAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonChildAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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