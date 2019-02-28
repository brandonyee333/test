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
import com.liferay.watson.exception.NoSuchRelationshipAuditException;
import com.liferay.watson.model.WatsonRelationshipAudit;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson relationship audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonRelationshipAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonRelationshipAuditPersistence
	extends BasePersistence<WatsonRelationshipAudit> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonRelationshipAuditUtil} to access the watson relationship audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonRelationshipAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the watson relationship audit in the entity cache if it is enabled.
	 *
	 * @param watsonRelationshipAudit the watson relationship audit
	 */
	public void cacheResult(WatsonRelationshipAudit watsonRelationshipAudit);

	/**
	 * Caches the watson relationship audits in the entity cache if it is enabled.
	 *
	 * @param watsonRelationshipAudits the watson relationship audits
	 */
	public void cacheResult(
		java.util.List<WatsonRelationshipAudit> watsonRelationshipAudits);

	/**
	 * Creates a new watson relationship audit with the primary key. Does not add the watson relationship audit to the database.
	 *
	 * @param watsonRelationshipAuditId the primary key for the new watson relationship audit
	 * @return the new watson relationship audit
	 */
	public WatsonRelationshipAudit create(long watsonRelationshipAuditId);

	/**
	 * Removes the watson relationship audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonRelationshipAuditId the primary key of the watson relationship audit
	 * @return the watson relationship audit that was removed
	 * @throws NoSuchRelationshipAuditException if a watson relationship audit with the primary key could not be found
	 */
	public WatsonRelationshipAudit remove(long watsonRelationshipAuditId)
		throws NoSuchRelationshipAuditException;

	public WatsonRelationshipAudit updateImpl(
		WatsonRelationshipAudit watsonRelationshipAudit);

	/**
	 * Returns the watson relationship audit with the primary key or throws a <code>NoSuchRelationshipAuditException</code> if it could not be found.
	 *
	 * @param watsonRelationshipAuditId the primary key of the watson relationship audit
	 * @return the watson relationship audit
	 * @throws NoSuchRelationshipAuditException if a watson relationship audit with the primary key could not be found
	 */
	public WatsonRelationshipAudit findByPrimaryKey(
			long watsonRelationshipAuditId)
		throws NoSuchRelationshipAuditException;

	/**
	 * Returns the watson relationship audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonRelationshipAuditId the primary key of the watson relationship audit
	 * @return the watson relationship audit, or <code>null</code> if a watson relationship audit with the primary key could not be found
	 */
	public WatsonRelationshipAudit fetchByPrimaryKey(
		long watsonRelationshipAuditId);

	/**
	 * Returns all the watson relationship audits.
	 *
	 * @return the watson relationship audits
	 */
	public java.util.List<WatsonRelationshipAudit> findAll();

	/**
	 * Returns a range of all the watson relationship audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonRelationshipAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson relationship audits
	 * @param end the upper bound of the range of watson relationship audits (not inclusive)
	 * @return the range of watson relationship audits
	 */
	public java.util.List<WatsonRelationshipAudit> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the watson relationship audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonRelationshipAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson relationship audits
	 * @param end the upper bound of the range of watson relationship audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson relationship audits
	 */
	public java.util.List<WatsonRelationshipAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<WatsonRelationshipAudit> orderByComparator);

	/**
	 * Returns an ordered range of all the watson relationship audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonRelationshipAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson relationship audits
	 * @param end the upper bound of the range of watson relationship audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson relationship audits
	 */
	public java.util.List<WatsonRelationshipAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<WatsonRelationshipAudit> orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the watson relationship audits from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of watson relationship audits.
	 *
	 * @return the number of watson relationship audits
	 */
	public int countAll();

}