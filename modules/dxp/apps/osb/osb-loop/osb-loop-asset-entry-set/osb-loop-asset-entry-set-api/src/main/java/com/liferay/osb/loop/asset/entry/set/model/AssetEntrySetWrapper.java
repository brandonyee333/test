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

package com.liferay.osb.loop.asset.entry.set.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link AssetEntrySet}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySet
 * @generated
 */
public class AssetEntrySetWrapper
	implements AssetEntrySet, ModelWrapper<AssetEntrySet> {

	public AssetEntrySetWrapper(AssetEntrySet assetEntrySet) {
		_assetEntrySet = assetEntrySet;
	}

	@Override
	public Class<?> getModelClass() {
		return AssetEntrySet.class;
	}

	@Override
	public String getModelClassName() {
		return AssetEntrySet.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetEntrySetId", getAssetEntrySetId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createTime", getCreateTime());
		attributes.put("modifiedTime", getModifiedTime());
		attributes.put("assetEntryId", getAssetEntryId());
		attributes.put("parentAssetEntrySetId", getParentAssetEntrySetId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("creatorClassNameId", getCreatorClassNameId());
		attributes.put("creatorClassPK", getCreatorClassPK());
		attributes.put("creatorName", getCreatorName());
		attributes.put("assetEntrySetLikesCount", getAssetEntrySetLikesCount());
		attributes.put(
			"childAssetEntrySetsCount", getChildAssetEntrySetsCount());
		attributes.put("level", getLevel());
		attributes.put("payload", getPayload());
		attributes.put("privateAssetEntrySet", isPrivateAssetEntrySet());
		attributes.put("stickyTime", getStickyTime());
		attributes.put("title", getTitle());
		attributes.put("type", getType());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetEntrySetId = (Long)attributes.get("assetEntrySetId");

		if (assetEntrySetId != null) {
			setAssetEntrySetId(assetEntrySetId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long createTime = (Long)attributes.get("createTime");

		if (createTime != null) {
			setCreateTime(createTime);
		}

		Long modifiedTime = (Long)attributes.get("modifiedTime");

		if (modifiedTime != null) {
			setModifiedTime(modifiedTime);
		}

		Long assetEntryId = (Long)attributes.get("assetEntryId");

		if (assetEntryId != null) {
			setAssetEntryId(assetEntryId);
		}

		Long parentAssetEntrySetId = (Long)attributes.get(
			"parentAssetEntrySetId");

		if (parentAssetEntrySetId != null) {
			setParentAssetEntrySetId(parentAssetEntrySetId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long creatorClassNameId = (Long)attributes.get("creatorClassNameId");

		if (creatorClassNameId != null) {
			setCreatorClassNameId(creatorClassNameId);
		}

		Long creatorClassPK = (Long)attributes.get("creatorClassPK");

		if (creatorClassPK != null) {
			setCreatorClassPK(creatorClassPK);
		}

		String creatorName = (String)attributes.get("creatorName");

		if (creatorName != null) {
			setCreatorName(creatorName);
		}

		Integer assetEntrySetLikesCount = (Integer)attributes.get(
			"assetEntrySetLikesCount");

		if (assetEntrySetLikesCount != null) {
			setAssetEntrySetLikesCount(assetEntrySetLikesCount);
		}

		Integer childAssetEntrySetsCount = (Integer)attributes.get(
			"childAssetEntrySetsCount");

		if (childAssetEntrySetsCount != null) {
			setChildAssetEntrySetsCount(childAssetEntrySetsCount);
		}

		Integer level = (Integer)attributes.get("level");

		if (level != null) {
			setLevel(level);
		}

		String payload = (String)attributes.get("payload");

		if (payload != null) {
			setPayload(payload);
		}

		Boolean privateAssetEntrySet = (Boolean)attributes.get(
			"privateAssetEntrySet");

		if (privateAssetEntrySet != null) {
			setPrivateAssetEntrySet(privateAssetEntrySet);
		}

		Long stickyTime = (Long)attributes.get("stickyTime");

		if (stickyTime != null) {
			setStickyTime(stickyTime);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public Object clone() {
		return new AssetEntrySetWrapper((AssetEntrySet)_assetEntrySet.clone());
	}

	@Override
	public int compareTo(AssetEntrySet assetEntrySet) {
		return _assetEntrySet.compareTo(assetEntrySet);
	}

	/**
	 * Returns the asset entry ID of this asset entry set.
	 *
	 * @return the asset entry ID of this asset entry set
	 */
	@Override
	public long getAssetEntryId() {
		return _assetEntrySet.getAssetEntryId();
	}

	/**
	 * Returns the asset entry set ID of this asset entry set.
	 *
	 * @return the asset entry set ID of this asset entry set
	 */
	@Override
	public long getAssetEntrySetId() {
		return _assetEntrySet.getAssetEntrySetId();
	}

	/**
	 * Returns the asset entry set likes count of this asset entry set.
	 *
	 * @return the asset entry set likes count of this asset entry set
	 */
	@Override
	public int getAssetEntrySetLikesCount() {
		return _assetEntrySet.getAssetEntrySetLikesCount();
	}

	@Override
	public java.util.List<AssetEntrySet> getChildAssetEntrySets() {
		return _assetEntrySet.getChildAssetEntrySets();
	}

	/**
	 * Returns the child asset entry sets count of this asset entry set.
	 *
	 * @return the child asset entry sets count of this asset entry set
	 */
	@Override
	public int getChildAssetEntrySetsCount() {
		return _assetEntrySet.getChildAssetEntrySetsCount();
	}

	/**
	 * Returns the fully qualified class name of this asset entry set.
	 *
	 * @return the fully qualified class name of this asset entry set
	 */
	@Override
	public String getClassName() {
		return _assetEntrySet.getClassName();
	}

	/**
	 * Returns the class name ID of this asset entry set.
	 *
	 * @return the class name ID of this asset entry set
	 */
	@Override
	public long getClassNameId() {
		return _assetEntrySet.getClassNameId();
	}

	/**
	 * Returns the class pk of this asset entry set.
	 *
	 * @return the class pk of this asset entry set
	 */
	@Override
	public long getClassPK() {
		return _assetEntrySet.getClassPK();
	}

	/**
	 * Returns the company ID of this asset entry set.
	 *
	 * @return the company ID of this asset entry set
	 */
	@Override
	public long getCompanyId() {
		return _assetEntrySet.getCompanyId();
	}

	/**
	 * Returns the create time of this asset entry set.
	 *
	 * @return the create time of this asset entry set
	 */
	@Override
	public long getCreateTime() {
		return _assetEntrySet.getCreateTime();
	}

	/**
	 * Returns the creator class name ID of this asset entry set.
	 *
	 * @return the creator class name ID of this asset entry set
	 */
	@Override
	public long getCreatorClassNameId() {
		return _assetEntrySet.getCreatorClassNameId();
	}

	/**
	 * Returns the creator class pk of this asset entry set.
	 *
	 * @return the creator class pk of this asset entry set
	 */
	@Override
	public long getCreatorClassPK() {
		return _assetEntrySet.getCreatorClassPK();
	}

	/**
	 * Returns the creator name of this asset entry set.
	 *
	 * @return the creator name of this asset entry set
	 */
	@Override
	public String getCreatorName() {
		return _assetEntrySet.getCreatorName();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _assetEntrySet.getExpandoBridge();
	}

	/**
	 * Returns the level of this asset entry set.
	 *
	 * @return the level of this asset entry set
	 */
	@Override
	public int getLevel() {
		return _assetEntrySet.getLevel();
	}

	/**
	 * Returns the modified time of this asset entry set.
	 *
	 * @return the modified time of this asset entry set
	 */
	@Override
	public long getModifiedTime() {
		return _assetEntrySet.getModifiedTime();
	}

	/**
	 * Returns the parent asset entry set ID of this asset entry set.
	 *
	 * @return the parent asset entry set ID of this asset entry set
	 */
	@Override
	public long getParentAssetEntrySetId() {
		return _assetEntrySet.getParentAssetEntrySetId();
	}

	/**
	 * Returns the payload of this asset entry set.
	 *
	 * @return the payload of this asset entry set
	 */
	@Override
	public String getPayload() {
		return _assetEntrySet.getPayload();
	}

	/**
	 * Returns the primary key of this asset entry set.
	 *
	 * @return the primary key of this asset entry set
	 */
	@Override
	public long getPrimaryKey() {
		return _assetEntrySet.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _assetEntrySet.getPrimaryKeyObj();
	}

	/**
	 * Returns the private asset entry set of this asset entry set.
	 *
	 * @return the private asset entry set of this asset entry set
	 */
	@Override
	public boolean getPrivateAssetEntrySet() {
		return _assetEntrySet.getPrivateAssetEntrySet();
	}

	/**
	 * Returns the status of this asset entry set.
	 *
	 * @return the status of this asset entry set
	 */
	@Override
	public int getStatus() {
		return _assetEntrySet.getStatus();
	}

	/**
	 * Returns the sticky time of this asset entry set.
	 *
	 * @return the sticky time of this asset entry set
	 */
	@Override
	public long getStickyTime() {
		return _assetEntrySet.getStickyTime();
	}

	/**
	 * Returns the title of this asset entry set.
	 *
	 * @return the title of this asset entry set
	 */
	@Override
	public String getTitle() {
		return _assetEntrySet.getTitle();
	}

	/**
	 * Returns the type of this asset entry set.
	 *
	 * @return the type of this asset entry set
	 */
	@Override
	public int getType() {
		return _assetEntrySet.getType();
	}

	/**
	 * Returns the user ID of this asset entry set.
	 *
	 * @return the user ID of this asset entry set
	 */
	@Override
	public long getUserId() {
		return _assetEntrySet.getUserId();
	}

	/**
	 * Returns the user uuid of this asset entry set.
	 *
	 * @return the user uuid of this asset entry set
	 */
	@Override
	public String getUserUuid() {
		return _assetEntrySet.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _assetEntrySet.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _assetEntrySet.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _assetEntrySet.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _assetEntrySet.isNew();
	}

	/**
	 * Returns <code>true</code> if this asset entry set is private asset entry set.
	 *
	 * @return <code>true</code> if this asset entry set is private asset entry set; <code>false</code> otherwise
	 */
	@Override
	public boolean isPrivateAssetEntrySet() {
		return _assetEntrySet.isPrivateAssetEntrySet();
	}

	@Override
	public void persist() {
		_assetEntrySet.persist();
	}

	/**
	 * Sets the asset entry ID of this asset entry set.
	 *
	 * @param assetEntryId the asset entry ID of this asset entry set
	 */
	@Override
	public void setAssetEntryId(long assetEntryId) {
		_assetEntrySet.setAssetEntryId(assetEntryId);
	}

	/**
	 * Sets the asset entry set ID of this asset entry set.
	 *
	 * @param assetEntrySetId the asset entry set ID of this asset entry set
	 */
	@Override
	public void setAssetEntrySetId(long assetEntrySetId) {
		_assetEntrySet.setAssetEntrySetId(assetEntrySetId);
	}

	/**
	 * Sets the asset entry set likes count of this asset entry set.
	 *
	 * @param assetEntrySetLikesCount the asset entry set likes count of this asset entry set
	 */
	@Override
	public void setAssetEntrySetLikesCount(int assetEntrySetLikesCount) {
		_assetEntrySet.setAssetEntrySetLikesCount(assetEntrySetLikesCount);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_assetEntrySet.setCachedModel(cachedModel);
	}

	@Override
	public void setChildAssetEntrySets(
			java.util.List<AssetEntrySet> childAssetEntrySets)
		throws com.liferay.portal.kernel.exception.PortalException {

		_assetEntrySet.setChildAssetEntrySets(childAssetEntrySets);
	}

	/**
	 * Sets the child asset entry sets count of this asset entry set.
	 *
	 * @param childAssetEntrySetsCount the child asset entry sets count of this asset entry set
	 */
	@Override
	public void setChildAssetEntrySetsCount(int childAssetEntrySetsCount) {
		_assetEntrySet.setChildAssetEntrySetsCount(childAssetEntrySetsCount);
	}

	@Override
	public void setClassName(String className) {
		_assetEntrySet.setClassName(className);
	}

	/**
	 * Sets the class name ID of this asset entry set.
	 *
	 * @param classNameId the class name ID of this asset entry set
	 */
	@Override
	public void setClassNameId(long classNameId) {
		_assetEntrySet.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this asset entry set.
	 *
	 * @param classPK the class pk of this asset entry set
	 */
	@Override
	public void setClassPK(long classPK) {
		_assetEntrySet.setClassPK(classPK);
	}

	/**
	 * Sets the company ID of this asset entry set.
	 *
	 * @param companyId the company ID of this asset entry set
	 */
	@Override
	public void setCompanyId(long companyId) {
		_assetEntrySet.setCompanyId(companyId);
	}

	/**
	 * Sets the create time of this asset entry set.
	 *
	 * @param createTime the create time of this asset entry set
	 */
	@Override
	public void setCreateTime(long createTime) {
		_assetEntrySet.setCreateTime(createTime);
	}

	/**
	 * Sets the creator class name ID of this asset entry set.
	 *
	 * @param creatorClassNameId the creator class name ID of this asset entry set
	 */
	@Override
	public void setCreatorClassNameId(long creatorClassNameId) {
		_assetEntrySet.setCreatorClassNameId(creatorClassNameId);
	}

	/**
	 * Sets the creator class pk of this asset entry set.
	 *
	 * @param creatorClassPK the creator class pk of this asset entry set
	 */
	@Override
	public void setCreatorClassPK(long creatorClassPK) {
		_assetEntrySet.setCreatorClassPK(creatorClassPK);
	}

	/**
	 * Sets the creator name of this asset entry set.
	 *
	 * @param creatorName the creator name of this asset entry set
	 */
	@Override
	public void setCreatorName(String creatorName) {
		_assetEntrySet.setCreatorName(creatorName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_assetEntrySet.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_assetEntrySet.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_assetEntrySet.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the level of this asset entry set.
	 *
	 * @param level the level of this asset entry set
	 */
	@Override
	public void setLevel(int level) {
		_assetEntrySet.setLevel(level);
	}

	/**
	 * Sets the modified time of this asset entry set.
	 *
	 * @param modifiedTime the modified time of this asset entry set
	 */
	@Override
	public void setModifiedTime(long modifiedTime) {
		_assetEntrySet.setModifiedTime(modifiedTime);
	}

	@Override
	public void setNew(boolean n) {
		_assetEntrySet.setNew(n);
	}

	/**
	 * Sets the parent asset entry set ID of this asset entry set.
	 *
	 * @param parentAssetEntrySetId the parent asset entry set ID of this asset entry set
	 */
	@Override
	public void setParentAssetEntrySetId(long parentAssetEntrySetId) {
		_assetEntrySet.setParentAssetEntrySetId(parentAssetEntrySetId);
	}

	/**
	 * Sets the payload of this asset entry set.
	 *
	 * @param payload the payload of this asset entry set
	 */
	@Override
	public void setPayload(String payload) {
		_assetEntrySet.setPayload(payload);
	}

	/**
	 * Sets the primary key of this asset entry set.
	 *
	 * @param primaryKey the primary key of this asset entry set
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_assetEntrySet.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_assetEntrySet.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets whether this asset entry set is private asset entry set.
	 *
	 * @param privateAssetEntrySet the private asset entry set of this asset entry set
	 */
	@Override
	public void setPrivateAssetEntrySet(boolean privateAssetEntrySet) {
		_assetEntrySet.setPrivateAssetEntrySet(privateAssetEntrySet);
	}

	/**
	 * Sets the status of this asset entry set.
	 *
	 * @param status the status of this asset entry set
	 */
	@Override
	public void setStatus(int status) {
		_assetEntrySet.setStatus(status);
	}

	/**
	 * Sets the sticky time of this asset entry set.
	 *
	 * @param stickyTime the sticky time of this asset entry set
	 */
	@Override
	public void setStickyTime(long stickyTime) {
		_assetEntrySet.setStickyTime(stickyTime);
	}

	/**
	 * Sets the title of this asset entry set.
	 *
	 * @param title the title of this asset entry set
	 */
	@Override
	public void setTitle(String title) {
		_assetEntrySet.setTitle(title);
	}

	/**
	 * Sets the type of this asset entry set.
	 *
	 * @param type the type of this asset entry set
	 */
	@Override
	public void setType(int type) {
		_assetEntrySet.setType(type);
	}

	/**
	 * Sets the user ID of this asset entry set.
	 *
	 * @param userId the user ID of this asset entry set
	 */
	@Override
	public void setUserId(long userId) {
		_assetEntrySet.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this asset entry set.
	 *
	 * @param userUuid the user uuid of this asset entry set
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_assetEntrySet.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<AssetEntrySet>
		toCacheModel() {

		return _assetEntrySet.toCacheModel();
	}

	@Override
	public AssetEntrySet toEscapedModel() {
		return new AssetEntrySetWrapper(_assetEntrySet.toEscapedModel());
	}

	@Override
	public String toString() {
		return _assetEntrySet.toString();
	}

	@Override
	public AssetEntrySet toUnescapedModel() {
		return new AssetEntrySetWrapper(_assetEntrySet.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _assetEntrySet.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntrySetWrapper)) {
			return false;
		}

		AssetEntrySetWrapper assetEntrySetWrapper = (AssetEntrySetWrapper)obj;

		if (Objects.equals(
				_assetEntrySet, assetEntrySetWrapper._assetEntrySet)) {

			return true;
		}

		return false;
	}

	@Override
	public AssetEntrySet getWrappedModel() {
		return _assetEntrySet;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _assetEntrySet.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _assetEntrySet.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_assetEntrySet.resetOriginalValues();
	}

	private final AssetEntrySet _assetEntrySet;

}