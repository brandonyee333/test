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

package com.liferay.osb.customer.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.exception.NoSuchAuditFormException;
import com.liferay.osb.customer.model.AuditForm;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the audit form service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.customer.service.persistence.impl.AuditFormPersistenceImpl
 * @see AuditFormUtil
 * @generated
 */
@ProviderType
public interface AuditFormPersistence extends BasePersistence<AuditForm> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AuditFormUtil} to access the audit form persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the audit form in the entity cache if it is enabled.
	*
	* @param auditForm the audit form
	*/
	public void cacheResult(AuditForm auditForm);

	/**
	* Caches the audit forms in the entity cache if it is enabled.
	*
	* @param auditForms the audit forms
	*/
	public void cacheResult(java.util.List<AuditForm> auditForms);

	/**
	* Creates a new audit form with the primary key. Does not add the audit form to the database.
	*
	* @param auditFormId the primary key for the new audit form
	* @return the new audit form
	*/
	public AuditForm create(long auditFormId);

	/**
	* Removes the audit form with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditFormId the primary key of the audit form
	* @return the audit form that was removed
	* @throws NoSuchAuditFormException if a audit form with the primary key could not be found
	*/
	public AuditForm remove(long auditFormId) throws NoSuchAuditFormException;

	public AuditForm updateImpl(AuditForm auditForm);

	/**
	* Returns the audit form with the primary key or throws a {@link NoSuchAuditFormException} if it could not be found.
	*
	* @param auditFormId the primary key of the audit form
	* @return the audit form
	* @throws NoSuchAuditFormException if a audit form with the primary key could not be found
	*/
	public AuditForm findByPrimaryKey(long auditFormId)
		throws NoSuchAuditFormException;

	/**
	* Returns the audit form with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditFormId the primary key of the audit form
	* @return the audit form, or <code>null</code> if a audit form with the primary key could not be found
	*/
	public AuditForm fetchByPrimaryKey(long auditFormId);

	@Override
	public java.util.Map<java.io.Serializable, AuditForm> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the audit forms.
	*
	* @return the audit forms
	*/
	public java.util.List<AuditForm> findAll();

	/**
	* Returns a range of all the audit forms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit forms
	* @param end the upper bound of the range of audit forms (not inclusive)
	* @return the range of audit forms
	*/
	public java.util.List<AuditForm> findAll(int start, int end);

	/**
	* Returns an ordered range of all the audit forms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit forms
	* @param end the upper bound of the range of audit forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of audit forms
	*/
	public java.util.List<AuditForm> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditForm> orderByComparator);

	/**
	* Returns an ordered range of all the audit forms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit forms
	* @param end the upper bound of the range of audit forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of audit forms
	*/
	public java.util.List<AuditForm> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditForm> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the audit forms from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of audit forms.
	*
	* @return the number of audit forms
	*/
	public int countAll();
}