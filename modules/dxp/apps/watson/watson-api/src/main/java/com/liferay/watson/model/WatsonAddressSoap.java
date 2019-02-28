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
public class WatsonAddressSoap implements Serializable {

	public static WatsonAddressSoap toSoapModel(WatsonAddress model) {
		WatsonAddressSoap soapModel = new WatsonAddressSoap();

		soapModel.setWatsonAddressId(model.getWatsonAddressId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCountryId(model.getCountryId());
		soapModel.setDistrictWatsonListTypeId(
			model.getDistrictWatsonListTypeId());
		soapModel.setOriginalWatsonAddressId(
			model.getOriginalWatsonAddressId());
		soapModel.setProvinceWatsonListTypeId(
			model.getProvinceWatsonListTypeId());
		soapModel.setSubDistrictWatsonListTypeId(
			model.getSubDistrictWatsonListTypeId());
		soapModel.setTypeWatsonListTypeId(model.getTypeWatsonListTypeId());
		soapModel.setWatsonIncidentId(model.getWatsonIncidentId());
		soapModel.setName(model.getName());
		soapModel.setPostalCode(model.getPostalCode());
		soapModel.setRegion(model.getRegion());
		soapModel.setStreet(model.getStreet());
		soapModel.setNumber(model.getNumber());
		soapModel.setBuilding(model.getBuilding());
		soapModel.setFloor(model.getFloor());
		soapModel.setRoom(model.getRoom());
		soapModel.setDescription(model.getDescription());
		soapModel.setImagePayload(model.getImagePayload());
		soapModel.setOtherType(model.getOtherType());
		soapModel.setLastSeenDate(model.getLastSeenDate());
		soapModel.setLatitude(model.getLatitude());
		soapModel.setLongitude(model.getLongitude());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static WatsonAddressSoap[] toSoapModels(WatsonAddress[] models) {
		WatsonAddressSoap[] soapModels = new WatsonAddressSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WatsonAddressSoap[][] toSoapModels(WatsonAddress[][] models) {
		WatsonAddressSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WatsonAddressSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WatsonAddressSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WatsonAddressSoap[] toSoapModels(List<WatsonAddress> models) {
		List<WatsonAddressSoap> soapModels = new ArrayList<WatsonAddressSoap>(
			models.size());

		for (WatsonAddress model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WatsonAddressSoap[soapModels.size()]);
	}

	public WatsonAddressSoap() {
	}

	public long getPrimaryKey() {
		return _watsonAddressId;
	}

	public void setPrimaryKey(long pk) {
		setWatsonAddressId(pk);
	}

	public long getWatsonAddressId() {
		return _watsonAddressId;
	}

	public void setWatsonAddressId(long watsonAddressId) {
		_watsonAddressId = watsonAddressId;
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

	public long getCountryId() {
		return _countryId;
	}

	public void setCountryId(long countryId) {
		_countryId = countryId;
	}

	public long getDistrictWatsonListTypeId() {
		return _districtWatsonListTypeId;
	}

	public void setDistrictWatsonListTypeId(long districtWatsonListTypeId) {
		_districtWatsonListTypeId = districtWatsonListTypeId;
	}

	public long getOriginalWatsonAddressId() {
		return _originalWatsonAddressId;
	}

	public void setOriginalWatsonAddressId(long originalWatsonAddressId) {
		_originalWatsonAddressId = originalWatsonAddressId;
	}

	public long getProvinceWatsonListTypeId() {
		return _provinceWatsonListTypeId;
	}

	public void setProvinceWatsonListTypeId(long provinceWatsonListTypeId) {
		_provinceWatsonListTypeId = provinceWatsonListTypeId;
	}

	public long getSubDistrictWatsonListTypeId() {
		return _subDistrictWatsonListTypeId;
	}

	public void setSubDistrictWatsonListTypeId(
		long subDistrictWatsonListTypeId) {

		_subDistrictWatsonListTypeId = subDistrictWatsonListTypeId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getPostalCode() {
		return _postalCode;
	}

	public void setPostalCode(String postalCode) {
		_postalCode = postalCode;
	}

	public String getRegion() {
		return _region;
	}

	public void setRegion(String region) {
		_region = region;
	}

	public String getStreet() {
		return _street;
	}

	public void setStreet(String street) {
		_street = street;
	}

	public String getNumber() {
		return _number;
	}

	public void setNumber(String number) {
		_number = number;
	}

	public String getBuilding() {
		return _building;
	}

	public void setBuilding(String building) {
		_building = building;
	}

	public String getFloor() {
		return _floor;
	}

	public void setFloor(String floor) {
		_floor = floor;
	}

	public String getRoom() {
		return _room;
	}

	public void setRoom(String room) {
		_room = room;
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

	public String getOtherType() {
		return _otherType;
	}

	public void setOtherType(String otherType) {
		_otherType = otherType;
	}

	public Date getLastSeenDate() {
		return _lastSeenDate;
	}

	public void setLastSeenDate(Date lastSeenDate) {
		_lastSeenDate = lastSeenDate;
	}

	public double getLatitude() {
		return _latitude;
	}

	public void setLatitude(double latitude) {
		_latitude = latitude;
	}

	public double getLongitude() {
		return _longitude;
	}

	public void setLongitude(double longitude) {
		_longitude = longitude;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _watsonAddressId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _countryId;
	private long _districtWatsonListTypeId;
	private long _originalWatsonAddressId;
	private long _provinceWatsonListTypeId;
	private long _subDistrictWatsonListTypeId;
	private long _typeWatsonListTypeId;
	private long _watsonIncidentId;
	private String _name;
	private String _postalCode;
	private String _region;
	private String _street;
	private String _number;
	private String _building;
	private String _floor;
	private String _room;
	private String _description;
	private String _imagePayload;
	private String _otherType;
	private Date _lastSeenDate;
	private double _latitude;
	private double _longitude;
	private int _status;

}