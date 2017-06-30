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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the portal release service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortalReleasePersistenceImpl
 * @see PortalReleaseUtil
 * @generated
 */
public interface PortalReleasePersistence extends BasePersistence<PortalRelease> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PortalReleaseUtil} to access the portal release persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the portal release in the entity cache if it is enabled.
	*
	* @param portalRelease the portal release
	*/
	public void cacheResult(com.liferay.osb.model.PortalRelease portalRelease);

	/**
	* Caches the portal releases in the entity cache if it is enabled.
	*
	* @param portalReleases the portal releases
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.PortalRelease> portalReleases);

	/**
	* Creates a new portal release with the primary key. Does not add the portal release to the database.
	*
	* @param portalReleaseId the primary key for the new portal release
	* @return the new portal release
	*/
	public com.liferay.osb.model.PortalRelease create(long portalReleaseId);

	/**
	* Removes the portal release with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param portalReleaseId the primary key of the portal release
	* @return the portal release that was removed
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease remove(long portalReleaseId)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.PortalRelease updateImpl(
		com.liferay.osb.model.PortalRelease portalRelease, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the portal release with the primary key or throws a {@link com.liferay.osb.NoSuchPortalReleaseException} if it could not be found.
	*
	* @param portalReleaseId the primary key of the portal release
	* @return the portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a portal release with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease findByPrimaryKey(
		long portalReleaseId)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the portal release with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param portalReleaseId the primary key of the portal release
	* @return the portal release, or <code>null</code> if a portal release with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease fetchByPrimaryKey(
		long portalReleaseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the portal release where buildNumber = &#63; or throws a {@link com.liferay.osb.NoSuchPortalReleaseException} if it could not be found.
	*
	* @param buildNumber the build number
	* @return the matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease findByBuildNumber(
		int buildNumber)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the portal release where buildNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param buildNumber the build number
	* @return the matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease fetchByBuildNumber(
		int buildNumber)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the portal release where buildNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param buildNumber the build number
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease fetchByBuildNumber(
		int buildNumber, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the portal releases where ee = &#63;.
	*
	* @param ee the ee
	* @return the matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PortalRelease> findByEE(
		boolean ee) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.PortalRelease> findByEE(
		boolean ee, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.PortalRelease> findByEE(
		boolean ee, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first portal release in the ordered set where ee = &#63;.
	*
	* @param ee the ee
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease findByEE_First(boolean ee,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first portal release in the ordered set where ee = &#63;.
	*
	* @param ee the ee
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease fetchByEE_First(boolean ee,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last portal release in the ordered set where ee = &#63;.
	*
	* @param ee the ee
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease findByEE_Last(boolean ee,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last portal release in the ordered set where ee = &#63;.
	*
	* @param ee the ee
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease fetchByEE_Last(boolean ee,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.PortalRelease[] findByEE_PrevAndNext(
		long portalReleaseId, boolean ee,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the portal releases where marketplaceSupport = &#63;.
	*
	* @param marketplaceSupport the marketplace support
	* @return the matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PortalRelease> findByMarketplaceSupport(
		boolean marketplaceSupport)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.PortalRelease> findByMarketplaceSupport(
		boolean marketplaceSupport, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.PortalRelease> findByMarketplaceSupport(
		boolean marketplaceSupport, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first portal release in the ordered set where marketplaceSupport = &#63;.
	*
	* @param marketplaceSupport the marketplace support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease findByMarketplaceSupport_First(
		boolean marketplaceSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first portal release in the ordered set where marketplaceSupport = &#63;.
	*
	* @param marketplaceSupport the marketplace support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease fetchByMarketplaceSupport_First(
		boolean marketplaceSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last portal release in the ordered set where marketplaceSupport = &#63;.
	*
	* @param marketplaceSupport the marketplace support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease findByMarketplaceSupport_Last(
		boolean marketplaceSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last portal release in the ordered set where marketplaceSupport = &#63;.
	*
	* @param marketplaceSupport the marketplace support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease fetchByMarketplaceSupport_Last(
		boolean marketplaceSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.PortalRelease[] findByMarketplaceSupport_PrevAndNext(
		long portalReleaseId, boolean marketplaceSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the portal releases where osgiSupport = &#63;.
	*
	* @param osgiSupport the osgi support
	* @return the matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PortalRelease> findByOSGiSupport(
		boolean osgiSupport)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.PortalRelease> findByOSGiSupport(
		boolean osgiSupport, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.PortalRelease> findByOSGiSupport(
		boolean osgiSupport, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first portal release in the ordered set where osgiSupport = &#63;.
	*
	* @param osgiSupport the osgi support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease findByOSGiSupport_First(
		boolean osgiSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first portal release in the ordered set where osgiSupport = &#63;.
	*
	* @param osgiSupport the osgi support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease fetchByOSGiSupport_First(
		boolean osgiSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last portal release in the ordered set where osgiSupport = &#63;.
	*
	* @param osgiSupport the osgi support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease findByOSGiSupport_Last(
		boolean osgiSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last portal release in the ordered set where osgiSupport = &#63;.
	*
	* @param osgiSupport the osgi support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease fetchByOSGiSupport_Last(
		boolean osgiSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.PortalRelease[] findByOSGiSupport_PrevAndNext(
		long portalReleaseId, boolean osgiSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the portal releases where paclSupport = &#63;.
	*
	* @param paclSupport the pacl support
	* @return the matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PortalRelease> findByPACLSupport(
		boolean paclSupport)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.PortalRelease> findByPACLSupport(
		boolean paclSupport, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.PortalRelease> findByPACLSupport(
		boolean paclSupport, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first portal release in the ordered set where paclSupport = &#63;.
	*
	* @param paclSupport the pacl support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease findByPACLSupport_First(
		boolean paclSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first portal release in the ordered set where paclSupport = &#63;.
	*
	* @param paclSupport the pacl support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease fetchByPACLSupport_First(
		boolean paclSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last portal release in the ordered set where paclSupport = &#63;.
	*
	* @param paclSupport the pacl support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release
	* @throws com.liferay.osb.NoSuchPortalReleaseException if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease findByPACLSupport_Last(
		boolean paclSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last portal release in the ordered set where paclSupport = &#63;.
	*
	* @param paclSupport the pacl support
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching portal release, or <code>null</code> if a matching portal release could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease fetchByPACLSupport_Last(
		boolean paclSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.PortalRelease[] findByPACLSupport_PrevAndNext(
		long portalReleaseId, boolean paclSupport,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the portal releases.
	*
	* @return the portal releases
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.PortalRelease> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.PortalRelease> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.PortalRelease> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the portal release where buildNumber = &#63; from the database.
	*
	* @param buildNumber the build number
	* @return the portal release that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.PortalRelease removeByBuildNumber(
		int buildNumber)
		throws com.liferay.osb.NoSuchPortalReleaseException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the portal releases where ee = &#63; from the database.
	*
	* @param ee the ee
	* @throws SystemException if a system exception occurred
	*/
	public void removeByEE(boolean ee)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the portal releases where marketplaceSupport = &#63; from the database.
	*
	* @param marketplaceSupport the marketplace support
	* @throws SystemException if a system exception occurred
	*/
	public void removeByMarketplaceSupport(boolean marketplaceSupport)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the portal releases where osgiSupport = &#63; from the database.
	*
	* @param osgiSupport the osgi support
	* @throws SystemException if a system exception occurred
	*/
	public void removeByOSGiSupport(boolean osgiSupport)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the portal releases where paclSupport = &#63; from the database.
	*
	* @param paclSupport the pacl support
	* @throws SystemException if a system exception occurred
	*/
	public void removeByPACLSupport(boolean paclSupport)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the portal releases from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of portal releases where buildNumber = &#63;.
	*
	* @param buildNumber the build number
	* @return the number of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public int countByBuildNumber(int buildNumber)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of portal releases where ee = &#63;.
	*
	* @param ee the ee
	* @return the number of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public int countByEE(boolean ee)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of portal releases where marketplaceSupport = &#63;.
	*
	* @param marketplaceSupport the marketplace support
	* @return the number of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public int countByMarketplaceSupport(boolean marketplaceSupport)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of portal releases where osgiSupport = &#63;.
	*
	* @param osgiSupport the osgi support
	* @return the number of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public int countByOSGiSupport(boolean osgiSupport)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of portal releases where paclSupport = &#63;.
	*
	* @param paclSupport the pacl support
	* @return the number of matching portal releases
	* @throws SystemException if a system exception occurred
	*/
	public int countByPACLSupport(boolean paclSupport)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of portal releases.
	*
	* @return the number of portal releases
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}