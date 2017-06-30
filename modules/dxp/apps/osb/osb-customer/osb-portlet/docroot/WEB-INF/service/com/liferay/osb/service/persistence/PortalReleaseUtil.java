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

import com.liferay.osb.model.PortalRelease;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the portal release service. This utility wraps {@link PortalReleasePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortalReleasePersistence
 * @see PortalReleasePersistenceImpl
 * @generated
 */
public class PortalReleaseUtil {
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
	public static void clearCache(PortalRelease portalRelease) {
		getPersistence().clearCache(portalRelease);
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
	public static List<PortalRelease> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PortalRelease> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PortalRelease> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static PortalRelease update(PortalRelease portalRelease,
		boolean merge) throws SystemException {
		return getPersistence().update(portalRelease, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static PortalRelease update(PortalRelease portalRelease,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(portalRelease, merge, serviceContext);
	}

	/**
	* Caches the portal release in the entity cache if it is enabled.
	*
	* @param portalRelease the portal release
	*/
	public static void cacheResult(
		com.liferay.osb.model.PortalRelease portalRelease) {
		getPersistence().cacheResult(portalRelease);
	}

	/**
	* Caches the portal releases in the entity cache if it is enabled.
	*
	* @param portalReleases the portal releases
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.PortalRelease> portalReleases) {
		getPersistence().cacheResult(portalReleases);
	}

	/**
	* Creates a new portal release with the primary key. Does not add the portal release to the database.
	*
	* @param portalReleaseId the primary key for the new portal release
	* @return the new portal release
	*/
	public static com.liferay.osb.model.PortalRelease create(
		long portalReleaseId) {
		return getPersistence().create(portalReleaseId);
	}

	/**
	* Removes the portal release with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param portalReleaseId the primary key of the portal release
	* @return the portal release that was removed
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease remove(
		long portalReleaseId)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(portalReleaseId);
	}

	public static com.liferay.osb.model.PortalRelease updateImpl(
		com.liferay.osb.model.PortalRelease portalRelease, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(portalRelease, merge);
	}

	/**
	* Returns the portal release with the primary key or throws a {@link com.liferay.osb.NoSuchPortalReleaseException} if it could not be found.
	*
	* @param portalReleaseId the primary key of the portal release
	* @return the portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease findByPrimaryKey(
		long portalReleaseId)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(portalReleaseId);
	}

	/**
	* Returns the portal release with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param portalReleaseId the primary key of the portal release
	* @return the portal release, or <code>null</code> if a portal release with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease fetchByPrimaryKey(
		long portalReleaseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(portalReleaseId);
	}

	/**
	* Returns the portal release where buildNumber = &#63; or throws a {@link com.liferay.osb.NoSuchPortalReleaseException} if it could not be found.
	*
	* @param buildNumber the build number
	* @return the matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease findByBuildNumber(
		int buildNumber)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByBuildNumber(buildNumber);
	}

	/**
	* Returns the portal release where buildNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param buildNumber the build number
	* @return the matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease fetchByBuildNumber(
		int buildNumber)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByBuildNumber(buildNumber);
	}

	/**
	* Returns the portal release where buildNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param buildNumber the build number
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease fetchByBuildNumber(
		int buildNumber, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByBuildNumber(buildNumber, retrieveFromCache);
	}

	/**
	* Returns all the portal releases where ee = &#63;.
	*
	* @param ee the ee
	* @return the matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findByEE(
		boolean ee) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEE(ee);
	}

	/**
	* Returns a range of all the portal releases where ee = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ee the ee
	* @param start the lower bound of the range of portal releases
	* @param end the upper bound of the range of portal releases (not inclusive)
	* @return the range of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findByEE(
		boolean ee, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEE(ee, start, end);
	}

	/**
	* Returns an ordered range of all the portal releases where ee = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ee the ee
	* @param start the lower bound of the range of portal releases
	* @param end the upper bound of the range of portal releases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findByEE(
		boolean ee, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEE(ee, start, end, orderByComparator);
	}

	/**
	* Returns the first portal release in the ordered set where ee = &#63;.
	*
	* @param ee the ee
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease findByEE_First(
		boolean ee,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEE_First(ee, orderByComparator);
	}

	/**
	* Returns the first portal release in the ordered set where ee = &#63;.
	*
	* @param ee the ee
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease fetchByEE_First(
		boolean ee,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByEE_First(ee, orderByComparator);
	}

	/**
	* Returns the last portal release in the ordered set where ee = &#63;.
	*
	* @param ee the ee
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease findByEE_Last(
		boolean ee,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEE_Last(ee, orderByComparator);
	}

	/**
	* Returns the last portal release in the ordered set where ee = &#63;.
	*
	* @param ee the ee
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease fetchByEE_Last(
		boolean ee,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByEE_Last(ee, orderByComparator);
	}

	/**
	* Returns the portal releases before and after the current portal release in the ordered set where ee = &#63;.
	*
	* @param portalReleaseId the primary key of the current portal release
	* @param ee the ee
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease[] findByEE_PrevAndNext(
		long portalReleaseId, boolean ee,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEE_PrevAndNext(portalReleaseId, ee, orderByComparator);
	}

	/**
	* Returns all the portal releases where marketplaceSupport = &#63;.
	*
	* @param marketplaceSupport the marketplace support
	* @return the matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findByMarketplaceSupport(
		boolean marketplaceSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByMarketplaceSupport(marketplaceSupport);
	}

	/**
	* Returns a range of all the portal releases where marketplaceSupport = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param marketplaceSupport the marketplace support
	* @param start the lower bound of the range of portal releases
	* @param end the upper bound of the range of portal releases (not inclusive)
	* @return the range of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findByMarketplaceSupport(
		boolean marketplaceSupport, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMarketplaceSupport(marketplaceSupport, start, end);
	}

	/**
	* Returns an ordered range of all the portal releases where marketplaceSupport = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param marketplaceSupport the marketplace support
	* @param start the lower bound of the range of portal releases
	* @param end the upper bound of the range of portal releases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findByMarketplaceSupport(
		boolean marketplaceSupport, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMarketplaceSupport(marketplaceSupport, start, end,
			orderByComparator);
	}

	/**
	* Returns the first portal release in the ordered set where marketplaceSupport = &#63;.
	*
	* @param marketplaceSupport the marketplace support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease findByMarketplaceSupport_First(
		boolean marketplaceSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMarketplaceSupport_First(marketplaceSupport,
			orderByComparator);
	}

	/**
	* Returns the first portal release in the ordered set where marketplaceSupport = &#63;.
	*
	* @param marketplaceSupport the marketplace support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease fetchByMarketplaceSupport_First(
		boolean marketplaceSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByMarketplaceSupport_First(marketplaceSupport,
			orderByComparator);
	}

	/**
	* Returns the last portal release in the ordered set where marketplaceSupport = &#63;.
	*
	* @param marketplaceSupport the marketplace support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease findByMarketplaceSupport_Last(
		boolean marketplaceSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMarketplaceSupport_Last(marketplaceSupport,
			orderByComparator);
	}

	/**
	* Returns the last portal release in the ordered set where marketplaceSupport = &#63;.
	*
	* @param marketplaceSupport the marketplace support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease fetchByMarketplaceSupport_Last(
		boolean marketplaceSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByMarketplaceSupport_Last(marketplaceSupport,
			orderByComparator);
	}

	/**
	* Returns the portal releases before and after the current portal release in the ordered set where marketplaceSupport = &#63;.
	*
	* @param portalReleaseId the primary key of the current portal release
	* @param marketplaceSupport the marketplace support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease[] findByMarketplaceSupport_PrevAndNext(
		long portalReleaseId, boolean marketplaceSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByMarketplaceSupport_PrevAndNext(portalReleaseId,
			marketplaceSupport, orderByComparator);
	}

	/**
	* Returns all the portal releases where osgiSupport = &#63;.
	*
	* @param osgiSupport the osgi support
	* @return the matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findByOSGiSupport(
		boolean osgiSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOSGiSupport(osgiSupport);
	}

	/**
	* Returns a range of all the portal releases where osgiSupport = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param osgiSupport the osgi support
	* @param start the lower bound of the range of portal releases
	* @param end the upper bound of the range of portal releases (not inclusive)
	* @return the range of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findByOSGiSupport(
		boolean osgiSupport, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOSGiSupport(osgiSupport, start, end);
	}

	/**
	* Returns an ordered range of all the portal releases where osgiSupport = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param osgiSupport the osgi support
	* @param start the lower bound of the range of portal releases
	* @param end the upper bound of the range of portal releases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findByOSGiSupport(
		boolean osgiSupport, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOSGiSupport(osgiSupport, start, end, orderByComparator);
	}

	/**
	* Returns the first portal release in the ordered set where osgiSupport = &#63;.
	*
	* @param osgiSupport the osgi support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease findByOSGiSupport_First(
		boolean osgiSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOSGiSupport_First(osgiSupport, orderByComparator);
	}

	/**
	* Returns the first portal release in the ordered set where osgiSupport = &#63;.
	*
	* @param osgiSupport the osgi support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease fetchByOSGiSupport_First(
		boolean osgiSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOSGiSupport_First(osgiSupport, orderByComparator);
	}

	/**
	* Returns the last portal release in the ordered set where osgiSupport = &#63;.
	*
	* @param osgiSupport the osgi support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease findByOSGiSupport_Last(
		boolean osgiSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOSGiSupport_Last(osgiSupport, orderByComparator);
	}

	/**
	* Returns the last portal release in the ordered set where osgiSupport = &#63;.
	*
	* @param osgiSupport the osgi support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease fetchByOSGiSupport_Last(
		boolean osgiSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOSGiSupport_Last(osgiSupport, orderByComparator);
	}

	/**
	* Returns the portal releases before and after the current portal release in the ordered set where osgiSupport = &#63;.
	*
	* @param portalReleaseId the primary key of the current portal release
	* @param osgiSupport the osgi support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease[] findByOSGiSupport_PrevAndNext(
		long portalReleaseId, boolean osgiSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOSGiSupport_PrevAndNext(portalReleaseId, osgiSupport,
			orderByComparator);
	}

	/**
	* Returns all the portal releases where paclSupport = &#63;.
	*
	* @param paclSupport the pacl support
	* @return the matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findByPACLSupport(
		boolean paclSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPACLSupport(paclSupport);
	}

	/**
	* Returns a range of all the portal releases where paclSupport = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param paclSupport the pacl support
	* @param start the lower bound of the range of portal releases
	* @param end the upper bound of the range of portal releases (not inclusive)
	* @return the range of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findByPACLSupport(
		boolean paclSupport, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPACLSupport(paclSupport, start, end);
	}

	/**
	* Returns an ordered range of all the portal releases where paclSupport = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param paclSupport the pacl support
	* @param start the lower bound of the range of portal releases
	* @param end the upper bound of the range of portal releases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findByPACLSupport(
		boolean paclSupport, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPACLSupport(paclSupport, start, end, orderByComparator);
	}

	/**
	* Returns the first portal release in the ordered set where paclSupport = &#63;.
	*
	* @param paclSupport the pacl support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease findByPACLSupport_First(
		boolean paclSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPACLSupport_First(paclSupport, orderByComparator);
	}

	/**
	* Returns the first portal release in the ordered set where paclSupport = &#63;.
	*
	* @param paclSupport the pacl support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease fetchByPACLSupport_First(
		boolean paclSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPACLSupport_First(paclSupport, orderByComparator);
	}

	/**
	* Returns the last portal release in the ordered set where paclSupport = &#63;.
	*
	* @param paclSupport the pacl support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease findByPACLSupport_Last(
		boolean paclSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPACLSupport_Last(paclSupport, orderByComparator);
	}

	/**
	* Returns the last portal release in the ordered set where paclSupport = &#63;.
	*
	* @param paclSupport the pacl support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease fetchByPACLSupport_Last(
		boolean paclSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPACLSupport_Last(paclSupport, orderByComparator);
	}

	/**
	* Returns the portal releases before and after the current portal release in the ordered set where paclSupport = &#63;.
	*
	* @param portalReleaseId the primary key of the current portal release
	* @param paclSupport the pacl support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease[] findByPACLSupport_PrevAndNext(
		long portalReleaseId, boolean paclSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPACLSupport_PrevAndNext(portalReleaseId, paclSupport,
			orderByComparator);
	}

	/**
	* Returns all the portal releases.
	*
	* @return the portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the portal releases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of portal releases
	* @param end the upper bound of the range of portal releases (not inclusive)
	* @return the range of portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the portal releases.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of portal releases
	* @param end the upper bound of the range of portal releases (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PortalRelease> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the portal release where buildNumber = &#63; from the database.
	*
	* @param buildNumber the build number
	* @return the portal release that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PortalRelease removeByBuildNumber(
		int buildNumber)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByBuildNumber(buildNumber);
	}

	/**
	* Removes all the portal releases where ee = &#63; from the database.
	*
	* @param ee the ee
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByEE(boolean ee)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByEE(ee);
	}

	/**
	* Removes all the portal releases where marketplaceSupport = &#63; from the database.
	*
	* @param marketplaceSupport the marketplace support
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByMarketplaceSupport(boolean marketplaceSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByMarketplaceSupport(marketplaceSupport);
	}

	/**
	* Removes all the portal releases where osgiSupport = &#63; from the database.
	*
	* @param osgiSupport the osgi support
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOSGiSupport(boolean osgiSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByOSGiSupport(osgiSupport);
	}

	/**
	* Removes all the portal releases where paclSupport = &#63; from the database.
	*
	* @param paclSupport the pacl support
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByPACLSupport(boolean paclSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByPACLSupport(paclSupport);
	}

	/**
	* Removes all the portal releases from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of portal releases where buildNumber = &#63;.
	*
	* @param buildNumber the build number
	* @return the number of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static int countByBuildNumber(int buildNumber)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByBuildNumber(buildNumber);
	}

	/**
	* Returns the number of portal releases where ee = &#63;.
	*
	* @param ee the ee
	* @return the number of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static int countByEE(boolean ee)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByEE(ee);
	}

	/**
	* Returns the number of portal releases where marketplaceSupport = &#63;.
	*
	* @param marketplaceSupport the marketplace support
	* @return the number of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static int countByMarketplaceSupport(boolean marketplaceSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByMarketplaceSupport(marketplaceSupport);
	}

	/**
	* Returns the number of portal releases where osgiSupport = &#63;.
	*
	* @param osgiSupport the osgi support
	* @return the number of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOSGiSupport(boolean osgiSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByOSGiSupport(osgiSupport);
	}

	/**
	* Returns the number of portal releases where paclSupport = &#63;.
	*
	* @param paclSupport the pacl support
	* @return the number of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPACLSupport(boolean paclSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByPACLSupport(paclSupport);
	}

	/**
	* Returns the number of portal releases.
	*
	* @return the number of portal releases
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static PortalReleasePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (PortalReleasePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					PortalReleasePersistence.class.getName());

			ReferenceRegistry.registerReference(PortalReleaseUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(PortalReleasePersistence persistence) {
	}

	private static PortalReleasePersistence _persistence;
}