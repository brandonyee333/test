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

import com.liferay.osb.model.AssetReceiptRedeemToken;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the asset receipt redeem token service. This utility wraps {@link AssetReceiptRedeemTokenPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptRedeemTokenPersistence
 * @see AssetReceiptRedeemTokenPersistenceImpl
 * @generated
 */
public class AssetReceiptRedeemTokenUtil {
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
	public static void clearCache(
		AssetReceiptRedeemToken assetReceiptRedeemToken) {
		getPersistence().clearCache(assetReceiptRedeemToken);
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
	public static List<AssetReceiptRedeemToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetReceiptRedeemToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetReceiptRedeemToken> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AssetReceiptRedeemToken update(
		AssetReceiptRedeemToken assetReceiptRedeemToken, boolean merge)
		throws SystemException {
		return getPersistence().update(assetReceiptRedeemToken, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AssetReceiptRedeemToken update(
		AssetReceiptRedeemToken assetReceiptRedeemToken, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(assetReceiptRedeemToken, merge, serviceContext);
	}

	/**
	* Caches the asset receipt redeem token in the entity cache if it is enabled.
	*
	* @param assetReceiptRedeemToken the asset receipt redeem token
	*/
	public static void cacheResult(
		com.liferay.osb.model.AssetReceiptRedeemToken assetReceiptRedeemToken) {
		getPersistence().cacheResult(assetReceiptRedeemToken);
	}

	/**
	* Caches the asset receipt redeem tokens in the entity cache if it is enabled.
	*
	* @param assetReceiptRedeemTokens the asset receipt redeem tokens
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AssetReceiptRedeemToken> assetReceiptRedeemTokens) {
		getPersistence().cacheResult(assetReceiptRedeemTokens);
	}

	/**
	* Creates a new asset receipt redeem token with the primary key. Does not add the asset receipt redeem token to the database.
	*
	* @param AssetReceiptRedeemTokenId the primary key for the new asset receipt redeem token
	* @return the new asset receipt redeem token
	*/
	public static com.liferay.osb.model.AssetReceiptRedeemToken create(
		long AssetReceiptRedeemTokenId) {
		return getPersistence().create(AssetReceiptRedeemTokenId);
	}

	/**
	* Removes the asset receipt redeem token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param AssetReceiptRedeemTokenId the primary key of the asset receipt redeem token
	* @return the asset receipt redeem token that was removed
	* @throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException if a asset receipt redeem token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptRedeemToken remove(
		long AssetReceiptRedeemTokenId)
		throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(AssetReceiptRedeemTokenId);
	}

	public static com.liferay.osb.model.AssetReceiptRedeemToken updateImpl(
		com.liferay.osb.model.AssetReceiptRedeemToken assetReceiptRedeemToken,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(assetReceiptRedeemToken, merge);
	}

	/**
	* Returns the asset receipt redeem token with the primary key or throws a {@link com.liferay.osb.NoSuchAssetReceiptRedeemTokenException} if it could not be found.
	*
	* @param AssetReceiptRedeemTokenId the primary key of the asset receipt redeem token
	* @return the asset receipt redeem token
	* @throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException if a asset receipt redeem token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptRedeemToken findByPrimaryKey(
		long AssetReceiptRedeemTokenId)
		throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(AssetReceiptRedeemTokenId);
	}

	/**
	* Returns the asset receipt redeem token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param AssetReceiptRedeemTokenId the primary key of the asset receipt redeem token
	* @return the asset receipt redeem token, or <code>null</code> if a asset receipt redeem token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptRedeemToken fetchByPrimaryKey(
		long AssetReceiptRedeemTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(AssetReceiptRedeemTokenId);
	}

	/**
	* Returns the asset receipt redeem token where token = &#63; or throws a {@link com.liferay.osb.NoSuchAssetReceiptRedeemTokenException} if it could not be found.
	*
	* @param token the token
	* @return the matching asset receipt redeem token
	* @throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException if a matching asset receipt redeem token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptRedeemToken findByToken(
		java.lang.String token)
		throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByToken(token);
	}

	/**
	* Returns the asset receipt redeem token where token = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param token the token
	* @return the matching asset receipt redeem token, or <code>null</code> if a matching asset receipt redeem token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptRedeemToken fetchByToken(
		java.lang.String token)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByToken(token);
	}

	/**
	* Returns the asset receipt redeem token where token = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param token the token
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset receipt redeem token, or <code>null</code> if a matching asset receipt redeem token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptRedeemToken fetchByToken(
		java.lang.String token, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByToken(token, retrieveFromCache);
	}

	/**
	* Returns the asset receipt redeem token where redeemEmailAddress = &#63; and redeemDate = &#63; or throws a {@link com.liferay.osb.NoSuchAssetReceiptRedeemTokenException} if it could not be found.
	*
	* @param redeemEmailAddress the redeem email address
	* @param redeemDate the redeem date
	* @return the matching asset receipt redeem token
	* @throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException if a matching asset receipt redeem token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptRedeemToken findByREA_RD(
		java.lang.String redeemEmailAddress, java.util.Date redeemDate)
		throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByREA_RD(redeemEmailAddress, redeemDate);
	}

	/**
	* Returns the asset receipt redeem token where redeemEmailAddress = &#63; and redeemDate = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param redeemEmailAddress the redeem email address
	* @param redeemDate the redeem date
	* @return the matching asset receipt redeem token, or <code>null</code> if a matching asset receipt redeem token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptRedeemToken fetchByREA_RD(
		java.lang.String redeemEmailAddress, java.util.Date redeemDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByREA_RD(redeemEmailAddress, redeemDate);
	}

	/**
	* Returns the asset receipt redeem token where redeemEmailAddress = &#63; and redeemDate = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param redeemEmailAddress the redeem email address
	* @param redeemDate the redeem date
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset receipt redeem token, or <code>null</code> if a matching asset receipt redeem token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptRedeemToken fetchByREA_RD(
		java.lang.String redeemEmailAddress, java.util.Date redeemDate,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByREA_RD(redeemEmailAddress, redeemDate,
			retrieveFromCache);
	}

	/**
	* Returns all the asset receipt redeem tokens.
	*
	* @return the asset receipt redeem tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptRedeemToken> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the asset receipt redeem tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt redeem tokens
	* @param end the upper bound of the range of asset receipt redeem tokens (not inclusive)
	* @return the range of asset receipt redeem tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptRedeemToken> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the asset receipt redeem tokens.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt redeem tokens
	* @param end the upper bound of the range of asset receipt redeem tokens (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset receipt redeem tokens
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptRedeemToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the asset receipt redeem token where token = &#63; from the database.
	*
	* @param token the token
	* @return the asset receipt redeem token that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptRedeemToken removeByToken(
		java.lang.String token)
		throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByToken(token);
	}

	/**
	* Removes the asset receipt redeem token where redeemEmailAddress = &#63; and redeemDate = &#63; from the database.
	*
	* @param redeemEmailAddress the redeem email address
	* @param redeemDate the redeem date
	* @return the asset receipt redeem token that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptRedeemToken removeByREA_RD(
		java.lang.String redeemEmailAddress, java.util.Date redeemDate)
		throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByREA_RD(redeemEmailAddress, redeemDate);
	}

	/**
	* Removes all the asset receipt redeem tokens from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset receipt redeem tokens where token = &#63;.
	*
	* @param token the token
	* @return the number of matching asset receipt redeem tokens
	* @throws SystemException if a system exception occurred
	*/
	public static int countByToken(java.lang.String token)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByToken(token);
	}

	/**
	* Returns the number of asset receipt redeem tokens where redeemEmailAddress = &#63; and redeemDate = &#63;.
	*
	* @param redeemEmailAddress the redeem email address
	* @param redeemDate the redeem date
	* @return the number of matching asset receipt redeem tokens
	* @throws SystemException if a system exception occurred
	*/
	public static int countByREA_RD(java.lang.String redeemEmailAddress,
		java.util.Date redeemDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByREA_RD(redeemEmailAddress, redeemDate);
	}

	/**
	* Returns the number of asset receipt redeem tokens.
	*
	* @return the number of asset receipt redeem tokens
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetReceiptRedeemTokenPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetReceiptRedeemTokenPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetReceiptRedeemTokenPersistence.class.getName());

			ReferenceRegistry.registerReference(AssetReceiptRedeemTokenUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AssetReceiptRedeemTokenPersistence persistence) {
	}

	private static AssetReceiptRedeemTokenPersistence _persistence;
}