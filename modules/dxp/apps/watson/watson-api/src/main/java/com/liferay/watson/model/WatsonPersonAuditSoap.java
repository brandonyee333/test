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

package com.liferay.watson.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Steven Smith
 * @generated
 */
@ProviderType
public class WatsonPersonAuditSoap implements Serializable {

	public static WatsonPersonAuditSoap toSoapModel(WatsonPersonAudit model) {
		WatsonPersonAuditSoap soapModel = new WatsonPersonAuditSoap();

		soapModel.setWatsonPersonAuditId(model.getWatsonPersonAuditId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBirthCountryId(model.getBirthCountryId());
		soapModel.setCitizenshipWatsonListTypeId(
			model.getCitizenshipWatsonListTypeId());
		soapModel.setCountryWatsonListTypeId(
			model.getCountryWatsonListTypeId());
		soapModel.setEthnicityWatsonListTypeId(
			model.getEthnicityWatsonListTypeId());
		soapModel.setEyesWatsonListTypeId(model.getEyesWatsonListTypeId());
		soapModel.setHairWatsonListTypeId(model.getHairWatsonListTypeId());
		soapModel.setOriginalWatsonPersonId(model.getOriginalWatsonPersonId());
		soapModel.setSexWatsonListTypeId(model.getSexWatsonListTypeId());
		soapModel.setTypeWatsonListTypeId(model.getTypeWatsonListTypeId());
		soapModel.setWatsonIncidentId(model.getWatsonIncidentId());
		soapModel.setWatsonPersonId(model.getWatsonPersonId());
		soapModel.setDescription(model.getDescription());
		soapModel.setImagePayload(model.getImagePayload());
		soapModel.setBirthDate(model.getBirthDate());
		soapModel.setDateAccepted(model.getDateAccepted());
		soapModel.setDateRescued(model.getDateRescued());
		soapModel.setStartAge(model.getStartAge());
		soapModel.setEndAge(model.getEndAge());
		soapModel.setOccupation(model.getOccupation());
		soapModel.setHeight(model.getHeight());
		soapModel.setWeight(model.getWeight());
		soapModel.setAccepted(model.isAccepted());
		soapModel.setRescued(model.isRescued());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonPersonAuditSoap[] toSoapModels(
		WatsonPersonAudit[] models) {

		WatsonPersonAuditSoap[] soapModels =
			new WatsonPersonAuditSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonPersonAuditSoap[][] toSoapModels(
		WatsonPersonAudit[][] models) {

		WatsonPersonAuditSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new WatsonPersonAuditSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonPersonAuditSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonPersonAuditSoap[] toSoapModels(
		List<WatsonPersonAudit> models) {

		List<WatsonPersonAuditSoap> soapModels =
			new ArrayList<WatsonPersonAuditSoap>(models.size());

		for (WatsonPersonAudit model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonPersonAuditSoap[soapModels.size()]);
	}

	public WatsonPersonAuditSoap() {
	}

	public long getPrimaryKey() {
		return _watsonPersonAuditId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonPersonAuditId(pk);
	}

	public long getWatsonPersonAuditId() {
		return _watsonPersonAuditId;
	}

	public void setWatsonPersonAuditId(long watsonPersonAuditId) {
		_watsonPersonAuditId = watsonPersonAuditId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public void setCitizenshipWatsonListTypeId(
		long citizenshipWatsonListTypeId) {

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

	public long getWatsonPersonId() {
		return _watsonPersonId;
	}

	public void setWatsonPersonId(long watsonPersonId) {
		_watsonPersonId = watsonPersonId;
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

	public Date getDateRescued() {
		return _dateRescued;
	}

	public void setDateRescued(Date dateRescued) {
		_dateRescued = dateRescued;
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

	public boolean getRescued() {
		return _rescued;
	}

	public boolean isRescued() {
		return _rescued;
	}

	public void setRescued(boolean rescued) {
		_rescued = rescued;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonPersonAuditId;
	private long _groupId;
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
	private long _watsonPersonId;
	private String _description;
	private String _imagePayload;
	private Date _birthDate;
	private Date _dateAccepted;
	private Date _dateRescued;
	private String _startAge;
	private String _endAge;
	private String _occupation;
	private String _height;
	private String _weight;
	private boolean _accepted;
	private boolean _rescued;
	private int _status;

}