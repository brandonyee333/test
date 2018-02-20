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

import com.liferay.watson.exception.NoSuchActivityAuditException;
import com.liferay.watson.model.WatsonActivityAudit;

/**
 * The persistence interface for the watson activity audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see com.liferay.watson.service.persistence.impl.WatsonActivityAuditPersistenceImpl
 * @see WatsonActivityAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonActivityAuditPersistence extends BasePersistence<WatsonActivityAudit> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonActivityAuditUtil} to access the watson activity audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the watson activity audit in the entity cache if it is enabled.
	*
	* @param watsonActivityAudit the watson activity audit
	*/
	public void cacheResult(WatsonActivityAudit watsonActivityAudit);

	/**
	* Caches the watson activity audits in the entity cache if it is enabled.
	*
	* @param watsonActivityAudits the watson activity audits
	*/
	public void cacheResult(
		java.util.List<WatsonActivityAudit> watsonActivityAudits);

	/**
	* Creates a new watson activity audit with the primary key. Does not add the watson activity audit to the database.
	*
	* @param watsonActivityAuditId the primary key for the new watson activity audit
	* @return the new watson activity audit
	*/
	public WatsonActivityAudit create(long watsonActivityAuditId);

	/**
	* Removes the watson activity audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonActivityAuditId the primary key of the watson activity audit
	* @return the watson activity audit that was removed
	* @throws NoSuchActivityAuditException if a watson activity audit with the primary key could not be found
	*/
	public WatsonActivityAudit remove(long watsonActivityAuditId)
		throws NoSuchActivityAuditException;

	public WatsonActivityAudit updateImpl(
		WatsonActivityAudit watsonActivityAudit);

	/**
	* Returns the watson activity audit with the primary key or throws a {@link NoSuchActivityAuditException} if it could not be found.
	*
	* @param watsonActivityAuditId the primary key of the watson activity audit
	* @return the watson activity audit
	* @throws NoSuchActivityAuditException if a watson activity audit with the primary key could not be found
	*/
	public WatsonActivityAudit findByPrimaryKey(long watsonActivityAuditId)
		throws NoSuchActivityAuditException;

	/**
	* Returns the watson activity audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonActivityAuditId the primary key of the watson activity audit
	* @return the watson activity audit, or <code>null</code> if a watson activity audit with the primary key could not be found
	*/
	public WatsonActivityAudit fetchByPrimaryKey(long watsonActivityAuditId);

	@Override
	public java.util.Map<java.io.Serializable, WatsonActivityAudit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the watson activity audits.
	*
	* @return the watson activity audits
	*/
	public java.util.List<WatsonActivityAudit> findAll();

	/**
	* Returns a range of all the watson activity audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson activity audits
	* @param end the upper bound of the range of watson activity audits (not inclusive)
	* @return the range of watson activity audits
	*/
	public java.util.List<WatsonActivityAudit> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson activity audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson activity audits
	* @param end the upper bound of the range of watson activity audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson activity audits
	*/
	public java.util.List<WatsonActivityAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonActivityAudit> orderByComparator);

	/**
	* Returns an ordered range of all the watson activity audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonActivityAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson activity audits
	* @param end the upper bound of the range of watson activity audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson activity audits
	*/
	public java.util.List<WatsonActivityAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonActivityAudit> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson activity audits from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson activity audits.
	*
	* @return the number of watson activity audits
	*/
	public int countAll();
}