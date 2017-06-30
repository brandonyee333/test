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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset receipt redeem token service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptRedeemTokenPersistenceImpl
 * @see AssetReceiptRedeemTokenUtil
 * @generated
 */
public interface AssetReceiptRedeemTokenPersistence extends BasePersistence<AssetReceiptRedeemToken> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetReceiptRedeemTokenUtil} to access the asset receipt redeem token persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the asset receipt redeem token in the entity cache if it is enabled.
	*
	* @param assetReceiptRedeemToken the asset receipt redeem token
	*/
	public void cacheResult(
		com.liferay.osb.model.AssetReceiptRedeemToken assetReceiptRedeemToken);

	/**
	* Caches the asset receipt redeem tokens in the entity cache if it is enabled.
	*
	* @param assetReceiptRedeemTokens the asset receipt redeem tokens
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AssetReceiptRedeemToken> assetReceiptRedeemTokens);

	/**
	* Creates a new asset receipt redeem token with the primary key. Does not add the asset receipt redeem token to the database.
	*
	* @param AssetReceiptRedeemTokenId the primary key for the new asset receipt redeem token
	* @return the new asset receipt redeem token
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken create(
		long AssetReceiptRedeemTokenId);

	/**
	* Removes the asset receipt redeem token with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param AssetReceiptRedeemTokenId the primary key of the asset receipt redeem token
	* @return the asset receipt redeem token that was removed
	* @throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException if a asset receipt redeem token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken remove(
		long AssetReceiptRedeemTokenId)
		throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetReceiptRedeemToken updateImpl(
		com.liferay.osb.model.AssetReceiptRedeemToken assetReceiptRedeemToken,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt redeem token with the primary key or throws a {@link com.liferay.osb.NoSuchAssetReceiptRedeemTokenException} if it could not be found.
	*
	* @param AssetReceiptRedeemTokenId the primary key of the asset receipt redeem token
	* @return the asset receipt redeem token
	* @throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException if a asset receipt redeem token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken findByPrimaryKey(
		long AssetReceiptRedeemTokenId)
		throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt redeem token with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param AssetReceiptRedeemTokenId the primary key of the asset receipt redeem token
	* @return the asset receipt redeem token, or <code>null</code> if a asset receipt redeem token with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken fetchByPrimaryKey(
		long AssetReceiptRedeemTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt redeem token where token = &#63; or throws a {@link com.liferay.osb.NoSuchAssetReceiptRedeemTokenException} if it could not be found.
	*
	* @param token the token
	* @return the matching asset receipt redeem token
	* @throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException if a matching asset receipt redeem token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken findByToken(
		java.lang.String token)
		throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt redeem token where token = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param token the token
	* @return the matching asset receipt redeem token, or <code>null</code> if a matching asset receipt redeem token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken fetchByToken(
		java.lang.String token)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt redeem token where token = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param token the token
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset receipt redeem token, or <code>null</code> if a matching asset receipt redeem token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken fetchByToken(
		java.lang.String token, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt redeem token where redeemEmailAddress = &#63; and redeemDate = &#63; or throws a {@link com.liferay.osb.NoSuchAssetReceiptRedeemTokenException} if it could not be found.
	*
	* @param redeemEmailAddress the redeem email address
	* @param redeemDate the redeem date
	* @return the matching asset receipt redeem token
	* @throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException if a matching asset receipt redeem token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken findByREA_RD(
		java.lang.String redeemEmailAddress, java.util.Date redeemDate)
		throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt redeem token where redeemEmailAddress = &#63; and redeemDate = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param redeemEmailAddress the redeem email address
	* @param redeemDate the redeem date
	* @return the matching asset receipt redeem token, or <code>null</code> if a matching asset receipt redeem token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken fetchByREA_RD(
		java.lang.String redeemEmailAddress, java.util.Date redeemDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt redeem token where redeemEmailAddress = &#63; and redeemDate = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param redeemEmailAddress the redeem email address
	* @param redeemDate the redeem date
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching asset receipt redeem token, or <code>null</code> if a matching asset receipt redeem token could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken fetchByREA_RD(
		java.lang.String redeemEmailAddress, java.util.Date redeemDate,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset receipt redeem tokens.
	*
	* @return the asset receipt redeem tokens
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetReceiptRedeemToken> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptRedeemToken> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetReceiptRedeemToken> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the asset receipt redeem token where token = &#63; from the database.
	*
	* @param token the token
	* @return the asset receipt redeem token that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken removeByToken(
		java.lang.String token)
		throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the asset receipt redeem token where redeemEmailAddress = &#63; and redeemDate = &#63; from the database.
	*
	* @param redeemEmailAddress the redeem email address
	* @param redeemDate the redeem date
	* @return the asset receipt redeem token that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptRedeemToken removeByREA_RD(
		java.lang.String redeemEmailAddress, java.util.Date redeemDate)
		throws com.liferay.osb.NoSuchAssetReceiptRedeemTokenException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset receipt redeem tokens from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt redeem tokens where token = &#63;.
	*
	* @param token the token
	* @return the number of matching asset receipt redeem tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countByToken(java.lang.String token)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt redeem tokens where redeemEmailAddress = &#63; and redeemDate = &#63;.
	*
	* @param redeemEmailAddress the redeem email address
	* @param redeemDate the redeem date
	* @return the number of matching asset receipt redeem tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countByREA_RD(java.lang.String redeemEmailAddress,
		java.util.Date redeemDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt redeem tokens.
	*
	* @return the number of asset receipt redeem tokens
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}