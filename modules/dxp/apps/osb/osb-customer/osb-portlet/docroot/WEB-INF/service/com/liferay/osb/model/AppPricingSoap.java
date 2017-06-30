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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.AppPricingServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.AppPricingServiceSoap
 * @generated
 */
public class AppPricingSoap implements Serializable {
	public static AppPricingSoap toSoapModel(AppPricing model) {
		AppPricingSoap soapModel = new AppPricingSoap();

		soapModel.setAppPricingId(model.getAppPricingId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAppEntryId(model.getAppEntryId());
		soapModel.setAppVersionId(model.getAppVersionId());
		soapModel.setName(model.getName());
		soapModel.setCurrencyEntryId(model.getCurrencyEntryId());
		soapModel.setStandardSupportPrice(model.getStandardSupportPrice());
		soapModel.setDeveloperSupportPrice(model.getDeveloperSupportPrice());
		soapModel.setRank(model.getRank());

		return soapModel;
	}

	public static AppPricingSoap[] toSoapModels(AppPricing[] models) {
		AppPricingSoap[] soapModels = new AppPricingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AppPricingSoap[][] toSoapModels(AppPricing[][] models) {
		AppPricingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AppPricingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AppPricingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AppPricingSoap[] toSoapModels(List<AppPricing> models) {
		List<AppPricingSoap> soapModels = new ArrayList<AppPricingSoap>(models.size());

		for (AppPricing model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AppPricingSoap[soapModels.size()]);
	}

	public AppPricingSoap() {
	}

	public long getPrimaryKey() {
		return _appPricingId;
	}

	public void setPrimaryKey(long pk) {
		setAppPricingId(pk);
	}

	public long getAppPricingId() {
		return _appPricingId;
	}

	public void setAppPricingId(long appPricingId) {
		_appPricingId = appPricingId;
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

	public long getAppEntryId() {
		return _appEntryId;
	}

	public void setAppEntryId(long appEntryId) {
		_appEntryId = appEntryId;
	}

	public long getAppVersionId() {
		return _appVersionId;
	}

	public void setAppVersionId(long appVersionId) {
		_appVersionId = appVersionId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getCurrencyEntryId() {
		return _currencyEntryId;
	}

	public void setCurrencyEntryId(long currencyEntryId) {
		_currencyEntryId = currencyEntryId;
	}

	public double getStandardSupportPrice() {
		return _standardSupportPrice;
	}

	public void setStandardSupportPrice(double standardSupportPrice) {
		_standardSupportPrice = standardSupportPrice;
	}

	public double getDeveloperSupportPrice() {
		return _developerSupportPrice;
	}

	public void setDeveloperSupportPrice(double developerSupportPrice) {
		_developerSupportPrice = developerSupportPrice;
	}

	public int getRank() {
		return _rank;
	}

	public void setRank(int rank) {
		_rank = rank;
	}

	private long _appPricingId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _appEntryId;
	private long _appVersionId;
	private String _name;
	private long _currencyEntryId;
	private double _standardSupportPrice;
	private double _developerSupportPrice;
	private int _rank;
}