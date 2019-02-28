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
import com.liferay.watson.exception.NoSuchListTypeRelAuditException;
import com.liferay.watson.model.WatsonListTypeRelAudit;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson list type rel audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonListTypeRelAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonListTypeRelAuditPersistence
	extends BasePersistence<WatsonListTypeRelAudit> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonListTypeRelAuditUtil} to access the watson list type rel audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonListTypeRelAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the watson list type rel audit in the entity cache if it is enabled.
	 *
	 * @param watsonListTypeRelAudit the watson list type rel audit
	 */
	public void cacheResult(WatsonListTypeRelAudit watsonListTypeRelAudit);

	/**
	 * Caches the watson list type rel audits in the entity cache if it is enabled.
	 *
	 * @param watsonListTypeRelAudits the watson list type rel audits
	 */
	public void cacheResult(
		java.util.List<WatsonListTypeRelAudit> watsonListTypeRelAudits);

	/**
	 * Creates a new watson list type rel audit with the primary key. Does not add the watson list type rel audit to the database.
	 *
	 * @param watsonListTypeRelAuditId the primary key for the new watson list type rel audit
	 * @return the new watson list type rel audit
	 */
	public WatsonListTypeRelAudit create(long watsonListTypeRelAuditId);

	/**
	 * Removes the watson list type rel audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRelAuditId the primary key of the watson list type rel audit
	 * @return the watson list type rel audit that was removed
	 * @throws NoSuchListTypeRelAuditException if a watson list type rel audit with the primary key could not be found
	 */
	public WatsonListTypeRelAudit remove(long watsonListTypeRelAuditId)
		throws NoSuchListTypeRelAuditException;

	public WatsonListTypeRelAudit updateImpl(
		WatsonListTypeRelAudit watsonListTypeRelAudit);

	/**
	 * Returns the watson list type rel audit with the primary key or throws a <code>NoSuchListTypeRelAuditException</code> if it could not be found.
	 *
	 * @param watsonListTypeRelAuditId the primary key of the watson list type rel audit
	 * @return the watson list type rel audit
	 * @throws NoSuchListTypeRelAuditException if a watson list type rel audit with the primary key could not be found
	 */
	public WatsonListTypeRelAudit findByPrimaryKey(
			long watsonListTypeRelAuditId)
		throws NoSuchListTypeRelAuditException;

	/**
	 * Returns the watson list type rel audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonListTypeRelAuditId the primary key of the watson list type rel audit
	 * @return the watson list type rel audit, or <code>null</code> if a watson list type rel audit with the primary key could not be found
	 */
	public WatsonListTypeRelAudit fetchByPrimaryKey(
		long watsonListTypeRelAuditId);

	/**
	 * Returns all the watson list type rel audits.
	 *
	 * @return the watson list type rel audits
	 */
	public java.util.List<WatsonListTypeRelAudit> findAll();

	/**
	 * Returns a range of all the watson list type rel audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeRelAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rel audits
	 * @param end the upper bound of the range of watson list type rel audits (not inclusive)
	 * @return the range of watson list type rel audits
	 */
	public java.util.List<WatsonListTypeRelAudit> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the watson list type rel audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeRelAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rel audits
	 * @param end the upper bound of the range of watson list type rel audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson list type rel audits
	 */
	public java.util.List<WatsonListTypeRelAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonListTypeRelAudit>
			orderByComparator);

	/**
	 * Returns an ordered range of all the watson list type rel audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeRelAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rel audits
	 * @param end the upper bound of the range of watson list type rel audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson list type rel audits
	 */
	public java.util.List<WatsonListTypeRelAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonListTypeRelAudit>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the watson list type rel audits from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of watson list type rel audits.
	 *
	 * @return the number of watson list type rel audits
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}