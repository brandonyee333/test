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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.CountryAppPricingServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.CountryAppPricingServiceSoap
 * @generated
 */
public class CountryAppPricingSoap implements Serializable {
	public static CountryAppPricingSoap toSoapModel(CountryAppPricing model) {
		CountryAppPricingSoap soapModel = new CountryAppPricingSoap();

		soapModel.setCountryAppPricingId(model.getCountryAppPricingId());
		soapModel.setAppEntryId(model.getAppEntryId());
		soapModel.setAppVersionId(model.getAppVersionId());
		soapModel.setAppPricingId(model.getAppPricingId());
		soapModel.setCountryId(model.getCountryId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static CountryAppPricingSoap[] toSoapModels(
		CountryAppPricing[] models) {
		CountryAppPricingSoap[] soapModels = new CountryAppPricingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CountryAppPricingSoap[][] toSoapModels(
		CountryAppPricing[][] models) {
		CountryAppPricingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CountryAppPricingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CountryAppPricingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CountryAppPricingSoap[] toSoapModels(
		List<CountryAppPricing> models) {
		List<CountryAppPricingSoap> soapModels = new ArrayList<CountryAppPricingSoap>(models.size());

		for (CountryAppPricing model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CountryAppPricingSoap[soapModels.size()]);
	}

	public CountryAppPricingSoap() {
	}

	public long getPrimaryKey() {
		return _countryAppPricingId;
	}

	public void setPrimaryKey(long pk) {
		setCountryAppPricingId(pk);
	}

	public long getCountryAppPricingId() {
		return _countryAppPricingId;
	}

	public void setCountryAppPricingId(long countryAppPricingId) {
		_countryAppPricingId = countryAppPricingId;
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

	public long getAppPricingId() {
		return _appPricingId;
	}

	public void setAppPricingId(long appPricingId) {
		_appPricingId = appPricingId;
	}

	public long getCountryId() {
		return _countryId;
	}

	public void setCountryId(long countryId) {
		_countryId = countryId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _countryAppPricingId;
	private long _appEntryId;
	private long _appVersionId;
	private long _appPricingId;
	private long _countryId;
	private String _name;
}