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

package com.liferay.watson.model;

import aQute.bnd.annotation.ProviderType;

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
 * This class is a wrapper for {@link WatsonChild}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonChild
 * @generated
 */
@ProviderType
public class WatsonChildWrapper implements WatsonChild,
	ModelWrapper<WatsonChild> {
	public WatsonChildWrapper(WatsonChild watsonChild) {
		_watsonChild = watsonChild;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonChild.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonChild.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonChildId", getWatsonChildId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("birthCountryId", getBirthCountryId());
		attributes.put("citizenshipWatsonListTypeId",
			getCitizenshipWatsonListTypeId());
		attributes.put("countryWatsonListTypeId", getCountryWatsonListTypeId());
		attributes.put("dischargeWatsonListTypeId",
			getDischargeWatsonListTypeId());
		attributes.put("ethnicityWatsonListTypeId",
			getEthnicityWatsonListTypeId());
		attributes.put("originalWatsonPersonId", getOriginalWatsonPersonId());
		attributes.put("sexWatsonListTypeId", getSexWatsonListTypeId());
		attributes.put("sourceSubtypeWatsonListTypeId",
			getSourceSubtypeWatsonListTypeId());
		attributes.put("sourceWatsonListTypeId", getSourceWatsonListTypeId());
		attributes.put("typeWatsonListTypeId", getTypeWatsonListTypeId());
		attributes.put("dateAccepted", getDateAccepted());
		attributes.put("dateDischarged", getDateDischarged());
		attributes.put("dateFollowUp", getDateFollowUp());
		attributes.put("source", getSource());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonChildId = (Long)attributes.get("watsonChildId");

		if (watsonChildId != null) {
			setWatsonChildId(watsonChildId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

		Long birthCountryId = (Long)attributes.get("birthCountryId");

		if (birthCountryId != null) {
			setBirthCountryId(birthCountryId);
		}

		Long citizenshipWatsonListTypeId = (Long)attributes.get(
				"citizenshipWatsonListTypeId");

		if (citizenshipWatsonListTypeId != null) {
			setCitizenshipWatsonListTypeId(citizenshipWatsonListTypeId);
		}

		Long countryWatsonListTypeId = (Long)attributes.get(
				"countryWatsonListTypeId");

		if (countryWatsonListTypeId != null) {
			setCountryWatsonListTypeId(countryWatsonListTypeId);
		}

		Long dischargeWatsonListTypeId = (Long)attributes.get(
				"dischargeWatsonListTypeId");

		if (dischargeWatsonListTypeId != null) {
			setDischargeWatsonListTypeId(dischargeWatsonListTypeId);
		}

		Long ethnicityWatsonListTypeId = (Long)attributes.get(
				"ethnicityWatsonListTypeId");

		if (ethnicityWatsonListTypeId != null) {
			setEthnicityWatsonListTypeId(ethnicityWatsonListTypeId);
		}

		Long originalWatsonPersonId = (Long)attributes.get(
				"originalWatsonPersonId");

		if (originalWatsonPersonId != null) {
			setOriginalWatsonPersonId(originalWatsonPersonId);
		}

		Long sexWatsonListTypeId = (Long)attributes.get("sexWatsonListTypeId");

		if (sexWatsonListTypeId != null) {
			setSexWatsonListTypeId(sexWatsonListTypeId);
		}

		Long sourceSubtypeWatsonListTypeId = (Long)attributes.get(
				"sourceSubtypeWatsonListTypeId");

		if (sourceSubtypeWatsonListTypeId != null) {
			setSourceSubtypeWatsonListTypeId(sourceSubtypeWatsonListTypeId);
		}

		Long sourceWatsonListTypeId = (Long)attributes.get(
				"sourceWatsonListTypeId");

		if (sourceWatsonListTypeId != null) {
			setSourceWatsonListTypeId(sourceWatsonListTypeId);
		}

		Long typeWatsonListTypeId = (Long)attributes.get("typeWatsonListTypeId");

		if (typeWatsonListTypeId != null) {
			setTypeWatsonListTypeId(typeWatsonListTypeId);
		}

		Date dateAccepted = (Date)attributes.get("dateAccepted");

		if (dateAccepted != null) {
			setDateAccepted(dateAccepted);
		}

		Date dateDischarged = (Date)attributes.get("dateDischarged");

		if (dateDischarged != null) {
			setDateDischarged(dateDischarged);
		}

		Date dateFollowUp = (Date)attributes.get("dateFollowUp");

		if (dateFollowUp != null) {
			setDateFollowUp(dateFollowUp);
		}

		String source = (String)attributes.get("source");

		if (source != null) {
			setSource(source);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new WatsonChildWrapper((WatsonChild)_watsonChild.clone());
	}

	@Override
	public int compareTo(WatsonChild watsonChild) {
		return _watsonChild.compareTo(watsonChild);
	}

	/**
	* Returns the birth country ID of this watson child.
	*
	* @return the birth country ID of this watson child
	*/
	@Override
	public long getBirthCountryId() {
		return _watsonChild.getBirthCountryId();
	}

	/**
	* Returns the citizenship watson list type ID of this watson child.
	*
	* @return the citizenship watson list type ID of this watson child
	*/
	@Override
	public long getCitizenshipWatsonListTypeId() {
		return _watsonChild.getCitizenshipWatsonListTypeId();
	}

	/**
	* Returns the company ID of this watson child.
	*
	* @return the company ID of this watson child
	*/
	@Override
	public long getCompanyId() {
		return _watsonChild.getCompanyId();
	}

	/**
	* Returns the country watson list type ID of this watson child.
	*
	* @return the country watson list type ID of this watson child
	*/
	@Override
	public long getCountryWatsonListTypeId() {
		return _watsonChild.getCountryWatsonListTypeId();
	}

	/**
	* Returns the create date of this watson child.
	*
	* @return the create date of this watson child
	*/
	@Override
	public Date getCreateDate() {
		return _watsonChild.getCreateDate();
	}

	/**
	* Returns the date accepted of this watson child.
	*
	* @return the date accepted of this watson child
	*/
	@Override
	public Date getDateAccepted() {
		return _watsonChild.getDateAccepted();
	}

	/**
	* Returns the date discharged of this watson child.
	*
	* @return the date discharged of this watson child
	*/
	@Override
	public Date getDateDischarged() {
		return _watsonChild.getDateDischarged();
	}

	/**
	* Returns the date follow up of this watson child.
	*
	* @return the date follow up of this watson child
	*/
	@Override
	public Date getDateFollowUp() {
		return _watsonChild.getDateFollowUp();
	}

	/**
	* Returns the discharge watson list type ID of this watson child.
	*
	* @return the discharge watson list type ID of this watson child
	*/
	@Override
	public long getDischargeWatsonListTypeId() {
		return _watsonChild.getDischargeWatsonListTypeId();
	}

	/**
	* Returns the ethnicity watson list type ID of this watson child.
	*
	* @return the ethnicity watson list type ID of this watson child
	*/
	@Override
	public long getEthnicityWatsonListTypeId() {
		return _watsonChild.getEthnicityWatsonListTypeId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonChild.getExpandoBridge();
	}

	/**
	* Returns the group ID of this watson child.
	*
	* @return the group ID of this watson child
	*/
	@Override
	public long getGroupId() {
		return _watsonChild.getGroupId();
	}

	/**
	* Returns the modified date of this watson child.
	*
	* @return the modified date of this watson child
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonChild.getModifiedDate();
	}

	/**
	* Returns the original watson person ID of this watson child.
	*
	* @return the original watson person ID of this watson child
	*/
	@Override
	public long getOriginalWatsonPersonId() {
		return _watsonChild.getOriginalWatsonPersonId();
	}

	/**
	* Returns the primary key of this watson child.
	*
	* @return the primary key of this watson child
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonChild.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonChild.getPrimaryKeyObj();
	}

	/**
	* Returns the sex watson list type ID of this watson child.
	*
	* @return the sex watson list type ID of this watson child
	*/
	@Override
	public long getSexWatsonListTypeId() {
		return _watsonChild.getSexWatsonListTypeId();
	}

	/**
	* Returns the source of this watson child.
	*
	* @return the source of this watson child
	*/
	@Override
	public java.lang.String getSource() {
		return _watsonChild.getSource();
	}

	/**
	* Returns the source subtype watson list type ID of this watson child.
	*
	* @return the source subtype watson list type ID of this watson child
	*/
	@Override
	public long getSourceSubtypeWatsonListTypeId() {
		return _watsonChild.getSourceSubtypeWatsonListTypeId();
	}

	/**
	* Returns the source watson list type ID of this watson child.
	*
	* @return the source watson list type ID of this watson child
	*/
	@Override
	public long getSourceWatsonListTypeId() {
		return _watsonChild.getSourceWatsonListTypeId();
	}

	/**
	* Returns the status of this watson child.
	*
	* @return the status of this watson child
	*/
	@Override
	public int getStatus() {
		return _watsonChild.getStatus();
	}

	/**
	* Returns the type watson list type ID of this watson child.
	*
	* @return the type watson list type ID of this watson child
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonChild.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson child.
	*
	* @return the user ID of this watson child
	*/
	@Override
	public long getUserId() {
		return _watsonChild.getUserId();
	}

	/**
	* Returns the user name of this watson child.
	*
	* @return the user name of this watson child
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonChild.getUserName();
	}

	/**
	* Returns the user uuid of this watson child.
	*
	* @return the user uuid of this watson child
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonChild.getUserUuid();
	}

	/**
	* Returns the watson child ID of this watson child.
	*
	* @return the watson child ID of this watson child
	*/
	@Override
	public long getWatsonChildId() {
		return _watsonChild.getWatsonChildId();
	}

	@Override
	public int hashCode() {
		return _watsonChild.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonChild.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonChild.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonChild.isNew();
	}

	@Override
	public void persist() {
		_watsonChild.persist();
	}

	/**
	* Sets the birth country ID of this watson child.
	*
	* @param birthCountryId the birth country ID of this watson child
	*/
	@Override
	public void setBirthCountryId(long birthCountryId) {
		_watsonChild.setBirthCountryId(birthCountryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonChild.setCachedModel(cachedModel);
	}

	/**
	* Sets the citizenship watson list type ID of this watson child.
	*
	* @param citizenshipWatsonListTypeId the citizenship watson list type ID of this watson child
	*/
	@Override
	public void setCitizenshipWatsonListTypeId(long citizenshipWatsonListTypeId) {
		_watsonChild.setCitizenshipWatsonListTypeId(citizenshipWatsonListTypeId);
	}

	/**
	* Sets the company ID of this watson child.
	*
	* @param companyId the company ID of this watson child
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonChild.setCompanyId(companyId);
	}

	/**
	* Sets the country watson list type ID of this watson child.
	*
	* @param countryWatsonListTypeId the country watson list type ID of this watson child
	*/
	@Override
	public void setCountryWatsonListTypeId(long countryWatsonListTypeId) {
		_watsonChild.setCountryWatsonListTypeId(countryWatsonListTypeId);
	}

	/**
	* Sets the create date of this watson child.
	*
	* @param createDate the create date of this watson child
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonChild.setCreateDate(createDate);
	}

	/**
	* Sets the date accepted of this watson child.
	*
	* @param dateAccepted the date accepted of this watson child
	*/
	@Override
	public void setDateAccepted(Date dateAccepted) {
		_watsonChild.setDateAccepted(dateAccepted);
	}

	/**
	* Sets the date discharged of this watson child.
	*
	* @param dateDischarged the date discharged of this watson child
	*/
	@Override
	public void setDateDischarged(Date dateDischarged) {
		_watsonChild.setDateDischarged(dateDischarged);
	}

	/**
	* Sets the date follow up of this watson child.
	*
	* @param dateFollowUp the date follow up of this watson child
	*/
	@Override
	public void setDateFollowUp(Date dateFollowUp) {
		_watsonChild.setDateFollowUp(dateFollowUp);
	}

	/**
	* Sets the discharge watson list type ID of this watson child.
	*
	* @param dischargeWatsonListTypeId the discharge watson list type ID of this watson child
	*/
	@Override
	public void setDischargeWatsonListTypeId(long dischargeWatsonListTypeId) {
		_watsonChild.setDischargeWatsonListTypeId(dischargeWatsonListTypeId);
	}

	/**
	* Sets the ethnicity watson list type ID of this watson child.
	*
	* @param ethnicityWatsonListTypeId the ethnicity watson list type ID of this watson child
	*/
	@Override
	public void setEthnicityWatsonListTypeId(long ethnicityWatsonListTypeId) {
		_watsonChild.setEthnicityWatsonListTypeId(ethnicityWatsonListTypeId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonChild.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonChild.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonChild.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this watson child.
	*
	* @param groupId the group ID of this watson child
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonChild.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this watson child.
	*
	* @param modifiedDate the modified date of this watson child
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonChild.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonChild.setNew(n);
	}

	/**
	* Sets the original watson person ID of this watson child.
	*
	* @param originalWatsonPersonId the original watson person ID of this watson child
	*/
	@Override
	public void setOriginalWatsonPersonId(long originalWatsonPersonId) {
		_watsonChild.setOriginalWatsonPersonId(originalWatsonPersonId);
	}

	/**
	* Sets the primary key of this watson child.
	*
	* @param primaryKey the primary key of this watson child
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonChild.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonChild.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sex watson list type ID of this watson child.
	*
	* @param sexWatsonListTypeId the sex watson list type ID of this watson child
	*/
	@Override
	public void setSexWatsonListTypeId(long sexWatsonListTypeId) {
		_watsonChild.setSexWatsonListTypeId(sexWatsonListTypeId);
	}

	/**
	* Sets the source of this watson child.
	*
	* @param source the source of this watson child
	*/
	@Override
	public void setSource(java.lang.String source) {
		_watsonChild.setSource(source);
	}

	/**
	* Sets the source subtype watson list type ID of this watson child.
	*
	* @param sourceSubtypeWatsonListTypeId the source subtype watson list type ID of this watson child
	*/
	@Override
	public void setSourceSubtypeWatsonListTypeId(
		long sourceSubtypeWatsonListTypeId) {
		_watsonChild.setSourceSubtypeWatsonListTypeId(sourceSubtypeWatsonListTypeId);
	}

	/**
	* Sets the source watson list type ID of this watson child.
	*
	* @param sourceWatsonListTypeId the source watson list type ID of this watson child
	*/
	@Override
	public void setSourceWatsonListTypeId(long sourceWatsonListTypeId) {
		_watsonChild.setSourceWatsonListTypeId(sourceWatsonListTypeId);
	}

	/**
	* Sets the status of this watson child.
	*
	* @param status the status of this watson child
	*/
	@Override
	public void setStatus(int status) {
		_watsonChild.setStatus(status);
	}

	/**
	* Sets the type watson list type ID of this watson child.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson child
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonChild.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson child.
	*
	* @param userId the user ID of this watson child
	*/
	@Override
	public void setUserId(long userId) {
		_watsonChild.setUserId(userId);
	}

	/**
	* Sets the user name of this watson child.
	*
	* @param userName the user name of this watson child
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonChild.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson child.
	*
	* @param userUuid the user uuid of this watson child
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonChild.setUserUuid(userUuid);
	}

	/**
	* Sets the watson child ID of this watson child.
	*
	* @param watsonChildId the watson child ID of this watson child
	*/
	@Override
	public void setWatsonChildId(long watsonChildId) {
		_watsonChild.setWatsonChildId(watsonChildId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonChild> toCacheModel() {
		return _watsonChild.toCacheModel();
	}

	@Override
	public WatsonChild toEscapedModel() {
		return new WatsonChildWrapper(_watsonChild.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonChild.toString();
	}

	@Override
	public WatsonChild toUnescapedModel() {
		return new WatsonChildWrapper(_watsonChild.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonChild.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonChildWrapper)) {
			return false;
		}

		WatsonChildWrapper watsonChildWrapper = (WatsonChildWrapper)obj;

		if (Objects.equals(_watsonChild, watsonChildWrapper._watsonChild)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonChild getWrappedModel() {
		return _watsonChild;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonChild.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonChild.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonChild.resetOriginalValues();
	}

	private final WatsonChild _watsonChild;
}