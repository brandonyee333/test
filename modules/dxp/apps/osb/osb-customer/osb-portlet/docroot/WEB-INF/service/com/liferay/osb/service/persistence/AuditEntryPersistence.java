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

package com.liferay.osb.service.persistence;

import com.liferay.osb.model.AuditEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the audit entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryPersistenceImpl
 * @see AuditEntryUtil
 * @generated
 */
public interface AuditEntryPersistence extends BasePersistence<AuditEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AuditEntryUtil} to access the audit entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the audit entry in the entity cache if it is enabled.
	*
	* @param auditEntry the audit entry
	*/
	public void cacheResult(com.liferay.osb.model.AuditEntry auditEntry);

	/**
	* Caches the audit entries in the entity cache if it is enabled.
	*
	* @param auditEntries the audit entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AuditEntry> auditEntries);

	/**
	* Creates a new audit entry with the primary key. Does not add the audit entry to the database.
	*
	* @param auditEntryId the primary key for the new audit entry
	* @return the new audit entry
	*/
	public com.liferay.osb.model.AuditEntry create(long auditEntryId);

	/**
	* Removes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditEntryId the primary key of the audit entry
	* @return the audit entry that was removed
	* @throws com.liferay.osb.NoSuchAuditEntryException if a audit entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry remove(long auditEntryId)
		throws com.liferay.osb.NoSuchAuditEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AuditEntry updateImpl(
		com.liferay.osb.model.AuditEntry auditEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the audit entry with the primary key or throws a {@link com.liferay.osb.NoSuchAuditEntryException} if it could not be found.
	*
	* @param auditEntryId the primary key of the audit entry
	* @return the audit entry
	* @throws com.liferay.osb.NoSuchAuditEntryException if a audit entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry findByPrimaryKey(long auditEntryId)
		throws com.liferay.osb.NoSuchAuditEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the audit entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditEntryId the primary key of the audit entry
	* @return the audit entry, or <code>null</code> if a audit entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry fetchByPrimaryKey(long auditEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the audit entries where createDate &gt; &#63; and classNameId = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @return the matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByGtCD_C(
		java.util.Date createDate, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the audit entries where createDate &gt; &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByGtCD_C(
		java.util.Date createDate, long classNameId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the audit entries where createDate &gt; &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByGtCD_C(
		java.util.Date createDate, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry findByGtCD_C_First(
		java.util.Date createDate, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry fetchByGtCD_C_First(
		java.util.Date createDate, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry findByGtCD_C_Last(
		java.util.Date createDate, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry fetchByGtCD_C_Last(
		java.util.Date createDate, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	*
	* @param auditEntryId the primary key of the current audit entry
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws com.liferay.osb.NoSuchAuditEntryException if a audit entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry[] findByGtCD_C_PrevAndNext(
		long auditEntryId, java.util.Date createDate, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param visibility the visibility
	* @return the matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByC_C_V(
		long classNameId, long classPK, int visibility)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param visibility the visibility
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByC_C_V(
		long classNameId, long classPK, int visibility, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param visibility the visibility
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByC_C_V(
		long classNameId, long classPK, int visibility, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry findByC_C_V_First(
		long classNameId, long classPK, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry fetchByC_C_V_First(
		long classNameId, long classPK, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry findByC_C_V_Last(long classNameId,
		long classPK, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry fetchByC_C_V_Last(
		long classNameId, long classPK, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* @param auditEntryId the primary key of the current audit entry
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws com.liferay.osb.NoSuchAuditEntryException if a audit entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry[] findByC_C_V_PrevAndNext(
		long auditEntryId, long classNameId, long classPK, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param visibilities the visibilities
	* @return the matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByC_C_V(
		long classNameId, long classPK, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param visibilities the visibilities
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByC_C_V(
		long classNameId, long classPK, int[] visibilities, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param visibilities the visibilities
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByC_C_V(
		long classNameId, long classPK, int[] visibilities, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class p k
	* @param field the field
	* @return the matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByFC_FC_F(
		long fieldClassNameId, long fieldClassPK, int field)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class p k
	* @param field the field
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByFC_FC_F(
		long fieldClassNameId, long fieldClassPK, int field, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class p k
	* @param field the field
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByFC_FC_F(
		long fieldClassNameId, long fieldClassPK, int field, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class p k
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry findByFC_FC_F_First(
		long fieldClassNameId, long fieldClassPK, int field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class p k
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry fetchByFC_FC_F_First(
		long fieldClassNameId, long fieldClassPK, int field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class p k
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry findByFC_FC_F_Last(
		long fieldClassNameId, long fieldClassPK, int field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class p k
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry fetchByFC_FC_F_Last(
		long fieldClassNameId, long fieldClassPK, int field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* @param auditEntryId the primary key of the current audit entry
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class p k
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws com.liferay.osb.NoSuchAuditEntryException if a audit entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry[] findByFC_FC_F_PrevAndNext(
		long auditEntryId, long fieldClassNameId, long fieldClassPK, int field,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param field the field
	* @param action the action
	* @return the matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByC_C_F_A(
		long classNameId, long classPK, int field, int action)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param field the field
	* @param action the action
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByC_C_F_A(
		long classNameId, long classPK, int field, int action, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param field the field
	* @param action the action
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findByC_C_F_A(
		long classNameId, long classPK, int field, int action, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param field the field
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry findByC_C_F_A_First(
		long classNameId, long classPK, int field, int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param field the field
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry fetchByC_C_F_A_First(
		long classNameId, long classPK, int field, int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param field the field
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws com.liferay.osb.NoSuchAuditEntryException if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry findByC_C_F_A_Last(
		long classNameId, long classPK, int field, int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param field the field
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry fetchByC_C_F_A_Last(
		long classNameId, long classPK, int field, int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* @param auditEntryId the primary key of the current audit entry
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param field the field
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws com.liferay.osb.NoSuchAuditEntryException if a audit entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AuditEntry[] findByC_C_F_A_PrevAndNext(
		long auditEntryId, long classNameId, long classPK, int field,
		int action,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAuditEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the audit entries.
	*
	* @return the audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of audit entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AuditEntry> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the audit entries where createDate &gt; &#63; and classNameId = &#63; from the database.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGtCD_C(java.util.Date createDate, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param visibility the visibility
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C_V(long classNameId, long classPK, int visibility)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63; from the database.
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class p k
	* @param field the field
	* @throws SystemException if a system exception occurred
	*/
	public void removeByFC_FC_F(long fieldClassNameId, long fieldClassPK,
		int field) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param field the field
	* @param action the action
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C_F_A(long classNameId, long classPK, int field,
		int action) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the audit entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of audit entries where createDate &gt; &#63; and classNameId = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @return the number of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByGtCD_C(java.util.Date createDate, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param visibility the visibility
	* @return the number of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_V(long classNameId, long classPK, int visibility)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param visibilities the visibilities
	* @return the number of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_V(long classNameId, long classPK, int[] visibilities)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class p k
	* @param field the field
	* @return the number of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByFC_FC_F(long fieldClassNameId, long fieldClassPK,
		int field) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param field the field
	* @param action the action
	* @return the number of matching audit entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_F_A(long classNameId, long classPK, int field,
		int action) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of audit entries.
	*
	* @return the number of audit entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}