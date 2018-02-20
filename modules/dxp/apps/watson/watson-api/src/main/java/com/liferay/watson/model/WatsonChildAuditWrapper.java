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
 * This class is a wrapper for {@link WatsonChildAudit}.
 * </p>
 *
 * @author Steven Smith
 * @see WatsonChildAudit
 * @generated
 */
@ProviderType
public class WatsonChildAuditWrapper implements WatsonChildAudit,
	ModelWrapper<WatsonChildAudit> {
	public WatsonChildAuditWrapper(WatsonChildAudit watsonChildAudit) {
		_watsonChildAudit = watsonChildAudit;
	}

	@Override
	public Class<?> getModelClass() {
		return WatsonChildAudit.class;
	}

	@Override
	public String getModelClassName() {
		return WatsonChildAudit.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("watsonChildAuditId", getWatsonChildAuditId());
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
		attributes.put("watsonChildId", getWatsonChildId());
		attributes.put("dateAccepted", getDateAccepted());
		attributes.put("dateDischarged", getDateDischarged());
		attributes.put("dateFollowUp", getDateFollowUp());
		attributes.put("source", getSource());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long watsonChildAuditId = (Long)attributes.get("watsonChildAuditId");

		if (watsonChildAuditId != null) {
			setWatsonChildAuditId(watsonChildAuditId);
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

		Long watsonChildId = (Long)attributes.get("watsonChildId");

		if (watsonChildId != null) {
			setWatsonChildId(watsonChildId);
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
		return new WatsonChildAuditWrapper((WatsonChildAudit)_watsonChildAudit.clone());
	}

	@Override
	public int compareTo(WatsonChildAudit watsonChildAudit) {
		return _watsonChildAudit.compareTo(watsonChildAudit);
	}

	/**
	* Returns the birth country ID of this watson child audit.
	*
	* @return the birth country ID of this watson child audit
	*/
	@Override
	public long getBirthCountryId() {
		return _watsonChildAudit.getBirthCountryId();
	}

	/**
	* Returns the citizenship watson list type ID of this watson child audit.
	*
	* @return the citizenship watson list type ID of this watson child audit
	*/
	@Override
	public long getCitizenshipWatsonListTypeId() {
		return _watsonChildAudit.getCitizenshipWatsonListTypeId();
	}

	/**
	* Returns the company ID of this watson child audit.
	*
	* @return the company ID of this watson child audit
	*/
	@Override
	public long getCompanyId() {
		return _watsonChildAudit.getCompanyId();
	}

	/**
	* Returns the country watson list type ID of this watson child audit.
	*
	* @return the country watson list type ID of this watson child audit
	*/
	@Override
	public long getCountryWatsonListTypeId() {
		return _watsonChildAudit.getCountryWatsonListTypeId();
	}

	/**
	* Returns the create date of this watson child audit.
	*
	* @return the create date of this watson child audit
	*/
	@Override
	public Date getCreateDate() {
		return _watsonChildAudit.getCreateDate();
	}

	/**
	* Returns the date accepted of this watson child audit.
	*
	* @return the date accepted of this watson child audit
	*/
	@Override
	public Date getDateAccepted() {
		return _watsonChildAudit.getDateAccepted();
	}

	/**
	* Returns the date discharged of this watson child audit.
	*
	* @return the date discharged of this watson child audit
	*/
	@Override
	public Date getDateDischarged() {
		return _watsonChildAudit.getDateDischarged();
	}

	/**
	* Returns the date follow up of this watson child audit.
	*
	* @return the date follow up of this watson child audit
	*/
	@Override
	public Date getDateFollowUp() {
		return _watsonChildAudit.getDateFollowUp();
	}

	/**
	* Returns the discharge watson list type ID of this watson child audit.
	*
	* @return the discharge watson list type ID of this watson child audit
	*/
	@Override
	public long getDischargeWatsonListTypeId() {
		return _watsonChildAudit.getDischargeWatsonListTypeId();
	}

	/**
	* Returns the ethnicity watson list type ID of this watson child audit.
	*
	* @return the ethnicity watson list type ID of this watson child audit
	*/
	@Override
	public long getEthnicityWatsonListTypeId() {
		return _watsonChildAudit.getEthnicityWatsonListTypeId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _watsonChildAudit.getExpandoBridge();
	}

	/**
	* Returns the group ID of this watson child audit.
	*
	* @return the group ID of this watson child audit
	*/
	@Override
	public long getGroupId() {
		return _watsonChildAudit.getGroupId();
	}

	/**
	* Returns the modified date of this watson child audit.
	*
	* @return the modified date of this watson child audit
	*/
	@Override
	public Date getModifiedDate() {
		return _watsonChildAudit.getModifiedDate();
	}

	/**
	* Returns the original watson person ID of this watson child audit.
	*
	* @return the original watson person ID of this watson child audit
	*/
	@Override
	public long getOriginalWatsonPersonId() {
		return _watsonChildAudit.getOriginalWatsonPersonId();
	}

	/**
	* Returns the primary key of this watson child audit.
	*
	* @return the primary key of this watson child audit
	*/
	@Override
	public long getPrimaryKey() {
		return _watsonChildAudit.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _watsonChildAudit.getPrimaryKeyObj();
	}

	/**
	* Returns the sex watson list type ID of this watson child audit.
	*
	* @return the sex watson list type ID of this watson child audit
	*/
	@Override
	public long getSexWatsonListTypeId() {
		return _watsonChildAudit.getSexWatsonListTypeId();
	}

	/**
	* Returns the source of this watson child audit.
	*
	* @return the source of this watson child audit
	*/
	@Override
	public java.lang.String getSource() {
		return _watsonChildAudit.getSource();
	}

	/**
	* Returns the source subtype watson list type ID of this watson child audit.
	*
	* @return the source subtype watson list type ID of this watson child audit
	*/
	@Override
	public long getSourceSubtypeWatsonListTypeId() {
		return _watsonChildAudit.getSourceSubtypeWatsonListTypeId();
	}

	/**
	* Returns the source watson list type ID of this watson child audit.
	*
	* @return the source watson list type ID of this watson child audit
	*/
	@Override
	public long getSourceWatsonListTypeId() {
		return _watsonChildAudit.getSourceWatsonListTypeId();
	}

	/**
	* Returns the status of this watson child audit.
	*
	* @return the status of this watson child audit
	*/
	@Override
	public int getStatus() {
		return _watsonChildAudit.getStatus();
	}

	/**
	* Returns the type watson list type ID of this watson child audit.
	*
	* @return the type watson list type ID of this watson child audit
	*/
	@Override
	public long getTypeWatsonListTypeId() {
		return _watsonChildAudit.getTypeWatsonListTypeId();
	}

	/**
	* Returns the user ID of this watson child audit.
	*
	* @return the user ID of this watson child audit
	*/
	@Override
	public long getUserId() {
		return _watsonChildAudit.getUserId();
	}

	/**
	* Returns the user name of this watson child audit.
	*
	* @return the user name of this watson child audit
	*/
	@Override
	public java.lang.String getUserName() {
		return _watsonChildAudit.getUserName();
	}

	/**
	* Returns the user uuid of this watson child audit.
	*
	* @return the user uuid of this watson child audit
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _watsonChildAudit.getUserUuid();
	}

	/**
	* Returns the watson child audit ID of this watson child audit.
	*
	* @return the watson child audit ID of this watson child audit
	*/
	@Override
	public long getWatsonChildAuditId() {
		return _watsonChildAudit.getWatsonChildAuditId();
	}

	/**
	* Returns the watson child ID of this watson child audit.
	*
	* @return the watson child ID of this watson child audit
	*/
	@Override
	public long getWatsonChildId() {
		return _watsonChildAudit.getWatsonChildId();
	}

	@Override
	public int hashCode() {
		return _watsonChildAudit.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _watsonChildAudit.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _watsonChildAudit.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _watsonChildAudit.isNew();
	}

	@Override
	public void persist() {
		_watsonChildAudit.persist();
	}

	/**
	* Sets the birth country ID of this watson child audit.
	*
	* @param birthCountryId the birth country ID of this watson child audit
	*/
	@Override
	public void setBirthCountryId(long birthCountryId) {
		_watsonChildAudit.setBirthCountryId(birthCountryId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_watsonChildAudit.setCachedModel(cachedModel);
	}

	/**
	* Sets the citizenship watson list type ID of this watson child audit.
	*
	* @param citizenshipWatsonListTypeId the citizenship watson list type ID of this watson child audit
	*/
	@Override
	public void setCitizenshipWatsonListTypeId(long citizenshipWatsonListTypeId) {
		_watsonChildAudit.setCitizenshipWatsonListTypeId(citizenshipWatsonListTypeId);
	}

	/**
	* Sets the company ID of this watson child audit.
	*
	* @param companyId the company ID of this watson child audit
	*/
	@Override
	public void setCompanyId(long companyId) {
		_watsonChildAudit.setCompanyId(companyId);
	}

	/**
	* Sets the country watson list type ID of this watson child audit.
	*
	* @param countryWatsonListTypeId the country watson list type ID of this watson child audit
	*/
	@Override
	public void setCountryWatsonListTypeId(long countryWatsonListTypeId) {
		_watsonChildAudit.setCountryWatsonListTypeId(countryWatsonListTypeId);
	}

	/**
	* Sets the create date of this watson child audit.
	*
	* @param createDate the create date of this watson child audit
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_watsonChildAudit.setCreateDate(createDate);
	}

	/**
	* Sets the date accepted of this watson child audit.
	*
	* @param dateAccepted the date accepted of this watson child audit
	*/
	@Override
	public void setDateAccepted(Date dateAccepted) {
		_watsonChildAudit.setDateAccepted(dateAccepted);
	}

	/**
	* Sets the date discharged of this watson child audit.
	*
	* @param dateDischarged the date discharged of this watson child audit
	*/
	@Override
	public void setDateDischarged(Date dateDischarged) {
		_watsonChildAudit.setDateDischarged(dateDischarged);
	}

	/**
	* Sets the date follow up of this watson child audit.
	*
	* @param dateFollowUp the date follow up of this watson child audit
	*/
	@Override
	public void setDateFollowUp(Date dateFollowUp) {
		_watsonChildAudit.setDateFollowUp(dateFollowUp);
	}

	/**
	* Sets the discharge watson list type ID of this watson child audit.
	*
	* @param dischargeWatsonListTypeId the discharge watson list type ID of this watson child audit
	*/
	@Override
	public void setDischargeWatsonListTypeId(long dischargeWatsonListTypeId) {
		_watsonChildAudit.setDischargeWatsonListTypeId(dischargeWatsonListTypeId);
	}

	/**
	* Sets the ethnicity watson list type ID of this watson child audit.
	*
	* @param ethnicityWatsonListTypeId the ethnicity watson list type ID of this watson child audit
	*/
	@Override
	public void setEthnicityWatsonListTypeId(long ethnicityWatsonListTypeId) {
		_watsonChildAudit.setEthnicityWatsonListTypeId(ethnicityWatsonListTypeId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_watsonChildAudit.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_watsonChildAudit.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_watsonChildAudit.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this watson child audit.
	*
	* @param groupId the group ID of this watson child audit
	*/
	@Override
	public void setGroupId(long groupId) {
		_watsonChildAudit.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this watson child audit.
	*
	* @param modifiedDate the modified date of this watson child audit
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_watsonChildAudit.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_watsonChildAudit.setNew(n);
	}

	/**
	* Sets the original watson person ID of this watson child audit.
	*
	* @param originalWatsonPersonId the original watson person ID of this watson child audit
	*/
	@Override
	public void setOriginalWatsonPersonId(long originalWatsonPersonId) {
		_watsonChildAudit.setOriginalWatsonPersonId(originalWatsonPersonId);
	}

	/**
	* Sets the primary key of this watson child audit.
	*
	* @param primaryKey the primary key of this watson child audit
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_watsonChildAudit.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_watsonChildAudit.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sex watson list type ID of this watson child audit.
	*
	* @param sexWatsonListTypeId the sex watson list type ID of this watson child audit
	*/
	@Override
	public void setSexWatsonListTypeId(long sexWatsonListTypeId) {
		_watsonChildAudit.setSexWatsonListTypeId(sexWatsonListTypeId);
	}

	/**
	* Sets the source of this watson child audit.
	*
	* @param source the source of this watson child audit
	*/
	@Override
	public void setSource(java.lang.String source) {
		_watsonChildAudit.setSource(source);
	}

	/**
	* Sets the source subtype watson list type ID of this watson child audit.
	*
	* @param sourceSubtypeWatsonListTypeId the source subtype watson list type ID of this watson child audit
	*/
	@Override
	public void setSourceSubtypeWatsonListTypeId(
		long sourceSubtypeWatsonListTypeId) {
		_watsonChildAudit.setSourceSubtypeWatsonListTypeId(sourceSubtypeWatsonListTypeId);
	}

	/**
	* Sets the source watson list type ID of this watson child audit.
	*
	* @param sourceWatsonListTypeId the source watson list type ID of this watson child audit
	*/
	@Override
	public void setSourceWatsonListTypeId(long sourceWatsonListTypeId) {
		_watsonChildAudit.setSourceWatsonListTypeId(sourceWatsonListTypeId);
	}

	/**
	* Sets the status of this watson child audit.
	*
	* @param status the status of this watson child audit
	*/
	@Override
	public void setStatus(int status) {
		_watsonChildAudit.setStatus(status);
	}

	/**
	* Sets the type watson list type ID of this watson child audit.
	*
	* @param typeWatsonListTypeId the type watson list type ID of this watson child audit
	*/
	@Override
	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_watsonChildAudit.setTypeWatsonListTypeId(typeWatsonListTypeId);
	}

	/**
	* Sets the user ID of this watson child audit.
	*
	* @param userId the user ID of this watson child audit
	*/
	@Override
	public void setUserId(long userId) {
		_watsonChildAudit.setUserId(userId);
	}

	/**
	* Sets the user name of this watson child audit.
	*
	* @param userName the user name of this watson child audit
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_watsonChildAudit.setUserName(userName);
	}

	/**
	* Sets the user uuid of this watson child audit.
	*
	* @param userUuid the user uuid of this watson child audit
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_watsonChildAudit.setUserUuid(userUuid);
	}

	/**
	* Sets the watson child audit ID of this watson child audit.
	*
	* @param watsonChildAuditId the watson child audit ID of this watson child audit
	*/
	@Override
	public void setWatsonChildAuditId(long watsonChildAuditId) {
		_watsonChildAudit.setWatsonChildAuditId(watsonChildAuditId);
	}

	/**
	* Sets the watson child ID of this watson child audit.
	*
	* @param watsonChildId the watson child ID of this watson child audit
	*/
	@Override
	public void setWatsonChildId(long watsonChildId) {
		_watsonChildAudit.setWatsonChildId(watsonChildId);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<WatsonChildAudit> toCacheModel() {
		return _watsonChildAudit.toCacheModel();
	}

	@Override
	public WatsonChildAudit toEscapedModel() {
		return new WatsonChildAuditWrapper(_watsonChildAudit.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _watsonChildAudit.toString();
	}

	@Override
	public WatsonChildAudit toUnescapedModel() {
		return new WatsonChildAuditWrapper(_watsonChildAudit.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _watsonChildAudit.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WatsonChildAuditWrapper)) {
			return false;
		}

		WatsonChildAuditWrapper watsonChildAuditWrapper = (WatsonChildAuditWrapper)obj;

		if (Objects.equals(_watsonChildAudit,
					watsonChildAuditWrapper._watsonChildAudit)) {
			return true;
		}

		return false;
	}

	@Override
	public WatsonChildAudit getWrappedModel() {
		return _watsonChildAudit;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _watsonChildAudit.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _watsonChildAudit.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_watsonChildAudit.resetOriginalValues();
	}

	private final WatsonChildAudit _watsonChildAudit;
}