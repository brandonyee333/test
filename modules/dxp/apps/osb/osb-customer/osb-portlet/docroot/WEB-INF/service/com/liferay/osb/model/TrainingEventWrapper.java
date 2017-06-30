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
 * This class is a wrapper for {@link TrainingEvent}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingEvent
 * @generated
 */
public class TrainingEventWrapper implements TrainingEvent,
	ModelWrapper<TrainingEvent> {
	public TrainingEventWrapper(TrainingEvent trainingEvent) {
		_trainingEvent = trainingEvent;
	}

	public Class<?> getModelClass() {
		return TrainingEvent.class;
	}

	public String getModelClassName() {
		return TrainingEvent.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingEventId", getTrainingEventId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("DDLRecordSetId", getDDLRecordSetId());
		attributes.put("partnerEntryId", getPartnerEntryId());
		attributes.put("trainingCertificateTemplateId",
			getTrainingCertificateTemplateId());
		attributes.put("trainingCourseId", getTrainingCourseId());
		attributes.put("trainingLocationId", getTrainingLocationId());
		attributes.put("name", getName());
		attributes.put("emailAddress", getEmailAddress());
		attributes.put("portalMinorVersion", getPortalMinorVersion());
		attributes.put("type", getType());
		attributes.put("languageId", getLanguageId());
		attributes.put("localizedSlides", getLocalizedSlides());
		attributes.put("timeZoneId", getTimeZoneId());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("addressId", getAddressId());
		attributes.put("maxCustomers", getMaxCustomers());
		attributes.put("enrollmentURL", getEnrollmentURL());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingEventId = (Long)attributes.get("trainingEventId");

		if (trainingEventId != null) {
			setTrainingEventId(trainingEventId);
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

		Long DDLRecordSetId = (Long)attributes.get("DDLRecordSetId");

		if (DDLRecordSetId != null) {
			setDDLRecordSetId(DDLRecordSetId);
		}

		Long partnerEntryId = (Long)attributes.get("partnerEntryId");

		if (partnerEntryId != null) {
			setPartnerEntryId(partnerEntryId);
		}

		Long trainingCertificateTemplateId = (Long)attributes.get(
				"trainingCertificateTemplateId");

		if (trainingCertificateTemplateId != null) {
			setTrainingCertificateTemplateId(trainingCertificateTemplateId);
		}

		Long trainingCourseId = (Long)attributes.get("trainingCourseId");

		if (trainingCourseId != null) {
			setTrainingCourseId(trainingCourseId);
		}

		Long trainingLocationId = (Long)attributes.get("trainingLocationId");

		if (trainingLocationId != null) {
			setTrainingLocationId(trainingLocationId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String emailAddress = (String)attributes.get("emailAddress");

		if (emailAddress != null) {
			setEmailAddress(emailAddress);
		}

		Integer portalMinorVersion = (Integer)attributes.get(
				"portalMinorVersion");

		if (portalMinorVersion != null) {
			setPortalMinorVersion(portalMinorVersion);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String languageId = (String)attributes.get("languageId");

		if (languageId != null) {
			setLanguageId(languageId);
		}

		Boolean localizedSlides = (Boolean)attributes.get("localizedSlides");

		if (localizedSlides != null) {
			setLocalizedSlides(localizedSlides);
		}

		String timeZoneId = (String)attributes.get("timeZoneId");

		if (timeZoneId != null) {
			setTimeZoneId(timeZoneId);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Long addressId = (Long)attributes.get("addressId");

		if (addressId != null) {
			setAddressId(addressId);
		}

		Integer maxCustomers = (Integer)attributes.get("maxCustomers");

		if (maxCustomers != null) {
			setMaxCustomers(maxCustomers);
		}

		String enrollmentURL = (String)attributes.get("enrollmentURL");

		if (enrollmentURL != null) {
			setEnrollmentURL(enrollmentURL);
		}
	}

	/**
	* Returns the primary key of this training event.
	*
	* @return the primary key of this training event
	*/
	public long getPrimaryKey() {
		return _trainingEvent.getPrimaryKey();
	}

	/**
	* Sets the primary key of this training event.
	*
	* @param primaryKey the primary key of this training event
	*/
	public void setPrimaryKey(long primaryKey) {
		_trainingEvent.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the training event ID of this training event.
	*
	* @return the training event ID of this training event
	*/
	public long getTrainingEventId() {
		return _trainingEvent.getTrainingEventId();
	}

	/**
	* Sets the training event ID of this training event.
	*
	* @param trainingEventId the training event ID of this training event
	*/
	public void setTrainingEventId(long trainingEventId) {
		_trainingEvent.setTrainingEventId(trainingEventId);
	}

	/**
	* Returns the user ID of this training event.
	*
	* @return the user ID of this training event
	*/
	public long getUserId() {
		return _trainingEvent.getUserId();
	}

	/**
	* Sets the user ID of this training event.
	*
	* @param userId the user ID of this training event
	*/
	public void setUserId(long userId) {
		_trainingEvent.setUserId(userId);
	}

	/**
	* Returns the user uuid of this training event.
	*
	* @return the user uuid of this training event
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingEvent.getUserUuid();
	}

	/**
	* Sets the user uuid of this training event.
	*
	* @param userUuid the user uuid of this training event
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_trainingEvent.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this training event.
	*
	* @return the user name of this training event
	*/
	public java.lang.String getUserName() {
		return _trainingEvent.getUserName();
	}

	/**
	* Sets the user name of this training event.
	*
	* @param userName the user name of this training event
	*/
	public void setUserName(java.lang.String userName) {
		_trainingEvent.setUserName(userName);
	}

	/**
	* Returns the create date of this training event.
	*
	* @return the create date of this training event
	*/
	public java.util.Date getCreateDate() {
		return _trainingEvent.getCreateDate();
	}

	/**
	* Sets the create date of this training event.
	*
	* @param createDate the create date of this training event
	*/
	public void setCreateDate(java.util.Date createDate) {
		_trainingEvent.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this training event.
	*
	* @return the modified date of this training event
	*/
	public java.util.Date getModifiedDate() {
		return _trainingEvent.getModifiedDate();
	}

	/**
	* Sets the modified date of this training event.
	*
	* @param modifiedDate the modified date of this training event
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_trainingEvent.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the d d l record set ID of this training event.
	*
	* @return the d d l record set ID of this training event
	*/
	public long getDDLRecordSetId() {
		return _trainingEvent.getDDLRecordSetId();
	}

	/**
	* Sets the d d l record set ID of this training event.
	*
	* @param DDLRecordSetId the d d l record set ID of this training event
	*/
	public void setDDLRecordSetId(long DDLRecordSetId) {
		_trainingEvent.setDDLRecordSetId(DDLRecordSetId);
	}

	/**
	* Returns the partner entry ID of this training event.
	*
	* @return the partner entry ID of this training event
	*/
	public long getPartnerEntryId() {
		return _trainingEvent.getPartnerEntryId();
	}

	/**
	* Sets the partner entry ID of this training event.
	*
	* @param partnerEntryId the partner entry ID of this training event
	*/
	public void setPartnerEntryId(long partnerEntryId) {
		_trainingEvent.setPartnerEntryId(partnerEntryId);
	}

	/**
	* Returns the training certificate template ID of this training event.
	*
	* @return the training certificate template ID of this training event
	*/
	public long getTrainingCertificateTemplateId() {
		return _trainingEvent.getTrainingCertificateTemplateId();
	}

	/**
	* Sets the training certificate template ID of this training event.
	*
	* @param trainingCertificateTemplateId the training certificate template ID of this training event
	*/
	public void setTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) {
		_trainingEvent.setTrainingCertificateTemplateId(trainingCertificateTemplateId);
	}

	/**
	* Returns the training course ID of this training event.
	*
	* @return the training course ID of this training event
	*/
	public long getTrainingCourseId() {
		return _trainingEvent.getTrainingCourseId();
	}

	/**
	* Sets the training course ID of this training event.
	*
	* @param trainingCourseId the training course ID of this training event
	*/
	public void setTrainingCourseId(long trainingCourseId) {
		_trainingEvent.setTrainingCourseId(trainingCourseId);
	}

	/**
	* Returns the training location ID of this training event.
	*
	* @return the training location ID of this training event
	*/
	public long getTrainingLocationId() {
		return _trainingEvent.getTrainingLocationId();
	}

	/**
	* Sets the training location ID of this training event.
	*
	* @param trainingLocationId the training location ID of this training event
	*/
	public void setTrainingLocationId(long trainingLocationId) {
		_trainingEvent.setTrainingLocationId(trainingLocationId);
	}

	/**
	* Returns the name of this training event.
	*
	* @return the name of this training event
	*/
	public java.lang.String getName() {
		return _trainingEvent.getName();
	}

	/**
	* Sets the name of this training event.
	*
	* @param name the name of this training event
	*/
	public void setName(java.lang.String name) {
		_trainingEvent.setName(name);
	}

	/**
	* Returns the email address of this training event.
	*
	* @return the email address of this training event
	*/
	public java.lang.String getEmailAddress() {
		return _trainingEvent.getEmailAddress();
	}

	/**
	* Sets the email address of this training event.
	*
	* @param emailAddress the email address of this training event
	*/
	public void setEmailAddress(java.lang.String emailAddress) {
		_trainingEvent.setEmailAddress(emailAddress);
	}

	/**
	* Returns the portal minor version of this training event.
	*
	* @return the portal minor version of this training event
	*/
	public int getPortalMinorVersion() {
		return _trainingEvent.getPortalMinorVersion();
	}

	/**
	* Sets the portal minor version of this training event.
	*
	* @param portalMinorVersion the portal minor version of this training event
	*/
	public void setPortalMinorVersion(int portalMinorVersion) {
		_trainingEvent.setPortalMinorVersion(portalMinorVersion);
	}

	/**
	* Returns the type of this training event.
	*
	* @return the type of this training event
	*/
	public int getType() {
		return _trainingEvent.getType();
	}

	/**
	* Sets the type of this training event.
	*
	* @param type the type of this training event
	*/
	public void setType(int type) {
		_trainingEvent.setType(type);
	}

	/**
	* Returns the language ID of this training event.
	*
	* @return the language ID of this training event
	*/
	public java.lang.String getLanguageId() {
		return _trainingEvent.getLanguageId();
	}

	/**
	* Sets the language ID of this training event.
	*
	* @param languageId the language ID of this training event
	*/
	public void setLanguageId(java.lang.String languageId) {
		_trainingEvent.setLanguageId(languageId);
	}

	/**
	* Returns the localized slides of this training event.
	*
	* @return the localized slides of this training event
	*/
	public boolean getLocalizedSlides() {
		return _trainingEvent.getLocalizedSlides();
	}

	/**
	* Returns <code>true</code> if this training event is localized slides.
	*
	* @return <code>true</code> if this training event is localized slides; <code>false</code> otherwise
	*/
	public boolean isLocalizedSlides() {
		return _trainingEvent.isLocalizedSlides();
	}

	/**
	* Sets whether this training event is localized slides.
	*
	* @param localizedSlides the localized slides of this training event
	*/
	public void setLocalizedSlides(boolean localizedSlides) {
		_trainingEvent.setLocalizedSlides(localizedSlides);
	}

	/**
	* Returns the time zone ID of this training event.
	*
	* @return the time zone ID of this training event
	*/
	public java.lang.String getTimeZoneId() {
		return _trainingEvent.getTimeZoneId();
	}

	/**
	* Sets the time zone ID of this training event.
	*
	* @param timeZoneId the time zone ID of this training event
	*/
	public void setTimeZoneId(java.lang.String timeZoneId) {
		_trainingEvent.setTimeZoneId(timeZoneId);
	}

	/**
	* Returns the start date of this training event.
	*
	* @return the start date of this training event
	*/
	public java.util.Date getStartDate() {
		return _trainingEvent.getStartDate();
	}

	/**
	* Sets the start date of this training event.
	*
	* @param startDate the start date of this training event
	*/
	public void setStartDate(java.util.Date startDate) {
		_trainingEvent.setStartDate(startDate);
	}

	/**
	* Returns the end date of this training event.
	*
	* @return the end date of this training event
	*/
	public java.util.Date getEndDate() {
		return _trainingEvent.getEndDate();
	}

	/**
	* Sets the end date of this training event.
	*
	* @param endDate the end date of this training event
	*/
	public void setEndDate(java.util.Date endDate) {
		_trainingEvent.setEndDate(endDate);
	}

	/**
	* Returns the address ID of this training event.
	*
	* @return the address ID of this training event
	*/
	public long getAddressId() {
		return _trainingEvent.getAddressId();
	}

	/**
	* Sets the address ID of this training event.
	*
	* @param addressId the address ID of this training event
	*/
	public void setAddressId(long addressId) {
		_trainingEvent.setAddressId(addressId);
	}

	/**
	* Returns the max customers of this training event.
	*
	* @return the max customers of this training event
	*/
	public int getMaxCustomers() {
		return _trainingEvent.getMaxCustomers();
	}

	/**
	* Sets the max customers of this training event.
	*
	* @param maxCustomers the max customers of this training event
	*/
	public void setMaxCustomers(int maxCustomers) {
		_trainingEvent.setMaxCustomers(maxCustomers);
	}

	/**
	* Returns the enrollment u r l of this training event.
	*
	* @return the enrollment u r l of this training event
	*/
	public java.lang.String getEnrollmentURL() {
		return _trainingEvent.getEnrollmentURL();
	}

	/**
	* Sets the enrollment u r l of this training event.
	*
	* @param enrollmentURL the enrollment u r l of this training event
	*/
	public void setEnrollmentURL(java.lang.String enrollmentURL) {
		_trainingEvent.setEnrollmentURL(enrollmentURL);
	}

	public boolean isNew() {
		return _trainingEvent.isNew();
	}

	public void setNew(boolean n) {
		_trainingEvent.setNew(n);
	}

	public boolean isCachedModel() {
		return _trainingEvent.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_trainingEvent.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _trainingEvent.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _trainingEvent.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_trainingEvent.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _trainingEvent.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_trainingEvent.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TrainingEventWrapper((TrainingEvent)_trainingEvent.clone());
	}

	public int compareTo(com.liferay.osb.model.TrainingEvent trainingEvent) {
		return _trainingEvent.compareTo(trainingEvent);
	}

	@Override
	public int hashCode() {
		return _trainingEvent.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TrainingEvent> toCacheModel() {
		return _trainingEvent.toCacheModel();
	}

	public com.liferay.osb.model.TrainingEvent toEscapedModel() {
		return new TrainingEventWrapper(_trainingEvent.toEscapedModel());
	}

	public com.liferay.osb.model.TrainingEvent toUnescapedModel() {
		return new TrainingEventWrapper(_trainingEvent.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trainingEvent.toString();
	}

	public java.lang.String toXmlString() {
		return _trainingEvent.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingEvent.persist();
	}

	public com.liferay.portal.model.Address getAddress()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingEvent.getAddress();
	}

	public java.lang.String getAddressDisplayHTML()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingEvent.getAddressDisplayHTML();
	}

	public java.lang.String getCertificateCountDisplayHTML()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingEvent.getCertificateCountDisplayHTML();
	}

	public com.liferay.osb.model.PartnerEntry getPartnerEntry()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingEvent.getPartnerEntry();
	}

	public com.liferay.osb.model.TrainingCourse getTrainingCourse()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingEvent.getTrainingCourse();
	}

	public java.util.List<com.liferay.osb.model.TrainingCustomer> getTrainingCustomers()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _trainingEvent.getTrainingCustomers();
	}

	public com.liferay.osb.model.TrainingLocation getTrainingLocation()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingEvent.getTrainingLocation();
	}

	public java.lang.String getTrainingWorkerDisplayHTML()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingEvent.getTrainingWorkerDisplayHTML();
	}

	public java.lang.String getTrainingWorkersDisplayHTML()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _trainingEvent.getTrainingWorkersDisplayHTML();
	}

	public java.lang.String getTypeLabel() {
		return _trainingEvent.getTypeLabel();
	}

	public boolean isDurationSingleDay() {
		return _trainingEvent.isDurationSingleDay();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingEventWrapper)) {
			return false;
		}

		TrainingEventWrapper trainingEventWrapper = (TrainingEventWrapper)obj;

		if (Validator.equals(_trainingEvent, trainingEventWrapper._trainingEvent)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TrainingEvent getWrappedTrainingEvent() {
		return _trainingEvent;
	}

	public TrainingEvent getWrappedModel() {
		return _trainingEvent;
	}

	public void resetOriginalValues() {
		_trainingEvent.resetOriginalValues();
	}

	private TrainingEvent _trainingEvent;
}