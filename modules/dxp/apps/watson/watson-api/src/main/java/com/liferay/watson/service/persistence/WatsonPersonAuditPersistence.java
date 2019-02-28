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
import com.liferay.watson.exception.NoSuchPersonAuditException;
import com.liferay.watson.model.WatsonPersonAudit;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson person audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonPersonAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonPersonAuditPersistence
	extends BasePersistence<WatsonPersonAudit> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonPersonAuditUtil} to access the watson person audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonPersonAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the watson person audit in the entity cache if it is enabled.
	 *
	 * @param watsonPersonAudit the watson person audit
	 */
	public void cacheResult(WatsonPersonAudit watsonPersonAudit);

	/**
	 * Caches the watson person audits in the entity cache if it is enabled.
	 *
	 * @param watsonPersonAudits the watson person audits
	 */
	public void cacheResult(
		java.util.List<WatsonPersonAudit> watsonPersonAudits);

	/**
	 * Creates a new watson person audit with the primary key. Does not add the watson person audit to the database.
	 *
	 * @param watsonPersonAuditId the primary key for the new watson person audit
	 * @return the new watson person audit
	 */
	public WatsonPersonAudit create(long watsonPersonAuditId);

	/**
	 * Removes the watson person audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPersonAuditId the primary key of the watson person audit
	 * @return the watson person audit that was removed
	 * @throws NoSuchPersonAuditException if a watson person audit with the primary key could not be found
	 */
	public WatsonPersonAudit remove(long watsonPersonAuditId)
		throws NoSuchPersonAuditException;

	public WatsonPersonAudit updateImpl(WatsonPersonAudit watsonPersonAudit);

	/**
	 * Returns the watson person audit with the primary key or throws a <code>NoSuchPersonAuditException</code> if it could not be found.
	 *
	 * @param watsonPersonAuditId the primary key of the watson person audit
	 * @return the watson person audit
	 * @throws NoSuchPersonAuditException if a watson person audit with the primary key could not be found
	 */
	public WatsonPersonAudit findByPrimaryKey(long watsonPersonAuditId)
		throws NoSuchPersonAuditException;

	/**
	 * Returns the watson person audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonPersonAuditId the primary key of the watson person audit
	 * @return the watson person audit, or <code>null</code> if a watson person audit with the primary key could not be found
	 */
	public WatsonPersonAudit fetchByPrimaryKey(long watsonPersonAuditId);

	/**
	 * Returns all the watson person audits.
	 *
	 * @return the watson person audits
	 */
	public java.util.List<WatsonPersonAudit> findAll();

	/**
	 * Returns a range of all the watson person audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonPersonAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson person audits
	 * @param end the upper bound of the range of watson person audits (not inclusive)
	 * @return the range of watson person audits
	 */
	public java.util.List<WatsonPersonAudit> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the watson person audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonPersonAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson person audits
	 * @param end the upper bound of the range of watson person audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson person audits
	 */
	public java.util.List<WatsonPersonAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonPersonAudit>
			orderByComparator);

	/**
	 * Returns an ordered range of all the watson person audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonPersonAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson person audits
	 * @param end the upper bound of the range of watson person audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson person audits
	 */
	public java.util.List<WatsonPersonAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonPersonAudit>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the watson person audits from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of watson person audits.
	 *
	 * @return the number of watson person audits
	 */
	public int countAll();

}