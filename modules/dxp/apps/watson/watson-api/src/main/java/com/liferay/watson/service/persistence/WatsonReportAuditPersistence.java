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
import com.liferay.watson.exception.NoSuchReportAuditException;
import com.liferay.watson.model.WatsonReportAudit;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson report audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonReportAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonReportAuditPersistence
	extends BasePersistence<WatsonReportAudit> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonReportAuditUtil} to access the watson report audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonReportAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the watson report audit in the entity cache if it is enabled.
	 *
	 * @param watsonReportAudit the watson report audit
	 */
	public void cacheResult(WatsonReportAudit watsonReportAudit);

	/**
	 * Caches the watson report audits in the entity cache if it is enabled.
	 *
	 * @param watsonReportAudits the watson report audits
	 */
	public void cacheResult(
		java.util.List<WatsonReportAudit> watsonReportAudits);

	/**
	 * Creates a new watson report audit with the primary key. Does not add the watson report audit to the database.
	 *
	 * @param watsonReportAuditId the primary key for the new watson report audit
	 * @return the new watson report audit
	 */
	public WatsonReportAudit create(long watsonReportAuditId);

	/**
	 * Removes the watson report audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonReportAuditId the primary key of the watson report audit
	 * @return the watson report audit that was removed
	 * @throws NoSuchReportAuditException if a watson report audit with the primary key could not be found
	 */
	public WatsonReportAudit remove(long watsonReportAuditId)
		throws NoSuchReportAuditException;

	public WatsonReportAudit updateImpl(WatsonReportAudit watsonReportAudit);

	/**
	 * Returns the watson report audit with the primary key or throws a <code>NoSuchReportAuditException</code> if it could not be found.
	 *
	 * @param watsonReportAuditId the primary key of the watson report audit
	 * @return the watson report audit
	 * @throws NoSuchReportAuditException if a watson report audit with the primary key could not be found
	 */
	public WatsonReportAudit findByPrimaryKey(long watsonReportAuditId)
		throws NoSuchReportAuditException;

	/**
	 * Returns the watson report audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonReportAuditId the primary key of the watson report audit
	 * @return the watson report audit, or <code>null</code> if a watson report audit with the primary key could not be found
	 */
	public WatsonReportAudit fetchByPrimaryKey(long watsonReportAuditId);

	/**
	 * Returns all the watson report audits.
	 *
	 * @return the watson report audits
	 */
	public java.util.List<WatsonReportAudit> findAll();

	/**
	 * Returns a range of all the watson report audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonReportAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson report audits
	 * @param end the upper bound of the range of watson report audits (not inclusive)
	 * @return the range of watson report audits
	 */
	public java.util.List<WatsonReportAudit> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the watson report audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonReportAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson report audits
	 * @param end the upper bound of the range of watson report audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson report audits
	 */
	public java.util.List<WatsonReportAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonReportAudit>
			orderByComparator);

	/**
	 * Returns an ordered range of all the watson report audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonReportAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson report audits
	 * @param end the upper bound of the range of watson report audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson report audits
	 */
	public java.util.List<WatsonReportAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonReportAudit>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the watson report audits from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of watson report audits.
	 *
	 * @return the number of watson report audits
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}