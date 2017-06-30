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
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.service.http.CurrencyEntryServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.osb.service.http.CurrencyEntryServiceSoap
 * @generated
 */
public class CurrencyEntrySoap implements Serializable {
	public static CurrencyEntrySoap toSoapModel(CurrencyEntry model) {
		CurrencyEntrySoap soapModel = new CurrencyEntrySoap();

		soapModel.setCurrencyEntryId(model.getCurrencyEntryId());
		soapModel.setCountryId(model.getCountryId());
		soapModel.setCurrencyCode(model.getCurrencyCode());
		soapModel.setMarketplaceEnabled(model.getMarketplaceEnabled());
		soapModel.setMarketplaceMinPrice(model.getMarketplaceMinPrice());

		return soapModel;
	}

	public static CurrencyEntrySoap[] toSoapModels(CurrencyEntry[] models) {
		CurrencyEntrySoap[] soapModels = new CurrencyEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CurrencyEntrySoap[][] toSoapModels(CurrencyEntry[][] models) {
		CurrencyEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CurrencyEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new CurrencyEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CurrencyEntrySoap[] toSoapModels(List<CurrencyEntry> models) {
		List<CurrencyEntrySoap> soapModels = new ArrayList<CurrencyEntrySoap>(models.size());

		for (CurrencyEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CurrencyEntrySoap[soapModels.size()]);
	}

	public CurrencyEntrySoap() {
	}

	public long getPrimaryKey() {
		return _currencyEntryId;
	}

	public void setPrimaryKey(long pk) {
		setCurrencyEntryId(pk);
	}

	public long getCurrencyEntryId() {
		return _currencyEntryId;
	}

	public void setCurrencyEntryId(long currencyEntryId) {
		_currencyEntryId = currencyEntryId;
	}

	public long getCountryId() {
		return _countryId;
	}

	public void setCountryId(long countryId) {
		_countryId = countryId;
	}

	public String getCurrencyCode() {
		return _currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		_currencyCode = currencyCode;
	}

	public boolean getMarketplaceEnabled() {
		return _marketplaceEnabled;
	}

	public boolean isMarketplaceEnabled() {
		return _marketplaceEnabled;
	}

	public void setMarketplaceEnabled(boolean marketplaceEnabled) {
		_marketplaceEnabled = marketplaceEnabled;
	}

	public double getMarketplaceMinPrice() {
		return _marketplaceMinPrice;
	}

	public void setMarketplaceMinPrice(double marketplaceMinPrice) {
		_marketplaceMinPrice = marketplaceMinPrice;
	}

	private long _currencyEntryId;
	private long _countryId;
	private String _currencyCode;
	private boolean _marketplaceEnabled;
	private double _marketplaceMinPrice;
}