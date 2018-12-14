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

import com.liferay.watson.exception.NoSuchDocumentAuditException;
import com.liferay.watson.model.WatsonDocumentAudit;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson document audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see com.liferay.watson.service.persistence.impl.WatsonDocumentAuditPersistenceImpl
 * @see WatsonDocumentAuditUtil
 * @generated
 */
@ProviderType
public interface WatsonDocumentAuditPersistence extends BasePersistence<WatsonDocumentAudit> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonDocumentAuditUtil} to access the watson document audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonDocumentAudit> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	* Caches the watson document audit in the entity cache if it is enabled.
	*
	* @param watsonDocumentAudit the watson document audit
	*/
	public void cacheResult(WatsonDocumentAudit watsonDocumentAudit);

	/**
	* Caches the watson document audits in the entity cache if it is enabled.
	*
	* @param watsonDocumentAudits the watson document audits
	*/
	public void cacheResult(
		java.util.List<WatsonDocumentAudit> watsonDocumentAudits);

	/**
	* Creates a new watson document audit with the primary key. Does not add the watson document audit to the database.
	*
	* @param watsonDocumentAuditId the primary key for the new watson document audit
	* @return the new watson document audit
	*/
	public WatsonDocumentAudit create(long watsonDocumentAuditId);

	/**
	* Removes the watson document audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonDocumentAuditId the primary key of the watson document audit
	* @return the watson document audit that was removed
	* @throws NoSuchDocumentAuditException if a watson document audit with the primary key could not be found
	*/
	public WatsonDocumentAudit remove(long watsonDocumentAuditId)
		throws NoSuchDocumentAuditException;

	public WatsonDocumentAudit updateImpl(
		WatsonDocumentAudit watsonDocumentAudit);

	/**
	* Returns the watson document audit with the primary key or throws a {@link NoSuchDocumentAuditException} if it could not be found.
	*
	* @param watsonDocumentAuditId the primary key of the watson document audit
	* @return the watson document audit
	* @throws NoSuchDocumentAuditException if a watson document audit with the primary key could not be found
	*/
	public WatsonDocumentAudit findByPrimaryKey(long watsonDocumentAuditId)
		throws NoSuchDocumentAuditException;

	/**
	* Returns the watson document audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonDocumentAuditId the primary key of the watson document audit
	* @return the watson document audit, or <code>null</code> if a watson document audit with the primary key could not be found
	*/
	public WatsonDocumentAudit fetchByPrimaryKey(long watsonDocumentAuditId);

	/**
	* Returns all the watson document audits.
	*
	* @return the watson document audits
	*/
	public java.util.List<WatsonDocumentAudit> findAll();

	/**
	* Returns a range of all the watson document audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonDocumentAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson document audits
	* @param end the upper bound of the range of watson document audits (not inclusive)
	* @return the range of watson document audits
	*/
	public java.util.List<WatsonDocumentAudit> findAll(int start, int end);

	/**
	* Returns an ordered range of all the watson document audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonDocumentAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson document audits
	* @param end the upper bound of the range of watson document audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson document audits
	*/
	public java.util.List<WatsonDocumentAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonDocumentAudit> orderByComparator);

	/**
	* Returns an ordered range of all the watson document audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonDocumentAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson document audits
	* @param end the upper bound of the range of watson document audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson document audits
	*/
	public java.util.List<WatsonDocumentAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonDocumentAudit> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson document audits from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson document audits.
	*
	* @return the number of watson document audits
	*/
	public int countAll();
}