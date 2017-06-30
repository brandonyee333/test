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

package com.liferay.osb.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AssetReceiptRedeemToken}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetReceiptRedeemToken
 * @generated
 */
public class AssetReceiptRedeemTokenWrapper implements AssetReceiptRedeemToken,
	ModelWrapper<AssetReceiptRedeemToken> {
	public AssetReceiptRedeemTokenWrapper(
		AssetReceiptRedeemToken assetReceiptRedeemToken) {
		_assetReceiptRedeemToken = assetReceiptRedeemToken;
	}

	public Class<?> getModelClass() {
		return AssetReceiptRedeemToken.class;
	}

	public String getModelClassName() {
		return AssetReceiptRedeemToken.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("AssetReceiptRedeemTokenId",
			getAssetReceiptRedeemTokenId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("redeemEmailAddress", getRedeemEmailAddress());
		attributes.put("redeemDate", getRedeemDate());
		attributes.put("token", getToken());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long AssetReceiptRedeemTokenId = (Long)attributes.get(
				"AssetReceiptRedeemTokenId");

		if (AssetReceiptRedeemTokenId != null) {
			setAssetReceiptRedeemTokenId(AssetReceiptRedeemTokenId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String redeemEmailAddress = (String)attributes.get("redeemEmailAddress");

		if (redeemEmailAddress != null) {
			setRedeemEmailAddress(redeemEmailAddress);
		}

		Date redeemDate = (Date)attributes.get("redeemDate");

		if (redeemDate != null) {
			setRedeemDate(redeemDate);
		}

		String token = (String)attributes.get("token");

		if (token != null) {
			setToken(token);
		}
	}

	/**
	* Returns the primary key of this asset receipt redeem token.
	*
	* @return the primary key of this asset receipt redeem token
	*/
	public long getPrimaryKey() {
		return _assetReceiptRedeemToken.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset receipt redeem token.
	*
	* @param primaryKey the primary key of this asset receipt redeem token
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetReceiptRedeemToken.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset receipt redeem token ID of this asset receipt redeem token.
	*
	* @return the asset receipt redeem token ID of this asset receipt redeem token
	*/
	public long getAssetReceiptRedeemTokenId() {
		return _assetReceiptRedeemToken.getAssetReceiptRedeemTokenId();
	}

	/**
	* Sets the asset receipt redeem token ID of this asset receipt redeem token.
	*
	* @param AssetReceiptRedeemTokenId the asset receipt redeem token ID of this asset receipt redeem token
	*/
	public void setAssetReceiptRedeemTokenId(long AssetReceiptRedeemTokenId) {
		_assetReceiptRedeemToken.setAssetReceiptRedeemTokenId(AssetReceiptRedeemTokenId);
	}

	/**
	* Returns the user ID of this asset receipt redeem token.
	*
	* @return the user ID of this asset receipt redeem token
	*/
	public long getUserId() {
		return _assetReceiptRedeemToken.getUserId();
	}

	/**
	* Sets the user ID of this asset receipt redeem token.
	*
	* @param userId the user ID of this asset receipt redeem token
	*/
	public void setUserId(long userId) {
		_assetReceiptRedeemToken.setUserId(userId);
	}

	/**
	* Returns the user uuid of this asset receipt redeem token.
	*
	* @return the user uuid of this asset receipt redeem token
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetReceiptRedeemToken.getUserUuid();
	}

	/**
	* Sets the user uuid of this asset receipt redeem token.
	*
	* @param userUuid the user uuid of this asset receipt redeem token
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_assetReceiptRedeemToken.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this asset receipt redeem token.
	*
	* @return the user name of this asset receipt redeem token
	*/
	public java.lang.String getUserName() {
		return _assetReceiptRedeemToken.getUserName();
	}

	/**
	* Sets the user name of this asset receipt redeem token.
	*
	* @param userName the user name of this asset receipt redeem token
	*/
	public void setUserName(java.lang.String userName) {
		_assetReceiptRedeemToken.setUserName(userName);
	}

	/**
	* Returns the create date of this asset receipt redeem token.
	*
	* @return the create date of this asset receipt redeem token
	*/
	public java.util.Date getCreateDate() {
		return _assetReceiptRedeemToken.getCreateDate();
	}

	/**
	* Sets the create date of this asset receipt redeem token.
	*
	* @param createDate the create date of this asset receipt redeem token
	*/
	public void setCreateDate(java.util.Date createDate) {
		_assetReceiptRedeemToken.setCreateDate(createDate);
	}

	/**
	* Returns the fully qualified class name of this asset receipt redeem token.
	*
	* @return the fully qualified class name of this asset receipt redeem token
	*/
	public java.lang.String getClassName() {
		return _assetReceiptRedeemToken.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_assetReceiptRedeemToken.setClassName(className);
	}

	/**
	* Returns the class name ID of this asset receipt redeem token.
	*
	* @return the class name ID of this asset receipt redeem token
	*/
	public long getClassNameId() {
		return _assetReceiptRedeemToken.getClassNameId();
	}

	/**
	* Sets the class name ID of this asset receipt redeem token.
	*
	* @param classNameId the class name ID of this asset receipt redeem token
	*/
	public void setClassNameId(long classNameId) {
		_assetReceiptRedeemToken.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this asset receipt redeem token.
	*
	* @return the class p k of this asset receipt redeem token
	*/
	public long getClassPK() {
		return _assetReceiptRedeemToken.getClassPK();
	}

	/**
	* Sets the class p k of this asset receipt redeem token.
	*
	* @param classPK the class p k of this asset receipt redeem token
	*/
	public void setClassPK(long classPK) {
		_assetReceiptRedeemToken.setClassPK(classPK);
	}

	/**
	* Returns the redeem email address of this asset receipt redeem token.
	*
	* @return the redeem email address of this asset receipt redeem token
	*/
	public java.lang.String getRedeemEmailAddress() {
		return _assetReceiptRedeemToken.getRedeemEmailAddress();
	}

	/**
	* Sets the redeem email address of this asset receipt redeem token.
	*
	* @param redeemEmailAddress the redeem email address of this asset receipt redeem token
	*/
	public void setRedeemEmailAddress(java.lang.String redeemEmailAddress) {
		_assetReceiptRedeemToken.setRedeemEmailAddress(redeemEmailAddress);
	}

	/**
	* Returns the redeem date of this asset receipt redeem token.
	*
	* @return the redeem date of this asset receipt redeem token
	*/
	public java.util.Date getRedeemDate() {
		return _assetReceiptRedeemToken.getRedeemDate();
	}

	/**
	* Sets the redeem date of this asset receipt redeem token.
	*
	* @param redeemDate the redeem date of this asset receipt redeem token
	*/
	public void setRedeemDate(java.util.Date redeemDate) {
		_assetReceiptRedeemToken.setRedeemDate(redeemDate);
	}

	/**
	* Returns the token of this asset receipt redeem token.
	*
	* @return the token of this asset receipt redeem token
	*/
	public java.lang.String getToken() {
		return _assetReceiptRedeemToken.getToken();
	}

	/**
	* Sets the token of this asset receipt redeem token.
	*
	* @param token the token of this asset receipt redeem token
	*/
	public void setToken(java.lang.String token) {
		_assetReceiptRedeemToken.setToken(token);
	}

	public boolean isNew() {
		return _assetReceiptRedeemToken.isNew();
	}

	public void setNew(boolean n) {
		_assetReceiptRedeemToken.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetReceiptRedeemToken.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetReceiptRedeemToken.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetReceiptRedeemToken.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetReceiptRedeemToken.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetReceiptRedeemToken.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetReceiptRedeemToken.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetReceiptRedeemToken.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetReceiptRedeemTokenWrapper((AssetReceiptRedeemToken)_assetReceiptRedeemToken.clone());
	}

	public int compareTo(
		com.liferay.osb.model.AssetReceiptRedeemToken assetReceiptRedeemToken) {
		return _assetReceiptRedeemToken.compareTo(assetReceiptRedeemToken);
	}

	@Override
	public int hashCode() {
		return _assetReceiptRedeemToken.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AssetReceiptRedeemToken> toCacheModel() {
		return _assetReceiptRedeemToken.toCacheModel();
	}

	public com.liferay.osb.model.AssetReceiptRedeemToken toEscapedModel() {
		return new AssetReceiptRedeemTokenWrapper(_assetReceiptRedeemToken.toEscapedModel());
	}

	public com.liferay.osb.model.AssetReceiptRedeemToken toUnescapedModel() {
		return new AssetReceiptRedeemTokenWrapper(_assetReceiptRedeemToken.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetReceiptRedeemToken.toString();
	}

	public java.lang.String toXmlString() {
		return _assetReceiptRedeemToken.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetReceiptRedeemToken.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetReceiptRedeemTokenWrapper)) {
			return false;
		}

		AssetReceiptRedeemTokenWrapper assetReceiptRedeemTokenWrapper = (AssetReceiptRedeemTokenWrapper)obj;

		if (Validator.equals(_assetReceiptRedeemToken,
					assetReceiptRedeemTokenWrapper._assetReceiptRedeemToken)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetReceiptRedeemToken getWrappedAssetReceiptRedeemToken() {
		return _assetReceiptRedeemToken;
	}

	public AssetReceiptRedeemToken getWrappedModel() {
		return _assetReceiptRedeemToken;
	}

	public void resetOriginalValues() {
		_assetReceiptRedeemToken.resetOriginalValues();
	}

	private AssetReceiptRedeemToken _assetReceiptRedeemToken;
}