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
import com.liferay.watson.exception.NoSuchListTypeAuditException;
import com.liferay.watson.model.WatsonListTypeAudit;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson list type audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonListTypeAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonListTypeAuditPersistence
	extends BasePersistence<WatsonListTypeAudit> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonListTypeAuditUtil} to access the watson list type audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonListTypeAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the watson list type audit in the entity cache if it is enabled.
	 *
	 * @param watsonListTypeAudit the watson list type audit
	 */
	public void cacheResult(WatsonListTypeAudit watsonListTypeAudit);

	/**
	 * Caches the watson list type audits in the entity cache if it is enabled.
	 *
	 * @param watsonListTypeAudits the watson list type audits
	 */
	public void cacheResult(
		java.util.List<WatsonListTypeAudit> watsonListTypeAudits);

	/**
	 * Creates a new watson list type audit with the primary key. Does not add the watson list type audit to the database.
	 *
	 * @param watsonListTypeAuditId the primary key for the new watson list type audit
	 * @return the new watson list type audit
	 */
	public WatsonListTypeAudit create(long watsonListTypeAuditId);

	/**
	 * Removes the watson list type audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeAuditId the primary key of the watson list type audit
	 * @return the watson list type audit that was removed
	 * @throws NoSuchListTypeAuditException if a watson list type audit with the primary key could not be found
	 */
	public WatsonListTypeAudit remove(long watsonListTypeAuditId)
		throws NoSuchListTypeAuditException;

	public WatsonListTypeAudit updateImpl(
		WatsonListTypeAudit watsonListTypeAudit);

	/**
	 * Returns the watson list type audit with the primary key or throws a <code>NoSuchListTypeAuditException</code> if it could not be found.
	 *
	 * @param watsonListTypeAuditId the primary key of the watson list type audit
	 * @return the watson list type audit
	 * @throws NoSuchListTypeAuditException if a watson list type audit with the primary key could not be found
	 */
	public WatsonListTypeAudit findByPrimaryKey(long watsonListTypeAuditId)
		throws NoSuchListTypeAuditException;

	/**
	 * Returns the watson list type audit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonListTypeAuditId the primary key of the watson list type audit
	 * @return the watson list type audit, or <code>null</code> if a watson list type audit with the primary key could not be found
	 */
	public WatsonListTypeAudit fetchByPrimaryKey(long watsonListTypeAuditId);

	/**
	 * Returns all the watson list type audits.
	 *
	 * @return the watson list type audits
	 */
	public java.util.List<WatsonListTypeAudit> findAll();

	/**
	 * Returns a range of all the watson list type audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type audits
	 * @param end the upper bound of the range of watson list type audits (not inclusive)
	 * @return the range of watson list type audits
	 */
	public java.util.List<WatsonListTypeAudit> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the watson list type audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type audits
	 * @param end the upper bound of the range of watson list type audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson list type audits
	 */
	public java.util.List<WatsonListTypeAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonListTypeAudit>
			orderByComparator);

	/**
	 * Returns an ordered range of all the watson list type audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonListTypeAuditModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type audits
	 * @param end the upper bound of the range of watson list type audits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson list type audits
	 */
	public java.util.List<WatsonListTypeAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonListTypeAudit>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the watson list type audits from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of watson list type audits.
	 *
	 * @return the number of watson list type audits
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}