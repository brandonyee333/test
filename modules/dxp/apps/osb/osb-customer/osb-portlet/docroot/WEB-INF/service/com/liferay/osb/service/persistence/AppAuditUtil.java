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

import com.liferay.osb.model.AppAudit;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the app audit service. This utility wraps {@link AppAuditPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppAuditPersistence
 * @see AppAuditPersistenceImpl
 * @generated
 */
public class AppAuditUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(AppAudit appAudit) {
		getPersistence().clearCache(appAudit);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AppAudit> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AppAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AppAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AppAudit update(AppAudit appAudit, boolean merge)
		throws SystemException {
		return getPersistence().update(appAudit, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AppAudit update(AppAudit appAudit, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(appAudit, merge, serviceContext);
	}

	/**
	* Caches the app audit in the entity cache if it is enabled.
	*
	* @param appAudit the app audit
	*/
	public static void cacheResult(com.liferay.osb.model.AppAudit appAudit) {
		getPersistence().cacheResult(appAudit);
	}

	/**
	* Caches the app audits in the entity cache if it is enabled.
	*
	* @param appAudits the app audits
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AppAudit> appAudits) {
		getPersistence().cacheResult(appAudits);
	}

	/**
	* Creates a new app audit with the primary key. Does not add the app audit to the database.
	*
	* @param appAuditId the primary key for the new app audit
	* @return the new app audit
	*/
	public static com.liferay.osb.model.AppAudit create(long appAuditId) {
		return getPersistence().create(appAuditId);
	}

	/**
	* Removes the app audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appAuditId the primary key of the app audit
	* @return the app audit that was removed
	* @throws com.liferay.osb.NoSuchAppAuditException if a app audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit remove(long appAuditId)
		throws com.liferay.osb.NoSuchAppAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(appAuditId);
	}

	public static com.liferay.osb.model.AppAudit updateImpl(
		com.liferay.osb.model.AppAudit appAudit, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(appAudit, merge);
	}

	/**
	* Returns the app audit with the primary key or throws a {@link com.liferay.osb.NoSuchAppAuditException} if it could not be found.
	*
	* @param appAuditId the primary key of the app audit
	* @return the app audit
	* @throws com.liferay.osb.NoSuchAppAuditException if a app audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit findByPrimaryKey(
		long appAuditId)
		throws com.liferay.osb.NoSuchAppAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(appAuditId);
	}

	/**
	* Returns the app audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param appAuditId the primary key of the app audit
	* @return the app audit, or <code>null</code> if a app audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit fetchByPrimaryKey(
		long appAuditId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(appAuditId);
	}

	/**
	* Returns all the app audits where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching app audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppAudit> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the app audits where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of app audits
	* @param end the upper bound of the range of app audits (not inclusive)
	* @return the range of matching app audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppAudit> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the app audits where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of app audits
	* @param end the upper bound of the range of app audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppAudit> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns the first app audit in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app audit
	* @throws com.liferay.osb.NoSuchAppAuditException if a matching app audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first app audit in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app audit, or <code>null</code> if a matching app audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last app audit in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app audit
	* @throws com.liferay.osb.NoSuchAppAuditException if a matching app audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last app audit in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app audit, or <code>null</code> if a matching app audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the app audits before and after the current app audit in the ordered set where uuid = &#63;.
	*
	* @param appAuditId the primary key of the current app audit
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app audit
	* @throws com.liferay.osb.NoSuchAppAuditException if a app audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit[] findByUuid_PrevAndNext(
		long appAuditId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(appAuditId, uuid, orderByComparator);
	}

	/**
	* Returns all the app audits where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @return the matching app audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppAudit> findByAppVersionId(
		long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppVersionId(appVersionId);
	}

	/**
	* Returns a range of all the app audits where appVersionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appVersionId the app version ID
	* @param start the lower bound of the range of app audits
	* @param end the upper bound of the range of app audits (not inclusive)
	* @return the range of matching app audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppAudit> findByAppVersionId(
		long appVersionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAppVersionId(appVersionId, start, end);
	}

	/**
	* Returns an ordered range of all the app audits where appVersionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param appVersionId the app version ID
	* @param start the lower bound of the range of app audits
	* @param end the upper bound of the range of app audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching app audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppAudit> findByAppVersionId(
		long appVersionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId(appVersionId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first app audit in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app audit
	* @throws com.liferay.osb.NoSuchAppAuditException if a matching app audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit findByAppVersionId_First(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_First(appVersionId, orderByComparator);
	}

	/**
	* Returns the first app audit in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching app audit, or <code>null</code> if a matching app audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit fetchByAppVersionId_First(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppVersionId_First(appVersionId, orderByComparator);
	}

	/**
	* Returns the last app audit in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app audit
	* @throws com.liferay.osb.NoSuchAppAuditException if a matching app audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit findByAppVersionId_Last(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_Last(appVersionId, orderByComparator);
	}

	/**
	* Returns the last app audit in the ordered set where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching app audit, or <code>null</code> if a matching app audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit fetchByAppVersionId_Last(
		long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAppVersionId_Last(appVersionId, orderByComparator);
	}

	/**
	* Returns the app audits before and after the current app audit in the ordered set where appVersionId = &#63;.
	*
	* @param appAuditId the primary key of the current app audit
	* @param appVersionId the app version ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next app audit
	* @throws com.liferay.osb.NoSuchAppAuditException if a app audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppAudit[] findByAppVersionId_PrevAndNext(
		long appAuditId, long appVersionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAppAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAppVersionId_PrevAndNext(appAuditId, appVersionId,
			orderByComparator);
	}

	/**
	* Returns all the app audits.
	*
	* @return the app audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppAudit> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the app audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app audits
	* @param end the upper bound of the range of app audits (not inclusive)
	* @return the range of app audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppAudit> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the app audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app audits
	* @param end the upper bound of the range of app audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of app audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the app audits where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Removes all the app audits where appVersionId = &#63; from the database.
	*
	* @param appVersionId the app version ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAppVersionId(appVersionId);
	}

	/**
	* Removes all the app audits from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of app audits where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching app audits
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the number of app audits where appVersionId = &#63;.
	*
	* @param appVersionId the app version ID
	* @return the number of matching app audits
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAppVersionId(long appVersionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAppVersionId(appVersionId);
	}

	/**
	* Returns the number of app audits.
	*
	* @return the number of app audits
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AppAuditPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AppAuditPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AppAuditPersistence.class.getName());

			ReferenceRegistry.registerReference(AppAuditUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AppAuditPersistence persistence) {
	}

	private static AppAuditPersistence _persistence;
}