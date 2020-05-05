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
 * This class is a wrapper for {@link SupportResponse}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportResponse
 * @generated
 */
@ProviderType
public class SupportResponseWrapper implements SupportResponse,
	ModelWrapper<SupportResponse> {
	public SupportResponseWrapper(SupportResponse supportResponse) {
		_supportResponse = supportResponse;
	}

	@Override
	public Class<?> getModelClass() {
		return SupportResponse.class;
	}

	@Override
	public String getModelClassName() {
		return SupportResponse.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportResponseId", getSupportResponseId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("supportLevel", getSupportLevel());
		attributes.put("severity1Response", getSeverity1Response());
		attributes.put("severity1Resolution", getSeverity1Resolution());
		attributes.put("severity2Response", getSeverity2Response());
		attributes.put("severity2Resolution", getSeverity2Resolution());
		attributes.put("severity3Response", getSeverity3Response());
		attributes.put("severity3Resolution", getSeverity3Resolution());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportResponseId = (Long)attributes.get("supportResponseId");

		if (supportResponseId != null) {
			setSupportResponseId(supportResponseId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Integer supportLevel = (Integer)attributes.get("supportLevel");

		if (supportLevel != null) {
			setSupportLevel(supportLevel);
		}

		Integer severity1Response = (Integer)attributes.get("severity1Response");

		if (severity1Response != null) {
			setSeverity1Response(severity1Response);
		}

		Integer severity1Resolution = (Integer)attributes.get(
				"severity1Resolution");

		if (severity1Resolution != null) {
			setSeverity1Resolution(severity1Resolution);
		}

		Integer severity2Response = (Integer)attributes.get("severity2Response");

		if (severity2Response != null) {
			setSeverity2Response(severity2Response);
		}

		Integer severity2Resolution = (Integer)attributes.get(
				"severity2Resolution");

		if (severity2Resolution != null) {
			setSeverity2Resolution(severity2Resolution);
		}

		Integer severity3Response = (Integer)attributes.get("severity3Response");

		if (severity3Response != null) {
			setSeverity3Response(severity3Response);
		}

		Integer severity3Resolution = (Integer)attributes.get(
				"severity3Resolution");

		if (severity3Resolution != null) {
			setSeverity3Resolution(severity3Resolution);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _supportResponse.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _supportResponse.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _supportResponse.isNew();
	}

	@Override
	public boolean isPlatinumLevel() {
		return _supportResponse.isPlatinumLevel();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _supportResponse.getExpandoBridge();
	}

	@Override
	public SupportResponse toEscapedModel() {
		return new SupportResponseWrapper(_supportResponse.toEscapedModel());
	}

	@Override
	public SupportResponse toUnescapedModel() {
		return new SupportResponseWrapper(_supportResponse.toUnescapedModel());
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SupportResponse> toCacheModel() {
		return _supportResponse.toCacheModel();
	}

	@Override
	public int compareTo(SupportResponse supportResponse) {
		return _supportResponse.compareTo(supportResponse);
	}

	/**
	* Returns the severity1 resolution of this support response.
	*
	* @return the severity1 resolution of this support response
	*/
	@Override
	public int getSeverity1Resolution() {
		return _supportResponse.getSeverity1Resolution();
	}

	/**
	* Returns the severity1 response of this support response.
	*
	* @return the severity1 response of this support response
	*/
	@Override
	public int getSeverity1Response() {
		return _supportResponse.getSeverity1Response();
	}

	/**
	* Returns the severity2 resolution of this support response.
	*
	* @return the severity2 resolution of this support response
	*/
	@Override
	public int getSeverity2Resolution() {
		return _supportResponse.getSeverity2Resolution();
	}

	/**
	* Returns the severity2 response of this support response.
	*
	* @return the severity2 response of this support response
	*/
	@Override
	public int getSeverity2Response() {
		return _supportResponse.getSeverity2Response();
	}

	/**
	* Returns the severity3 resolution of this support response.
	*
	* @return the severity3 resolution of this support response
	*/
	@Override
	public int getSeverity3Resolution() {
		return _supportResponse.getSeverity3Resolution();
	}

	/**
	* Returns the severity3 response of this support response.
	*
	* @return the severity3 response of this support response
	*/
	@Override
	public int getSeverity3Response() {
		return _supportResponse.getSeverity3Response();
	}

	@Override
	public int getSeverityResolution(int severityLevel) {
		return _supportResponse.getSeverityResolution(severityLevel);
	}

	@Override
	public int getSeverityResponse(int severityLevel) {
		return _supportResponse.getSeverityResponse(severityLevel);
	}

	/**
	* Returns the support level of this support response.
	*
	* @return the support level of this support response
	*/
	@Override
	public int getSupportLevel() {
		return _supportResponse.getSupportLevel();
	}

	@Override
	public int hashCode() {
		return _supportResponse.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _supportResponse.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SupportResponseWrapper((SupportResponse)_supportResponse.clone());
	}

	/**
	* Returns the name of this support response.
	*
	* @return the name of this support response
	*/
	@Override
	public java.lang.String getName() {
		return _supportResponse.getName();
	}

	@Override
	public java.lang.String getSupportLevelLabel() {
		return _supportResponse.getSupportLevelLabel();
	}

	@Override
	public java.lang.String getSupportLevelLabel(java.util.Locale locale) {
		return _supportResponse.getSupportLevelLabel(locale);
	}

	/**
	* Returns the user name of this support response.
	*
	* @return the user name of this support response
	*/
	@Override
	public java.lang.String getUserName() {
		return _supportResponse.getUserName();
	}

	/**
	* Returns the user uuid of this support response.
	*
	* @return the user uuid of this support response
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _supportResponse.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _supportResponse.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _supportResponse.toXmlString();
	}

	/**
	* Returns the create date of this support response.
	*
	* @return the create date of this support response
	*/
	@Override
	public Date getCreateDate() {
		return _supportResponse.getCreateDate();
	}

	/**
	* Returns the modified date of this support response.
	*
	* @return the modified date of this support response
	*/
	@Override
	public Date getModifiedDate() {
		return _supportResponse.getModifiedDate();
	}

	/**
	* Returns the primary key of this support response.
	*
	* @return the primary key of this support response
	*/
	@Override
	public long getPrimaryKey() {
		return _supportResponse.getPrimaryKey();
	}

	/**
	* Returns the support response ID of this support response.
	*
	* @return the support response ID of this support response
	*/
	@Override
	public long getSupportResponseId() {
		return _supportResponse.getSupportResponseId();
	}

	/**
	* Returns the user ID of this support response.
	*
	* @return the user ID of this support response
	*/
	@Override
	public long getUserId() {
		return _supportResponse.getUserId();
	}

	@Override
	public void persist() {
		_supportResponse.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_supportResponse.setCachedModel(cachedModel);
	}

	/**
	* Sets the create date of this support response.
	*
	* @param createDate the create date of this support response
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_supportResponse.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_supportResponse.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_supportResponse.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_supportResponse.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the modified date of this support response.
	*
	* @param modifiedDate the modified date of this support response
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_supportResponse.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this support response.
	*
	* @param name the name of this support response
	*/
	@Override
	public void setName(java.lang.String name) {
		_supportResponse.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_supportResponse.setNew(n);
	}

	/**
	* Sets the primary key of this support response.
	*
	* @param primaryKey the primary key of this support response
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_supportResponse.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_supportResponse.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the severity1 resolution of this support response.
	*
	* @param severity1Resolution the severity1 resolution of this support response
	*/
	@Override
	public void setSeverity1Resolution(int severity1Resolution) {
		_supportResponse.setSeverity1Resolution(severity1Resolution);
	}

	/**
	* Sets the severity1 response of this support response.
	*
	* @param severity1Response the severity1 response of this support response
	*/
	@Override
	public void setSeverity1Response(int severity1Response) {
		_supportResponse.setSeverity1Response(severity1Response);
	}

	/**
	* Sets the severity2 resolution of this support response.
	*
	* @param severity2Resolution the severity2 resolution of this support response
	*/
	@Override
	public void setSeverity2Resolution(int severity2Resolution) {
		_supportResponse.setSeverity2Resolution(severity2Resolution);
	}

	/**
	* Sets the severity2 response of this support response.
	*
	* @param severity2Response the severity2 response of this support response
	*/
	@Override
	public void setSeverity2Response(int severity2Response) {
		_supportResponse.setSeverity2Response(severity2Response);
	}

	/**
	* Sets the severity3 resolution of this support response.
	*
	* @param severity3Resolution the severity3 resolution of this support response
	*/
	@Override
	public void setSeverity3Resolution(int severity3Resolution) {
		_supportResponse.setSeverity3Resolution(severity3Resolution);
	}

	/**
	* Sets the severity3 response of this support response.
	*
	* @param severity3Response the severity3 response of this support response
	*/
	@Override
	public void setSeverity3Response(int severity3Response) {
		_supportResponse.setSeverity3Response(severity3Response);
	}

	/**
	* Sets the support level of this support response.
	*
	* @param supportLevel the support level of this support response
	*/
	@Override
	public void setSupportLevel(int supportLevel) {
		_supportResponse.setSupportLevel(supportLevel);
	}

	/**
	* Sets the support response ID of this support response.
	*
	* @param supportResponseId the support response ID of this support response
	*/
	@Override
	public void setSupportResponseId(long supportResponseId) {
		_supportResponse.setSupportResponseId(supportResponseId);
	}

	/**
	* Sets the user ID of this support response.
	*
	* @param userId the user ID of this support response
	*/
	@Override
	public void setUserId(long userId) {
		_supportResponse.setUserId(userId);
	}

	/**
	* Sets the user name of this support response.
	*
	* @param userName the user name of this support response
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_supportResponse.setUserName(userName);
	}

	/**
	* Sets the user uuid of this support response.
	*
	* @param userUuid the user uuid of this support response
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_supportResponse.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportResponseWrapper)) {
			return false;
		}

		SupportResponseWrapper supportResponseWrapper = (SupportResponseWrapper)obj;

		if (Objects.equals(_supportResponse,
					supportResponseWrapper._supportResponse)) {
			return true;
		}

		return false;
	}

	@Override
	public SupportResponse getWrappedModel() {
		return _supportResponse;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _supportResponse.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _supportResponse.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_supportResponse.resetOriginalValues();
	}

	private final SupportResponse _supportResponse;
}