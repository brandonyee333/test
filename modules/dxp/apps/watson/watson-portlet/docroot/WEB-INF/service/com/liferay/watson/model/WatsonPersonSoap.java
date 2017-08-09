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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Eddie Olson
 * @generated
 */
@ProviderType
public class WatsonPersonSoap implements Serializable {
	public static WatsonPersonSoap toSoapModel(WatsonPerson model) {
		WatsonPersonSoap soapModel = new WatsonPersonSoap();

		soapModel.setWatsonPersonId(model.getWatsonPersonId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBirthCountryId(model.getBirthCountryId());
		soapModel.setCitizenshipWatsonListTypeId(model.getCitizenshipWatsonListTypeId());
		soapModel.setCountryWatsonListTypeId(model.getCountryWatsonListTypeId());
		soapModel.setEthnicityWatsonListTypeId(model.getEthnicityWatsonListTypeId());
		soapModel.setEyesWatsonListTypeId(model.getEyesWatsonListTypeId());
		soapModel.setHairWatsonListTypeId(model.getHairWatsonListTypeId());
		soapModel.setOriginalWatsonPersonId(model.getOriginalWatsonPersonId());
		soapModel.setSexWatsonListTypeId(model.getSexWatsonListTypeId());
		soapModel.setTypeWatsonListTypeId(model.getTypeWatsonListTypeId());
		soapModel.setWatsonIncidentId(model.getWatsonIncidentId());
		soapModel.setDescription(model.getDescription());
		soapModel.setImagePayload(model.getImagePayload());
		soapModel.setBirthDate(model.getBirthDate());
		soapModel.setDateAccepted(model.getDateAccepted());
		soapModel.setStartAge(model.getStartAge());
		soapModel.setEndAge(model.getEndAge());
		soapModel.setOccupation(model.getOccupation());
		soapModel.setHeight(model.getHeight());
		soapModel.setWeight(model.getWeight());
		soapModel.setAccepted(model.getAccepted());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonPersonSoap[] toSoapModels(WatsonPerson[] models) {
		WatsonPersonSoap[] soapModels = new WatsonPersonSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonPersonSoap[][] toSoapModels(WatsonPerson[][] models) {
		WatsonPersonSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonPersonSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonPersonSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonPersonSoap[] toSoapModels(List<WatsonPerson> models) {
		List<WatsonPersonSoap> soapModels = new ArrayList<WatsonPersonSoap>(models.size());

		for (WatsonPerson model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonPersonSoap[soapModels.size()]);
	}

	public WatsonPersonSoap() {
	}

	public long getPrimaryKey() {
		return _watsonPersonId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonPersonId(pk);
	}

	public long getWatsonPersonId() {
		return _watsonPersonId;
	}

	public void setWatsonPersonId(long watsonPersonId) {
		_watsonPersonId = watsonPersonId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
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

	public long getBirthCountryId() {
		return _birthCountryId;
	}

	public void setBirthCountryId(long birthCountryId) {
		_birthCountryId = birthCountryId;
	}

	public long getCitizenshipWatsonListTypeId() {
		return _citizenshipWatsonListTypeId;
	}

	public void setCitizenshipWatsonListTypeId(long citizenshipWatsonListTypeId) {
		_citizenshipWatsonListTypeId = citizenshipWatsonListTypeId;
	}

	public long getCountryWatsonListTypeId() {
		return _countryWatsonListTypeId;
	}

	public void setCountryWatsonListTypeId(long countryWatsonListTypeId) {
		_countryWatsonListTypeId = countryWatsonListTypeId;
	}

	public long getEthnicityWatsonListTypeId() {
		return _ethnicityWatsonListTypeId;
	}

	public void setEthnicityWatsonListTypeId(long ethnicityWatsonListTypeId) {
		_ethnicityWatsonListTypeId = ethnicityWatsonListTypeId;
	}

	public long getEyesWatsonListTypeId() {
		return _eyesWatsonListTypeId;
	}

	public void setEyesWatsonListTypeId(long eyesWatsonListTypeId) {
		_eyesWatsonListTypeId = eyesWatsonListTypeId;
	}

	public long getHairWatsonListTypeId() {
		return _hairWatsonListTypeId;
	}

	public void setHairWatsonListTypeId(long hairWatsonListTypeId) {
		_hairWatsonListTypeId = hairWatsonListTypeId;
	}

	public long getOriginalWatsonPersonId() {
		return _originalWatsonPersonId;
	}

	public void setOriginalWatsonPersonId(long originalWatsonPersonId) {
		_originalWatsonPersonId = originalWatsonPersonId;
	}

	public long getSexWatsonListTypeId() {
		return _sexWatsonListTypeId;
	}

	public void setSexWatsonListTypeId(long sexWatsonListTypeId) {
		_sexWatsonListTypeId = sexWatsonListTypeId;
	}

	public long getTypeWatsonListTypeId() {
		return _typeWatsonListTypeId;
	}

	public void setTypeWatsonListTypeId(long typeWatsonListTypeId) {
		_typeWatsonListTypeId = typeWatsonListTypeId;
	}

	public long getWatsonIncidentId() {
		return _watsonIncidentId;
	}

	public void setWatsonIncidentId(long watsonIncidentId) {
		_watsonIncidentId = watsonIncidentId;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getImagePayload() {
		return _imagePayload;
	}

	public void setImagePayload(String imagePayload) {
		_imagePayload = imagePayload;
	}

	public Date getBirthDate() {
		return _birthDate;
	}

	public void setBirthDate(Date birthDate) {
		_birthDate = birthDate;
	}

	public Date getDateAccepted() {
		return _dateAccepted;
	}

	public void setDateAccepted(Date dateAccepted) {
		_dateAccepted = dateAccepted;
	}

	public String getStartAge() {
		return _startAge;
	}

	public void setStartAge(String startAge) {
		_startAge = startAge;
	}

	public String getEndAge() {
		return _endAge;
	}

	public void setEndAge(String endAge) {
		_endAge = endAge;
	}

	public String getOccupation() {
		return _occupation;
	}

	public void setOccupation(String occupation) {
		_occupation = occupation;
	}

	public String getHeight() {
		return _height;
	}

	public void setHeight(String height) {
		_height = height;
	}

	public String getWeight() {
		return _weight;
	}

	public void setWeight(String weight) {
		_weight = weight;
	}

	public boolean getAccepted() {
		return _accepted;
	}

	public boolean isAccepted() {
		return _accepted;
	}

	public void setAccepted(boolean accepted) {
		_accepted = accepted;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonPersonId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _birthCountryId;
	private long _citizenshipWatsonListTypeId;
	private long _countryWatsonListTypeId;
	private long _ethnicityWatsonListTypeId;
	private long _eyesWatsonListTypeId;
	private long _hairWatsonListTypeId;
	private long _originalWatsonPersonId;
	private long _sexWatsonListTypeId;
	private long _typeWatsonListTypeId;
	private long _watsonIncidentId;
	private String _description;
	private String _imagePayload;
	private Date _birthDate;
	private Date _dateAccepted;
	private String _startAge;
	private String _endAge;
	private String _occupation;
	private String _height;
	private String _weight;
	private boolean _accepted;
	private int _status;
}