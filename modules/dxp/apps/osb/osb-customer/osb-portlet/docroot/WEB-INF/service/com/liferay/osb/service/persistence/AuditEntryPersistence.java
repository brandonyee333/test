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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchAuditEntryException;
import com.liferay.osb.model.AuditEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.util.Date;

/**
 * The persistence interface for the audit entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.AuditEntryPersistenceImpl
 * @see AuditEntryUtil
 * @generated
 */
@ProviderType
public interface AuditEntryPersistence extends BasePersistence<AuditEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AuditEntryUtil} to access the audit entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the audit entries where createDate &gt; &#63; and classNameId = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @return the matching audit entries
	*/
	public java.util.List<AuditEntry> findByGtCD_C(Date createDate,
		long classNameId);

	/**
	* Returns a range of all the audit entries where createDate &gt; &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByGtCD_C(Date createDate,
		long classNameId, int start, int end);

	/**
	* Returns an ordered range of all the audit entries where createDate &gt; &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByGtCD_C(Date createDate,
		long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns an ordered range of all the audit entries where createDate &gt; &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByGtCD_C(Date createDate,
		long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByGtCD_C_First(Date createDate, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	* Returns the first audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByGtCD_C_First(Date createDate, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the last audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByGtCD_C_Last(Date createDate, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	* Returns the last audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByGtCD_C_Last(Date createDate, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	*
	* @param auditEntryId the primary key of the current audit entry
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	*/
	public AuditEntry[] findByGtCD_C_PrevAndNext(long auditEntryId,
		Date createDate, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	* Removes all the audit entries where createDate &gt; &#63; and classNameId = &#63; from the database.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	*/
	public void removeByGtCD_C(Date createDate, long classNameId);

	/**
	* Returns the number of audit entries where createDate &gt; &#63; and classNameId = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @return the number of matching audit entries
	*/
	public int countByGtCD_C(Date createDate, long classNameId);

	/**
	* Returns all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibility the visibility
	* @return the matching audit entries
	*/
	public java.util.List<AuditEntry> findByC_C_V(long classNameId,
		long classPK, int visibility);

	/**
	* Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibility the visibility
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByC_C_V(long classNameId,
		long classPK, int visibility, int start, int end);

	/**
	* Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibility the visibility
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByC_C_V(long classNameId,
		long classPK, int visibility, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibility the visibility
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByC_C_V(long classNameId,
		long classPK, int visibility, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByC_C_V_First(long classNameId, long classPK,
		int visibility,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	* Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByC_C_V_First(long classNameId, long classPK,
		int visibility,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByC_C_V_Last(long classNameId, long classPK,
		int visibility,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	* Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByC_C_V_Last(long classNameId, long classPK,
		int visibility,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* @param auditEntryId the primary key of the current audit entry
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	*/
	public AuditEntry[] findByC_C_V_PrevAndNext(long auditEntryId,
		long classNameId, long classPK, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	* Returns all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibilities the visibilities
	* @return the matching audit entries
	*/
	public java.util.List<AuditEntry> findByC_C_V(long classNameId,
		long classPK, int[] visibilities);

	/**
	* Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibilities the visibilities
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByC_C_V(long classNameId,
		long classPK, int[] visibilities, int start, int end);

	/**
	* Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibilities the visibilities
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByC_C_V(long classNameId,
		long classPK, int[] visibilities, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibility the visibility
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByC_C_V(long classNameId,
		long classPK, int[] visibilities, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibility the visibility
	*/
	public void removeByC_C_V(long classNameId, long classPK, int visibility);

	/**
	* Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibility the visibility
	* @return the number of matching audit entries
	*/
	public int countByC_C_V(long classNameId, long classPK, int visibility);

	/**
	* Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param visibilities the visibilities
	* @return the number of matching audit entries
	*/
	public int countByC_C_V(long classNameId, long classPK, int[] visibilities);

	/**
	* Returns all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class pk
	* @param field the field
	* @return the matching audit entries
	*/
	public java.util.List<AuditEntry> findByFC_FC_F(long fieldClassNameId,
		long fieldClassPK, int field);

	/**
	* Returns a range of all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class pk
	* @param field the field
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByFC_FC_F(long fieldClassNameId,
		long fieldClassPK, int field, int start, int end);

	/**
	* Returns an ordered range of all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class pk
	* @param field the field
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByFC_FC_F(long fieldClassNameId,
		long fieldClassPK, int field, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns an ordered range of all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class pk
	* @param field the field
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByFC_FC_F(long fieldClassNameId,
		long fieldClassPK, int field, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class pk
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByFC_FC_F_First(long fieldClassNameId,
		long fieldClassPK, int field,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	* Returns the first audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class pk
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByFC_FC_F_First(long fieldClassNameId,
		long fieldClassPK, int field,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the last audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class pk
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByFC_FC_F_Last(long fieldClassNameId,
		long fieldClassPK, int field,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	* Returns the last audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class pk
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByFC_FC_F_Last(long fieldClassNameId,
		long fieldClassPK, int field,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* @param auditEntryId the primary key of the current audit entry
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class pk
	* @param field the field
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	*/
	public AuditEntry[] findByFC_FC_F_PrevAndNext(long auditEntryId,
		long fieldClassNameId, long fieldClassPK, int field,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	* Removes all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63; from the database.
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class pk
	* @param field the field
	*/
	public void removeByFC_FC_F(long fieldClassNameId, long fieldClassPK,
		int field);

	/**
	* Returns the number of audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	*
	* @param fieldClassNameId the field class name ID
	* @param fieldClassPK the field class pk
	* @param field the field
	* @return the number of matching audit entries
	*/
	public int countByFC_FC_F(long fieldClassNameId, long fieldClassPK,
		int field);

	/**
	* Returns all the audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param field the field
	* @param action the action
	* @return the matching audit entries
	*/
	public java.util.List<AuditEntry> findByC_C_F_A(long classNameId,
		long classPK, int field, int action);

	/**
	* Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param field the field
	* @param action the action
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByC_C_F_A(long classNameId,
		long classPK, int field, int action, int start, int end);

	/**
	* Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param field the field
	* @param action the action
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByC_C_F_A(long classNameId,
		long classPK, int field, int action, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param field the field
	* @param action the action
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching audit entries
	*/
	public java.util.List<AuditEntry> findByC_C_F_A(long classNameId,
		long classPK, int field, int action, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param field the field
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByC_C_F_A_First(long classNameId, long classPK,
		int field, int action,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	* Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param field the field
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByC_C_F_A_First(long classNameId, long classPK,
		int field, int action,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param field the field
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry
	* @throws NoSuchAuditEntryException if a matching audit entry could not be found
	*/
	public AuditEntry findByC_C_F_A_Last(long classNameId, long classPK,
		int field, int action,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	* Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param field the field
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	*/
	public AuditEntry fetchByC_C_F_A_Last(long classNameId, long classPK,
		int field, int action,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns the audit entries before and after the current audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* @param auditEntryId the primary key of the current audit entry
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param field the field
	* @param action the action
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next audit entry
	* @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	*/
	public AuditEntry[] findByC_C_F_A_PrevAndNext(long auditEntryId,
		long classNameId, long classPK, int field, int action,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator)
		throws NoSuchAuditEntryException;

	/**
	* Removes all the audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param field the field
	* @param action the action
	*/
	public void removeByC_C_F_A(long classNameId, long classPK, int field,
		int action);

	/**
	* Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and field = &#63; and action = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param field the field
	* @param action the action
	* @return the number of matching audit entries
	*/
	public int countByC_C_F_A(long classNameId, long classPK, int field,
		int action);

	/**
	* Caches the audit entry in the entity cache if it is enabled.
	*
	* @param auditEntry the audit entry
	*/
	public void cacheResult(AuditEntry auditEntry);

	/**
	* Caches the audit entries in the entity cache if it is enabled.
	*
	* @param auditEntries the audit entries
	*/
	public void cacheResult(java.util.List<AuditEntry> auditEntries);

	/**
	* Creates a new audit entry with the primary key. Does not add the audit entry to the database.
	*
	* @param auditEntryId the primary key for the new audit entry
	* @return the new audit entry
	*/
	public AuditEntry create(long auditEntryId);

	/**
	* Removes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param auditEntryId the primary key of the audit entry
	* @return the audit entry that was removed
	* @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	*/
	public AuditEntry remove(long auditEntryId)
		throws NoSuchAuditEntryException;

	public AuditEntry updateImpl(AuditEntry auditEntry);

	/**
	* Returns the audit entry with the primary key or throws a {@link NoSuchAuditEntryException} if it could not be found.
	*
	* @param auditEntryId the primary key of the audit entry
	* @return the audit entry
	* @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	*/
	public AuditEntry findByPrimaryKey(long auditEntryId)
		throws NoSuchAuditEntryException;

	/**
	* Returns the audit entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param auditEntryId the primary key of the audit entry
	* @return the audit entry, or <code>null</code> if a audit entry with the primary key could not be found
	*/
	public AuditEntry fetchByPrimaryKey(long auditEntryId);

	@Override
	public java.util.Map<java.io.Serializable, AuditEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the audit entries.
	*
	* @return the audit entries
	*/
	public java.util.List<AuditEntry> findAll();

	/**
	* Returns a range of all the audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @return the range of audit entries
	*/
	public java.util.List<AuditEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of audit entries
	*/
	public java.util.List<AuditEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator);

	/**
	* Returns an ordered range of all the audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of audit entries
	* @param end the upper bound of the range of audit entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of audit entries
	*/
	public java.util.List<AuditEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AuditEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the audit entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of audit entries.
	*
	* @return the number of audit entries
	*/
	public int countAll();
}