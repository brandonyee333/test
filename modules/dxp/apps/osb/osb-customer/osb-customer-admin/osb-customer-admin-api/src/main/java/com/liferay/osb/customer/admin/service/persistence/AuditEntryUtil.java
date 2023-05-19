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

package com.liferay.osb.customer.admin.service.persistence;

import com.liferay.osb.customer.admin.model.AuditEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the audit entry service. This utility wraps <code>com.liferay.osb.customer.admin.service.persistence.impl.AuditEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryPersistence
 * @generated
 */
public class AuditEntryUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(AuditEntry auditEntry) {
		getPersistence().clearCache(auditEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, AuditEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AuditEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AuditEntry update(AuditEntry auditEntry) {
		return getPersistence().update(auditEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AuditEntry update(
		AuditEntry auditEntry, ServiceContext serviceContext) {

		return getPersistence().update(auditEntry, serviceContext);
	}

	/**
	 * Returns all the audit entries where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @return the matching audit entries
	 */
	public static List<AuditEntry> findByGtCD_C(
		Date createDate, long classNameId) {

		return getPersistence().findByGtCD_C(createDate, classNameId);
	}

	/**
	 * Returns a range of all the audit entries where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of matching audit entries
	 */
	public static List<AuditEntry> findByGtCD_C(
		Date createDate, long classNameId, int start, int end) {

		return getPersistence().findByGtCD_C(
			createDate, classNameId, start, end);
	}

	/**
	 * Returns an ordered range of all the audit entries where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit entries
	 */
	public static List<AuditEntry> findByGtCD_C(
		Date createDate, long classNameId, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().findByGtCD_C(
			createDate, classNameId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit entries where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching audit entries
	 */
	public static List<AuditEntry> findByGtCD_C(
		Date createDate, long classNameId, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGtCD_C(
			createDate, classNameId, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	public static AuditEntry findByGtCD_C_First(
			Date createDate, long classNameId,
			OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByGtCD_C_First(
			createDate, classNameId, orderByComparator);
	}

	/**
	 * Returns the first audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public static AuditEntry fetchByGtCD_C_First(
		Date createDate, long classNameId,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().fetchByGtCD_C_First(
			createDate, classNameId, orderByComparator);
	}

	/**
	 * Returns the last audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	public static AuditEntry findByGtCD_C_Last(
			Date createDate, long classNameId,
			OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByGtCD_C_Last(
			createDate, classNameId, orderByComparator);
	}

	/**
	 * Returns the last audit entry in the ordered set where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public static AuditEntry fetchByGtCD_C_Last(
		Date createDate, long classNameId,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().fetchByGtCD_C_Last(
			createDate, classNameId, orderByComparator);
	}

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
	public static AuditEntry[] findByGtCD_C_PrevAndNext(
			long auditEntryId, Date createDate, long classNameId,
			OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByGtCD_C_PrevAndNext(
			auditEntryId, createDate, classNameId, orderByComparator);
	}

	/**
	 * Removes all the audit entries where createDate &gt; &#63; and classNameId = &#63; from the database.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 */
	public static void removeByGtCD_C(Date createDate, long classNameId) {
		getPersistence().removeByGtCD_C(createDate, classNameId);
	}

	/**
	 * Returns the number of audit entries where createDate &gt; &#63; and classNameId = &#63;.
	 *
	 * @param createDate the create date
	 * @param classNameId the class name ID
	 * @return the number of matching audit entries
	 */
	public static int countByGtCD_C(Date createDate, long classNameId) {
		return getPersistence().countByGtCD_C(createDate, classNameId);
	}

	/**
	 * Returns all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param visibility the visibility
	 * @return the matching audit entries
	 */
	public static List<AuditEntry> findByC_C_V(
		long classNameId, long classPK, int visibility) {

		return getPersistence().findByC_C_V(classNameId, classPK, visibility);
	}

	/**
	 * Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param visibility the visibility
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of matching audit entries
	 */
	public static List<AuditEntry> findByC_C_V(
		long classNameId, long classPK, int visibility, int start, int end) {

		return getPersistence().findByC_C_V(
			classNameId, classPK, visibility, start, end);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
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
	public static List<AuditEntry> findByC_C_V(
		long classNameId, long classPK, int visibility, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().findByC_C_V(
			classNameId, classPK, visibility, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param visibility the visibility
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching audit entries
	 */
	public static List<AuditEntry> findByC_C_V(
		long classNameId, long classPK, int visibility, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C_V(
			classNameId, classPK, visibility, start, end, orderByComparator,
			useFinderCache);
	}

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
	public static AuditEntry findByC_C_V_First(
			long classNameId, long classPK, int visibility,
			OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByC_C_V_First(
			classNameId, classPK, visibility, orderByComparator);
	}

	/**
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param visibility the visibility
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public static AuditEntry fetchByC_C_V_First(
		long classNameId, long classPK, int visibility,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().fetchByC_C_V_First(
			classNameId, classPK, visibility, orderByComparator);
	}

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
	public static AuditEntry findByC_C_V_Last(
			long classNameId, long classPK, int visibility,
			OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByC_C_V_Last(
			classNameId, classPK, visibility, orderByComparator);
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param visibility the visibility
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public static AuditEntry fetchByC_C_V_Last(
		long classNameId, long classPK, int visibility,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().fetchByC_C_V_Last(
			classNameId, classPK, visibility, orderByComparator);
	}

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
	public static AuditEntry[] findByC_C_V_PrevAndNext(
			long auditEntryId, long classNameId, long classPK, int visibility,
			OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByC_C_V_PrevAndNext(
			auditEntryId, classNameId, classPK, visibility, orderByComparator);
	}

	/**
	 * Returns all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param visibilities the visibilities
	 * @return the matching audit entries
	 */
	public static List<AuditEntry> findByC_C_V(
		long classNameId, long classPK, int[] visibilities) {

		return getPersistence().findByC_C_V(classNameId, classPK, visibilities);
	}

	/**
	 * Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param visibilities the visibilities
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of matching audit entries
	 */
	public static List<AuditEntry> findByC_C_V(
		long classNameId, long classPK, int[] visibilities, int start,
		int end) {

		return getPersistence().findByC_C_V(
			classNameId, classPK, visibilities, start, end);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
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
	public static List<AuditEntry> findByC_C_V(
		long classNameId, long classPK, int[] visibilities, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().findByC_C_V(
			classNameId, classPK, visibilities, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param visibilities the visibilities
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching audit entries
	 */
	public static List<AuditEntry> findByC_C_V(
		long classNameId, long classPK, int[] visibilities, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C_V(
			classNameId, classPK, visibilities, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Removes all the audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param visibility the visibility
	 */
	public static void removeByC_C_V(
		long classNameId, long classPK, int visibility) {

		getPersistence().removeByC_C_V(classNameId, classPK, visibility);
	}

	/**
	 * Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and visibility = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param visibility the visibility
	 * @return the number of matching audit entries
	 */
	public static int countByC_C_V(
		long classNameId, long classPK, int visibility) {

		return getPersistence().countByC_C_V(classNameId, classPK, visibility);
	}

	/**
	 * Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and visibility = any &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param visibilities the visibilities
	 * @return the number of matching audit entries
	 */
	public static int countByC_C_V(
		long classNameId, long classPK, int[] visibilities) {

		return getPersistence().countByC_C_V(
			classNameId, classPK, visibilities);
	}

	/**
	 * Returns all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param field the field
	 * @return the matching audit entries
	 */
	public static List<AuditEntry> findByFC_FC_F(
		long fieldClassNameId, long fieldClassPK, int field) {

		return getPersistence().findByFC_FC_F(
			fieldClassNameId, fieldClassPK, field);
	}

	/**
	 * Returns a range of all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param field the field
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of matching audit entries
	 */
	public static List<AuditEntry> findByFC_FC_F(
		long fieldClassNameId, long fieldClassPK, int field, int start,
		int end) {

		return getPersistence().findByFC_FC_F(
			fieldClassNameId, fieldClassPK, field, start, end);
	}

	/**
	 * Returns an ordered range of all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
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
	public static List<AuditEntry> findByFC_FC_F(
		long fieldClassNameId, long fieldClassPK, int field, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().findByFC_FC_F(
			fieldClassNameId, fieldClassPK, field, start, end,
			orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param field the field
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching audit entries
	 */
	public static List<AuditEntry> findByFC_FC_F(
		long fieldClassNameId, long fieldClassPK, int field, int start, int end,
		OrderByComparator<AuditEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByFC_FC_F(
			fieldClassNameId, fieldClassPK, field, start, end,
			orderByComparator, useFinderCache);
	}

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
	public static AuditEntry findByFC_FC_F_First(
			long fieldClassNameId, long fieldClassPK, int field,
			OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByFC_FC_F_First(
			fieldClassNameId, fieldClassPK, field, orderByComparator);
	}

	/**
	 * Returns the first audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public static AuditEntry fetchByFC_FC_F_First(
		long fieldClassNameId, long fieldClassPK, int field,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().fetchByFC_FC_F_First(
			fieldClassNameId, fieldClassPK, field, orderByComparator);
	}

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
	public static AuditEntry findByFC_FC_F_Last(
			long fieldClassNameId, long fieldClassPK, int field,
			OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByFC_FC_F_Last(
			fieldClassNameId, fieldClassPK, field, orderByComparator);
	}

	/**
	 * Returns the last audit entry in the ordered set where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public static AuditEntry fetchByFC_FC_F_Last(
		long fieldClassNameId, long fieldClassPK, int field,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().fetchByFC_FC_F_Last(
			fieldClassNameId, fieldClassPK, field, orderByComparator);
	}

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
	public static AuditEntry[] findByFC_FC_F_PrevAndNext(
			long auditEntryId, long fieldClassNameId, long fieldClassPK,
			int field, OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByFC_FC_F_PrevAndNext(
			auditEntryId, fieldClassNameId, fieldClassPK, field,
			orderByComparator);
	}

	/**
	 * Removes all the audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63; from the database.
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param field the field
	 */
	public static void removeByFC_FC_F(
		long fieldClassNameId, long fieldClassPK, int field) {

		getPersistence().removeByFC_FC_F(fieldClassNameId, fieldClassPK, field);
	}

	/**
	 * Returns the number of audit entries where fieldClassNameId = &#63; and fieldClassPK = &#63; and field = &#63;.
	 *
	 * @param fieldClassNameId the field class name ID
	 * @param fieldClassPK the field class pk
	 * @param field the field
	 * @return the number of matching audit entries
	 */
	public static int countByFC_FC_F(
		long fieldClassNameId, long fieldClassPK, int field) {

		return getPersistence().countByFC_FC_F(
			fieldClassNameId, fieldClassPK, field);
	}

	/**
	 * Returns all the audit entries where classNameId = &#63; and classPK = &#63; and action = &#63; and field = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param action the action
	 * @param field the field
	 * @return the matching audit entries
	 */
	public static List<AuditEntry> findByC_C_A_F(
		long classNameId, long classPK, int action, int field) {

		return getPersistence().findByC_C_A_F(
			classNameId, classPK, action, field);
	}

	/**
	 * Returns a range of all the audit entries where classNameId = &#63; and classPK = &#63; and action = &#63; and field = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param action the action
	 * @param field the field
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of matching audit entries
	 */
	public static List<AuditEntry> findByC_C_A_F(
		long classNameId, long classPK, int action, int field, int start,
		int end) {

		return getPersistence().findByC_C_A_F(
			classNameId, classPK, action, field, start, end);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and action = &#63; and field = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param action the action
	 * @param field the field
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching audit entries
	 */
	public static List<AuditEntry> findByC_C_A_F(
		long classNameId, long classPK, int action, int field, int start,
		int end, OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().findByC_C_A_F(
			classNameId, classPK, action, field, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit entries where classNameId = &#63; and classPK = &#63; and action = &#63; and field = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param action the action
	 * @param field the field
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching audit entries
	 */
	public static List<AuditEntry> findByC_C_A_F(
		long classNameId, long classPK, int action, int field, int start,
		int end, OrderByComparator<AuditEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByC_C_A_F(
			classNameId, classPK, action, field, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and action = &#63; and field = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param action the action
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	public static AuditEntry findByC_C_A_F_First(
			long classNameId, long classPK, int action, int field,
			OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByC_C_A_F_First(
			classNameId, classPK, action, field, orderByComparator);
	}

	/**
	 * Returns the first audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and action = &#63; and field = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param action the action
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public static AuditEntry fetchByC_C_A_F_First(
		long classNameId, long classPK, int action, int field,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().fetchByC_C_A_F_First(
			classNameId, classPK, action, field, orderByComparator);
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and action = &#63; and field = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param action the action
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry
	 * @throws NoSuchAuditEntryException if a matching audit entry could not be found
	 */
	public static AuditEntry findByC_C_A_F_Last(
			long classNameId, long classPK, int action, int field,
			OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByC_C_A_F_Last(
			classNameId, classPK, action, field, orderByComparator);
	}

	/**
	 * Returns the last audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and action = &#63; and field = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param action the action
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching audit entry, or <code>null</code> if a matching audit entry could not be found
	 */
	public static AuditEntry fetchByC_C_A_F_Last(
		long classNameId, long classPK, int action, int field,
		OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().fetchByC_C_A_F_Last(
			classNameId, classPK, action, field, orderByComparator);
	}

	/**
	 * Returns the audit entries before and after the current audit entry in the ordered set where classNameId = &#63; and classPK = &#63; and action = &#63; and field = &#63;.
	 *
	 * @param auditEntryId the primary key of the current audit entry
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param action the action
	 * @param field the field
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next audit entry
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	public static AuditEntry[] findByC_C_A_F_PrevAndNext(
			long auditEntryId, long classNameId, long classPK, int action,
			int field, OrderByComparator<AuditEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByC_C_A_F_PrevAndNext(
			auditEntryId, classNameId, classPK, action, field,
			orderByComparator);
	}

	/**
	 * Removes all the audit entries where classNameId = &#63; and classPK = &#63; and action = &#63; and field = &#63; from the database.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param action the action
	 * @param field the field
	 */
	public static void removeByC_C_A_F(
		long classNameId, long classPK, int action, int field) {

		getPersistence().removeByC_C_A_F(classNameId, classPK, action, field);
	}

	/**
	 * Returns the number of audit entries where classNameId = &#63; and classPK = &#63; and action = &#63; and field = &#63;.
	 *
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param action the action
	 * @param field the field
	 * @return the number of matching audit entries
	 */
	public static int countByC_C_A_F(
		long classNameId, long classPK, int action, int field) {

		return getPersistence().countByC_C_A_F(
			classNameId, classPK, action, field);
	}

	/**
	 * Caches the audit entry in the entity cache if it is enabled.
	 *
	 * @param auditEntry the audit entry
	 */
	public static void cacheResult(AuditEntry auditEntry) {
		getPersistence().cacheResult(auditEntry);
	}

	/**
	 * Caches the audit entries in the entity cache if it is enabled.
	 *
	 * @param auditEntries the audit entries
	 */
	public static void cacheResult(List<AuditEntry> auditEntries) {
		getPersistence().cacheResult(auditEntries);
	}

	/**
	 * Creates a new audit entry with the primary key. Does not add the audit entry to the database.
	 *
	 * @param auditEntryId the primary key for the new audit entry
	 * @return the new audit entry
	 */
	public static AuditEntry create(long auditEntryId) {
		return getPersistence().create(auditEntryId);
	}

	/**
	 * Removes the audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry that was removed
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	public static AuditEntry remove(long auditEntryId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAuditEntryException {

		return getPersistence().remove(auditEntryId);
	}

	public static AuditEntry updateImpl(AuditEntry auditEntry) {
		return getPersistence().updateImpl(auditEntry);
	}

	/**
	 * Returns the audit entry with the primary key or throws a <code>NoSuchAuditEntryException</code> if it could not be found.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry
	 * @throws NoSuchAuditEntryException if a audit entry with the primary key could not be found
	 */
	public static AuditEntry findByPrimaryKey(long auditEntryId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAuditEntryException {

		return getPersistence().findByPrimaryKey(auditEntryId);
	}

	/**
	 * Returns the audit entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param auditEntryId the primary key of the audit entry
	 * @return the audit entry, or <code>null</code> if a audit entry with the primary key could not be found
	 */
	public static AuditEntry fetchByPrimaryKey(long auditEntryId) {
		return getPersistence().fetchByPrimaryKey(auditEntryId);
	}

	/**
	 * Returns all the audit entries.
	 *
	 * @return the audit entries
	 */
	public static List<AuditEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @return the range of audit entries
	 */
	public static List<AuditEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of audit entries
	 */
	public static List<AuditEntry> findAll(
		int start, int end, OrderByComparator<AuditEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of audit entries
	 * @param end the upper bound of the range of audit entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of audit entries
	 */
	public static List<AuditEntry> findAll(
		int start, int end, OrderByComparator<AuditEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the audit entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of audit entries.
	 *
	 * @return the number of audit entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static AuditEntryPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(AuditEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile AuditEntryPersistence _persistence;

}