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

import com.liferay.watson.exception.NoSuchIncidentRelAuditException;
import com.liferay.watson.model.WatsonIncidentRelAudit;

/**
 * The persistence interface for the watson incident rel audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see com.liferay.watson.service.persistence.impl.WatsonIncidentRelAuditPersistenceImpl
 * @see WatsonIncidentRelAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonIncidentRelAuditPersistence extends BasePersistence<WatsonIncidentRelAudit> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonIncidentRelAuditUtil} to access the watson incident rel audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the watson incident rel audit in the entity cache if it is enabled.
	*
	* @param watsonIncidentRelAudit the watson incident rel audit
	*/
	public void cacheResult(WatsonIncidentRelAudit watsonIncidentRelAudit);

	/**
	* Caches the watson incident rel audits in the entity cache if it is enabled.
	*
	* @param watsonIncidentRelAudits the watson incident rel audits
	*/
	public void cacheResult(
		java.util.List<WatsonIncidentRelAudit> watsonIncidentRelAudits);

	/**
	* Creates a new watson incident rel audit with the primary key. Does not add the watson incident rel audit to the database.
	*
	* @param watsonIncidentRelAuditId the primary key for the new watson incident rel audit
	* @return the new watson incident rel audit
	*/
	public WatsonIncidentRelAudit create(long watsonIncidentRelAuditId);

	/**
	* Removes the watson incident rel audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonIncidentRelAuditId the primary key of the watson incident rel audit
	* @return the watson incident rel audit that was removed
	* @throws NoSuchIncidentRelAuditException if a watson incident rel audit with the primary key could not be found
	*/
	public WatsonIncidentRelAudit remove(long watsonIncidentRelAuditId)
		throws NoSuchIncidentRelAuditException;

	public WatsonIncidentRelAudit updateImpl(
		WatsonIncidentRelAudit watsonIncidentRelAudit);

	/**
	* Returns the watson incident rel audit with the primary key or throws a {@link NoSuchIncidentRelAuditException} if it could not be found.
	*
	* @param watsonIncidentRelAuditId the primary key of the watson incident rel audit
	* @return the watson incident rel audit
	* @throws NoSuchIncidentRelAuditException if a watson incident rel audit with the primary key could not be found
	*/
	public WatsonIncidentRelAudit findByPrimaryKey(
		long watsonIncidentRelAuditId) throws NoSuchIncidentRelAuditException;

	/**
	* Returns the watson incident rel audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonIncidentRelAuditId the primary key of the watson incident rel audit
	* @return the watson incident rel audit, or <code>null</code> if a watson incident rel audit with the primary key could not be found
	*/
	public WatsonIncidentRelAudit fetchByPrimaryKey(
		long watsonIncidentRelAuditId);

	@Override
	public java.util.Map<java.io.Serializable, WatsonIncidentRelAudit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the watson incident rel audits.
	*
	* @return the watson incident rel audits
	*/
	public java.util.List<WatsonIncidentRelAudit> findAll();

	/**
	* Returns a range of all the watson incident rel audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentRelAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incident rel audits
	* @param end the upper bound of the range of watson incident rel audits (not inclusive)
	* @return the range of watson incident rel audits
	*/
	public java.util.List<WatsonIncidentRelAudit> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson incident rel audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentRelAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incident rel audits
	* @param end the upper bound of the range of watson incident rel audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson incident rel audits
	*/
	public java.util.List<WatsonIncidentRelAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonIncidentRelAudit> orderByComparator);

	/**
	* Returns an ordered range of all the watson incident rel audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentRelAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incident rel audits
	* @param end the upper bound of the range of watson incident rel audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson incident rel audits
	*/
	public java.util.List<WatsonIncidentRelAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonIncidentRelAudit> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson incident rel audits from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson incident rel audits.
	*
	* @return the number of watson incident rel audits
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}