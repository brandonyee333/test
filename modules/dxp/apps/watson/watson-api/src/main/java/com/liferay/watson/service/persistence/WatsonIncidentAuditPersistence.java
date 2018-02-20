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

import com.liferay.watson.exception.NoSuchIncidentAuditException;
import com.liferay.watson.model.WatsonIncidentAudit;

/**
 * The persistence interface for the watson incident audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see com.liferay.watson.service.persistence.impl.WatsonIncidentAuditPersistenceImpl
 * @see WatsonIncidentAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonIncidentAuditPersistence extends BasePersistence<WatsonIncidentAudit> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonIncidentAuditUtil} to access the watson incident audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the watson incident audit in the entity cache if it is enabled.
	*
	* @param watsonIncidentAudit the watson incident audit
	*/
	public void cacheResult(WatsonIncidentAudit watsonIncidentAudit);

	/**
	* Caches the watson incident audits in the entity cache if it is enabled.
	*
	* @param watsonIncidentAudits the watson incident audits
	*/
	public void cacheResult(
		java.util.List<WatsonIncidentAudit> watsonIncidentAudits);

	/**
	* Creates a new watson incident audit with the primary key. Does not add the watson incident audit to the database.
	*
	* @param watsonIncidentAuditId the primary key for the new watson incident audit
	* @return the new watson incident audit
	*/
	public WatsonIncidentAudit create(long watsonIncidentAuditId);

	/**
	* Removes the watson incident audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonIncidentAuditId the primary key of the watson incident audit
	* @return the watson incident audit that was removed
	* @throws NoSuchIncidentAuditException if a watson incident audit with the primary key could not be found
	*/
	public WatsonIncidentAudit remove(long watsonIncidentAuditId)
		throws NoSuchIncidentAuditException;

	public WatsonIncidentAudit updateImpl(
		WatsonIncidentAudit watsonIncidentAudit);

	/**
	* Returns the watson incident audit with the primary key or throws a {@link NoSuchIncidentAuditException} if it could not be found.
	*
	* @param watsonIncidentAuditId the primary key of the watson incident audit
	* @return the watson incident audit
	* @throws NoSuchIncidentAuditException if a watson incident audit with the primary key could not be found
	*/
	public WatsonIncidentAudit findByPrimaryKey(long watsonIncidentAuditId)
		throws NoSuchIncidentAuditException;

	/**
	* Returns the watson incident audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonIncidentAuditId the primary key of the watson incident audit
	* @return the watson incident audit, or <code>null</code> if a watson incident audit with the primary key could not be found
	*/
	public WatsonIncidentAudit fetchByPrimaryKey(long watsonIncidentAuditId);

	@Override
	public java.util.Map<java.io.Serializable, WatsonIncidentAudit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the watson incident audits.
	*
	* @return the watson incident audits
	*/
	public java.util.List<WatsonIncidentAudit> findAll();

	/**
	* Returns a range of all the watson incident audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incident audits
	* @param end the upper bound of the range of watson incident audits (not inclusive)
	* @return the range of watson incident audits
	*/
	public java.util.List<WatsonIncidentAudit> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson incident audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incident audits
	* @param end the upper bound of the range of watson incident audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson incident audits
	*/
	public java.util.List<WatsonIncidentAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonIncidentAudit> orderByComparator);

	/**
	* Returns an ordered range of all the watson incident audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonIncidentAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson incident audits
	* @param end the upper bound of the range of watson incident audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson incident audits
	*/
	public java.util.List<WatsonIncidentAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonIncidentAudit> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson incident audits from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson incident audits.
	*
	* @return the number of watson incident audits
	*/
	public int countAll();
}