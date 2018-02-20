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

import com.liferay.watson.exception.NoSuchAddressAuditException;
import com.liferay.watson.model.WatsonAddressAudit;

/**
 * The persistence interface for the watson address audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see com.liferay.watson.service.persistence.impl.WatsonAddressAuditPersistenceImpl
 * @see WatsonAddressAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonAddressAuditPersistence extends BasePersistence<WatsonAddressAudit> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonAddressAuditUtil} to access the watson address audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

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
	* Returns the watson address audit with the primary key or throws a {@link NoSuchAddressAuditException} if it could not be found.
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

	@Override
	public java.util.Map<java.io.Serializable, WatsonAddressAudit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonAddressAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonAddressAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson address audits
	* @param end the upper bound of the range of watson address audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson address audits
	*/
	public java.util.List<WatsonAddressAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonAddressAudit> orderByComparator);

	/**
	* Returns an ordered range of all the watson address audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonAddressAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson address audits
	* @param end the upper bound of the range of watson address audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson address audits
	*/
	public java.util.List<WatsonAddressAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonAddressAudit> orderByComparator,
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
	public java.util.Set<java.lang.String> getBadColumnNames();
}