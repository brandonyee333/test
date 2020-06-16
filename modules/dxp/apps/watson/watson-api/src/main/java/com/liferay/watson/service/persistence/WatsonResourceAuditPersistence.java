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

import com.liferay.watson.exception.NoSuchResourceAuditException;
import com.liferay.watson.model.WatsonResourceAudit;

/**
 * The persistence interface for the watson resource audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see com.liferay.watson.service.persistence.impl.WatsonResourceAuditPersistenceImpl
 * @see WatsonResourceAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonResourceAuditPersistence extends BasePersistence<WatsonResourceAudit> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonResourceAuditUtil} to access the watson resource audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the watson resource audit in the entity cache if it is enabled.
	*
	* @param watsonResourceAudit the watson resource audit
	*/
	public void cacheResult(WatsonResourceAudit watsonResourceAudit);

	/**
	* Caches the watson resource audits in the entity cache if it is enabled.
	*
	* @param watsonResourceAudits the watson resource audits
	*/
	public void cacheResult(
		java.util.List<WatsonResourceAudit> watsonResourceAudits);

	/**
	* Creates a new watson resource audit with the primary key. Does not add the watson resource audit to the database.
	*
	* @param watsonResourceAuditId the primary key for the new watson resource audit
	* @return the new watson resource audit
	*/
	public WatsonResourceAudit create(long watsonResourceAuditId);

	/**
	* Removes the watson resource audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonResourceAuditId the primary key of the watson resource audit
	* @return the watson resource audit that was removed
	* @throws NoSuchResourceAuditException if a watson resource audit with the primary key could not be found
	*/
	public WatsonResourceAudit remove(long watsonResourceAuditId)
		throws NoSuchResourceAuditException;

	public WatsonResourceAudit updateImpl(
		WatsonResourceAudit watsonResourceAudit);

	/**
	* Returns the watson resource audit with the primary key or throws a {@link NoSuchResourceAuditException} if it could not be found.
	*
	* @param watsonResourceAuditId the primary key of the watson resource audit
	* @return the watson resource audit
	* @throws NoSuchResourceAuditException if a watson resource audit with the primary key could not be found
	*/
	public WatsonResourceAudit findByPrimaryKey(long watsonResourceAuditId)
		throws NoSuchResourceAuditException;

	/**
	* Returns the watson resource audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonResourceAuditId the primary key of the watson resource audit
	* @return the watson resource audit, or <code>null</code> if a watson resource audit with the primary key could not be found
	*/
	public WatsonResourceAudit fetchByPrimaryKey(long watsonResourceAuditId);

	@Override
	public java.util.Map<java.io.Serializable, WatsonResourceAudit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the watson resource audits.
	*
	* @return the watson resource audits
	*/
	public java.util.List<WatsonResourceAudit> findAll();

	/**
	* Returns a range of all the watson resource audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonResourceAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson resource audits
	* @param end the upper bound of the range of watson resource audits (not inclusive)
	* @return the range of watson resource audits
	*/
	public java.util.List<WatsonResourceAudit> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson resource audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonResourceAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson resource audits
	* @param end the upper bound of the range of watson resource audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson resource audits
	*/
	public java.util.List<WatsonResourceAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonResourceAudit> orderByComparator);

	/**
	* Returns an ordered range of all the watson resource audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonResourceAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson resource audits
	* @param end the upper bound of the range of watson resource audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson resource audits
	*/
	public java.util.List<WatsonResourceAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonResourceAudit> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson resource audits from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson resource audits.
	*
	* @return the number of watson resource audits
	*/
	public int countAll();
}