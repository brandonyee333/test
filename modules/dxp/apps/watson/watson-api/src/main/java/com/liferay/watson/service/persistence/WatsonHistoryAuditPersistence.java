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
import com.liferay.watson.exception.NoSuchHistoryAuditException;
import com.liferay.watson.model.WatsonHistoryAudit;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson history audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonHistoryAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonHistoryAuditPersistence
	extends BasePersistence<WatsonHistoryAudit> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonHistoryAuditUtil} to access the watson history audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonHistoryAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the watson history audit in the entity cache if it is enabled.
	 *
	 * @param watsonHistoryAudit the watson history audit
	 */
	public void cacheResult(WatsonHistoryAudit watsonHistoryAudit);

	/**
	 * Caches the watson history audits in the entity cache if it is enabled.
	 *
	 * @param watsonHistoryAudits the watson history audits
	 */
	public void cacheResult(
		java.util.List<WatsonHistoryAudit> watsonHistoryAudits);

	/**
	 * Creates a new watson history audit with the primary key. Does not add the watson history audit to the database.
	 *
	 * @param watsonHistoryAuditId the primary key for the new watson history audit
	 * @return the new watson history audit
	 */
	public WatsonHistoryAudit create(long watsonHistoryAuditId);

	/**
	 * Removes the watson history audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonHistoryAuditId the primary key of the watson history audit
	 * @return the watson history audit that was removed
	 * @throws NoSuchHistoryAuditException if a watson history audit with the primary key could not be found
	 */
	public WatsonHistoryAudit remove(long watsonHistoryAuditId)
		throws NoSuchHistoryAuditException;

	public WatsonHistoryAudit updateImpl(WatsonHistoryAudit watsonHistoryAudit);

	/**
	 * Returns the watson history audit with the primary key or throws a <code>NoSuchHistoryAuditException</code> if it could not be found.
	 *
	 * @param watsonHistoryAuditId the primary key of the watson history audit
	 * @return the watson history audit
	 * @throws NoSuchHistoryAuditException if a watson history audit with the primary key could not be found
	 */
	public WatsonHistoryAudit findByPrimaryKey(long watsonHistoryAuditId)
		throws NoSuchHistoryAuditException;

	/**
	 * Returns the watson history audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonHistoryAuditId the primary key of the watson history audit
	 * @return the watson history audit, or <code>null</code> if a watson history audit with the primary key could not be found
	 */
	public WatsonHistoryAudit fetchByPrimaryKey(long watsonHistoryAuditId);

	/**
	 * Returns all the watson history audits.
	 *
	 * @return the watson history audits
	 */
	public java.util.List<WatsonHistoryAudit> findAll();

	/**
	 * Returns a range of all the watson history audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonHistoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson history audits
	 * @param end the upper bound of the range of watson history audits (not inclusive)
	 * @return the range of watson history audits
	 */
	public java.util.List<WatsonHistoryAudit> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the watson history audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonHistoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson history audits
	 * @param end the upper bound of the range of watson history audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson history audits
	 */
	public java.util.List<WatsonHistoryAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonHistoryAudit>
			orderByComparator);

	/**
	 * Returns an ordered range of all the watson history audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonHistoryAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson history audits
	 * @param end the upper bound of the range of watson history audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson history audits
	 */
	public java.util.List<WatsonHistoryAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonHistoryAudit>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the watson history audits from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of watson history audits.
	 *
	 * @return the number of watson history audits
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}