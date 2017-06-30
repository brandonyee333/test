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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.TrainingEventServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.TrainingEventServiceSoap
 * @generated
 */
public class TrainingEventSoap implements Serializable {
	public static TrainingEventSoap toSoapModel(TrainingEvent model) {
		TrainingEventSoap soapModel = new TrainingEventSoap();

		soapModel.setTrainingEventId(model.getTrainingEventId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setDDLRecordSetId(model.getDDLRecordSetId());
		soapModel.setPartnerEntryId(model.getPartnerEntryId());
		soapModel.setTrainingCertificateTemplateId(model.getTrainingCertificateTemplateId());
		soapModel.setTrainingCourseId(model.getTrainingCourseId());
		soapModel.setTrainingLocationId(model.getTrainingLocationId());
		soapModel.setName(model.getName());
		soapModel.setEmailAddress(model.getEmailAddress());
		soapModel.setPortalMinorVersion(model.getPortalMinorVersion());
		soapModel.setType(model.getType());
		soapModel.setLanguageId(model.getLanguageId());
		soapModel.setLocalizedSlides(model.getLocalizedSlides());
		soapModel.setTimeZoneId(model.getTimeZoneId());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setAddressId(model.getAddressId());
		soapModel.setMaxCustomers(model.getMaxCustomers());
		soapModel.setEnrollmentURL(model.getEnrollmentURL());

		return soapModel;
	}

	public static TrainingEventSoap[] toSoapModels(TrainingEvent[] models) {
		TrainingEventSoap[] soapModels = new TrainingEventSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrainingEventSoap[][] toSoapModels(TrainingEvent[][] models) {
		TrainingEventSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrainingEventSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrainingEventSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrainingEventSoap[] toSoapModels(List<TrainingEvent> models) {
		List<TrainingEventSoap> soapModels = new ArrayList<TrainingEventSoap>(models.size());

		for (TrainingEvent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrainingEventSoap[soapModels.size()]);
	}

	public TrainingEventSoap() {
	}

	public long getPrimaryKey() {
		return _trainingEventId;
	}

	public void setPrimaryKey(long pk) {
		setTrainingEventId(pk);
	}

	public long getTrainingEventId() {
		return _trainingEventId;
	}

	public void setTrainingEventId(long trainingEventId) {
		_trainingEventId = trainingEventId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getDDLRecordSetId() {
		return _DDLRecordSetId;
	}

	public void setDDLRecordSetId(long DDLRecordSetId) {
		_DDLRecordSetId = DDLRecordSetId;
	}

	public long getPartnerEntryId() {
		return _partnerEntryId;
	}

	public void setPartnerEntryId(long partnerEntryId) {
		_partnerEntryId = partnerEntryId;
	}

	public long getTrainingCertificateTemplateId() {
		return _trainingCertificateTemplateId;
	}

	public void setTrainingCertificateTemplateId(
		long trainingCertificateTemplateId) {
		_trainingCertificateTemplateId = trainingCertificateTemplateId;
	}

	public long getTrainingCourseId() {
		return _trainingCourseId;
	}

	public void setTrainingCourseId(long trainingCourseId) {
		_trainingCourseId = trainingCourseId;
	}

	public long getTrainingLocationId() {
		return _trainingLocationId;
	}

	public void setTrainingLocationId(long trainingLocationId) {
		_trainingLocationId = trainingLocationId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public int getPortalMinorVersion() {
		return _portalMinorVersion;
	}

	public void setPortalMinorVersion(int portalMinorVersion) {
		_portalMinorVersion = portalMinorVersion;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	public boolean getLocalizedSlides() {
		return _localizedSlides;
	}

	public boolean isLocalizedSlides() {
		return _localizedSlides;
	}

	public void setLocalizedSlides(boolean localizedSlides) {
		_localizedSlides = localizedSlides;
	}

	public String getTimeZoneId() {
		return _timeZoneId;
	}

	public void setTimeZoneId(String timeZoneId) {
		_timeZoneId = timeZoneId;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public long getAddressId() {
		return _addressId;
	}

	public void setAddressId(long addressId) {
		_addressId = addressId;
	}

	public int getMaxCustomers() {
		return _maxCustomers;
	}

	public void setMaxCustomers(int maxCustomers) {
		_maxCustomers = maxCustomers;
	}

	public String getEnrollmentURL() {
		return _enrollmentURL;
	}

	public void setEnrollmentURL(String enrollmentURL) {
		_enrollmentURL = enrollmentURL;
	}

	private long _trainingEventId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _DDLRecordSetId;
	private long _partnerEntryId;
	private long _trainingCertificateTemplateId;
	private long _trainingCourseId;
	private long _trainingLocationId;
	private String _name;
	private String _emailAddress;
	private int _portalMinorVersion;
	private int _type;
	private String _languageId;
	private boolean _localizedSlides;
	private String _timeZoneId;
	private Date _startDate;
	private Date _endDate;
	private long _addressId;
	private int _maxCustomers;
	private String _enrollmentURL;
}