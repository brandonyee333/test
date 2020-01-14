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

package com.liferay.osb.customer.admin.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LicenseEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntry
 * @generated
 */
public class LicenseEntryWrapper
	implements LicenseEntry, ModelWrapper<LicenseEntry> {

	public LicenseEntryWrapper(LicenseEntry licenseEntry) {
		_licenseEntry = licenseEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return LicenseEntry.class;
	}

	@Override
	public String getModelClassName() {
		return LicenseEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("licenseEntryId", getLicenseEntryId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("productEntryId", getProductEntryId());
		attributes.put("name", getName());
		attributes.put("type", getType());
		attributes.put("portalVersionMin", getPortalVersionMin());
		attributes.put("portalVersionMax", getPortalVersionMax());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long licenseEntryId = (Long)attributes.get("licenseEntryId");

		if (licenseEntryId != null) {
			setLicenseEntryId(licenseEntryId);
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

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long productEntryId = (Long)attributes.get("productEntryId");

		if (productEntryId != null) {
			setProductEntryId(productEntryId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer portalVersionMin = (Integer)attributes.get("portalVersionMin");

		if (portalVersionMin != null) {
			setPortalVersionMin(portalVersionMin);
		}

		Integer portalVersionMax = (Integer)attributes.get("portalVersionMax");

		if (portalVersionMax != null) {
			setPortalVersionMax(portalVersionMax);
		}
	}

	@Override
	public Object clone() {
		return new LicenseEntryWrapper((LicenseEntry)_licenseEntry.clone());
	}

	@Override
	public int compareTo(LicenseEntry licenseEntry) {
		return _licenseEntry.compareTo(licenseEntry);
	}

	/**
	 * Returns the create date of this license entry.
	 *
	 * @return the create date of this license entry
	 */
	@Override
	public Date getCreateDate() {
		return _licenseEntry.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _licenseEntry.getExpandoBridge();
	}

	/**
	 * Returns the license entry ID of this license entry.
	 *
	 * @return the license entry ID of this license entry
	 */
	@Override
	public long getLicenseEntryId() {
		return _licenseEntry.getLicenseEntryId();
	}

	/**
	 * Returns the modified date of this license entry.
	 *
	 * @return the modified date of this license entry
	 */
	@Override
	public Date getModifiedDate() {
		return _licenseEntry.getModifiedDate();
	}

	/**
	 * Returns the name of this license entry.
	 *
	 * @return the name of this license entry
	 */
	@Override
	public String getName() {
		return _licenseEntry.getName();
	}

	@Override
	public String getPortalVersionLabel() {
		return _licenseEntry.getPortalVersionLabel();
	}

	/**
	 * Returns the portal version max of this license entry.
	 *
	 * @return the portal version max of this license entry
	 */
	@Override
	public int getPortalVersionMax() {
		return _licenseEntry.getPortalVersionMax();
	}

	/**
	 * Returns the portal version min of this license entry.
	 *
	 * @return the portal version min of this license entry
	 */
	@Override
	public int getPortalVersionMin() {
		return _licenseEntry.getPortalVersionMin();
	}

	/**
	 * Returns the primary key of this license entry.
	 *
	 * @return the primary key of this license entry
	 */
	@Override
	public long getPrimaryKey() {
		return _licenseEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _licenseEntry.getPrimaryKeyObj();
	}

	/**
	 * Returns the product entry ID of this license entry.
	 *
	 * @return the product entry ID of this license entry
	 */
	@Override
	public long getProductEntryId() {
		return _licenseEntry.getProductEntryId();
	}

	/**
	 * Returns the type of this license entry.
	 *
	 * @return the type of this license entry
	 */
	@Override
	public String getType() {
		return _licenseEntry.getType();
	}

	/**
	 * Returns the user ID of this license entry.
	 *
	 * @return the user ID of this license entry
	 */
	@Override
	public long getUserId() {
		return _licenseEntry.getUserId();
	}

	/**
	 * Returns the user name of this license entry.
	 *
	 * @return the user name of this license entry
	 */
	@Override
	public String getUserName() {
		return _licenseEntry.getUserName();
	}

	/**
	 * Returns the user uuid of this license entry.
	 *
	 * @return the user uuid of this license entry
	 */
	@Override
	public String getUserUuid() {
		return _licenseEntry.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _licenseEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _licenseEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _licenseEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _licenseEntry.isNew();
	}

	@Override
	public void persist() {
		_licenseEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_licenseEntry.setCachedModel(cachedModel);
	}

	/**
	 * Sets the create date of this license entry.
	 *
	 * @param createDate the create date of this license entry
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_licenseEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_licenseEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_licenseEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_licenseEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the license entry ID of this license entry.
	 *
	 * @param licenseEntryId the license entry ID of this license entry
	 */
	@Override
	public void setLicenseEntryId(long licenseEntryId) {
		_licenseEntry.setLicenseEntryId(licenseEntryId);
	}

	/**
	 * Sets the modified date of this license entry.
	 *
	 * @param modifiedDate the modified date of this license entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_licenseEntry.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this license entry.
	 *
	 * @param name the name of this license entry
	 */
	@Override
	public void setName(String name) {
		_licenseEntry.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_licenseEntry.setNew(n);
	}

	/**
	 * Sets the portal version max of this license entry.
	 *
	 * @param portalVersionMax the portal version max of this license entry
	 */
	@Override
	public void setPortalVersionMax(int portalVersionMax) {
		_licenseEntry.setPortalVersionMax(portalVersionMax);
	}

	/**
	 * Sets the portal version min of this license entry.
	 *
	 * @param portalVersionMin the portal version min of this license entry
	 */
	@Override
	public void setPortalVersionMin(int portalVersionMin) {
		_licenseEntry.setPortalVersionMin(portalVersionMin);
	}

	/**
	 * Sets the primary key of this license entry.
	 *
	 * @param primaryKey the primary key of this license entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_licenseEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_licenseEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the product entry ID of this license entry.
	 *
	 * @param productEntryId the product entry ID of this license entry
	 */
	@Override
	public void setProductEntryId(long productEntryId) {
		_licenseEntry.setProductEntryId(productEntryId);
	}

	/**
	 * Sets the type of this license entry.
	 *
	 * @param type the type of this license entry
	 */
	@Override
	public void setType(String type) {
		_licenseEntry.setType(type);
	}

	/**
	 * Sets the user ID of this license entry.
	 *
	 * @param userId the user ID of this license entry
	 */
	@Override
	public void setUserId(long userId) {
		_licenseEntry.setUserId(userId);
	}

	/**
	 * Sets the user name of this license entry.
	 *
	 * @param userName the user name of this license entry
	 */
	@Override
	public void setUserName(String userName) {
		_licenseEntry.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this license entry.
	 *
	 * @param userUuid the user uuid of this license entry
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_licenseEntry.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LicenseEntry>
		toCacheModel() {

		return _licenseEntry.toCacheModel();
	}

	@Override
	public LicenseEntry toEscapedModel() {
		return new LicenseEntryWrapper(_licenseEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _licenseEntry.toString();
	}

	@Override
	public LicenseEntry toUnescapedModel() {
		return new LicenseEntryWrapper(_licenseEntry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _licenseEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LicenseEntryWrapper)) {
			return false;
		}

		LicenseEntryWrapper licenseEntryWrapper = (LicenseEntryWrapper)obj;

		if (Objects.equals(_licenseEntry, licenseEntryWrapper._licenseEntry)) {
			return true;
		}

		return false;
	}

	@Override
	public LicenseEntry getWrappedModel() {
		return _licenseEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _licenseEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _licenseEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_licenseEntry.resetOriginalValues();
	}

	private final LicenseEntry _licenseEntry;

}